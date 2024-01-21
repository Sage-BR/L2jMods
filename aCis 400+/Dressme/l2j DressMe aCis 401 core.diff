### Eclipse Workspace Patch 1.0
#P aCis_gameserver401
diff --git server/gameserver/data/html/dressme/allskins.htm server/gameserver/data/html/dressme/allskins.htm
new file mode 100644
index 0000000..f46a2bd
--- /dev/null
+++ server/gameserver/data/html/dressme/allskins.htm
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
diff --git server/gameserver/data/html/dressme/index.htm server/gameserver/data/html/dressme/index.htm
new file mode 100644
index 0000000..1175fed
--- /dev/null
+++ server/gameserver/data/html/dressme/index.htm
@@ -0,0 +1,26 @@
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
diff --git server/gameserver/data/html/dressme/myskins.htm server/gameserver/data/html/dressme/myskins.htm
new file mode 100644
index 0000000..381164c
--- /dev/null
+++ server/gameserver/data/html/dressme/myskins.htm
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
diff --git server/gameserver/data/xml/dressme.xml server/gameserver/data/xml/dressme.xml
new file mode 100644
index 0000000..b8c5ef5
--- /dev/null
+++ server/gameserver/data/xml/dressme.xml
@@ -0,0 +1,18 @@
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
diff --git config/players.properties config/players.properties
index 8943505..4b6076c 100644
--- config/players.properties
+++ config/players.properties
@@ -257,4 +257,17 @@
 MaxBuffsAmount = 20
 
 # Store buffs/debuffs on user logout. Default: True
