diff --git server/game/data/html/dressme/allskins.htm server/game/data/html/dressme/allskins.htm
new file mode 100644
index 0000000..f46a2bd
--- /dev/null
+++ server/game/data/html/dressme/allskins.htm
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
diff --git server/game/data/html/dressme/index.htm server/game/data/html/dressme/index.htm
new file mode 100644
index 0000000..1175fed
--- /dev/null
+++ server/game/data/html/dressme/index.htm
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
diff --git server/game/data/html/dressme/myskins.htm server/game/data/html/dressme/myskins.htm
new file mode 100644
index 0000000..381164c
--- /dev/null
+++ server/game/data/html/dressme/myskins.htm
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
diff --git server/game/data/xml/dressme.xml server/game/data/xml/dressme.xml
new file mode 100644
index 0000000..b8c5ef5
--- /dev/null
+++ server/game/data/xml/dressme.xml
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
diff --git server/game/config/players.properties server/game/config/players.properties
index a3e8e08..c1da93b 100644
--- server/game/config/players.properties
+++ server/game/config/players.properties
@@ -14,6 +14,22 @@
 MpRegenMultiplier = 1.
 CpRegenMultiplier = 1.
 
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
+
+
+


diff --git java/Base/Skin/DressMeData.java java/Base/Skin/DressMeData.java
new file mode 100644
index 0000000..5237d53
--- /dev/null
+++ java/Base/Skin/DressMeData.java
@@ -0,0 +1,216 @@
+package Base.Skin;
+
+import java.util.HashMap;
+import java.util.Map;
+import java.util.logging.Logger;
+
+import com.l2j4team.gameserver.templates.StatsSet;
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
diff --git java/Base/Skin/SkinPackage.java java/Base/Skin/SkinPackage.java
new file mode 100644
index 0000000..9d32fda
--- /dev/null
+++ java/Base/Skin/SkinPackage.java
@@ -0,0 +1,102 @@
+package Base.Skin;
+
+import com.l2j4team.gameserver.templates.StatsSet;
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
diff --git java/Base/Xml/IXmlReader.java java/Base/Xml/IXmlReader.java
new file mode 100644
index 0000000..f6959f7
--- /dev/null
+++ java/Base/Xml/IXmlReader.java
@@ -0,0 +1,575 @@
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
+import com.l2j4team.gameserver.game;
+import com.l2j4team.gameserver.templates.StatsSet;
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
+	static final Logger LOG = Logger.getLogger(game.class.getName());
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
+	default StatsSet parseAttributes(Node node)
+	{
+		final NamedNodeMap attrs = node.getAttributes();
+		final StatsSet map = new StatsSet();
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
index 91fc0f1..2307c74 100644
--- java/net/sf/l2j/Config.java
+++ java/net/sf/l2j/Config.java
@@ -58,6 +58,13 @@
 	public static boolean ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH;
 	public static boolean REMOVE_CASTLE_CIRCLETS;
 	
+	
+	public static boolean ALLOW_DRESS_ME_SYSTEM;
+	public static String DRESS_ME_COMMAND;
+	public static boolean ALLOW_DRESS_ME_FOR_PREMIUM;
+	public static boolean ALLOW_DRESS_ME_IN_OLY;	
+	
+	
 	/** Manor */
 	public static int ALT_MANOR_REFRESH_TIME;
 	public static int ALT_MANOR_REFRESH_MIN;
@@ -1081,6 +1088,15 @@
 		ALT_GAME_DELEVEL = players.getProperty("Delevel", true);
 		DEATH_PENALTY_CHANCE = players.getProperty("DeathPenaltyChance", 20);
 		
+		ALLOW_DRESS_ME_SYSTEM = players.getProperty("AllowDressMeSystem", false);
+		DRESS_ME_COMMAND = players.getProperty("DressMeCommand", "dressme");
+		ALLOW_DRESS_ME_FOR_PREMIUM = players.getProperty("AllowDressMeForPremiumOnly", false);
+		ALLOW_DRESS_ME_IN_OLY = players.getProperty("AllowDressMeInOly", false);
+		
+		
+		
+		
+		
 		INVENTORY_MAXIMUM_NO_DWARF = players.getProperty("MaximumSlotsForNoDwarf", 80);
 		INVENTORY_MAXIMUM_DWARF = players.getProperty("MaximumSlotsForDwarf", 100);
 		INVENTORY_MAXIMUM_QUEST_ITEMS = players.getProperty("MaximumSlotsForQuestItems", 100);
diff --git java/com/l2j4team/gameserver/game.java java/com/l2j4team/gameserver/game.java
index 65bf5ef..ab80e3e 100644
--- java/com/l2j4team/gameserver/game.java
+++ java/com/l2j4team/gameserver/game.java
@@ -83,6 +83,7 @@
 import com.l2j4team.gameserver.instancemanager.ZoneManager;
 import com.l2j4team.gameserver.model.World;
 import com.l2j4team.gameserver.model.entity.Hero;
+import com.l2j4team.gameserver.model.item.kind.Item;
 import com.l2j4team.gameserver.model.olympiad.Olympiad;
 import com.l2j4team.gameserver.model.olympiad.OlympiadGameManager;
 import com.l2j4team.gameserver.model.partymatching.PartyMatchRoomList;
@@ -108,6 +109,9 @@
 import com.l2j4team.util.DeadLockDetector;
 import com.l2j4team.util.IPv4Filter;
 
+import Base.Skin.DressMeData;
+
+
 public class game
 {
 	private static final Logger _log = Logger.getLogger(game.class.getName());
@@ -281,6 +285,17 @@
 			BoatTalkingGludin.load();
 		}
 		
+		Item.LoadAllIcons();
+		
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			StringUtil.printSection("Dress Me / Skins");
+			DressMeData.getInstance();
+		}
+		
+		
+		
+		
 		StringUtil.printSection("Events");
 		DerbyTrackManager.getInstance();
 		LotteryManager.getInstance();
