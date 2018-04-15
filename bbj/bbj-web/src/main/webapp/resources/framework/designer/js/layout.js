var currentRowCols = [];
var fieldItems = [];
$(document).ready(function() {
	$("#field-item-row_num").val("3");
	newPage();
	
} );

function getElementById (id) {
  return document.getElementById(id);
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
	var rowNum = $("#field-item-col_num").val();
	var row = $("#item-field-row_id").val();
	var tagCol = fieldItems[row].length % rowNum;
	
	fieldItems[row][fieldItems[row].length] = getFieldItem(itemData);
	var htmlValue = '<div'+
	' class="col-sm-12 item"' +
	' id="item-' + tagCol + '-' + (fieldItems[row].length - 1) +'">'
	+getFieldItem(itemData)+
	'</div>';
	if(fieldItems[row].length - 1 - rowNum > 0){
		$('#item-'+tagCol +'-'+(fieldItems[row].length - 1 - rowNum)).parent().append(htmlValue);
		initWidgets();
	} else {
		$("#item-parent-" + row).html("");

		var baseWidth = 12;
		var colHtml = [];
		for (var i = 0; i < rowNum; i++) {
			colHtml[i] = '<div' + ' class = "col-sm-'+(baseWidth / rowNum)+' item-parent' + '" ' + ' id="item-parent-' + i +'">\n';
		}
		var name = "";
		var clazz = "";
		for (var i = 0; i < fieldItems[row].length; i++) {
				colHtml[i % rowNum] += '    <div'+ ' class="col-sm-12 item"' + ' id="item-' + (i % rowNum) + '-' + i +'">\n'
									    +fieldItems[row][i]+ '\n' + 
									   '    </div>\n';
		}
		var containerHtml = "";
		for (var i = 0; i < rowNum; i++) {
			colHtml[i] += '</div>\n'; // 结束标签
			containerHtml += colHtml[i]; // 进行拼接容器HTML
		}
		$("#item-parent-" + row).html(containerHtml);
		
		initWidgets();
		var colDiv = [];
		for (var i = 0; i < rowNum; i++) {
			colDiv[i] = getElementById("item-parent-" + row + '-' + i);
		}
		dragula(colDiv);
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
function getFieldItems(){
	return fieldItems;
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

function drowRow(){
	var row = $("#item-field-row_id").val();
	$("#item-parent-" + row).html("");

	var colNum = $("#field-item-col_num").val();
	var baseWidth = 12;
	var fieldItems = getFieldItems();
	var colHtml = [];
	for (var i = 0; i < colNum; i++) {
		colHtml[i] = '<div' + 
		' class = "col-sm-'+(baseWidth / colNum)+' item-parent' + '" ' + 
		' id="item-parent-' + row + '-' + i +'">\n';
	}
	var name = "";
	var clazz = "";
	for (var i = 0; i < fieldItems[row].length; i++) {
			colHtml[i % colNum] += '<div'+
								' class="col-sm-12 item"' +
								' id="item-' + (i % colNum) + '-' + row + '-' + i +'">\n'
								+fieldItems[row][i]+ 
								'</div>\n';
	}
	var containerHtml = "";
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += '</div>\n'; // 结束标签
		containerHtml += colHtml[i]; // 进行拼接容器HTML
	}
	$("#item-parent-" + row).html('\n' + containerHtml + '\n');
	
	initWidgets();
	var colDiv = [];
	for (var i = 0; i < colNum; i++) {
		colDiv[i] = getElementById("item-parent-" + row + i);
	}
	dragula(colDiv);
}
/**
 * 新建一个页面设计布局
 * 
 * @returns
 */
function newPage(){
	$("#container-view").html("");

	var rowNum = $("#field-item-row_num").val();
	var rowHtml = [];
	for (var i = 0; i < rowNum; i++) {
		rowHtml[i] = '<div class = "row item-row" id="item-parent-' + i +'">row'+i+'\n</div>\n';
		currentRowCols[i] = 3;
		fieldItems[i] = [];
	}
	
	var containerHtml = "";
	for (var i = 0; i < rowNum; i++) {
		containerHtml += rowHtml[i]; // 进行拼接容器HTML
	}
	$("#container-view").html('\n' + containerHtml + '\n');
	
	initWidgets();
	var colDiv = [];
	for (var i = 0; i < rowNum; i++) {
		colDiv[i] = getElementById("item-parent-" + i);
	}
	dragula(colDiv);
}

function initWidgets(){
	Utils.initWidgets();
	$(".item-row").unbind('click').bind('click',function(){
		var itemField = $(this);
		if(itemField.attr("class").indexOf("selected") >=0 ){
			itemField.removeClass("selected");
		} else {
			itemField.addClass("selected");
		}
	});
	$(".item-row").unbind('dblclick').bind('dblclick',function(){
		var id = $(this).attr('id').split('-')[2];
		$("#field-item-col_num").val(currentRowCols[id]);
		$("#item-field-row_id").val(id);
		addComponent();
	});
}