-StoreSkillCooltime = True
\ No newline at end of file
+StoreSkillCooltime = True
+
+# DressMe system.
+AllowDressMeSystem = True
+
+# DressMe Command
+DressMeCommand = dressme
+
+# Only for premium account
+AllowDressMeForPremiumOnly = False
+
+
+# Players won't see the skins in Olympiad
+AllowDressMeInOly = True
\ No newline at end of file
diff --git java/Base/Skin/DressMeData.java java/Base/Skin/DressMeData.java
new file mode 100644
index 0000000..cdf1053
--- /dev/null
+++ java/Base/Skin/DressMeData.java
@@ -0,0 +1,216 @@
+package Base.Skin;
+
+import java.util.HashMap;
+import java.util.Map;
+import java.util.logging.Logger;
+
+import net.sf.l2j.commons.data.StatSet;
+
+import org.w3c.dom.Document;
+import org.w3c.dom.NamedNodeMap;
+import org.w3c.dom.Node;
+
+import Base.Xml.IXmlReader;
+
+
+
+public class DressMeData implements IXmlReader
+{
+	private static final Logger LOG = Logger.getLogger(DressMeData.class.getName());
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
+						final StatSet set = new StatSet();
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
diff --git java/Base/Skin/SkinPackage.java java/Base/Skin/SkinPackage.java
new file mode 100644
index 0000000..ff40f74
--- /dev/null
+++ java/Base/Skin/SkinPackage.java
@@ -0,0 +1,104 @@
+package Base.Skin;
+
+import net.sf.l2j.commons.data.StatSet;
+
+
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
+	public SkinPackage(StatSet set)
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
diff --git java/Base/Xml/IXmlReader.java java/Base/Xml/IXmlReader.java
new file mode 100644
index 0000000..044830c
--- /dev/null
+++ java/Base/Xml/IXmlReader.java
@@ -0,0 +1,577 @@
+
+package Base.Xml;
+
+import java.io.File;
+import java.io.FileFilter;
+import java.util.logging.Logger;
+
+import javax.xml.parsers.DocumentBuilder;
+import javax.xml.parsers.DocumentBuilderFactory;
+
+import net.sf.l2j.commons.data.StatSet;
+
+import net.sf.l2j.gameserver.GameServer;
+
+
+import org.w3c.dom.Document;
+import org.w3c.dom.NamedNodeMap;
+import org.w3c.dom.Node;
+import org.xml.sax.ErrorHandler;
+import org.xml.sax.SAXParseException;
+
+
+
+public interface IXmlReader
+{
+	static final Logger LOG = Logger.getLogger(GameServer.class.getName());
+	
+	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
+	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
+	
+	static final XMLFilter XML_FILTER = new XMLFilter();
+	
+	public void load();
+	
+	default void parseDatapackFile(String path)
+	{
+		parseFile(new File(".", path));
+	}
+	
+	default void parseFile(File f)
+	{
+		if (!getCurrentFileFilter().accept(f))
+		{
+			LOG.warning("{}: Could not parse {} is not a file or it doesn't exist!");
+			return;
+		}
+		
+		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
+		dbf.setNamespaceAware(true);
+		dbf.setValidating(false);
+		dbf.setIgnoringComments(true);
+		
+		try
+		{
+			dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
+			final DocumentBuilder db = dbf.newDocumentBuilder();
+			db.setErrorHandler(new XMLErrorHandler());
+			parseDocument(db.parse(f), f);
+		}
+		catch (SAXParseException e)
+		{
+			LOG.warning("{}: Could not parse file {} at line {}, column {}");
+			return;
+		}
+		catch (Exception e)
+		{
+			LOG.warning("{}: Could not parse file {}");
+			return;
+		}
+	}
+	
+	default boolean parseDirectory(File file)
+	{
+		return parseDirectory(file, false);
+	}
+	
+	default boolean parseDirectory(String path)
+	{
+		return parseDirectory(new File(path), false);
+	}
+	
+	default boolean parseDirectory(String path, boolean recursive)
+	{
+		return parseDirectory(new File(path), recursive);
+	}
+	
+	default boolean parseDirectory(File dir, boolean recursive)
+	{
+		if (!dir.exists())
+		{
+			LOG.warning("{}: Folder {} doesn't exist!");
+			return false;
+		}
+		
+		final File[] files = dir.listFiles();
+		if (files != null)
+		{
+			for (File f : files)
+			{
+				if (recursive && f.isDirectory())
+				{
+					parseDirectory(f, recursive);
+				}
+				else if (getCurrentFileFilter().accept(f))
+				{
+					parseFile(f);
+				}
+			}
+		}
+		return true;
+	}
+	
+	default boolean parseDatapackDirectory(String path, boolean recursive)
+	{
+		return parseDirectory(new File(".", path), recursive);
+	}
+	
+	default void parseDocument(Document doc, File f)
+	{
+		parseDocument(doc);
+	}
+	
+	default void parseDocument(Document doc)
+	{
+		LOG.warning("{}: Parser not implemented!");
+	}
+	
+	default Boolean parseBoolean(Node node, Boolean defaultValue)
+	{
+		return node != null ? Boolean.valueOf(node.getNodeValue()) : defaultValue;
+	}
+	
+	default Boolean parseBoolean(Node node)
+	{
+		return parseBoolean(node, null);
+	}
+	
+	default Boolean parseBoolean(NamedNodeMap attrs, String name)
+	{
+		return parseBoolean(attrs.getNamedItem(name));
+	}
+	
+	default Boolean parseBoolean(NamedNodeMap attrs, String name, Boolean defaultValue)
+	{
+		return parseBoolean(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	default Byte parseByte(Node node, Byte defaultValue)
+	{
+		return node != null ? Byte.valueOf(node.getNodeValue()) : defaultValue;
+	}
+	
+	default StatSet parseAttributes(Node node)
+	{
+		final NamedNodeMap attrs = node.getAttributes();
+		final StatSet map = new StatSet();
+		for (int i = 0; i < attrs.getLength(); i++)
+		{
+			final Node att = attrs.item(i);
+			map.set(att.getNodeName(), att.getNodeValue());
+		}
+		return map;
+	}
+	
+	/**
+	 * Parses a byte value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Byte parseByte(Node node)
+	{
+		return parseByte(node, null);
+	}
+	
+	/**
+	 * Parses a byte value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Byte parseByte(NamedNodeMap attrs, String name)
+	{
+		return parseByte(attrs.getNamedItem(name));
+	}
+	
+	/**
+	 * Parses a byte value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Byte parseByte(NamedNodeMap attrs, String name, Byte defaultValue)
+	{
+		return parseByte(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	/**
+	 * Parses a short value.
+	 * @param node the node to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Short parseShort(Node node, Short defaultValue)
+	{
+		return node != null ? Short.valueOf(node.getNodeValue()) : defaultValue;
+	}
+	
+	/**
+	 * Parses a short value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Short parseShort(Node node)
+	{
+		return parseShort(node, null);
+	}
+	
+	/**
+	 * Parses a short value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Short parseShort(NamedNodeMap attrs, String name)
+	{
+		return parseShort(attrs.getNamedItem(name));
+	}
+	
+	/**
+	 * Parses a short value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Short parseShort(NamedNodeMap attrs, String name, Short defaultValue)
+	{
+		return parseShort(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	/**
+	 * Parses an int value.
+	 * @param node the node to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default int parseInt(Node node, Integer defaultValue)
+	{
+		return node != null ? Integer.parseInt(node.getNodeValue()) : defaultValue;
+	}
+	
+	/**
+	 * Parses an int value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default int parseInt(Node node)
+	{
+		return parseInt(node, -1);
+	}
+	
+	/**
+	 * Parses an integer value.
+	 * @param node the node to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Integer parseInteger(Node node, Integer defaultValue)
+	{
+		return node != null ? Integer.valueOf(node.getNodeValue()) : defaultValue;
+	}
+	
+	/**
+	 * Parses an integer value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Integer parseInteger(Node node)
+	{
+		return parseInteger(node, null);
+	}
+	
+	/**
+	 * Parses an integer value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Integer parseInteger(NamedNodeMap attrs, String name)
+	{
+		return parseInteger(attrs.getNamedItem(name));
+	}
+	
+	/**
+	 * Parses an integer value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Integer parseInteger(NamedNodeMap attrs, String name, Integer defaultValue)
+	{
+		return parseInteger(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	/**
+	 * Parses a long value.
+	 * @param node the node to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Long parseLong(Node node, Long defaultValue)
+	{
+		return node != null ? Long.valueOf(node.getNodeValue()) : defaultValue;
+	}
+	
+	/**
+	 * Parses a long value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Long parseLong(Node node)
+	{
+		return parseLong(node, null);
+	}
+	
+	/**
+	 * Parses a long value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Long parseLong(NamedNodeMap attrs, String name)
+	{
+		return parseLong(attrs.getNamedItem(name));
+	}
+	
+	/**
+	 * Parses a long value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Long parseLong(NamedNodeMap attrs, String name, Long defaultValue)
+	{
+		return parseLong(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	/**
+	 * Parses a float value.
+	 * @param node the node to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Float parseFloat(Node node, Float defaultValue)
+	{
+		return node != null ? Float.valueOf(node.getNodeValue()) : defaultValue;
+	}
+	
+	/**
+	 * Parses a float value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Float parseFloat(Node node)
+	{
+		return parseFloat(node, null);
+	}
+	
+	/**
+	 * Parses a float value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Float parseFloat(NamedNodeMap attrs, String name)
+	{
+		return parseFloat(attrs.getNamedItem(name));
+	}
+	
+	/**
+	 * Parses a float value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Float parseFloat(NamedNodeMap attrs, String name, Float defaultValue)
+	{
+		return parseFloat(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	/**
+	 * Parses a double value.
+	 * @param node the node to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Double parseDouble(Node node, Double defaultValue)
+	{
+		return node != null ? Double.valueOf(node.getNodeValue()) : defaultValue;
+	}
+	
+	/**
+	 * Parses a double value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Double parseDouble(Node node)
+	{
+		return parseDouble(node, null);
+	}
+	
+	/**
+	 * Parses a double value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default Double parseDouble(NamedNodeMap attrs, String name)
+	{
+		return parseDouble(attrs.getNamedItem(name));
+	}
+	
+	/**
+	 * Parses a double value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default Double parseDouble(NamedNodeMap attrs, String name, Double defaultValue)
+	{
+		return parseDouble(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	/**
+	 * Parses a string value.
+	 * @param node the node to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default String parseString(Node node, String defaultValue)
+	{
+		return node != null ? node.getNodeValue() : defaultValue;
+	}
+	
+	/**
+	 * Parses a string value.
+	 * @param node the node to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default String parseString(Node node)
+	{
+		return parseString(node, null);
+	}
+	
+	/**
+	 * Parses a string value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null, the value of the parsed node, otherwise null
+	 */
+	default String parseString(NamedNodeMap attrs, String name)
+	{
+		return parseString(attrs.getNamedItem(name));
+	}
+	
+	/**
+	 * Parses a string value.
+	 * @param attrs the attributes
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null, the value of the parsed node, otherwise the default value
+	 */
+	default String parseString(NamedNodeMap attrs, String name, String defaultValue)
+	{
+		return parseString(attrs.getNamedItem(name), defaultValue);
+	}
+	
+	/**
+	 * Parses an enumerated value.
+	 * @param <T> the enumerated type
+	 * @param node the node to parse
+	 * @param clazz the class of the enumerated
+	 * @param defaultValue the default value
+	 * @return if the node is not null and the node value is valid the parsed value, otherwise the default value
+	 */
+	default <T extends Enum<T>> T parseEnum(Node node, Class<T> clazz, T defaultValue)
+	{
+		if (node == null)
+		{
+			return defaultValue;
+		}
+		
+		try
+		{
+			return Enum.valueOf(clazz, node.getNodeValue());
+		}
+		catch (IllegalArgumentException e)
+		{
+			LOG.warning("Invalid value specified for node: {} specified value: {} should be enum value of \"{}\" using default value: {}");
+			return defaultValue;
+		}
+	}
+	
+	/**
+	 * Parses an enumerated value.
+	 * @param <T> the enumerated type
+	 * @param node the node to parse
+	 * @param clazz the class of the enumerated
+	 * @return if the node is not null and the node value is valid the parsed value, otherwise null
+	 */
+	default <T extends Enum<T>> T parseEnum(Node node, Class<T> clazz)
+	{
+		return parseEnum(node, clazz, null);
+	}
+	
+	/**
+	 * Parses an enumerated value.
+	 * @param <T> the enumerated type
+	 * @param attrs the attributes
+	 * @param clazz the class of the enumerated
+	 * @param name the name of the attribute to parse
+	 * @return if the node is not null and the node value is valid the parsed value, otherwise null
+	 */
+	default <T extends Enum<T>> T parseEnum(NamedNodeMap attrs, Class<T> clazz, String name)
+	{
+		return parseEnum(attrs.getNamedItem(name), clazz);
+	}
+	
+	/**
+	 * Parses an enumerated value.
+	 * @param <T> the enumerated type
+	 * @param attrs the attributes
+	 * @param clazz the class of the enumerated
+	 * @param name the name of the attribute to parse
+	 * @param defaultValue the default value
+	 * @return if the node is not null and the node value is valid the parsed value, otherwise the default value
+	 */
+	default <T extends Enum<T>> T parseEnum(NamedNodeMap attrs, Class<T> clazz, String name, T defaultValue)
+	{
+		return parseEnum(attrs.getNamedItem(name), clazz, defaultValue);
+	}
+	
+	/**
+	 * Gets the current file filter.
+	 * @return the current file filter
+	 */
+	default FileFilter getCurrentFileFilter()
+	{
+		return XML_FILTER;
+	}
+	
+	static class XMLErrorHandler implements ErrorHandler
+	{
+		@Override
+		public void warning(SAXParseException e) throws SAXParseException
+		{
+			throw e;
+		}
+		
+		@Override
+		public void error(SAXParseException e) throws SAXParseException
+		{
+			throw e;
+		}
+		
+		@Override
+		public void fatalError(SAXParseException e) throws SAXParseException
+		{
+			throw e;
+		}
+	}
+}
diff --git java/Base/Xml/XMLFilter.java java/Base/Xml/XMLFilter.java
new file mode 100644
index 0000000..faa1805
--- /dev/null
+++ java/Base/Xml/XMLFilter.java
@@ -0,0 +1,17 @@
+package Base.Xml;
+
+import java.io.File;
+import java.io.FileFilter;
+
+public class XMLFilter implements FileFilter
+{
+	@Override
+	public boolean accept(File f)
+	{
+		if ((f == null) || !f.isFile())
+		{
+			return false;
+		}
+		return f.getName().toLowerCase().endsWith(".xml");
+	}
+}
diff --git java/net/sf/l2j/Config.java java/net/sf/l2j/Config.java
index 65daaa6..cdb2437 100644
--- java/net/sf/l2j/Config.java
+++ java/net/sf/l2j/Config.java
@@ -344,6 +344,15 @@
 	public static boolean ALLOW_DELEVEL;
 	public static int DEATH_PENALTY_CHANCE;
 	
+	
+			public static boolean ALLOW_DRESS_ME_SYSTEM;
+			public static String DRESS_ME_COMMAND;
+			public static boolean ALLOW_DRESS_ME_FOR_PREMIUM;
+			public static boolean ALLOW_DRESS_ME_IN_OLY;	
+	
+	
+	
+	
 	/** Inventory & WH */
 	public static int INVENTORY_MAXIMUM_NO_DWARF;
 	public static int INVENTORY_MAXIMUM_DWARF;
@@ -972,6 +981,11 @@
 		ALLOW_DELEVEL = players.getProperty("AllowDelevel", true);
 		DEATH_PENALTY_CHANCE = players.getProperty("DeathPenaltyChance", 20);
 		
+						ALLOW_DRESS_ME_SYSTEM = players.getProperty("AllowDressMeSystem", false);
+						DRESS_ME_COMMAND = players.getProperty("DressMeCommand", "dressme");
+						ALLOW_DRESS_ME_FOR_PREMIUM = players.getProperty("AllowDressMeForPremiumOnly", false);
+						ALLOW_DRESS_ME_IN_OLY = players.getProperty("AllowDressMeInOly", false);	
+		
 		INVENTORY_MAXIMUM_NO_DWARF = players.getProperty("MaximumSlotsForNoDwarf", 80);
 		INVENTORY_MAXIMUM_DWARF = players.getProperty("MaximumSlotsForDwarf", 100);
 		INVENTORY_MAXIMUM_PET = players.getProperty("MaximumSlotsForPet", 12);
diff --git java/net/sf/l2j/gameserver/GameServer.java java/net/sf/l2j/gameserver/GameServer.java
index e682cc6..eb72c0f 100644
--- java/net/sf/l2j/gameserver/GameServer.java
+++ java/net/sf/l2j/gameserver/GameServer.java
@@ -80,6 +80,7 @@
 import net.sf.l2j.gameserver.handler.SkillHandler;
 import net.sf.l2j.gameserver.handler.TargetHandler;
 import net.sf.l2j.gameserver.handler.UserCommandHandler;
+import net.sf.l2j.gameserver.handler.VoicedCommandHandler;
 import net.sf.l2j.gameserver.idfactory.IdFactory;
 import net.sf.l2j.gameserver.model.World;
 import net.sf.l2j.gameserver.model.boat.BoatGiranTalking;
@@ -87,6 +88,7 @@
 import net.sf.l2j.gameserver.model.boat.BoatInnadrilTour;
 import net.sf.l2j.gameserver.model.boat.BoatRunePrimeval;
 import net.sf.l2j.gameserver.model.boat.BoatTalkingGludin;
+import net.sf.l2j.gameserver.model.item.kind.Item;
 import net.sf.l2j.gameserver.model.olympiad.Olympiad;
 import net.sf.l2j.gameserver.model.olympiad.OlympiadGameManager;
 import net.sf.l2j.gameserver.network.GameClient;
@@ -102,6 +104,8 @@
 import net.sf.l2j.util.DeadLockDetector;
 import net.sf.l2j.util.IPv4Filter;
 
+import Base.Skin.DressMeData;
+
 public class GameServer
 {
 	private static final CLogger LOGGER = new CLogger(GameServer.class.getName());
@@ -272,10 +276,21 @@
 		LOGGER.info("Loaded {} skill handlers.", SkillHandler.getInstance().size());
 		LOGGER.info("Loaded {} target handlers.", TargetHandler.getInstance().size());
 		LOGGER.info("Loaded {} user command handlers.", UserCommandHandler.getInstance().size());
-		
+		LOGGER.info("Loaded {} user VoicedCommandHandler handlers.", VoicedCommandHandler.getInstance().size());				
 		StringUtil.printSection("System");
 		Runtime.getRuntime().addShutdownHook(Shutdown.getInstance());
 		
+		
+						Item.LoadAllIcons();
+						
+						if (Config.ALLOW_DRESS_ME_SYSTEM)
+					{
+							StringUtil.printSection("Dress Me / Skins");
+							DressMeData.getInstance();
+						}		
+		
+		
+		
 		if (Config.DEADLOCK_DETECTOR)
 		{
 			LOGGER.info("Deadlock detector is enabled. Timer: {}s.", Config.DEADLOCK_CHECK_INTERVAL);
diff --git java/net/sf/l2j/gameserver/data/xml/ItemData.java java/net/sf/l2j/gameserver/data/xml/ItemData.java
index a6f5da2..549a501 100644
--- java/net/sf/l2j/gameserver/data/xml/ItemData.java
+++ java/net/sf/l2j/gameserver/data/xml/ItemData.java
@@ -1,12 +1,14 @@
 package net.sf.l2j.gameserver.data.xml;
 
 import java.io.File;
+import java.lang.System.Logger.Level;
 import java.util.HashMap;
 import java.util.Map;
 
 import net.sf.l2j.commons.logging.CLogger;
 
 import net.sf.l2j.gameserver.data.DocumentItem;
+import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
 import net.sf.l2j.gameserver.model.item.kind.Armor;
 import net.sf.l2j.gameserver.model.item.kind.EtcItem;
 import net.sf.l2j.gameserver.model.item.kind.Item;
@@ -88,6 +90,41 @@
 		return SingletonHolder.INSTANCE;
 	}
 	
+	
+	
+		public ItemInstance createDummyItem(final int itemId)
+		{
+			final Item item = getTemplate(itemId);
+			
+			if (item == null)
+			{
+				return null;
+			}
+			
+			ItemInstance temp = new ItemInstance(0, item);
+			
+			try
+			{
+				temp = new ItemInstance(0, itemId);
+			}
+			catch (final ArrayIndexOutOfBoundsException e)
+			{
+		
+					e.printStackTrace();
+				
+			}
+			
+			if (temp.getItem() == null)
+			{
+				LOGGER.warn(Level.WARNING, "ItemTable: Item Template missing for Id: {}" + " " + itemId);
+			}
+			
+			return temp;
+		}	
+	
+	
+	
+	
 	private static class SingletonHolder
 	{
 		protected static final ItemData INSTANCE = new ItemData();
diff --git java/net/sf/l2j/gameserver/handler/BypassHandler.java java/net/sf/l2j/gameserver/handler/BypassHandler.java
new file mode 100644
index 0000000..a6aa0d1
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/BypassHandler.java
@@ -0,0 +1,69 @@
+package net.sf.l2j.gameserver.handler;
+
+import java.util.HashMap;
+import java.util.Map;
+import java.util.logging.Logger;
+
+import net.sf.l2j.Config;
+
+
+
+/**
+ * @author Dwight
+ */
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
+	
+		
+	}
+	
+	public void registerBypassHandler(IBypassHandler handler)
+	{
+		String[] ids = handler.getBypassHandlersList();
+		for (int i = 0; i < ids.length; i++)
+		{
+			if (Config.PACKET_HANDLER_DEBUG)
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
+		if (Config.PACKET_HANDLER_DEBUG)
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
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/handler/CustomBypassHandler.java java/net/sf/l2j/gameserver/handler/CustomBypassHandler.java
new file mode 100644
index 0000000..aba39f5
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/CustomBypassHandler.java
@@ -0,0 +1,69 @@
+package net.sf.l2j.gameserver.handler;
+
+import java.util.HashMap;
+import java.util.Map;
+
+import net.sf.l2j.gameserver.handler.usercommandhandlers.DressMe;
+import net.sf.l2j.gameserver.model.actor.Player;
+
+
+
+
+public class CustomBypassHandler
+{
+
+	
+	private static CustomBypassHandler _instance = null;
+	private final Map<String, ICustomByPassHandler> _handlers;
+	
+	private CustomBypassHandler()
+	{
+		_handlers = new HashMap<>();
+		
+		
+		registerCustomBypassHandler(new DressMe());
+	}
+	
+	public static CustomBypassHandler getInstance()
+	{
+		if (_instance == null)
+		{
+			_instance = new CustomBypassHandler();
+		}
+		
+		return _instance;
+	}
+	
+	public void registerCustomBypassHandler(final ICustomByPassHandler handler)
+	{
+		for (final String s : handler.getByPassCommands())
+		{
+			_handlers.put(s, handler);
+		}
+	}
+	
+	public void handleBypass(final Player player, final String command)
+	{
+		String cmd = "";
+		String params = "";
+		final int iPos = command.indexOf(" ");
+		if (iPos != -1)
+		{
+			cmd = command.substring(7, iPos);
+			params = command.substring(iPos + 1);
+		}
+		else
+		{
+			cmd = command.substring(7);
+		}
+		final ICustomByPassHandler ch = _handlers.get(cmd);
+		if (ch != null)
+		{
+			ch.handleCommand(cmd, player, params);
+		}
+		else
+		{
+
+		}
+	}
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/handler/IBypassHandler.java java/net/sf/l2j/gameserver/handler/IBypassHandler.java
new file mode 100644
index 0000000..54af486
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/IBypassHandler.java
@@ -0,0 +1,13 @@
+package net.sf.l2j.gameserver.handler;
+
+import net.sf.l2j.gameserver.model.actor.Player;
+
+/**
+ * @author Dwight
+ */
+public interface IBypassHandler
+{
+	public boolean handleBypass(String bypass, Player activeChar);
+	
+	public String[] getBypassHandlersList();
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/handler/ICustomByPassHandler.java java/net/sf/l2j/gameserver/handler/ICustomByPassHandler.java
new file mode 100644
index 0000000..6f4f5ca
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/ICustomByPassHandler.java
@@ -0,0 +1,10 @@
+package net.sf.l2j.gameserver.handler;
+
+import net.sf.l2j.gameserver.model.actor.Player;
+
+public interface ICustomByPassHandler
+{
+	public String[] getByPassCommands();
+	
+	public void handleCommand(String command, Player player, String parameters);
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/handler/IVoicedCommandHandler.java java/net/sf/l2j/gameserver/handler/IVoicedCommandHandler.java
new file mode 100644
index 0000000..3b0f12a
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/IVoicedCommandHandler.java
@@ -0,0 +1,10 @@
+package net.sf.l2j.gameserver.handler;
+
+import net.sf.l2j.gameserver.model.actor.Player;
+
+public interface IVoicedCommandHandler
+{
+	public boolean useVoicedCommand(String command, Player activeChar, String params);
+	
+	public String[] getVoicedCommandList();
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/handler/VoicedCommandHandler.java java/net/sf/l2j/gameserver/handler/VoicedCommandHandler.java
new file mode 100644
index 0000000..35df73c
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/VoicedCommandHandler.java
@@ -0,0 +1,51 @@
+package net.sf.l2j.gameserver.handler;
+
+import java.util.HashMap;
+import java.util.Map;
+
+import net.sf.l2j.gameserver.handler.usercommandhandlers.DressMe;
+
+public class VoicedCommandHandler
+{
+	private final Map<Integer, IVoicedCommandHandler> _datatable = new HashMap<>();
+	
+	public static VoicedCommandHandler getInstance()
+	{
+		return SingletonHolder._instance;
+	}
+	
+	protected VoicedCommandHandler()
+	{
+		//Codigos para colocar aqui dentro
+		registerHandler(new DressMe());
+		
+	}
+	
+	public void registerHandler(IVoicedCommandHandler handler)
+	{
+		String[] ids = handler.getVoicedCommandList();
+		
+		for (int i = 0; i < ids.length; i++)
+			_datatable.put(ids[i].hashCode(), handler);
+	}
+	
+	public IVoicedCommandHandler getHandler(String voicedCommand)
+	{
+		String command = voicedCommand;
+		
+		if (voicedCommand.indexOf(" ") != -1)
+			command = voicedCommand.substring(0, voicedCommand.indexOf(" "));
+		
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
+		protected static final VoicedCommandHandler _instance = new VoicedCommandHandler();
+	}
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/handler/chathandlers/ChatAll.java java/net/sf/l2j/gameserver/handler/chathandlers/ChatAll.java
index a707ce5..f8cc4fb 100644
--- java/net/sf/l2j/gameserver/handler/chathandlers/ChatAll.java
+++ java/net/sf/l2j/gameserver/handler/chathandlers/ChatAll.java
@@ -1,8 +1,12 @@
 package net.sf.l2j.gameserver.handler.chathandlers;
 
+import java.util.StringTokenizer;
+
 import net.sf.l2j.gameserver.enums.FloodProtector;
 import net.sf.l2j.gameserver.enums.SayType;
 import net.sf.l2j.gameserver.handler.IChatHandler;
+import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
+import net.sf.l2j.gameserver.handler.VoicedCommandHandler;
 import net.sf.l2j.gameserver.model.actor.Player;
 import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
 
@@ -19,11 +23,39 @@
 		if (!player.getClient().performAction(FloodProtector.GLOBAL_CHAT))
 			return;
 		
-		final CreatureSay cs = new CreatureSay(player, type, text);
-		for (Player knownPlayer : player.getKnownTypeInRadius(Player.class, 1250))
-			knownPlayer.sendPacket(cs);
+		boolean vcd_used = false;
+		if (text.startsWith("."))
+		{
+			StringTokenizer st = new StringTokenizer(text);
+			IVoicedCommandHandler vch;
+			String command = "";
+			if (st.countTokens() > 1)
+			{
+				command = st.nextToken().substring(1);
+				target = text.substring(command.length() + 2);
+				vch = VoicedCommandHandler.getInstance().getHandler(command);
+			}
+			else
+			{
+				command = text.substring(1);
+				vch = VoicedCommandHandler.getInstance().getHandler(command);
+			}
 		
-		player.sendPacket(cs);
+			if (vch != null)
+			{
+				vch.useVoicedCommand(command, player, text);
+				vcd_used = true;
+
+			}
+		}
+		if (!vcd_used)
+		{
+			final CreatureSay cs = new CreatureSay(player, type, text);
+			for (Player knownPlayer : player.getKnownTypeInRadius(Player.class, 1250))
+				knownPlayer.sendPacket(cs);
+
+			player.sendPacket(cs);
+		}
 	}
 	
 	@Override
diff --git java/net/sf/l2j/gameserver/handler/usercommandhandlers/DressMe.java java/net/sf/l2j/gameserver/handler/usercommandhandlers/DressMe.java
new file mode 100644
index 0000000..56c1309
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/usercommandhandlers/DressMe.java
@@ -0,0 +1,93 @@
+package net.sf.l2j.gameserver.handler.usercommandhandlers;
+
+import java.text.SimpleDateFormat;
+import java.util.Date;
+
+import net.sf.l2j.Config;
+import net.sf.l2j.gameserver.data.cache.HtmCache;
+import net.sf.l2j.gameserver.handler.ICustomByPassHandler;
+import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
+import net.sf.l2j.gameserver.model.actor.Player;
+import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
+
+
+
+public class DressMe implements IVoicedCommandHandler, ICustomByPassHandler
+{
+	private static String[] _voicedCommands =
+	{
+		Config.DRESS_ME_COMMAND
+	};
+	
+	@Override
+	public boolean useVoicedCommand(String command, Player activeChar, String target)
+	{
+
+		
+		if (command.startsWith(Config.DRESS_ME_COMMAND))
+		{
+			showHtm(activeChar);
+		}
+		
+
+		return true;
+	}
+	
+	private static void showHtm(Player player)
+	{
+		NpcHtmlMessage htm = new NpcHtmlMessage(1);
+		String text = HtmCache.getInstance().getHtm("data/html/dressme/index.htm");
+		
+		htm.setHtml(text);
+		
+		{
+			htm.replace("%time%", sdf.format(new Date(System.currentTimeMillis())));
+			htm.replace("%dat%", (new SimpleDateFormat("dd/MM/yyyy")).format(new Date(System.currentTimeMillis())));
+			
+		}
+		
+		player.sendPacket(htm);
+	}
+	
+	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
+	
+	@Override
+	public String[] getVoicedCommandList()
+	{
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
+	public void handleCommand(String command, Player player, String parameters)
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
+	}
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/model/actor/Player.java java/net/sf/l2j/gameserver/model/actor/Player.java
index 023516a..71de9a3 100644
--- java/net/sf/l2j/gameserver/model/actor/Player.java
+++ java/net/sf/l2j/gameserver/model/actor/Player.java
@@ -14,6 +14,7 @@
 import java.util.Set;
 import java.util.concurrent.ConcurrentHashMap;
 import java.util.concurrent.ConcurrentSkipListMap;
+import java.util.concurrent.CopyOnWriteArrayList;
 import java.util.concurrent.Future;
 import java.util.concurrent.ScheduledFuture;
 import java.util.concurrent.atomic.AtomicInteger;
@@ -162,6 +163,7 @@
 import net.sf.l2j.gameserver.network.serverpackets.ExOlympiadMode;
 import net.sf.l2j.gameserver.network.serverpackets.ExServerPrimitive;
 import net.sf.l2j.gameserver.network.serverpackets.ExSetCompassZoneCode;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
 import net.sf.l2j.gameserver.network.serverpackets.ExStorageMaxCount;
 import net.sf.l2j.gameserver.network.serverpackets.GetOnVehicle;
 import net.sf.l2j.gameserver.network.serverpackets.HennaInfo;
@@ -176,6 +178,7 @@
 import net.sf.l2j.gameserver.network.serverpackets.ObservationReturn;
 import net.sf.l2j.gameserver.network.serverpackets.PartySmallWindowUpdate;
 import net.sf.l2j.gameserver.network.serverpackets.PetInventoryUpdate;
+import net.sf.l2j.gameserver.network.serverpackets.PlaySound;
 import net.sf.l2j.gameserver.network.serverpackets.PledgeShowMemberListDelete;
 import net.sf.l2j.gameserver.network.serverpackets.PledgeShowMemberListUpdate;
 import net.sf.l2j.gameserver.network.serverpackets.PrivateStoreListBuy;
@@ -223,6 +226,8 @@
 import net.sf.l2j.gameserver.taskmanager.ShadowItemTaskManager;
 import net.sf.l2j.gameserver.taskmanager.WaterTaskManager;
 
+import Base.Skin.DressMeData;
+
 /**
  * This class represents a player in the world.<br>
  * There is always a client-thread connected to this (except if a player-store is activated upon logout).
@@ -238,6 +243,41 @@
 	private static final String RESTORE_SKILL_SAVE = "SELECT skill_id,skill_level,effect_count,effect_cur_time, reuse_delay, systime, restore_type FROM character_skills_save WHERE char_obj_id=? AND class_index=? ORDER BY buff_index ASC";
 	private static final String DELETE_SKILL_SAVE = "DELETE FROM character_skills_save WHERE char_obj_id=? AND class_index=?";
 	
+	
+	
+	private int _armorSkinOption = 0;
+	private int _weaponSkinOption = 0;
+	private int _hairSkinOption = 0;
+	private int _faceSkinOption = 0;
+	private int _shieldSkinOption = 0;
+
+
+	private boolean isTryingSkin = false;
+	private boolean isTryingSkinPremium = false;
+	private List<Integer> _armorSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _weaponSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _hairSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _faceSkins = new CopyOnWriteArrayList<>();
+	private List<Integer> _shieldSkins = new CopyOnWriteArrayList<>();
+	
+	
+		private static final String INSERT_OR_UPDATE_CHARACTER_DRESSME_DATA = "INSERT INTO characters_dressme_data (obj_Id, armor_skins, armor_skin_option, weapon_skins, weapon_skin_option, hair_skins, hair_skin_option, face_skins, face_skin_option) VALUES (?,?,?,?,?,?,?,?,?) "
+		+ "ON DUPLICATE KEY UPDATE obj_Id=?, armor_skins=?, armor_skin_option=?, weapon_skins=?, weapon_skin_option=?, hair_skins=?, hair_skin_option=?, face_skins=?, face_skin_option=?, shield_skins=?, shield_skin_option=?";
+	private static final String RESTORE_CHARACTER_DRESSME_DATA = "SELECT obj_Id, armor_skins, armor_skin_option, weapon_skins, weapon_skin_option, hair_skins, hair_skin_option, face_skins, face_skin_option, shield_skins, shield_skin_option FROM characters_dressme_data WHERE obj_id=?";
+	
+	
+	
+	
+	
+	
+	
+
+	
+	
+	
+	
+	
+	
 	private static final String INSERT_CHARACTER = "INSERT INTO characters (account_name,obj_Id,char_name,level,maxHp,curHp,maxCp,curCp,maxMp,curMp,face,hairStyle,hairColor,sex,exp,sp,karma,pvpkills,pkkills,clanid,race,classid,deletetime,cancraft,title,accesslevel,online,isin7sdungeon,clan_privs,wantspeace,base_class,nobless,power_grade) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 	private static final String UPDATE_CHARACTER = "UPDATE characters SET level=?,maxHp=?,curHp=?,maxCp=?,curCp=?,maxMp=?,curMp=?,face=?,hairStyle=?,hairColor=?,sex=?,heading=?,x=?,y=?,z=?,exp=?,expBeforeDeath=?,sp=?,karma=?,pvpkills=?,pkkills=?,clanid=?,race=?,classid=?,deletetime=?,title=?,accesslevel=?,online=?,isin7sdungeon=?,clan_privs=?,wantspeace=?,base_class=?,onlinetime=?,punish_level=?,punish_timer=?,nobless=?,power_grade=?,subpledge=?,lvl_joined_academy=?,apprentice=?,sponsor=?,varka_ketra_ally=?,clan_join_expiry_time=?,clan_create_expiry_time=?,char_name=?,death_penalty_level=? WHERE obj_id=?";
 	private static final String RESTORE_CHARACTER = "SELECT * FROM characters WHERE obj_id=?";
@@ -1221,6 +1261,29 @@
 		if (item.getItem() instanceof Weapon)
 			item.unChargeAllShots();
 		
+		
+		
+		if (getWeaponSkinOption() > 0 && DressMeData.getInstance().getWeaponSkinsPackage(getWeaponSkinOption()) != null)
+		{
+			sendMessage("At first you must to remove a skin of weapon.");
+			sendPacket(new ExShowScreenMessage("At first you must to remove a skin of weapon.", 2000));
+			sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+			return;
+		}
+		
+		if (getShieldSkinOption() > 0 && DressMeData.getInstance().getShieldSkinsPackage(getShieldSkinOption()) != null)
+		{
+			sendMessage("At first you must to remove a skin of weapon.");
+			sendPacket(new ExShowScreenMessage("At first you must to remove a skin of weapon.", 2000));
+			sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+			return;
+		}
+		
+		
+		
+		
+		
+		
 		if (isEquipped)
 		{
 			if (item.getEnchantLevel() > 0)
@@ -4474,6 +4537,11 @@
 	 */
 	public synchronized void store(boolean storeActiveEffects)
 	{
+		if (!isTryingSkin() || !isTryingSkinPremium())
+		{
+			storeDressMeData();
+		}
+		
 		storeCharBase();
 		storeCharSub();
 		storeEffect(storeActiveEffects);
@@ -7407,4 +7475,462 @@
 		
 		return gms;
 	}
+	
+	
+	@SuppressWarnings("resource")
+	private synchronized void storeDressMeData()
+	{
+		
+		Connection con = null;
+		try
+		{
+			con = ConnectionPool.getConnection();
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
+				LOGGER.warn("Could not store storeDressMeData():");
+				e.printStackTrace();
+			
+		}
+		finally
+		{
+			
+		}
+	}
+
+
+
+
+	@SuppressWarnings("resource")
+	private void restoreDressMeData()
+	{
+
+		Connection con = null;
+		try
+		{
+			con = ConnectionPool.getConnection();
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
+			LOGGER.warn("Could not restore char data:");
+			e.printStackTrace();
+		}
+		finally
+		{
+			
+		}
+	}
+
+
+
+
+
+
+
+
+
+
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
+	
+	
 }
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/model/item/kind/Item.java java/net/sf/l2j/gameserver/model/item/kind/Item.java
index ca4ba59..87cebc7 100644
--- java/net/sf/l2j/gameserver/model/item/kind/Item.java
+++ java/net/sf/l2j/gameserver/model/item/kind/Item.java
@@ -1,5 +1,8 @@
 package net.sf.l2j.gameserver.model.item.kind;
 
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.HashMap;
@@ -7,7 +10,9 @@
 import java.util.Map;
 
 import net.sf.l2j.commons.data.StatSet;
+import net.sf.l2j.commons.pool.ConnectionPool;
 
+import net.sf.l2j.gameserver.data.xml.ItemData;
 import net.sf.l2j.gameserver.enums.items.ActionType;
 import net.sf.l2j.gameserver.enums.items.ArmorType;
 import net.sf.l2j.gameserver.enums.items.CrystalType;
@@ -21,6 +26,7 @@
 import net.sf.l2j.gameserver.model.actor.Summon;
 import net.sf.l2j.gameserver.model.holder.IntIntHolder;
 import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
+import net.sf.l2j.gameserver.network.GameClient;
 import net.sf.l2j.gameserver.network.SystemMessageId;
 import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;
 import net.sf.l2j.gameserver.scripting.Quest;
@@ -33,6 +39,11 @@
  */
 public abstract class Item
 {
+			private static Map<Integer, String> _Icons = null;
+			private static GameClient _client;
+	
+	
+	
 	private static final Map<String, Integer> SLOTS = new HashMap<>();
 	{
 		SLOTS.put("chest", SLOT_CHEST);
@@ -543,4 +554,88 @@
 	{
 		return _questEvents;
 	}
+	
+	
+	public static String getItemIcon(int itemId)
+	{
+		if (_Icons != null && !_Icons.isEmpty())
+		{
+			return _Icons.get(itemId);
+		}
+		return null;
+	}
+	
+	public static void LoadAllIcons()
+	{
+		loadIcons();
+	}
+	
+	
+	
+	public static String getItemNameById(int itemId)
+	{
+	     Item item = ItemData.getInstance().getTemplate(itemId);
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
+	
+	
+	
+	
+	/**
+	 * @return The client owner of this char.
+	 */
+	public GameClient getClient()
+	{
+		return _client;
+	}
+	
+	public void setClient(GameClient client)
+	{
+		_client = client;
+	}
+	
+	
+	@SuppressWarnings("resource")
+	private static void loadIcons()
+	{
+		_Icons = new HashMap<>();
+	
+		Connection con = null;
+		try
+		{
+			con = ConnectionPool.getConnection();
+			PreparedStatement statement = con.prepareStatement("SELECT * FROM item_icons");
+			ResultSet rset = statement.executeQuery();
+			
+			while (rset.next())
+			{
+				int itemId = rset.getInt("itemId");
+				String itemIcon = rset.getString("itemIcon");
+				_Icons.put(itemId, itemIcon);
+			}
+			
+			rset.close();
+			statement.close();
+		}
+		catch (Exception e)
+		{
+			
+		}
+		finally
+		{
+			
+		}
+	}
+	
+	
+	
 }
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java
index d8adb02..77ac398 100644
--- java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java
+++ java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java
@@ -1,26 +1,44 @@
 package net.sf.l2j.gameserver.network.clientpackets;
 
+import java.sql.Date;
+import java.text.SimpleDateFormat;
+import java.util.List;
 import java.util.StringTokenizer;
 import java.util.logging.Logger;
+import java.util.stream.Collectors;
+import java.util.stream.Stream;
+
+import net.sf.l2j.commons.pool.ThreadPool;
 
 import net.sf.l2j.Config;
 import net.sf.l2j.gameserver.communitybbs.CommunityBoard;
+import net.sf.l2j.gameserver.data.cache.HtmCache;
 import net.sf.l2j.gameserver.data.manager.HeroManager;
 import net.sf.l2j.gameserver.data.xml.AdminData;
+import net.sf.l2j.gameserver.data.xml.ItemData;
 import net.sf.l2j.gameserver.enums.FloodProtector;
+import net.sf.l2j.gameserver.enums.items.WeaponType;
 import net.sf.l2j.gameserver.handler.AdminCommandHandler;
+import net.sf.l2j.gameserver.handler.CustomBypassHandler;
 import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
 import net.sf.l2j.gameserver.model.World;
 import net.sf.l2j.gameserver.model.WorldObject;
 import net.sf.l2j.gameserver.model.actor.Npc;
 import net.sf.l2j.gameserver.model.actor.Player;
 import net.sf.l2j.gameserver.model.actor.instance.OlympiadManagerNpc;
+import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
+import net.sf.l2j.gameserver.model.item.kind.Item;
 import net.sf.l2j.gameserver.model.olympiad.OlympiadManager;
 import net.sf.l2j.gameserver.network.SystemMessageId;
 import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
 import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
+import net.sf.l2j.gameserver.network.serverpackets.PlaySound;
 import net.sf.l2j.gameserver.scripting.QuestState;
 
+import Base.Skin.DressMeData;
+import Base.Skin.SkinPackage;
+
 public final class RequestBypassToServer extends L2GameClientPacket
 {
 	private static final Logger GMAUDIT_LOG = Logger.getLogger("gmaudit");
@@ -98,6 +116,412 @@
 			html.disableValidation();
 			player.sendPacket(html);
 		}
+		
+		
+		else if (_command.startsWith("custom_"))
+		{
+			Player player2 = getClient().getPlayer();
+			CustomBypassHandler.getInstance().handleBypass(player2, _command);
+		}
+		
+		
+		else if (_command.startsWith("dressme"))
+		{
+			if (!Config.ALLOW_DRESS_ME_IN_OLY && player.isInOlympiadMode())
+			{
+				player.sendMessage("DressMe can't be used on The Olympiad game.");
+				return;
+			}
+			
+			StringTokenizer st = new StringTokenizer(_command, " ");
+			st.nextToken();
+			if (!st.hasMoreTokens())
+			{
+				showDressMeMainPage(player);
+				return;
+			}
+			int page = Integer.parseInt(st.nextToken());
+			
+			if (!st.hasMoreTokens())
+			{
+				showDressMeMainPage(player);
+				return;
+			}
+			String next = st.nextToken();
+			if (next.startsWith("skinlist"))
+			{
+				String type = st.nextToken();
+				showSkinList(player, type, page);
+			}
+			else if (next.startsWith("myskinlist"))
+			{
+				
+				showMySkinList(player, page);
+			}
+			if (next.equals("clean"))
+			{
+				String type = st.nextToken();
+				
+				if (player.isTryingSkin())
+				{
+					player.sendMessage("You can't do this while trying a skin.");
+					player.sendPacket(new ExShowScreenMessage("You can't do this while trying a skin.", 2000));
+					player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+					showDressMeMainPage(player);
+					return;
+				}
+				
+				switch (type.toLowerCase())
+				{
+					case "armor":
+						player.setArmorSkinOption(0);
+						break;
+					case "weapon":
+						player.setWeaponSkinOption(0);
+						break;
+					case "hair":
+						player.setHairSkinOption(0);
+						break;
+					case "face":
+						player.setFaceSkinOption(0);
+						break;
+					case "shield":
+						player.setShieldSkinOption(0);
+						break;
+				}
+				
+				player.broadcastUserInfo();
+				showMySkinList(player, page);
+			}
+			else if (next.startsWith("buyskin"))
+			{
+				if (!st.hasMoreTokens())
+				{
+					showDressMeMainPage(player);
+					return;
+				}
+				
+				int skinId = Integer.parseInt(st.nextToken());
+				String type = st.nextToken();
+				int itemId = Integer.parseInt(st.nextToken());
+				
+				SkinPackage sp = null;
+				
+				switch (type.toLowerCase())
+				{
+					case "armor":
+						sp = DressMeData.getInstance().getArmorSkinsPackage(skinId);
+						break;
+					case "weapon":
+						sp = DressMeData.getInstance().getWeaponSkinsPackage(skinId);
+						
+						if (player.getActiveWeaponItem() == null)
+						{
+							player.sendMessage("You can't buy this skin without a weapon.");
+							player.sendPacket(new ExShowScreenMessage("You can't buy this skin without a weapon.", 2000));
+							player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+							showSkinList(player, type, page);
+							return;
+						}
+						
+						ItemInstance skinWeapon = null;
+						if (ItemData.getInstance().getTemplate(itemId) != null)
+						{
+							skinWeapon = ItemData.getInstance().createDummyItem(itemId);
+							
+							if (!checkWeapons(player, skinWeapon, WeaponType.BOW, WeaponType.BOW) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.SWORD, WeaponType.SWORD) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.BLUNT, WeaponType.BLUNT) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.DAGGER, WeaponType.DAGGER) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.POLE, WeaponType.POLE) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.DUAL, WeaponType.DUAL) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.FIST, WeaponType.FIST) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
+							{
+								player.sendMessage("This skin is not suitable for your weapon type.");
+								player.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000));
+								player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showSkinList(player, type, page);
+								return;
+							}
+						}
+						break;
+					case "hair":
+						sp = DressMeData.getInstance().getHairSkinsPackage(skinId);
+						break;
+					case "face":
+						sp = DressMeData.getInstance().getFaceSkinsPackage(skinId);
+						break;
+					case "shield":
+						sp = DressMeData.getInstance().getShieldSkinsPackage(skinId);
+						if (player.getActiveWeaponItem() == null)
+						{
+							player.sendMessage("You can't buy this skin without a weapon.");
+							player.sendPacket(new ExShowScreenMessage("You can't buy this skin without a weapon.", 2000));
+							player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+							showSkinList(player, type, page);
+							return;
+						}
+						
+						ItemInstance skinShield = null;
+						if (ItemData.getInstance().getTemplate(itemId) != null)
+						{
+							skinShield = ItemData.getInstance().createDummyItem(itemId);
+							
+							if (!checkWeapons(player, skinShield, WeaponType.BOW, WeaponType.BOW) //
+								|| !checkWeapons(player, skinShield, WeaponType.POLE, WeaponType.POLE) //
+								|| !checkWeapons(player, skinShield, WeaponType.DUAL, WeaponType.DUAL) //
+								|| !checkWeapons(player, skinShield, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+								|| !checkWeapons(player, skinShield, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+								|| !checkWeapons(player, skinShield, WeaponType.FIST, WeaponType.FIST) //
+								|| !checkWeapons(player, skinShield, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
+							{
+								player.sendMessage("This skin is not suitable for your weapon type.");
+								player.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000));
+								player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showSkinList(player, type, page);
+								return;
+							}
+						}
+						break;
+				}
+				
+				if (sp == null)
+				{
+					player.sendMessage("There is no such skin.");
+					player.sendPacket(new ExShowScreenMessage("There is no such skin.", 2000));
+					player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+					showSkinList(player, type, page);
+					return;
+				}
+				
+				
+				if (player.destroyItemByItemId("dressme", sp.getPriceId(), sp.getPriceCount(), player, true))
+				{
+					player.sendMessage("You have successfully purchased " + sp.getName() + " skin.");
+					player.sendPacket(new ExShowScreenMessage("You have successfully purchased " + sp.getName() + " skin.", 2000));
+					
+					switch (type.toLowerCase())
+					{
+						case "armor":
+							player.buyArmorSkin(skinId);
+							player.setArmorSkinOption(skinId);
+							break;
+						case "weapon":
+							player.buyWeaponSkin(skinId);
+							player.setWeaponSkinOption(skinId);
+							break;
+						case "hair":
+							player.buyHairSkin(skinId);
+							player.setHairSkinOption(skinId);
+							break;
+						case "face":
+							player.buyFaceSkin(skinId);
+							player.setFaceSkinOption(skinId);
+							break;
+						case "shield":
+							player.buyShieldSkin(skinId);
+							player.setShieldSkinOption(skinId);
+							break;
+					}
+					
+					player.broadcastUserInfo();
+				}
+				showSkinList(player, type, page);
+			}
+			else if (next.startsWith("tryskin"))
+			{
+				
+				int skinId = Integer.parseInt(st.nextToken());
+				
+				String type = st.nextToken();
+				
+				if (player.isTryingSkin())
+				{
+					player.sendMessage("You are already trying a skin.");
+					player.sendPacket(new ExShowScreenMessage("You are already trying a skin.", 2000));
+					player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+					showSkinList(player, type, page);
+					return;
+				}
+				
+				player.setIsTryingSkin(true);
+				
+				int oldArmorSkinId = player.getArmorSkinOption();
+				int oldWeaponSkinId = player.getWeaponSkinOption();
+				int oldHairSkinId = player.getHairSkinOption();
+				int oldFaceSkinId = player.getFaceSkinOption();
+				int oldShieldSkinId = player.getShieldSkinOption();
+				
+				switch (type.toLowerCase())
+				{
+					case "armor":
+						player.setArmorSkinOption(skinId);
+						break;
+					case "weapon":
+						player.setWeaponSkinOption(skinId);
+						break;
+					case "hair":
+						player.setHairSkinOption(skinId);
+						break;
+					case "face":
+						player.setFaceSkinOption(skinId);
+						break;
+					case "shield":
+						
+						player.setShieldSkinOption(skinId);
+						
+						break;
+				}
+				
+				player.broadcastUserInfo();
+				showSkinList(player, type, page);
+				
+				ThreadPool.schedule(() ->
+				{
+					switch (type.toLowerCase())
+					{
+						case "armor":
+							player.setArmorSkinOption(oldArmorSkinId);
+							break;
+						case "weapon":
+							player.setWeaponSkinOption(oldWeaponSkinId);
+							break;
+						case "hair":
+							player.setHairSkinOption(oldHairSkinId);
+							break;
+						case "face":
+							player.setFaceSkinOption(oldFaceSkinId);
+							break;
+						case "shield":
+							player.setShieldSkinOption(oldShieldSkinId);
+							break;
+					}
+					
+					player.broadcastUserInfo();
+					player.setIsTryingSkin(false);
+				}, 5000);
+			}
+			else if (next.startsWith("setskin"))
+			{
+				int id = Integer.parseInt(st.nextToken());
+				String type = st.nextToken();
+				int itemId = Integer.parseInt(st.nextToken());
+				
+				if (player.isTryingSkin())
+				{
+					player.sendMessage("You can't do this while trying skins.");
+					player.sendPacket(new ExShowScreenMessage("You can't do this while trying skins.", 2000));
+					player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+					showMySkinList(player, page);
+					return;
+				}
+				
+				if (type.toLowerCase().contains("armor") && player.hasEquippedArmorSkin(String.valueOf(id)) || type.toLowerCase().contains("weapon") && player.hasEquippedWeaponSkin(String.valueOf(id))
+					|| type.toLowerCase().contains("hair") && player.hasEquippedHairSkin(String.valueOf(id)) || type.toLowerCase().contains("face") && player.hasEquippedFaceSkin(String.valueOf(id)))
+				{
+					player.sendMessage("You are already equipped this skin.");
+					player.sendPacket(new ExShowScreenMessage("You are already equipped this skin.", 2000));
+					player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+					showMySkinList(player, page);
+					return;
+				}
+				
+				switch (type.toLowerCase())
+				{
+					case "armor":
+						player.setArmorSkinOption(id);
+						break;
+					case "weapon":
+						if (player.getActiveWeaponItem() == null)
+						{
+							player.sendMessage("You can't use this skin without a weapon.");
+							player.sendPacket(new ExShowScreenMessage("You can't use this skin without a weapon.", 2000));
+							player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+							showMySkinList(player, page);
+							return;
+						}
+						
+						ItemInstance skinWeapon = null;
+						if (ItemData.getInstance().getTemplate(itemId) != null)
+						{
+							skinWeapon = ItemData.getInstance().createDummyItem(itemId);
+							
+							if (!checkWeapons(player, skinWeapon, WeaponType.BOW, WeaponType.BOW) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.SWORD, WeaponType.SWORD) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.BLUNT, WeaponType.BLUNT) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.DAGGER, WeaponType.DAGGER) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.POLE, WeaponType.POLE) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.DUAL, WeaponType.DUAL) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.FIST, WeaponType.FIST) //
+								|| !checkWeapons(player, skinWeapon, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
+							{
+								player.sendMessage("This skin is not suitable for your weapon type.");
+								player.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000));
+								player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showMySkinList(player, page);
+								return;
+							}
+							
+							player.setWeaponSkinOption(id);
+						}
+						break;
+					case "hair":
+						player.setHairSkinOption(id);
+						break;
+					case "face":
+						player.setFaceSkinOption(id);
+						break;
+					case "shield":
+						if (player.getActiveWeaponItem() == null)
+						{
+							player.sendMessage("You can't use this skin without a weapon.");
+							player.sendPacket(new ExShowScreenMessage("You can't use this skin without a weapon.", 2000));
+							player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+							showMySkinList(player, page);
+							return;
+						}
+						
+						ItemInstance skinShield = null;
+						if (ItemData.getInstance().getTemplate(itemId) != null)
+						{
+							skinShield = ItemData.getInstance().createDummyItem(itemId);
+							
+							if (!checkWeapons(player, skinShield, WeaponType.BOW, WeaponType.BOW) //
+								|| !checkWeapons(player, skinShield, WeaponType.POLE, WeaponType.POLE) //
+								|| !checkWeapons(player, skinShield, WeaponType.DUAL, WeaponType.DUAL) //
+								|| !checkWeapons(player, skinShield, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+								|| !checkWeapons(player, skinShield, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+								|| !checkWeapons(player, skinShield, WeaponType.FIST, WeaponType.FIST) //
+								|| !checkWeapons(player, skinShield, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
+							{
+								player.sendMessage("This skin is not suitable for your weapon type.");
+								player.sendPacket(new ExShowScreenMessage("This skin is not suitable for your weapon type.", 2000));
+								player.sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+								showMySkinList(player, page);
+								return;
+							}
+							
+							player.setShieldSkinOption(id);
+						}
+						
+						break;
+				}
+				
+				player.broadcastUserInfo();
+				showMySkinList(player, page);
+			}
+
+		
+		}		
+		
+		
+		
+		
 		else if (_command.startsWith("npc_"))
 		{
 			if (!player.validateBypass(_command))
@@ -186,4 +610,263 @@
 			player.enterOlympiadObserverMode(arenaId);
 		}
 	}
+	
+	public static String getItemNameById(int itemId)
+	{
+		Item item = ItemData.getInstance().getTemplate(itemId);
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
+	
+	public static void showDressMeMainPage(Player player)
+	{
+		NpcHtmlMessage htm = new NpcHtmlMessage(1);
+		String text = HtmCache.getInstance().getHtm("data/html/dressme/index.htm");
+		
+		htm.setHtml(text);
+		
+		{
+			htm.replace("%time%", sdf.format(new Date(System.currentTimeMillis())));
+			htm.replace("%dat%", (new SimpleDateFormat("dd/MM/yyyy")).format(new Date(System.currentTimeMillis())));
+			
+		}
+		
+		player.sendPacket(htm);
+	}
+	
+	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
+	
+	private static void showSkinList(Player player, String type, int page)
+	{
+		NpcHtmlMessage html = new NpcHtmlMessage(1);
+		
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
+		{
+			case "armor":
+				tempList = DressMeData.getInstance().getArmorSkinOptions().values().stream().filter(s -> !player.hasArmorSkin(s.getId())).collect(Collectors.toList());
+				break;
+			case "weapon":
+				tempList = DressMeData.getInstance().getWeaponSkinOptions().values().stream().filter(s -> !player.hasWeaponSkin(s.getId())).collect(Collectors.toList());
+				break;
+			case "hair":
+				tempList = DressMeData.getInstance().getHairSkinOptions().values().stream().filter(s -> !player.hasHairSkin(s.getId())).collect(Collectors.toList());
+				break;
+			case "face":
+				tempList = DressMeData.getInstance().getFaceSkinOptions().values().stream().filter(s -> !player.hasFaceSkin(s.getId())).collect(Collectors.toList());
+				break;
+			case "shield":
+				tempList = DressMeData.getInstance().getShieldSkinOptions().values().stream().filter(s -> !player.hasShieldSkin(s.getId())).collect(Collectors.toList());
+				break;
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
+				sb.append("<td width=32 align=center>" + "<button width=32 height=32 back=" + Item.getItemIcon(itemId) + " fore=" + Item.getItemIcon(itemId) + ">" + "</td>");
+				sb.append("<td width=124>" + sp.getName() + "<br1> <font color=999999>Price:</font> <font color=339966>" + Item.getItemNameById(sp.getPriceId()) + "</font> (<font color=LEVEL>" + sp.getPriceCount() + "</font>)</td>");
+				sb.append("<td align=center width=65>" + "<button value=\"Buy\" action=\"bypass -h dressme " + page + " buyskin  " + sp.getId() + " " + type + " " + itemId + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" + "</td>");
+				sb.append("<td align=center width=65>" + "<button value=\"Try\" action=\"bypass -h dressme " + page + " tryskin  " + sp.getId() + " " + type + "\" width=65 height=19 back=L2UI_ch3.smallbutton2_over fore=L2UI_ch3.smallbutton2>" + "</td>");
+				
+				sb.append("</tr></table>");
+				sb.append("<img src=\"L2UI.Squaregray\" width=\"300\" height=\"1\">");
+				shown++;
+			}
+		}
+		
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
+	private static void showMySkinList(Player player, int page)
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
+		List<SkinPackage> faces = DressMeData.getInstance().getFaceSkinOptions().values().stream().filter(s -> player.hasFaceSkin(s.getId())).collect(Collectors.toList());
+		List<SkinPackage> shield = DressMeData.getInstance().getShieldSkinOptions().values().stream().filter(s -> player.hasShieldSkin(s.getId())).collect(Collectors.toList());
+		
+		List<SkinPackage> list = Stream.concat(armors.stream(), weapons.stream()).collect(Collectors.toList());
+		shield.stream().collect(Collectors.toList());
+		List<SkinPackage> list2 = Stream.concat(hairs.stream(), shield.stream()).collect(Collectors.toList());
+		List<SkinPackage> list3 = faces.stream().collect(Collectors.toList());
+		
+		List<SkinPackage> allLists = Stream.concat(list.stream(),Stream.concat(list2.stream(), list3.stream())).collect(Collectors.toList());
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
+				sb.append("<td width=32 align=center>" + "<button width=32 height=32 back=" + Item.getItemIcon(itemId) + " fore=" + Item.getItemIcon(itemId) + ">" + "</td>");
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
+	public static boolean checkWeapons(Player player, ItemInstance skin, WeaponType weapon1, WeaponType weapon2)
+	{
+		if (player.getActiveWeaponItem().getItemType() == weapon1 && skin.getItem().getItemType() != weapon2)
+		{
+			return false;
+		}
+		
+		return true;
+	}	
+	
 }
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/network/serverpackets/CharInfo.java java/net/sf/l2j/gameserver/network/serverpackets/CharInfo.java
index f83da89..76b6fb9 100644
--- java/net/sf/l2j/gameserver/network/serverpackets/CharInfo.java
+++ java/net/sf/l2j/gameserver/network/serverpackets/CharInfo.java
@@ -9,6 +9,9 @@
 import net.sf.l2j.gameserver.model.actor.Summon;
 import net.sf.l2j.gameserver.model.actor.instance.Cubic;
 
+import Base.Skin.DressMeData;
+import Base.Skin.SkinPackage;
+
 public class CharInfo extends L2GameServerPacket
 {
 	private final Player _player;
@@ -41,18 +44,85 @@
 		writeD(_player.getAppearance().getSex().ordinal());
 		writeD((_player.getClassIndex() == 0) ? _player.getClassId().getId() : _player.getBaseClass());
 		
		writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIRALL));
		writeD(_player.getInventory().getItemIdFrom(Paperdoll.HEAD));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.LHAND));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.CHEST));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.LEGS));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.FEET));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.CLOAK));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIR));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.FACE));
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			if (_player.getWeaponSkinOption() > 0 && getWeaponOption(_player.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() : _player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			
+			if (_player.getShieldSkinOption() > 0 && getShieldOption(_player.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_player.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_player.getShieldSkinOption()).getShieldId() : _player.getInventory().getItemIdFrom(Paperdoll.LHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.LHAND));
+			}
+			
+			if (_player.getArmorSkinOption() > 0 && getArmorOption(_player.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_player.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getGlovesId() : _player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getChestId() : _player.getInventory().getItemIdFrom(Paperdoll.CHEST));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getLegsId() : _player.getInventory().getItemIdFrom(Paperdoll.LEGS));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getFeetId() : _player.getInventory().getItemIdFrom(Paperdoll.FEET));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.CHEST));
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.LEGS));
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.FEET));
+			}
+			
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.CLOAK));
+			
+			if (_player.getWeaponSkinOption() > 0 && getWeaponOption(_player.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() : _player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			
+			if (_player.getHairSkinOption() > 0 && getHairOption(_player.getHairSkinOption()) != null)
+			{
+				writeD(getHairOption(_player.getHairSkinOption()).getHairId() != 0 ? getHairOption(_player.getHairSkinOption()).getHairId() : _player.getInventory().getItemIdFrom(Paperdoll.HAIR));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIR));
+			}
+			
+			if (_player.getFaceSkinOption() > 0 && getFaceOption(_player.getFaceSkinOption()) != null)
+			{
+				writeD(getFaceOption(_player.getFaceSkinOption()).getFaceId() != 0 ? getFaceOption(_player.getFaceSkinOption()).getFaceId() : _player.getInventory().getItemIdFrom(Paperdoll.FACE));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.FACE));
+			}
+			
+		}
+		else {
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.LHAND));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.CHEST));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.LEGS));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.FEET));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.CLOAK));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIR));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.FACE));
+		}
 		
 		writeH(0x00);
 		writeH(0x00);
