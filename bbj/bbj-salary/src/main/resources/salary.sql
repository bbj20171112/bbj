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

/*Table structure for table `fin_salary` */

DROP TABLE IF EXISTS `fin_salary`;

CREATE TABLE `fin_salary` (
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_staff_id` varchar(64) DEFAULT NULL,
  `update_staff_id` varchar(64) DEFAULT NULL,
  `delete_state` varchar(2) DEFAULT NULL,
  `base_salary` varchar(64) DEFAULT NULL,
  `merit_pay` varchar(64) DEFAULT NULL,
  `job_type_add` varchar(64) DEFAULT NULL,
  `serve_age` varchar(64) DEFAULT NULL,
  `professor_add` varchar(64) DEFAULT NULL,
  `add_cut` float DEFAULT NULL,
  `food_site` float DEFAULT NULL,
  `food_office` varchar(64) DEFAULT NULL,
  `weekends_add` varchar(64) DEFAULT NULL,
  `festival_add` varchar(64) DEFAULT NULL,
  `site_add` varchar(64) DEFAULT NULL,
  `remote_add` varchar(64) DEFAULT NULL,
  `tired_add` varchar(64) DEFAULT NULL,
  `add_cut2` varchar(64) DEFAULT NULL,
  `draw_percent` varchar(64) DEFAULT NULL,
  `drive_amount` varchar(64) DEFAULT NULL,
  `work_amount` varchar(64) DEFAULT NULL,
  `add_cut3` varchar(64) DEFAULT NULL,
  `bonus_safe` varchar(64) DEFAULT NULL,
  `bonus_check` varchar(64) DEFAULT NULL,
  `bonus_certificate` varchar(64) DEFAULT NULL,
  `bonus_communicate` varchar(64) DEFAULT NULL,
  `bonus_hot` varchar(64) DEFAULT NULL,
  `travel_expense` varchar(64) DEFAULT NULL,
  `computer` varchar(64) DEFAULT NULL,
  `bonus_annual` varchar(64) DEFAULT NULL,
  `good_start` varchar(64) DEFAULT NULL,
  `spring_festival` varchar(64) DEFAULT NULL,
  `company_peer` varchar(64) DEFAULT NULL,
  `mid_autumn` varchar(64) DEFAULT NULL,
  `dragon_boat_festival` varchar(64) DEFAULT NULL,
  `bonus_drive_class` varchar(64) DEFAULT NULL,
  `double_beyond_half` varchar(64) DEFAULT NULL,
  `summer_hot` varchar(64) DEFAULT NULL,
  `special1` varchar(64) DEFAULT NULL,
  `special2` varchar(64) DEFAULT NULL,
  `special3` varchar(64) DEFAULT NULL,
  `special4` varchar(64) DEFAULT NULL,
  `total` varchar(64) DEFAULT NULL,
  `insurance_self` varchar(64) DEFAULT NULL,
  `public_reserve_funds_self` varchar(64) DEFAULT NULL,
  `person_income_tax` varchar(64) DEFAULT NULL,
  `withhold` varchar(64) DEFAULT NULL,
  `fact_total` varchar(64) DEFAULT NULL,
  `insurance_com` varchar(64) DEFAULT NULL,
  `public_reserve_funds_com` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fin_salary` */

insert  into `fin_salary`(`create_time`,`update_time`,`create_staff_id`,`update_staff_id`,`delete_state`,`base_salary`,`merit_pay`,`job_type_add`,`serve_age`,`professor_add`,`add_cut`,`food_site`,`food_office`,`weekends_add`,`festival_add`,`site_add`,`remote_add`,`tired_add`,`add_cut2`,`draw_percent`,`drive_amount`,`work_amount`,`add_cut3`,`bonus_safe`,`bonus_check`,`bonus_certificate`,`bonus_communicate`,`bonus_hot`,`travel_expense`,`computer`,`bonus_annual`,`good_start`,`spring_festival`,`company_peer`,`mid_autumn`,`dragon_boat_festival`,`bonus_drive_class`,`double_beyond_half`,`summer_hot`,`special1`,`special2`,`special3`,`special4`,`total`,`insurance_self`,`public_reserve_funds_self`,`person_income_tax`,`withhold`,`fact_total`,`insurance_com`,`public_reserve_funds_com`) values (NULL,NULL,NULL,NULL,'0','2340','2006','0','350',NULL,NULL,NULL,'1350',NULL,NULL,'0',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'1000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'7046','756.97','569','117','0','5603','2113','569'),(NULL,NULL,NULL,NULL,'0','1833','1571','0','350',NULL,NULL,600,NULL,NULL,NULL,'521.4',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'1000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'5876','587.08','440','104.9','0','4744','1632','440'),(NULL,NULL,NULL,NULL,'0','2022','1733','0','350',NULL,NULL,600,NULL,NULL,NULL,'557.7',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'1000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'6912','721.8','542','194.8','0','5453','2013','542');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
