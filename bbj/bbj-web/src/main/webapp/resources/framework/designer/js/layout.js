
var componentItems = []; // 组件数组[二位数组]
$(document).ready(function() {
	dragula([getElementById('container-view')]);
	context.init({
	    fadeSpeed: 100,
	    filter: function ($obj){},
	    above: 'auto',
	    preventDoubleContext: true,
	    compress: false
	});
	
	newRowLayout(); // 新建一个布局
	initRowRightMenu();
	
} );

function initRowRightMenu(){
	context.attach('.row-component', [
		{header: '操作'},
		{text: '列', subMenu: [
			{text: '增加', target:'', action: function(e){
				var itemChildren = $(".selected").children();
				// 清空原浮动样式
				itemChildren.removeClass("text-center");
				itemChildren.removeClass("center-block ");
				itemChildren.removeClass("pull-right");
				// 设置居左
				itemChildren.addClass("pull-left");
			}},
			{text: '居中', target:'', action: function(e){
				$(".selected").children().each(function (){
					var itemField = $(this);
					var tagName = itemField.get(0).tagName;
					if("IMG" == tagName.toUpperCase() || "INPUT" == tagName.toUpperCase()){
						// 清空原浮动样式
						itemField.removeClass("pull-right");
						itemField.removeClass("pull-left");
						// 设置居中
						itemField.addClass("center-block");
					}else {
						// 清空原浮动样式
						itemField.removeClass("pull-right");
						itemField.removeClass("pull-left");
						// 设置居中
						itemField.addClass("text-center");
					}						
				});
			}},
			{text: '居右', target:'', action: function(e){
				var itemChildren = $(".selected").children();
				// 清空原浮动样式
				itemChildren.removeClass("text-center");
				itemChildren.removeClass("center-block ");
				itemChildren.removeClass("pull-left");
				// 设置居右
				itemChildren.addClass("pull-right");
			}}
		]},
		{divider: true},
		{header: '列操作'},
		{text: '选择', subMenu: [
			{text: '选择', target:'', action: function(e){
				$(this).addClass("selected");
			}},
			{text: '清空', target:'', action: function(e){
				$(".item").removeClass("selected");
			}},
			{text: '反选', target:'', action: function(e){
				$(".item").each(function (){
					var itemField = $(this);
					if(itemField.attr("class").indexOf("selected") >=0 ){
						itemField.removeClass("selected");
					} else {
						itemField.addClass("selected");
					}
				});
			}}
		]}
	]);
}
/**
 * 新建一个页面设计布局
 * 
 * @returns
 */
function newRowLayout(){
	$("#container-view").html(""); // 清空

	var rowNum = $("#field-item-row_num").val();
	var containerHtml = "";
	for (var row = 0; row < rowNum; row++) {
		containerHtml  += '<div class = "row row-component" row-data="'+row+'" id="row-component-' + row +'"  ></div>\n';
		componentItems[row] = []; // 组件数组[二位数组]
	}
	$("#container-view").html(containerHtml + '\n');
	
	var dragulaElements = [];
	for (var row = 0; row < rowNum; row++) {
		dragulaElements[row] = getElementById("row-component-" + row);
	}
	dragula(dragulaElements);
	initWidgets(); // 初始化
	// 增加事件监听事件
	$(".row-component").unbind('click').bind('click',function(e){
		e.stopPropagation();
        e.preventDefault();
		var itemField = $(this);
		if(itemField.attr("class").indexOf("selected") >=0 ){
			itemField.removeClass("selected");
		} else {
			itemField.addClass("selected");
		}
	});
	$(".row-component").unbind('dblclick').bind('dblclick',function(e){
		var isFocus = $(this).attr('class').indexOf('row-component-focusing') >= 0;
		if(isFocus){
			e.stopPropagation();
	        e.preventDefault();
			var row = $(this).attr('row-data');
			$("#item-field-col_num").val(componentItems[row].length);
			$("#item-field-row").val(row);
			newColLayout();
		}
	});
	$(".row-component").unbind('mouseover').bind('mouseover',function(e){
		e.stopPropagation();
        e.preventDefault();
        var itemField = $(this);
		if(itemField.attr("class").indexOf("row-component-focusing") <= 0 ){
			itemField.addClass("row-component-focusing");
		} 
	});
	$(".row-component").unbind('mouseout').bind('mouseout',function(e){
		e.stopPropagation();
        e.preventDefault();
        var itemField = $(this);
		if(itemField.attr("class").indexOf("row-component-focusing") >=0 ){
			itemField.removeClass("row-component-focusing");
		} 
	});
}

