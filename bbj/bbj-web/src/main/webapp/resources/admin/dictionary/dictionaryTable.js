$(document).ready(function(){
	initGrid();
});


function initGrid(){
	var headers = [];
	headers.push("表名称");
	headers.push("表说明");
	headers.push("表描述");
	headers.push("序列");
	headers.push("备注");
	headers.push("操作");
	// console.log(data);
	var tableExp = $('#example').DataTable({
		ajax: {
            url: contextPath + "/admin/dictionary/table"
        },
        lengthChange: false,
        columnDefs: [{
 			targets: -1,
 			render: function(data, type, row) {
	          	var operatorDiv = '<div>'
	          	+'<i class="fa fa-edit" title="编辑"></i>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_edit(\''+row.attr.table_name+'\')" >编辑</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_delete(\''+row.attr.table_name+'\')" >删除</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_edit_field(\''+row.attr.table_name+'\')" >字段维护</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_design_form(\''+row.attr.table_name+'\')" >Form设计</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_design_grid(\''+row.attr.table_name+'\')" >Grid设计</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_design_layout(\''+row.attr.table_name+'\')" >Layout设计</button>'
	          	+'</div>';
          	return operatorDiv;
         }
      }],
      columns: [ // 配置列映射（对象数据[嵌套数据：attr.id]）,如果是数组数据,直接 0,1,2,3 
    	  { data: 'attr.table_name', title: headers[0]},
    	  { data: 'attr.table_comment', title: headers[1]},
    	  { data: 'attr.table_description', title: headers[2]},
    	  { data: 'attr.sequence_id', title: headers[3]},
    	  { data: 'attr.table_remark', title: headers[4]},
    	  { data: null , title: headers[5]}, 
		]
	});
}
	 	
// 获取选中
function showSelectItems() {
	
		// $('#example').DataTable().ajax.reload();
	console.log( $('#example').DataTable().rows('.selected').data().length +' row(s) selected' );
        /* console.log( $('#example').DataTable().rows('.selected').data()[0]);
    	alert( $('#example').DataTable().rows('.selected').data().length +' row(s) selected' );*/
}


function row_edit(tableName){
	Utils.ajax({
        url: contextPath + "/admin/dictionary/table/"+tableName,
        type : "GET",
        success: function(data){
        	data = data.data.attr;
        	$("#item-field-table-name-old").val(data.table_name);
        	$("#item-field-table-name").val(data.table_name);
			$("#item-field-table-comment").val(data.table_comment);
			$("#item-field-table-description").val(data.table_description);
			$("#item-field-sequence-id").val(data.sequence_id);
			
			$("#item-field-table-remark").val(data.table_remark);
			

			$("#modal-new-table").modal('show');
		}
	});
}

function row_delete(tableName){
	Utils.ajax({
        url: contextPath + "/admin/dictionary/table/"+tableName,
        type : 'DELETE',
        success: function(data){
        	$('#example').DataTable().ajax.reload();
		}
	});
}

function row_design_layout(table_name){
	location.href = contextPath + "/admin/designer/layout?table_name=" + table_name;
}

function row_design_form(table_name){
	location.href = contextPath + "/admin/designer/form?table_name=" + table_name;
}
function row_design_grid(table_name){
	location.href = contextPath + "/admin/designer/grid?table_name=" +table_name ;
}

function row_edit_field(row_edit){
	location.href = contextPath + "/admin/dictionary/field/page?table_name=" +row_edit ;
}


function newTable(){
	var data = {};
	
	$("#item-field-table-name-old").val(data.table_name);
	$("#item-field-table-name").val(data.table_name);
	$("#item-field-table-comment").val(data.table_comment);
	$("#item-field-table-description").val(data.table_description);
	$("#item-field-sequence-id").val(data.sequence_id);
	
	$("#item-field-table-remark").val(data.table_remark);
	$("#modal-new-table").modal('show');
}

function newTableSave(){
	var jsonData= {};
	jsonData.table_name = $("#item-field-table-name-old").val();
	jsonData.table_name_new = $("#item-field-table-name").val();
	jsonData.table_comment = $("#item-field-table-comment").val();
	jsonData.table_description = $("#item-field-table-description").val();
	jsonData.sequence_id = $("#item-field-sequence-id").val();
	
	jsonData.table_remark = $("#item-field-table-remark").val();
	
	$("#modal-new-table").modal('hide');
	var url = contextPath + "/admin/dictionary/table";
	if(jsonData.table_name){
		url = contextPath + "/admin/dictionary/table";
		Utils.ajax({
			type : "PUT",
	        url: url,
	        data: jsonData,
	        success: function(data){
	        	$('#example').DataTable().ajax.reload();
			}
		});
	} else {
		url = contextPath + "/admin/dictionary/table";
		Utils.ajax({
			type : "POST",
	        url: url,
	        data: jsonData,
	        success: function(data){
	        	$('#example').DataTable().ajax.reload();
			}
		});
	}
}


function getSql(){
	
}
		
		