@@ -159,4 +229,29 @@
 		writeD(_player.getAppearance().getTitleColor());
 		writeD(CursedWeaponManager.getInstance().getCurrentStage(_player.getCursedWeaponEquippedId()));
 	}
+	
+	public SkinPackage getArmorOption(int option)
+{
+	return (DressMeData.getInstance().getArmorSkinsPackage(option));
+}
+
+public SkinPackage getWeaponOption(int option)
+{
+	return DressMeData.getInstance().getWeaponSkinsPackage(option);
+}
+
+public SkinPackage getHairOption(int option)
+{
+	return DressMeData.getInstance().getHairSkinsPackage(option);
+}
+
+public SkinPackage getFaceOption(int option)
+{
+	return DressMeData.getInstance().getFaceSkinsPackage(option);
+}
+
+public SkinPackage getShieldOption(int option)
+{
+	return DressMeData.getInstance().getShieldSkinsPackage(option);
+}	
 }
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/network/serverpackets/UserInfo.java java/net/sf/l2j/gameserver/network/serverpackets/UserInfo.java
index bb1ce74..fc49b7a 100644
--- java/net/sf/l2j/gameserver/network/serverpackets/UserInfo.java
+++ java/net/sf/l2j/gameserver/network/serverpackets/UserInfo.java
@@ -9,6 +9,9 @@
 import net.sf.l2j.gameserver.model.actor.Summon;
 import net.sf.l2j.gameserver.model.actor.instance.Cubic;
 
