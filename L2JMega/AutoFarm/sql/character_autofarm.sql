/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50740
Source Host           : localhost:3306
Source Database       : 1faris

Target Server Type    : MYSQL
Target Server Version : 50740
File Encoding         : 65001

Date: 2023-10-15 07:01:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for character_autofarm
-- ----------------------------
DROP TABLE IF EXISTS `character_autofarm`;
CREATE TABLE `character_autofarm` (
  `char_id` int(10) unsigned NOT NULL,
  `char_name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `auto_farm` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `radius` int(10) unsigned NOT NULL DEFAULT '1200',
  `short_cut` int(10) unsigned NOT NULL DEFAULT '9',
  `heal_percent` int(10) unsigned NOT NULL DEFAULT '30',
  `buff_protection` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `anti_ks_protection` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `summon_attack` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `summon_skill_percent` int(10) unsigned NOT NULL DEFAULT '0',
  `hp_potion_percent` int(10) unsigned NOT NULL DEFAULT '60',
  `mp_potion_percent` int(10) unsigned NOT NULL DEFAULT '60',
  PRIMARY KEY (`char_id`),
  KEY `char_name` (`char_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
