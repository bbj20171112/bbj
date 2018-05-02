var fieldItems = [];

var items = [];
var clicker = {};

$(document).ready(function() {		
				
	// addRow();
	
	initContext();
	
} );


/**
 * 新建一个页面设计布局
 * 
 * @returns
 */

function addRow(){
	$("#modal-add-row").modal('show');
}

function addRowSure(){
	var colNum = $("#select-col_num").val();
	if(colNum){} else {
		colNum = 1;
	}
	var baseNum = 12;
	var colHtml = []; // 拼凑列的HTML
	
	var currentRow = items.length;
	var rowHtml = '<div class="row item-row" data-column="'+currentRow+'" >';
	for (var col = 0; col < colNum; col++) {
		colHtml[col] = '<div' + 
		' class = "col-sm-'+(baseNum / colNum)+' item-col' + '" data-column="'+currentRow + '-' + col +'"' + 
		' id="item-col-' + currentRow + '-' + col +'">\n';
		
	}	
	items[currentRow] = {};
	items[currentRow].data = [];
	items[currentRow].colNum = colNum;
		
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += '</div>\n'; // 结束标签
		rowHtml += colHtml[i]; // 进行拼接容器HTML
	}
	rowHtml += "</div>";
	
	if(items.length == 1){ // 首次加入
		$("#div-row-list").html("");
	}
	$("#div-row-list").html($("#div-row-list").html() + '\n' + rowHtml + '\n');
	
	$("#modal-add-row").modal('hide'); // 隐藏对话框
	// 可拖动
	var dragulaItems = [];
	for(var row = 0; row < items.length; row++){
		colNum = items[row].colNum;
		for (var col = 0; col < colNum; col++) {
			dragulaItems[dragulaItems.length] = getElementById("item-col-" + row + '-' + col);
		}
	}
	dragula(dragulaItems);
	initWidgets();
}


function addComponent(){
	$("#h4-modal-title").val("添加组件");
	$("#modal-new-component").modal('show');
}

function editComponent(){
	var dataColumns = clicker.attr('data-column').split("-");
	var currentRow = new Number(dataColumns[0]);
	var currentCol = new Number(dataColumns[1]);
	
	$("#h4-modal-title").val("编辑组件");
	var component = items[currentRow].data[currentCol];
	$("#item-component-component_name").val(component.attr.component_name);
	$("#item-component-component_title").val(component.attr.component_title);
	$("#item-component-component_show_type").val(component.attr.component_show_type).trigger("change");
	$("#modal-new-component").modal('show');
	
}

function addComponentSure() {
	
	var component = {};
	component.attr = {};
	component.attr.component_name = $("#item-component-component_name").val();
	component.attr.component_title = $("#item-component-component_title")
			.val();
	component.attr.component_show_type = $("#item-component-component_show_type").val();
	if(!component.attr.component_show_type){
		component.attr.component_show_type = 'label';
	}
	
	var dataColumns = clicker.attr('data-column').split("-");
	var currentRow = new Number(dataColumns[0]);
	var currentCol = -1;
	if(dataColumns.length > 1){
		currentCol = new Number(dataColumns[1]);
	}
	
	var colNum = items[currentRow].colNum; // 列数
	var length = items[currentRow].data.length;
	var tagCol = length % colNum;
	
	
	if(currentCol == -1){ // 新增
		items[currentRow].data[length] = component;
		currentCol = length;
	} else {  // 编辑
		items[currentRow].data[currentCol] = component;
	} 
	
	var htmlValue = '<div class="col-sm-12 item"  id="item-' + currentRow + '-' + currentCol +'">'
						+getComponentItem(component)+
					'</div>';
	if(currentCol - colNum >= 0){ // 超出原数目，直接在后面进行添加
		$('#item-' + currentRow + '-'+tagCol).parent().append(htmlValue);
	} else { // 还能挂组件		
		$("#item-col-" + currentRow + '-' + currentCol).html(htmlValue);		
	}
	$("#modal-new-component").modal('hide');
	initWidgets();
}


