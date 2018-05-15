/**
 * BBJEntity 对象的常用方法
 * 
 * 
 */
jQuery.extend(bbj, (function(win, $) {

	return {


		/**
		 * obj : bbj 对象
		 * prefix : ID 前缀
		 * 
		 */
		getBBJEntityDictionary : function(tableId){
			
			if(tableId == null || tableId == ""){
				return "";
			}
			var dictionary = {};
			Utils.ajax({
				url : contextPath + "/admin/dictionary/field/all?table_id=" + tableId,
				type : 'GET',
				async : false, // 同步
				success : function(response) {
					if(response.code = '200'){
						dictionary = response.data;
					}
				}
			});
			return dictionary;
		},
		
		/**
		 * bbjEntity : 对象
		 * dictionary : 实体对象的数据字典
		 * idPrefix : ID 前缀
		 * 
		 */
		setBBJEntityValue : function(bbjEntity,dictionary,idPrefix){
			
			if(idPrefix){
			} else { // 如果没有前置，默认为 item_field
				idPrefix = "item_field";
			}
			
			for (var i = 0;i < dictionary.length ;i ++) { // 便利对象
				var fieldName = dictionary[i].attr.field_name;
				var element = $("#"+idPrefix + "-" + dictionary[i].attr.table_name +"-" + fieldName);
				if(element){
					if(bbjEntity && bbjEntity.attr){
						element.val(bbjEntity.attr[fieldName]); // 赋值
					} else {
						element.val("");
					}
				}
			}
			
		},
		
		/**
		 * dictionary : 实体对象的数据字典
		 * idPrefix : ID 前缀
		 * 
		 */
		getBBJEntityValue : function(dictionary,idPrefix){
			
			if(idPrefix){
			} else { // 如果没有前置，默认为 item_field
				idPrefix = "item_field";
			}
			var bbjEntity = {};
			for (var i = 0;i < dictionary.length ;i ++) { // 便利对象
				var fieldName = dictionary[i].attr.field_name;
				var element = $("#"+idPrefix + "-" + dictionary[i].attr.table_name +"-" + fieldName);
				if(element){
					var value = element.val();
					if(value){
						bbjEntity[fieldName] = value; // 获取值
					} else {
						bbjEntity[fieldName] = null;
					}
				}
			}
			return bbjEntity;
		},		
	}	
	
}(this, jQuery)));