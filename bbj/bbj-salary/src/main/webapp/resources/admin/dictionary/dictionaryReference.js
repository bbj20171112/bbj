$(function () {
		        	var headers = [];
					headers.push("ID");
					headers.push("表名称");
					headers.push("参照值");
					headers.push("参照名称");
					headers.push("参照说明");
					headers.push("操作");
					
		        	// console.log(data);
		        	var tableExp = $('#example').DataTable({
		        		ajax: {
		                    url: contextPath + "/admin/dictionary/reference"
		                },
	      		columnDefs: [{
	             			targets: -1,
	                      render: function(data, type, row) {
	                      	var operatorDiv = '<div class="btn-group">'
	                      	+'<button type="button" onclick="row_edit(\''+row.attr.id+'\')" class="btn btn-info">编辑</button>'
	                      	+'<button type="button" onclick="row_delete(\''+row.attr.id+'\')" class="btn btn-danger">删除</button>'
	                      	+'<button type="button" onclick="row_edit_field(\''+row.attr.id+'\')" class="btn btn-info">字段维护</button>'
							+'</div>';
	                      	return operatorDiv;
	                     }
	                  }],
	      		columns: [ // 配置列映射（对象数据[嵌套数据：attr.id]）,如果是数组数据,直接 0,1,2,3 
	      			{ data: 'attr.id' , title: headers[0]},
	      			{ data: 'attr.table_name', title: headers[1]},
					{ data: 'attr.reference_value', title: headers[2]},
					{ data: 'attr.reference_name', title: headers[3]},
					{ data: 'attr.reference_remark', title: headers[4]},
					{ data: null , title: headers[5]}, 
	              ]
	      	});
	  });
		// 获取选中
	    function showSelectItems() {
		$('#example').DataTable().ajax.reload();
	        /* console.log( $('#example').DataTable().rows('.selected').data()[0]);
	    	alert( $('#example').DataTable().rows('.selected').data().length +' row(s) selected' );*/
		}
	
	function row_edit(id) {
		var ss = "";
		Utils.ajax({
			url : contextPath + "/admin/dictionary/reference/" + id,
			type : 'GET',
			success : function(data) {
				data = data.data.attr;
				
				$("#item-field-id").val(data.id);
				$("#item-field-table_name").val(data.table_name);
				$("#item-field-reference_value").val(data.reference_value);
				$("#item-field-reference_name").val(data.reference_name);				
				$("#item-field-reference_remark").val(data.reference_remark);

				$("#modal-new-div").modal('show');
			}
		});
	}

	function row_delete(id) {
		Utils.ajax({
			url : contextPath + "/admin/dictionary/reference/" + id,
			type : 'DELETE',
			success : function(data) {
				$('#example').DataTable().ajax.reload();
			}
		});
	}

	function newData() {
		var data = {};
		
		$("#item-field-id").val(data.id);
		$("#item-field-table_name").val(data.table_name);
		$("#item-field-reference_value").val(data.reference_value);
		$("#item-field-reference_name").val(data.reference_name);				
		$("#item-field-reference_remark").val(data.reference_remark);
		
		$("#modal-new-div").modal('show');
	}

	function newDataSave() {
		var jsonData = {};
		jsonData.id = $("#item-field-id").val();
		jsonData.table_name = $("#item-field-table_name").val();
		jsonData.reference_value = $("#item-field-reference_value").val();
		jsonData.reference_name = $("#item-field-reference_name").val();				
		jsonData.reference_remark = $("#item-field-reference_remark").val();

		$("#modal-new-div").modal('hide');
		console.log("jsonData.id::" + jsonData.id);
		if (jsonData.id) {
			var url = contextPath + "/admin/dictionary/reference";
			Utils.ajax({
				type : "PUT",
				url : url,
				data : jsonData,
				success : function(data) {
					$('#example').DataTable().ajax.reload();
				}
			});
		} else {
			var url = contextPath + "/admin/dictionary/reference";
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