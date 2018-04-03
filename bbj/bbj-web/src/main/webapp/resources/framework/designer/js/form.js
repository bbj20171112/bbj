
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
	
	$("#field-item-col_num").val("3");
	newPage();
	
} );

function getElementById (id) {
  return document.getElementById(id);
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

function getFieldItem(field){
	var fieldKeyType = field.attr.field_show_type;
	if(fieldKeyType == 'input'){
		return '<input value='+field.attr.field_name_comment+'></input>';
	}else if (fieldKeyType == 'checkbox'){
		return '<div name="item_check" class="checkbox checkbox-info">		<input type="checkbox" class="styled" aria-label="Single checkbox One"></input>   	<label>'+field.attr.field_name_comment+'</label>   </div>';
	}else if (fieldKeyType == 'img'){
		return '<img src="'+contextPath+'/resources/dist/img/avatar5.png" title="'+field.attr.field_name_comment+'" class="img-circle"></img>';
	}else if (fieldKeyType == 'button'){
		return '<button>'+field.attr.field_name_comment+'</button>';
	}else if(fieldKeyType == 'date'){
		return '<div class="input-group date">'
        +'<div class="input-group-addon">'
         	+'<i class="fa fa-calendar"></i>'
        +'</div>'
        +'<input type="text" class="datepicker form-control pull-right"></input>'
		+'</div>';
	}else if(fieldKeyType == 'datetime'){
		 return '<div class="input-group">'
	     +   '<div class="input-group-addon">'
	     +     '<i class="fa fa-clock-o"></i>'
	     +   '</div>'
	     +   '<input type="text" class="form-control pull-right datetimepicker"></input>'
	     + '</div>';
      
	}else if(fieldKeyType == 'textarea'){
		return '<textarea class="form-control" rows="3" placeholder="'+field.attr.field_name_comment+'"></textarea>';
	}else if(fieldKeyType == 'select'){
		return '<select class="form-control select2" style="width: 100%">'
					+'<option value="0">'+field.attr.field_name_comment+'</option>'
				+'</select>';
	}else { // 当成label
		return '<label>'+field.attr.field_name_comment+'</label>';
	}
}


function getFieldItems(){
	var fields = getDictionaryFields($("#field-item-table_id").val());
	var fieldItems = [];
	if(fields){
		for (var i = 0; i < fields.length; i++) {
			fields[i].attr.field_show_default = "FieldValue" + i;
			var item = getFieldItem(fields[i]);
			fieldItems[fieldItems.length] = item;
		}
	}
	return fieldItems;
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
		' id="item-parent-' + i +'">';
	}
	var name = "";
	var clazz = "";
	for (var i = 0; i < fieldItems.length; i++) {
			colHtml[i % colNum] += '<div'+
								' class="col-sm-12 item"' +
								' id="item-' + (i % colNum) + '-' + i +'">'
								+fieldItems[i]+
								'</div>';
	}
	var containerHtml = "";
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += '</div>'; // 结束标签
		containerHtml += colHtml[i]; // 进行拼接容器HTML
	}
	$("#container-form").html(containerHtml);
	
	Utils.initWidgets();
	$(".item").click(function (){
		var itemField = $(this);
		if(itemField.attr("class").indexOf("selected") >=0 ){
			itemField.removeClass("selected");
		} else {
			itemField.addClass("selected");
		}
	});
	
	var colDiv = [];
	for (var i = 0; i < colNum; i++) {
		colDiv[i] = getElementById("item-parent-" + i);
	}
	dragula(colDiv);
}









