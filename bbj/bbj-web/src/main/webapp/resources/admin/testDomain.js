
var tableId = "1525515537026"; // 当前表ID
// var tableName = "admin_test";
var dictionary = {}; // 数据字典
var baseURL = contextPath + "/admin/testDomain";
var tableDataTable = {};

$(document).ready(function(){
	// 获取数据字典
	dictionary = bbj.getBBJEntityDictionary(tableId);
	//dictionary.tableName = tableName;
	// 
	// dictionary = [{"tableName":"admin_dictionary_field","attr":{"delete_state":"0","create_time":"2018-05-05 18:19:44.0","field_type_comment":"ID","update_staff_id":null,"table_id":"1525515537026","field_constraint":null,"field_name":"id","field_show_type_comment":"ID","create_staff_id":null,"update_time":null,"field_name_comment":"ID","id":"1525515584797","field_show_type":"label","field_constraint_comment":null,"field_type":"varchar(64)","field_reference_comment":null,"field_reference":null},"id":"id","attrKeysStr":"delete_state,create_time,field_type_comment,update_staff_id,table_id,field_constraint,field_name,field_show_type_comment,create_staff_id,update_time,field_name_comment,id,field_show_type,field_constraint_comment,field_type,field_reference_comment,field_reference","attrKeys":["delete_state","create_time","field_type_comment","update_staff_id","table_id","field_constraint","field_name","field_show_type_comment","create_staff_id","update_time","field_name_comment","id","field_show_type","field_constraint_comment","field_type","field_reference_comment","field_reference"]},{"tableName":"admin_dictionary_field","attr":{"delete_state":"0","create_time":"2018-05-05 18:20:15.0","field_type_comment":"NAME","update_staff_id":null,"table_id":"1525515537026","field_constraint":null,"field_name":"name","field_show_type_comment":"ID","create_staff_id":null,"update_time":"2018-05-05 19:42:26.0","field_name_comment":"名称","id":"1525515615003","field_show_type":"label","field_constraint_comment":null,"field_type":"varchar(64)","field_reference_comment":null,"field_reference":null},"id":"id","attrKeysStr":"delete_state,create_time,field_type_comment,update_staff_id,table_id,field_constraint,field_name,field_show_type_comment,create_staff_id,update_time,field_name_comment,id,field_show_type,field_constraint_comment,field_type,field_reference_comment,field_reference","attrKeys":["delete_state","create_time","field_type_comment","update_staff_id","table_id","field_constraint","field_name","field_show_type_comment","create_staff_id","update_time","field_name_comment","id","field_show_type","field_constraint_comment","field_type","field_reference_comment","field_reference"]},{"tableName":"admin_dictionary_field","attr":{"delete_state":"0","create_time":"2018-05-05 18:20:43.0","field_type_comment":null,"update_staff_id":null,"table_id":"1525515537026","field_constraint":null,"field_name":"sex","field_show_type_comment":"ID","create_staff_id":null,"update_time":"2018-05-05 19:42:33.0","field_name_comment":"性别","id":"1525515643008","field_show_type":"label","field_constraint_comment":null,"field_type":"varchar(64)","field_reference_comment":null,"field_reference":null},"id":"id","attrKeysStr":"delete_state,create_time,field_type_comment,update_staff_id,table_id,field_constraint,field_name,field_show_type_comment,create_staff_id,update_time,field_name_comment,id,field_show_type,field_constraint_comment,field_type,field_reference_comment,field_reference","attrKeys":["delete_state","create_time","field_type_comment","update_staff_id","table_id","field_constraint","field_name","field_show_type_comment","create_staff_id","update_time","field_name_comment","id","field_show_type","field_constraint_comment","field_type","field_reference_comment","field_reference"]},{"tableName":"admin_dictionary_field","attr":{"delete_state":"0","create_time":"2018-05-05 18:21:36.0","field_type_comment":"icon","update_staff_id":null,"table_id":"1525515537026","field_constraint":null,"field_name":"icon","field_show_type_comment":"icon","create_staff_id":null,"update_time":null,"field_name_comment":"icon","id":"1525515696868","field_show_type":"img","field_constraint_comment":null,"field_type":"varchar(64)","field_reference_comment":null,"field_reference":null},"id":"id","attrKeysStr":"delete_state,create_time,field_type_comment,update_staff_id,table_id,field_constraint,field_name,field_show_type_comment,create_staff_id,update_time,field_name_comment,id,field_show_type,field_constraint_comment,field_type,field_reference_comment,field_reference","attrKeys":["delete_state","create_time","field_type_comment","update_staff_id","table_id","field_constraint","field_name","field_show_type_comment","create_staff_id","update_time","field_name_comment","id","field_show_type","field_constraint_comment","field_type","field_reference_comment","field_reference"]},{"tableName":"admin_dictionary_field","attr":{"delete_state":"0","create_time":"2018-05-05 18:29:45.0","field_type_comment":"ok","update_staff_id":null,"table_id":"1525515537026","field_constraint":null,"field_name":"ok","field_show_type_comment":"icon","create_staff_id":null,"update_time":null,"field_name_comment":"ok","id":"1525516185960","field_show_type":"button","field_constraint_comment":null,"field_type":"varchar(64)","field_reference_comment":null,"field_reference":null},"id":"id","attrKeysStr":"delete_state,create_time,field_type_comment,update_staff_id,table_id,field_constraint,field_name,field_show_type_comment,create_staff_id,update_time,field_name_comment,id,field_show_type,field_constraint_comment,field_type,field_reference_comment,field_reference","attrKeys":["delete_state","create_time","field_type_comment","update_staff_id","table_id","field_constraint","field_name","field_show_type_comment","create_staff_id","update_time","field_name_comment","id","field_show_type","field_constraint_comment","field_type","field_reference_comment","field_reference"]},{"tableName":"admin_dictionary_field","attr":{"delete_state":"0","create_time":"2018-05-05 19:32:25.0","field_type_comment":"user_name","update_staff_id":null,"table_id":"1525515537026","field_constraint":null,"field_name":"user_name","field_show_type_comment":"user_name","create_staff_id":null,"update_time":null,"field_name_comment":"user_name","id":"1525519945176","field_show_type":"label","field_constraint_comment":null,"field_type":"varchar(64)","field_reference_comment":null,"field_reference":null},"id":"id","attrKeysStr":"delete_state,create_time,field_type_comment,update_staff_id,table_id,field_constraint,field_name,field_show_type_comment,create_staff_id,update_time,field_name_comment,id,field_show_type,field_constraint_comment,field_type,field_reference_comment,field_reference","attrKeys":["delete_state","create_time","field_type_comment","update_staff_id","table_id","field_constraint","field_name","field_show_type_comment","create_staff_id","update_time","field_name_comment","id","field_show_type","field_constraint_comment","field_type","field_reference_comment","field_reference"]}];
	// 初始化
	initTable(dictionary);
	
});

