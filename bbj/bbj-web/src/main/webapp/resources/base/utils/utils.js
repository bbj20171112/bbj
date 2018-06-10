/**
 * 常用工具类
 */

var Utils = new Utils();
function Utils() {

	// 获取绝对路径
	this.getAbsoluteUrl = function(url) {
		if (Utils.isEmpty(url))
			return contextPath;
		if (url.charAt(0) != "/")
			url = "/" + url;
		return contextPath + url;
		// return "http://localhost:8080" + contextPath + url;
	}

	// 跳转至局对路径
	this.locateUrl = function(url) {
		if (url.charAt(0) != "/")
			url = "/" + url;
		location.href = contextPath + url;

	}

	/**
	 * 是否存在属性
	 */
	this.hasOwnProperty = function(obj, attr) {
		if (obj == null || obj == undefined || obj == "") {
			return false;
		}
		return obj.hasOwnProperty(attr);
		;
	}

	/**
	 * 判断空
	 */
	this.isEmpty = function(obj) {
		if (obj == null || obj == undefined || obj == ""
				|| $.trim("" + obj) == "") {
			return true;
		}
		return false;
	}

	/**
	 * 判断不空
	 */
	this.isNotEmpty = function(obj) {
		if (obj == null || obj == undefined || obj == ""
			|| $.trim("" + obj) == "") {
		return false;
	}
	return true;
	}

	/**
	 * ajax 方法
	 */
	this.ajax = function(ajaxSetting) {
		// 在这里进行处理一些别的操作控制，比如加密，安全过滤等
		// TODO
		return $.ajax(ajaxSetting);
	}

	// 校验是否全由数字组成

	this.isDigit = function isDigit(s) {
		var patrn = /^[0-9]{1,20}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}

	// 校验登录名：只能输入5-20个以字母开头、可带数字、“_”、“.”的字串
	this.isRegisterUserName = function isRegisterUserName(s) {
		var patrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}

	// 校验用户姓名：只能输入1-30个以字母开头的字串
	this.isTrueName = function isTrueName(s) {
		var patrn = /^[a-zA-Z]{1,30}$/;
		if (!patrn.exec(s))
			return false;
		return true;

	}

	// 校验密码：只能输入6-20个字母、数字、下划线
	this.isPasswd = function isPasswd(s) {
		var patrn = /^(\w){6,20}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}

	// 校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
	this.isTel = function isTel(s) {
		// var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?(\d){1,12})+$/;
		var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}

	// 校验手机号码：必须以数字开头，除数字外，可含有“-”
	this.isMobil = function isMobil(s) {
		var patrn = /^1[34578]\d{9}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}

	// 校验邮政编码
	this.isPostalCode = function isPostalCode(s) {
		// var patrn=/^[a-zA-Z0-9]{3,12}$/;
		var patrn = /^[a-zA-Z0-9 ]{3,12}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}
	
	this.isEmail = function isEmail(s) {
		var patrn = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}
	
	this.isValidPassword = function isValidPassword(s) {
		var patrn = /^(\w){6,20}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}
	
	// 基本页面元素

	this.initWidgets = function(){
		//Date picker
	    $('.datepicker').datepicker({
	      autoclose: true,
	      language: 'zh-CN',
	      orientation: "bottom"
	    });
	    
	    //Timepicker
	    $('.timepicker').timepicker({
	      showInputs: false
	    });

	    $('.datetimepicker').datetimepicker({
	      format: 'yyyy-mm-dd hh:ii'
	    });
	    
	    $('.select2').select2({
	    	language: "zh-CN"
	    	}
	    );
	}
	
	this.getCamelCaseName = function(name){
		var strArray = name.split("_");
		var camelCaseName = strArray[0];
		if(strArray.length > 1){
			for (var i = 0; i < strArray.length - 1; i++) {
				camelCaseName += strArray[i + 1].substring(0,1).toUpperCase() + strArray[i + 1].substring(1);
			}
		}
		return camelCaseName;
	}

}
