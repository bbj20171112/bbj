/*
SQLyog v10.2 
MySQL - 5.5.43-log : Database - bbj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bbj` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bbj`;

/*Table structure for table `admin_dictionary_field` */

DROP TABLE IF EXISTS `admin_dictionary_field`;

CREATE TABLE `admin_dictionary_field` (
  `id` varchar(64) DEFAULT NULL,
  `table_name` varchar(64) DEFAULT NULL,
  `field_name` varchar(1024) DEFAULT NULL,
  `field_name_comment` varchar(64) DEFAULT NULL,
  `field_type` varchar(256) DEFAULT NULL,
  `field_type_comment` varchar(1024) DEFAULT NULL,
  `field_show_type` varchar(256) DEFAULT NULL,
  `field_show_type_comment` varchar(1024) DEFAULT NULL,
  `field_constraint` varchar(1024) DEFAULT NULL,
  `field_constraint_comment` varchar(1024) DEFAULT NULL,
  `field_reference_table_name` varchar(64) DEFAULT NULL,
  `field_reference_table_field_name` varchar(1024) DEFAULT NULL,
  `field_reference_table_field_value` varchar(1024) DEFAULT NULL,
  `field_reference_comment` varchar(1024) DEFAULT NULL,
  `field_remark` varchar(1024) DEFAULT NULL,
  `field_order_number` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_dictionary_field` */

insert  into `admin_dictionary_field`(`id`,`table_name`,`field_name`,`field_name_comment`,`field_type`,`field_type_comment`,`field_show_type`,`field_show_type_comment`,`field_constraint`,`field_constraint_comment`,`field_reference_table_name`,`field_reference_table_field_name`,`field_reference_table_field_value`,`field_reference_comment`,`field_remark`,`field_order_number`,`create_time`,`update_time`,`create_staff_id`,`update_staff_id`,`delete_state`) values ('1522551071158','tbUser','id','ID','varchar(64)','ID','input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'2018-04-01 10:51:11','2018-05-15 22:38:07',NULL,NULL,'0'),('1522551706203','tbUser','name','姓名','varchar(64)','姓名','input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'2018-04-01 11:01:46','2018-04-01 11:04:24',NULL,NULL,'0'),('1522551732470','tbUser','sex','性别','varchar(64)','性别','select',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'2018-04-01 11:02:12','2018-05-18 22:24:10',NULL,NULL,'0'),('1522551763741','tbUser','love','爱好','varchar(64)','爱好','checkbox',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,'2018-04-01 11:02:43','2018-05-18 22:24:00',NULL,NULL,'0'),('1522551788862','tbUser','icon','头像','varchar(64)','头像','img',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,'2018-04-01 11:03:08','2018-04-01 11:16:43',NULL,NULL,'0'),('1522551884178','tbUser','birthday','出生日期','varchar(64)','出生日期','date',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,'2018-04-01 11:04:44','2018-04-01 11:04:54',NULL,NULL,'0'),('1522551965005','tbUser','latest_update_time','最近活跃时间','varchar(64)','最近活跃时间','datetime',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,'2018-04-01 11:06:05','2018-05-18 21:57:39',NULL,NULL,'0'),('1522585042820','tbUser','introduction','简介','varchar(64)','简介','textarea',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,'2018-04-01 20:17:22','2018-04-01 20:17:52',NULL,NULL,'0'),('1524497533745','admin_menu','id','ID','varchar(64)','ID','input','ID',NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,'2018-04-23 23:32:13','2018-05-15 22:38:33',NULL,NULL,'0'),('1524497580768','admin_menu','menu_name','菜单名称','varchar(64)','menu_name','input','名称',NULL,NULL,NULL,NULL,NULL,NULL,NULL,11,'2018-04-23 23:33:00','2018-05-14 21:35:48',NULL,NULL,'0'),('1524497607524','admin_menu','up_menu_id','上级菜单ID','varchar(64)','up_menu_id','input','上级菜单',NULL,NULL,NULL,NULL,NULL,NULL,NULL,17,'2018-04-23 23:33:27','2018-05-14 21:36:00',NULL,NULL,'0'),('1524497622312','admin_menu','program_id','程序ID','varchar(64)','program_id','input','程序ID',NULL,NULL,NULL,NULL,NULL,NULL,NULL,16,'2018-04-23 23:33:42','2018-05-14 21:36:06',NULL,NULL,'0'),('1524497637902','admin_menu','menu_icon','菜单图标','varchar(64)','menu_icon','img','菜单图标',NULL,NULL,NULL,NULL,NULL,NULL,NULL,15,'2018-04-23 23:33:57','2018-05-14 20:15:39',NULL,NULL,'0'),('1524497651963','admin_menu','menu_link','菜单链接','varchar(64)','menu_link','input','链接',NULL,NULL,NULL,NULL,NULL,NULL,NULL,13,'2018-04-23 23:34:11','2018-05-14 21:36:11',NULL,NULL,'0'),('1524497699859','admin_menu','menu_remark','菜单备注','varchar(64)','menu_remark','textarea','备注',NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,'2018-04-23 23:34:59','2018-05-14 20:15:55',NULL,NULL,'0'),('1525515584797','admin_test','id','ID','varchar(64)','ID','input','ID',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2018-05-05 18:19:44','2018-06-03 21:54:53',NULL,NULL,'0'),('1525515615003','admin_test','name','名称','varchar(64)','NAME','input','ID',NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,'2018-05-05 18:20:15','2018-06-03 21:55:48',NULL,NULL,'0'),('1525515643008','admin_test','sex','性别','varchar(64)',NULL,'select','ID',NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'2018-05-05 18:20:43','2018-06-03 21:55:12',NULL,NULL,'0'),('1525515696868','admin_test','icon','icon','varchar(64)','icon','img','icon',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'2018-05-05 18:21:36','2018-05-18 22:23:53',NULL,NULL,'0'),('1525516185960','admin_test','birthday','ok','varchar(64)','ok','date','icon',NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,'2018-05-05 18:29:45','2018-06-03 21:55:36',NULL,NULL,'0'),('1525519945176','admin_test','user_name','user_name','varchar(64)','user_name','label','user_name',NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'2018-05-05 19:32:25','2018-05-18 22:22:20',NULL,NULL,'1'),('1526568990474','admin_menu','menu_number','菜单编号','varchar(64)','menu_number','input','menu_number',NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,'2018-05-17 22:56:30','2018-05-18 20:48:47',NULL,NULL,'0'),('1526719794391','admin_menu','menu_order_number','序号','varchar(64)',NULL,'label',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,'2018-05-19 16:49:54','2018-05-19 16:50:00',NULL,NULL,'0'),('1527170247104','admin_type','id','ID','varchar(64)',NULL,'label',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-05-24 21:57:27',NULL,NULL,NULL,'0'),('1527170798352','admin_type','type_key','类型key','varchar(64)',NULL,'label',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-05-24 22:06:38',NULL,NULL,NULL,'0'),('1527170819235','admin_type','type_value','类型值','varchar(64)',NULL,'label',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-05-24 22:06:59',NULL,NULL,NULL,'0'),('1527170845259','admin_type','type_remark','备注','varchar(64)',NULL,'label',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-05-24 22:07:25',NULL,NULL,NULL,'0'),('1527170905893','admin_test','test_type','测试类型','varchar(64)',NULL,'select',NULL,NULL,NULL,'admin_type','type_key','type_value',NULL,NULL,7,'2018-05-24 22:08:25','2018-06-03 21:55:59',NULL,NULL,'0'),('1527413593284','admin_type','id','ID','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,-1,'2018-05-27 17:33:13','2018-05-27 17:56:57',NULL,NULL,'0'),('1527413617187','admin_type','type_key','类型key','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2018-05-27 17:33:37','2018-05-27 17:57:03',NULL,NULL,'0'),('1527413633441','admin_type','type_value','类型value','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'2018-05-27 17:33:53','2018-05-27 17:57:09',NULL,NULL,'0'),('1527413646398','admin_type','type_remark','类型remark','varchar(64)',NULL,'textarea',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'2018-05-27 17:34:06','2018-05-27 17:57:17',NULL,NULL,'0'),('1527932693985','org_company','code','公司编号','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-06-02 17:44:53',NULL,NULL,NULL,'0'),('1527932710684','org_company','name','公司名称','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-06-02 17:45:10',NULL,NULL,NULL,'0'),('1527932814959','org_department','code','编号','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-06-02 17:46:54',NULL,NULL,NULL,'0'),('1527932829774','org_department','name','名称','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-06-02 17:47:09',NULL,NULL,NULL,'0'),('1527932994875','org_position','code','编号','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-06-02 17:49:54',NULL,NULL,NULL,'0'),('1527933022960','org_position','name','名称','varchar(64)',NULL,'input',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-06-02 17:50:22',NULL,NULL,NULL,'0'),('1527933082566','org_position','dep_code','部门编号','varchar(64)',NULL,'input',NULL,NULL,NULL,'org_department','code','name',NULL,NULL,NULL,'2018-06-02 17:51:22',NULL,NULL,NULL,'0'),('1527933124895','org_department','com_code','公司编号','varchar(64)',NULL,'input',NULL,NULL,NULL,'org_company','code','name',NULL,NULL,NULL,'2018-06-02 17:52:04',NULL,NULL,NULL,'0');

/*Table structure for table `admin_dictionary_reference` */

DROP TABLE IF EXISTS `admin_dictionary_reference`;

CREATE TABLE `admin_dictionary_reference` (
  `id` varchar(64) DEFAULT NULL,
  `table_id` varchar(64) DEFAULT NULL,
  `reference_value` varchar(64) DEFAULT NULL,
  `reference_name` varchar(1024) DEFAULT NULL,
  `reference_remark` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_dictionary_reference` */

/*Table structure for table `admin_dictionary_table` */

DROP TABLE IF EXISTS `admin_dictionary_table`;

CREATE TABLE `admin_dictionary_table` (
  `id` varchar(64) DEFAULT NULL,
  `table_name` varchar(256) DEFAULT NULL,
  `table_comment` varchar(64) DEFAULT NULL,
  `table_description` varchar(256) DEFAULT NULL,
  `sequence_id` varchar(256) DEFAULT NULL,
  `table_remark` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_dictionary_table` */

insert  into `admin_dictionary_table`(`id`,`table_name`,`table_comment`,`table_description`,`sequence_id`,`table_remark`,`create_time`,`update_time`,`create_staff_id`,`update_staff_id`,`delete_state`) values ('tbid_dictionary_reference','dictionary_reference','tbcomm_0','tbdesc_0','tbseq_0','tbremark_0','2018-01-15 23:40:05',NULL,'0','0','1'),('1522550999489','admin_user','用户表','用户表',NULL,'用户表','2018-04-01 10:49:59','2018-06-02 17:17:11',NULL,NULL,'0'),('1524497478126','admin_menu','admin_menu','系统菜单',NULL,NULL,'2018-04-23 23:31:18',NULL,NULL,NULL,'0'),('1525515537026','admin_test','admin_test','测试表',NULL,'admin_test','2018-05-05 18:18:57','2018-06-02 17:41:18',NULL,NULL,'0'),('1527413520879','admin_type','类型表','类型表',NULL,'类型表','2018-05-27 17:32:00',NULL,NULL,NULL,'0'),(NULL,'org_company','公司表','公司表',NULL,'公司表','2018-06-02 17:43:55',NULL,NULL,NULL,'0'),(NULL,'org_department','部门','部门',NULL,'部门','2018-06-02 17:46:08',NULL,NULL,NULL,'0'),(NULL,'org_position','岗位','岗位',NULL,'岗位','2018-06-02 17:46:28',NULL,NULL,NULL,'0');

/*Table structure for table `admin_menu` */

DROP TABLE IF EXISTS `admin_menu`;

CREATE TABLE `admin_menu` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `id` varchar(64) DEFAULT NULL,
  `menu_name` varchar(64) DEFAULT NULL,
  `up_menu_id` varchar(64) DEFAULT NULL,
  `program_id` varchar(64) DEFAULT NULL,
  `menu_icon` varchar(64) DEFAULT NULL,
  `menu_link` varchar(64) DEFAULT NULL,
  `menu_remark` varchar(64) DEFAULT NULL,
  `menu_number` varchar(64) DEFAULT NULL,
  `menu_order_number` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_menu` */

insert  into `admin_menu`(`create_time`,`update_time`,`create_staff_id`,`update_staff_id`,`delete_state`,`id`,`menu_name`,`up_menu_id`,`program_id`,`menu_icon`,`menu_link`,`menu_remark`,`menu_number`,`menu_order_number`) values ('2018-05-15 23:27:55','2018-05-19 16:38:22',NULL,NULL,'0','1526398075162','菜单管理','0','abcd',NULL,'/admin/menu/menu/page',NULL,NULL,NULL),('2018-05-16 22:53:31','2018-05-19 16:35:36',NULL,NULL,'0','1526482411444','开发者管理','0','cddd',NULL,'','给开发人员使用',NULL,NULL),('2018-05-16 22:53:42',NULL,NULL,NULL,'1','1526482422680','dsd','dd','ddd',NULL,'','',NULL,NULL),('2018-05-19 15:56:07','2018-05-19 16:38:34',NULL,NULL,'0','1526716567659','常用组件','1526482411444',NULL,NULL,NULL,NULL,NULL,NULL),('2018-05-19 15:56:46',NULL,NULL,NULL,'0','1526716606486','对话框','1526716567659',NULL,NULL,'/base/widgets/alert',NULL,NULL,NULL),('2018-05-19 15:59:11',NULL,NULL,NULL,'0','1526716751412','表格','1526716567659',NULL,NULL,'/base/widgets/grid',NULL,NULL,NULL),('2018-05-19 16:40:27','2018-05-19 16:48:38',NULL,NULL,'0','1526719227696','数据字典','0',NULL,NULL,NULL,NULL,NULL,NULL),('2018-05-19 16:41:56',NULL,NULL,NULL,'0','1526719316639','表管理','1526719227696',NULL,NULL,'/admin/dictionary/table/page',NULL,NULL,NULL),('2018-05-19 16:42:26',NULL,NULL,NULL,'0','1526719346467','字段管理','1526719227696',NULL,NULL,'/admin/dictionary/field/page',NULL,NULL,NULL),('2018-05-19 16:43:08','2018-05-19 16:46:44',NULL,NULL,'0','1526719388293','Layout设计','1526719517639',NULL,NULL,'/admin/designer/layout',NULL,NULL,NULL),('2018-05-19 16:43:35','2018-05-19 16:46:50',NULL,NULL,'0','1526719415435','Form设计','1526719517639',NULL,NULL,'/admin/designer/form',NULL,NULL,NULL),('2018-05-19 16:45:17',NULL,NULL,NULL,'0','1526719517639','页面设计','1526482411444',NULL,NULL,NULL,NULL,NULL,NULL),('2018-05-23 21:55:13',NULL,NULL,NULL,'0','1527083713984','参照值管理','1526719227696',NULL,NULL,'/admin/dictionary/reference/page',NULL,NULL,NULL),('2018-06-02 22:10:59','2018-06-03 10:39:37','','','','1527948659259','组织机构','0','','','','','',''),('2018-06-03 11:03:33',NULL,NULL,NULL,'0','1527995013923','公司管理','1527948659259',NULL,NULL,'/organization/company/page',NULL,NULL,NULL),('2018-06-03 11:08:52',NULL,NULL,NULL,'0','1527995332790','部门管理','1527948659259',NULL,NULL,'/organization/department/page',NULL,NULL,NULL),('2018-06-03 11:10:36',NULL,NULL,NULL,'0','1527995436094','岗位管理','1527948659259',NULL,NULL,'/organization/position/page',NULL,NULL,NULL);

/*Table structure for table `admin_test` */

DROP TABLE IF EXISTS `admin_test`;

CREATE TABLE `admin_test` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `id` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `sex` varchar(64) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `test_type` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_test` */

insert  into `admin_test`(`create_time`,`update_time`,`create_staff_id`,`update_staff_id`,`delete_state`,`id`,`name`,`sex`,`icon`,`birthday`,`test_type`) values ('2018-05-09 20:38:50','2018-05-09 21:05:30',NULL,NULL,'1','1525869530552','aaa','b','ccafff','0000-00-00','01'),('2018-05-09 21:05:23','2018-05-09 21:06:07',NULL,NULL,'1','1525871123220','a','aaa','aa','0000-00-00','02'),('2018-05-09 21:10:54',NULL,NULL,NULL,'1','1525871454379','ad','dddd','dd',NULL,'01'),('2018-05-09 21:11:13',NULL,NULL,NULL,'0','1525871473458','ss','sss','ss',NULL,'02'),('2018-05-14 18:02:57',NULL,NULL,NULL,'1','1526292177222','ddddddd','d','d',NULL,'01');

/*Table structure for table `admin_type` */

DROP TABLE IF EXISTS `admin_type`;

CREATE TABLE `admin_type` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `id` varchar(64) DEFAULT NULL,
  `type_key` varchar(64) DEFAULT NULL,
  `type_value` varchar(64) DEFAULT NULL,
  `type_remark` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_type` */

insert  into `admin_type`(`create_time`,`update_time`,`create_staff_id`,`update_staff_id`,`delete_state`,`id`,`type_key`,`type_value`,`type_remark`) values ('2018-05-27 17:58:54',NULL,NULL,NULL,'0','1527415134300','01','类型1','类型1类型1'),('2018-05-27 17:59:03',NULL,NULL,NULL,'0','1527415143381','02','类型2','类型2');

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `id` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `sex` varchar(64) DEFAULT NULL,
  `love` varchar(64) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `birthday` varchar(64) DEFAULT NULL,
  `latest_update_time` varchar(64) DEFAULT NULL,
  `introduction` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_user` */

/*Table structure for table `org_company` */

DROP TABLE IF EXISTS `org_company`;

CREATE TABLE `org_company` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org_company` */

insert  into `org_company`(`create_time`,`update_time`,`create_staff_id`,`update_staff_id`,`delete_state`,`code`,`name`) values ('2018-06-02 22:18:19',NULL,NULL,NULL,'0','test','测试公司');

/*Table structure for table `org_department` */

DROP TABLE IF EXISTS `org_department`;

CREATE TABLE `org_department` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `com_code` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org_department` */

/*Table structure for table `org_position` */

DROP TABLE IF EXISTS `org_position`;

CREATE TABLE `org_position` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `dep_code` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org_position` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
