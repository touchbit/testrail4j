-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: testrail
-- ------------------------------------------------------
-- Server version	5.7.22
DROP DATABASE IF EXISTS `testrail`;
CREATE DATABASE testrail;
USE testrail;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` int(11) DEFAULT NULL,
  `end_date` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `view` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `filename` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `size` int(11) NOT NULL,
  `created_on` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `case_id` int(11) DEFAULT NULL,
  `test_change_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_attachments_case_id` (`case_id`),
  KEY `ix_attachments_test_change_id` (`test_change_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `case_assocs`
--

DROP TABLE IF EXISTS `case_assocs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `case_assocs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_case_assocs_case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_assocs`
--

LOCK TABLES `case_assocs` WRITE;
/*!40000 ALTER TABLE `case_assocs` DISABLE KEYS */;
/*!40000 ALTER TABLE `case_assocs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `case_changes`
--

DROP TABLE IF EXISTS `case_changes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `case_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `created_on` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `changes` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `ix_case_changes_case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_changes`
--

LOCK TABLES `case_changes` WRITE;
/*!40000 ALTER TABLE `case_changes` DISABLE KEYS */;
/*!40000 ALTER TABLE `case_changes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `case_types`
--

DROP TABLE IF EXISTS `case_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `case_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `is_default` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_types`
--

LOCK TABLES `case_types` WRITE;
/*!40000 ALTER TABLE `case_types` DISABLE KEYS */;
INSERT INTO `case_types` VALUES (1,'Acceptance',0,0),(2,'Accessibility',0,0),(3,'Automated',0,0),(4,'Compatibility',0,0),(5,'Destructive',0,0),(6,'Functional',0,0),(7,'Other',1,0),(8,'Performance',0,0),(9,'Regression',0,0),(10,'Security',0,0),(11,'Smoke & Sanity',0,0),(12,'Usability',0,0);
/*!40000 ALTER TABLE `case_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cases`
--

DROP TABLE IF EXISTS `cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cases` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) NOT NULL,
  `title` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `display_order` int(11) NOT NULL,
  `priority_id` int(11) NOT NULL,
  `estimate` int(11) DEFAULT NULL,
  `milestone_id` int(11) DEFAULT NULL,
  `custom_preconds` longtext COLLATE utf8_unicode_ci,
  `custom_steps` longtext COLLATE utf8_unicode_ci,
  `custom_expected` longtext COLLATE utf8_unicode_ci,
  `custom_steps_separated` longtext COLLATE utf8_unicode_ci,
  `custom_mission` longtext COLLATE utf8_unicode_ci,
  `custom_goals` longtext COLLATE utf8_unicode_ci,
  `custom_automation_type` int(11) DEFAULT '0',
  `type_id` int(11) NOT NULL,
  `is_copy` tinyint(1) NOT NULL,
  `copyof_id` int(11) DEFAULT NULL,
  `created_on` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `estimate_forecast` int(11) DEFAULT NULL,
  `refs` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `suite_id` int(11) NOT NULL,
  `updated_on` int(11) NOT NULL,
  `updated_by` int(11) NOT NULL,
  `template_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_cases_section_id` (`section_id`),
  KEY `ix_cases_suite_id` (`suite_id`),
  KEY `ix_cases_copyof_id` (`copyof_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cases`
--

LOCK TABLES `cases` WRITE;
/*!40000 ALTER TABLE `cases` DISABLE KEYS */;
/*!40000 ALTER TABLE `cases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_groups`
--

DROP TABLE IF EXISTS `config_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_config_groups_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_groups`
--

LOCK TABLES `config_groups` WRITE;
/*!40000 ALTER TABLE `config_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configs`
--

DROP TABLE IF EXISTS `configs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_configs_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configs`
--

LOCK TABLES `configs` WRITE;
/*!40000 ALTER TABLE `configs` DISABLE KEYS */;
/*!40000 ALTER TABLE `configs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `defects`
--

DROP TABLE IF EXISTS `defects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `defects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `defect_id` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `test_change_id` int(11) NOT NULL,
  `case_id` int(11) DEFAULT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_defects_defect_id` (`defect_id`),
  KEY `ix_defects_test_change_id` (`test_change_id`),
  KEY `ix_defects_case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defects`
--

LOCK TABLES `defects` WRITE;
/*!40000 ALTER TABLE `defects` DISABLE KEYS */;
/*!40000 ALTER TABLE `defects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exports`
--

DROP TABLE IF EXISTS `exports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `size` bigint(20) NOT NULL,
  `created_on` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_exports_created_on` (`created_on`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exports`
--

LOCK TABLES `exports` WRITE;
/*!40000 ALTER TABLE `exports` DISABLE KEYS */;
/*!40000 ALTER TABLE `exports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `field_templates`
--

DROP TABLE IF EXISTS `field_templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `field_templates` (
  `field_id` int(11) NOT NULL,
  `template_id` int(11) NOT NULL,
  PRIMARY KEY (`field_id`,`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field_templates`
--

LOCK TABLES `field_templates` WRITE;
/*!40000 ALTER TABLE `field_templates` DISABLE KEYS */;
INSERT INTO `field_templates` VALUES (1,1),(1,2),(2,1),(3,1),(10,2),(11,2),(12,3),(13,3);
/*!40000 ALTER TABLE `field_templates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fields`
--

DROP TABLE IF EXISTS `fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fields` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `system_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `entity_id` int(11) NOT NULL,
  `label` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `type_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `display_order` int(11) NOT NULL,
  `configs` longtext COLLATE utf8_unicode_ci NOT NULL,
  `is_multi` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `status_id` int(11) NOT NULL,
  `is_system` tinyint(1) NOT NULL,
  `include_all` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_fields_name` (`entity_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fields`
--

LOCK TABLES `fields` WRITE;
/*!40000 ALTER TABLE `fields` DISABLE KEYS */;
INSERT INTO `fields` VALUES (1,'preconds','custom_preconds',1,'Preconditions','The preconditions of this test case. Reference other test cases with [C#] (e.g. [C17]).',3,2,1,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false,\"default_value\":\"\",\"format\":\"markdown\",\"rows\":\"7\"},\"id\":\"4be1344d55d11\"}]',0,1,1,0,0),(2,'steps','custom_steps',1,'Steps','The required steps to execute the test case.',3,2,2,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false,\"default_value\":\"\",\"format\":\"markdown\",\"rows\":\"7\"},\"id\":\"4be97c65ea2fd\"}]',0,1,1,0,0),(3,'expected','custom_expected',1,'Expected Result','The expected result after executing the test case.',3,2,3,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false,\"default_value\":\"\",\"format\":\"markdown\",\"rows\":\"7\"},\"id\":\"4be1345cafd07\"}]',0,1,1,0,0),(4,'dc330d77','estimate',1,'Estimate',NULL,1,1,1,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false},\"id\":\"4be97c65ea2fd\"}]',0,1,1,1,1),(5,'ddfe71c8','milestone_id',1,'Milestone',NULL,9,1,2,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false},\"id\":\"4be97c65ea2fd\"}]',0,0,1,1,1),(6,'c4bd4336','refs',1,'References',NULL,1,1,3,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false},\"id\":\"4be97c65ea2fd\"}]',0,1,1,1,1),(7,'d4d1e651','version',2,'Version',NULL,1,4,2,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false},\"id\":\"4be97c65ea2fd\"}]',0,1,1,1,1),(8,'e7c13ac2','elapsed',2,'Elapsed',NULL,1,4,3,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false},\"id\":\"4be97c65ea2fd\"}]',0,1,1,1,1),(9,'a6637b4f','defects',2,'Defects',NULL,1,4,4,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false},\"id\":\"4be97c65ea2fd\"}]',0,1,1,1,1),(10,'steps_separated','custom_steps_separated',1,'Steps',NULL,10,2,4,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false,\"format\":\"markdown\",\"has_expected\":true,\"rows\":\"5\"},\"id\":\"4be97c65ea2fd\"}]',0,1,1,0,0),(11,'step_results','custom_step_results',2,'Steps',NULL,11,3,1,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false,\"format\":\"markdown\",\"has_expected\":true,\"has_actual\":true,\"rows\":\"5\"},\"id\":\"4be97c65ea2fd\"}]',0,1,1,0,0),(12,'mission','custom_mission',1,'Mission','A high-level overview of what to test and which areas to cover, usually just 1-2 sentences.',3,2,5,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false,\"default_value\":\"\",\"format\":\"markdown\",\"rows\":\"7\"},\"id\":\"4be1345cafd07\"}]',0,1,1,0,0),(13,'goals','custom_goals',1,'Goals','A detailed list of goals to cover as part of the exploratory sessions.',3,2,6,'[{\"context\":{\"is_global\":true,\"project_ids\":null},\"options\":{\"is_required\":false,\"default_value\":\"\",\"format\":\"markdown\",\"rows\":\"7\"},\"id\":\"4be1345cafd07\"}]',0,1,1,0,0),(14,'automation_type','custom_automation_type',1,'Automation Type',NULL,6,1,5,'[{\"context\":{\"is_global\":true,\"project_ids\":[]},\"options\":{\"is_required\":false,\"default_value\":\"0\",\"items\":\"0, None\\n1, Ranorex\"},\"id\":\"7a34a519-f458-40bb-af43-ed63baf874ee\"}]',0,1,1,0,1);
/*!40000 ALTER TABLE `fields` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_users`
--

DROP TABLE IF EXISTS `group_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_users` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `ix_group_users_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_users`
--

LOCK TABLES `group_users` WRITE;
/*!40000 ALTER TABLE `group_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `created_on` int(11) NOT NULL,
  `is_locked` tinyint(1) NOT NULL,
  `heartbeat` int(11) NOT NULL,
  `is_done` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_jobs_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recps`
--

DROP TABLE IF EXISTS `message_recps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recps` (
  `user_id` int(11) NOT NULL,
  `message_id` int(11) NOT NULL,
  PRIMARY KEY (`message_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recps`
--

LOCK TABLES `message_recps` WRITE;
/*!40000 ALTER TABLE `message_recps` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_recps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_on` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milestones`
--

DROP TABLE IF EXISTS `milestones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `milestones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `due_on` int(11) DEFAULT NULL,
  `completed_on` int(11) DEFAULT NULL,
  `is_completed` tinyint(1) NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `start_on` int(11) DEFAULT NULL,
  `started_on` int(11) DEFAULT NULL,
  `is_started` tinyint(1) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_milestones_project_id` (`project_id`),
  KEY `ix_milestones_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestones`
--

LOCK TABLES `milestones` WRITE;
/*!40000 ALTER TABLE `milestones` DISABLE KEYS */;
/*!40000 ALTER TABLE `milestones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferences`
--

DROP TABLE IF EXISTS `preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preferences` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `value` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_preferences_name` (`user_id`,`name`),
  KEY `ix_preferences_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferences`
--

LOCK TABLES `preferences` WRITE;
/*!40000 ALTER TABLE `preferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `priorities`
--

DROP TABLE IF EXISTS `priorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `priorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `priority` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `short_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `is_default` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priorities`
--

LOCK TABLES `priorities` WRITE;
/*!40000 ALTER TABLE `priorities` DISABLE KEYS */;
INSERT INTO `priorities` VALUES (1,1,'Low','Low',0,0),(2,2,'Medium','Medium',1,0),(3,3,'High','High',0,0),(4,4,'Critical','Critical',0,0);
/*!40000 ALTER TABLE `priorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_access`
--

DROP TABLE IF EXISTS `project_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_access` (
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `access` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_access`
--

LOCK TABLES `project_access` WRITE;
/*!40000 ALTER TABLE `project_access` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_favs`
--

DROP TABLE IF EXISTS `project_favs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_favs` (
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `created_on` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_favs`
--

LOCK TABLES `project_favs` WRITE;
/*!40000 ALTER TABLE `project_favs` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_favs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_groups`
--

DROP TABLE IF EXISTS `project_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_groups` (
  `project_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `access` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_groups`
--

LOCK TABLES `project_groups` WRITE;
/*!40000 ALTER TABLE `project_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_history`
--

DROP TABLE IF EXISTS `project_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `action` int(11) NOT NULL,
  `created_on` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `suite_id` int(11) DEFAULT NULL,
  `milestone_id` int(11) DEFAULT NULL,
  `run_id` int(11) DEFAULT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `plan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_project_history_project_order` (`project_id`,`created_on`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_history`
--

LOCK TABLES `project_history` WRITE;
/*!40000 ALTER TABLE `project_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `announcement` longtext COLLATE utf8_unicode_ci,
  `show_announcement` tinyint(1) NOT NULL,
  `defect_id_url` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `defect_add_url` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `default_access` int(11) NOT NULL,
  `default_role_id` int(11) DEFAULT NULL,
  `reference_id_url` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reference_add_url` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `defect_plugin` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `defect_config` longtext COLLATE utf8_unicode_ci,
  `is_completed` tinyint(1) NOT NULL,
  `completed_on` int(11) DEFAULT NULL,
  `defect_template` longtext COLLATE utf8_unicode_ci,
  `suite_mode` int(11) NOT NULL,
  `master_id` int(11) DEFAULT NULL,
  `reference_plugin` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reference_config` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refs`
--

DROP TABLE IF EXISTS `refs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reference_id` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `case_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_refs_reference_id` (`reference_id`),
  KEY `ix_refs_case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refs`
--

LOCK TABLES `refs` WRITE;
/*!40000 ALTER TABLE `refs` DISABLE KEYS */;
/*!40000 ALTER TABLE `refs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_jobs`
--

DROP TABLE IF EXISTS `report_jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plugin` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_on` int(11) NOT NULL,
  `executed_on` int(11) DEFAULT NULL,
  `system_options` longtext COLLATE utf8_unicode_ci,
  `custom_options` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `ix_report_jobs_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_jobs`
--

LOCK TABLES `report_jobs` WRITE;
/*!40000 ALTER TABLE `report_jobs` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plugin` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `access` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_on` int(11) NOT NULL,
  `executed_on` int(11) DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL,
  `dir` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `formats` longtext COLLATE utf8_unicode_ci NOT NULL,
  `system_options` longtext COLLATE utf8_unicode_ci,
  `custom_options` longtext COLLATE utf8_unicode_ci,
  `status` int(11) NOT NULL,
  `status_message` longtext COLLATE utf8_unicode_ci,
  `status_trace` longtext COLLATE utf8_unicode_ci,
  `is_locked` tinyint(1) NOT NULL,
  `heartbeat` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_reports_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `permissions` int(11) NOT NULL,
  `is_default` int(11) NOT NULL,
  `display_order` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Lead',262143,1,18),(2,'Designer',258636,0,10),(3,'Tester',258624,0,8),(4,'Read-only',0,0,0);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `runs`
--

DROP TABLE IF EXISTS `runs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `runs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suite_id` int(11) DEFAULT NULL,
  `milestone_id` int(11) DEFAULT NULL,
  `created_on` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `is_completed` tinyint(1) NOT NULL,
  `completed_on` int(11) DEFAULT NULL,
  `include_all` tinyint(1) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `passed_count` int(11) NOT NULL DEFAULT '0',
  `retest_count` int(11) NOT NULL DEFAULT '0',
  `failed_count` int(11) NOT NULL DEFAULT '0',
  `untested_count` int(11) NOT NULL DEFAULT '0',
  `assignedto_id` int(11) DEFAULT NULL,
  `is_plan` tinyint(1) NOT NULL DEFAULT '0',
  `plan_id` int(11) DEFAULT NULL,
  `entry_id` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `entries` longtext COLLATE utf8_unicode_ci,
  `config` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `config_ids` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `entry_index` int(11) DEFAULT NULL,
  `blocked_count` int(11) NOT NULL DEFAULT '0',
  `is_editable` tinyint(1) NOT NULL,
  `content_id` int(11) DEFAULT NULL,
  `custom_status1_count` int(11) NOT NULL DEFAULT '0',
  `custom_status2_count` int(11) NOT NULL DEFAULT '0',
  `custom_status3_count` int(11) NOT NULL DEFAULT '0',
  `custom_status4_count` int(11) NOT NULL DEFAULT '0',
  `custom_status5_count` int(11) NOT NULL DEFAULT '0',
  `custom_status6_count` int(11) NOT NULL DEFAULT '0',
  `custom_status7_count` int(11) NOT NULL DEFAULT '0',
  `updated_by` int(11) NOT NULL,
  `updated_on` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_runs_project_id` (`project_id`),
  KEY `ix_runs_plan_id` (`plan_id`),
  KEY `ix_runs_milestone_id` (`milestone_id`),
  KEY `ix_runs_suite_id` (`suite_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `runs`
--

LOCK TABLES `runs` WRITE;
/*!40000 ALTER TABLE `runs` DISABLE KEYS */;
/*!40000 ALTER TABLE `runs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sections`
--

DROP TABLE IF EXISTS `sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suite_id` int(11) DEFAULT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `display_order` int(11) NOT NULL,
  `is_copy` tinyint(1) NOT NULL,
  `copyof_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `depth` int(11) NOT NULL DEFAULT '0',
  `description` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `ix_sections_suite_id` (`suite_id`),
  KEY `ix_sections_copyof_id` (`copyof_id`),
  KEY `ix_sections_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sections`
--

LOCK TABLES `sections` WRITE;
/*!40000 ALTER TABLE `sections` DISABLE KEYS */;
/*!40000 ALTER TABLE `sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessions` (
  `session_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `ip_address` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `user_agent` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `last_activity` int(10) NOT NULL,
  `user_data` longtext COLLATE utf8_unicode_ci,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_sessions_session_id` (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
INSERT INTO `sessions` VALUES ('e67f3bfe-2358-4dc0-a0db-bbbb283d8b50','','',1546206313,'{\"user_id\":1,\"last_login\":1546206313,\"rememberme\":true,\"csrf\":\"fgjV8brgoUz5ucA2k3uo\"}',1);
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `value` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_settings_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,'session_policy','0'),(2,'session_absolute_policy','0'),(3,'session_remember_me_disabled','0'),(4,'is_fallback_enabled','0'),(5,'create_account_on_first_login','0'),(6,'login_type','local'),(7,'current_sso_integration','none'),(8,'database_version','189'),(9,'installation_name','TestRail QA'),(10,'installation_url','http://localhost/'),(11,'attachment_dir','/var/www/testrail/attachments'),(12,'report_dir','/var/www/testrail/reports'),(13,'default_language','en'),(14,'default_locale','en-us'),(15,'default_timezone',NULL),(16,'email_server',NULL),(17,'email_ssl',NULL),(18,'email_from',NULL),(19,'email_user','testrail@testrail.testrail'),(20,'email_pass','testrail'),(21,'email_notifications',NULL),(22,'license_key','jGoxrctxLleoWDiS9ekkKqjds5KgRH5iEJaHTSzto5LcZcvJGIlBUvxmGf3M\r\nn8gXOlqMFerHVpS2QMTbJzECjZuNoTvWTCJlfbA/7oumRc2juuLOJManLNE7\r\n3jI0cgURCstkNUL/dmfVojiIrlDw4+57g9N8+6vSmFurdmz4BK1asEdxnlSw\r\nVoW3LIcGu5ErJ5lE4UDJm1IGDMx/nQGy3WbnRokYb0ygwLOVYaBA86EU8Bas\r\nipoC4hU+Nign68z0');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sso_settings`
--

DROP TABLE IF EXISTS `sso_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sso_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sso_integration_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `testrail_entity_id` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sso_url` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idp_sso_url` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idp_issuer_url` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ssl_certificate` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_sso_integration_name` (`sso_integration_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sso_settings`
--

LOCK TABLES `sso_settings` WRITE;
/*!40000 ALTER TABLE `sso_settings` DISABLE KEYS */;
INSERT INTO `sso_settings` VALUES (1,'saml',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sso_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `system_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `label` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `color_dark` int(11) NOT NULL,
  `color_medium` int(11) NOT NULL,
  `color_bright` int(11) NOT NULL,
  `display_order` int(11) NOT NULL,
  `is_system` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `is_untested` tinyint(1) NOT NULL,
  `is_final` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_statuses_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (1,'passed','passed','Passed',6667107,9820525,12709313,1,1,1,0,1),(2,'blocked','blocked','Blocked',9474192,13684944,14737632,2,1,1,0,1),(3,'untested','untested','Untested',11579568,15395562,15790320,3,1,1,1,0),(4,'retest','retest','Retest',13026868,15593088,16448182,4,1,1,0,0),(5,'failed','failed','Failed',14250867,15829135,16631751,5,1,1,0,1),(6,'custom_status1','custom_status1','Unnamed 1',0,10526880,13684944,6,0,0,0,0),(7,'custom_status2','custom_status2','Unnamed 2',0,10526880,13684944,7,0,0,0,0),(8,'custom_status3','custom_status3','Unnamed 3',0,10526880,13684944,8,0,0,0,0),(9,'custom_status4','custom_status4','Unnamed 4',0,10526880,13684944,9,0,0,0,0),(10,'custom_status5','custom_status5','Unnamed 5',0,10526880,13684944,10,0,0,0,0),(11,'custom_status6','custom_status6','Unnamed 6',0,10526880,13684944,11,0,0,0,0),(12,'custom_status7','custom_status7','Unnamed 7',0,10526880,13684944,12,0,0,0,0);
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `is_subscribed` tinyint(1) NOT NULL,
  `test_id` int(11) NOT NULL,
  `run_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_subscriptions_run_test` (`run_id`,`test_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suites`
--

DROP TABLE IF EXISTS `suites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` int(11) NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `created_on` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `is_copy` tinyint(1) NOT NULL,
  `copyof_id` int(11) DEFAULT NULL,
  `is_master` tinyint(1) NOT NULL,
  `is_baseline` tinyint(1) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_completed` tinyint(1) NOT NULL,
  `completed_on` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_suites_project_id` (`project_id`),
  KEY `ix_suites_copyof_id` (`copyof_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suites`
--

LOCK TABLES `suites` WRITE;
/*!40000 ALTER TABLE `suites` DISABLE KEYS */;
/*!40000 ALTER TABLE `suites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` int(11) NOT NULL,
  `is_locked` tinyint(1) NOT NULL,
  `heartbeat` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,0,0);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template_projects`
--

DROP TABLE IF EXISTS `template_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template_projects` (
  `template_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`template_id`,`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template_projects`
--

LOCK TABLES `template_projects` WRITE;
/*!40000 ALTER TABLE `template_projects` DISABLE KEYS */;
/*!40000 ALTER TABLE `template_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `templates`
--

DROP TABLE IF EXISTS `templates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `templates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `is_default` tinyint(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `include_all` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `templates`
--

LOCK TABLES `templates` WRITE;
/*!40000 ALTER TABLE `templates` DISABLE KEYS */;
INSERT INTO `templates` VALUES (1,'Test Case (Text)',1,0,1),(2,'Test Case (Steps)',0,0,1),(3,'Exploratory Session',0,0,1);
/*!40000 ALTER TABLE `templates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_activities`
--

DROP TABLE IF EXISTS `test_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_activities` (
  `date` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `run_id` int(11) NOT NULL,
  `passed_count` int(11) NOT NULL,
  `retest_count` int(11) NOT NULL,
  `failed_count` int(11) NOT NULL,
  `untested_count` int(11) NOT NULL,
  `blocked_count` int(11) NOT NULL,
  `custom_status1_count` int(11) NOT NULL,
  `custom_status2_count` int(11) NOT NULL,
  `custom_status3_count` int(11) NOT NULL,
  `custom_status4_count` int(11) NOT NULL,
  `custom_status5_count` int(11) NOT NULL,
  `custom_status6_count` int(11) NOT NULL,
  `custom_status7_count` int(11) NOT NULL,
  PRIMARY KEY (`date`,`project_id`,`run_id`),
  KEY `ix_test_activities_run_id` (`run_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_activities`
--

LOCK TABLES `test_activities` WRITE;
/*!40000 ALTER TABLE `test_activities` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_assocs`
--

DROP TABLE IF EXISTS `test_assocs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_assocs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_change_id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_test_assocs_test_change_id` (`test_change_id`),
  KEY `ix_test_assocs_test_id` (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_assocs`
--

LOCK TABLES `test_assocs` WRITE;
/*!40000 ALTER TABLE `test_assocs` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_assocs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_changes`
--

DROP TABLE IF EXISTS `test_changes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status_id` int(11) DEFAULT NULL,
  `comment` longtext COLLATE utf8_unicode_ci,
  `version` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `elapsed` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `defects` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_on` int(11) NOT NULL,
  `assignedto_id` int(11) DEFAULT NULL,
  `unassigned` tinyint(1) NOT NULL,
  `project_id` int(11) NOT NULL,
  `run_id` int(11) NOT NULL,
  `is_selected` tinyint(1) NOT NULL,
  `caching` int(11) NOT NULL,
  `custom_step_results` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `ix_test_changes_test_id` (`test_id`),
  KEY `ix_test_changes_project_order` (`project_id`,`is_selected`,`created_on`),
  KEY `ix_test_changes_run_order` (`run_id`,`is_selected`,`created_on`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_changes`
--

LOCK TABLES `test_changes` WRITE;
/*!40000 ALTER TABLE `test_changes` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_changes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_progress`
--

DROP TABLE IF EXISTS `test_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_progress` (
  `date` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `run_id` int(11) NOT NULL,
  `tests` int(11) NOT NULL,
  `forecasts` int(11) NOT NULL,
  PRIMARY KEY (`date`,`project_id`,`run_id`),
  KEY `ix_test_progress_run_id` (`run_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_progress`
--

LOCK TABLES `test_progress` WRITE;
/*!40000 ALTER TABLE `test_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_timers`
--

DROP TABLE IF EXISTS `test_timers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_timers` (
  `test_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `started_on` int(11) NOT NULL,
  `elapsed` int(11) NOT NULL,
  `is_paused` tinyint(1) NOT NULL,
  PRIMARY KEY (`test_id`,`user_id`),
  KEY `ix_test_timers_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_timers`
--

LOCK TABLES `test_timers` WRITE;
/*!40000 ALTER TABLE `test_timers` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_timers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tests`
--

DROP TABLE IF EXISTS `tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `run_id` int(11) NOT NULL,
  `case_id` int(11) DEFAULT NULL,
  `status_id` int(11) NOT NULL,
  `assignedto_id` int(11) DEFAULT NULL,
  `is_selected` tinyint(1) NOT NULL,
  `last_status_change_id` int(11) DEFAULT NULL,
  `is_completed` tinyint(1) NOT NULL,
  `in_progress` int(11) NOT NULL,
  `in_progress_by` int(11) DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  `tested_by` int(11) DEFAULT NULL,
  `tested_on` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_tests_run_id` (`run_id`),
  KEY `ix_tests_case_id` (`case_id`,`is_selected`),
  KEY `ix_tests_content_id` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
/*!40000 ALTER TABLE `tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uiscripts`
--

DROP TABLE IF EXISTS `uiscripts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uiscripts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `includes` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `excludes` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meta` longtext COLLATE utf8_unicode_ci,
  `js` longtext COLLATE utf8_unicode_ci,
  `css` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uiscripts`
--

LOCK TABLES `uiscripts` WRITE;
/*!40000 ALTER TABLE `uiscripts` DISABLE KEYS */;
/*!40000 ALTER TABLE `uiscripts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_columns`
--

DROP TABLE IF EXISTS `user_columns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_columns` (
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `area_id` int(11) NOT NULL,
  `columns` longtext COLLATE utf8_unicode_ci NOT NULL,
  `group_by` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `group_order` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`,`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_columns`
--

LOCK TABLES `user_columns` WRITE;
/*!40000 ALTER TABLE `user_columns` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_columns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_exports`
--

DROP TABLE IF EXISTS `user_exports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_exports` (
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `area_id` int(11) NOT NULL,
  `format` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `options` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`,`area_id`,`format`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_exports`
--

LOCK TABLES `user_exports` WRITE;
/*!40000 ALTER TABLE `user_exports` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_exports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_fields`
--

DROP TABLE IF EXISTS `user_fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_fields` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `system_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `label` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `type_id` int(11) NOT NULL,
  `fallback` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_fields_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_fields`
--

LOCK TABLES `user_fields` WRITE;
/*!40000 ALTER TABLE `user_fields` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_fields` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_filters`
--

DROP TABLE IF EXISTS `user_filters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_filters` (
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `area_id` int(11) NOT NULL,
  `filters` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`,`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_filters`
--

LOCK TABLES `user_filters` WRITE;
/*!40000 ALTER TABLE `user_filters` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_filters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_logins`
--

DROP TABLE IF EXISTS `user_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_logins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `created_on` int(11) NOT NULL,
  `updated_on` int(11) NOT NULL,
  `attempts` int(11) NOT NULL,
  `current_auth` varchar(10) COLLATE utf8_unicode_ci DEFAULT 'local',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_logins_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logins`
--

LOCK TABLES `user_logins` WRITE;
/*!40000 ALTER TABLE `user_logins` DISABLE KEYS */;
INSERT INTO `user_logins` VALUES (1,'testrail@testrail.testrail',1546206313,1546206313,0,'local');
/*!40000 ALTER TABLE `user_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_settings`
--

DROP TABLE IF EXISTS `user_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `value` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_settings_name` (`user_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_settings`
--

LOCK TABLES `user_settings` WRITE;
/*!40000 ALTER TABLE `user_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tokens`
--

DROP TABLE IF EXISTS `user_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_tokens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `series` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hash` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `created_on` int(11) NOT NULL,
  `expires_on` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_user_tokens_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tokens`
--

LOCK TABLES `user_tokens` WRITE;
/*!40000 ALTER TABLE `user_tokens` DISABLE KEYS */;
INSERT INTO `user_tokens` VALUES (1,1,2,NULL,'lCzqEP8vMONseoOLAYzx','1:$2y$10$KGZvoCcdty.xQC0ZPSJQ7.KzpdSZgSi.QWUMUzlQ/ycZa/sRoTfzu',1546206313,1548798313);
/*!40000 ALTER TABLE `user_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `salt` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `hash` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `rememberme` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `locale` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `language` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notifications` tinyint(1) NOT NULL,
  `csrf` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` int(11) NOT NULL,
  `login_token` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `timezone` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_token_until` int(11) DEFAULT NULL,
  `last_activity` int(11) DEFAULT NULL,
  `is_reset_password_forced` tinyint(1) NOT NULL DEFAULT '0',
  `data_processing_agreement` longtext COLLATE utf8_unicode_ci,
  `is_sso_enabled` int(11) DEFAULT '0',
  `is_fallback_password_set` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_users_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'testrail','testrail@testrail.testrail',1,'','1:$2y$10$YGKjb39C5jIedZxflt496.BVKjiyq4.15Hq0XTTjBIIAp8ZrwMsoi',1,'',NULL,NULL,0,'',1,NULL,NULL,NULL,1546206313,0,NULL,0,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-30 21:49:38
