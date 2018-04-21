var fieldItems = [];

var items = [];
var clicker = {};

$(document).ready(function() {	
	
	// addRow();
	
	initContext();
	
} );


/**
 * 新建一个页面设计布局
 * 
 * @returns
 */

function addRow(){
	$("#modal-add-row").modal('show');
}

function addRowSure(){
	var colNum = $("#select-col_num").val();
	if(colNum){} else {
		colNum = 1;
	}
	var baseNum = 12;
	var colHtml = []; // 拼凑列的HTML
	
	var currentRow = items.length;
	var rowHtml = '<div class="row item-row" data-column="'+currentRow+'" >';
	for (var i = 0; i < colNum; i++) {
		colHtml[i] = '<div' + 
		' class = "col-sm-'+(baseNum / colNum)+' item-col' + '" ' + 
		' id="item-col-' + currentRow + '-' + i +'">\n';
		
	}	
	items[currentRow] = {};
	items[currentRow].data = [];
	items[currentRow].colNum = colNum;
		
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += '</div>\n'; // 结束标签
		rowHtml += colHtml[i]; // 进行拼接容器HTML
	}
	rowHtml += "</div>";
	
	if(items.length == 1){ // 首次加入
		$("#div-row-list").html("");
	}
	$("#div-row-list").html($("#div-row-list").html() + '\n' + rowHtml + '\n');
	
	$("#modal-add-row").modal('hide'); // 隐藏对话框
	// 可拖动
	var dragulaItems = [];
	for(var row = 0; row < items.length; row++){
		colNum = items[row].colNum;
		for (var col = 0; col < colNum; col++) {
			dragulaItems[dragulaItems.length] = getElementById("item-col-" + row + '-' + col);
		}
	}
	dragula(dragulaItems);
	initWidgets();
}


function addComponent(){
	$("#modal-new-component").modal('show');
}


function addComponentSure() {
	
	var currentRow = new Number(clicker.attr('data-column'));
	
	var component = {};
	component.attr = {};
	component.attr.component_name = $("#item-component-component_name").val();
	component.attr.component_title = $("#item-component-component_title")
			.val();
	component.attr.component_show_type = $("#item-component-component_show_type").val();
	if(!component.attr.component_show_type){
		component.attr.component_show_type = 'label';
	}
	
	var colNum = items[currentRow].colNum; // 列数
	var length = items[currentRow].data.length;
	var tagCol = length % colNum;
	
	items[currentRow].data[length] = getComponentItem(component);
	
	var htmlValue = '<div class="col-sm-12 item"  id="item-' + currentRow + '-' + length +'">'
						+items[currentRow].data[length]+
					'</div>';
	if(length - colNum >= 0){ // 超出原数目，直接在后面进行添加
		$('#item-' + currentRow + '-'+tagCol).parent().append(htmlValue);
	} else { // 还能挂组件		
		$("#item-col-" + currentRow + '-' + length).html(htmlValue);		
	}
	$("#modal-new-component").modal('hide');
	initWidgets();
}


