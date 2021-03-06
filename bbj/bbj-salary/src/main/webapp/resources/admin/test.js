
var tableName = "admin_test"; // 当前表ID
var dictionary = {}; // 数据字典
var baseURL = contextPath + "/admin/test"; // 基URL
var tableDataTable = {}; // 表格对象
var tableElementId = "table-admin_test"; // 当前表ID
var modalElementId = "modal-admin_test"; // 当前表ID

/**
 * 页面初始化
 */
$(document).ready(function(){
	
	// 获取数据字典
	dictionary = bbj.getBBJEntityDictionary(tableName);
	
	$("#"+modalElementId).html(bbj.getModalContent(dictionary));
	
	Utils.initWidgets();
	
	// 初始化表格
	initTable(dictionary);
	
});


/**
 * 初始化表格数据
 * @param dictionary
 * @returns
 */
function initTable(dictionary){
	var dataTableDefs = bbj.getDataTableDefs(dictionary);
	tableDataTable = $('#'+tableElementId).DataTable({
		ajax: { 
            url: baseURL
        },
        lengthChange: false,
        columnDefs: dataTableDefs.columnDefs,
        columns: dataTableDefs.columns
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
			tableDataTable.draw(false);// 刷新页面
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
			tableDataTable.draw(false);// 刷新页面
		}
	});
}

