### Eclipse Workspace Patch 1.0
#P Orion2.0
diff --git cfiles/game/config/main/L2jOrion.ini cfiles/game/config/main/L2jOrion.ini
index bf1867e..a005574 100644
--- cfiles/game/config/main/L2jOrion.ini
+++ cfiles/game/config/main/L2jOrion.ini
@@ -333,4 +333,17 @@
 NewPlayerEffect = False
 
 # It shows Castle's Lord clan crest in town nearby npc name
-ShowNpcCrest = False
\ No newline at end of file
+ShowNpcCrest = False
+
+# DressMe system.
+AllowDressMeSystem = True
+
+# DressMe Command
+DressMeCommand = dressme
+
+# Only for premium account
+AllowDressMeForPremiumOnly = True
+
+
+# Players won't see the skins in Olympiad
+AllowDressMeInOly = True
\ No newline at end of file
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
diff --git src/l2jorion/Config.java src/l2jorion/Config.java
index bbf70a0..ad4830a 100644
--- src/l2jorion/Config.java
+++ src/l2jorion/Config.java
@@ -2358,6 +2358,11 @@
 	public static String PM_TEXT2;
 	public static boolean NEW_PLAYER_EFFECT;
 	
+	public static boolean ALLOW_DRESS_ME_SYSTEM;
+	public static String DRESS_ME_COMMAND;
+	public static boolean ALLOW_DRESS_ME_FOR_PREMIUM;
+	public static boolean ALLOW_DRESS_ME_IN_OLY;
+	
 	public static void loadL2jOrionConfig()
 	{
 		final String L2jOrion = ConfigLoader.L2jOrion_CONFIG_FILE;
@@ -2381,6 +2386,11 @@
 				LIST_FAKE_ARMOR_ITEMS.add(Integer.parseInt(id));
 			}
 			
+			ALLOW_DRESS_ME_SYSTEM = Boolean.parseBoolean(L2jOrionSettings.getProperty("AllowDressMeSystem", "false"));
+			DRESS_ME_COMMAND = String.valueOf(L2jOrionSettings.getProperty("DressMeCommand", "dressme"));
+			ALLOW_DRESS_ME_FOR_PREMIUM = Boolean.parseBoolean(L2jOrionSettings.getProperty("AllowDressMeForPremiumOnly", "false"));
+			ALLOW_DRESS_ME_IN_OLY = Boolean.parseBoolean(L2jOrionSettings.getProperty("AllowDressMeInOly", "false"));
+			
 			FREE_TELEPORT_UNTIL = Integer.parseInt(L2jOrionSettings.getProperty("FreeTeleportUntil", "1"));
 			REMOVAL_AUGMENTATION_FREE = Boolean.parseBoolean(L2jOrionSettings.getProperty("RemovalAugmentationFree", "False"));
 			ALLOW_FREIGHT_AUGMENTED = Boolean.parseBoolean(L2jOrionSettings.getProperty("AllowFreightAugmentedItem", "False"));
@@ -4773,109 +4783,6 @@
 		}
 	}
 	
-	public static boolean ALLOW_DRESS_ME_SYSTEM;
-	public static boolean ALLOW_DRESS_ME_FOR_ITEM;
-	public static int DRESS_ME_ITEM_ID;
-	public static int DRESS_ME_ITEM_COUNT;
-	public static boolean ALLOW_DRESS_ME_FOR_PREMIUM;
-	public static Map<String, Integer> DRESS_ME_CHESTS = new HashMap<>();
-	public static Map<String, Integer> DRESS_ME_LEGS = new HashMap<>();
-	public static Map<String, Integer> DRESS_ME_BOOTS = new HashMap<>();
-	public static Map<String, Integer> DRESS_ME_GLOVES = new HashMap<>();
-	public static Map<String, Integer> DRESS_ME_WEAPONS = new HashMap<>();
-	
-	public static void loadDressMeConfig()
-	{
-		final PropertiesParser dressMe = new PropertiesParser(ConfigLoader.DRESSME_CONFIG_FILE);
-		
-		ALLOW_DRESS_ME_SYSTEM = dressMe.getBoolean("AllowDressMeSystem", false);
-		ALLOW_DRESS_ME_FOR_ITEM = dressMe.getBoolean("DressMeForItem", false);
-		DRESS_ME_ITEM_ID = dressMe.getInt("DressMeItemId", 57);
-		DRESS_ME_ITEM_COUNT = dressMe.getInt("DressMeItemCount", 1);
-		ALLOW_DRESS_ME_FOR_PREMIUM = dressMe.getBoolean("AllowDressMeForPremiumOnly", false);
-		
-		String temp = dressMe.getString("DressMeChests", "");
-		String[] temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_CHESTS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeLegs", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_LEGS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeBoots", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_BOOTS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeGloves", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_GLOVES.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeWeapons", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_WEAPONS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeChests", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_CHESTS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeLegs", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_LEGS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeBoots", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_BOOTS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeGloves", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_GLOVES.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-		temp = dressMe.getString("DressMeWeapons", "");
-		temp2 = temp.split(";");
-		for (String s : temp2)
-		{
-			String[] t = s.split(",");
-			DRESS_ME_WEAPONS.put(t[0], Integer.parseInt(t[1]));
-		}
-		
-	}
-	
 	/** Enumeration for type of ID Factory */
 	public static enum IdFactoryType
 	{
@@ -4906,7 +4813,6 @@
 			
 			loadUserConfig();
 			loadSellBuffConfig();
-			loadDressMeConfig();
 			
 			// Load network
 			loadServerConfig();
diff --git src/l2jorion/game/GameServer.java src/l2jorion/game/GameServer.java
index 214bbfe..94a2df6 100644
--- src/l2jorion/game/GameServer.java
+++ src/l2jorion/game/GameServer.java
@@ -102,6 +102,7 @@
 import l2jorion.game.datatables.sql.TeleportLocationTable;
 import l2jorion.game.datatables.xml.AugmentScrollData;
 import l2jorion.game.datatables.xml.AugmentationData;
+import l2jorion.game.datatables.xml.DressMeData;
 import l2jorion.game.datatables.xml.ExperienceData;
 import l2jorion.game.geo.GeoData;
 import l2jorion.game.geo.pathfinding.PathFinding;
@@ -547,6 +548,14 @@
 			PowerPack.getInstance();
 		}
 		
+		L2Item.LoadAllIcons();
+		
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			Util.printSection("Dress Me / Skins");
+			DressMeData.getInstance();
+		}
+		
 		EventManager.getInstance().startEventRegistration();
 		
 		if (EventManager.TVT_EVENT_ENABLED || EventManager.CTF_EVENT_ENABLED || EventManager.DM_EVENT_ENABLED)
@@ -688,4 +697,5 @@
 	{
 		return _deadDetectThread;
 	}
+	
 }
