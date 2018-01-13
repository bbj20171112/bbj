/**
 * 控件模版
 */
var bbj = (function(bbj) {
	// 用户自定义control
	var customControlResource = {

	};

	// control的模板及默认配置
	var controlResource = {
		infobox: {
			templ: '<div class="info-box {{bgcolorcls}}">' +
				'		<span class="info-box-icon"><i class="{{iconcls}}"></i></span>' +
				'		<div class="info-box-content">' +
				'			<span class="info-box-text">{{text1}}</span>' +
				'			<span class="info-box-number">{{text2}}</span>' +
				'			{{#progress}}' +
				'			<div class="progress">' +
				'				<div class="progress-bar" style="width:{{width}}"></div>' +
				'			</div>' +
				'			<span class="progress-description">{{description}}</span>' +
				'			{{/progress}}' +
				'		</div>' +
				'	</div>',
			options: {
				bgcolorcls: 'bg-aqua',
				iconcls: 'fa fa-bookmark-o',
				text1: 'text1',
				text2: 'text2',
				progress: null
			}
		},

		smallbox: {
			templ: '<div class="small-box {{bgcolorcls}}">' +
				'		<div class="inner">' +
				'			<h3>{{text1}}</h3>' +
				'			<p>{{text2}}</p>' +
				'		</div>' +
				'		<div class="icon">' +
				'			<i class="{{iconcls}}"></i>' +
				'		</div>' +
				'		{{#footer}}' +
				'		<a href="{{url}}" class="small-box-footer">{{text}}<i class="{{iconcls}}"></i></a>' +
				'		{{/footer}}' +
				'	</div>',
			options: {
				bgcolorcls: 'bg-aqua',
				text1: 'text1',
				text2: 'text2',
				iconcls: 'fa fa-shopping-cart',
				footer: null
			}
		},

		//带标题、按钮的卡片框
		panelbox: {
			templ: '<div class="box box-{{state}} {{boxsolid}} {{collapsedbox}}">' +
				'		<div class="box-header {{withborder}}">' +
				'			<h3 class="box-title">{{title}}</h3>' +
				'			<div class="box-tools pull-right">' +
				'				{{#button}}' +
				'				<button type="button" class="btn btn-box-tool" data-widget="{{datawidget}}"><i class="{{iconcls}}"></i></button>' +
				'				{{/button}}' +
				'			</div>' +
				'		</div>' +
				'		<div class="box-body">{{&body}}</div>' +
				'		{{#overlay}}' +
				'		<div class="overlay"><i class="fa fa-refresh fa-spin"></i></div>' +
				'		{{/overlay}}' +
				'	</div>',
			options: {
				state: 'default',
				boxsolid: 'box-solid',
				collapsedbox: '',
				withborder: 'with-border',
				title: '标题',
				button: [{
					datawidget: 'collapse',
					iconcls: 'fa fa-plus'
				}],
				body: '自定义div或已有控件',
				overlay: null
			}
		},
		//卡片列表,自定义div或已有控件
		cardlist: {
			templ: '{{#cards}}' +
				'	<div class="card" style="float:left;width:300px;height:300px;border:1px solid #aaa;border-radius:10px;margin:5px;padding:5px;box-size:border-box;">' +
				'	{{&body}}' +
				'	</div>' +
				'	{{/cards}}',
			options: {
				cards: [{
					body: '<div>33333</div>'
				}, {
					body: '自定义div或已有控件2'
				}, {
					body: '自定义div或已有控件3'
				}]
			}
		},
		
		//输入框，含提示————input
		forminput: {
			templ:'<div id={{input_state_id}} class="form-group has-success">' + 
				'<input type="{{input_type}}" class="form-control" id="{{input_id}}" placeholder="{{placeholder}}">' +
                '<label class="control-label" for="{{input_id}}" id="{{hint_id}}">{{hint_text}}</label> </div>',
            options: {
				input_type: 'text', //输入类型
				input_id: 'input_id', //输入框id
				placeholder: 'Please input something here !', //输入提示信息
				hint_id: 'label_id',	//提示信息——id
				hint_text: 'I am hint', //提示信息
				input_state_id: 'input_state_id' //输入框输入信息校正控制状态,修改class改变输入框状态（正确、警告、错误）has-success has-error has-warning
			}
		}
	};

	jQuery.extend(controlResource, customControlResource);

	return jQuery.extend(bbj, {
		controlResource: controlResource
	});
})(bbj || {});