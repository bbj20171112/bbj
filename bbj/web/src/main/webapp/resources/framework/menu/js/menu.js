/**
 * menu.js
 */

function Menu(){
	this.buildMenu = function(menuArray){
		if(menuArray == null || menuArray == undefined){
			return [];
		}
		//  temp.clone(true).appendTo($("#pages"));//把所有匹配的元素追加到另一个、指定的元素元素集合中。
	}
	this.sayHello = function(word){
		console.log("hello," + word);
	}
}
var menu = new Menu();