diff --git java/com/l2j4team/gameserver/handler/BypassHandler.java java/com/l2j4team/gameserver/handler/BypassHandler.java
new file mode 100644
index 0000000..2cbae33
--- /dev/null
+++ java/com/l2j4team/gameserver/handler/BypassHandler.java
@@ -0,0 +1,69 @@
+package com.l2j4team.gameserver.handler;
+
+import java.util.HashMap;
+import java.util.Map;
+import java.util.logging.Logger;
+
+import com.l2j4team.Config;
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
diff --git java/com/l2j4team/gameserver/handler/CustomBypassHandler.java java/com/l2j4team/gameserver/handler/CustomBypassHandler.java
new file mode 100644
index 0000000..ce0210b
--- /dev/null
+++ java/com/l2j4team/gameserver/handler/CustomBypassHandler.java
@@ -0,0 +1,69 @@
+
+package com.l2j4team.gameserver.handler;
+
+import java.util.HashMap;
+import java.util.Map;
+
+import com.l2j4team.gameserver.handler.usercommandhandlers.DressMe;
+import com.l2j4team.gameserver.model.actor.instance.Player;
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
diff --git java/com/l2j4team/gameserver/handler/IBypassHandler.java java/com/l2j4team/gameserver/handler/IBypassHandler.java
new file mode 100644
index 0000000..32bc7b4
--- /dev/null
+++ java/com/l2j4team/gameserver/handler/IBypassHandler.java
@@ -0,0 +1,13 @@
+package com.l2j4team.gameserver.handler;
+
+import com.l2j4team.gameserver.model.actor.instance.Player;
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
diff --git java/com/l2j4team/gameserver/handler/ICustomByPassHandler.java java/com/l2j4team/gameserver/handler/ICustomByPassHandler.java
new file mode 100644
index 0000000..6312dc0
--- /dev/null
+++ java/com/l2j4team/gameserver/handler/ICustomByPassHandler.java
@@ -0,0 +1,10 @@
+package com.l2j4team.gameserver.handler;
+
+import com.l2j4team.gameserver.model.actor.instance.Player;
+
+public interface ICustomByPassHandler
+{
+	public String[] getByPassCommands();
+	
+	public void handleCommand(String command, Player player, String parameters);
+}
diff --git java/com/l2j4team/gameserver/handler/VoicedCommandHandler.java java/com/l2j4team/gameserver/handler/VoicedCommandHandler.java
index a1b430a..ca516e6 100644
--- java/com/l2j4team/gameserver/handler/VoicedCommandHandler.java
+++ java/com/l2j4team/gameserver/handler/VoicedCommandHandler.java
@@ -17,6 +17,10 @@
 import java.util.HashMap;
 import java.util.Map;
 
