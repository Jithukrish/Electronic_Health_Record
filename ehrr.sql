/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 8.0.22 : Database - ehr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ehr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ehr`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add departments',7,'add_departments'),
(26,'Can change departments',7,'change_departments'),
(27,'Can delete departments',7,'delete_departments'),
(28,'Can view departments',7,'view_departments'),
(29,'Can add doctor',8,'add_doctor'),
(30,'Can change doctor',8,'change_doctor'),
(31,'Can delete doctor',8,'delete_doctor'),
(32,'Can view doctor',8,'view_doctor'),
(33,'Can add hospital',9,'add_hospital'),
(34,'Can change hospital',9,'change_hospital'),
(35,'Can delete hospital',9,'delete_hospital'),
(36,'Can view hospital',9,'view_hospital'),
(37,'Can add login',10,'add_login'),
(38,'Can change login',10,'change_login'),
(39,'Can delete login',10,'delete_login'),
(40,'Can view login',10,'view_login'),
(41,'Can add user',11,'add_user'),
(42,'Can change user',11,'change_user'),
(43,'Can delete user',11,'delete_user'),
(44,'Can view user',11,'view_user'),
(45,'Can add userrecord',12,'add_userrecord'),
(46,'Can change userrecord',12,'change_userrecord'),
(47,'Can delete userrecord',12,'delete_userrecord'),
(48,'Can view userrecord',12,'view_userrecord'),
(49,'Can add slot',13,'add_slot'),
(50,'Can change slot',13,'change_slot'),
(51,'Can delete slot',13,'delete_slot'),
(52,'Can view slot',13,'view_slot'),
(53,'Can add schedule',14,'add_schedule'),
(54,'Can change schedule',14,'change_schedule'),
(55,'Can delete schedule',14,'delete_schedule'),
(56,'Can view schedule',14,'view_schedule'),
(57,'Can add rating',15,'add_rating'),
(58,'Can change rating',15,'change_rating'),
(59,'Can delete rating',15,'delete_rating'),
(60,'Can view rating',15,'view_rating'),
(61,'Can add feedback',16,'add_feedback'),
(62,'Can change feedback',16,'change_feedback'),
(63,'Can delete feedback',16,'delete_feedback'),
(64,'Can view feedback',16,'view_feedback'),
(65,'Can add complaint',17,'add_complaint'),
(66,'Can change complaint',17,'change_complaint'),
(67,'Can delete complaint',17,'delete_complaint'),
(68,'Can view complaint',17,'view_complaint'),
(69,'Can add booking',18,'add_booking'),
(70,'Can change booking',18,'change_booking'),
(71,'Can delete booking',18,'delete_booking'),
(72,'Can view booking',18,'view_booking');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user` */

