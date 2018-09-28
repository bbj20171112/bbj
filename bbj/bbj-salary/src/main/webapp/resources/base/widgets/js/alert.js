/**
 * 对话框组件，基于  SweetAlert 和 layer 的弹框
 */

var defaultOkText = "确定";
var defaultCancelText = "取消";
var defaultMsgText = "";


/**
 * 写bbj的通用方法
 */
jQuery.extend(bbj, (function(win, $) {

	return {

		///////////////////////////////////////     自定义框 			///////////////////////////////////////     
		
		customHtml : function (element){
			return swal({
			  content: element,
			  button: false
			});
		},
		///////////////////////////////////////     自定义框 			///////////////////////////////////////     
		
		///////////////////////////////////////     确认框 			///////////////////////////////////////     
		
		confirm : function (textText,titleText,okText,cancelText,type,okFunc,cancelFunc){
			var opts = {};
			if(Utils.isEmpty(type)){
				opts = this.getInfoOpts({},textText,titleText,okText,type);
			} else {
				opts = this.getIconOpts({},textText,titleText,okText,type);
			}
			delete opts.button; // 删除 确认按钮
			if(Utils.isEmpty(okText)){
				okText = defaultOkText;
			}
			if(Utils.isEmpty(cancelText)){
				cancelText = defaultCancelText;
			}
			opts.buttons = [okText,cancelText];
			return swal(opts).then((value) => {
				if(value){
			    	if(typeof okFunc == "function"){
			    		okFunc();
			    	}
				} else {
			    	if(typeof cancelFunc == "function"){
			    		cancelFunc();
			    	}
				}
			});
		},
		
		///////////////////////////////////////     操作框 			///////////////////////////////////////     
		/**
		 * 弹出操作成功对话框
		 */
		successWithIcon : function (textText,titleText,okText){
			var opts = this.getIconOpts({},textText,titleText,okText,"success");
			return swal(opts);
		},
		
		/**
		 * 弹出操作错误对话框
		 */
		errorWithIcon : function (textText,titleText,okText){
			var opts = this.getIconOpts({},textText,titleText,okText,"error");
			return swal(opts);
		},
		
		/**
		 * 弹出警告对话框
		 */
		warningWithIcon : function (textText,titleText,okText){
			var opts = this.getIconOpts({},textText,titleText,okText,"warning");
			return swal(opts);
		},

		/**
		 * 弹出说明对话框
		 */
		infoWithIcon : function (textText,titleText,okText){
			var opts = this.getIconOpts({},textText,titleText,okText,"info");
			return swal(opts);
		},
		
		/**
		 * 获取一个带图片的提示信息配置,默认是 info
		 * opts 已经设置的配置对象
		 * textText 提示内容
		 * titleText 标题
		 * okText 确定的文字
		 * type "info" || "error" || "success" || "warning"
		 * 
		 */
		getIconOpts : function (opts,textText,titleText,okText,type){
			if(type == "warning"){
				opts.icon = "warning";
			} else if(type == "error"){
				opts.icon = "error";
			} else if(type == "success"){
				opts.icon = "success";
			} else {
				opts.icon = "info";
			}
			return this.getInfoOpts(opts,textText,titleText,okText);
		},
		
		///////////////////////////////////////     提示框             /////////////////////////////////
		/**
		 * 弹出一个对话框
		 * textText 提示内容
		 * titleText 标题
		 * okText 确定的文字
		 * 
		 */
		info : function (textText,titleText,okText){
			var opts = this.getInfoOpts({},textText,titleText,okText);
			return swal(opts);
			
		},
		
		/**
		 * 获取info的配置对象
		 * opts 已经设置的配置对象
		 * textText 提示内容
		 * titleText 标题
		 * okText 确定的文字
		 * 
		 */
		getInfoOpts : function(opts,textText,titleText,okText){
			var opts = this.getBasicOpts(opts,textText,titleText);
			if(!Utils.isEmpty(okText)){
				opts.button = okText;
			} else {
				opts.button = defaultOkText;
			}
			return opts;
		},
		
		/**
		 * 获取基本的配置集合，以数组形式返回
		 * opts 已经设置的配置对象
		 * textText 提示内容
		 * titleText 标题
		 * 
		 */
		getBasicArray : function (textText,titleText){
			var optsArray = [];
			if(!Utils.isEmpty(textText)){
				optsArray[optsArray.length] = textText;
			}
			if(!Utils.isEmpty(titleText)){
				optsArray[optsArray.length] = titleText;
			}
			return optsArray;
		},
		
		/**
		 * 获取基本的配置对象
		 * opts 已经设置的配置对象
		 * textText 提示内容
		 * titleText 标题
		 * 
		 */
		getBasicOpts : function(opts,textText,titleText){
			if(!Utils.isEmpty(titleText)){
				opts.title = titleText;
			}
			if(!Utils.isEmpty(textText)){
				opts.text = textText;
			}
			return opts;
		},
		
		/**
		 * (基于SweetAlert)调用swal
		 */
		swal : function (opts){
			return swal(opts);
		},
		
		/**
		 * 弹出一个对话框,只有内容
		 */
		msg : function (msgText){
			if(Utils.isEmpty(msgText)){
				msgText = defaultMsgText;
			}
			return swal(msgText);
		},
		
		///////////////////////////////////////     layer ///////////////////////////////////// 
		
		/**
		 * 基于layer弹出一个对话框
		 */
		msg : function (obj){
			return layer.msg(obj);
		}
	}
	
	
}(this, jQuery)));
