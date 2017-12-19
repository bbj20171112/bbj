/*
 * This makes sure that we can use the global
 * swal() function, instead of swal.default()
 * See: https://github.com/webpack/webpack/issues/3929
 */

if (typeof window !== 'undefined') {
  require(''+contextPath+'/resources/plugins/sweetalert/sweetalert.css');
}

require(''+contextPath+'/resources/plugins/sweetalert/polyfills');

var swal = require(''+contextPath+'/resources/plugins/sweetalert/core').default;

module.exports = swal;