insert  into `auth_user`(`id`,`password`,`last_login`,`is_superuser`,`username`,`first_name`,`last_name`,`email`,`is_staff`,`is_active`,`date_joined`) values 
(1,'pbkdf2_sha256$390000$Y2CKDc4ggrBmOKyqbBcGY8$a87rYtjpgzKr++8pF5KQp4R2L8U0kuCufjwrniyn5yE=',NULL,1,'admin','','','',1,1,'2023-03-01 10:19:54.721482');

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(3,'auth','group'),
(2,'auth','permission'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(18,'ehr_app','booking'),
(17,'ehr_app','complaint'),
(7,'ehr_app','departments'),
(8,'ehr_app','doctor'),
(16,'ehr_app','feedback'),
(9,'ehr_app','hospital'),
(10,'ehr_app','login'),
(15,'ehr_app','rating'),
(14,'ehr_app','schedule'),
(13,'ehr_app','slot'),
(11,'ehr_app','user'),
(12,'ehr_app','userrecord'),
(6,'sessions','session');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'contenttypes','0001_initial','2023-03-01 10:18:24.565663'),
(2,'auth','0001_initial','2023-03-01 10:18:25.438663'),
(3,'admin','0001_initial','2023-03-01 10:18:25.664666'),
(4,'admin','0002_logentry_remove_auto_add','2023-03-01 10:18:25.681665'),
(5,'admin','0003_logentry_add_action_flag_choices','2023-03-01 10:18:25.716673'),
(6,'contenttypes','0002_remove_content_type_name','2023-03-01 10:18:25.979687'),
(7,'auth','0002_alter_permission_name_max_length','2023-03-01 10:18:26.081689'),
(8,'auth','0003_alter_user_email_max_length','2023-03-01 10:18:26.141717'),
(9,'auth','0004_alter_user_username_opts','2023-03-01 10:18:26.159661'),
(10,'auth','0005_alter_user_last_login_null','2023-03-01 10:18:26.270664'),
(11,'auth','0006_require_contenttypes_0002','2023-03-01 10:18:26.275662'),
(12,'auth','0007_alter_validators_add_error_messages','2023-03-01 10:18:26.287665'),
(13,'auth','0008_alter_user_username_max_length','2023-03-01 10:18:26.396662'),
(14,'auth','0009_alter_user_last_name_max_length','2023-03-01 10:18:26.518667'),
(15,'auth','0010_alter_group_name_max_length','2023-03-01 10:18:26.554669'),
(16,'auth','0011_update_proxy_permissions','2023-03-01 10:18:26.577668'),
(17,'auth','0012_alter_user_first_name_max_length','2023-03-01 10:18:26.764663'),
(18,'ehr_app','0001_initial','2023-03-01 10:18:28.908662'),
(19,'sessions','0001_initial','2023-03-01 10:18:28.987664');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('3265i5owcexjqzjx6ho3aew2g7zxskyn','eyJsaWQiOjJ9:1pXKNl:TWVIUc4SL8UNWwuK5SJy0PDqEezPhqtU_Bx7hyf-PGc','2023-03-15 11:12:25.611145');

/*Table structure for table `ehr_app_booking` */

DROP TABLE IF EXISTS `ehr_app_booking`;

CREATE TABLE `ehr_app_booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bdate` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `d_id_id` bigint NOT NULL,
  `s_id_id` bigint NOT NULL,
  `u_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_booking_d_id_id_10b13292_fk_ehr_app_doctor_id` (`d_id_id`),
  KEY `ehr_app_booking_s_id_id_1af4fb79_fk_ehr_app_schedule_id` (`s_id_id`),
  KEY `ehr_app_booking_u_id_id_a8d59ae6_fk_ehr_app_user_id` (`u_id_id`),
  CONSTRAINT `ehr_app_booking_d_id_id_10b13292_fk_ehr_app_doctor_id` FOREIGN KEY (`d_id_id`) REFERENCES `ehr_app_doctor` (`id`),
  CONSTRAINT `ehr_app_booking_s_id_id_1af4fb79_fk_ehr_app_schedule_id` FOREIGN KEY (`s_id_id`) REFERENCES `ehr_app_schedule` (`id`),
  CONSTRAINT `ehr_app_booking_u_id_id_a8d59ae6_fk_ehr_app_user_id` FOREIGN KEY (`u_id_id`) REFERENCES `ehr_app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_booking` */

/*Table structure for table `ehr_app_complaint` */

DROP TABLE IF EXISTS `ehr_app_complaint`;

CREATE TABLE `ehr_app_complaint` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `complaints` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `reply` varchar(100) NOT NULL,
  `u_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_complaint_u_id_id_5e59559c_fk_ehr_app_user_id` (`u_id_id`),
  CONSTRAINT `ehr_app_complaint_u_id_id_5e59559c_fk_ehr_app_user_id` FOREIGN KEY (`u_id_id`) REFERENCES `ehr_app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_complaint` */

/*Table structure for table `ehr_app_departments` */

DROP TABLE IF EXISTS `ehr_app_departments`;