+import com.l2j4team.gameserver.handler.usercommandhandlers.DressMe;
+
+
+
 public class VoicedCommandHandler
 {
 	private final Map<Integer, IVoicedCommandHandler> _datatable = new HashMap<>();
@@ -28,7 +32,7 @@
 
 	protected VoicedCommandHandler()
 	{
-
+		registerHandler(new DressMe());
 	}
 	public void registerHandler(IVoicedCommandHandler handler)
 	{
diff --git java/com/l2j4team/gameserver/handler/usercommandhandlers/DressMe.java java/com/l2j4team/gameserver/handler/usercommandhandlers/DressMe.java
new file mode 100644
index 0000000..4d5948c
--- /dev/null
+++ java/com/l2j4team/gameserver/handler/usercommandhandlers/DressMe.java
@@ -0,0 +1,93 @@
+package com.l2j4team.gameserver.handler.usercommandhandlers;
+
+import java.text.SimpleDateFormat;
+import java.util.Date;
+
+import com.l2j4team.Config;
+import com.l2j4team.gameserver.cache.HtmCache;
+import com.l2j4team.gameserver.handler.ICustomByPassHandler;
+import com.l2j4team.gameserver.handler.IVoicedCommandHandler;
+import com.l2j4team.gameserver.model.actor.instance.Player;
+import com.l2j4team.gameserver.network.serverpackets.NpcHtmlMessage;
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
diff --git java/com/l2j4team/gameserver/model/actor/instance/Player.java java/com/l2j4team/gameserver/model/actor/instance/Player.java
index b2f5469..6f6c777 100644
--- java/com/l2j4team/gameserver/model/actor/instance/Player.java
+++ java/com/l2j4team/gameserver/model/actor/instance/Player.java
@@ -13,6 +13,7 @@
 import java.util.Set;
 import java.util.concurrent.ConcurrentHashMap;
 import java.util.concurrent.ConcurrentSkipListMap;
+import java.util.concurrent.CopyOnWriteArrayList;
 import java.util.concurrent.Future;
 import java.util.concurrent.ScheduledFuture;
 import java.util.concurrent.TimeUnit;
@@ -28,6 +29,7 @@
 import com.l2j4team.Config;
 import com.l2j4team.L2DatabaseFactory;
 import com.l2j4team.gameserver.LoginServerThread;
 import com.l2j4team.gameserver.communitybbs.BB.Forum;
 import com.l2j4team.gameserver.communitybbs.Manager.ForumsBBSManager;
 import com.l2j4team.gameserver.data.ItemTable;
@@ -46,6 +48,7 @@
 import com.l2j4team.gameserver.data.xml.PlayerData;
 import com.l2j4team.gameserver.data.xml.RecipeData;
 import com.l2j4team.gameserver.geoengine.GeoEngine;
 import com.l2j4team.gameserver.handler.IItemHandler;
 import com.l2j4team.gameserver.handler.ItemHandler;
 import com.l2j4team.gameserver.handler.admincommandhandlers.AdminEditChar;
@@ -241,6 +244,13 @@
 import com.l2j4team.gameserver.templates.skills.L2SkillType;
 import com.l2j4team.gameserver.util.Broadcast;
 


 /**
  * This class represents a player in the world.<br>
  * There is always a client-thread connected to this (except if a player-store is activated upon logout).
@@ -306,6 +316,33 @@
 		}
 	}
 	
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
 	private static final String RESTORE_SKILLS_FOR_CHAR = "SELECT skill_id,skill_level FROM character_skills WHERE char_obj_id=? AND class_index=?";
 	private static final String ADD_OR_UPDATE_SKILL = "INSERT INTO character_skills (char_obj_id,skill_id,skill_level,class_index) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE skill_level=?";
 	private static final String DELETE_SKILL_FROM_CHAR = "DELETE FROM character_skills WHERE skill_id=? AND char_obj_id=? AND class_index=?";




@@ -1225,6 +1264,28 @@
        if (item.getItem() instanceof Weapon)
            item.unChargeAllShots();
        
+       
+       
+
+       if (getWeaponSkinOption() > 0 && DressMeData.getInstance().getWeaponSkinsPackage(getWeaponSkinOption()) != null)
+       {
+           sendMessage("At first you must to remove a skin of weapon.");
+           sendPacket(new ExShowScreenMessage("At first you must to remove a skin of weapon.", 2000));
+           sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+           return;
+       }
+       
+       if (getShieldSkinOption() > 0 && DressMeData.getInstance().getShieldSkinsPackage(getShieldSkinOption()) != null)
+       {
+           sendMessage("At first you must to remove a skin of weapon.");
+           sendPacket(new ExShowScreenMessage("At first you must to remove a skin of weapon.", 2000));
+           sendPacket(new PlaySound("ItemSound3.sys_impossible"));
+           return;
+       }
+       
+       
+       
+       
        if (isEquipped)
        {
            if (item.getEnchantLevel() > 0)


@@ -5298,6 +5335,7 @@
 				player.refreshExpertisePenalty();
 				
 				player.restoreFriendList();
+				player.restoreDressMeData();
 				
 				// Retrieve the name and ID of the other characters assigned to this account.
 				PreparedStatement stmt = con.prepareStatement("SELECT obj_Id, char_name FROM characters WHERE account_name=? AND obj_Id<>?");
@@ -5500,6 +5538,16 @@
 	 */
 	public synchronized void store(boolean storeActiveEffects)
 	{
+		
+		
+				if (!isTryingSkin() || !isTryingSkinPremium())
+					{
+						storeDressMeData();
+					}
+		
+		
+		
+		
 		// update client coords, if these look like true
 		if (isInsideRadius(getClientX(), getClientY(), 1000, true))
 			setXYZ(getClientX(), getClientY(), getClientZ());
@@ -10289,4 +10337,544 @@
 			}
 		}
 	}
