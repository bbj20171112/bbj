/**
 * 对话框组件
 */
var Alert = new Alert(); // 基于  SweetAlert 和 layer 的弹框

function Alert() {
	
	/**
	 * 基于SweetAlert弹出一个对话框
	 */
	this.alert = function (obj){
		return swal(obj);
	}
	
	/**
	 * 基于layer弹出一个对话框
	 */
	this.msg = function (obj){
		return layer.msg(obj);
	}
	
}