function getComponentItem(component){
	component.attr.component_name = $("#item-component-component_name").val();
	component.attr.component_title = $("#item-component-component_title")
			.val();
	component.attr.component_show_type = $("#item-component-component_show_type").val();
	if(!component.attr.component_show_type){
		component.attr.component_show_type = 'label';
	}
	
	var componentShowType = component.attr.component_show_type;
	if(componentShowType == 'form'){
		return 	'    <form>'+component.attr.component_name+'</form>\n';
	}else if (componentShowType == 'grid'){
		return 	'    <table title="'+component.attr.component_name+'">\n'		
			  	+'    </table>\n';
	}else if(componentShowType == 'input'){
		return 	'    <input value='+component.attr.component_name+'></input>\n';
	}else if (componentShowType == 'checkbox'){
		return 	'    <div name="item_check" class="checkbox checkbox-info">\n'		
			  	+'        <input type="checkbox" class="styled" aria-label="Single checkbox One"></input>\n'
			  	+'        <label>'+component.attr.component_name+'</label>\n'
			  	+'    </div>\n';
	}else if (componentShowType == 'img'){
		return 	'    <img src="'+contextPath+'/resources/dist/img/avatar5.png" title="'+component.attr.component_title+'" class="img-circle"></img>\n';
	}else if (componentShowType == 'button'){
		return 	'    <button class="btn btn-info">'+component.attr.component_name+'</button>\n';
	}else if(componentShowType == 'date'){
		return 	'    <div class="input-group date">\n'
        	  	+'        <div class="input-group-addon">\n'
        	  	+'            <i class="fa fa-calendar"></i>\n'
        	  	+'        </div>\n'
        	  	+'        <input type="text" class="datepicker form-control pull-right"></input>\n'
        	  	+'    </div>\n';
	}else if(componentShowType == 'datetime'){
		 return '    <div class="input-group">\n'
	     	   	+'        <div class="input-group-addon">\n'
	     	   	+'            <i class="fa fa-clock-o"></i>\n'
	     	   	+'        </div>\n'
	     	   	+'        <input type="text" class="form-control pull-right datetimepicker"></input>\n'
	     	   	+'    </div>\n';
	}else if(componentShowType == 'textarea'){
		return 	'    <textarea class="form-control" rows="3" placeholder="'+component.attr.component_name+'"></textarea>\n';
	}else if(componentShowType == 'select'){
		return 	'    <select class="form-control select2" style="width: 100%">\n'
			  	+'        <option value="0">'+component.attr.component_name+'</option>\n'
			  	+'    </select>\n';
	}else { // 当成label
		return 	'    <label>'+component.attr.component_name+'</label>\n';
	}
}

function initWidgets(){
	Utils.initWidgets();
	$(".item").unbind('click').bind('click',function(){
		var itemField = $(this);
		if(itemField.attr("class").indexOf("selected") >=0 ){
			itemField.removeClass("selected");
		} else {
			itemField.addClass("selected");
		}
	});
	var itemRow = $(".item-row");
	itemRow.unbind('dblclick').bind('dblclick',function(e){
		e.stopPropagation();
        e.preventDefault();
        clicker = $(this);
		addComponent();
	});	
}


function getElementById (id) {
  return document.getElementById(id);
}

function editFormSure(){
	$("#modal-edit-form").modal('hide');
}
function getDictionaryFields(tableId) {
	var returnData = {};
	Utils.ajax({
		url : contextPath + "/base/dictionary/field/all?table_id=" + tableId,
		type : 'GET',
		async : false, // 同步
		success : function(data) {
			if(true){
				returnData = data.data;
			}
		}
	});
	return returnData;
}


function getFieldItems(){
	return fieldItems;
}
function initFieldItems(){
	var fields = getDictionaryFields($("#field-item-table_id").val());
	fieldItems = [];
	if(fields){
		for (var i = 0; i < fields.length; i++) {
			fields[i].attr.field_show_default = "FieldValue" + i;
			var item = getFieldItem(fields[i]);
			fieldItems[fieldItems.length] = item;
		}
	}
	//return fieldItems;
}


function showSource(){
	var containerSource = document.getElementById('container-source');
	containerSource.value = $("#container-form").html();
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


function initContext(){
	context.init({
	    fadeSpeed: 100,
	    filter: function ($obj){},
	    above: 'auto',
	    preventDoubleContext: true,
	    compress: false
	});		
	context.attach('.item', [
		{header: '排列'},
		{text: '浮动', subMenu: [
			{text: '居左', target:'', action: function(e){
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
		{header: '操作'},
		{text: '选择', subMenu: [
			{text: '全选', target:'', action: function(e){
				$(".item").addClass("selected");
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
		]},
//		{text: '组件', subMenu: [
//			{text: '添加', target:'', action: function(e){
//				clicker = context.getClicker().parent();
//				addComponent();
//			}}
//		]}
		
	]);
	
	context.attach('.item > form', [		
		{header: '表单操作'},		
		{text: '子组件', subMenu: [
			{text: '添加', target:'', action: function(e){
				clicker = context.getClicker();
				$("#modal-edit-form").modal('show');
			}}
		]}
	]);

}



