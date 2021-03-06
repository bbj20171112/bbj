/**
 * 初始化
 */
$(document).ready(function(){
	init();
});

function init() {
	var headers = [];
	headers.push("ID");
	headers.push("字段名称");
	headers.push("字段类型");
	headers.push("字段说明");
	headers.push("字段展示类型");
	headers.push("序号");
	headers.push("键值");
	headers.push("键值说明");
	headers.push("约束");
	headers.push("约束说明");
	headers.push("参照");
	headers.push("参照说明");
	headers.push("备注");
	headers.push("排序");
	headers.push("参照显示字段");
	headers.push("操作");
	
	// console.log(data);
	var tableExp = $('#example')
			.DataTable(
					{
						ajax : {
							url : contextPath + "/admin/dictionary/field",
							data : function(searchParam) {
								//添加额外的参数传给服务器  
								searchParam.table_name = $("#item-field-table_name")
										.val();
							}
						},
						columnDefs : [ {
							targets : -2,
							render : function(data, type, row) {
								var operatorDiv = '<div class="btn-group">'
								+ '<span onclick="row_order_update(\''+row.attr.id+'\',\'previous\')" class="btn btn-info">上移</span>'
								+ '<span onclick="row_order_update(\''+ row.attr.id+ '\',\'next\')" class="btn btn-danger">下移</span>'
								+ '<span onclick="row_order_update(\''+ row.attr.id+ '\',\'first\')" class="btn btn-info">置顶</span>'
								+ '<span onclick="row_order_update(\''+ row.attr.id+ '\',\'last\')" class="btn btn-danger">置底</span>'
								+ '</div>'
								return operatorDiv;
							}
						} ,{
							targets : -1,
							render : function(data, type, row) {
								var operatorDiv = '<div class="btn-group">'
										+ '<button type="button" onclick="row_edit(\''
										+ row.attr.id
										+ '\')" class="btn btn-info">编辑</button>'
										+ '<button type="button" onclick="row_delete(\''
										+ row.attr.id
										+ '\')" class="btn btn-danger">删除</button>'
										+ '</div>';
								return operatorDiv;
							}
						}],
						columns : [ // 配置列映射（对象数据[嵌套数据：attr.id]）,如果是数组数据,直接 0,1,2,3 
						{
							data : 'attr.id',
							title : headers[0]
						}, {
							data : 'attr.field_name',
							title : headers[1]
						}, {
							data : 'attr.field_type',
							title : headers[2]
						}, {
							data : 'attr.field_name_comment',
							title : headers[3]
						}, {
							data : 'attr.field_show_type',
							title : headers[4]
						}, {
							data : 'attr.field_order_number',
							title : headers[5]
						}, {
							data : 'attr.field_reference_table_name',
							title : headers[6]
						}, {
							data : 'attr.field_constraint_comment',
							title : headers[7]
						},
						/* { data: 'field_cons', title: headers[8]},
						{ data: 'field_consdesc', title: headers[9]},
						{ data: 'field_ref', title: headers[10]},
						{ data: 'field_refdesc', title: headers[11]},
						{ data: 'field_remark', title: headers[12]}, */
						{
							data : 'attr.field_reference_table_field_value',
							title : headers[13]
						},
						{
							data : null,
							title : headers[14]
						},
						{
							data : null,
							title : headers[15]
						},]
					});
}
// 获取选中
function showSelectItems() {
	console.log($('#example').DataTable().rows('.selected').data()[0]);
}

