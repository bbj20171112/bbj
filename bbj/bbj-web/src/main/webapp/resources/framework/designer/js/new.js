
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
} );

function getElementById (id) {
  return document.getElementById(id);
}


