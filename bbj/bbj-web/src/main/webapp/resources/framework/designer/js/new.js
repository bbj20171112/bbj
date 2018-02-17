
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
	
	$("#navbar-col-num-input").val("3");
	newPage();
	
} );

function getElementById (id) {
  return document.getElementById(id);
}

function getFieldItems(){
	var fieldItems = [];
	fieldItems[0] = '<input value="这是字段1"></input>';
	fieldItems[1] = '<label>这是字段2</label>';
	fieldItems[2] = '<select><option label="这是字段3" value="3" /></select>';
	fieldItems[3] = '<button>这是字段4</button>';
	fieldItems[4] = '<img src="../../resources/dist/img/avatar5.png"/>';
	return fieldItems;
}

/**
 * 新建一个页面设计布局
 * 
 * @returns
 */

function newPage(){
	$("#container").html("");

	var colNum = $("#navbar-col-num-input").val();
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