\ No newline at end of file
diff --git src/l2jorion/game/datatables/xml/DressMeData.java src/l2jorion/game/datatables/xml/DressMeData.java
new file mode 100644
index 0000000..cda2e91
--- /dev/null
+++ src/l2jorion/game/datatables/xml/DressMeData.java
@@ -0,0 +1,215 @@
+package l2jorion.game.datatables.xml;
+
+import java.util.HashMap;
+import java.util.Map;
+
+import org.w3c.dom.Document;
+import org.w3c.dom.NamedNodeMap;
+import org.w3c.dom.Node;
+
+import l2jorion.game.model.base.SkinPackage;
+import l2jorion.game.templates.StatsSet;
+import l2jorion.logger.Logger;
+import l2jorion.logger.LoggerFactory;
+import l2jorion.util.xml.IXmlReader;
+
+public class DressMeData implements IXmlReader
+{
+	private static final Logger LOG = LoggerFactory.getLogger(DressMeData.class);
+	
+	private final static Map<Integer, SkinPackage> _armorSkins = new HashMap<>();
+	private final static Map<Integer, SkinPackage> _weaponSkins = new HashMap<>();
+	private final static Map<Integer, SkinPackage> _hairSkins = new HashMap<>();
+	private final static Map<Integer, SkinPackage> _faceSkins = new HashMap<>();
+	private final static Map<Integer, SkinPackage> _shieldSkins = new HashMap<>();
+	
+	public DressMeData()
+	{
+		load();
+	}
+	
+	public void reload()
+	{
+		_armorSkins.clear();
+		_weaponSkins.clear();
+		_hairSkins.clear();
+		_faceSkins.clear();
+		_shieldSkins.clear();
+		
+		load();
+	}
+	
+	@Override
+	public void load()
+	{
+		parseDatapackFile("./data/xml/dressme.xml");
+		LOG.info(getClass().getSimpleName() + ": Loaded " + _armorSkins.size() + " armor skins");
+		LOG.info(getClass().getSimpleName() + ": Loaded " + _weaponSkins.size() + " weapon skins");
+		LOG.info(getClass().getSimpleName() + ": Loaded " + _hairSkins.size() + " hair skins");
+		LOG.info(getClass().getSimpleName() + ": Loaded " + _faceSkins.size() + " face skins");
+		LOG.info(getClass().getSimpleName() + ": Loaded " + _shieldSkins.size() + " shield skins");
+	}
+	
+	@Override
+	public void parseDocument(Document doc)
+	{
+		for (Node list = doc.getFirstChild(); list != null; list = list.getNextSibling())
+		{
+			if ("list".equalsIgnoreCase(list.getNodeName()))
+			{
+				for (Node skin = list.getFirstChild(); skin != null; skin = skin.getNextSibling())
+				{
+					if ("skin".equalsIgnoreCase(skin.getNodeName()))
+					{
+						final NamedNodeMap attrs = skin.getAttributes();
+						
+						String type = parseString(attrs, "type");
+						
+						final StatsSet set = new StatsSet();
+						
+						for (Node typeN = skin.getFirstChild(); typeN != null; typeN = typeN.getNextSibling())
+						{
+							if ("type".equalsIgnoreCase(typeN.getNodeName()))
+							{
+								final NamedNodeMap attrs2 = typeN.getAttributes();
+								
+								int id = parseInteger(attrs2, "id");
+								String name = parseString(attrs2, "name");
+								int weaponId = parseInteger(attrs2, "weaponId", 0);
+								int shieldId = parseInteger(attrs2, "shieldId", 0);
+								int chestId = parseInteger(attrs2, "chestId", 0);
+								int hairId = parseInteger(attrs2, "hairId", 0);
+								int faceId = parseInteger(attrs2, "faceId", 0);
+								int legsId = parseInteger(attrs2, "legsId", 0);
+								int glovesId = parseInteger(attrs2, "glovesId", 0);
+								int feetId = parseInteger(attrs2, "feetId", 0);
+								int priceId = parseInteger(attrs2, "priceId", 0);
+								int priceCount = parseInteger(attrs2, "priceCount", 0);
+								
+								set.set("type", type);
+								
+								set.set("id", id);
+								set.set("name", name);
+								set.set("weaponId", weaponId);
+								set.set("shieldId", shieldId);
+								set.set("chestId", chestId);
+								set.set("hairId", hairId);
+								set.set("faceId", faceId);
+								set.set("legsId", legsId);
+								set.set("glovesId", glovesId);
+								set.set("feetId", feetId);
+								set.set("priceId", priceId);
+								set.set("priceCount", priceCount);
+								
+								switch (type.toLowerCase())
+								{
+									case "armor":
+										_armorSkins.put(id, new SkinPackage(set));
+										break;
+									case "weapon":
+										_weaponSkins.put(id, new SkinPackage(set));
+										break;
+									case "hair":
+										_hairSkins.put(id, new SkinPackage(set));
+										break;
+									case "face":
+										_faceSkins.put(id, new SkinPackage(set));
+										break;
+									case "shield":
+										_shieldSkins.put(id, new SkinPackage(set));
+										break;
+								}
+							}
+						}
+					}
+				}
+			}
+		}
+		
+	}
+	
+	public SkinPackage getArmorSkinsPackage(int id)
+	{
+		if (!_armorSkins.containsKey(id))
+		{
+			return null;
+		}
+		
+		return _armorSkins.get(id);
+	}
+	
+	public Map<Integer, SkinPackage> getArmorSkinOptions()
+	{
+		return _armorSkins;
+	}
+	
+	public SkinPackage getWeaponSkinsPackage(int id)
+	{
+		if (!_weaponSkins.containsKey(id))
+		{
+			return null;
+		}
+		
+		return _weaponSkins.get(id);
+	}
+	
+	public Map<Integer, SkinPackage> getWeaponSkinOptions()
+	{
+		return _weaponSkins;
+	}
+	
+	public SkinPackage getHairSkinsPackage(int id)
+	{
+		if (!_hairSkins.containsKey(id))
+		{
+			return null;
+		}
+		
+		return _hairSkins.get(id);
+	}
+	
+	public Map<Integer, SkinPackage> getHairSkinOptions()
+	{
+		return _hairSkins;
+	}
+	
+	public SkinPackage getFaceSkinsPackage(int id)
+	{
+		if (!_faceSkins.containsKey(id))
+		{
+			return null;
+		}
+		
+		return _faceSkins.get(id);
+	}
+	
+	public Map<Integer, SkinPackage> getFaceSkinOptions()
+	{
+		return _faceSkins;
+	}
+	
+	public SkinPackage getShieldSkinsPackage(int id)
+	{
+		if (!_shieldSkins.containsKey(id))
+		{
+			return null;
+		}
+		
+		return _shieldSkins.get(id);
+	}
+	
+	public Map<Integer, SkinPackage> getShieldSkinOptions()
+	{
+		return _shieldSkins;
+	}
+	
+	public static DressMeData getInstance()
+	{
+		return SingletonHolder._instance;
+	}
+	
+	private static class SingletonHolder
+	{
+		protected static final DressMeData _instance = new DressMeData();
+	}
+}
\ No newline at end of file
diff --git src/l2jorion/game/handler/BypassHandler.java src/l2jorion/game/handler/BypassHandler.java
new file mode 100644
index 0000000..899a49b
--- /dev/null
+++ src/l2jorion/game/handler/BypassHandler.java
@@ -0,0 +1,62 @@
+package l2jorion.game.handler;
+
+import java.util.HashMap;
+import java.util.Map;
+import java.util.logging.Logger;
+
+import l2jorion.Config;
+
+public class BypassHandler
+{
+	private static Logger _log = Logger.getLogger(BypassHandler.class.getName());
+	private final Map<Integer, IBypassHandler> _datatable = new HashMap<>();
+	
+	public static BypassHandler getInstance()
+	{
+		return SingletonHolder._instance;
+	}
+	
+	private BypassHandler()
+	{
+		
+	}
+	
+	public void registerBypassHandler(IBypassHandler handler)
+	{
+		String[] ids = handler.getBypassHandlersList();
+		for (int i = 0; i < ids.length; i++)
+		{
+			if (Config.DEBUG)
+			{
+				_log.fine("Adding handler for command " + ids[i]);
+			}
+			_datatable.put(ids[i].hashCode(), handler);
+		}
+	}
+	
+	public IBypassHandler getBypassHandler(String bypass)
+	{
+		String command = bypass;
+		
+		if (bypass.indexOf(" ") != -1)
+		{
+			command = bypass.substring(0, bypass.indexOf(" "));
+		}
+		
+		if (Config.DEBUG)
+		{
+			_log.fine("getting handler for command: " + command + " -> " + (_datatable.get(command.hashCode()) != null));
+		}
+		return _datatable.get(command.hashCode());
+	}
+	
+	public int size()
+	{
+		return _datatable.size();
+	}
+	
+	private static class SingletonHolder
+	{
+		protected static final BypassHandler _instance = new BypassHandler();
+	}
+}
diff --git src/l2jorion/game/handler/IBypassHandler.java src/l2jorion/game/handler/IBypassHandler.java
new file mode 100644
index 0000000..e75d83a
--- /dev/null
+++ src/l2jorion/game/handler/IBypassHandler.java
@@ -0,0 +1,10 @@
+package l2jorion.game.handler;
+
+import l2jorion.game.model.actor.instance.L2PcInstance;
+
+public interface IBypassHandler
+{
+	public boolean handleBypass(String bypass, L2PcInstance activeChar);
+	
+	public String[] getBypassHandlersList();
+}
diff --git src/l2jorion/game/handler/voice/DressMe.java src/l2jorion/game/handler/voice/DressMe.java
index d057559..474d423 100644
--- src/l2jorion/game/handler/voice/DressMe.java
+++ src/l2jorion/game/handler/voice/DressMe.java
@@ -1,71 +1,116 @@
-/*
- * This program is free software: you can redistribute it and/or modify it under
- * the terms of the GNU General Public License as published by the Free Software
- * Foundation, either version 3 of the License, or (at your option) any later
- * version.
- *
- * This program is distributed in the hope that it will be useful, but WITHOUT
- * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
- * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
- * details.
- *
- * You should have received a copy of the GNU General Public License along with
- * this program. If not, see <http://www.gnu.org/licenses/>.
- */
+
 package l2jorion.game.handler.voice;
 
-import l2jorion.game.datatables.sql.ItemTable;
+import java.text.SimpleDateFormat;
+import java.util.Date;
+
+import l2jorion.Config;
+import l2jorion.game.cache.HtmCache;
+import l2jorion.game.community.manager.BaseBBSManager;
+import l2jorion.game.handler.ICustomByPassHandler;
 import l2jorion.game.handler.IVoicedCommandHandler;
 import l2jorion.game.model.actor.instance.L2PcInstance;
+import l2jorion.game.network.serverpackets.ExShowScreenMessage;
 import l2jorion.game.network.serverpackets.NpcHtmlMessage;
+import l2jorion.game.network.serverpackets.PlaySound;
 
-public class DressMe implements IVoicedCommandHandler
+public class DressMe implements IVoicedCommandHandler, ICustomByPassHandler
 {
-	private static final String[] VOICED_COMMANDS =
+	private static String[] _voicedCommands =
 	{
-		"dressme"
+		Config.DRESS_ME_COMMAND
 	};
 	
 	@Override
-	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String parameter)
+	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String target)
 	{
-		if (command.equals("dressme"))
+		if (Config.ALLOW_DRESS_ME_FOR_PREMIUM && activeChar.getPremiumService() == 0)
 		{
-			sendMainWindow(activeChar);
+			activeChar.sendMessage("You're not The Premium account.");
+			activeChar.sendPacket(new ExShowScreenMessage("You're not The Premium account.", 1000, 2, false));
+			activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+			return false;
 		}
 		
+		if (command.startsWith("testd"))
+		{
+			showHtm2(activeChar);
+		}
+		
+		if (command.startsWith(Config.DRESS_ME_COMMAND))
+		{
+			showHtm(activeChar);
+		}
 		return true;
 	}
 	
-	public static void sendMainWindow(L2PcInstance activeChar)
+	private static void showHtm(L2PcInstance player)
 	{
-		NpcHtmlMessage htm = new NpcHtmlMessage(0);
-		htm.setFile("./data/html/custom/dressme/main.htm");
-		htm.replace("%enabled%", activeChar.isDressMeEnabled() ? "enabled" : "disabled");
+		NpcHtmlMessage htm = new NpcHtmlMessage(1);
+		String text = HtmCache.getInstance().getHtm("data/html/dressme/index.htm");
 		
-		if (activeChar.getDressMeData() == null)
+		htm.setHtml(text);
+		
 		{
-			htm.replace("%chestinfo%", "You have no custom chest.");
-			htm.replace("%legsinfo%", "You have no custom legs.");
-			htm.replace("%bootsinfo%", "You have no custom boots.");
-			htm.replace("%glovesinfo%", "You have no custom gloves.");
-			htm.replace("%weapinfo%", "You have no custom weapon.");
-		}
-		else
-		{
-			htm.replace("%chestinfo%", activeChar.getDressMeData().getChestId() == 0 ? "You have no custom chest." : ItemTable.getInstance().getTemplate(activeChar.getDressMeData().getChestId()).getName());
-			htm.replace("%legsinfo%", activeChar.getDressMeData().getLegsId() == 0 ? "You have no custom legs." : ItemTable.getInstance().getTemplate(activeChar.getDressMeData().getLegsId()).getName());
-			htm.replace("%bootsinfo%", activeChar.getDressMeData().getBootsId() == 0 ? "You have no custom boots." : ItemTable.getInstance().getTemplate(activeChar.getDressMeData().getBootsId()).getName());
-			htm.replace("%glovesinfo%", activeChar.getDressMeData().getGlovesId() == 0 ? "You have no custom gloves." : ItemTable.getInstance().getTemplate(activeChar.getDressMeData().getGlovesId()).getName());
-			htm.replace("%weapinfo%", activeChar.getDressMeData().getWeapId() == 0 ? "You have no custom weapon." : ItemTable.getInstance().getTemplate(activeChar.getDressMeData().getWeapId()).getName());
+			htm.replace("%time%", sdf.format(new Date(System.currentTimeMillis())));
+			htm.replace("%dat%", (new SimpleDateFormat("dd/MM/yyyy")).format(new Date(System.currentTimeMillis())));
+			
 		}
 		
-		activeChar.sendPacket(htm);
+		player.sendPacket(htm);
 	}
 	
+	private static void showHtm2(L2PcInstance activeChar)
+	{
+		String content = HtmCache.getInstance().getHtm("data/html/CommunityBoard/Shop/1.htm");
+		content = content.replace("%CharName%", String.valueOf(activeChar.getName()));
+		if (content == null)
+		{
+			content = "<html><body><br><br><center>404 :File Not foud: 'data/html/CommunityBoard/shop/index.htm' </center></body></html>";
+		}
+		BaseBBSManager.separateAndSend(content, activeChar);
+		content = null;
+	}
+	
+	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
+	
 	@Override
 	public String[] getVoicedCommandList()
 	{
-		return VOICED_COMMANDS;
+		return _voicedCommands;
+	}
+	
+	@Override
+	public String[] getByPassCommands()
+	{
+		return new String[]
+		{
+			"dressme_back"
+		};
+	}
+	
+	private enum CommandEnum
+	{
+		dressme_back,
+	}
+	
+	@Override
+	public void handleCommand(String command, L2PcInstance player, String parameters)
+	{
+		CommandEnum comm = CommandEnum.valueOf(command);
+		
+		if (comm == null)
+		{
+			return;
+		}
+		
+		switch (comm)
+		{
+			case dressme_back:
+			{
+				showHtm(player);
+			}
+				break;
+		}
 	}
 }