function row_order_update(id,type){
	Utils.ajax({
		url : contextPath + "/admin/dictionary/field",
		type : 'PUT',
		data : "action=ordernumber&type=" + type + "&id=" + id,
		success : function(data) {
			$('#example').DataTable().ajax.reload();
		}
	});
}
function row_edit(id) {
	var ss = "";
	Utils.ajax({
		url : contextPath + "/admin/dictionary/field/" + id,
		type : 'GET',
		success : function(data) {
			data = data.data.attr;

			// Utils.setBBJEntityValue(data);
			$("#item-field-id").val(data.id);
			//$("#item-field-table_name").val(data.table_name);
			$("#item-field-field_name").val(data.field_name);
			$("#item-field-field_name_comment").val(
					data.field_name_comment);
			$("#item-field-field_type").val(data.field_type);

			$("#item-field-field_type_comment").val(
					data.field_type_comment);
			$("#item-field-field_constraint")
					.val(data.field_constraint);
			$("#item-field-field_constraint_comment").val(
					data.field_constraint_comment);
			$("#item-field-field_reference_table_name").val(data.field_reference_table_name);
			$("#item-field-field_reference_table_field_name").val(data.field_reference_table_field_name);
			$("#item-field-field_reference_table_field_value").val(data.field_reference_table_field_value);
			
			$("#item-field-field_remark").val(data.field_remark);
			$("#modal-new-field").modal('show');
		}
	});
}

function row_delete(id) {
	Utils.ajax({
		url : contextPath + "/admin/dictionary/field/" + id,
		type : 'DELETE',
		success : function(data) {
			$('#example').DataTable().ajax.reload();
		}
	});
}

function newField() {
	var data = {};

	$("#item-field-id").val(data.id);
	//$("#item-field-table_name").val(data.table_name);
	$("#item-field-field_name").val(data.field_name);
	$("#item-field-field_name_comment").val(data.field_name_comment);
	$("#item-field-field_type").val(data.field_type);

	$("#item-field-field_type_comment").val(data.field_type_comment);
	$("#item-field-field_constraint").val(data.field_constraint);
	$("#item-field-field_constraint_comment")
			.val(data.field_constraint_comment);
	
	$("#item-field-field_reference_table_name").val(data.field_reference_table_name);
	$("#item-field-field_reference_table_field_name").val(data.field_reference_table_field_name);
	$("#item-field-field_reference_table_field_value").val(data.field_reference_table_field_value);
	$("#item-field-field_reference_comment").val(data.field_reference_comment);
	$("#item-field-field_remark").val(data.field_remark);

	$("#modal-new-field").modal('show');
}

function newFieldSave() {
	var jsonData = {};
	jsonData.id = $("#item-field-id").val();
	jsonData.table_name = $("#item-field-table_name").val();
	jsonData.field_name = $("#item-field-field_name").val();
	jsonData.field_name_comment = $("#item-field-field_name_comment")
			.val();
	jsonData.field_type = $("#item-field-field_type").val();
	if(!jsonData.field_type){
		jsonData.field_type = 'varchar(64)';
	}
	jsonData.field_type_comment = $(
			"#item-field-field_type_comment").val();
	jsonData.field_show_type = $("#item-field-field_show_type").val();
	if(!jsonData.field_show_type){
		jsonData.field_show_type = 'label';
	}
	jsonData.field_show_type_comment = $(
			"#item-field-field_show_type_comment").val();
	
	
	jsonData.field_constraint = $("#item-field-field_constraint").val();
	jsonData.field_constraint_comment = $(
			"#item-field-field_constraint_comment").val();
	
	jsonData.field_reference_table_name = $("#item-field-field_reference_table_name").val();
	jsonData.field_reference_table_field_name = $("#item-field-field_reference_table_field_name").val();
	jsonData.field_reference_table_field_value = $("#item-field-field_reference_table_field_value").val();
	jsonData.field_reference_comment = $("#item-field-field_reference_comment").val();
	
	jsonData.field_remark = $("#item-field-field_remark").val();

	//Utils.getBBJEntityValue(jsonData);

	$("#modal-new-field").modal('hide');

	if (jsonData.id) {
		var url = contextPath + "/admin/dictionary/field";
		Utils.ajax({
			type : "PUT",
			url : url,
			data : jsonData,
			success : function(data) {
				$('#example').DataTable().ajax.reload();
			}
		});
	} else {
		var url = contextPath + "/admin/dictionary/field";
		Utils.ajax({
			type : "POST",
			url : url,
			data : jsonData,
			success : function(data) {
				$('#example').DataTable().ajax.reload();
			}
		});
	}

}