CREATE TABLE `ehr_app_departments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `departmentname` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `H_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_departments_H_id_id_0ed26f19_fk_ehr_app_hospital_id` (`H_id_id`),
  CONSTRAINT `ehr_app_departments_H_id_id_0ed26f19_fk_ehr_app_hospital_id` FOREIGN KEY (`H_id_id`) REFERENCES `ehr_app_hospital` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_departments` */

/*Table structure for table `ehr_app_doctor` */

DROP TABLE IF EXISTS `ehr_app_doctor`;

CREATE TABLE `ehr_app_doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `regno` int NOT NULL,
  `dob` date NOT NULL,
  `image` varchar(100) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `phone` bigint NOT NULL,
  `email` varchar(100) NOT NULL,
  `specilization` varchar(100) NOT NULL,
  `d_id_id` bigint NOT NULL,
  `l_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_doctor_l_id_id_44060a92_fk_ehr_app_login_id` (`l_id_id`),
  KEY `ehr_app_doctor_d_id_id_662d0013_fk_ehr_app_departments_id` (`d_id_id`),
  CONSTRAINT `ehr_app_doctor_d_id_id_662d0013_fk_ehr_app_departments_id` FOREIGN KEY (`d_id_id`) REFERENCES `ehr_app_departments` (`id`),
  CONSTRAINT `ehr_app_doctor_l_id_id_44060a92_fk_ehr_app_login_id` FOREIGN KEY (`l_id_id`) REFERENCES `ehr_app_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_doctor` */

/*Table structure for table `ehr_app_feedback` */

DROP TABLE IF EXISTS `ehr_app_feedback`;

CREATE TABLE `ehr_app_feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `feedback` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `H_id_id` bigint NOT NULL,
  `u_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_feedback_H_id_id_470d31c6_fk_ehr_app_hospital_id` (`H_id_id`),
  KEY `ehr_app_feedback_u_id_id_10db3245_fk_ehr_app_user_id` (`u_id_id`),
  CONSTRAINT `ehr_app_feedback_H_id_id_470d31c6_fk_ehr_app_hospital_id` FOREIGN KEY (`H_id_id`) REFERENCES `ehr_app_hospital` (`id`),
  CONSTRAINT `ehr_app_feedback_u_id_id_10db3245_fk_ehr_app_user_id` FOREIGN KEY (`u_id_id`) REFERENCES `ehr_app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_feedback` */

/*Table structure for table `ehr_app_hospital` */

DROP TABLE IF EXISTS `ehr_app_hospital`;

CREATE TABLE `ehr_app_hospital` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hospitalname` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL,
  `pincode` bigint NOT NULL,
  `phone` bigint NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `l_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_hospital_l_id_id_619320b4_fk_ehr_app_login_id` (`l_id_id`),
  CONSTRAINT `ehr_app_hospital_l_id_id_619320b4_fk_ehr_app_login_id` FOREIGN KEY (`l_id_id`) REFERENCES `ehr_app_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_hospital` */

insert  into `ehr_app_hospital`(`id`,`hospitalname`,`image`,`place`,`pincode`,`phone`,`email`,`address`,`l_id_id`) values 
(1,'BABY MEMORIAL HOSPITAL','istockphoto-495900710-612x612.jpg','CALICUT',673572,9766567687,'babymemoriral@gmail.com','Baby memorial hospital, Indira ghandi road,calicut',2);

/*Table structure for table `ehr_app_login` */

DROP TABLE IF EXISTS `ehr_app_login`;

CREATE TABLE `ehr_app_login` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `usertype` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_login` */

