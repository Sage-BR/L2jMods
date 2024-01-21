### Eclipse Workspace Patch 1.0
#P aCis_datapack401
diff --git data/html/dressme/allskins.htm data/html/dressme/allskins.htm
new file mode 100644
index 0000000..f46a2bd
--- /dev/null
+++ data/html/dressme/allskins.htm
@@ -0,0 +1,20 @@
+<html>
+<body>
+<img src="L2UI.Squaregray" width="300" height="1">
+<table width=300 border=0 cellspacing=0 cellpadding=1 bgcolor=000000 height=15>
+<tr>
+<td width=36 align=center></td>
+<td width=120 align=left>Name</td>
+<td width=65 align=left>Actions</td>
+</tr>
+</table>
+<img src="L2UI.Squaregray" width="300" height="1">
+
+%showList%
+
+<center>
+<button value="Back" action="bypass -h custom_dressme_back" width=65 height=19 back="L2UI_ch3.smallbutton2_over" fore="L2UI_ch3.smallbutton2">
+</center>
+
+</body>
+</html>
\ No newline at end of file
diff --git data/html/dressme/index.htm data/html/dressme/index.htm
new file mode 100644
index 0000000..36f3b4a
--- /dev/null
+++ data/html/dressme/index.htm
@@ -0,0 +1,29 @@
+<html>
+<title>Skins Shop</title>
+<body>
+<br>
+<center>Skins:</center>
+
+<table width=300>
+<tr>
+<td align=center><button value="Armor buy/try" action="bypass -h dressme 1 skinlist armor" width=134 height=19 back="L2UI_ch3.BigButton3_over" fore="L2UI_ch3.BigButton3"></td>
+</tr>
+<tr>
+<td align=center><button value="Weapon buy/try" action="bypass -h dressme 1 skinlist weapon" width=134 height=19 back="L2UI_ch3.BigButton3_over" fore="L2UI_ch3.BigButton3"></td>
+</tr>
+<tr>
+<td align=center><button value="Hair buy/try" action="bypass -h dressme 1 skinlist hair" width=134 height=19 back="L2UI_ch3.BigButton3_over" fore="L2UI_ch3.BigButton3"></td>
+</tr>
+<tr>
+<td align=center><button value="Face buy/try" action="bypass -h dressme 1 skinlist face" width=134 height=19 back="L2UI_ch3.BigButton3_over" fore="L2UI_ch3.BigButton3"></td>
+</tr>
+<tr>
+<td align=center><button value="Shield buy/try" action="bypass -h dressme 1 skinlist shield" width=134 height=19 back="L2UI_ch3.BigButton3_over" fore="L2UI_ch3.BigButton3"></td>
+</tr>
+<tr>
+<td align=center><button value="My skins" action="bypass -h dressme 1 myskinlist" width=134 height=19 back="L2UI_ch3.BigButton3_over" fore="L2UI_ch3.BigButton3"></td>
+</tr>
+</table>
+
+</body>
+</html>
\ No newline at end of file
diff --git data/html/dressme/myskins.htm data/html/dressme/myskins.htm
new file mode 100644
index 0000000..381164c
--- /dev/null
+++ data/html/dressme/myskins.htm
@@ -0,0 +1,21 @@
+<html>
+<body>
+<img src="L2UI.Squaregray" width="300" height="1">
+<table border=0 cellspacing=0 cellpadding=2 bgcolor=000000 height=20>
+<tr>
+<td width=32 align=center></td>
+<td width=203 align=left>Name</td>
+<td width=65 align=left>Actions</td>
+</tr>
+</table>
+<img src="L2UI.Squaregray" width="300" height="1">
+
+%showList%
+
+<br>
+<br>
+<center>
+<button value="Back" action="bypass -h custom_dressme_back" width=65 height=19 back="L2UI_ch3.Btn1_normalOn" fore="L2UI_ch3.Btn1_normal">
+</center>
+</body>
+</html>
\ No newline at end of file
diff --git data/xml/dressme.xml data/xml/dressme.xml
new file mode 100644
index 0000000..7156269
--- /dev/null
+++ data/xml/dressme.xml
@@ -0,0 +1,22 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<list>
+	<skin type="armor"> <!-- Armors -->
+		<type id="1" name="Draconic Armor" chestId="6379" legsId="0" glovesId="6380" feetId="6381" priceId="57" priceCount="100"/>
+		<type id="2" name="Blue Wolf Leather Armor" chestId="2391" legsId="0" glovesId="0" feetId="0" priceId="57" priceCount="100"/>
+	</skin>
+	<skin type="weapon"> <!-- Weapons -->
+		<type id="1" name="Draconic Bow" weaponId="7575" priceId="57" priceCount="100"/>
+		<type id="2" name="Arcana Mace" weaponId="6608"  priceId="57" priceCount="100"/>
+		<type id="3" name="Keshanberk*Keshanberk" weaponId="5704" priceId="57" priceCount="100"/>
+	</skin>
+	<skin type="hair"> <!-- Hairs -->
+		<type id="1" name="Cat Ear" hairId="6843" priceId="57" priceCount="100"/>
+	</skin>
+	<skin type="face"> <!-- Faces -->
+		<type id="1" name="Party Mask" faceId="5808" priceId="57" priceCount="100"/>
+	</skin>
+	<skin type="shield"> <!-- Shields -->
+		<type id="1" name="Shield of Night" shieldId="2498" priceId="57" priceCount="100"/>
+		<type id="2" name="Imperial Shield" shieldId="6377" priceId="57" priceCount="100"/>
+	</skin>
+</list>
\ No newline at end of file
diff --git sql/characters_dressme_data.sql sql/characters_dressme_data.sql
new file mode 100644
index 0000000..306a503
--- /dev/null
+++ sql/characters_dressme_data.sql
@@ -0,0 +1,35 @@
+/*
+Navicat MySQL Data Transfer
+
+Source Server         : localhost
+Source Server Version : 50740
+Source Host           : localhost:3306
+Source Database       : l2j
+
+Target Server Type    : MYSQL
+Target Server Version : 50740
+File Encoding         : 65001
+
+Date: 2023-04-15 19:23:24
+*/
+
+SET FOREIGN_KEY_CHECKS=0;
+
+-- ----------------------------
+-- Table structure for characters_dressme_data
+-- ----------------------------
+DROP TABLE IF EXISTS `characters_dressme_data`;
+CREATE TABLE `characters_dressme_data` (
+  `obj_Id` decimal(11,0) NOT NULL DEFAULT '0',
+  `armor_skins` varchar(255) DEFAULT NULL,
+  `armor_skin_option` int(10) DEFAULT '0',
+  `weapon_skins` varchar(255) DEFAULT NULL,
+  `weapon_skin_option` int(10) DEFAULT '0',
+  `hair_skins` varchar(255) DEFAULT NULL,
+  `hair_skin_option` int(10) DEFAULT '0',
+  `face_skins` varchar(255) DEFAULT NULL,
+  `face_skin_option` int(10) DEFAULT '0',
+  `shield_skins` varchar(255) DEFAULT NULL,
+  `shield_skin_option` int(10) DEFAULT '0',
+  PRIMARY KEY (`obj_Id`)
+) ENGINE=InnoDB DEFAULT CHARSET=utf8;