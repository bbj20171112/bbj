
var tableName = "org_position"; // 当前表名称
var dictionary = {}; // 数据字典
var baseURL = contextPath + "/organization/position"; // 基URL
var tableDataTable = {}; // 表格对象
var tableElementId = "table-org_position"; // 当前表名称
var modalElementId = "modal-org_position"; // 当前表名称


/**
 * 页面初始化
 */
$(document).ready(function(){
	
	// 获取数据字典
	dictionary = bbj.getBBJEntityDictionary(tableName);
	
	// 初始化表格
	initTable(dictionary);
	
});


/**
 * 初始化表格数据
 * @param dictionary
 * @returns
 */
function initTable(dictionary){
	// 获取配置信息[默认全部进行展示，当数据库新增表结构时候会进行同步更新展示]
	var columns = [];
	for(var i = 0; i < dictionary.length ;i ++){
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
	
	// 初始化DataTable
	tableDataTable = $('#'+tableElementId).DataTable({
		ajax: { 
            url: baseURL
        },
        lengthChange: false,
        columnDefs: [{
 			targets: -1,
 			render: function(data, type, row) { 
	          	var operatorDiv = '<div>'
	          	+'<i class="fa fa-edit" title="编辑"></i>'
	          	+'<button class = "btn btn-link btn-sm" onclick="updateOption('+row.attr.id+')" >编辑</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="deleteOption('+row.attr.id+')" >删除</button>'
	          	+'</div>';
          	return operatorDiv;
         }
      }],
      columns: columns
	});
}


/**
 * 插入[新增]操作
 * @returns
 */
function insertOption(){
	bbj.setBBJEntityValue({},dictionary); // 清空Form数据
	$("#"+modalElementId).modal('show'); // 打开输入Form模态框
}


/**
 * 删除操作
 * @param id
 * @returns
 */
function deleteOption(id){
	Utils.ajax({ 
 		url : baseURL + "/" + id,
		type : 'DELETE',
		success : function(data) {
			tableDataTable.ajax.reload();// 刷新页面
		}
	});
}


/**
 * 更新[修改]操作
 * @param id
 * @returns
 */
function updateOption(id){
	// 根据主键获取对应行数据
	Utils.ajax({ 
		url : baseURL + "/" + id,
		type : 'GET',
		success : function(data) {
			bbj.setBBJEntityValue(data.data,dictionary); // 设置Form值
			$("#"+modalElementId).modal('show'); // 打开输入Form模态框
		}
	});
}


/**
 * 新增或修改保存函数
 * @returns
 */
function insertOrUpdateSave(){
	$("#"+modalElementId).modal('hide'); // 隐藏Form模态框
	var entity= bbj.getBBJEntityValue(dictionary); // 获取输入的值
	
	var type = "POST"; // 新增操作
	if (entity.id) { // 如果主键不为有效
		type = "PUT"; // 修改操作
	}
	// 提交给后台进行保存
	Utils.ajax({ 
		type : type,
		url : baseURL,
		data : entity,
		success : function(data) {
			tableDataTable.ajax.reload();// 刷新页面
		}
	});
}

