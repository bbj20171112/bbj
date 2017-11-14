/**
 * menu.js
 */

function initOnload(){
	$.ajax({
		url : "retrieve",
		method : "GET",
		// async : false,
		// data : "maxId=" + maxId,
		dataType : "html"
	}).done(function(responseText) {		
		// console.log("responseText:"+responseText);
		// new Menu().sayHello("bage");
		var menuArray = JSON.parse(responseText);
		var container = $("#menu-navbar");
		buildMenuNavbar(menuArray,container);
		
	}).fail(function(jqXHR, textStatus) {
				
	});	
}

function buildMenuNavbar(menuArray,container){
	
	var firstLevelMenu = container.find("li[id=first-level-menu]");
	// 只要第一个
	var root = menuArray[0];
	for (var i = 0; i < root.children.length; i++) {
		var firstLevelMenuData = root.children[i]; // firstLevelMenu
		
		var newFirstLevelMenu = firstLevelMenu.clone(true); // TRUE 包含事件处理
		newFirstLevelMenu.insertBefore(firstLevelMenu);
		newFirstLevelMenu.attr("id",newFirstLevelMenu.attr("id") + "-" + i);
		newFirstLevelMenu.find("span[id=first-level-menu-text]").html(firstLevelMenuData.attributes.text);
		newFirstLevelMenu.show();
		
		var secondLevelMenu = newFirstLevelMenu.find("ul[class=dropdown-menu]").find("li[id=second-level-menu]");
		for (var j = 0; j < firstLevelMenuData.children.length; j++) {
			var secondLevelMenuData = firstLevelMenuData.children[j]; // secondLevelMenu
			
			var newSecondLevelMenu = secondLevelMenu.clone();
			newSecondLevelMenu.insertBefore(secondLevelMenu);
			newSecondLevelMenu.attr("id",newSecondLevelMenu.attr("id")+ "-" + i + "-" + j);
			newSecondLevelMenu.children("a").html(secondLevelMenuData.attributes.text);
			newSecondLevelMenu.show();
		}
	}
	
}

