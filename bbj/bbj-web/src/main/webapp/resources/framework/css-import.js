/**
 * 引入公共的css样式
 */

document.writeln('<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">');
document.writeln('<link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css" th:href="@{/resources/bower_components/bootstrap/dist/css/bootstrap.min.css}">');
document.writeln('<link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css" th:href="@{/resources/bower_components/font-awesome/css/font-awesome.min.css}">');
document.writeln('<link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css" th:href="@{/resources/bower_components/Ionicons/css/ionicons.min.css}">');
document.writeln('<link rel="stylesheet" href="../dist/css/AdminLTE.css" th:href="@{/resources/dist/css/AdminLTE.css}">');
document.writeln('<link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css" th:href="@{/resources/dist/css/skins/_all-skins.min.css}">');
document.writeln('<!--[if lt IE 9]>');
document.writeln('<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>');
document.writeln('<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>');
document.writeln('<![endif]-->');

//document.writeln('<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">');

document.writeln('<!-- Pace style -->');
document.writeln('<link rel="stylesheet" href="../bower_components/PACE/themes/blue/pace-theme-minimal.css" th:href="@{/resources/bower_components/PACE/themes/blue/pace-theme-minimal.css}">');

document.writeln('<!-- ICheck -->');
document.writeln('<link rel="stylesheet" href="../plugins/iCheck/all.css" th:href="@{/resources/plugins/iCheck/all.css}">');

document.writeln('<!-- alert -->');
document.writeln('<link rel="stylesheet" href="../framework/widgets/css/alert.css" th:href="@{/resources/framework/widgets/css/alert.css}">');

document.writeln('<!-- grid -->');
document.writeln('<link rel="stylesheet" href="../framework/widgets/css/grid.css" th:href="@{/resources/framework/widgets/css/grid.css}">');

document.writeln('<!-- select2.css -->');
document.writeln('<link rel="stylesheet" href="../bower_components/select2/dist/css/select2.css" th:href="@{/resources/bower_components/select2/dist/css/select2.css}">');


document.writeln('<!-- base.css -->');
document.writeln('<link rel="stylesheet" href="../framework/common/css/base.css" th:href="@{/resources/framework/common/css/base.css}">');