function getComponentItem(component){
	component.attr.component_name = $("#item-component-component_name").val();
	component.attr.component_title = $("#item-component-component_title")
			.val();
	component.attr.component_show_type = $("#item-component-component_show_type").val();
	if(!component.attr.component_show_type){
		component.attr.component_show_type = 'label';
	}
	
	var componentShowType = component.attr.component_show_type;
	if(componentShowType == 'form'){
		return 	'    <form>'+component.attr.component_name+'</form>\n';
	}else if (componentShowType == 'grid'){
		return 	'    <table title="'+component.attr.component_name+'">\n'		
			  	+'    </table>\n';
	}else if(componentShowType == 'input'){
		return 	'    <input value='+component.attr.component_name+'></input>\n';
	}else if (componentShowType == 'checkbox'){
		return 	'    <div name="item_check" class="checkbox checkbox-info">\n'		
			  	+'        <input type="checkbox" class="styled" aria-label="Single checkbox One"></input>\n'
			  	+'        <label>'+component.attr.component_name+'</label>\n'
			  	+'    </div>\n';
	}else if (componentShowType == 'img'){
		return 	'    <img src="'+contextPath+'/resources/dist/img/avatar5.png" title="'+component.attr.component_title+'" class="img-circle"></img>\n';
	}else if (componentShowType == 'button'){
		return 	'    <button class="btn btn-info">'+component.attr.component_name+'</button>\n';
	}else if(componentShowType == 'date'){
		return 	'    <div class="input-group date">\n'
        	  	+'        <div class="input-group-addon">\n'
        	  	+'            <i class="fa fa-calendar"></i>\n'
        	  	+'        </div>\n'
        	  	+'        <input type="text" class="datepicker form-control pull-right"></input>\n'
        	  	+'    </div>\n';
	}else if(componentShowType == 'datetime'){
		 return '    <div class="input-group">\n'
	     	   	+'        <div class="input-group-addon">\n'
	     	   	+'            <i class="fa fa-clock-o"></i>\n'
	     	   	+'        </div>\n'
	     	   	+'        <input type="text" class="form-control pull-right datetimepicker"></input>\n'
	     	   	+'    </div>\n';
	}else if(componentShowType == 'textarea'){
		return 	'    <textarea class="form-control" rows="3" placeholder="'+component.attr.component_name+'"></textarea>\n';
	}else if(componentShowType == 'select'){
		return 	'    <select class="form-control select2" style="width: 100%">\n'
			  	+'        <option value="0">'+component.attr.component_name+'</option>\n'
			  	+'    </select>\n';
	}else { // 当成label
		return 	'    <label>'+component.attr.component_name+'</label>\n';
	}
}

function initWidgets(){
	Utils.initWidgets();
	$(".item").unbind('click').bind('click',function(){
		var itemField = $(this);
		if(itemField.attr("class").indexOf("selected") >=0 ){
			itemField.removeClass("selected");
		} else {
			itemField.addClass("selected");
		}
	});
	var itemRow = $(".item-row");
	itemRow.unbind('dblclick').bind('dblclick',function(e){
		e.stopPropagation();
        e.preventDefault();
        clicker = $(this);
		addComponent();
	});	
	var itemCol = $(".item-col");
	itemCol.unbind('dblclick').bind('dblclick',function(e){
		e.stopPropagation();
        e.preventDefault();
        clicker = $(this);
		editComponent();
	});	
//	var item = $(".item");
//	item.unbind('dblclick').bind('dblclick',function(e){
//		e.stopPropagation();
//      e.preventDefault();
//      clicker = $(this);
//		editComponent();
//	});	

}


function getElementById (id) {
  return document.getElementById(id);
}

function editFormSure(){
	$("#modal-edit-form").modal('hide');
}
function getDictionaryFields(tableId) {
	var returnData = {};
	Utils.ajax({
		url : contextPath + "/admin/dictionary/field/all?table_id=" + tableId,
		type : 'GET',
		async : false, // 同步
		success : function(data) {
			if(true){
				returnData = data.data;
			}
		}
	});
	return returnData;
}


function getFieldItems(){
	return fieldItems;
}
function initFieldItems(){
	var fields = getDictionaryFields($("#field-item-table_id").val());
	fieldItems = [];
	if(fields){
		for (var i = 0; i < fields.length; i++) {
			fields[i].attr.field_show_default = "FieldValue" + i;
			var item = getFieldItem(fields[i]);
			fieldItems[fieldItems.length] = item;
		}
	}
	//return fieldItems;
}


function showSource(){
	var containerSource = document.getElementById('container-source');
	containerSource.value = $("#container-form").html();
	var editor = CodeMirror.fromTextArea(containerSource, {
	    mode: "xml",
	    tabMode: "indent",
	    lineWrapping: true,
	    lineNumbers: true
	});
}
function autoFormatRange(){
	var containerSource = document.getElementById('container-source');
	var editor = CodeMirror.fromTextArea(containerSource, {
	});
	editor.autoFormatRange({line:0, ch:0}, {line:totalLines});
}


