
项目结构说明：
bbj -- 总体的项目结构
	bbj-base -- 核心模块，包含所有的项目必须的类的实现；是所有项目都需要的一个模块
		com.bbj.base -- 基础包
			controller -- 平台的Controller
			dao -- 平台的数据库层
			domain --  平台的对象
			filter -- 过滤器
			responseconverter --  请求相应消息处理器
			service -- 业务层
			utils -- 工具类
			
	bbj-web(war) --  前台模块，只有前台相关的页面、静态资源文件等
		src/main/webapp
			resources -- 静态资源文件(下面没有列举的文件或者目录可以暂时忽略)
				bower_components -- AdminLTE的自带前台框架
				documentation -- AdminLTE的使用说明文档
				framework -- bbj前台框架，需要我们不断进行完善！！
					utils -- 放js工具类
					menu -- menu相关的静态文件
					css-import.js -- 框架引入公共的css
					javascript-import.js -- 框架引入公共的js
				pages -- AdminLTE的页面示例
				plugins -- AdminLTE的使用的插件
				index.html、index.html -- AdminLTE的整体效果页面
				README.md -- AdminLTE的readme
			WEB-INF	-- 配置文件和页面
				html/framework -- 放置平台的页面
					menu -- 菜单页面
					template -- 常用模板页面
					widgets -- 组件页面
				dispatcher-servlet.xml
				
	bbj-example(war) -- demo模块，每一个功能的使用例子，包含测试，顺便测试相关的模块功能
		com.bbj.test -- 测试根目录
			base -- 测试base模块
				crud -- 测试base的基本增删改查
				entity -- 测试实体的基本您使用
				rtnObj -- 测试返回对象
	
	
		