function newColLayout(){
	$("#modal-new-col").modal('show');
}

function newColLayoutSave(){
	var row = $("#item-field-row").val();
	$("#row-component-" + row).html("");

	var colNum = $("#item-field-col_num").val();
	var baseWidth = 12;
	var colHtml = [];
	for (var col = 0; col < colNum; col++) {
		colHtml[col] = '<div' + 
		' class = "col-sm-'+(baseWidth / colNum)+' col-component' + '" ' + 
		' row-data="'+row+'"' + ' col-data="'+col+'"' + 
		' id="col-component-' + row + '-' + col +'">\n';
		componentItems[row][col] = "";
	}
	for (var i = 0; i < componentItems[row].length; i++) {
			colHtml[i % colNum] += '<div'+
								' class="col-sm-12 item"' +
								' id="item-' + (i % colNum) + '-' + row + '-' + i +'">\n'
								+componentItems[row][i]+ 
								'</div>\n';
	}
	var containerHtml = "";
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += '</div>\n'; // 结束标签
		containerHtml += colHtml[i]; // 进行拼接容器HTML
	}
	$("#row-component-" + row).html('\n' + containerHtml + '\n');
	
	var colDiv = [];
	for (var col = 0; col < colNum; col++) {
		colDiv[col] = getElementById("col-component-" + row + '-' +col);
	}
	dragula(colDiv);
	
	initWidgets();
	$(".col-component").unbind('click').bind('click',function(e){
		var itemField = $(this);
		if(itemField.attr("class").indexOf("selected") >=0 ){
			itemField.removeClass("selected");
		} else {
			itemField.addClass("selected");
		}
		e.stopPropagation();
        e.preventDefault();
	});
	$(".col-component").unbind('mouseover').bind('mouseover',function(e){
		var itemField = $(this);
		if(itemField.attr("class").indexOf("col-component-focusing") <= 0 ){
			itemField.addClass("col-component-focusing");
		} 
		e.stopPropagation();
        e.preventDefault();
	});
	$(".col-component").unbind('mouseout').bind('mouseout',function(e){
		var itemField = $(this);
		if(itemField.attr("class").indexOf("col-component-focusing") >=0 ){
			itemField.removeClass("col-component-focusing");
		} 
		e.stopPropagation();
        e.preventDefault();
	});
	$(".col-component").unbind('dblclick').bind('dblclick',function(e){
		e.stopPropagation();
        e.preventDefault();
        var isFocus = $(this).attr('class').indexOf('col-component-focusing') >= 0;
		if(isFocus){
			var row = $(this).attr('row-data');
			var col = $(this).attr('col-data');
			$("#item-field-col").val(col);
			$("#item-field-col_row").val(row);
			newComponentLayout();
		}
	});
	$("#modal-new-col").modal('hide');
}


function newComponentLayout(){
	$("#modal-new-component").modal('show');
}

function initWidgets(){
	Utils.initWidgets();
}

function getElementById (id) {
  return document.getElementById(id);
}


function newCoSave() {
	var itemData = {};
	itemData.attr = {};
	itemData.col_name = $("#item-field-col_name").val();
	itemData.col_name_comment = $("#item-field-col_name_comment")
			.val();
	itemData.col_type = $("#item-field-col_type").val();
	if(!itemData.col_type){
		itemData.col_type = 'form';
	}
	itemData.col_type_comment = $(
			"#item-field-col_type_comment").val();
	
	$("#modal-new-col").modal('hide');
	var rowNum = $("#item-field-col_num").val();
	var row = $("#item-field-row").val();
	var tagCol = componentItems[row].length % rowNum;
	
	var htmlValue = '<div'+
	' class="col-sm-12 item"' +
	' id="item-' + tagCol + '-' + (componentItems[row].length - 1) +'">'
	+getFieldItem(itemData)+
	'</div>';
	if(componentItems[row].length - 1 - rowNum > 0){
		$('#item-'+tagCol +'-'+(componentItems[row].length - 1 - rowNum)).parent().append(htmlValue);
		initWidgets();
	} else {
		$("#row-component-" + row).html("");

		var baseWidth = 12;
		var colHtml = [];
		for (var i = 0; i < rowNum; i++) {
			colHtml[i] = '<div' + ' class = "col-sm-'+(baseWidth / rowNum)+' row-component' + '" ' + ' id="row-component-' + i +'">\n';
		}
		var name = "";
		var clazz = "";
		for (var i = 0; i < componentItems[row].length; i++) {
				colHtml[i % rowNum] += '    <div'+ ' class="col-sm-12 item"' + ' id="item-' + (i % rowNum) + '-' + i +'">\n'
									    +componentItems[row][i]+ '\n' + 
									   '    </div>\n';
		}
		var containerHtml = "";
		for (var i = 0; i < rowNum; i++) {
			colHtml[i] += '</div>\n'; // 结束标签
			containerHtml += colHtml[i]; // 进行拼接容器HTML
		}
		$("#row-component-" + row).html(containerHtml);

	}
}

