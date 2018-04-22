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
				success : function(data) {
					if(data.code = '200'){
						dictionary = data.data;
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
			} else { // 如果没有前置，默认为 item-field
				idPrefix = "item-field";
			}
			
			for (var attr in bbjEntity) { // 便利对象
				var e = $("#"+idPrefix+"-" + attr);
				if(e){
					var value = bbjEntity[attr];
					if(value){
						e.val(value); // 赋值
					} else {
						e.val("");
					}
				}
			}
			
		},
		
		/**
		 * bbjEntity : 对象
		 * dictionary : 实体对象的数据字典
		 * idPrefix : ID 前缀
		 * 
		 */
		getBBJEntityValue : function(bbjEntity,dictionary,idPrefix){
			
			if(idPrefix){
			} else { // 如果没有前置，默认为 item-field
				idPrefix = "item-field";
			}
			
			for (var attr in bbjEntity) { // 便利对象
				var e = $("#"+idPrefix+"-" + attr);
				if(e){
					var value = e.val();
					if(value){
						bbjEntity[attr] = value; // 赋值
					} else {
						bbjEntity[attr] = null;
					}
				}
			}
			return bbjEntity;
		},		
	}	
	
}(this, jQuery)));