function initContext(){
	context.init({
	    fadeSpeed: 100,
	    filter: function ($obj){},
	    above: 'auto',
	    preventDoubleContext: true,
	    compress: false
	});		
	context.attach('.item', [
		{header: '排列'},
		{text: '浮动', subMenu: [
			{text: '居左', target:'', action: function(e){
				var itemChildren = $(".selected").children();
				// 清空原浮动样式
				itemChildren.removeClass("text-center");
				itemChildren.removeClass("center-block ");
				itemChildren.removeClass("pull-right");
				// 设置居左
				itemChildren.addClass("pull-left");
			}},
			{text: '居中', target:'', action: function(e){
				$(".selected").children().each(function (){
					var itemField = $(this);
					var tagName = itemField.get(0).tagName;
					if("IMG" == tagName.toUpperCase() || "INPUT" == tagName.toUpperCase()){
						// 清空原浮动样式
						itemField.removeClass("pull-right");
						itemField.removeClass("pull-left");
						// 设置居中
						itemField.addClass("center-block");
					}else {
						// 清空原浮动样式
						itemField.removeClass("pull-right");
						itemField.removeClass("pull-left");
						// 设置居中
						itemField.addClass("text-center");
					}						
				});
			}},
			{text: '居右', target:'', action: function(e){
				var itemChildren = $(".selected").children();
				// 清空原浮动样式
				itemChildren.removeClass("text-center");
				itemChildren.removeClass("center-block ");
				itemChildren.removeClass("pull-left");
				// 设置居右
				itemChildren.addClass("pull-right");
			}}
		]},
		{divider: true},
		{header: '操作'},
		{text: '选择', subMenu: [
			{text: '全选', target:'', action: function(e){
				$(".item").addClass("selected");
			}},
			{text: '清空', target:'', action: function(e){
				$(".item").removeClass("selected");
			}},
			{text: '反选', target:'', action: function(e){
				$(".item").each(function (){
					var itemField = $(this);
					if(itemField.attr("class").indexOf("selected") >=0 ){
						itemField.removeClass("selected");
					} else {
						itemField.addClass("selected");
					}
				});
			}}
		]},
		
	]);
	
	context.attach('.item > form', [		
		{header: '表单操作'},		
		{text: '子组件', subMenu: [
			{text: '添加', target:'', action: function(e){
				clicker = context.getClicker();		
				$("#div-modal-body").load( "layout2.html" );
				$("#modal-edit-form").modal('show');
				
			}}
		]}
	]);

}


////////////// 程序生成

function generateProgramCode(tableName){
	var classNameObj = getClassNameObj(tableName);
	var controllerStr = getControllerStr(classNameObj);
	$("#input-program-controller-name").val(classNameObj.controllerName);
	$("#input-program-controller-source").html(controllerStr);
	$("#modal-program-view").modal("show");
}


function getClassNameObj(tableName){
	var domainName = "User";
	var daoName = domainName + "Dao";
	var serviceName = domainName + "Service";
	var controllerName = domainName + "Controller";
	
	var domainNameParam = "user";
	var daoNameParam = domainNameParam + "Dao";
	var serviceNameParam = domainNameParam + "Service";
	var controllerNameParam = domainNameParam + "Controller";
	
	return {
		domainName : domainName,
		domainNameParam : domainNameParam,
		daoName : daoName,
		daoNameParam : daoNameParam,
		serviceName : serviceName,
		serviceNameParam : serviceNameParam,
		controllerName : controllerName,
		controllerNameParam : controllerNameParam
	}; 
}

