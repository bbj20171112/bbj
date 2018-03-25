/**
 * 引入公共的css样式
 */
/*var contextPath = 'bbj-web';
var contextPath = (function() {
	var path = location.pathname;
	if (path.indexOf('/') === 0) {
		path = path.substring(1);
	}
	return "/" + path.split('/')[0];
}());*/
var contextPath = getRealPath();

function getRealPath() {
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
	return basePath;
}

document
		.writeln('<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">');
document
		.writeln('<link rel="stylesheet" href="'
				+ contextPath
				+ '/resources/bower_components/font-awesome/css/font-awesome.min.css">');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/bower_components/Ionicons/css/ionicons.min.css">');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/dist/css/AdminLTE.css">');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/dist/css/skins/_all-skins.min.css">');
document.writeln('<!--[if lt IE 9]>');
document
		.writeln('<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>');
document
		.writeln('<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>');
document.writeln('<![endif]-->');

//document.writeln('<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">');

document.writeln('<!-- Pace style -->');
document
		.writeln('<link rel="stylesheet" href="'
				+ contextPath
				+ '/resources/bower_components/PACE/themes/blue/pace-theme-minimal.css">');

document.writeln('<!-- ICheck -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/plugins/iCheck/all.css">');

document.writeln('<!-- alert -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/framework/widgets/css/alert.css">');

document.writeln('<!-- grid -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/framework/widgets/css/grid.css">');

document.writeln('<!-- jquery.dataTables.css -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/bower_components/datatables.net/css/jquery.dataTables.css">');

document.writeln('<!-- dataTables -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">');

document.writeln('<!-- ionicons -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/bower_components/Ionicons/css/ionicons.min.css">');

document.writeln('<!-- Awesome Bootstrap Checkbox -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/plugins/AwesomeBootstrapCheckbox/awesome-bootstrap-checkbox.css">');

document.writeln('<!-- select2 -->');
document.writeln('<link rel="stylesheet" href="' + contextPath
		+ '/resources/bower_components/select2/dist/css/select2.css">');