+	
+	
+	
+	
+	
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
+				_log.warning("Could not store storeDressMeData():");
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
+			_log.warning("Could not restore char data:");
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
	


 }
\ No newline at end of file
diff --git java/com/l2j4team/gameserver/model/item/kind/Item.java java/com/l2j4team/gameserver/model/item/kind/Item.java
index f72530e..2c2edae 100644
--- java/com/l2j4team/gameserver/model/item/kind/Item.java
+++ java/com/l2j4team/gameserver/model/item/kind/Item.java
@@ -1,10 +1,16 @@
 package com.l2j4team.gameserver.model.item.kind;
 
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
 import java.util.ArrayList;
 import java.util.Collections;
+import java.util.HashMap;
 import java.util.List;
+import java.util.Map;
 import java.util.logging.Logger;
 
+import com.l2j4team.L2DatabaseFactory;
 import com.l2j4team.gameserver.data.ItemTable;
 import com.l2j4team.gameserver.model.WorldObject;
 import com.l2j4team.gameserver.model.actor.Creature;
@@ -19,6 +25,7 @@
 import com.l2j4team.gameserver.model.item.type.ItemType;
 import com.l2j4team.gameserver.model.item.type.MaterialType;
 import com.l2j4team.gameserver.model.item.type.WeaponType;
+import com.l2j4team.gameserver.network.L2GameClient;
 import com.l2j4team.gameserver.network.SystemMessageId;
 import com.l2j4team.gameserver.network.serverpackets.SystemMessage;
 import com.l2j4team.gameserver.scripting.Quest;
@@ -28,6 +35,8 @@
 import com.l2j4team.gameserver.skills.conditions.Condition;
 import com.l2j4team.gameserver.templates.StatsSet;
 
+
+
 /**
  * This class contains all informations concerning the item (weapon, armor, etc). Mother class of :
  * <ul>
@@ -38,6 +47,9 @@
  */
 public abstract class Item
 {
+	
+	private static Map<Integer, String> _Icons = null;
+	private static L2GameClient _client;
 	public static final int TYPE1_WEAPON_RING_EARRING_NECKLACE = 0;
 	public static final int TYPE1_SHIELD_ARMOR = 1;
 	public static final int TYPE1_ITEM_QUESTITEM_ADENA = 4;
@@ -577,4 +589,84 @@
 	{
 		return _questEvents;
 	}
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
+	     Item item = ItemTable.getInstance().getTemplate(itemId);
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
+	public L2GameClient getClient()
+	{
+		return _client;
+	}
+	
+	public void setClient(L2GameClient client)
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
+			con = L2DatabaseFactory.getInstance().getConnection();
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
 }
\ No newline at end of file
diff --git java/com/l2j4team/gameserver/network/clientpackets/RequestBypassToServer.java java/com/l2j4team/gameserver/network/clientpackets/RequestBypassToServer.java
index 7e74c2b..94db266 100644
--- java/com/l2j4team/gameserver/network/clientpackets/RequestBypassToServer.java
+++ java/com/l2j4team/gameserver/network/clientpackets/RequestBypassToServer.java
@@ -1,13 +1,23 @@
 package com.l2j4team.gameserver.network.clientpackets;
 