+import Base.Skin.DressMeData;
+import Base.Skin.SkinPackage;
+
 public class UserInfo extends L2GameServerPacket
 {
 	private final Player _player;
@@ -63,16 +66,88 @@
 		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.RFINGER));
 		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.LFINGER));
 		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.HEAD));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.LHAND));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.GLOVES));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.CHEST));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.LEGS));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.FEET));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.CLOAK));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.HAIR));
-		writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.FACE));
+		
+		
+		
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			if (_player.getWeaponSkinOption() > 0 && getWeaponOption(_player.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
+			}
+			
+			if (_player.getShieldSkinOption() > 0 && getShieldOption(_player.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_player.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_player.getShieldSkinOption()).getShieldId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.LHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.LHAND));
+			}
+			
+			if (_player.getArmorSkinOption() > 0 && getArmorOption(_player.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_player.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getGlovesId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.GLOVES));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getChestId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.CHEST));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getLegsId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.LEGS));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getFeetId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.FEET));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.GLOVES));
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.CHEST));
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.LEGS));
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.FEET));
+			}
+			
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.CLOAK));
+			
+			if (_player.getWeaponSkinOption() > 0 && getWeaponOption(_player.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
+			}
+			
+			if (_player.getHairSkinOption() > 0 && getHairOption(_player.getHairSkinOption()) != null)
+			{
+				writeD(getHairOption(_player.getHairSkinOption()).getHairId() != 0 ? getHairOption(_player.getHairSkinOption()).getHairId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.HAIR));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.HAIR));
+			}
+			
+			if (_player.getFaceSkinOption() > 0 && getFaceOption(_player.getFaceSkinOption()) != null)
+			{
+				writeD(getFaceOption(_player.getFaceSkinOption()).getFaceId() != 0 ? getFaceOption(_player.getFaceSkinOption()).getFaceId() : _player.getInventory().getItemObjectIdFrom(Paperdoll.FACE));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.FACE));
+			}
+			
+		}
+		else {
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.LHAND));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.GLOVES));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.CHEST));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.LEGS));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.FEET));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.CLOAK));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.RHAND));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.HAIR));
+			writeD(_player.getInventory().getItemObjectIdFrom(Paperdoll.FACE));
+		}
+		
+
 		
 		writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIRALL));
 		writeD(_player.getInventory().getItemIdFrom(Paperdoll.REAR));