\ No newline at end of file
diff --git src/l2jorion/game/model/actor/instance/L2PcInstance.java src/l2jorion/game/model/actor/instance/L2PcInstance.java
index 8d9b411..f6c3536 100644
--- src/l2jorion/game/model/actor/instance/L2PcInstance.java
+++ src/l2jorion/game/model/actor/instance/L2PcInstance.java
@@ -35,6 +35,7 @@
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
+import java.util.concurrent.CopyOnWriteArrayList;
 import java.util.concurrent.Future;
 import java.util.concurrent.ScheduledFuture;
 import java.util.concurrent.TimeUnit;
@@ -282,6 +283,24 @@
 	
 	private FastSet<String> Favorites = new FastSet<>();
 	
+	private int _armorSkinOption = 0;
+	private int _weaponSkinOption = 0;
+	private int _hairSkinOption = 0;
+	private int _faceSkinOption = 0;
+	private int _shieldSkinOption = 0;
+	
+	private boolean isTryingSkin = false;
+	private boolean isTryingSkinPremium = false;
+	private List<Integer> _armorSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _weaponSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _hairSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _faceSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _shieldSkins = new CopyOnWriteArrayList<>();
+	
+	private static final String INSERT_OR_UPDATE_CHARACTER_DRESSME_DATA = "INSERT INTO characters_dressme_data (obj_Id, armor_skins, armor_skin_option, weapon_skins, weapon_skin_option, hair_skins, hair_skin_option, face_skins, face_skin_option) VALUES (?,?,?,?,?,?,?,?,?) "
+		+ "ON DUPLICATE KEY UPDATE obj_Id=?, armor_skins=?, armor_skin_option=?, weapon_skins=?, weapon_skin_option=?, hair_skins=?, hair_skin_option=?, face_skins=?, face_skin_option=?, shield_skins=?, shield_skin_option=?";
+	private static final String RESTORE_CHARACTER_DRESSME_DATA = "SELECT obj_Id, armor_skins, armor_skin_option, weapon_skins, weapon_skin_option, hair_skins, hair_skin_option, face_skins, face_skin_option, shield_skins, shield_skin_option FROM characters_dressme_data WHERE obj_id=?";
+	
 	private static final String UPDATE_CHARACTER = "UPDATE characters SET level=?,maxHp=?,curHp=?,maxCp=?,curCp=?,maxMp=?,curMp=?,str=?,con=?,dex=?,_int=?,men=?,wit=?,face=?,hairStyle=?,hairColor=?,heading=?,x=?,y=?,z=?,exp=?,expBeforeDeath=?,sp=?,karma=?,pvpkills=?,pkkills=?,rec_have=?,rec_left=?,clanid=?,maxload=?,race=?,classid=?,deletetime=?,title=?,accesslevel=?,online=?,isin7sdungeon=?,clan_privs=?,wantspeace=?,base_class=?,onlinetime=?,punish_level=?,punish_timer=?,newbie=?,nobless=?,power_grade=?,subpledge=?,last_recom_date=?,lvl_joined_academy=?,apprentice=?,sponsor=?,varka_ketra_ally=?,clan_join_expiry_time=?,clan_create_expiry_time=?,char_name=?,death_penalty_level=?,pc_point=?,name_color=?,title_color=?,aio=?,aio_end=?,hitman_target=?,autoloot=?,autoloot_herbs=? WHERE obj_id=?";
 	private static final String RESTORE_CHARACTER = "SELECT account_name, obj_Id, char_name, level, maxHp, curHp, maxCp, curCp, maxMp, curMp, acc, crit, evasion, mAtk, mDef, mSpd, pAtk, pDef, pSpd, runSpd, walkSpd, str, con, dex, _int, men, wit, face, hairStyle, hairColor, sex, heading, x, y, z, movement_multiplier, attack_speed_multiplier, colRad, colHeight, exp, expBeforeDeath, sp, karma, pvpkills, pkkills, clanid, maxload, race, classid, deletetime, cancraft, title, rec_have, rec_left, accesslevel, online, char_slot, lastAccess, clan_privs, wantspeace, base_class, onlinetime, isin7sdungeon,punish_level,punish_timer,"
 		+ "newbie, nobless, power_grade, subpledge, last_recom_date, lvl_joined_academy, apprentice, sponsor, varka_ketra_ally,clan_join_expiry_time,clan_create_expiry_time,death_penalty_level,pc_point"
@@ -10096,6 +10115,7 @@
 			player.refreshOverloaded();
 			
 			player.restoreFriendList();