insert  into `ehr_app_login`(`id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'baby','baby','hospital'),
(3,'uuu','uuu','doctor');

/*Table structure for table `ehr_app_rating` */

DROP TABLE IF EXISTS `ehr_app_rating`;

CREATE TABLE `ehr_app_rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rating` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `d_id_id` bigint NOT NULL,
  `u_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_rating_d_id_id_9c445630_fk_ehr_app_doctor_id` (`d_id_id`),
  KEY `ehr_app_rating_u_id_id_c71d369e_fk_ehr_app_user_id` (`u_id_id`),
  CONSTRAINT `ehr_app_rating_d_id_id_9c445630_fk_ehr_app_doctor_id` FOREIGN KEY (`d_id_id`) REFERENCES `ehr_app_doctor` (`id`),
  CONSTRAINT `ehr_app_rating_u_id_id_c71d369e_fk_ehr_app_user_id` FOREIGN KEY (`u_id_id`) REFERENCES `ehr_app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_rating` */

/*Table structure for table `ehr_app_schedule` */

DROP TABLE IF EXISTS `ehr_app_schedule`;

CREATE TABLE `ehr_app_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `day` varchar(100) NOT NULL,
  `AN` varchar(100) NOT NULL,
  `FN` varchar(100) NOT NULL,
  `Fromtime` time(6) NOT NULL,
  `Totime` time(6) NOT NULL,
  `H_id_id` bigint NOT NULL,
  `d_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_schedule_H_id_id_14674337_fk_ehr_app_hospital_id` (`H_id_id`),
  KEY `ehr_app_schedule_d_id_id_f58c45e8_fk_ehr_app_doctor_id` (`d_id_id`),
  CONSTRAINT `ehr_app_schedule_d_id_id_f58c45e8_fk_ehr_app_doctor_id` FOREIGN KEY (`d_id_id`) REFERENCES `ehr_app_doctor` (`id`),
  CONSTRAINT `ehr_app_schedule_H_id_id_14674337_fk_ehr_app_hospital_id` FOREIGN KEY (`H_id_id`) REFERENCES `ehr_app_hospital` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_schedule` */

/*Table structure for table `ehr_app_slot` */

DROP TABLE IF EXISTS `ehr_app_slot`;

CREATE TABLE `ehr_app_slot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `strength` int NOT NULL,
  `available` int NOT NULL,
  `Fromtime` time(6) NOT NULL,
  `Totime` time(6) NOT NULL,
  `d_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_slot_d_id_id_4c40a33e_fk_ehr_app_doctor_id` (`d_id_id`),
  CONSTRAINT `ehr_app_slot_d_id_id_4c40a33e_fk_ehr_app_doctor_id` FOREIGN KEY (`d_id_id`) REFERENCES `ehr_app_doctor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_slot` */

/*Table structure for table `ehr_app_user` */

DROP TABLE IF EXISTS `ehr_app_user`;

CREATE TABLE `ehr_app_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Fname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `place` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` int NOT NULL,
  `l_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_user_l_id_id_1a817167_fk_ehr_app_login_id` (`l_id_id`),
  CONSTRAINT `ehr_app_user_l_id_id_1a817167_fk_ehr_app_login_id` FOREIGN KEY (`l_id_id`) REFERENCES `ehr_app_login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_user` */

/*Table structure for table `ehr_app_userrecord` */

DROP TABLE IF EXISTS `ehr_app_userrecord`;

CREATE TABLE `ehr_app_userrecord` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `records` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `d_id_id` bigint NOT NULL,
  `u_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ehr_app_userrecord_d_id_id_82ce2ee3_fk_ehr_app_doctor_id` (`d_id_id`),
  KEY `ehr_app_userrecord_u_id_id_b7f88e18_fk_ehr_app_user_id` (`u_id_id`),
  CONSTRAINT `ehr_app_userrecord_d_id_id_82ce2ee3_fk_ehr_app_doctor_id` FOREIGN KEY (`d_id_id`) REFERENCES `ehr_app_doctor` (`id`),
  CONSTRAINT `ehr_app_userrecord_u_id_id_b7f88e18_fk_ehr_app_user_id` FOREIGN KEY (`u_id_id`) REFERENCES `ehr_app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ehr_app_userrecord` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
