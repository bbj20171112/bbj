var fieldItems = [];
$(document).ready(function() {
	dragula([
		getElementById('item-parent1'), 
		getElementById('item-parent2'),
		getElementById('item-parent3')
		]);
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
		]}
	]);
	
	$("#field-item-col_num").val("1");
	initFieldItems();
	newPage();
} );

function getElementById (id) {
  return document.getElementById(id);
}

function getDictionaryFields(tableId) {
	var returnData = {};
	Utils.ajax({
		url : contextPath + "/admin/dictionary/field/all?table_id=" + tableId,
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

function getFieldItem(field){
		
	var labelCol = "3";
	var contentCol = "9";
	var itemTabStr = "\t\t\t";
	var fieldKeyType = field.attr.field_show_type;
	var idStr = "item_field-"+field.attr.table_name+"-" + field.attr.field_name;
	if(fieldKeyType == 'input'){
		var hiddenAttr = "";
		if(field.id == field.attr.field_name){ // 主键ID
			hiddenAttr = "hidden";
		}
		var item =  itemTabStr + '<div class="form-group ' + hiddenAttr + '">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
					itemTabStr + '  		<input id="'+idStr+'" type="input" class="form-control" placeholder="'+field.attr.field_name_comment+'">\n' + 
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}else if (fieldKeyType == 'checkbox'){
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<div class="col-sm-offset-'+labelCol+' col-sm-'+contentCol+'">\n' + 
					itemTabStr + '    <div name="item_check" class="checkbox checkbox-info">\n'	+
					itemTabStr + '        <input id="'+idStr+'" type="checkbox" class="styled" aria-label="Single checkbox One"></input>\n' + 
					itemTabStr + '        <label>'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '    </div>\n' + 
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}else if (fieldKeyType == 'img'){
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
					itemTabStr + '    <input type="file" id="'+idStr+'">\n' + 
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}else if (fieldKeyType == 'button'){
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
					itemTabStr + '    <button id="'+idStr+'" class="btn btn-info"></button>\n' + 
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}else if(fieldKeyType == 'date'){
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
					itemTabStr + '		<div class="input-group">\n' + 
					itemTabStr + '        <div class="input-group-addon">\n' + 
					itemTabStr + '            <i class="fa fa-calendar"></i>\n' + 
					itemTabStr + '        </div>\n' + 
					itemTabStr + '        <input type="text" class="form-control pull-right datepicker"></input>\n' + 
					itemTabStr + '    	</div>\n' + 
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;				
	}else if(fieldKeyType == 'datetime'){
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
					itemTabStr + '		<div class="input-group">\n' + 
					itemTabStr + '        <div class="input-group-addon">\n' + 
					itemTabStr + '            <i class="fa fa-calendar"></i>\n' + 
					itemTabStr + '        </div>\n' + 
					itemTabStr + '        <input type="text" class="form-control pull-right datetimepicker"></input>\n' + 
					itemTabStr + '    	</div>\n' + 
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}else if(fieldKeyType == 'textarea'){
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+' date">\n' + 
					itemTabStr + '    <textarea id="'+idStr+'" class="form-control" rows="3" placeholder="'+field.attr.field_name_comment+'"></textarea>\n' + 
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}else if(fieldKeyType == 'select'){
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
					itemTabStr + '    <select id="'+idStr+'" class="form-control select2" style="width: 100%">\n' +
					itemTabStr + '    	<option value="0">'+field.attr.field_name_comment+'</option>\n' +
					itemTabStr + '    </select>\n' +
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}else { // 当成label
		var item =  itemTabStr + '<div class="form-group">\n' + 
					itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
					itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
					itemTabStr + '    <label id="'+idStr+'" >'+field.attr.field_name_comment+'</label>\n' +
					itemTabStr + '	</div>\n' + 
					itemTabStr + '</div>\n';
		return 	item;
	}
}

function addComponent(){
	$("#modal-new-field").modal('show');
}

function newFieldSave() {
	var itemData = {};
	itemData.attr = {};
	itemData.attr.id = $("#item-field-id").val();
	itemData.attr.table_id = $("#item-field-table_id").val();
	itemData.attr.field_name = $("#item-field-field_name").val();
	itemData.attr.field_name_comment = $("#item-field-field_name_comment")
			.val();
	itemData.attr.field_type = $("#item-field-field_type").val();
	if(!itemData.attr.field_type){
		itemData.attr.field_type = 'varchar(64)';
	}
	itemData.attr.field_type_comment = $(
			"#item-field-field_type_comment").val();
	itemData.attr.field_show_type = $("#item-field-field_show_type").val();
	if(!itemData.attr.field_show_type){
		itemData.attr.field_show_type = 'label';
	}
	itemData.attr.field_show_type_comment = $(
			"#item-field-field_show_type_comment").val();
	
	
	itemData.attr.field_constraint = $("#item-field-field_constraint").val();
	itemData.attr.field_constraint_comment = $(
			"#item-field-field_constraint_comment").val();
	itemData.attr.field_reference = $("#item-field-field_reference").val();
	itemData.attr.field_reference_comment = $(
			"#item-field-field_reference_comment").val();
	itemData.attr.field_remark = $("#item-field-field_remark").val();

	//Utils.getBBJEntityValue(jsonData);
	$("#modal-new-field").modal('hide');
	var colNum = $("#field-item-col_num").val();
	var tagCol = fieldItems.length % colNum;
	
	fieldItems[fieldItems.length] = getFieldItem(itemData);
	var htmlValue = '<div'+
	' class="col-sm-12 item"' +
	' id="item-' + tagCol + '-' + (fieldItems.length - 1) +'">'
	+getFieldItem(itemData)+
	'</div>';
	if(fieldItems.length - 1 - colNum > 0){
		$('#item-'+tagCol +'-'+(fieldItems.length - 1 - colNum)).parent().append(htmlValue);
		initWidgets();
	} else {
		$("#container-form").find("div[class=box-body]").html("");

		var tabStr = "\t\t";
		var baseWidth = 12;
		var colHtml = [];
		for (var i = 0; i < colNum; i++) {
			colHtml[i] = tabStr + '<div' + ' class = "col-sm-'+(baseWidth / colNum)+' item-parent' + '" ' + ' id="item-parent-' + i +'">\n';
		}
		var name = "";
		var clazz = "";
		for (var i = 0; i < fieldItems.length; i++) {
				colHtml[i % colNum] += tabStr + '    <div'+ ' class="col-sm-12 item"' + ' id="item-' + (i % colNum) + '-' + i +'">\n'
									    +fieldItems[i]+ '\n' + 
									    tabStr + '    </div>\n';
		}
		var containerHtml = "";
		for (var i = 0; i < colNum; i++) {
			colHtml[i] += tabStr + '</div>\n'; // 结束标签
			containerHtml += colHtml[i]; // 进行拼接容器HTML
		}
		$("#container-form").find("div[class=box-body]").html("\n" + containerHtml + "\n");
		
		initWidgets();
		var colDiv = [];
		for (var i = 0; i < colNum; i++) {
			colDiv[i] = getElementById("item-parent-" + i);
		}
		dragula(colDiv);
	}
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



function generateProgramCode(){
	
	var formStr = getFormStr();
	$("#input-program-form-source").html(escapeSpecialChars(formStr));
	
	var modalStr = getModalStr($('#field-item-table_name').val());
	$("#input-program-modal-source").html(escapeSpecialChars(modalStr));
	
	$("#modal-source-view").modal("show");
}
function escapeSpecialChars(sourceStr){
	return sourceStr.replace(/</g,"&lt;").replace(/>/g,"&gt;")
}

function getModalStr(tableName){
	var modalStr = '<!-- Modal -->\n'  +
		'<div class="modal fade" id="modal-'+tableName+'" aria-labelledby="modal-'+tableName+'">\n'  +
		'  <div class="modal-dialog">\n'  +
		'    <div class="modal-content">\n'  +
		'      <div class="modal-header">\n'  +
		'        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n'  +
		'        <h4 class="modal-title">Modal title</h4>\n'  +
		'      </div>\n'  +
		'      <div class="modal-body">\n'  +
		'      	<form class="form-horizontal">\n'  +
		'         <div class="box-body">\n'  +                
		$("#container-form").find("div[class=box-body]").html() + 
		'         </div>\n'  +
		'      </div>\n'  +
		'      <div class="modal-footer">\n'  +
		'        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>\n'  +
		'        <button type="button" class="btn btn-primary" onclick="insertOrUpdateSave()">保存</button>\n'  +
		'      </div>\n'  +
		'     </div>\n'  +
		'    </div>\n'  +
		'  </div>\n'  +
		'</div>';
	return modalStr;
}
function getFormStr(){
	var formStr = $("#container-form").html();
	return formStr ;
}

/**
 * 新建一个页面设计布局
 * 
 * @returns
 */

function newPage(){
	$("#container-form").find("div[class=box-body]").html("");

	var colNum = $("#field-item-col_num").val();
	var baseWidth = 12;
	var fieldItems = getFieldItems();
	var colHtml = [];
	var tabStr = "\t";
	for (var i = 0; i < colNum; i++) {
		colHtml[i] = tabStr + '<div' + ' class = "col-sm-'+(baseWidth / colNum)+' item-parent' + '" ' + ' id="item-parent-' + i +'">\n';
	}
	var name = "";
	var clazz = "";
	for (var i = 0; i < fieldItems.length; i++) {
			colHtml[i % colNum] += tabStr +  '	<div'+' class="col-sm-12 item"' + ' id="item-' + (i % colNum) + '-' + i +'">\n'
									+fieldItems[i]+ 
								tabStr + '	</div>\n';
	}
	var containerHtml = "";
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += tabStr + '</div>\n'; // 结束标签
		containerHtml += colHtml[i]; // 进行拼接容器HTML
	}
	$("#container-form").find("div[class=box-body]").html('\n' + containerHtml + '\n');
	
	initWidgets();
	var colDiv = [];
	for (var i = 0; i < colNum; i++) {
		colDiv[i] = getElementById("item-parent-" + i);
	}
	dragula(colDiv);
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
}