+			player.restoreDressMeData();
 		}
 		catch (Exception e)
 		{
@@ -10314,6 +10334,11 @@
 		storeCharBase();
 		storeCharSub();
 		
+		if (!isTryingSkin() || !isTryingSkinPremium())
+		{
+			storeDressMeData();
+		}
+		
 		// Dont store effect if the char was on Offline trade
 		if (!isStored())
 		{
@@ -21241,4 +21266,446 @@
 	{
 		return _achievement;
 	}
+	
+	@SuppressWarnings("resource")
+	private synchronized void storeDressMeData()
+	{
+		
+		Connection con = null;
+		try
+		{
+			con = L2DatabaseFactory.getInstance().getConnection();
+			PreparedStatement statement = con.prepareStatement(INSERT_OR_UPDATE_CHARACTER_DRESSME_DATA);
+			
+			statement.setInt(1, getObjectId());
+			if (_armorSkins.isEmpty())
+			{
+				statement.setString(2, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _armorSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(2, s);
+			}
+			statement.setInt(3, getArmorSkinOption());
+			
+			if (_weaponSkins.isEmpty())
+			{
+				statement.setString(4, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _weaponSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(4, s);
+			}
+			statement.setInt(5, getWeaponSkinOption());
+			
+			if (_hairSkins.isEmpty())
+			{
+				statement.setString(6, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _hairSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(6, s);
+			}
+			statement.setInt(7, getHairSkinOption());
+			
+			if (_faceSkins.isEmpty())
+			{
+				statement.setString(8, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _faceSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(8, s);
+			}
+			statement.setInt(9, getFaceSkinOption());
+			
+			if (_shieldSkins.isEmpty())
+			{
+				statement.setString(10, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _shieldSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(10, s);
+			}
+			statement.setInt(11, getShieldSkinOption());
+			
+			// second part
+			
+			statement.setInt(10, getObjectId());
+			if (_armorSkins.isEmpty())
+			{
+				statement.setString(11, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _armorSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(11, s);
+			}
+			statement.setInt(12, getArmorSkinOption());
+			
+			if (_weaponSkins.isEmpty())
+			{
+				statement.setString(13, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _weaponSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(13, s);
+			}
+			statement.setInt(14, getWeaponSkinOption());
+			
+			if (_hairSkins.isEmpty())
+			{
+				statement.setString(15, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _hairSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(15, s);
+			}
+			statement.setInt(16, getHairSkinOption());
+			
+			if (_faceSkins.isEmpty())
+			{
+				statement.setString(17, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _faceSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(17, s);
+			}
+			statement.setInt(18, getFaceSkinOption());
+			
+			if (_shieldSkins.isEmpty())
+			{
+				statement.setString(19, "");
+			}
+			else
+			{
+				String s = "";
+				for (int i : _shieldSkins)
+				{
+					s += String.valueOf(i) + ",";
+				}
+				statement.setString(19, s);
+			}
+			statement.setInt(20, getShieldSkinOption());
+			
+			statement.execute();
+			statement.close();
+		}
+		catch (Exception e)
+		{
+			
+			LOG.warn("Could not store storeDressMeData():");
+			e.printStackTrace();
+			
+		}
+		finally
+		{
+			
+		}
+	}
+	
+	@SuppressWarnings("resource")
+	private void restoreDressMeData()
+	{
+		
+		Connection con = null;
+		try
+		{
+			con = L2DatabaseFactory.getInstance().getConnection();
+			PreparedStatement statement = con.prepareStatement(RESTORE_CHARACTER_DRESSME_DATA);
+			
+			statement.setInt(1, getObjectId());
+			ResultSet rset = statement.executeQuery();
+			
+			while (rset.next())
+			{
+				String armorskinIds = rset.getString("armor_skins");
+				if (armorskinIds != null && armorskinIds.length() > 0)
+				{
+					for (String s : armorskinIds.split(","))
+					{
+						if (s == null)
+						{
+							continue;
+						}
+						buyArmorSkin(Integer.parseInt(s));
+					}
+				}
+				setArmorSkinOption(rset.getInt("armor_skin_option"));
+				
+				String weaponskinIds = rset.getString("weapon_skins");
+				if (weaponskinIds != null && weaponskinIds.length() > 0)
+				{
+					for (String s : weaponskinIds.split(","))
+					{
+						if (s == null)
+						{
+							continue;
+						}
+						buyWeaponSkin(Integer.parseInt(s));
+					}
+				}
+				setWeaponSkinOption(rset.getInt("weapon_skin_option"));
+				
+				String hairskinIds = rset.getString("hair_skins");
+				if (hairskinIds != null && hairskinIds.length() > 0)
+				{
+					for (String s : hairskinIds.split(","))
+					{
+						if (s == null)
+						{
+							continue;
+						}
+						buyHairSkin(Integer.parseInt(s));
+					}
+				}
+				setHairSkinOption(rset.getInt("hair_skin_option"));
+				
+				String faceskinIds = rset.getString("face_skins");
+				if (faceskinIds != null && faceskinIds.length() > 0)
+				{
+					for (String s : faceskinIds.split(","))
+					{
+						if (s == null)
+						{
+							continue;
+						}
+						buyFaceSkin(Integer.parseInt(s));
+					}
+				}
+				setFaceSkinOption(rset.getInt("face_skin_option"));
+				
+				String shieldkinIds = rset.getString("shield_skins");
+				if (shieldkinIds != null && shieldkinIds.length() > 0)
+				{
+					for (String s : shieldkinIds.split(","))
+					{
+						if (s == null)
+						{
+							continue;
+						}
+						buyShieldSkin(Integer.parseInt(s));
+					}
+				}
+				setShieldSkinOption(rset.getInt("shield_skin_option"));
+			}
+			
+			rset.close();
+			statement.close();
+		}
+		catch (Exception e)
+		{
+			LOG.warn("Could not restore char data:");
+			e.printStackTrace();
+		}
+		finally
+		{
+			
+		}
+	}
+	
+	// Dress Me
+	public boolean isTryingSkin()
+	{
+		return isTryingSkin;
+	}
+	
+	public void setIsTryingSkin(boolean b)
+	{
+		isTryingSkin = b;
+	}
+	
+	public boolean isTryingSkinPremium()
+	{
+		return isTryingSkinPremium;
+	}
+	
+	public void setIsTryingSkinPremium(boolean b)
+	{
+		isTryingSkinPremium = b;
+	}
+	
+	public boolean hasArmorSkin(int skin)
+	{
+		return _armorSkins.contains(skin);
+	}
+	
+	public boolean hasWeaponSkin(int skin)
+	{
+		return _weaponSkins.contains(skin);
+	}
+	
+	public boolean hasHairSkin(int skin)
+	{
+		return _hairSkins.contains(skin);
+	}
+	
+	public boolean hasFaceSkin(int skin)
+	{
+		return _faceSkins.contains(skin);
+	}
+	
+	public boolean hasShieldSkin(int skin)
+	{
+		return _shieldSkins.contains(skin);
+	}
+	
+	public boolean hasEquippedArmorSkin(String skin)
+	{
+		return String.valueOf(_armorSkinOption).contains(String.valueOf(skin));
+	}
+	
+	public boolean hasEquippedWeaponSkin(String skin)
+	{
+		return String.valueOf(_weaponSkinOption).contains(String.valueOf(skin));
+	}
+	
+	public boolean hasEquippedHairSkin(String skin)
+	{
+		return String.valueOf(_hairSkinOption).contains(String.valueOf(skin));
+	}
+	
+	public boolean hasEquippedFaceSkin(String skin)
+	{
+		return String.valueOf(_faceSkinOption).contains(String.valueOf(skin));
+	}
+	
+	public boolean hasEquippedShieldSkin(String skin)
+	{
+		return String.valueOf(_shieldSkinOption).contains(String.valueOf(skin));
+	}
+	
+	public void buyArmorSkin(int id)
+	{
+		if (!_armorSkins.contains(id))
+		{
+			_armorSkins.add(id);
+		}
+	}
+	
+	public void buyWeaponSkin(int id)
+	{
+		if (!_weaponSkins.contains(id))
+		{
+			_weaponSkins.add(id);
+		}
+	}
+	
+	public void buyHairSkin(int id)
+	{
+		if (!_hairSkins.contains(id))
+		{
+			_hairSkins.add(id);
+		}
+	}
+	
+	public void buyFaceSkin(int id)
+	{
+		if (!_faceSkins.contains(id))
+		{
+			_faceSkins.add(id);
+		}
+	}
+	
+	public void buyShieldSkin(int id)
+	{
+		if (!_shieldSkins.contains(id))
+		{
+			_shieldSkins.add(id);
+		}
+	}
+	
+	public void setArmorSkinOption(int armorSkinOption)
+	{
+		_armorSkinOption = armorSkinOption;
+	}
+	
+	public int getArmorSkinOption()
+	{
+		return _armorSkinOption;
+	}
+	
+	public void setWeaponSkinOption(int weaponSkinOption)
+	{
+		_weaponSkinOption = weaponSkinOption;
+	}
+	
+	public int getWeaponSkinOption()
+	{
+		return _weaponSkinOption;
+	}
+	
+	public void setHairSkinOption(int hairSkinOption)
+	{
+		_hairSkinOption = hairSkinOption;
+	}
+	
+	public int getHairSkinOption()
+	{
+		return _hairSkinOption;
+	}
+	
+	public void setFaceSkinOption(int faceSkinOption)
+	{
+		_faceSkinOption = faceSkinOption;
+	}
+	
+	public int getFaceSkinOption()
+	{
+		return _faceSkinOption;
+	}
+	
+	public void setShieldSkinOption(int shieldSkinOption)
+	{
+		_shieldSkinOption = shieldSkinOption;
+	}
+	
+	public int getShieldSkinOption()
+	{
+		return _shieldSkinOption;
+	}
+	
 }
\ No newline at end of file
diff --git src/l2jorion/game/model/base/SkinPackage.java src/l2jorion/game/model/base/SkinPackage.java
new file mode 100644
index 0000000..1dad239
--- /dev/null
+++ src/l2jorion/game/model/base/SkinPackage.java
@@ -0,0 +1,102 @@
+package l2jorion.game.model.base;
+
+import l2jorion.game.templates.StatsSet;
+
+public class SkinPackage
+{
+	private String _type;
+	private String _name;
+	private int _id;
+	private int _weaponId;
+	private int _shieldId;
+	private int _chestId;
+	private int _hairId;
+	private int _faceId;
+	private int _legsId;
+	private int _glovesId;
+	private int _feetId;
+	private int _priceId;
+	private int _priceCount;
+	
+	public SkinPackage(StatsSet set)
+	{
+		_type = set.getString("type", "default");
+		_name = set.getString("name", "NoName");
+		_id = set.getInteger("id", 0);
+		_weaponId = set.getInteger("weaponId", 0);
+		_shieldId = set.getInteger("shieldId", 0);
+		_chestId = set.getInteger("chestId", 0);
+		_hairId = set.getInteger("hairId", 0);
+		_faceId = set.getInteger("faceId", 0);
+		_legsId = set.getInteger("legsId", 0);
+		_glovesId = set.getInteger("glovesId", 0);
+		_feetId = set.getInteger("feetId", 0);
+		_priceId = set.getInteger("priceId", 0);
+		_priceCount = set.getInteger("priceCount", 0);
+	}
+	
+	public int getId()
+	{
+		return _id;
+	}
+	
+	public String getType()
+	{
+		return _type;
+	}
+	
+	public String getName()
+	{
+		return _name;
+	}
+	
+	public int getWeaponId()
+	{
+		return _weaponId;
+	}
+	
+	public int getShieldId()
+	{
+		return _shieldId;
+	}
+	
+	public int getChestId()
+	{
+		return _chestId;
+	}
+	
+	public int getHairId()
+	{
+		return _hairId;
+	}
+	
+	public int getFaceId()
+	{
+		return _faceId;
+	}
+	
+	public int getLegsId()
+	{
+		return _legsId;
+	}
+	
+	public int getGlovesId()
+	{
+		return _glovesId;
+	}
+	
+	public int getFeetId()
+	{
+		return _feetId;
+	}
+	
+	public int getPriceId()
+	{
+		return _priceId;
+	}
+	
+	public int getPriceCount()
+	{
+		return _priceCount;
+	}
+}
\ No newline at end of file
diff --git src/l2jorion/game/network/clientpackets/RequestBypassToServer.java src/l2jorion/game/network/clientpackets/RequestBypassToServer.java
index 7bb0cf1..91070a7 100644
--- src/l2jorion/game/network/clientpackets/RequestBypassToServer.java
+++ src/l2jorion/game/network/clientpackets/RequestBypassToServer.java
@@ -22,18 +22,24 @@
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.text.NumberFormat;
+import java.text.SimpleDateFormat;
+import java.util.Date;
+import java.util.List;
 import java.util.StringTokenizer;
+import java.util.stream.Collectors;
+import java.util.stream.Stream;
 
 import javolution.text.TextBuilder;
 import l2jorion.Config;
+import l2jorion.game.cache.HtmCache;
 import l2jorion.game.community.CommunityBoard;
 import l2jorion.game.datatables.SkillTable;
 import l2jorion.game.datatables.sql.AdminCommandAccessRights;
 import l2jorion.game.datatables.sql.ItemTable;
+import l2jorion.game.datatables.xml.DressMeData;
 import l2jorion.game.handler.AdminCommandHandler;
 import l2jorion.game.handler.IAdminCommandHandler;
 import l2jorion.game.handler.custom.CustomBypassHandler;
-import l2jorion.game.handler.voice.DressMe;
 import l2jorion.game.handler.voice.Vote;
 import l2jorion.game.handler.vote.Brasil;
 import l2jorion.game.handler.vote.Hopzone;
@@ -41,7 +47,6 @@
 import l2jorion.game.handler.vote.L2TopOnline;
 import l2jorion.game.handler.vote.Network;
 import l2jorion.game.handler.vote.Topzone;
-import l2jorion.game.model.Inventory;
 import l2jorion.game.model.L2DropCategory;
 import l2jorion.game.model.L2DropData;
 import l2jorion.game.model.L2Object;
@@ -56,7 +61,7 @@
 import l2jorion.game.model.actor.instance.L2PcInstance;
 import l2jorion.game.model.actor.instance.L2RaidBossInstance;
 import l2jorion.game.model.actor.instance.L2SymbolMakerInstance;
-import l2jorion.game.model.custom.DressMeData;
+import l2jorion.game.model.base.SkinPackage;
 import l2jorion.game.model.entity.event.CTF;
 import l2jorion.game.model.entity.event.DM;
 import l2jorion.game.model.entity.event.L2Event;
@@ -71,6 +76,8 @@
 import l2jorion.game.network.serverpackets.PlaySound;
 import l2jorion.game.network.serverpackets.SystemMessage;
 import l2jorion.game.templates.L2Item;
+import l2jorion.game.templates.L2WeaponType;
+import l2jorion.game.thread.ThreadPoolManager;
 import l2jorion.game.util.GMAudit;
 import l2jorion.game.util.Util;
 import l2jorion.log.Log;
@@ -765,6 +772,429 @@
 				final int arenaId = Integer.parseInt(_command.substring(12).trim());
 				activeChar.enterOlympiadObserverMode(arenaId);
 			}
+			
+			else if (_command.startsWith("dressme"))
+			{
+				if (!Config.ALLOW_DRESS_ME_IN_OLY && activeChar.isInOlympiadMode())
+				{
+					activeChar.sendMessage("DressMe can't be used on The Olympiad game.");
+					return;
+				}
+				
+				StringTokenizer st = new StringTokenizer(_command, " ");
+				st.nextToken();
+				if (!st.hasMoreTokens())
+				{
+					showDressMeMainPage(activeChar);
+					return;
+				}
+				int page = Integer.parseInt(st.nextToken());
+				
+				if (!st.hasMoreTokens())
+				{
+					showDressMeMainPage(activeChar);
+					return;
+				}
+				String next = st.nextToken();
+				if (next.startsWith("skinlist"))
+				{
+					String type = st.nextToken();
+					showSkinList(activeChar, type, page);
+				}
+				else if (next.startsWith("myskinlist"))
+				{
+					
+					showMySkinList(activeChar, page);
+				}
+				if (next.equals("clean"))
+				{
+					String type = st.nextToken();
+					
+					if (activeChar.isTryingSkin())
+					{
+						activeChar.sendMessage("You can't do this while trying a skin.");
+						activeChar.sendPacket(new ExShowScreenMessage("You can't do this while trying a skin.", 2000, 2, false));
+						activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+						showDressMeMainPage(activeChar);
+						return;
+					}
+					
+					switch (type.toLowerCase())
+					{
+						case "armor":
+							activeChar.setArmorSkinOption(0);
+							break;
+						case "weapon":
+							activeChar.setWeaponSkinOption(0);
+							break;
+						case "hair":
+							activeChar.setHairSkinOption(0);
+							break;
+						case "face":
+							activeChar.setFaceSkinOption(0);
+							break;
+						case "shield":
+							activeChar.setShieldSkinOption(0);
+							break;
+					}
+					
+					activeChar.broadcastUserInfo();
+					showMySkinList(activeChar, page);
+				}
+				else if (next.startsWith("buyskin"))
+				{
+					if (!st.hasMoreTokens())
+					{
+						showDressMeMainPage(activeChar);
+						return;
+					}
+					
+					int skinId = Integer.parseInt(st.nextToken());
+					String type = st.nextToken();
+					int itemId = Integer.parseInt(st.nextToken());
+					
+					SkinPackage sp = null;
+					
+					switch (type.toLowerCase())
+					{
+						case "armor":
+							sp = DressMeData.getInstance().getArmorSkinsPackage(skinId);
+							break;
+						case "weapon":
+							sp = DressMeData.getInstance().getWeaponSkinsPackage(skinId);
+							
+							if (activeChar.getActiveWeaponItem() == null)
+							{
+								activeChar.sendMessage("You can't buy this skin without a weapon.");
+								activeChar.sendPacket(new ExShowScreenMessage("You can't buy this skin without a weapon.", 2000, 2, false));
+								activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showSkinList(activeChar, type, page);
+								return;
+							}
+							
+							L2ItemInstance skinWeapon = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinWeapon = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinWeapon, L2WeaponType.BOW, L2WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.SWORD, L2WeaponType.SWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.BLUNT, L2WeaponType.BLUNT) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.DAGGER, L2WeaponType.DAGGER) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.POLE, L2WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.DUAL, L2WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.DUALFIST, L2WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.BIGSWORD, L2WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.FIST, L2WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.BIGBLUNT, L2WeaponType.BIGBLUNT))
+								{
+									activeChar.sendMessage("This skin is not suitable for your weapon type.");
+									activeChar.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000, 2, false));
+									activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+									showSkinList(activeChar, type, page);
+									return;
+								}
+							}
+							break;
+						case "hair":
+							sp = DressMeData.getInstance().getHairSkinsPackage(skinId);
+							break;
+						case "face":
+							sp = DressMeData.getInstance().getFaceSkinsPackage(skinId);
+							break;
+						case "shield":
+							sp = DressMeData.getInstance().getShieldSkinsPackage(skinId);
+							if (activeChar.getActiveWeaponItem() == null)
+							{
+								activeChar.sendMessage("You can't buy this skin without a weapon.");
+								activeChar.sendPacket(new ExShowScreenMessage("You can't buy this skin without a weapon.", 2000, 2, false));
+								activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showSkinList(activeChar, type, page);
+								return;
+							}
+							
+							L2ItemInstance skinShield = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinShield = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinShield, L2WeaponType.BOW, L2WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.POLE, L2WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.DUAL, L2WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.DUALFIST, L2WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.BIGSWORD, L2WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.FIST, L2WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.BIGBLUNT, L2WeaponType.BIGBLUNT))
+								{
+									activeChar.sendMessage("This skin is not suitable for your weapon type.");
+									activeChar.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000, 2, false));
+									activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+									showSkinList(activeChar, type, page);
+									return;
+								}
+							}
+							break;
+					}
+					
+					if (sp == null)
+					{
+						activeChar.sendMessage("There is no such skin.");
+						activeChar.sendPacket(new ExShowScreenMessage("There is no such skin.", 2000, 2, false));
+						activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+						showSkinList(activeChar, type, page);
+						return;
+					}
+					
+					if (activeChar.getPremiumService() >= 1)
+					{
+						activeChar.sendMessage("You have successfully purchased " + sp.getName() + " skin.");
+						activeChar.sendPacket(new ExShowScreenMessage("You have successfully purchased " + sp.getName() + " skin.", 2000, 2, false));
+						
+						activeChar.setIsTryingSkinPremium(true);
+						
+						switch (type.toLowerCase())
+						{
+							case "armor":
+								activeChar.setArmorSkinOption(skinId);
+								break;
+							case "weapon":
+								activeChar.setWeaponSkinOption(skinId);
+								break;
+							case "hair":
+								activeChar.setHairSkinOption(skinId);
+								break;
+							case "face":
+								activeChar.setFaceSkinOption(skinId);
+								break;
+							case "shield":
+								activeChar.setShieldSkinOption(skinId);
+								break;
+						}
+						
+						activeChar.broadcastUserInfo();
+						showSkinList(activeChar, type, page);
+						return;
+					}
+					
+					if (activeChar.destroyItemByItemId("dressme", sp.getPriceId(), sp.getPriceCount(), activeChar, true))
+					{
+						activeChar.sendMessage("You have successfully purchased " + sp.getName() + " skin.");
+						activeChar.sendPacket(new ExShowScreenMessage("You have successfully purchased " + sp.getName() + " skin.", 2000, 2, false));
+						
+						switch (type.toLowerCase())
+						{
+							case "armor":
+								activeChar.buyArmorSkin(skinId);
+								activeChar.setArmorSkinOption(skinId);
+								break;
+							case "weapon":
+								activeChar.buyWeaponSkin(skinId);
+								activeChar.setWeaponSkinOption(skinId);
+								break;
+							case "hair":
+								activeChar.buyHairSkin(skinId);
+								activeChar.setHairSkinOption(skinId);
+								break;
+							case "face":
+								activeChar.buyFaceSkin(skinId);
+								activeChar.setFaceSkinOption(skinId);
+								break;
+							case "shield":
+								activeChar.buyShieldSkin(skinId);
+								activeChar.setShieldSkinOption(skinId);
+								break;
+						}
+						
+						activeChar.broadcastUserInfo();
+					}
+					showSkinList(activeChar, type, page);
+				}
+				else if (next.startsWith("tryskin"))
+				{
+					
+					int skinId = Integer.parseInt(st.nextToken());
+					
+					String type = st.nextToken();
+					
+					if (activeChar.isTryingSkin())
+					{
+						activeChar.sendMessage("You are already trying a skin.");
+						activeChar.sendPacket(new ExShowScreenMessage("You are already trying a skin.", 2000, 2, false));
+						activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+						showSkinList(activeChar, type, page);
+						return;
+					}
+					
+					activeChar.setIsTryingSkin(true);
+					
+					int oldArmorSkinId = activeChar.getArmorSkinOption();
+					int oldWeaponSkinId = activeChar.getWeaponSkinOption();
+					int oldHairSkinId = activeChar.getHairSkinOption();
+					int oldFaceSkinId = activeChar.getFaceSkinOption();
+					int oldShieldSkinId = activeChar.getShieldSkinOption();
+					
+					switch (type.toLowerCase())
+					{
+						case "armor":
+							activeChar.setArmorSkinOption(skinId);
+							break;
+						case "weapon":
+							activeChar.setWeaponSkinOption(skinId);
+							break;
+						case "hair":
+							activeChar.setHairSkinOption(skinId);
+							break;
+						case "face":
+							activeChar.setFaceSkinOption(skinId);
+							break;
+						case "shield":
+							
+							activeChar.setShieldSkinOption(skinId);
+							
+							break;
+					}
+					
+					activeChar.broadcastUserInfo();
+					showSkinList(activeChar, type, page);
+					
+					ThreadPoolManager.getInstance().scheduleGeneral(() ->
+					{
+						switch (type.toLowerCase())
+						{
+							case "armor":
+								activeChar.setArmorSkinOption(oldArmorSkinId);
+								break;
+							case "weapon":
+								activeChar.setWeaponSkinOption(oldWeaponSkinId);
+								break;
+							case "hair":
+								activeChar.setHairSkinOption(oldHairSkinId);
+								break;
+							case "face":
+								activeChar.setFaceSkinOption(oldFaceSkinId);
+								break;
+							case "shield":
+								activeChar.setShieldSkinOption(oldShieldSkinId);
+								break;
+						}
+						
+						activeChar.broadcastUserInfo();
+						activeChar.setIsTryingSkin(false);
+					}, 5000);
+				}
+				else if (next.startsWith("setskin"))
+				{
+					int id = Integer.parseInt(st.nextToken());
+					String type = st.nextToken();
+					int itemId = Integer.parseInt(st.nextToken());
+					
+					if (activeChar.isTryingSkin())
+					{
+						activeChar.sendMessage("You can't do this while trying skins.");
+						activeChar.sendPacket(new ExShowScreenMessage("You can't do this while trying skins.", 2000, 2, false));
+						activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+						showMySkinList(activeChar, page);
+						return;
+					}
+					
+					if (type.toLowerCase().contains("armor") && activeChar.hasEquippedArmorSkin(String.valueOf(id)) || type.toLowerCase().contains("weapon") && activeChar.hasEquippedWeaponSkin(String.valueOf(id))
+						|| type.toLowerCase().contains("hair") && activeChar.hasEquippedHairSkin(String.valueOf(id)) || type.toLowerCase().contains("face") && activeChar.hasEquippedFaceSkin(String.valueOf(id)))
+					{
+						activeChar.sendMessage("You are already equipped this skin.");
+						activeChar.sendPacket(new ExShowScreenMessage("You are already equipped this skin.", 2000, 2, false));
+						activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+						showMySkinList(activeChar, page);
+						return;
+					}
+					
+					switch (type.toLowerCase())
+					{
+						case "armor":
+							activeChar.setArmorSkinOption(id);
+							break;
+						case "weapon":
+							if (activeChar.getActiveWeaponItem() == null)
+							{
+								activeChar.sendMessage("You can't use this skin without a weapon.");
+								activeChar.sendPacket(new ExShowScreenMessage("You can't use this skin without a weapon.", 2000, 2, false));
+								activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showMySkinList(activeChar, page);
+								return;
+							}
+							
+							L2ItemInstance skinWeapon = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinWeapon = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinWeapon, L2WeaponType.BOW, L2WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.SWORD, L2WeaponType.SWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.BLUNT, L2WeaponType.BLUNT) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.DAGGER, L2WeaponType.DAGGER) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.POLE, L2WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.DUAL, L2WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.DUALFIST, L2WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.BIGSWORD, L2WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.FIST, L2WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinWeapon, L2WeaponType.BIGBLUNT, L2WeaponType.BIGBLUNT))
+								{
+									activeChar.sendMessage("This skin is not suitable for your weapon type.");
+									activeChar.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000, 2, false));
+									activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+									showMySkinList(activeChar, page);
+									return;
+								}
+								
+								activeChar.setWeaponSkinOption(id);
+							}
+							break;
+						case "hair":
+							activeChar.setHairSkinOption(id);
+							break;
+						case "face":
+							activeChar.setFaceSkinOption(id);
+							break;
+						case "shield":
+							if (activeChar.getActiveWeaponItem() == null)
+							{
+								activeChar.sendMessage("You can't use this skin without a weapon.");
+								activeChar.sendPacket(new ExShowScreenMessage("You can't use this skin without a weapon.", 2000, 2, false));
+								activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showMySkinList(activeChar, page);
+								return;
+							}
+							
+							L2ItemInstance skinShield = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinShield = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinShield, L2WeaponType.BOW, L2WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.POLE, L2WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.DUAL, L2WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.DUALFIST, L2WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.BIGSWORD, L2WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.FIST, L2WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinShield, L2WeaponType.BIGBLUNT, L2WeaponType.BIGBLUNT))
+								{
+									activeChar.sendMessage("This skin is not suitable for your weapon type.");
+									activeChar.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000, 2, false));
+									activeChar.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+									showMySkinList(activeChar, page);
+									return;
+								}
+								
+								activeChar.setShieldSkinOption(id);
+							}
+							
+							break;
+					}
+					
+					activeChar.broadcastUserInfo();
+					showMySkinList(activeChar, page);
+				}
+			}
+			
 			else if (_command.startsWith("dropInfo"))
 			{
 				final String[] val = _command.split(" ");
@@ -929,51 +1359,7 @@
 					activeChar.sendPacket(html);
 				}
 			}
