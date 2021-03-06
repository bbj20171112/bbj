if (!window.bbj) {
	window.bbj = {};
}

function doParse(options){
	var controlResource = bbj.controlResource || {};
	var M = Mustache;
	var type = options.type, config = controlResource[type], tpl, opt;
	
	if (config) {
		tpl = config.templ;
		opt = config.options;

		if (tpl) {
			if (opt) {
				options = $.extend({}, opt, options);
			}
			return M.render(tpl, options);
		}
	}
	//console.warn('widget解析失败！未找到该类型的widget模板！options：', options);
	return '';
}
/**
 * bbj.parseControl 解析控件方法
 */
(function(win, $) {
	var parseControl = function(options) {
		//增加一部分
		if(options.length > 1){
			var htmlTotal = "";
			for(var p in options){
				htmlTotal += doParse(options[p]);
			}
			return htmlTotal;
		}else{
			return doParse(options);
		}
	};
	

	win.bbj = (function(bbj) {
		return jQuery.extend(bbj, {
			parseControl : parseControl
		});
	})(win.bbj || {});

}(this, jQuery));

/**
 * 写bbj的通用方法
 */
jQuery.extend(bbj, (function(win, $) {

	/**
	 * 渲染页面，主要用于无自定义内容
	 */
	function renderHtml(id, options) {
		$('#' + id).html(bbj.parseControl(options));
	}

	/**
	 * 渲染panelbox
	 */
	function renderPanelbox(id, options) {
		var _box = $('#' + id);
		// 渲染好所有内容
		_box.html(bbj.parseControl(options));
		// 将body以html形式渲染
		var _box_body = _box.find('.box-body');
		_box_body.html(_box_body.html());
	}

	/**
	 * 渲染cardlist
	 */
	function renderCardlist(id, options) {
		var _box = $('#' + id);
		// 渲染好所有内容
		_box.html(bbj.parseControl(options));
		// 将body以html形式渲染
		_box.find('.card').each(function(i) {
			var _this = $(this);
			_this.html(_this.html());
		});
	}

	/**
	 * 获取echarts
	 */
	function initEchart(id, option) {
		var _echart = echarts.init(document.getElementById(id));
		_echart.setOption(option);
		return _echart;
	}

	return {
		renderHtml : renderHtml,
		renderPanelbox : renderPanelbox,
		renderCardlist : renderCardlist,
		initEchart : initEchart
	}

}(this, jQuery)));