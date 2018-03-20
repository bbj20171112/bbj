/**
 * 引入全局 JavaScript 文件
 */

// contextPath -- 已经在css-import里面定义
// 示例：contextPath = /bbj-web
document.writeln('<!-- REQUIRED JS SCRIPTS -->');

document.writeln('<!-- jQuery 3 -->');
document.writeln('<script src="../bower_components/jquery/dist/jquery.min.js" th:src="@{/resources/bower_components/jquery/dist/jquery.min.js}"></script>');

document.writeln('<!-- jQuery UI 1.11.4 -->');
document.writeln('<script src="../bower_components/jquery-ui/jquery-ui.min.js" th:src="@{/resources/bower_components/jquery-ui/jquery-ui.min.js}"></script>');

document.writeln('<!-- Bootstrap 3.3.7 -->');
document
		.writeln('<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js" th:src="@{/resources/bower_components/bootstrap/dist/js/bootstrap.min.js}"></script>');

document.writeln('<!-- AdminLTE App -->');
document.writeln('<script src="../dist/js/adminlte.js" th:src="@{/resources/dist/js/adminlte.js}"></script>');

document.writeln('<!-- PACE -->');
document.writeln('<script src="../bower_components/PACE/pace.min.js" th:src="@{/resources/bower_components/PACE/pace.min.js}"></script>');

document.writeln('<!-- SlimScroll -->');
document
		.writeln('<script src="../bower_components/jquery-slimscroll/jquery.slimscroll.min.js" th:src="@{/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js}"></script>');

document.writeln('<!-- FastClick -->');
document.writeln('<script src="../bower_components/fastclick/lib/fastclick.js" th:src="@{/resources/bower_components/fastclick/lib/fastclick.js}"></script>');

document.writeln('<!-- echarts.common.min.js -->');
document.writeln('<script src="../plugins/echarts/echarts.common.min.js" th:src="@{/resources/plugins/echarts/echarts.common.min.js}"></script>');

document.writeln('<!-- mustache -->');
document.writeln('<script src="../plugins/mustache/mustache.js" th:src="@{/resources/plugins/mustache/mustache.js}"></script>');

document.writeln('<!-- bbj.controls.js -->');
document.writeln('<script src="../framework/bbj.controls.js" th:src="@{/resources/framework/bbj.controls.js}"></script>');

document.writeln('<!-- bbj.js -->');
document.writeln('<script src="../framework/bbj.js" th:src="@{/resources/framework/bbj.js}"></script>');

document.writeln('<!-- Utils -->');
document.writeln('<script src="../framework/utils/utils.js" th:src="@{/resources/framework/utils/utils.js}"></script>');

document.writeln('<!-- DataTables -->');
document.writeln('<!-- grid 基于 DataTables 进行改装 -->');
document.writeln('<script src="../framework/widgets/js/grid.js" th:src="@{/resources/framework/widgets/js/grid.js}"></script>');
// document.writeln('<script src="../bower_components/datatables.net/js/jquery.dataTables.js"></script>');
document
		.writeln('<script src="../bower_components/datatables.net-bs/js/dataTables.bootstrap.js" th:src="@{/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.js}"></script>');

document.writeln('<!-- SlimScroll -->');
document
		.writeln('<script src="../bower_components/jquery-slimscroll/jquery.slimscroll.min.js" th:src="@{/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js}"></script>');

document.writeln('<!-- FastClick -->');
document.writeln('<script src="../bower_components/fastclick/lib/fastclick.js" th:src="@{/resources/bower_components/fastclick/lib/fastclick.js}"></script>');

document.writeln('<!-- iCheck -->');
document.writeln('<script src="../plugins/iCheck/icheck.min.js" th:src="@{/resources/plugins/iCheck/icheck.min.js}"></script>');

document.writeln('<!-- colResizable -->');
document.writeln('<script src="../plugins/colResizable/colResizable-1.5.min.js" th:src="@{/resources/plugins/colResizable/colResizable-1.5.min.js}"></script>');

document.writeln('<!-- SweetAlert -->');
document.writeln('<script src="../plugins/sweetalert/sweetalert.min.js" th:src="@{/resources/plugins/sweetalert/sweetalert.min.js}"></script>');

document.writeln('<!-- layer -->');
document.writeln('<script src="../plugins/layer/layer.min.js" th:src="@{/resources/plugins/layer/layer.min.js}"></script>');

document.writeln('<!-- alert -->');
document.writeln('<script src="../framework/widgets/js/alert.js" th:src="@{/resources/framework/widgets/js/alert.js}"></script>');

document.writeln('<!-- select2.js -->');
document
		.writeln('<script src="../bower_components/select2/dist/js/select2.full.js" th:src="@{/resources/bower_components/select2/dist/js/select2.full.js}"></script>');

document.writeln('<!-- base.js -->');
document.writeln('<script src="../framework/common/js/base.js" th:src="@{/resources/framework/common/js/base.js}"></script>');