-			else if (_command.equals("bp_changedressmestatus"))
-			{
-				if (activeChar.isDressMeEnabled())
-				{
-					activeChar.setDressMeEnabled(false);
-					activeChar.broadcastUserInfo();
-				}
-				else
-				{
-					activeChar.setDressMeEnabled(true);
-					activeChar.broadcastUserInfo();
-				}
-				
-				DressMe.sendMainWindow(activeChar);
-			}
-			else if (_command.startsWith("bp_editWindow"))
-			{
-				String bp = _command.substring(14);
-				StringTokenizer st = new StringTokenizer(bp);
-				
-				sendEditWindow(activeChar, st.nextToken());
-			}
-			else if (_command.startsWith("bp_setpart"))
-			{
-				String bp = _command.substring(11);
-				StringTokenizer st = new StringTokenizer(bp);
-				
-				String part = st.nextToken();
-				String type = st.nextToken();
-				
-				setPart(activeChar, part, type);
-			}
-			else if (_command.startsWith("bp_gettarget"))
-			{
-				String bp = _command.substring(13);
-				StringTokenizer st = new StringTokenizer(bp);
-				
-				String part = st.nextToken();
-				
-				stealTarget(activeChar, part);
-			}
-			else if (_command.equals("bp_main"))
-			{
-				DressMe.sendMainWindow(activeChar);
-			}
+			
 		}
 		catch (Exception e)
 		{
@@ -1038,144 +1424,6 @@
 		return formatedChance;
 	}
 	
