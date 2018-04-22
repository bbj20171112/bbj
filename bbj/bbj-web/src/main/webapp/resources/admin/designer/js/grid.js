
$(document).ready(function() {	
	var fields = getDictionary($("#field-item-table_id").val());
	initGrid(fields);
} );


function initGrid(fields){
	if(fields && fields.length > 0){
		var dataColumns = [];
		var columnDefs = [];
		var liFields = $("#li-fields");
		var liFieldsHtml = '<button type="button" onclick="showSelectItems()" class="btn btn-info">字段显示</button> ';
		liFields.html(liFieldsHtml);
		liFieldsHtml += '<div name="item_check" class="checkbox checkbox-inline">'+
							'<input type="checkbox" checked="checked" class="styled item-field" aria-label="Single checkbox One">'+
							'<label data-column="0">勾选框</label>'+
						'</div>';
		for (var i = 0; i < fields.length; i++) {
			var item = {};
			item.data = "attr." + fields[i].attr.field_name;
			item.title = fields[i].attr.field_name_comment;
			liFieldsHtml += '<div name="item_check" id="item-field-'+i+'" class="checkbox checkbox-inline">'+
								'<input type="checkbox" checked="checked" class="styled item-field" aria-label="Single checkbox One">'+
								'<label data-column="'+(i + 1)+'">'+fields[i].attr.field_name_comment+'</label>'+
							'</div>';
			dataColumns.push(item);
		}
		columnDefs.push({// 列渲染
			targets: -1,
        	render: function(data, type, row) {
        		var operatorDiv = '<div>'
    	          	+'<button class = "btn btn-link btn-sm" onclick="row_edit(\''+row.attr.id+'\')" >编辑</button>'
    	          	+'<button class = "btn btn-link btn-sm" onclick="row_delete(\''+row.attr.id+'\')" >删除</button>'
    	          	+'</div>';
            	return operatorDiv;
        	}
        });
		// 增加操作列
		dataColumns.push({
			data: null,
			title : '操作'
		}); 
		liFieldsHtml += '<div name="item_check" class="checkbox checkbox-inline">'+
							'<input type="checkbox" checked="checked" class="styled item-field" aria-label="Single checkbox One">'+
							'<label data-column="-1">操作</label>'+
						'</div>';
		liFields.html(liFieldsHtml);
		var tableId = $("#field-item-table_id").val();
		var tableExp = $('#example').DataTable({
			ajax: {
	            url: contextPath + "/base/designer/grid/simulatedata?table_id=" + tableId,
	        },
	        lengthChange: false	,    
	        scrollCollapse: true,
	        columnDefs : columnDefs,
	        columns: dataColumns
		});
		$('.item-field').change(function (){
			 // Get the column API object
	        var column = tableExp.column($(this).parent().find('label').attr('data-column') );
	 
	        // Toggle the visibility
	        column.visible( ! column.visible() );
		});
		//dragula(getElementById ('li-fields'));
	}
}

function getElementById (id) {
  return document.getElementById(id);
}

function getDictionary(tableId) {
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
		$("#container-form").html("");

		var baseWidth = 12;
		var colHtml = [];
		for (var i = 0; i < colNum; i++) {
			colHtml[i] = '<div' + ' class = "col-sm-'+(baseWidth / colNum)+' item-parent' + '" ' + ' id="item-parent-' + i +'">\n';
		}
		var name = "";
		var clazz = "";
		for (var i = 0; i < fieldItems.length; i++) {
				colHtml[i % colNum] += '    <div'+ ' class="col-sm-12 item"' + ' id="item-' + (i % colNum) + '-' + i +'">\n'
									    +fieldItems[i]+ '\n' + 
									   '    </div>\n';
		}
		var containerHtml = "";
		for (var i = 0; i < colNum; i++) {
			colHtml[i] += '</div>\n'; // 结束标签
			containerHtml += colHtml[i]; // 进行拼接容器HTML
		}
		$("#container-form").html(containerHtml);
		
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
	var fields = getDictionary($("#field-item-table_id").val());
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
/**
 * 新建一个页面设计布局
 * 
 * @returns
 */

function newPage(){
	$("#container-form").html("");

	var colNum = $("#field-item-col_num").val();
	var baseWidth = 12;
	var fieldItems = getFieldItems();
	var colHtml = [];
	for (var i = 0; i < colNum; i++) {
		colHtml[i] = '<div' + 
		' class = "col-sm-'+(baseWidth / colNum)+' item-parent' + '" ' + 
		' id="item-parent-' + i +'">\n';
	}
	var name = "";
	var clazz = "";
	for (var i = 0; i < fieldItems.length; i++) {
			colHtml[i % colNum] += '<div'+
								' class="col-sm-12 item"' +
								' id="item-' + (i % colNum) + '-' + i +'">\n'
								+fieldItems[i]+ 
								'</div>\n';
	}
	var containerHtml = "";
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += '</div>\n'; // 结束标签
		containerHtml += colHtml[i]; // 进行拼接容器HTML
	}
	$("#container-form").html('\n' + containerHtml + '\n');
	
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







