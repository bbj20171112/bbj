
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
	
	$(".item").click(function (){
		/*$(this).children().each(function (){
			var itemField = $(this);
			if(itemField.attr("class").indexOf("selected") >=0 ){
				itemField.removeClass("selected");
			} else {
				itemField.addClass("selected");
			}
		});*/
	});

	context.attach('.box-body', [
		{header: 'Menu1'},
		{text: 'float', subMenu: [
			{header: '左右'},
			{text: 'left', target:'', action: function(e){
					/*$(".selected").each(function(){
						$(this).addClass("pull-left");
					});*/
			}},
			{text: 'right', target:'', action: function(e){
				$(".selected").addClass("pull-right");
		}}
		]},
		{divider: true},
		{header: 'Menu2'},
		{text: 'todo', subMenu: [
			{header: 'menu21'},
			{text: '2nd Level', subMenu: [
				{header: 'You like?'},
				{text: '3rd Level!?', subMenu: [
					{header: 'Of course you do'},
					{text: 'todo1', subMenu: [
						{header:'todo2'},
						{text: 'todo3', subMenu: [
							{header: 'todo4!'},
							{text: 'Shieeet', subMenu: [
								{header: 'todo5'},
								{text: '_blank', href: '', target:'_blank', action: function(){
									alert(this.pathname + "," + this.innerHTML);
								}}
							]}
						]}
					]}
				]}
			]}
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
		return '<div name="item_check" class="checkbox checkbox-info">		<input type="checkbox" class="styled" aria-label="Single checkbox One">   	<label>'+field.attr.field_name_comment+'</label>   </div>';
	}else if (fieldKeyType == 'img'){
		return '<img src="'+contextPath+'/resources/dist/img/avatar5.png" title="'+field.attr.field_name_comment+'"></img>';
	}else if (fieldKeyType == 'button'){
		return '<button>'+field.attr.field_name_comment+'</button>';
	}else if(fieldKeyType == 'date'){
		return '<label>'+field.attr.field_name_comment+'</label>';
	}else if(fieldKeyType == 'datetime'){
		return '<label>'+field.attr.field_name_comment+'</label>';
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

/**
 * 新建一个页面设计布局
 * 
 * @returns
 */

function newPage(){
	$("#container").html("");

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
	$("#container").html(containerHtml);
	var colDiv = [];
	for (var i = 0; i < colNum; i++) {
		colDiv[i] = getElementById("item-parent-" + i);
	}
	dragula(colDiv);
}