-	public void stealTarget(L2PcInstance p, String part)
-	{
-		if (p.getTarget() == null || !(p.getTarget() instanceof L2PcInstance))
-		{
-			p.sendMessage("Invalid target.");
-			return;
-		}
-		
-		L2PcInstance t = (L2PcInstance) p.getTarget();
-		
-		if (p.getDressMeData() == null)
-		{
-			DressMeData dmd = new DressMeData();
-			p.setDressMeData(dmd);
-		}
-		
-		boolean returnMain = false;
-		
-		switch (part)
-		{
-			case "chest":
-			{
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST) == null)
-				{
-					p.getDressMeData().setChestId(0);
-				}
-				else
-				{
-					p.getDressMeData().setChestId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST).getItemId());
-				}
-				break;
-			}
-			case "legs":
-			{
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEGS) == null)
-				{
-					p.getDressMeData().setLegsId(0);
-				}
-				else
-				{
-					p.getDressMeData().setLegsId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEGS).getItemId());
-				}
-				break;
-			}
-			case "gloves":
-			{
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_GLOVES) == null)
-				{
-					p.getDressMeData().setGlovesId(0);
-				}
-				else
-				{
-					p.getDressMeData().setGlovesId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_GLOVES).getItemId());
-				}
-				break;
-			}
-			case "boots":
-			{
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_FEET) == null)
-				{
-					p.getDressMeData().setBootsId(0);
-				}
-				else
-				{
-					p.getDressMeData().setBootsId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_FEET).getItemId());
-				}
-				break;
-			}
-			case "weap":
-			{
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND) == null)
-				{
-					p.getDressMeData().setWeapId(0);
-				}
-				else
-				{
-					p.getDressMeData().setWeapId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND).getItemId());
-				}
-				break;
-			}
-			case "all":
-			{
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST) == null)
-				{
-					p.getDressMeData().setChestId(0);
-				}
-				else
-				{
-					p.getDressMeData().setChestId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST).getItemId());
-				}
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEGS) == null)
-				{
-					p.getDressMeData().setLegsId(0);
-				}
-				else
-				{
-					p.getDressMeData().setLegsId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEGS).getItemId());
-				}
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_GLOVES) == null)
-				{
-					p.getDressMeData().setGlovesId(0);
-				}
-				else
-				{
-					p.getDressMeData().setGlovesId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_GLOVES).getItemId());
-				}
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_FEET) == null)
-				{
-					p.getDressMeData().setBootsId(0);
-				}
-				else
-				{
-					p.getDressMeData().setBootsId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_FEET).getItemId());
-				}
-				if (t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND) == null)
-				{
-					p.getDressMeData().setWeapId(0);
-				}
-				else
-				{
-					p.getDressMeData().setWeapId(t.getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND).getItemId());
-				}
-				returnMain = true;
-				break;
-			}
-		}
-		
-		p.broadcastUserInfo();
-		if (!returnMain)
-		{
-			sendEditWindow(p, part);
-		}
-		else
-		{
-			DressMe.sendMainWindow(p);
-		}
-	}
-	
 	public String getItemNameById(int itemId)
 	{
 		L2Item item = ItemTable.getInstance().getTemplate(itemId);
@@ -1190,195 +1438,246 @@
 		return itemName;
 	}
 	
-	public void setPart(L2PcInstance p, String part, String type)
+	public static void showDressMeMainPage(L2PcInstance player)
 	{
-		if (p.getDressMeData() == null)
-		{
-			DressMeData dmd = new DressMeData();
-			p.setDressMeData(dmd);
-		}
+		NpcHtmlMessage htm = new NpcHtmlMessage(1);
+		String text = HtmCache.getInstance().getHtm("data/html/dressme/index.htm");
 		
-		if (Config.ALLOW_DRESS_ME_FOR_ITEM)
+		htm.setHtml(text);
+		
 		{
-			final int currency = Config.DRESS_ME_ITEM_ID;
-			final L2ItemInstance item = p.getInventory().getItemByItemId(currency);
+			htm.replace("%time%", sdf.format(new Date(System.currentTimeMillis())));
+			htm.replace("%dat%", (new SimpleDateFormat("dd/MM/yyyy")).format(new Date(System.currentTimeMillis())));
 			
-			if (item == null || item.getCount() < Config.DRESS_ME_ITEM_COUNT)
-			{
-				p.sendMessage("You don't have enough " + getItemNameById(currency) + ".");
-				p.sendPacket(new ExShowScreenMessage("You don't have enough " + getItemNameById(currency) + ".", 3000, 2, false));
-				p.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
-				return;
-			}
-			
-			p.destroyItem("Consume", item.getObjectId(), Config.DRESS_ME_ITEM_COUNT, null, true);
 		}
 		
-		if (Config.ALLOW_DRESS_ME_FOR_PREMIUM)
-		{
-			if (p.getPremiumService() == 0)
-			{
-				p.sendMessage("You're not The Premium Account.");
-				p.sendPacket(new ExShowScreenMessage("You're not The Premium account.", 3000, 2, false));
-				p.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
-				return;
-			}
-		}
-		
-		switch (part)
-		{
-			case "chest":
-			{
-				if (Config.DRESS_ME_CHESTS.keySet().contains(type))
-				{
-					p.getDressMeData().setChestId(Config.DRESS_ME_CHESTS.get(type));
-				}
-				
-				break;
-			}
-			case "legs":
-			{
-				if (Config.DRESS_ME_LEGS.keySet().contains(type))
-				{
-					p.getDressMeData().setLegsId(Config.DRESS_ME_LEGS.get(type));
-				}
-				
-				break;
-			}
-			case "gloves":
-			{
-				if (Config.DRESS_ME_GLOVES.keySet().contains(type))
-				{
-					p.getDressMeData().setGlovesId(Config.DRESS_ME_GLOVES.get(type));
-				}
-				
-				break;
-			}
-			case "boots":
-			{
-				if (Config.DRESS_ME_BOOTS.keySet().contains(type))
-				{
-					p.getDressMeData().setBootsId(Config.DRESS_ME_BOOTS.get(type));
-				}
-				
-				break;
-			}
-			case "weap":
-			{
-				if (Config.DRESS_ME_WEAPONS.keySet().contains(type))
-				{
-					p.getDressMeData().setWeapId(Config.DRESS_ME_WEAPONS.get(type));
-				}
-				
-				break;
-			}
-		}
-		
-		p.broadcastUserInfo();
-		sendEditWindow(p, part);
+		player.sendPacket(htm);
 	}
 	
