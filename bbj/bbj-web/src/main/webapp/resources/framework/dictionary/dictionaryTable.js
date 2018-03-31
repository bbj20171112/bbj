$(document).ready(function(){
	initGrid();
});


function initGrid(){
	var headers = [];
	headers.push("ID");
	headers.push("表名称");
	headers.push("表说明");
	headers.push("表描述");
	headers.push("序列");
	headers.push("备注");
	headers.push("操作");
	// console.log(data);
	var tableExp = $('#example').DataTable({
		ajax: {
            url: contextPath + "/base/dictionary/table"
        },
        lengthChange: false,
        columnDefs: [{
 			targets: -1,
 			render: function(data, type, row) {
	          	var operatorDiv = '<div>'
	          	+'<i class="fa fa-edit" title="编辑"></i>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_edit(\''+row.attr.id+'\')" >编辑</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_delete(\''+row.attr.id+'\')" >删除</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_edit_field(\''+row.attr.id+'\')" >字段维护</button>'
	          	+'<button class = "btn btn-link btn-sm" onclick="row_design(\''+row.attr.id+'\')" >设计</button>'
	          	+'</div>';
          	return operatorDiv;
         }
      }],
      columns: [ // 配置列映射（对象数据[嵌套数据：attr.id]）,如果是数组数据,直接 0,1,2,3 
    	  { data: 'attr.id' , title: headers[0]},
    	  { data: 'attr.table_name', title: headers[1]},
    	  { data: 'attr.table_comment', title: headers[2]},
    	  { data: 'attr.table_description', title: headers[3]},
    	  { data: 'attr.sequence_id', title: headers[4]},
    	  { data: 'attr.table_remark', title: headers[5]},
    	  { data: null , title: headers[6]}, 
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

function row_edit(id){
	Utils.ajax({
        url: contextPath + "/base/dictionary/table/"+id,
        type : "GET",
        success: function(data){
        	data = data.data.attr;
			$("#item-field-id").val(data.id);
        	$("#item-field-table-name").val(data.table_name);
			$("#item-field-table-comment").val(data.table_comment);
			$("#item-field-table-description").val(data.table_description);
			$("#item-field-sequence-id").val(data.sequence_id);
			
			$("#item-field-table-remark").val(data.table_remark);
			

			$("#modal-new-table").modal('show');
		}
	});
}

function row_delete(id){
	Utils.ajax({
        url: contextPath + "/base/dictionary/table/"+id,
        type : 'DELETE',
        success: function(data){
        	$('#example').DataTable().ajax.reload();
		}
	});
}

function row_design(id){
	location.href = contextPath + "/base/designer/new?table_id=" +id ;
}

function row_edit_field(id){
	location.href = contextPath + "/base/dictionary/field/page?id=" +id ;
}


function newTable(){
	var data = {};
	$("#item-field-id").val(data.id);
	$("#item-field-table-id").val(data.table_id);
	$("#item-field-table-name").val(data.table_name);
	$("#item-field-table-comment").val(data.table_comment);
	$("#item-field-table-description").val(data.table_description);
	$("#item-field-sequence-id").val(data.sequence_id);
	
	$("#item-field-table-remark").val(data.table_remark);
	$("#modal-new-table").modal('show');
}

function newTableSave(){
	var jsonData= {};
	jsonData.id = $("#item-field-id").val();;
	jsonData.table_id = $("#item-field-table-id").val();
	jsonData.table_name = $("#item-field-table-name").val();
	jsonData.table_comment = $("#item-field-table-comment").val();
	jsonData.table_description = $("#item-field-table-description").val();
	jsonData.sequence_id = $("#item-field-sequence-id").val();
	
	jsonData.table_remark = $("#item-field-table-remark").val();
	
	console.log(jsonData.table_id);
	$("#modal-new-table").modal('hide');
	var url = contextPath + "/base/dictionary/table";
	if(jsonData.id){
		url = contextPath + "/base/dictionary/table";
		Utils.ajax({
			type : "PUT",
	        url: url,
	        data: jsonData,
	        success: function(data){
	        	$('#example').DataTable().ajax.reload();
			}
		});
	} else {
		url = contextPath + "/base/dictionary/table";
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
		
		