function newComponentSave() {
	var itemData = {};
	itemData.attr = {};
	itemData.col_name = $("#item-field-col_name").val();
	itemData.col_name_comment = $("#item-field-col_name_comment")
			.val();
	itemData.col_type = $("#item-field-col_type").val();
	if(!itemData.col_type){
		itemData.col_type = 'form';
	}
	itemData.col_type_comment = $(
			"#item-field-col_type_comment").val();
	
	$("#modal-new-component").modal('hide');
	var rowNum = $("#item-field-col_num").val();
	var col = $("#item-field-col").val();
	var row = $("#item-field-col_row").val();
	
	var item = getComponent(itemData);
	
	$("#col-component-" + row + '-' + col).html("");
}

function getComponent(component){
	var componentType = component.component_type;
	if(componentType == 'form'){
		return 	'    <form>'+component.component__name+'</form>\n';
	}else if (componentType == 'grid'){
		return 	'    <table title="'+component.component__name+'">\n'		
			  	+'    </table>\n';
	}else { // 当成form
		return 	'    <label>'+component.component__name+'</label>\n';
	}
}
function showSource(){
	var containerSource = document.getElementById('container-source');
	containerSource.value = $("#container-view").html();
	var editor = CodeMirror.fromTextArea(containerSource, {
	    mode: "xml",
	    tabMode: "indent",
	    lineWrapping: true,
	    lineNumbers: true
	});
}
function autoFormatRange(){
	var containerSource = document.getElementById('container-source');
	var editor = CodeMirror.fromTextArea(containerSource, {
	});
	editor.autoFormatRange({line:0, ch:0}, {line:totalLines});
}
function getComponentItems(){
	return componentItems;
}

function getFieldItem(field){
	var fieldKeyType = field.attr.field_show_type;
	if(fieldKeyType == 'input'){
		return 	'    <input value='+field.attr.field_name_comment+'></input>\n';
	}else if (fieldKeyType == 'checkbox'){
		return 	'    <div name="item_check" class="checkbox checkbox-info">\n'		
			  	+'        <input type="checkbox" class="styled" aria-label="Single checkbox One"></input>\n'
			  	+'        <label>'+field.attr.field_name_comment+'</label>\n'
			  	+'    </div>\n';
	}else if (fieldKeyType == 'img'){
		return 	'    <img src="'+contextPath+'/resources/dist/img/avatar5.png" title="'+field.attr.field_name_comment+'" class="img-circle"></img>\n';
	}else if (fieldKeyType == 'button'){
		return 	'    <button class="btn btn-info">'+field.attr.field_name_comment+'</button>\n';
	}else if(fieldKeyType == 'date'){
		return 	'    <div class="input-group date">\n'
        	  	+'        <div class="input-group-addon">\n'
        	  	+'            <i class="fa fa-calendar"></i>\n'
        	  	+'        </div>\n'
        	  	+'        <input type="text" class="datepicker form-control pull-right"></input>\n'
        	  	+'    </div>\n';
	}else if(fieldKeyType == 'datetime'){
		 return '    <div class="input-group">\n'
	     	   	+'        <div class="input-group-addon">\n'
	     	   	+'            <i class="fa fa-clock-o"></i>\n'
	     	   	+'        </div>\n'
	     	   	+'        <input type="text" class="form-control pull-right datetimepicker"></input>\n'
	     	   	+'    </div>\n';
	}else if(fieldKeyType == 'textarea'){
		return 	'    <textarea class="form-control" rows="3" placeholder="'+field.attr.field_name_comment+'"></textarea>\n';
	}else if(fieldKeyType == 'select'){
		return 	'    <select class="form-control select2" style="width: 100%">\n'
			  	+'        <option value="0">'+field.attr.field_name_comment+'</option>\n'
			  	+'    </select>\n';
	}else { // 当成label
		return 	'    <label>'+field.attr.field_name_comment+'</label>\n';
	}
}