-	public void sendEditWindow(L2PcInstance p, String part)
+	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
+	
+	private static void showSkinList(L2PcInstance player, String type, int page)
 	{
-		NpcHtmlMessage htm = new NpcHtmlMessage(0);
-		htm.setFile("./data/html/custom/dressme/edit.htm");
-		htm.replace("%part%", part);
+		NpcHtmlMessage html = new NpcHtmlMessage(1);
 		
-		switch (part)
+		html.setFile("data/html/dressme/allskins.htm");
+		
+		html.replace("%time%", sdf.format(new Date(System.currentTimeMillis())));
+		html.replace("%dat%", (new SimpleDateFormat("dd/MM/yyyy")).format(new Date(System.currentTimeMillis())));
+		
+		final int ITEMS_PER_PAGE = 8;
+		
+		int myPage = 1;
+		int i = 0;
+		int shown = 0;
+		boolean hasMore = false;
+		int itemId = 0;
+		
+		final StringBuilder sb = new StringBuilder();
+		
+		List<SkinPackage> tempList = null;
+		switch (type.toLowerCase())
 		{
-			case "chest":
-			{
-				if (p.getDressMeData() == null)
-				{
-					htm.replace("%partinfo%", "You have no custom chest.");
-				}
-				else
-				{
-					htm.replace("%partinfo%", p.getDressMeData().getChestId() == 0 ? "You have no custom chest." : ItemTable.getInstance().getTemplate(p.getDressMeData().getChestId()).getName());
-				}
-				String temp = "";
-				for (String s : Config.DRESS_ME_CHESTS.keySet())
-				{
-					temp += s + ";";
-				}
-				htm.replace("%dropboxdata%", temp);
+			case "armor":
+				tempList = DressMeData.getInstance().getArmorSkinOptions().values().stream().filter(s -> !player.hasArmorSkin(s.getId())).collect(Collectors.toList());
 				break;
-			}
-			case "legs":
-			{
-				if (p.getDressMeData() == null)
-				{
-					htm.replace("%partinfo%", "You have no custom legs.");
-				}
-				else
-				{
-					htm.replace("%partinfo%", p.getDressMeData().getLegsId() == 0 ? "You have no custom legs." : ItemTable.getInstance().getTemplate(p.getDressMeData().getLegsId()).getName());
-				}
-				String temp = "";
-				for (String s : Config.DRESS_ME_LEGS.keySet())
-				{
-					temp += s + ";";
-				}
-				htm.replace("%dropboxdata%", temp);
+			case "weapon":
+				tempList = DressMeData.getInstance().getWeaponSkinOptions().values().stream().filter(s -> !player.hasWeaponSkin(s.getId())).collect(Collectors.toList());
 				break;
-			}
-			case "gloves":
-			{
-				if (p.getDressMeData() == null)
-				{
-					htm.replace("%partinfo%", "You have no custom gloves.");
-				}
-				else
-				{
-					htm.replace("%partinfo%", p.getDressMeData().getGlovesId() == 0 ? "You have no custom gloves." : ItemTable.getInstance().getTemplate(p.getDressMeData().getGlovesId()).getName());
-				}
-				String temp = "";
-				for (String s : Config.DRESS_ME_GLOVES.keySet())
-				{
-					temp += s + ";";
-				}
-				htm.replace("%dropboxdata%", temp);
+			case "hair":
+				tempList = DressMeData.getInstance().getHairSkinOptions().values().stream().filter(s -> !player.hasHairSkin(s.getId())).collect(Collectors.toList());
 				break;
-			}
-			case "boots":
-			{
-				if (p.getDressMeData() == null)
-				{
-					htm.replace("%partinfo%", "You have no custom boots.");
-				}
-				else
-				{
-					htm.replace("%partinfo%", p.getDressMeData().getBootsId() == 0 ? "You have no custom boots." : ItemTable.getInstance().getTemplate(p.getDressMeData().getBootsId()).getName());
-				}
-				String temp = "";
-				for (String s : Config.DRESS_ME_BOOTS.keySet())
-				{
-					temp += s + ";";
-				}
-				htm.replace("%dropboxdata%", temp);
+			case "face":
+				tempList = DressMeData.getInstance().getFaceSkinOptions().values().stream().filter(s -> !player.hasFaceSkin(s.getId())).collect(Collectors.toList());
 				break;
-			}
-			case "weap":
-			{
-				if (p.getDressMeData() == null)
-				{
-					htm.replace("%partinfo%", "You have no custom weapon.");
-				}
-				else
-				{
-					htm.replace("%partinfo%", p.getDressMeData().getWeapId() == 0 ? "You have no custom weapon." : ItemTable.getInstance().getTemplate(p.getDressMeData().getWeapId()).getName());
-				}
-				String temp = "";
-				for (String s : Config.DRESS_ME_WEAPONS.keySet())
-				{
-					temp += s + ";";
-				}
-				htm.replace("%dropboxdata%", temp);
+			case "shield":
+				tempList = DressMeData.getInstance().getShieldSkinOptions().values().stream().filter(s -> !player.hasShieldSkin(s.getId())).collect(Collectors.toList());
 				break;
+		}
+		
+		if (tempList != null && !tempList.isEmpty())
+		{
+			for (SkinPackage sp : tempList)
+			{
+				if (sp == null)
+				{
+					continue;
+				}
+				
+				if (shown == ITEMS_PER_PAGE)
+				{
+					hasMore = true;
+					break;
+				}
+				
+				if (myPage != page)
+				{
+					i++;
+					if (i == ITEMS_PER_PAGE)
+					{
+						myPage++;
+						i = 0;
+					}
+					continue;
+				}
+				
+				if (shown == ITEMS_PER_PAGE)
+				{
+					hasMore = true;
+					break;
+				}
+				
+				switch (type.toLowerCase())
+				{
+					case "armor":
+						itemId = sp.getChestId();
+						break;
+					case "weapon":
+						itemId = sp.getWeaponId();
+						break;
+					case "hair":
+						itemId = sp.getHairId();
+						break;
+					case "face":
+						itemId = sp.getFaceId();
+						break;
+					case "shield":
+						itemId = sp.getShieldId();
+						break;
+				}
+				
+				sb.append("<table border=0 cellspacing=0 cellpadding=2 height=36><tr>");
+				sb.append("<td width=32 align=center>" + "<button width=32 height=32 back=" + L2Item.getItemIcon(itemId) + " fore=" + L2Item.getItemIcon(itemId) + ">" + "</td>");
+				sb.append("<td width=124>" + sp.getName() + "<br1> <font color=999999>Price:</font> <font color=339966>" + L2Item.getItemNameById(sp.getPriceId()) + "</font> (<font color=LEVEL>" + sp.getPriceCount() + "</font>)</td>");
+				sb.append("<td align=center width=65>" + "<button value=\"Buy\" action=\"bypass -h dressme " + page + " buyskin  " + sp.getId() + " " + type + " " + itemId + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" + "</td>");
+				sb.append("<td align=center width=65>" + "<button value=\"Try\" action=\"bypass -h dressme " + page + " tryskin  " + sp.getId() + " " + type + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" + "</td>");
+				
+				sb.append("</tr></table>");
+				sb.append("<img src=\"L2UI.Squaregray\" width=\"300\" height=\"1\">");
+				shown++;
 			}
 		}
 		
-		p.sendPacket(htm);
+		sb.append("<table width=300><tr>");
+		sb.append("<td align=center width=70>" + (page > 1 ? "<button value=\"< PREV\" action=\"bypass -h dressme " + (page - 1) + " skinlist " + type + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" : "") + "</td>");
+		sb.append("<td align=center width=140>Page: " + page + "</td>");
+		sb.append("<td align=center width=70>" + (hasMore ? "<button value=\"NEXT >\" action=\"bypass -h dressme " + (page + 1) + " skinlist " + type + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" : "") + "</td>");
+		sb.append("</tr></table>");
+		
+		html.replace("%showList%", sb.toString());
+		player.sendPacket(html);
+	}
+	
+	private static void showMySkinList(L2PcInstance player, int page)
+	{
+		NpcHtmlMessage html = new NpcHtmlMessage(1);
+		html.setFile("data/html/dressme/myskins.htm");
+		
+		html.replace("%time%", sdf.format(new Date(System.currentTimeMillis())));
+		html.replace("%dat%", (new SimpleDateFormat("dd/MM/yyyy")).format(new Date(System.currentTimeMillis())));
+		
+		final int ITEMS_PER_PAGE = 8;
+		int itemId = 0;
+		
+		int myPage = 1;
+		int i = 0;
+		int shown = 0;
+		boolean hasMore = false;
+		
+		final StringBuilder sb = new StringBuilder();
+		
+		List<SkinPackage> armors = DressMeData.getInstance().getArmorSkinOptions().values().stream().filter(s -> player.hasArmorSkin(s.getId())).collect(Collectors.toList());
+		List<SkinPackage> weapons = DressMeData.getInstance().getWeaponSkinOptions().values().stream().filter(s -> player.hasWeaponSkin(s.getId())).collect(Collectors.toList());
+		List<SkinPackage> hairs = DressMeData.getInstance().getHairSkinOptions().values().stream().filter(s -> player.hasHairSkin(s.getId())).collect(Collectors.toList());
+		// List<SkinPackage> faces = DressMeData.getInstance().getFaceSkinOptions().values().stream().filter(s -> player.hasFaceSkin(s.getId())).collect(Collectors.toList());
+		List<SkinPackage> shield = DressMeData.getInstance().getShieldSkinOptions().values().stream().filter(s -> player.hasShieldSkin(s.getId())).collect(Collectors.toList());
+		
+		List<SkinPackage> list = Stream.concat(armors.stream(), weapons.stream()).collect(Collectors.toList());
+		shield.stream().collect(Collectors.toList());
+		List<SkinPackage> list2 = Stream.concat(hairs.stream(), shield.stream()).collect(Collectors.toList());
+		
+		List<SkinPackage> allLists = Stream.concat(list.stream(), list2.stream()).collect(Collectors.toList());
+		
+		if (!allLists.isEmpty())
+		{
+			for (SkinPackage sp : allLists)
+			{
+				if (sp == null)
+				{
+					continue;
+				}
+				
+				if (shown == ITEMS_PER_PAGE)
+				{
+					hasMore = true;
+					break;
+				}
+				
+				if (myPage != page)
+				{
+					i++;
+					if (i == ITEMS_PER_PAGE)
+					{
+						myPage++;
+						i = 0;
+					}
+					continue;
+				}
+				
+				if (shown == ITEMS_PER_PAGE)
+				{
+					hasMore = true;
+					break;
+				}
+				
+				switch (sp.getType().toLowerCase())
+				{
+					case "armor":
+						itemId = sp.getChestId();
+						break;
+					case "weapon":
+						itemId = sp.getWeaponId();
+						break;
+					case "hair":
+						itemId = sp.getHairId();
+						break;
+					case "face":
+						itemId = sp.getFaceId();
+						break;
+					case "shield":
+						itemId = sp.getShieldId();
+						break;
+				}
+				
+				sb.append("<table border=0 cellspacing=0 cellpadding=2 height=36><tr>");
+				sb.append("<td width=32 align=center>" + "<button width=32 height=32 back=" + L2Item.getItemIcon(itemId) + " fore=" + L2Item.getItemIcon(itemId) + ">" + "</td>");
+				sb.append("<td width=124>" + sp.getName() + "</td>");
+				sb.append("<td align=center width=65>" + "<button value=\"Equip\" action=\"bypass -h dressme " + page + " setskin " + sp.getId() + " " + sp.getType() + " " + itemId + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" + "</td>");
+				sb.append("<td align=center width=65>" + "<button value=\"Remove\" action=\"bypass -h dressme " + page + " clean " + sp.getType() + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" + "</td>");
+				sb.append("</tr></table>");
+				sb.append("<img src=\"L2UI.Squaregray\" width=\"300\" height=\"1\">");
+				shown++;
+			}
+		}
+		
+		sb.append("<table width=300><tr>");
+		sb.append("<td align=center width=70>" + (page > 1 ? "<button value=\"< PREV\" action=\"bypass -h dressme " + (page - 1) + " myskinlist\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" : "") + "</td>");
+		sb.append("<td align=center width=140>Page: " + page + "</td>");
+		sb.append("<td align=center width=70>" + (hasMore ? "<button value=\"NEXT >\" action=\"bypass -h dressme " + (page + 1) + " myskinlist\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" : "") + "</td>");
+		sb.append("</tr></table>");
+		
+		html.replace("%showList%", sb.toString());
+		player.sendPacket(html);
+	}
+	
+	public boolean checkWeapons(L2PcInstance player, L2ItemInstance skin, L2WeaponType weapon1, L2WeaponType weapon2)
+	{
+		if (player.getActiveWeaponItem().getItemType() == weapon1 && skin.getItem().getItemType() != weapon2)
+		{
+			return false;
+		}
+		
+		return true;
 	}
 	
 	@Override
diff --git src/l2jorion/game/network/serverpackets/CharInfo.java src/l2jorion/game/network/serverpackets/CharInfo.java
index 862911a..7473b11 100644
--- src/l2jorion/game/network/serverpackets/CharInfo.java
+++ src/l2jorion/game/network/serverpackets/CharInfo.java
@@ -25,11 +25,13 @@
 
 import l2jorion.Config;
 import l2jorion.game.datatables.sql.NpcTable;
+import l2jorion.game.datatables.xml.DressMeData;
 import l2jorion.game.managers.CursedWeaponsManager;
 import l2jorion.game.model.Inventory;
 import l2jorion.game.model.L2Character;
 import l2jorion.game.model.actor.instance.L2CubicInstance;
 import l2jorion.game.model.actor.instance.L2PcInstance;
+import l2jorion.game.model.base.SkinPackage;
 import l2jorion.game.templates.L2NpcTemplate;
 import l2jorion.logger.Logger;
 import l2jorion.logger.LoggerFactory;
@@ -182,55 +184,81 @@
 				writeD(_activeChar.getBaseClass());
 			}
 			
-			if (!_activeChar.isDressMeEnabled())
+			if (Config.ALLOW_DRESS_ME_SYSTEM)
 			{
-				
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_DHAIR));
-				
-				if (!Config.FAKE_ARMORS)
+				if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
 				{
-					writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
+					writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
 				}
 				else
 				{
-					writeD(_fakeArmorItemId == 0 ? _inv.getPaperdollItemId(Inventory.PAPERDOLL_HEAD) : 0);
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
 				}
 				
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
-				
-				if (!Config.FAKE_ARMORS)
+				if (_activeChar.getShieldSkinOption() > 0 && getShieldOption(_activeChar.getShieldSkinOption()) != null)
 				{
-					writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
-					writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
-					writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
-					writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+					writeD(getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
 				}
 				else
 				{
-					writeD(_fakeArmorItemId == 0 ? _inv.getPaperdollItemId(Inventory.PAPERDOLL_GLOVES) : 0);
-					writeD(_fakeArmorItemId == 0 ? _inv.getPaperdollItemId(Inventory.PAPERDOLL_CHEST) : _fakeArmorItemId);
-					writeD(_fakeArmorItemId == 0 ? _inv.getPaperdollItemId(Inventory.PAPERDOLL_LEGS) : 0);
-					writeD(_fakeArmorItemId == 0 ? _inv.getPaperdollItemId(Inventory.PAPERDOLL_FEET) : 0);
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
 				}
 				
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_BACK));
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+				if (_activeChar.getArmorSkinOption() > 0 && getArmorOption(_activeChar.getArmorSkinOption()) != null)
+				{
+					writeD(getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+					writeD(getArmorOption(_activeChar.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getChestId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+					writeD(getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+					writeD(getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+				}
+				else
+				{
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+				}
+				
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
+				
+				if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+				{
+					writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
+				}
+				else
+				{
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
+				}
+				
+				if (_activeChar.getHairSkinOption() > 0 && getHairOption(_activeChar.getHairSkinOption()) != null)
+				{
+					writeD(getHairOption(_activeChar.getHairSkinOption()).getHairId() != 0 ? getHairOption(_activeChar.getHairSkinOption()).getHairId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
+				}
+				else
+				{
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
+				}
+				
+				if (_activeChar.getFaceSkinOption() > 0 && getFaceOption(_activeChar.getFaceSkinOption()) != null)
+				{
+					writeD(getFaceOption(_activeChar.getFaceSkinOption()).getFaceId() != 0 ? getFaceOption(_activeChar.getFaceSkinOption()).getFaceId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+				}
+				else
+				{
+					writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+				}
+				
 			}
 			else
 			{
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_DHAIR));
-				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
-				writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : (_activeChar.getDressMeData().getWeapId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : _activeChar.getDressMeData().getWeapId()));
+				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
 				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
-				writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES) : (_activeChar.getDressMeData().getGlovesId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES) : _activeChar.getDressMeData().getGlovesId()));
-				writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST) : (_activeChar.getDressMeData().getChestId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST) : _activeChar.getDressMeData().getChestId()));
-				writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS) : (_activeChar.getDressMeData().getLegsId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS) : _activeChar.getDressMeData().getLegsId()));
-				writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET) : (_activeChar.getDressMeData().getBootsId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET) : _activeChar.getDressMeData().getBootsId()));
+				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FEET));
 				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_BACK));