function initTable(dictionary){
	// 初始化配置信息
	var columns = [];
	for(var i = 0; i < dictionary.length ;i ++){
		columns.push({
			data : 'attr.' + dictionary[i].attr.field_name,
			title : dictionary[i].attr.field_name_comment ,
		});
	}
	// 增加操作列
	columns.push({
		data : null,
		title : "编辑" ,
	});
	
	tableDataTable = $('#example').DataTable({
		ajax: {
            url: baseURL
        },
        lengthChange: false,
        columnDefs: [{
 			targets: -1,
 			render: function(data, type, row) {
	          	var operatorDiv = '<div>'
	          	+'<i class="fa fa-edit" title="编辑"></i>'
	          	+'<button class = "btn btn-link btn-sm" onclick="updateOption(\''+row.attr.id+'\')" >编辑</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="deleteOption(\''+row.attr.id+'\')" >删除</button>'
	          	+'</div>';
          	return operatorDiv;
         }
      }],
      columns: columns
	});
}

function insertOption(){
	bbj.setBBJEntityValue({},dictionary);
	$("#modal-new-option-testdomain").modal('show');
}

function updateOption(id){
	Utils.ajax({
		url : baseURL + "/" + id,
		type : 'GET',
		success : function(data) {
			bbj.setBBJEntityValue(data.data,dictionary);
			$("#modal-new-option-testdomain").modal('show');
		}
	});
}

function deleteOption(id){
	Utils.ajax({
		url : baseURL + "/" + id,
		type : 'DELETE',
		success : function(data) {
			tableDataTable.ajax.reload();
		}
	});
}

function insertOrUpdateSave(){
	$("#modal-new-option-testdomain").modal('hide');
	var entity= bbj.getBBJEntityValue(dictionary);
	var type = "POST";
	if (entity.id) {
		type = "PUT";
	}
	Utils.ajax({
		type : type,
		url : baseURL,
		data : entity,
		success : function(data) {
			tableDataTable.ajax.reload();
		}
	});
}

