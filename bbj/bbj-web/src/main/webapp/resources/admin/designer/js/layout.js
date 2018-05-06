var fieldItems = [];

var items = [];
var clicker = {};

var dictionary = null;

$(document).ready(function() {		
		
	var tableId = $("#input-table_id").val();
	if(tableId){
		dictionary = bbj.getBBJEntityDictionary(tableId);
		var tableName = $('#input-table_name').val();
		$("#input-program-controller-baseurl").val(tableName.substring(tableName.indexOf("_")).replace(/_/g,"/"));
	}
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
	
	var rowTabStr = "			";
	var currentRow = items.length;
	var rowHtml = rowTabStr + '<div class="row item-row" data-column="'+currentRow+'" >\n';
	for (var col = 0; col < colNum; col++) {
		colHtml[col] = rowTabStr + '	<div' + ' class = "col-sm-'+(baseNum / colNum)+' item-col' + '" data-column="'+currentRow + '-' + col +'"' + ' id="item-col-' + currentRow + '-' + col +'">\n';
		
	}	
	items[currentRow] = {};
	items[currentRow].data = [];
	items[currentRow].colNum = colNum;
		
	for (var i = 0; i < colNum; i++) {
		colHtml[i] += rowTabStr + '	</div>\n'; // 结束标签
		rowHtml += colHtml[i]; // 进行拼接容器HTML
	}
	rowHtml += rowTabStr + "</div>\n";
	
	if(items.length == 1){ // 首次加入
		$("#div-row-list").html("");
	}
	$("#div-row-list").html($("#div-row-list").html() + '\n' + rowHtml);
	
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
	
	var componentTabStr = "					";
	var htmlValue = componentTabStr + '<div class="col-sm-12 item"  id="item-' + currentRow + '-' + currentCol +'">\n'
									+ getComponentItem(component) +
					componentTabStr + '</div>\n';
	if(currentCol - colNum >= 0){ // 超出原数目，直接在后面进行添加
		$('#item-' + currentRow + '-'+tagCol).parent().append("\n" + htmlValue);
	} else { // 还能挂组件		
		$("#item-col-" + currentRow + '-' + currentCol).html("\n" + htmlValue + "				"); 
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
	
	var componentItemTabStr = "					";
	var componentShowType = component.attr.component_show_type;
	if(componentShowType == 'form'){
		return 	componentItemTabStr + '    <form>'+component.attr.component_name+'</form>\n';
	}else if (componentShowType == 'grid'){
		return 	componentItemTabStr + '    <table title="'+component.attr.component_name+'">\n'	+	
				componentItemTabStr +'    </table>\n';
	}else if(componentShowType == 'input'){
		return 	componentItemTabStr + '    <input value='+component.attr.component_name+'></input>\n';
	}else if (componentShowType == 'checkbox'){
		return 	componentItemTabStr + '    <div name="item_check" class="checkbox checkbox-info">\n' +		
				componentItemTabStr + '        <input type="checkbox" class="styled" aria-label="Single checkbox One"></input>\n' + 
				componentItemTabStr + '        <label>'+component.attr.component_name+'</label>\n' + 
				componentItemTabStr + '    </div>\n';
	}else if (componentShowType == 'img'){
		return 	componentItemTabStr + '    <img src="'+contextPath+'/resources/dist/img/avatar5.png" title="'+component.attr.component_title+'" class="img-circle"></img>\n';
	}else if (componentShowType == 'button'){
		return 	componentItemTabStr + '    <button class="btn btn-info">'+component.attr.component_name+'</button>\n';
	}else if(componentShowType == 'date'){
		return 	componentItemTabStr + '    <div class="input-group date">\n' + 
				componentItemTabStr + '        <div class="input-group-addon">\n' + 
				componentItemTabStr + '            <i class="fa fa-calendar"></i>\n' + 
				componentItemTabStr + '        </div>\n' + 
				componentItemTabStr + '        <input type="text" class="datepicker form-control pull-right"></input>\n' + 
				componentItemTabStr + '    </div>\n';
	}else if(componentShowType == 'datetime'){
		 return componentItemTabStr + '    <div class="input-group">\n' + 
		 		componentItemTabStr + '        <div class="input-group-addon">\n' + 
		 		componentItemTabStr + '            <i class="fa fa-clock-o"></i>\n' + 
		 		componentItemTabStr + '        </div>\n' + 
		 		componentItemTabStr + '        <input type="text" class="form-control pull-right datetimepicker"></input>\n' + 
		 		componentItemTabStr + '    </div>\n';
	}else if(componentShowType == 'textarea'){
		return 	componentItemTabStr + '    <textarea class="form-control" rows="3" placeholder="'+component.attr.component_name+'"></textarea>\n';
	}else if(componentShowType == 'select'){
		return 	componentItemTabStr + '    <select class="form-control select2" style="width: 100%">\n' + 
				componentItemTabStr + '        <option value="0">'+component.attr.component_name+'</option>\n' + 
				componentItemTabStr + '    </select>\n';
	}else { // 当成label
		return 	componentItemTabStr + '    <label>'+component.attr.component_name+'</label>\n';
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

function generateProgramCode(){
	
	var tableName = $('#input-table_name').val();
	var classNameObj = getClassNameObj(tableName);
	
	var jsStr = getJsStr(classNameObj);
	$("#input-program-js-source").html(escapeSpecialChars(jsStr));
	
	var htmlStr = getHTMLStr(classNameObj);
	$("#input-program-html-source").html(escapeSpecialChars(htmlStr));
	
	var controllerStr = getControllerStr(classNameObj);
	$("#input-program-controller-source").html(escapeSpecialChars(controllerStr));
	
	var serviceStr = getServiceStr(classNameObj);
	$("#input-program-service-source").html(escapeSpecialChars(serviceStr));
	
	var daoStr = getDaoStr(classNameObj);
	$("#input-program-dao-source").html(escapeSpecialChars(daoStr));
	
	var domainStr = getDomainStr(classNameObj);
	$("#input-program-domain-source").html(escapeSpecialChars(domainStr));
	
	$("#modal-program-view").modal("show");
}


function getClassNameObj(tableName){
	
	var domainName = Utils.getCamelCaseName("_" + tableName.substring(tableName.indexOf("_")));
	var domainElement = $("#input-program-domain-name");
	if(Utils.isNotEmpty(domainElement.val())){
		domainName = domainElement.val();
	} else {
		domainElement.val(domainName);
	}
	
	var daoName = domainName + "Dao";
	var daoElement = $("#input-program-dao-name");
	if(Utils.isNotEmpty(daoElement.val())){
		daoName = daoElement.val();
	} else {
		daoElement.val(daoName);
	}
	
	var serviceName = domainName + "Service";
	var serviceElement = $("#input-program-service-name");
	if(Utils.isNotEmpty(serviceElement.val())){
		serviceName = serviceElement.val();
	} else {
		serviceElement.val(serviceName);
	}
	
	var controllerName = domainName + "Controller";
	var controllerElement = $("#input-program-controller-name");
	if(Utils.isNotEmpty(controllerElement.val())){
		controllerName = controllerElement.val();
	} else {
		controllerElement.val(controllerName);
	}
	
	var moduleName =  $("#input-program-controller-module").val();;
	
	var domainNameParam = domainName.substring(0,1).toLowerCase() + domainName.substring(1);
	var daoNameParam = daoName.substring(0,1).toLowerCase() + daoName.substring(1);;
	var serviceNameParam = serviceName.substring(0,1).toLowerCase() + serviceName.substring(1);;
	var controllerNameParam = controllerName.substring(0,1).toLowerCase() + controllerName.substring(1);;
	
	var jsName = moduleName + "/" + domainName.substring(0,1).toLowerCase() + domainName.substring(1) + ".js";
	var jsElement = $("#input-program-js-name");
	if(Utils.isNotEmpty(jsElement.val())){
		jsName = jsElement.val();
	} else {
		jsElement.val(jsName);
	}
	
	var htmlName = domainName.substring(0,1).toLowerCase() + domainName.substring(1);
	var htmlElement = $("#input-program-html-name");
	if(Utils.isNotEmpty(htmlElement.val())){
		htmlElement.val('');
	} else {
		htmlElement.val(htmlName);
	}
	
	return {
		tableName : tableName,
		moduleName : moduleName,
		htmlName : htmlName,
		jsName : jsName,
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

function getDomainStr(classNameObj){
	
	var importStr = "import com.bbj.base.domain.BBJEntity;\n" ;
	
			
	var classDefHeader ="\n" +
						"public class " + classNameObj.domainName + " extends BBJEntity {\n";
	var classPropertyTabsStr = "\t"
	var classPropertyStr =	classPropertyTabsStr + "\n" +
							classPropertyTabsStr + "private static final long serialVersionUID = 1L;\n" + 
							classPropertyTabsStr + "\n" ;
	if(dictionary){
		var attributePropertyStr = "";
		for(var i in dictionary){
			attributePropertyStr += classPropertyTabsStr + "public static final String " +
									Utils.getCamelCaseName(dictionary[i].attr.field_name)+
									" = \"" + dictionary[i].attr.field_name + "\"; // "+
									dictionary[i].attr.field_name_comment+" \n"; 
		}
		classPropertyStr += attributePropertyStr + "\n";
	}
	
	var classDefFooter ="\n\n}\n";
	
	var classMethodsTabsStr = "\t";
	var classMethodsStr = 	classMethodsTabsStr + "\n" +
						classMethodsTabsStr + "@Override\n"+
						classMethodsTabsStr + "public String initTable() {\n"+
						classMethodsTabsStr + "	return tableName;\n"+
						classMethodsTabsStr + "}\n"+
						classMethodsTabsStr + "\n"+ 
						classMethodsTabsStr + "\n"+
						classMethodsTabsStr + "@Override\n"+
						classMethodsTabsStr + "public String[] initAttr() {\n"+
						classMethodsTabsStr + "	String attrs[] = new String[]{\n";
	if(dictionary){ // 增加字段
		for(var i in dictionary){
			classMethodsStr += classMethodsTabsStr + "\t\t" + Utils.getCamelCaseName(dictionary[i].attr.field_name) + ", \n"; 
		}
	}
	classMethodsStr +=  classMethodsTabsStr + "	};\n"+
						classMethodsTabsStr + "	return attrs;\n"+
						classMethodsTabsStr + "}\n";
						
	return importStr + classDefHeader + classPropertyStr + classMethodsStr + classDefFooter ;
}


function getDaoStr(classNameObj){
	
	var importStr = "import org.springframework.stereotype.Repository;\n"  + 
					"import com.bbj.base.dao.BBJDaoImp;\n" ;
			
	var classDefHeader ="\n" +
						"@Repository\n" +
						"public class " + classNameObj.daoName + " extends BBJDaoImp<"+ classNameObj.domainName +">{\n";
	
	var classPropertyTabsStr = "\t"
	var classPropertyStr =	"";
	
	var classDefFooter ="\n\n}\n";
	
	var classMethodsTabsStr = "\t";
	var classMethodsStr = 	"";
						
	return importStr + classDefHeader + classPropertyStr + classMethodsStr + classDefFooter ;
}

function getServiceStr(classNameObj){
	
	var importStr = "import java.util.List;\n" + 
					"\n" + 
					"import org.springframework.beans.factory.annotation.Autowired;\n" + 
					"import org.springframework.stereotype.Service;\n" + 
					"\n" + 
					"import com.bbj.base.domain.SqlFilter;\n" ;
			
	var classDefHeader ="\n" +
						"@Service\n" +
						"public class "+ classNameObj.serviceName +" {\n" +
						"\n";
	var classPropertyTabsStr = "\t"
	var classPropertyStr =	classPropertyTabsStr + "\n" +
							classPropertyTabsStr + "@Autowired\n" +
							classPropertyTabsStr + "private " + classNameObj.daoName + " " + classNameObj.daoNameParam + ";\n" + 
							classPropertyTabsStr + "\n";
	
	var classDefFooter ="\n\n}\n";
	
	var classMethodsTabsStr = "\t";
	var classMethodsStr = 	classMethodsTabsStr + "\n" +
							classMethodsTabsStr + "/**\n"+ 
							classMethodsTabsStr + " * 增\n"+ 
							classMethodsTabsStr + " * @param "+ classNameObj.domainNameParam +"\n"+ 
							classMethodsTabsStr + " * @return\n"+ 
							classMethodsTabsStr + " */\n"+ 
							classMethodsTabsStr + "public int insert("+ classNameObj.domainName +" "+ classNameObj.domainNameParam +"){\n"+ 
							classMethodsTabsStr + "	if("+ classNameObj.domainNameParam +" == null){\n"+ 
							classMethodsTabsStr + "		return 0;\n"+ 
							classMethodsTabsStr + "	}\n"+ 
							classMethodsTabsStr + "	return " + classNameObj.daoNameParam + ".insert("+ classNameObj.domainNameParam+");\n"+
							classMethodsTabsStr + "}\n"+ 
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "/**\n"+ 
							classMethodsTabsStr + " * 删\n"+ 
							classMethodsTabsStr + " * @param id\n"+ 
							classMethodsTabsStr + " * @return\n"+ 
							classMethodsTabsStr + " */\n"+ 
							classMethodsTabsStr + "public int deleteById(String id){\n"+ 
							classMethodsTabsStr + "	if(id == null){\n"+ 
							classMethodsTabsStr + "		return 0;\n"+ 
							classMethodsTabsStr + "	}\n"+ 
							classMethodsTabsStr + "	return " + classNameObj.daoNameParam + ".deleteById(id);\n"+ 
							classMethodsTabsStr + "}\n" +
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "/**\n"+ 
							classMethodsTabsStr + " * 改\n"+ 
							classMethodsTabsStr + " * @param "+ classNameObj.domainNameParam +"\n"+ 
							classMethodsTabsStr + " * @return\n"+ 
							classMethodsTabsStr + " */\n"+ 
							classMethodsTabsStr + "public int update("+ classNameObj.domainName +" "+ classNameObj.domainNameParam +"){\n"+ 
							classMethodsTabsStr + "	return " + classNameObj.daoNameParam + ".update("+ classNameObj.domainNameParam +");\n"+ 
							classMethodsTabsStr + "}\n"+ 
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "/**\n"+ 
							classMethodsTabsStr + " * 查\n"+ 
							classMethodsTabsStr + " * @param id\n"+ 
							classMethodsTabsStr + " * @return\n"+ 
							classMethodsTabsStr + " */\n"+ 
							classMethodsTabsStr + "public "+ classNameObj.domainName +" queryById(String id){\n"+ 
							classMethodsTabsStr + "	return " + classNameObj.daoNameParam + ".queryById(id);\n"+ 
							classMethodsTabsStr + "}\n"+ 
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "\n"+
							classMethodsTabsStr + "/**\n"+
							classMethodsTabsStr + " * 查（分页）\n"+
							classMethodsTabsStr + " * @param tagPage\n"+
							classMethodsTabsStr + " * @param pageSize\n"+
							classMethodsTabsStr + " * @param sqlFilter\n"+
							classMethodsTabsStr + " * @return\n"+
							classMethodsTabsStr + " */\n"+
							classMethodsTabsStr + "public List<" + classNameObj.domainName + "> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){\n"+
							classMethodsTabsStr + "	return " + classNameObj.daoNameParam + ".queryByPage(tagPage, pageSize, sqlFilter);\n"+
							classMethodsTabsStr + "}\n"+
							
							classMethodsTabsStr + "\n"+ 
							classMethodsTabsStr + "\n"+
							classMethodsTabsStr + "/**\n"+
							classMethodsTabsStr + " * 获取总数\n"+
							classMethodsTabsStr + " * @param sqlFilter\n"+
							classMethodsTabsStr + " * @return\n"+
							classMethodsTabsStr + " */\n"+
							classMethodsTabsStr + "public int getTotalRow(SqlFilter sqlFilter){\n"+
							classMethodsTabsStr + "	return " + classNameObj.daoNameParam + ".getTotalRow(sqlFilter);\n"+
							classMethodsTabsStr + "}\n";
						
	return importStr + classDefHeader + classPropertyStr + classMethodsStr + classDefFooter ;
}

function getControllerStr(classNameObj){
	
	var moduleName = "Constants.module_" + classNameObj.moduleName;
	var baseurl = $("#input-program-controller-baseurl").val();
	
	var importStr = "import java.util.HashMap;\n" + 
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
					"import com.bbj.base.domain.SqlFilter;\n" + 
					"import com.bbj.base.utils.BBJEntityUtils;\n" ;
			
	var classDefHeader ="\n" +
						"@Controller\n" + 
						"@RequestMapping(value={" + moduleName + " + \""+baseurl+"\"})\n" + 
						"public class " + classNameObj.controllerName + " {\n" +
						"\n";
	var classPropertyTabsStr = "\t"
	var classPropertyStr =	classPropertyTabsStr + "\n" +
							classPropertyTabsStr + "@Autowired\n" +
							classPropertyTabsStr + "private " + classNameObj.serviceName + " " + classNameObj.serviceNameParam + ";\n" + 
							classPropertyTabsStr + "\n";
	
	var classDefFooter ="\n\n}\n";
	
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
						classMethodsTabsStr + "	return " + classNameObj.serviceNameParam + ".update(" + classNameObj.domainNameParam + ");\n"+ 
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
						classMethodsTabsStr + "	return " + moduleName + " + \""+baseurl+"\";\n"+ 
						classMethodsTabsStr + "}\n";
						
	return importStr + classDefHeader + classPropertyStr + classMethodsStr + classDefFooter ;
}

function getHTMLStr(classNameObj){
				
	var htmlDefHeader ="<!DOCTYPE html>\n" + 
						"<html xmlns=\"http://www.w3.org/1999/xhtml\"\n" + 
						"	xmlns:th=\"http://www.thymeleaf.org\">\n" + 
						"	\n" + 
						"	<head>\n" + 
						"		<meta charset=\"UTF-8\">\n" + 
						"		<title>数据字典</title>\n" +
						"		\n" + 
						"		<!-- 引入全局css样式 -->\n" + 
						"		<script th:src=\"@{/resources/base/css-import.js}\" src=\"../../../../resources/base/css-import.js\"></script>\n" + 
						"	<head>\n" +
						"	\n" ;
	
	var bodyStrTabsStr = "\t";
	var bodyStr = 	bodyStrTabsStr + "<body>\n" + 
					bodyStrTabsStr + "	<div class=\"wrapper\">\n" + 
					bodyStrTabsStr + "		" + $("#div-row-list").html() + 
					bodyStrTabsStr + "		\n" + 
					bodyStrTabsStr + "	</div>\n" + 
					bodyStrTabsStr + "</body>\n" ;
	
	var importJsStrTabsStr = "\t";
	var importJsStr = 	importJsStrTabsStr + "\n" + 
						importJsStrTabsStr + "<!-- 引入全局JavaScript -->\n" + 
						importJsStrTabsStr + "<script th:src=\"@{/resources/base/javascript-import.js}\" src=\"../../../../resources/base/javascript-import.js\"></script>\n" + 
						importJsStrTabsStr + "<script th:src=\"@{/resources/"+classNameObj.jsName+"}\" src=\"../../../../resources/"+classNameObj.jsName+"\" ></script>\n" + 
						importJsStrTabsStr + "\n"; 

	var htmlDefFooter ="\n</html>\n";
	
						
	return htmlDefHeader + bodyStr + importJsStr + htmlDefFooter ;
}

function getJsStr(classNameObj){
				
	var initMethod ="$(document).ready(function(){\n" + 
					"	\n" + 
					"});\n\n\n";
						
	var insertMethod = "function insertEdit(id){\n" + 
						"	Utils.ajax({\n" + 
						"        url: contextPath + \"/admin/dictionary/table/\"+id,\n" + 
						"        type : \"GET\",\n" + 
						"        success: function(data){\n" + 
						"        	$(\"#modal-new-table\").modal('show');\n" + 
						"		}\n" + 
						"	});\n" + 
						"}\n" +
						"\n\n";
	var deleteMethod = "function insertEdit(id){\n" + 
						"	Utils.ajax({\n" + 
						"        url: contextPath + \"/admin/dictionary/table/\"+id,\n" + 
						"        type : \"GET\",\n" + 
						"        success: function(data){\n" + 
						"        	$(\"#modal-new-table\").modal('show');\n" + 
						"		}\n" + 
						"	});\n" + 
						"}\n" +
						"\n\n";
	var updateMethod = "function insertEdit(id){\n" + 
						"	Utils.ajax({\n" + 
						"        url: contextPath + \"/admin/dictionary/table/\"+id,\n" + 
						"        type : \"GET\",\n" + 
						"        success: function(data){\n" + 
						"        	$(\"#modal-new-table\").modal('show');\n" + 
						"		}\n" + 
						"	});\n" + 
						"}\n" +
						"\n\n";
	var searchMethod = "function insertEdit(id){\n" + 
						"	Utils.ajax({\n" + 
						"        url: contextPath + \"/admin/dictionary/table/\"+id,\n" + 
						"        type : \"GET\",\n" + 
						"        success: function(data){\n" + 
						"        	$(\"#modal-new-table\").modal('show');\n" + 
						"		}\n" + 
						"	});\n" + 
						"}\n" +
						"\n\n";
	
						
	return initMethod + insertMethod + deleteMethod + updateMethod + searchMethod;
}

function escapeSpecialChars(sourceStr){
	return sourceStr.replace(/</g,"&lt;").replace(/>/g,"&gt;")
}


