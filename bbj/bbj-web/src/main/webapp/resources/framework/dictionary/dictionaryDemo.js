$(function() {

	init('1521898758268');
	
});

function init(tableId) {
	Utils.ajax({
		url : contextPath + "/base/dictionary/field/all?table_id=" + tableId,
		type : 'GET',
		success : function(data) {
			console.log(data);
		}
	});
}