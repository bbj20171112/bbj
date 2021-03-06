﻿$(function () {

	// 页面初始化
	// http://www.datatables.club/manual/data.html
	Utils.ajax({
		type: "GET",
        url: "grid/query",
        data: "tagPage=1&&pageSize=15",
        success: function(data){
        	data = data.data;
        	console.log(data);
        	$('#example').DataTable({
        		data : data,
        		searching: false, // 是否搜索
        		bServerSide: false,
        		processing: true, // 显示进度
        	    ordering: true, // 是否排序
        	    order: [[ 1, "asc" ]], // 默认排序 // desc
        	    // scrollY: 200,scrollCollapse: true, // 滚动条
        	    pagingType:   "full_numbers",
        		lengthMenu: [[10, -1], [10, "All"]], // 定义页面大小
        		columnDefs: [{// 列渲染
           				targets: 3,
                    	render: function(data, type, row) {
	                    	return row.id+"-合并-" + row.desc;
                    	}
                    },{
               			targets: -1,
                        render: function(data, type, row) {
                        	var operatorDiv = '<div class="btn-group">'
                        	+'<button type="button" onclick="row_edit('+row.id+')" class="btn btn-info">编辑</button>'
                        	+'<button type="button" onclick="row_delete('+row.id+')" class="btn btn-danger">删除</button>'
                        	+'</div>';
                        	return operatorDiv;
                       }
                    },{
              			targets: 'Desc2',
                       	render: function(data, type, row) {
   	                    	return "Desc2:" + row.desc;
                       	}
                        
                }],
                
        		columns: [ // 配置列映射（对象数据[嵌套数据：attr.id]）,如果是数组数据,直接 0,1,2,3 
                    { data: 'id' },
                    { data: 'desc'},
                    { data: 'desc'},
                    { data: 'desc'},
                    { data: null }, 
                ],
                // 创建行回调
                "createdRow": function ( row, data, index ) {
                	// console.log(row);
                },
                // 初始化数据结束
                "initComplete": function () {
                	// console.log("initComplete");
                },
        	});
        	$(".table").colResizable(); // 使用colResizable插件实现表格头宽度拖拽,这个不能喝滚动条同时时候，不然会存在一些问题
        	}
		});
  	})
  	
  	
  	// 编辑事件(列点击)
  	function row_edit(id){
		alert("您要编辑：id=" + id);
	}
	
	// 删除事件(列点击)
	function row_delete(id){
		alert("您要删除：id=" + id);
	}

	// 行点击事件
	$('#example tbody').on('click', 'tr', function () {
		var data = $('#example').DataTable().row( this ).data();
        console.log( '您点击了ID为： '+data.id+' 的数据' );
    } );
	
	// 隐藏和显示列
	$('#example tbody').on('click', 'tr', function () {
		// Get the column API object
	    var column = $('#example').DataTable().column(1);

	    // Toggle the visibility
	    column.visible( ! column.visible() );
    } );
	
 
	// 获取选择的Item
    function showSelectItems() {
    	// console.log($('#example').DataTable().rows('.selected').data());
    	var rows = $('#example').DataTable().rows('.selected').data();
    	var rowsIdArray = [];
    	for(var i = 0;i < rows.length; i++){
    		rowsIdArray[i] = rows[i].id;
    	}
        alert('您选择的ID：' + rowsIdArray );
    }