function getControllerStr(classNameObj){
	
	var importStr = "import java.util.ArrayList;\n" + 
					"import java.util.HashMap;\n" + 
					"import java.util.List;\n" + 
					"import java.util.Map;\n" + 
					"\n" + 
					"import javax.servlet.http.HttpServletRequest;\n" + 
					"\n" + 
					"import org.springframework.beans.factory.annotation.Autowired;\n" + 
					"import org.springframework.stereotype.Controller;\n" + 
					"import org.springframework.web.bind.annotation.PathVariable;\n" + 
					"import org.springframework.web.bind.annotation.RequestMapping;\n" + 
					"import org.springframework.web.bind.annotation.RequestMethod;\n" + 
					"import org.springframework.web.bind.annotation.RequestParam;\n" + 
					"import org.springframework.web.bind.annotation.ResponseBody;\n" + 
					"\n" + 
					"import com.bbj.base.constant.Constants;\n" + 
					"import com.bbj.base.domain.BBJSqlFilter;\n" + 
					"import com.bbj.base.domain.SqlFilter;\n" + 
					"import com.bbj.base.domain.WhereFilter;\n" + 
					"import com.bbj.base.utils.BBJEntityUtils;\n" ;
			
	var classDefHeader ="\n" +
						"@Controller\n" + 
						"@RequestMapping(value={Constants.module_admin+\"/dictionary/field\"})\n" + 
						"public class " + classNameObj.controllerName + " {\n" +
						"\n";
	var classPropertyTabsStr = "\t"
	var classPropertyStr =	classPropertyTabsStr + "\n" +
							classPropertyTabsStr + "@Autowired\n" +
							classPropertyTabsStr + "private " + classNameObj.serviceName + " " + classNameObj.serviceNameParam + ";\n" + 
							classPropertyTabsStr + "\n";
	
	var classDefFooter ="\n}\n";
	
	var classMethodsTabsStr = "\t";
	var classMethodsStr = 	classMethodsTabsStr + "\n" +
						classMethodsTabsStr + "/**\n" + 
						classMethodsTabsStr + " * 增\n" + 
						classMethodsTabsStr + " * @param request\n" + 
						classMethodsTabsStr + " * @return\n" + 
						classMethodsTabsStr + " */\n" + 
						classMethodsTabsStr + "@RequestMapping(method=RequestMethod.POST)\n" + 
						classMethodsTabsStr + "@ResponseBody\n" + 
						classMethodsTabsStr + "public Object insert(HttpServletRequest request){\n" + 
						classMethodsTabsStr + "	" + classNameObj.domainName + " " + classNameObj.domainNameParam + " = BBJEntityUtils.parseFrom(request, " + classNameObj.domainName + ".class);\n" + 
						classMethodsTabsStr + "	return " + classNameObj.serviceNameParam + ".insert(" + classNameObj.domainNameParam + " );\n" + 
						classMethodsTabsStr + "}\n"+ 
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "/**\n"+ 
						classMethodsTabsStr +  "* 删\n"+ 
						classMethodsTabsStr +  "* @param id\n"+ 
						classMethodsTabsStr +  "* @return\n"+ 
						classMethodsTabsStr +  "*/\n"+ 
						classMethodsTabsStr + "@RequestMapping(value=\"/{id}\",method=RequestMethod.DELETE)\n"+ 
						classMethodsTabsStr + "@ResponseBody\n"+ 
						classMethodsTabsStr + "public Object deleteById(@PathVariable(\"id\")String id,HttpServletRequest request){\n"+ 
						classMethodsTabsStr + "	return " + classNameObj.serviceNameParam + ".deleteById(id);\n"+ 
						classMethodsTabsStr + "}\n" +
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "/**\n"+ 
						classMethodsTabsStr + " * 改\n"+ 
						classMethodsTabsStr + " * @param id\n"+ 
						classMethodsTabsStr + " * @return\n"+ 
						classMethodsTabsStr + " */\n"+ 
						classMethodsTabsStr + "@RequestMapping(method=RequestMethod.PUT)\n"+ 
						classMethodsTabsStr + "@ResponseBody\n"+ 
						classMethodsTabsStr + "public Object update(HttpServletRequest request){\n"+ 
						classMethodsTabsStr + "	" + classNameObj.domainName + " " + classNameObj.domainNameParam + " = BBJEntityUtils.parseFrom(request, " + classNameObj.domainName + ".class);\n" + 
						classMethodsTabsStr + "	return " + classNameObj.serviceNameParam + ".update(" + classNameObj.domainNameParam + " );\n"+ 
						classMethodsTabsStr + "}\n"+ 
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "/**\n"+ 
						classMethodsTabsStr + " * 查（单个）\n"+ 
						classMethodsTabsStr + " * @param id\n"+ 
						classMethodsTabsStr + " * @return\n"+ 
						classMethodsTabsStr + " */\n"+ 
						classMethodsTabsStr + "@RequestMapping(value=\"/{id}\",method=RequestMethod.GET)\n"+ 
						classMethodsTabsStr + "@ResponseBody\n"+ 
						classMethodsTabsStr + "public Object get(@PathVariable(\"id\")String id,HttpServletRequest request){\n"+ 
						classMethodsTabsStr + "	return " + classNameObj.serviceNameParam + ".queryById(id);\n"+ 
						classMethodsTabsStr + "}\n"+ 
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "\n"+
						classMethodsTabsStr + "/**\n"+
						classMethodsTabsStr + " * 查（分页）\n"+
						classMethodsTabsStr + " * @param start\n"+
						classMethodsTabsStr + " * @param length\n"+
						classMethodsTabsStr + " * @param draw\n"+
						classMethodsTabsStr + " * @param searchValue\n"+
						classMethodsTabsStr + " * @return\n"+
						classMethodsTabsStr + " */\n"+
						classMethodsTabsStr + "@RequestMapping(method=RequestMethod.GET)\n"+
						classMethodsTabsStr + "@ResponseBody\n"+
						classMethodsTabsStr + "public Object queryByPage(@RequestParam(value=\"start\",defaultValue=\"1\")int start,\n"+
						classMethodsTabsStr + "		@RequestParam(value=\"length\",defaultValue=\"10\")int length,\n"+
						classMethodsTabsStr + "		@RequestParam(value=\"draw\",defaultValue=\"0\")int draw,\n"+
						classMethodsTabsStr + "		@RequestParam(value=\"search[value]\",defaultValue=\"\")String searchValue,\n"+
						classMethodsTabsStr + "		HttpServletRequest request\n"+
						classMethodsTabsStr + "		){\n"+
						classMethodsTabsStr + "	\n"+
						classMethodsTabsStr + "	// 分页查询\n"+
						classMethodsTabsStr + "	SqlFilter sqlFilter = null;	\n"+	
						classMethodsTabsStr + "	Map<String, Object> map = new HashMap<String, Object>();\n"+
						classMethodsTabsStr + "	int tagPage = start / length;\n"+
						classMethodsTabsStr + "	if(tagPage < 1){\n"+
						classMethodsTabsStr + "		tagPage = 1;\n"+
						classMethodsTabsStr + "	} else {\n"+
						classMethodsTabsStr + "		tagPage = tagPage + 1;\n"+
						classMethodsTabsStr + "	}\n"+
						classMethodsTabsStr + "	map.put(\"data\", " + classNameObj.serviceNameParam + ".queryByPage(tagPage, length, sqlFilter));\n"+
						classMethodsTabsStr + "	map.put(\"recordsTotal\", " + classNameObj.serviceNameParam + ".getTotalRow(sqlFilter));\n"+
						classMethodsTabsStr + "	map.put(\"recordsFiltered\", " + classNameObj.serviceNameParam + ".getTotalRow(sqlFilter));\n"+
						classMethodsTabsStr + "	map.put(\"draw\", draw);\n"+
						classMethodsTabsStr + "	\n"+
						classMethodsTabsStr + "	return map;\n"+
						classMethodsTabsStr + "}\n"+
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "\n"+
						classMethodsTabsStr + "\n"+
						classMethodsTabsStr + "@RequestMapping(value=\"/page\")\n"+ 
						classMethodsTabsStr + "public Object page(HttpServletRequest request){\n"+ 
						classMethodsTabsStr + "	return Constants.module_admin + \"/dictionary/dictionaryField\";\n"+ 
						classMethodsTabsStr + "}";
						
	return importStr + classDefHeader + classPropertyStr + classMethodsStr + classDefFooter ;
}
function getImportStr(){
	return  "import java.util.ArrayList;\n" + 
			"import java.util.HashMap;\n" + 
			"import java.util.List;\n" + 
			"import java.util.Map;\n" + 
			"\n" + 
			"import javax.servlet.http.HttpServletRequest;\n" + 
			"\n" + 
			"import org.springframework.beans.factory.annotation.Autowired;\n" + 
			"import org.springframework.stereotype.Controller;\n" + 
			"import org.springframework.web.bind.annotation.PathVariable;\n" + 
			"import org.springframework.web.bind.annotation.RequestMapping;\n" + 
			"import org.springframework.web.bind.annotation.RequestMethod;\n" + 
			"import org.springframework.web.bind.annotation.RequestParam;\n" + 
			"import org.springframework.web.bind.annotation.ResponseBody;\n" + 
			"\n" + 
			"import com.bbj.base.constant.Constants;\n" + 
			"import com.bbj.base.domain.BBJSqlFilter;\n" + 
			"import com.bbj.base.domain.SqlFilter;\n" + 
			"import com.bbj.base.domain.WhereFilter;\n" + 
			"import com.bbj.base.utils.BBJEntityUtils;\n" ;
}



