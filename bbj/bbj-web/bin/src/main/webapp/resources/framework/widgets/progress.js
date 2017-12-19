/**
 * 进度加载组件
 */
var Progress = new Progress(); // 基于  Pace 的进度加载组件

function Progress() {
	
	/**
	 * 启动进度
	 */
	this.start = function (){
		return Pace.start();
	}
	
	/**
	 * 结束进度
	 */
	this.stop = function (){
		return Pace.stop();
	}
	
}