@@ -81,16 +156,83 @@
 		writeD(_player.getInventory().getItemIdFrom(Paperdoll.RFINGER));
 		writeD(_player.getInventory().getItemIdFrom(Paperdoll.LFINGER));
 		writeD(_player.getInventory().getItemIdFrom(Paperdoll.HEAD));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.LHAND));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.CHEST));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.LEGS));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.FEET));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.CLOAK));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIR));
-		writeD(_player.getInventory().getItemIdFrom(Paperdoll.FACE));
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			if (_player.getWeaponSkinOption() > 0 && getWeaponOption(_player.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() : _player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			
+			if (_player.getShieldSkinOption() > 0 && getShieldOption(_player.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_player.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_player.getShieldSkinOption()).getShieldId() : _player.getInventory().getItemIdFrom(Paperdoll.LHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.LHAND));
+			}
+			
+			if (_player.getArmorSkinOption() > 0 && getArmorOption(_player.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_player.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getGlovesId() : _player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getChestId() : _player.getInventory().getItemIdFrom(Paperdoll.CHEST));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getLegsId() : _player.getInventory().getItemIdFrom(Paperdoll.LEGS));
+				writeD(getArmorOption(_player.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_player.getArmorSkinOption()).getFeetId() : _player.getInventory().getItemIdFrom(Paperdoll.FEET));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.CHEST));
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.LEGS));
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.FEET));
+			}
+			
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.CLOAK));
+			
+			if (_player.getWeaponSkinOption() > 0 && getWeaponOption(_player.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_player.getWeaponSkinOption()).getWeaponId() : _player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			}
+			
+			if (_player.getHairSkinOption() > 0 && getHairOption(_player.getHairSkinOption()) != null)
+			{
+				writeD(getHairOption(_player.getHairSkinOption()).getHairId() != 0 ? getHairOption(_player.getHairSkinOption()).getHairId() : _player.getInventory().getItemIdFrom(Paperdoll.HAIR));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIR));
+			}
+			
+			if (_player.getFaceSkinOption() > 0 && getFaceOption(_player.getFaceSkinOption()) != null)
+			{
+				writeD(getFaceOption(_player.getFaceSkinOption()).getFaceId() != 0 ? getFaceOption(_player.getFaceSkinOption()).getFaceId() : _player.getInventory().getItemIdFrom(Paperdoll.FACE));
+			}
+			else
+			{
+				writeD(_player.getInventory().getItemIdFrom(Paperdoll.FACE));
+			}
+			
+		}
+		else {
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.LHAND));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.GLOVES));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.CHEST));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.LEGS));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.FEET));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.CLOAK));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.RHAND));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.HAIR));
+			writeD(_player.getInventory().getItemIdFrom(Paperdoll.FACE));
+		}
 		
 		writeH(0x00);
 		writeH(0x00);
@@ -214,4 +356,29 @@
 		writeD(_player.getAppearance().getTitleColor());
 		writeD(CursedWeaponManager.getInstance().getCurrentStage(_player.getCursedWeaponEquippedId()));
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
 }
\ No newline at end of file