+import java.sql.Date;
+import java.text.SimpleDateFormat;
+import java.util.List;
 import java.util.StringTokenizer;
 import java.util.logging.Level;
 import java.util.logging.Logger;
+import java.util.stream.Collectors;
+import java.util.stream.Stream;
+
+import com.l2j4team.commons.concurrent.ThreadPool;
 
 import com.l2j4team.Config;
+import com.l2j4team.gameserver.cache.HtmCache;
 import com.l2j4team.gameserver.communitybbs.CommunityBoard;
+import com.l2j4team.gameserver.data.ItemTable;
 import com.l2j4team.gameserver.data.xml.AdminData;
 import com.l2j4team.gameserver.handler.AdminCommandHandler;
+import com.l2j4team.gameserver.handler.CustomBypassHandler;
 import com.l2j4team.gameserver.handler.IAdminCommandHandler;
 import com.l2j4team.gameserver.handler.IVoicedCommandHandler;
 import com.l2j4team.gameserver.handler.VoicedCommandHandler;
@@ -17,12 +27,21 @@
 import com.l2j4team.gameserver.model.actor.instance.OlympiadManagerNpc;
 import com.l2j4team.gameserver.model.actor.instance.Player;
 import com.l2j4team.gameserver.model.entity.Hero;
+import com.l2j4team.gameserver.model.item.instance.ItemInstance;
+import com.l2j4team.gameserver.model.item.kind.Item;
+import com.l2j4team.gameserver.model.item.type.WeaponType;
 import com.l2j4team.gameserver.model.olympiad.OlympiadManager;
 import com.l2j4team.gameserver.network.FloodProtectors;
 import com.l2j4team.gameserver.network.FloodProtectors.Action;
 import com.l2j4team.gameserver.network.SystemMessageId;
 import com.l2j4team.gameserver.network.serverpackets.ActionFailed;
+import com.l2j4team.gameserver.network.serverpackets.ExShowScreenMessage;
 import com.l2j4team.gameserver.network.serverpackets.NpcHtmlMessage;
+import com.l2j4team.gameserver.network.serverpackets.PlaySound;
+
+import Base.Skin.DressMeData;
+import Base.Skin.SkinPackage;
+
 
 public final class RequestBypassToServer extends L2GameClientPacket
 {
@@ -55,6 +74,9 @@
 		
 		try
 		{
+			
+
+			
 			if (_command.startsWith("admin_"))
 			{
 				String command = _command.split(" ")[0];
@@ -132,6 +154,413 @@
 				else
 					activeChar.processQuestEvent(str[0], str[1]);
 			}
+			
+			else if (_command.startsWith("custom_"))
+			{
+				Player player = getClient().getActiveChar();
+				CustomBypassHandler.getInstance().handleBypass(player, _command);
+			}
+			
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
+							ItemInstance skinWeapon = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinWeapon = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinWeapon, WeaponType.BOW, WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.SWORD, WeaponType.SWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.BLUNT, WeaponType.BLUNT) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.DAGGER, WeaponType.DAGGER) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.POLE, WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.DUAL, WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.FIST, WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
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
+							ItemInstance skinShield = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinShield = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinShield, WeaponType.BOW, WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.POLE, WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.DUAL, WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.FIST, WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
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
+					ThreadPool.schedule(() ->
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
+							ItemInstance skinWeapon = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinWeapon = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinWeapon, WeaponType.BOW, WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.SWORD, WeaponType.SWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.BLUNT, WeaponType.BLUNT) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.DAGGER, WeaponType.DAGGER) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.POLE, WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.DUAL, WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.FIST, WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinWeapon, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
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
+							ItemInstance skinShield = null;
+							if (ItemTable.getInstance().getTemplate(itemId) != null)
+							{
+								skinShield = ItemTable.getInstance().createDummyItem(itemId);
+								
+								if (!checkWeapons(activeChar, skinShield, WeaponType.BOW, WeaponType.BOW) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.POLE, WeaponType.POLE) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.DUAL, WeaponType.DUAL) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.DUALFIST, WeaponType.DUALFIST) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.BIGSWORD, WeaponType.BIGSWORD) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.FIST, WeaponType.FIST) //
+									|| !checkWeapons(activeChar, skinShield, WeaponType.BIGBLUNT, WeaponType.BIGBLUNT))
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
+	
+			
+			}		
+			
+			
+			
+			
+			
+			
 			else if (_command.startsWith("_match"))
 			{
 				String params = _command.substring(_command.indexOf("?") + 1);
@@ -193,6 +622,267 @@
 		}
 	}
 	