-				writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : (_activeChar.getDressMeData().getWeapId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : _activeChar.getDressMeData().getWeapId()));
+				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
 				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
 				writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FACE));
 			}
@@ -393,6 +421,31 @@
 		}
 	}
 	
+	public SkinPackage getArmorOption(int option)
+	{
+		return (DressMeData.getInstance().getArmorSkinsPackage(option));
+	}
+	
+	public SkinPackage getWeaponOption(int option)
+	{
+		return DressMeData.getInstance().getWeaponSkinsPackage(option);
+	}
+	
+	public SkinPackage getHairOption(int option)
+	{
+		return DressMeData.getInstance().getHairSkinsPackage(option);
+	}
+	
+	public SkinPackage getFaceOption(int option)
+	{
+		return DressMeData.getInstance().getFaceSkinsPackage(option);
+	}
+	
+	public SkinPackage getShieldOption(int option)
+	{
+		return DressMeData.getInstance().getShieldSkinsPackage(option);
+	}
+	
 	@Override
 	public String getType()
 	{
diff --git src/l2jorion/game/network/serverpackets/UserInfo.java src/l2jorion/game/network/serverpackets/UserInfo.java
index bdc941f..94dcaf8 100644
--- src/l2jorion/game/network/serverpackets/UserInfo.java
+++ src/l2jorion/game/network/serverpackets/UserInfo.java
@@ -22,10 +22,12 @@
 
 import l2jorion.Config;
 import l2jorion.game.datatables.sql.NpcTable;
+import l2jorion.game.datatables.xml.DressMeData;
 import l2jorion.game.managers.CursedWeaponsManager;
 import l2jorion.game.model.Inventory;
 import l2jorion.game.model.L2Summon;
 import l2jorion.game.model.actor.instance.L2PcInstance;
+import l2jorion.game.model.base.SkinPackage;
 import l2jorion.game.templates.L2NpcTemplate;
 
 public class UserInfo extends L2GameServerPacket
@@ -115,125 +117,178 @@
 		
 		writeD(_activeChar.getActiveWeaponItem() != null ? 40 : 20); // 20 no weapon, 40 weapon equippe
 		
-		if (!_activeChar.isDressMeEnabled())
+		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DHAIR));
+		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_REAR));
+		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEAR));
+		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_NECK));
+		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RFINGER));
+		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LFINGER));
+		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HEAD));
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
 		{
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DHAIR));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_REAR));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEAR));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_NECK));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RFINGER));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LFINGER));
-			
-			if (!Config.FAKE_ARMORS)
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
 			{
-				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HEAD));
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
 			}
 			else
 			{
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HEAD) : 0);
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
 			}
 			
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
+			if (_activeChar.getShieldSkinOption() > 0 && getShieldOption(_activeChar.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
+			}
 			
-			if (!Config.FAKE_ARMORS)
+			if (_activeChar.getArmorSkinOption() > 0 && getArmorOption(_activeChar.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getChestId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
+			}
+			else
 			{
 				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
 				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
 				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
 				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
 			}
+			
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
+			
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LRHAND));
+			}
 			else
 			{
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES) : 0);
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST) : _fakeArmorObjectId);
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS) : 0);
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET) : 0);
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LRHAND));
 			}
 			
+			if (_activeChar.getHairSkinOption() > 0 && getHairOption(_activeChar.getHairSkinOption()) != null)
+			{
+				writeD(getHairOption(_activeChar.getHairSkinOption()).getHairId() != 0 ? getHairOption(_activeChar.getHairSkinOption()).getHairId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));
+			}
+			
+			if (_activeChar.getFaceSkinOption() > 0 && getFaceOption(_activeChar.getFaceSkinOption()) != null)
+			{
+				writeD(getFaceOption(_activeChar.getFaceSkinOption()).getFaceId() != 0 ? getFaceOption(_activeChar.getFaceSkinOption()).getFaceId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FACE));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FACE));
+			}
+			
+		}
+		else
+		{
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
 			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
 			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LRHAND));
 			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));
 			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FACE));
-			
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DHAIR));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_REAR));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEAR));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_NECK));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));
-			
-			if (!Config.FAKE_ARMORS)
+		}
+		
+		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DHAIR));
+		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_REAR));
+		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEAR));
+		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_NECK));
+		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
+		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));
+		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
+		
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
 			{
-				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
 			}
 			else
 			{
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HEAD) : 0);
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
 			}
 			
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			if (_activeChar.getShieldSkinOption() > 0 && getShieldOption(_activeChar.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			}
 			
-			if (!Config.FAKE_ARMORS)
+			if (_activeChar.getArmorSkinOption() > 0 && getArmorOption(_activeChar.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getChestId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+			}
+			else
 			{
 				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
 				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
 				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
 				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
 			}
+			
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
+			
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
+			}
 			else
 			{
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES) : 0);
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST) : _fakeArmorItemId);
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS) : 0);
-				writeD(_fakeArmorObjectId == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET) : 0);
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
 			}
 			
+			if (_activeChar.getHairSkinOption() > 0 && getHairOption(_activeChar.getHairSkinOption()) != null)
+			{
+				writeD(getHairOption(_activeChar.getHairSkinOption()).getHairId() != 0 ? getHairOption(_activeChar.getHairSkinOption()).getHairId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
+			}
+			
+			if (_activeChar.getFaceSkinOption() > 0 && getFaceOption(_activeChar.getFaceSkinOption()) != null)
+			{
+				writeD(getFaceOption(_activeChar.getFaceSkinOption()).getFaceId() != 0 ? getFaceOption(_activeChar.getFaceSkinOption()).getFaceId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+			}
+			
+		}
+		else
+		{
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
 			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
 			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
 			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
 			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
 		}
-		else
-		{
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_DHAIR));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_REAR));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEAR));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_NECK));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RFINGER));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LFINGER));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HEAD));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND) : (_activeChar.getDressMeData().getWeapId() == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND) : _activeChar.getDressMeData().getWeapId()));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES) : (_activeChar.getDressMeData().getGlovesId() == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES) : _activeChar.getDressMeData().getGlovesId()));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST) : (_activeChar.getDressMeData().getChestId() == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST) : _activeChar.getDressMeData().getChestId()));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS) : (_activeChar.getDressMeData().getLegsId() == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS) : _activeChar.getDressMeData().getLegsId()));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET) : (_activeChar.getDressMeData().getBootsId() == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET) : _activeChar.getDressMeData().getBootsId()));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND) : (_activeChar.getDressMeData().getWeapId() == 0 ? _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND) : _activeChar.getDressMeData().getWeapId()));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));
-			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FACE));
-			
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_DHAIR));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_REAR));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEAR));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_NECK));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : (_activeChar.getDressMeData().getWeapId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : _activeChar.getDressMeData().getWeapId()));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES) : (_activeChar.getDressMeData().getGlovesId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES) : _activeChar.getDressMeData().getGlovesId()));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST) : (_activeChar.getDressMeData().getChestId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST) : _activeChar.getDressMeData().getChestId()));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS) : (_activeChar.getDressMeData().getLegsId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS) : _activeChar.getDressMeData().getLegsId()));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET) : (_activeChar.getDressMeData().getBootsId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET) : _activeChar.getDressMeData().getBootsId()));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
-			writeD(_activeChar.getDressMeData() == null ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : (_activeChar.getDressMeData().getWeapId() == 0 ? _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND) : _activeChar.getDressMeData().getWeapId()));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
-			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
-			
-		}
 		
 		writeH(0x00);
 		writeH(0x00);
@@ -407,6 +462,31 @@
 		}
 	}
 	
+	public SkinPackage getArmorOption(int option)
+	{
+		return (DressMeData.getInstance().getArmorSkinsPackage(option));
+	}
+	
+	public SkinPackage getWeaponOption(int option)
+	{
+		return DressMeData.getInstance().getWeaponSkinsPackage(option);
+	}
+	
+	public SkinPackage getHairOption(int option)
+	{
+		return DressMeData.getInstance().getHairSkinsPackage(option);
+	}
+	
+	public SkinPackage getFaceOption(int option)
+	{
+		return DressMeData.getInstance().getFaceSkinsPackage(option);
+	}
+	
+	public SkinPackage getShieldOption(int option)
+	{
+		return DressMeData.getInstance().getShieldSkinsPackage(option);
+	}
+	
 	@Override
 	public String getType()
 	{
diff --git src/l2jorion/game/templates/L2Item.java src/l2jorion/game/templates/L2Item.java
index 7cc35b9..7372f22 100644
--- src/l2jorion/game/templates/L2Item.java
+++ src/l2jorion/game/templates/L2Item.java
@@ -29,6 +29,7 @@
 import javolution.util.FastList;
 import javolution.util.FastMap;
 import l2jorion.Config;
+import l2jorion.game.datatables.sql.ItemTable;
 import l2jorion.game.model.L2Character;
 import l2jorion.game.model.L2Effect;
 import l2jorion.game.model.L2Skill;
@@ -648,7 +649,21 @@
 		return (getItemType() == L2EtcItemType.QUEST);
 	}
 	
-	public String getItemIcon(int itemId)
+	public static String getItemNameById(int itemId)
+	{
+		L2Item item = ItemTable.getInstance().getTemplate(itemId);
+		
+		String itemName = "NoName";
+		
+		if (itemId != 0)
+		{
+			itemName = item.getName();
+		}
+		
+		return itemName;
+	}
+	
+	public static String getItemIcon(int itemId)
 	{
 		if (_Icons != null && !_Icons.isEmpty())
 		{
