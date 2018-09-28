/**
 * BBJEntity 对象的常用方法
 * 
 * 
 */
jQuery.extend(bbj, (function(win, $) {

	return {

		getModalContent : function(dictionary){
			
			var tableName = dictionary[0].attr.table_name;
			var colNum = "1";
			var baseWidth = 12;
			var colHtml = [];
			var tabStr = "\t";
			for (var i = 0; i < colNum; i++) {
				colHtml[i] = tabStr + '<div' + ' class = "col-sm-'+(baseWidth / colNum)+' item-parent' + '" ' + ' id="item-parent-' + i +'">\n';
			}
			var name = "";
			var clazz = "";
			for (var i = 0; i < dictionary.length; i++) {
					colHtml[i % colNum] += tabStr +  '	<div'+' class="col-sm-12 item"' + ' id="item-' + (i % colNum) + '-' + i +'">\n'
											+ bbj.getFieldItem(dictionary[i])+ 
										tabStr + '	</div>\n';
			}
			var containerHtml = "";
			for (var i = 0; i < colNum; i++) {
				colHtml[i] += tabStr + '</div>\n'; // 结束标签
				containerHtml += colHtml[i]; // 进行拼接容器HTML
			}
			var modalStr = //'<!-- Modal -->\n'  +
			// '<div class="modal fade" id="modal-'+tableName+'" aria-labelledby="modal-'+tableName+'">\n'  +
			'  <div class="modal-dialog">\n'  +
			'    <div class="modal-content">\n'  +
			'      <div class="modal-header">\n'  +
			'        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n'  +
			'        <h4 class="modal-title">Modal title</h4>\n'  +
			'      </div>\n'  +
			'      <div class="modal-body">\n'  +
			'      	<form class="form-horizontal">\n'  +
			'         <div class="box-body">\n'  +                
			'\n' + containerHtml + '\n' + 
			'         </div>\n'  +
			'      </div>\n'  +
			'      <div class="modal-footer">\n'  +
			'        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>\n'  +
			'        <button type="button" class="btn btn-primary" onclick="insertOrUpdateSave()">保存</button>\n'  +
			'      </div>\n'  +
			'     </div>\n'  +
			'    </div>\n'  +
			'  </div>\n' ; // +
			// '</div>';
			return modalStr;
			
		},

		getFieldItem : function (field){
				
			var labelCol = "3";
			var contentCol = "9";
			var itemTabStr = "\t\t\t";
			var fieldKeyType = field.attr.field_show_type;
			var idStr = "item_field-"+field.attr.table_name+"-" + field.attr.field_name;
			if(fieldKeyType == 'input'){
				var hiddenAttr = "";
				if(field.id == field.attr.field_name){ // 主键ID
					hiddenAttr = "hidden";
				}
				var item =  itemTabStr + '<div class="form-group ' + hiddenAttr + '">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
							itemTabStr + '  		<input id="'+idStr+'" type="input" class="form-control" placeholder="'+field.attr.field_name_comment+'">\n' + 
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}else if (fieldKeyType == 'checkbox'){
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<div class="col-sm-offset-'+labelCol+' col-sm-'+contentCol+'">\n' + 
							itemTabStr + '    <div name="item_check" class="checkbox checkbox-info">\n'	+
							itemTabStr + '        <input id="'+idStr+'" type="checkbox" class="styled" aria-label="Single checkbox One"></input>\n' + 
							itemTabStr + '        <label>'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '    </div>\n' + 
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}else if (fieldKeyType == 'img'){
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
							itemTabStr + '    <input type="file" id="'+idStr+'">\n' + 
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}else if (fieldKeyType == 'button'){
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
							itemTabStr + '    <button id="'+idStr+'" class="btn btn-info"></button>\n' + 
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}else if(fieldKeyType == 'date'){
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
							itemTabStr + '		<div class="input-group">\n' + 
							itemTabStr + '        <div class="input-group-addon">\n' + 
							itemTabStr + '            <i class="fa fa-calendar"></i>\n' + 
							itemTabStr + '        </div>\n' + 
							itemTabStr + '        <input type="text" class="form-control pull-right datepicker"></input>\n' + 
							itemTabStr + '    	</div>\n' + 
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;				
			}else if(fieldKeyType == 'datetime'){
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
							itemTabStr + '		<div class="input-group">\n' + 
							itemTabStr + '        <div class="input-group-addon">\n' + 
							itemTabStr + '            <i class="fa fa-calendar"></i>\n' + 
							itemTabStr + '        </div>\n' + 
							itemTabStr + '        <input type="text" class="form-control pull-right datetimepicker"></input>\n' + 
							itemTabStr + '    	</div>\n' + 
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}else if(fieldKeyType == 'textarea'){
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+' date">\n' + 
							itemTabStr + '    <textarea id="'+idStr+'" class="form-control" rows="3" placeholder="'+field.attr.field_name_comment+'"></textarea>\n' + 
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}else if(fieldKeyType == 'select'){
				
				var optionsHtml = [];
				if(field.attr.field_reference_table_name){ // 有参照值
					var references= bbj.getBBJEntityReference(field.attr.field_reference_table_name,
							field.attr.field_reference_table_field_name,
							field.attr.field_reference_table_field_value);
					var values = field.attr.field_reference_table_field_value.split(',');
					for (var i = 0; i < references.length; i++) {
						optionsHtml +='    	<option value="'+references[i].attr.reference_field_name+'">'+references[i].attr.reference_field_value[0][values[0]]+'</option>\n' ;
					}
				}
				
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
							itemTabStr + '    <select id="'+idStr+'" class="form-control select2" style="width: 100%">\n' +
							itemTabStr + optionsHtml +
							itemTabStr + '    </select>\n' +
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}else { // 当成label
				var item =  itemTabStr + '<div class="form-group">\n' + 
							itemTabStr + '	<label for="'+idStr+'" class="col-sm-'+labelCol+' control-label">'+field.attr.field_name_comment+'</label>\n' + 
							itemTabStr + '	<div class="col-sm-'+contentCol+'">\n' + 
							itemTabStr + '    <label id="'+idStr+'" >'+field.attr.field_name_comment+'</label>\n' +
							itemTabStr + '	</div>\n' + 
							itemTabStr + '</div>\n';
				return 	item;
			}
		},
		
		getDataTableDefs : function(dictionary){
			
			if(dictionary == null || dictionary == ""){
				return "";
			}
			var columnDefs =  [{
	 			targets: -1,
	 			render: function(data, type, row) { 
		          	var operatorDiv = '<div>'
		          	+'<i class="fa fa-edit" title="编辑"></i>'
		          	+'<button class = "btn btn-link btn-sm" onclick="updateOption(\''+row.attr[row["id"]]+'\')" >编辑</button>'
		          	+'<button class = "btn btn-link btn-sm" onclick="deleteOption(\''+row.attr[row["id"]]+'\')" >删除</button>'
		          	+'</div>';
	          	return operatorDiv;
	         }}];
			
			// 获取配置信息[默认全部进行展示，当数据库新增表结构时候会进行同步更新展示]
			var columns = [];
			for(var i = 0; i < dictionary.length ;i ++){
				if(dictionary[i].attr.field_reference_table_name){ // 有参照值
					var fieldName = dictionary[i].attr.field_name;
					var foreignField = dictionary[i].attr.field_reference_table_field_value;
					var fieldForeignKey = "foreigner_" + fieldName + "_" + foreignField;// 外键命名规则
					var index = 1 + i; // 第一个是勾选框
					columnDefs.push({
			 			targets: index,
			 			render: function(data, type, row) { 
			 				return row.attr[fieldForeignKey];
			 			}});
				}
				
				columns.push({
					data : 'attr.' + dictionary[i].attr.field_name, // 数据列key定义
					title : dictionary[i].attr.field_name_comment , // 数据列标题
				});
			}
			// 增加操作列
			columns.push({
				data : null,
				title : "编辑" ,
			});
			
			return {
					columnDefs : columnDefs,
					columns : columns
			};
		},

		/**
		 * 获取外键
		 */
		getBBJEntityReference : function(referenceTableName,referenceFieldName,referenceFieldValue){
			if(referenceTableName == null || referenceTableName == ""){
				return "";
			}
			if(referenceFieldName == null || referenceFieldName == ""){
				return "";
			}
			if(referenceFieldValue == null || referenceFieldValue == ""){
				return "";
			}
			var reference = [];
			Utils.ajax({
				url : contextPath + "/base/dictionary/reference/all?" +
						"reference_table_name=" + referenceTableName + 
						"&reference_field_name=" + referenceFieldName + 
						"&reference_field_value=" + referenceFieldValue + 
						"",
				type : 'GET',
				async : false, // 同步
				success : function(response) {
					if(response.code = '200'){
						reference = response.data;
					}
				}
			});
			return reference;
		},
		
		/**
		 * 获取数据字典
		 */
		getBBJEntityDictionary : function(tableName){
			
			if(tableName == null || tableName == ""){
				return "";
			}
			var dictionary = {};
			Utils.ajax({
				url : contextPath + "/admin/dictionary/field/all?table_name=" + tableName,
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
						var fieldKeyType = dictionary[i].attr.field_show_type;
						if(fieldKeyType == 'input'){
							element.val(bbjEntity.attr[fieldName]); // 赋值
						} else if(fieldKeyType == 'checkbox'){
							//element.val(bbjEntity.attr[fieldName]); // 赋值
						} else if(fieldKeyType == 'img'){
							element.attr("src",bbjEntity.attr[fieldName]); // 赋值
						} else if(fieldKeyType == 'button'){
							element.val(bbjEntity.attr[fieldName]); // 赋值
						} else if(fieldKeyType == 'date'){
							//element.val(bbjEntity.attr[fieldName]); // 赋值
						} else if(fieldKeyType == 'datetime'){
							//element.val(bbjEntity.attr[fieldName]); // 赋值
						} else if(fieldKeyType == 'textarea'){
							element.val(bbjEntity.attr[fieldName]); // 赋值
						} else if(fieldKeyType == 'select'){
							element.val(bbjEntity.attr[fieldName]).trigger('change'); // 赋值
						} else { // 当成label
							element.html(bbjEntity.attr[fieldName]); // 赋值
						}
						
						
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