+	
+	public static String getItemNameById(int itemId)
+	{
+		Item item = ItemTable.getInstance().getTemplate(itemId);
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
+	
+	
+	
 	private static void playerHelp(Player activeChar, String path)
 	{
 		if (path.indexOf("..") != -1)
diff --git java/com/l2j4team/gameserver/network/serverpackets/CharInfo.java java/com/l2j4team/gameserver/network/serverpackets/CharInfo.java
index 1704550..0c8919b 100644
--- java/com/l2j4team/gameserver/network/serverpackets/CharInfo.java
+++ java/com/l2j4team/gameserver/network/serverpackets/CharInfo.java
@@ -8,6 +8,9 @@
 import com.l2j4team.gameserver.model.location.Location;
 import com.l2j4team.gameserver.skills.AbnormalEffect;
 
+import Base.Skin.DressMeData;
+import Base.Skin.SkinPackage;
+
 public class CharInfo extends L2gamePacket
 {
 	private final Player _activeChar;
@@ -46,19 +49,84 @@
 		else
 			writeD(_activeChar.getBaseClass());
 		
		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HAIRALL));
	    writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FEET));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_BACK));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
-		writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FACE));
-		
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			
+			if (_activeChar.getShieldSkinOption() > 0 && getShieldOption(_activeChar.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			}
+			
+			if (_activeChar.getArmorSkinOption() > 0 && getArmorOption(_activeChar.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getChestId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+			}
+			
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
+			
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			
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
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_BACK));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
+			writeD(_inv.getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+		}
 		// c6 new h's
 		writeH(0x00);
 		writeH(0x00);
@@ -206,4 +274,30 @@
 		else
 			writeD(0x00);
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
+	
 }
\ No newline at end of file
diff --git java/com/l2j4team/gameserver/network/serverpackets/UserInfo.java java/com/l2j4team/gameserver/network/serverpackets/UserInfo.java
index deef1da..11835ce 100644
--- java/com/l2j4team/gameserver/network/serverpackets/UserInfo.java
+++ java/com/l2j4team/gameserver/network/serverpackets/UserInfo.java
@@ -9,6 +9,9 @@
 import com.l2j4team.gameserver.model.location.Location;
 import com.l2j4team.gameserver.skills.AbnormalEffect;
 
+import Base.Skin.DressMeData;
+import Base.Skin.SkinPackage;
+
 public class UserInfo extends L2gamePacket
 {
 	private final Player _activeChar;
@@ -72,16 +75,84 @@
 		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RFINGER));
 		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LFINGER));
 		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HEAD));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));
-		writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FACE));
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
+			}
+			
+			if (_activeChar.getShieldSkinOption() > 0 && getShieldOption(_activeChar.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
+			}
+			
+			if (_activeChar.getArmorSkinOption() > 0 && getArmorOption(_activeChar.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getChestId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
+			}
+			
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
+			
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
+			}
+			
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
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));
+			writeD(_activeChar.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FACE));
+		}
 		
 		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIRALL));
 		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_REAR));
@@ -90,16 +161,85 @@
 		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
 		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));
 		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
-		writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+		
+		if (Config.ALLOW_DRESS_ME_SYSTEM)
+		{
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			
+			if (_activeChar.getShieldSkinOption() > 0 && getShieldOption(_activeChar.getShieldSkinOption()) != null)
+			{
+				writeD(getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() != 0 ? getShieldOption(_activeChar.getShieldSkinOption()).getShieldId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
+			}
+			
+			if (_activeChar.getArmorSkinOption() > 0 && getArmorOption(_activeChar.getArmorSkinOption()) != null)
+			{
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getGlovesId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getChestId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getChestId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getLegsId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+				writeD(getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() != 0 ? getArmorOption(_activeChar.getArmorSkinOption()).getFeetId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
+			}
+			
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
+			
+			if (_activeChar.getWeaponSkinOption() > 0 && getWeaponOption(_activeChar.getWeaponSkinOption()) != null)
+			{
+				writeD(getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() != 0 ? getWeaponOption(_activeChar.getWeaponSkinOption()).getWeaponId() : _activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			else
+			{
+				writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			}
+			
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
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
+			writeD(_activeChar.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
+		}
 		
 		// c6 new h's
 		writeH(0x00);
@@ -265,4 +405,29 @@
 		else
 			writeD(0x00);
 	}
+	
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
 }
