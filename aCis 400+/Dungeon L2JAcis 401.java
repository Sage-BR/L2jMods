### Eclipse Workspace Patch 1.0
#P aCis_Gameserver
Index: java/net/sf/l2j/Config.java 
===================================================================
--- java/net/sf/l2j/Config.java   (nonexistent)
+++ java/net/sf/l2j/Config.java   (working copy)


	public static final String SIEGE_FILE = "./config/siege.properties";
+	public static final String DUNGEON_REWARD_FILE = "./events/Dungeon_Html_Reward.properties";	
+	
+	
+ 	public static int DUNGEON_COIN_ID;    	
+ 	public static int CONT_DUNGEON_ITEM;
+ 	public static int DUNGEON_SPAWN_X;
+ 	public static int DUNGEON_SPAWN_Y;
+ 	public static int DUNGEON_SPAWN_Z;
+ 	public static int DUNGEON_SPAWN_RND;     	
+ 	
+ 	public static int DUNGEON_ITEM_RENEWAL0;
+ 	public static int DUNGEON_ITEM_RENEWAL1;
+ 	public static int DUNGEON_ITEM_RENEWAL2;
+ 	public static int DUNGEON_ITEM_RENEWAL3;
+ 	public static int DUNGEON_ITEM_RENEWAL4;
+ 	public static int DUNGEON_ITEM_RENEWAL5;
+ 	public static int DUNGEON_ITEM_RENEWAL6;
+ 	public static int DUNGEON_ITEM_RENEWAL7;
+ 	public static int DUNGEON_ITEM_RENEWAL8;
+ 	public static int DUNGEON_ITEM_RENEWAL9;
+ 	public static int DUNGEON_ITEM_RENEWAL10;
+ 	
+ 	public static int DUNGEON_PARTY_ITEM_RENEWAL0;
+ 	public static int DUNGEON_PARTY_ITEM_RENEWAL1;
+ 	public static int DUNGEON_PARTY_ITEM_RENEWAL2;
+ 	public static int DUNGEON_PARTY_ITEM_RENEWAL3;
+ 	public static int DUNGEON_PARTY_ITEM_RENEWAL4;    		
+	
+	
+	
+	/**
+	 * Loads Dungeon settings.
+	 */
+	private static final void loadDungeon() 
+	{
+		// Dungeon
+		ExProperties SafeDungeon = initProperties(DUNGEON_REWARD_FILE);
+		
+		DUNGEON_COIN_ID = SafeDungeon.getProperty("DungeonCoinId", 57);
+		CONT_DUNGEON_ITEM = SafeDungeon.getProperty("DungeonContItem", 1);
+		DUNGEON_SPAWN_X = SafeDungeon.getProperty("DungeonSpawnX", 82635);
+		DUNGEON_SPAWN_Y = SafeDungeon.getProperty("DungeonSpawnY", 148798);
+		DUNGEON_SPAWN_Z = SafeDungeon.getProperty("DungeonSpawnZ", -3464);
+		DUNGEON_SPAWN_RND = SafeDungeon.getProperty("DungeonSpawnRnd", 25);			
+		
+		DUNGEON_ITEM_RENEWAL0 = SafeDungeon.getProperty("DungeonRenewalHtml0", 15);
+		DUNGEON_ITEM_RENEWAL1 = SafeDungeon.getProperty("DungeonRenewalHtml1", 15);
+		DUNGEON_ITEM_RENEWAL2 = SafeDungeon.getProperty("DungeonRenewalHtml2", 15);
+		DUNGEON_ITEM_RENEWAL3 = SafeDungeon.getProperty("DungeonRenewalHtml3", 15);
+		DUNGEON_ITEM_RENEWAL4 = SafeDungeon.getProperty("DungeonRenewalHtml4", 15);
+		DUNGEON_ITEM_RENEWAL5 = SafeDungeon.getProperty("DungeonRenewalHtml5", 15);
+		DUNGEON_ITEM_RENEWAL6 = SafeDungeon.getProperty("DungeonRenewalHtml6", 15);	
+		DUNGEON_ITEM_RENEWAL7 = SafeDungeon.getProperty("DungeonRenewalHtml7", 15);	
+		DUNGEON_ITEM_RENEWAL8 = SafeDungeon.getProperty("DungeonRenewalHtml8", 15);	
+		DUNGEON_ITEM_RENEWAL9 = SafeDungeon.getProperty("DungeonRenewalHtml9", 15);	
+		DUNGEON_ITEM_RENEWAL10 = SafeDungeon.getProperty("DungeonRenewalHtml10", 15);
+		DUNGEON_PARTY_ITEM_RENEWAL0 = SafeDungeon.getProperty("DungeonPartyRenewalHtml0", 15);
+		DUNGEON_PARTY_ITEM_RENEWAL1 = SafeDungeon.getProperty("DungeonPartyRenewalHtml1", 15);
+		DUNGEON_PARTY_ITEM_RENEWAL2 = SafeDungeon.getProperty("DungeonPartyRenewalHtml2", 15);
+		DUNGEON_PARTY_ITEM_RENEWAL3 = SafeDungeon.getProperty("DungeonPartyRenewalHtml3", 15);
+		DUNGEON_PARTY_ITEM_RENEWAL4 = SafeDungeon.getProperty("DungeonPartyRenewalHtml4", 15);			
+	}



	public static final void loadGameServer()
	{
		LOGGER.info("Loading gameserver configuration files.");
		
		
+		// Dungeon settings
+		loadDungeon();	

		// clans settings
		loadClans();




### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Manager/NewCharTaskManager.java
===================================================================
--- java/Base/Manager/NewCharTaskManager.java  (nonexistent)
+++ java/Base/Manager/NewCharTaskManager.java   (working copy)
+package Base.Manager;
+
+import java.util.Map;
+import java.util.concurrent.ConcurrentHashMap;
+
+import net.sf.l2j.commons.pool.ThreadPool;
+
+import net.sf.l2j.gameserver.model.actor.Creature;
+import net.sf.l2j.gameserver.model.actor.Player;
+
+/**
+ * @author Baggos
+ */
+public final class NewCharTaskManager implements Runnable
+{
+	private final Map<Player, Long> _players = new ConcurrentHashMap<>();
+
+	protected NewCharTaskManager()
+	{
+		// Run task each 10 second.
+		ThreadPool.scheduleAtFixedRate(this, 1000, 1000);
+	}
+
+	public final void add(Player player)
+	{
+		_players.put(player, System.currentTimeMillis());
+	}
+
+	public final void remove(Creature player)
+	{
+		_players.remove(player);
+	}
+
+	@Override
+	public final void run()
+	{
+		if (_players.isEmpty())
+			return;
+
+		for (Map.Entry<Player, Long> entry : _players.entrySet())
+		{
+			final Player player = entry.getKey();
+
+			if (player.getMemos().getLong("newEndTime") < System.currentTimeMillis())
+			{
+				Player.removeNewChar(player);
+				remove(player);
+			}
+		}
+	}
+
+	public static final NewCharTaskManager getInstance()
+	{
+		return SingletonHolder._instance;
+	}
+
+	private static class SingletonHolder
+	{
+		protected static final NewCharTaskManager _instance = new NewCharTaskManager();
+	}
+}

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Util/Mysql.java
===================================================================
--- java/Base/Util/Mysql.java (nonexistent)
+++ java/Base/Util/Mysql.java   (working copy)
+package Base.Util;
+
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
+import java.sql.SQLException;
+import java.sql.Statement;
+import java.util.logging.Logger;
+
+import net.sf.l2j.commons.pool.ConnectionPool;
+
+
+
+public abstract class Mysql
+{
+	public static final Logger _log = Logger.getLogger(Mysql.class.getName());
+
+	/**
+	 * Performs a simple sql queries where unnecessary control parameters <BR>
+	 * NOTE: In this method, the parameters passed are not valid for SQL-injection!
+	 * @param db 
+	 * @param query 
+	 * @param vars 
+	 * @return 
+	 */
+	public static boolean setEx(ConnectionPool db, String query, Object... vars)
+	{
+		Connection con = null;
+		Statement statement = null;
+		PreparedStatement pstatement = null;
+		boolean successed = true;
+		
+		try
+		{
+			if(db == null)
+				db = ConnectionPool.getInstance();
+
+			con = ConnectionPool.getConnection();
+			if(vars.length == 0)
+			{
+				statement = con.createStatement();
+				statement.executeUpdate(query);
+				statement.close();
+			}
+			else
+			{
+				pstatement = con.prepareStatement(query);
+				setVars(pstatement, vars);
+				pstatement.executeUpdate();
+				pstatement.close();
+			}
+			con.close();
+		}
+		catch(Exception e)
+		{
+			_log.warning("Could not execute update '" + query + "': " + e);
+			e.printStackTrace();
+			successed = false;
+		}
+		finally
+		{
+			closeQuietly(con, pstatement);
+			closeQuietly(statement);
+		}
+		return successed;
+	}
+
+	public static void setVars(PreparedStatement statement, Object... vars) throws SQLException
+	{
+		Number n;
+		long long_val;
+		double double_val;
+		for(int i = 0; i < vars.length; i++)
+			if(vars[i] instanceof Number)
+			{
+				n = (Number) vars[i];
+				long_val = n.longValue();
+				double_val = n.doubleValue();
+				if(long_val == double_val)
+					statement.setLong(i + 1, long_val);
+				else
+					statement.setDouble(i + 1, double_val);
+			}
+			else if(vars[i] instanceof String)
+				statement.setString(i + 1, (String) vars[i]);
+	}
+
+	public static boolean set(String query, Object... vars)
+	{
+		return setEx(null, query, vars);
+	}
+
+	public static boolean set(String query)
+	{
+		return setEx(null, query);
+	}
+	
+	public static void closeQuietly(Connection conn) 
+	{
+		try {
+			close(conn);
+		} catch (SQLException e) { // NOPMD
+			// quiet
+		}
+	}
+
+	public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
+
+        try {
+            closeQuietly(rs);
+        } finally {
+            try {
+                closeQuietly(stmt);
+            } finally {
+                closeQuietly(conn);
+            }
+        }
+    }
+	
+	public static void closeQuietly(Connection conn, Statement stmt) 
+    {
+    	try {
+    		closeQuietly(stmt);
+    	} finally {
+    		closeQuietly(conn);
+    	}
+    }
+
+    public static void closeQuietly(ResultSet rs) {
+        try {
+            close(rs);
+        } catch (SQLException e) { // NOPMD
+            // quiet
+        }
+    }
+
+    public static void closeQuietly(Statement stmt) {
+        try {
+            close(stmt);
+        } catch (SQLException e) { // NOPMD
+            // quiet
+        }
+    }
+
+    public static void close(Connection conn) throws SQLException {
+        if (conn != null) {
+            conn.close();
+        }
+    }
+
+    public static void close(ResultSet rs) throws SQLException {
+        if (rs != null) {
+            rs.close();
+        }
+    }
+
+    public static void close(Statement stmt) throws SQLException {
+        if (stmt != null) {
+            stmt.close();
+        }
+    }
+	
+}

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/XML/XMLDocumentFactory.java
===================================================================
--- java/Base/XML/XMLDocumentFactory.java (nonexistent)
+++ java/Base/XML/XMLDocumentFactory.java    (working copy)
+package Base.XML;
+
+import java.io.File;
+
+import javax.xml.parsers.DocumentBuilder;
+import javax.xml.parsers.DocumentBuilderFactory;
+
+import org.w3c.dom.Document;
+
+/**
+ * @author Forsaiken
+ */
+public final class XMLDocumentFactory
+{
+	public static final XMLDocumentFactory getInstance()
+	{
+		return SingletonHolder._instance;
+	}
+	
+	private final DocumentBuilder _builder;
+	
+	protected XMLDocumentFactory() throws Exception
+	{
+		try
+		{
+			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
+			factory.setValidating(false);
+			factory.setIgnoringComments(true);
+			
+			_builder = factory.newDocumentBuilder();
+		}
+		catch (Exception e)
+		{
+			throw new Exception("Failed initializing", e);
+		}
+	}
+	
+	public final Document loadDocument(final String filePath) throws Exception
+	{
+		return loadDocument(new File(filePath));
+	}
+	
+	public final Document loadDocument(final File file) throws Exception
+	{
+		if (!file.exists() || !file.isFile())
+			throw new Exception("File: " + file.getAbsolutePath() + " doesn't exist and/or is not a file.");
+		
+		return _builder.parse(file);
+	}
+	
+	public final Document newDocument()
+	{
+		return _builder.newDocument();
+	}
+	
+	private static class SingletonHolder
+	{
+		protected static final XMLDocumentFactory _instance;
+		
+		static
+		{
+			try
+			{
+				_instance = new XMLDocumentFactory();
+			}
+			catch (Exception e)
+			{
+				throw new ExceptionInInitializerError(e);
+			}
+		}
+	}
+}





### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/model/actor/Player.java
===================================================================
--- java/net/sf/l2j/gameserver/model/actor/Player.java  (nonexistent)
+++ java/net/sf/l2j/gameserver/model/actor/Player.java    (working copy)
import net.sf.l2j.gameserver.taskmanager.ShadowItemTaskManager;
import net.sf.l2j.gameserver.taskmanager.WaterTaskManager;
+import Base.Dungeon.Dungeon;
+import Base.Dungeon.Instance;
+import Base.Manager.NewCharTaskManager;
+import Base.Util.Mysql;












	@Override
	public void onInteract(Player player)
	{
		switch (getOperateType())
		{
			case SELL:
			case PACKAGE_SELL:
				player.sendPacket(new PrivateStoreListSell(player, this));
				break;
			
			case BUY:
				player.sendPacket(new PrivateStoreListBuy(player, this));
				break;
			
			case MANUFACTURE:
				player.sendPacket(new RecipeShopSellList(player, this));
				break;
		}
	}
	
	
+	public String getIP()
+	{
+		if (getClient().getConnection() == null)
+			return "N/A IP";
+		
+		return getClient().getConnection().getInetAddress().getHostAddress();
+	}
+	
+	
+		public static void doNewChar(Player player, int time)
+		{
+			player.setNewChar(true);
+			NewCharTaskManager.getInstance().add(player);
+			long remainingTime = player.getMemos().getLong("newEndTime", 0);
+			if (remainingTime > 0)
+			{
+				player.getMemos().set("newEndTime", remainingTime + TimeUnit.MINUTES.toMillis(time));
+			}
+			else
+			{
+				player.getMemos().set("newEndTime", System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(time));
+				player.broadcastUserInfo();
+			}
+		}
+		
+		public static void removeNewChar(Player player)
+		{
+			NewCharTaskManager.getInstance().remove(player);
+			player.getMemos().set("newEndTime", 0);
+			player.setNewChar(false);
+			player.broadcastUserInfo();
+			
+		}
+		private boolean _isnewChar;
+		public boolean isNewChar()
+		{
+			return _isnewChar;
+		}
+		
+		public void setNewChar(boolean b)
+		{
+			_isnewChar = b;
+		}
+		private Dungeon dungeon = null;
+		public void setDungeon(Dungeon val)
+		{
+			dungeon = val;
+		}
+		
+		public Dungeon getDungeon()
+		{
+			return dungeon;
+		}
+		
+		private boolean _isInDungeonZone;
+		public boolean isInDungeonZone()
+		{
+			return _isInDungeonZone;
+		}
+		
+		
+		public void deleteTempItem(int itemObjectID)
+		{
+			boolean destroyed = false;
+			if (getInventory().getItemByObjectId(itemObjectID) != null)
+			{
+				sendMessage("Your "+ItemData.getInstance().getTemplate(getInventory().getItemByObjectId(itemObjectID).getItemId()).getName()+" has expired.");
+				destroyItem("tempItemDestroy", itemObjectID, 1, this, true);
+				getInventory().updateDatabase();
+				sendPacket(new ItemList(this, true));
+				
+				destroyed = true;
+			}
+			
+			if (!destroyed)
+			{
+				Connection con = null;
+				PreparedStatement statement = null;
+				ResultSet rset = null;
+				try
+				{
+					con = ConnectionPool.getConnection();
+					statement = con.prepareStatement("DELETE FROM items WHERE object_id=?");
+					statement.setInt(1, itemObjectID);
+					statement.execute();
+				}
+				catch (Exception e)
+				{
+					e.printStackTrace();
+				}
+				finally
+				{
+					Mysql.closeQuietly(con, statement, rset);
+				}
+			}
+		}
+		private boolean _isInClanDungeonZone;
+		public boolean isInClanDungeonZone()
+		{
+			return _isInClanDungeonZone;
+		}
+		
+		public void setClanDungeonZone(boolean isInClanDungeonZone)
+		{
+			_isInClanDungeonZone = isInClanDungeonZone;
+		}
+		/**
+		 * @param instance
+		 * @param b
+		 */
+		public static void setInstance(Instance instance, boolean b)
+		{
+			return;
+			
+		}	

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/model/actor/Npc.java
===================================================================
--- java/net/sf/l2j/gameserver/model/actor/Npc.java  (nonexistent)
+++ java/net/sf/l2j/gameserver/model/actor/Npc.java    (working copy)
import net.sf.l2j.gameserver.taskmanager.DecayTaskManager;
import net.sf.l2j.gameserver.taskmanager.RandomAnimationTaskManager;
+import Base.Dungeon.Instance;








	@Override
	public void onActiveRegion()
	{
		startRandomAnimationTimer();
	}

+	/**
+	 * @param instance
+	 * @param b
+	 */
+	public void setInstance(Instance instance, boolean b)
+	{
+		return;		
+	}


### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/model/memo/AbstractMemo.java
===================================================================
--- java/net/sf/l2j/gameserver/model/memo/AbstractMemo.java (nonexistent)
+++ java/net/sf/l2j/gameserver/model/memo/AbstractMemo.java   (working copy)
+package net.sf.l2j.gameserver.model.memo;
+
+import java.util.concurrent.atomic.AtomicBoolean;
+
+import net.sf.l2j.commons.data.StatSet;
+
+
+
+/**
+ * A {@link StatSet} which overrides methods to prevent doing useless database operations if there is no changes since last edit (it +uses an AtomicBoolean to keep edition tracking).<br>
+ * <br>
+ * It also has 2 abstract methods, named restoreMe() and storeMe().
+ */
+public abstract class AbstractMemo extends StatSet
+{
+	/**
+	 *
+	 */
+	private static final long serialVersionUID = 1L;
+	private final AtomicBoolean _hasChanges = new AtomicBoolean(false);
+
+	@Override
+	public final void set(String name, boolean value)
+	{
+		_hasChanges.compareAndSet(false, true);
+		super.set(name, value);
+	}
+
+	@Override
+	public final void set(String name, double value)
+	{
+		_hasChanges.compareAndSet(false, true);
+		super.set(name, value);
+	}
+
+	@Override
+	public final void set(String name, Enum<?> value)
+	{
+		_hasChanges.compareAndSet(false, true);
+		super.set(name, value);
+	}
+
+	@Override
+	public final void set(String name, int value)
+	{
+		_hasChanges.compareAndSet(false, true);
+		super.set(name, value);
+	}
+
+	@Override
+	public final void set(String name, long value)
+	{
+		_hasChanges.compareAndSet(false, true);
+		super.set(name, value);
+	}
+
+	@Override
+	public final void set(String name, String value)
+	{
+		_hasChanges.compareAndSet(false, true);
+		super.set(name, value);
+	}
+
+	/**
+	 * @return {@code true} if changes are made since last load/save.
+	 */
+	public final boolean hasChanges()
+	{
+		return _hasChanges.get();
+	}
+
+	/**
+	 * Atomically sets the value to the given updated value if the current value {@code ==} the expected value.
+	 * @param expect
+	 * @param update
+	 * @return {@code true} if successful. {@code false} return indicates that the actual value was not equal to the expected value.
+	 */
+	public final boolean compareAndSetChanges(boolean expect, boolean update)
+	{
+		return _hasChanges.compareAndSet(expect, update);
+	}
+
+	/**
+	 * Removes variable
+	 * @param name
+	 */
+	public final void remove(String name)
+	{
+		_hasChanges.compareAndSet(false, true);
+		unset(name);
+	}
+
+	protected abstract boolean restoreMe();
+
+	protected abstract boolean storeMe();
+}


### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/model/memo/PlayerVar.java
===================================================================
--- java/net/sf/l2j/gameserver/model/memo/PlayerVar.java (nonexistent)
+++ java/net/sf/l2j/gameserver/model/memo/PlayerVar.java    (working copy)
+package net.sf.l2j.gameserver.model.memo;
+
+import java.util.concurrent.ScheduledFuture;
+
+import net.sf.l2j.commons.pool.ThreadPool;
+
+import net.sf.l2j.gameserver.model.actor.Player;
+
+public class PlayerVar
+{
+	private Player owner;
+	private String name;
+	private String value;
+	private long expire_time;
+	
+	@SuppressWarnings("rawtypes")
+	private ScheduledFuture task;
+	
+	public PlayerVar(Player owner, String name, String value, long expire_time)
+	{
+		this.owner = owner;
+		this.name = name;
+		this.value = value;
+		this.expire_time = expire_time;
+		
+		if (expire_time > 0) // if expires schedule expiration
+		{
+			task = ThreadPool.schedule(new PlayerVarExpireTask(this), expire_time - System.currentTimeMillis());
+		}
+	}
+	
+	public String getName()
+	{
+		return name;
+	}
+	
+	public Player getOwner()
+	{
+		return owner;
+	}
+	
+	public boolean hasExpired()
+	{
+		return task == null || task.isDone();
+	}
+	
+	public long getTimeToExpire()
+	{
+		return expire_time - System.currentTimeMillis();
+	}
+	
+	public String getValue()
+	{
+		return value;
+	}
+	
+	public boolean getValueBoolean()
+	{
+		if (isNumeric(value))
+			return Integer.parseInt(value) > 0;
+		
+		return value.equalsIgnoreCase("true");
+	}
+	
+	public void setValue(String val)
+	{
+		value = val;
+	}
+	
+	public void stopExpireTask()
+	{
+		if (task != null && !task.isDone())
+		{
+			task.cancel(true);
+		}
+	}
+	
+	private static class PlayerVarExpireTask implements Runnable
+	{
+		private PlayerVar _pv;
+		
+		public PlayerVarExpireTask(PlayerVar pv)
+		{
+			_pv = pv;
+		}
+		
+		@Override
+		public void run()
+		{
+			Player pc = _pv.getOwner();
+			if (pc == null)
+			{
+				return;
+			}
+			
+			PlayerMemo.unsetVar(pc, _pv.getName());
+		}
+	}
+	
+	public boolean isNumeric(String str)
+	{
+		try
+		{
+			@SuppressWarnings("unused")
+			double d = Double.parseDouble(str);
+		}
+		catch (NumberFormatException nfe)
+		{
+			return false;
+		}
+		return true;
+	}
+}







### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/model/memo/PlayerMemo.java
===================================================================
--- java/net/sf/l2j/gameserver/model/memo/PlayerMemo.java (nonexistent)
+++ java/net/sf/l2j/gameserver/model/memo/PlayerMemo.java    (working copy)
+package net.sf.l2j.gameserver.model.memo;
+
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
+import java.sql.SQLException;
+import java.util.logging.Level;
+import java.util.logging.Logger;
+
+import net.sf.l2j.commons.pool.ConnectionPool;
+
+import net.sf.l2j.gameserver.model.actor.Player;
+
+import Base.Util.Mysql;
+
+/**
+ * An implementation of {@link AbstractMemo} used for Player. There is a restore/save system.
+ */
+public class PlayerMemo extends AbstractMemo
+{
+	/**
+	 *
+	 */
+	private static final long serialVersionUID = 1L;
+	
+	private static final Logger LOG = Logger.getLogger(PlayerMemo.class.getName());
+
+	private static final String SELECT_QUERY = "SELECT * FROM character_memo WHERE charId = ?";
+	private static final String DELETE_QUERY = "DELETE FROM character_memo WHERE charId = ?";
+	private static final String INSERT_QUERY = "INSERT INTO character_memo (charId, var, val) VALUES (?, ?, ?)";
+
+	private final int _objectId;
+
+	public PlayerMemo(int objectId)
+	{
+		_objectId = objectId;
+		restoreMe();
+	}
+	
+	
+	// When var exist
+		public static void changeValue(Player player, String name, String value)
+		{
+			if (!player.getMemos().containsKey(name))
+			{
+				player.sendMessage("Variable is not exist...");
+				return;
+			}
+			
+			getVarObject(player, name).setValue(value);
+			Mysql.set("UPDATE character_memo_dungeon SET value=? WHERE obj_id=? AND name=?", value, player.getObjectId(), name);
+		}
+		
+		public static void setVar(Player player, String name, String value, long expirationTime)
+		{
+			if (player.getMemos().containsKey(name))
+				getVarObject(player, name).stopExpireTask();
+
+			player.getMemos().put(name, new PlayerVar(player, name, value, expirationTime));
+			Mysql.set("REPLACE INTO character_memo_dungeon (obj_id, name, value, expire_time) VALUES (?,?,?,?)", player.getObjectId(), name, value, expirationTime);
+		}
+		
+		public static void setVar(Player player, String name, int value, long expirationTime)
+		{
+			setVar(player, name, String.valueOf(value), expirationTime);
+		}	
+			
+		public void setVar(Player player, String name, long value, long expirationTime)
+		{
+			setVar(player, name, String.valueOf(value), expirationTime);
+		}
+		
+		
+		public static PlayerVar getVarObject(Player player, String name)
+		{
+			if(player.getMemos() == null)
+				return null;
+			
+			return (PlayerVar) player.getMemos().get(name);
+		}
+		
+		public static long getVarTimeToExpire(Player player, String name)
+		{
+			try
+			{
+				return getVarObject(player, name).getTimeToExpire();
+			}
+			catch (NullPointerException npe)
+			{
+			}
+				
+			return 0;
+		}
+
+		public static void unsetVar(Player player, String name)
+		{
+			if (name == null)
+				return;
+			
+			// Avoid possible unsetVar that have elements for login
+			if(player == null)
+				return;
+
+			PlayerVar pv = (PlayerVar) player.getMemos().get(name);
+
+			if (pv != null)
+			{
+				if(name.contains("delete_temp_item"))
+					pv.getOwner().deleteTempItem(Integer.parseInt(pv.getValue()));
+				else if(name.contains("solo_hero")) {
+					pv.getOwner().broadcastCharInfo();
+					pv.getOwner().broadcastUserInfo();
+				}
+			
+
+				Mysql.set("DELETE FROM character_memo_dungeon WHERE obj_id=? AND name=? LIMIT 1", pv.getOwner().getObjectId(), name);
+
+				pv.stopExpireTask();
+			}
+		}
+			
+		public static void deleteExpiredVar(Player player, String name, String value)
+		{
+			if (name == null)
+				return;
+
+			if(name.contains("delete_temp_item"))
+				player.deleteTempItem(Integer.parseInt(value));
+			/*else if(name.contains("solo_hero")) // Useless
+				player.broadcastCharInfo();*/
+			
+			Mysql.set("DELETE FROM character_memo_dungeon WHERE obj_id=? AND name=? LIMIT 1", player.getObjectId(), name);
+		}
+			
+		public static String getVar(Player player, String name)
+		{
+			PlayerVar pv = getVarObject(player, name);
+				
+			if (pv == null)
+				return null;
+
+			return pv.getValue();
+		}
+
+		@SuppressWarnings("resource")
+		public static long getVarTimeToExpireSQL(Player player, String name)
+		{
+			long expireTime = 0;
+
+			try (Connection con = ConnectionPool.getConnection())
+			{
+				PreparedStatement statement = con.prepareStatement("SELECT expire_time FROM character_memo_dungeon WHERE obj_id = ? AND name = ?");
+				statement.setLong(1, player.getObjectId());
+				statement.setString(2, name);
+				for (ResultSet rset = statement.executeQuery(); rset.next();)
+					expireTime = rset.getLong("expire_time");
+				 
+				con.close();
+				statement.close();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+
+			return expireTime;
+		}
+
+		public static boolean getVarB(Player player, String name, boolean defaultVal)
+		{
+			PlayerVar pv = getVarObject(player, name);
+			
+			if (pv == null)
+				return defaultVal;
+				
+			return pv.getValueBoolean();
+		}
+			
+		public static boolean getVarB(Player player, String name)
+		{
+			return getVarB(player, name, false);
+		}
+			
+		public long getVarLong(Player player, String name)
+		{
+			return getVarLong(player, name, 0L);
+		}
+			
+		public long getVarLong(Player player, String name, long defaultVal)
+		{
+			long result = defaultVal;
+			String var = getVar(player, name);
+			if (var != null)
+				result = Long.parseLong(var);
+				
+			return result;
+		}
+
+		public static int getVarInt(Player player, String name)
+		{
+			return getVarInt(player, name, 0);
+		}
+			
+		public static int getVarInt(Player player, String name, int defaultVal)
+		{
+			int result = defaultVal;
+			String var = getVar(player, name);
+			if (var != null)
+			{
+				if(var.equalsIgnoreCase("true"))
+					result = 1;
+				else if(var.equalsIgnoreCase("false"))
+					result = 0;
+				else
+					result = Integer.parseInt(var);
+			}
+			return result;
+		}
+		
+		public static void loadVariables(Player player)
+		{
+			Connection con = null;
+			PreparedStatement offline = null;
+			ResultSet rs = null;
+			try
+			{
+
+				con = ConnectionPool.getConnection();
+				offline = con.prepareStatement("SELECT * FROM character_memo_dungeon WHERE obj_id = ?");
+				offline.setInt(1, player.getObjectId());
+				rs = offline.executeQuery();
+					
+				while (rs.next())
+				{
+					String name = rs.getString("name");
+					String value = rs.getString("value");
+					long expire_time = rs.getLong("expire_time");
+					long curtime = System.currentTimeMillis();
+					
+					if ((expire_time <= curtime) && (expire_time > 0))
+					{
+						deleteExpiredVar(player, name, rs.getString("value")); //TODO: Remove the Var
+						continue;
+					}
+
+					player.getMemos().put(name, new PlayerVar(player, name, value, expire_time));
+				}
+				 
+				con.close();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+			finally
+			{
+				Mysql.closeQuietly(con, offline, rs);
+			}
+		}
+
+		public static String getVarValue(Player player, String var, String defaultString)
+		{
+			String value = null;
+			Connection con = null;
+			PreparedStatement offline = null;
+			ResultSet rs = null;
+			try
+			{
+
+				con = ConnectionPool.getConnection();
+				offline = con.prepareStatement("SELECT value FROM character_memo_dungeon WHERE obj_id = ? AND name = ?");
+				offline.setInt(1, player.getObjectId());
+				offline.setString(2, var);
+				rs = offline.executeQuery();
+				if (rs.next())
+					value = rs.getString("value");
+
+				con.close();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+			finally
+			{
+				Mysql.closeQuietly(con, offline, rs);
+			}
+			return value == null ? defaultString : value;
+		}
+		
+		public static String getVarValue(int objectId, String var, String defaultString)
+		{
+			String value = null;
+			Connection con = null;
+			PreparedStatement offline = null;
+			ResultSet rs = null;
+			try
+			{
+
+				con = ConnectionPool.getConnection();
+				offline = con.prepareStatement("SELECT value FROM character_memo_dungeon WHERE obj_id = ? AND name = ?");
+				offline.setInt(1, objectId);
+				offline.setString(2, var);
+				rs = offline.executeQuery();
+				if (rs.next())
+					value = rs.getString("value");
+
+				con.close();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+			finally
+			{
+				Mysql.closeQuietly(con, offline, rs);
+			}
+			return value == null ? defaultString : value;
+		}
+
+	@Override
+	public boolean restoreMe()
+	{
+
+		// Restore previous variables.
+		try (Connection con = ConnectionPool.getConnection())
+		{
+			PreparedStatement st = con.prepareStatement(SELECT_QUERY);
+			st.setInt(1, _objectId);
+
+			ResultSet rset = st.executeQuery();
+			while (rset.next())
+				set(rset.getString("var"), rset.getString("val"));
+
+			rset.close();
+			st.close();
+		}
+		catch (SQLException e)
+		{
+			LOG.log(Level.SEVERE, "Couldn't restore variables for player id: " + _objectId, e);
+			return false;
+		}
+		finally
+		{
+			compareAndSetChanges(true, false);
+		}
+		return true;
+	}
+
+	@Override
+	public boolean storeMe()
+	{
+		// No changes, nothing to store.
+		if (!hasChanges())
+			return false;
+
+
+		try (Connection con = ConnectionPool.getConnection())
+		{
+			// Clear previous entries.
+			PreparedStatement st = con.prepareStatement(DELETE_QUERY);
+			st.setInt(1, _objectId);
+			st.execute();
+			st.close();
+
+			// Insert all variables.
+			st = con.prepareStatement(INSERT_QUERY);
+			st.setInt(1, _objectId);
+			for (Entry<String, Object> entry : entrySet())
+			{
+				st.setString(2, entry.getKey());
+				st.setString(3, String.valueOf(entry.getValue()));
+				st.addBatch();
+			}
+			st.executeBatch();
+			st.close();
+		}
+		catch (SQLException e)
+		{
+			LOG.log(Level.SEVERE, "Couldn't update variables for player id: " + _objectId, e);
+			return false;
+		}
+		finally
+		{
+			compareAndSetChanges(true, false);
+		}
+		return true;
+	}
+}


### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/model/actor/instance/DungeonManagerNpc.java
===================================================================
--- java/net/sf/l2j/gameserver/model/actor/instance/DungeonManagerNpc.java  (nonexistent)
+++ java/net/sf/l2j/gameserver/model/actor/instance/DungeonManagerNpc.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package net.sf.l2j.gameserver.model.actor.instance;
+
+
+
+import java.util.StringTokenizer;
+
+import net.sf.l2j.Config;
+import net.sf.l2j.gameserver.model.actor.Player;
+import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
+import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
+
+import Base.Dungeon.DungeonManager;
+
+
+
+/**
+ * @author Anarchy
+ */
+public class DungeonManagerNpc extends Folk
+{
+	public DungeonManagerNpc(int objectId, NpcTemplate template)
+	{
+		super(objectId, template);
+	}
+	
+	@Override
+	public void onBypassFeedback(Player player, String command)
+	{
+		if (command.startsWith("dungeon"))
+		{
+			if (DungeonManager.getInstance().isInDungeon(player) || player.isInOlympiadMode())
+			{
+				player.sendMessage("You are currently unable to enter a Dungeon. Please try again later.");
+				return;
+			}
+			
+			if (player.getInventory().getItemByItemId(Config.DUNGEON_COIN_ID) == null)
+			{
+			DungeonManagerNpc.mainHtml(player, 0);
+			return;
+			}
+			
+			player.destroyItemByItemId("Quest",Config.DUNGEON_COIN_ID, Config.CONT_DUNGEON_ITEM, player, true);
+			int dungeonId = Integer.parseInt(command.substring(8));
+			
+			
+			if(dungeonId == 1 || dungeonId == 2)
+			{
+				
+				
+				DungeonManager.getInstance().enterDungeon(dungeonId, player);
+		
+			}
+		}
+		else
+			super.onBypassFeedback(player, command);
+	}
+	public static void mainHtml(Player activeChar, int time)
+	{		
+	if (activeChar.isOnline())
+    {
+        NpcHtmlMessage nhm = new NpcHtmlMessage(5);
+        StringBuilder html1 = new StringBuilder("");
+        html1.append("<html><head><title>Dungeon</title></head><body><center>");
+        html1.append("<br>");
+        html1.append("Your character Cont Item.");
+        html1.append("</center>");
+        html1.append("</body></html>");
+        nhm.setHtml(html1.toString());
+        activeChar.sendPacket(nhm);
+    }
+			
+	}
+	
+	public static String getPlayerStatus(Player player, int dungeonId)
+	{
+		String s = "You can enter";
+		
+		String ip = player.getIP(); // Is ip or hwid?
+		if (DungeonManager.getInstance().getPlayerData().containsKey(ip) && DungeonManager.getInstance().getPlayerData().get(ip)[dungeonId] > 0)
+		{
+			long total = (DungeonManager.getInstance().getPlayerData().get(ip)[dungeonId] + (1000*60*60*12)) - System.currentTimeMillis();
+			
+			if (total > 0)
+			{
+				int hours = (int) (total/1000/60/60);
+				int minutes = (int) ((total/1000/60) - hours*60);
+				int seconds = (int) ((total/1000) - (hours*60*60 + minutes*60));
+				
+				s = String.format("%02d:%02d:%02d", hours, minutes, seconds);
+			}
+		}
+		
+		return s;
+	}
+	
+	@Override
+	public void showChatWindow(Player player, int val)
+	{
+		NpcHtmlMessage htm = new NpcHtmlMessage(getObjectId());
+		htm.setFile("data/html/mods/dungeon/"+getNpcId()+(val == 0 ? "" : "-"+val)+".htm");
+		
+		String[] s = htm.getHtml().split("%");
+		for (int i = 0; i < s.length; i++)
+		{
+			if (i % 2 > 0 && s[i].contains("dung "))
+			{
+				StringTokenizer st = new StringTokenizer(s[i]);
+				st.nextToken();
+				htm.replace("%"+s[i]+"%", getPlayerStatus(player, Integer.parseInt(st.nextToken())));
+			}
+		}
+		
+		htm.replace("%objectId%", getObjectId()+"");
+		
+		player.sendPacket(htm);
+	}
+	
+	@Override
+	public String getHtmlPath(int npcId, int val)
+	{
+		String filename = "";
+		if (val == 0)
+			filename = "" + npcId;
+		else
+			filename = npcId + "-" + val;
+		
+		return "data/html/mods/dungeon/" + filename + ".htm";
+	}
+}

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/network/serverpackets/NpcHtmlMessage.java
===================================================================
--- java/net/sf/l2j/gameserver/network/serverpackets/NpcHtmlMessage.java (nonexistent)
+++ java/net/sf/l2j/gameserver/network/serverpackets/NpcHtmlMessage.java   (working copy)

	public void replace(String pattern, double value)
	{
		_html = _html.replaceAll(pattern, Double.toString(value));
	}

+	public String getHtml()
+	{
+		return _html;
+	}





### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/model/actor/instance/DungeonMob.java
===================================================================
--- java/net/sf/l2j/gameserver/model/actor/instance/DungeonMob.java (nonexistent)
+++ java/net/sf/l2j/gameserver/model/actor/instance/DungeonMob.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package net.sf.l2j.gameserver.model.actor.instance;
+
+
+
+
+import net.sf.l2j.commons.pool.ThreadPool;
+
+import net.sf.l2j.gameserver.model.actor.Creature;
+import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
+
+import Base.Dungeon.Dungeon;
+
+
+
+/**
+ * @author Anarchy
+ */
+public class DungeonMob extends Monster
+{
+	private Dungeon dungeon;
+	
+	public DungeonMob(int objectId, NpcTemplate template)
+	{
+		super(objectId, template);
+	}
+	
+	@Override
+	public boolean doDie(Creature killer)
+	{
+		if (!super.doDie(killer))
+			return false;
+		
+		if(dungeon != null) // It will be dungeon == null when mob is spawned from admin and not from dungeon
+			ThreadPool.schedule(() -> dungeon.onMobKill(this), 1000*2);
+		
+		return true;
+	}
+	
+	public void setDungeon(Dungeon dungeon)
+	{
+		this.dungeon = dungeon;
+	}
+}






### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Dungeon/Dungeon.java
===================================================================
--- java/Base/Dungeon/Dungeon.java  (nonexistent)
+++ java/Base/Dungeon/Dungeon.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dungeon;
+
+import java.util.List;
+import java.util.Map.Entry;
+import java.util.concurrent.CopyOnWriteArrayList;
+import java.util.concurrent.ScheduledFuture;

+import net.sf.l2j.commons.pool.ThreadPool;
+
+import net.sf.l2j.gameserver.data.sql.SpawnTable;
+import net.sf.l2j.gameserver.data.xml.NpcData;
+import net.sf.l2j.gameserver.model.actor.Player;
+import net.sf.l2j.gameserver.model.actor.instance.DungeonMob;
+import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
+import net.sf.l2j.gameserver.model.location.Location;
+import net.sf.l2j.gameserver.model.memo.PlayerMemo;
+import net.sf.l2j.gameserver.model.spawn.Spawn;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage.SMPOS;
+import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;
+import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

+/**
+ * @author Anarchy
+ */
+public class Dungeon
+{
+	private DungeonTemplate template;
+	private List<Player> players;
+	private ScheduledFuture<?> dungeonCancelTask = null;
+	private ScheduledFuture<?> nextTask = null;
+	private ScheduledFuture<?> timerTask = null;
+	private DungeonStage currentStage = null;
+	private long stageBeginTime = 0;
+	private List<DungeonMob> mobs = new CopyOnWriteArrayList<>();
+	private Instance instance;
+	
+	public Dungeon(DungeonTemplate template, List<Player> players)
+	{
+		this.template = template;
+		this.players = players;
+		instance = InstanceManager.getInstance().createInstance();
+		
+		for (Player player : players)
+			player.setDungeon(this);
+		
+		beginTeleport();
+	}
+	
+	public void onPlayerDeath(Player player)
+	{
+		if (!players.contains(player))
+			return;
+		
+		if (players.size() == 1)
+			ThreadPool.schedule(() -> cancelDungeon(), 5 * 1000);
+		else
+			player.sendMessage("You will be ressurected if your team completes this stage.");
+	}
+	
+	public synchronized void onMobKill(DungeonMob mob)
+	{
+		if (!mobs.contains(mob))
+			return;
+		
+		deleteMob(mob);
+		
+		if (mobs.isEmpty())
+		{
+			if (dungeonCancelTask != null)
+				dungeonCancelTask.cancel(false);
+			if (timerTask != null)
+				timerTask.cancel(true);
+			if (nextTask != null)
+				nextTask.cancel(true);
+			
+			for (Player player : players)
+				if (player.isDead())
+					player.doRevive();
+				
+			getNextStage();
+			if (currentStage == null) // No more stages
+			{
+				rewardPlayers();
+				if (template.getRewardHtm().equals("NULL"))
+				{
+					broadcastScreenMessage("You have completed the dungeon!", 5);
+					teleToTown();
+				}
+				else
+				{
+					broadcastScreenMessage("You have completed the dungeon! Select your reward", 5);
+					// teleToTown();
+				}
+				InstanceManager.getInstance().deleteInstance(instance.getId());
+				DungeonManager.getInstance().removeDungeon(this);
+			}
+			else
+			{
+				broadcastScreenMessage("You have completed stage " + (currentStage.getOrder() - 1) + "! Next stage begins in 10 seconds.", 5);
+				ThreadPool.schedule(() -> teleToStage(), 5 * 1000);
+				nextTask = ThreadPool.schedule(() -> beginStage(), 10 * 1000);
+			}
+		}
+	}
+	
+	private void rewardPlayers()
+	{
+		// First Add Fixed Reward
+		for (Player player : players)
+		{
+			
+			PlayerMemo.setVar(player, "dungeon_atleast1time", "true", -1);
+			for (Entry<Integer, Integer> itemId : template.getRewards().entrySet())
+			{
+				player.addItem("dungeon reward", itemId.getKey(), itemId.getValue(), null, true);
+			}
+		}
+		
+		if (!template.getRewardHtm().equals("NULL"))
+		{
+			NpcHtmlMessage htm = new NpcHtmlMessage(0);
+			htm.setFile(template.getRewardHtm());
+			for (Player player : players)
+				player.sendPacket(htm);
+		}
+		else
+		{
+			for (Player player : players)
+			{
+				Player.setInstance(InstanceManager.getInstance().getInstance(0), true);
+				player.teleportTo(82635, 148798, -3464, 25);
+			}
+		}
+	}
+	
+	private void teleToStage()
+	{
+		if (!currentStage.teleport())
+			return;
+		
+		for (Player player : players)
+			player.teleportTo(currentStage.getLocation(), 25);
+	}
+	
+	private void teleToTown()
+	{
+		for (Player player : players)
+		{
+			if (!player.isOnline() || player.getClient().isDetached())
+				continue;
+			
+			DungeonManager.getInstance().getDungeonParticipants().remove(player.getObjectId());
+			player.setDungeon(null);
+			Player.setInstance(InstanceManager.getInstance().getInstance(0), true);
+			player.teleportTo(82635, 148798, -3464, 25);
+		}
+	}
+	
+	private void cancelDungeon()
+	{
+		for (Player player : players)
+		{
+			if (player.isDead())
+				player.doRevive();
+			
+
+		}
+		
+		
+		for (DungeonMob mob : mobs)
+			deleteMob(mob);
+
+		broadcastScreenMessage("You have failed to complete the dungeon. You will be teleported back in 10 seconds.", 5);
+		teleToTown();
+		
+		InstanceManager.getInstance().deleteInstance(instance.getId());
+		DungeonManager.getInstance().removeDungeon(this);
+		
+		if (nextTask != null)
+			nextTask.cancel(true);
+		if (timerTask != null)
+			timerTask.cancel(true);
+		if (dungeonCancelTask != null)
+			dungeonCancelTask.cancel(true);
+	}
+	
+	private void deleteMob(DungeonMob mob)
+	{
+		if (!mobs.contains(mob))
+			return;
+		
+		mobs.remove(mob);
+		if (mob.getSpawn() != null)
+			SpawnTable.getInstance().deleteSpawn(mob.getSpawn(), false);
+		mob.deleteMe();
+	}
+	
+	private void beginStage()
+	{
+		for (int mobId : currentStage.getMobs().keySet())
+			spawnMob(mobId, currentStage.getMobs().get(mobId));
+		
+		stageBeginTime = System.currentTimeMillis();
+		timerTask = ThreadPool.scheduleAtFixedRate(() -> broadcastTimer(), 5 * 1000, 1000);
+		nextTask = null;
+		dungeonCancelTask = ThreadPool.schedule(() -> cancelDungeon(), 1000 * 60 * currentStage.getMinutes());
+		broadcastScreenMessage("You have " + currentStage.getMinutes() + " minutes to finish stage " + currentStage.getOrder() + "!", 5);
+	}
+	
+	private void spawnMob(int mobId, List<Location> locations)
+	{
+		NpcTemplate template = NpcData.getInstance().getTemplate(mobId);
+		try
+		{
+			for (Location loc : locations)
+			{
+				Spawn spawn = new Spawn(template);
+				spawn.setLoc(loc.getX(), loc.getY(), loc.getZ(), 0);
+				spawn.setRespawnDelay(1);
+				spawn.setRespawnState(false);
+				spawn.doSpawn(false);
+				
+				((DungeonMob) spawn.getNpc()).setDungeon(this);
+				spawn.getNpc().setInstance(instance, false); // Set instance first
+				// SpawnTable.getInstance().addNewSpawn(spawn, false); // TODO: Useless
+
+				
+				// Add it to memory
+				mobs.add((DungeonMob) spawn.getNpc());
+			}
+		}
+		catch (Exception e)
+		{
+			e.printStackTrace();
+		}
+	}
+	
+	private void teleportPlayers()
+	{
+		for (Player player : players)
+			Player.setInstance(instance, true);
+		
+		teleToStage();
+		
+		broadcastScreenMessage("Stage " + currentStage.getOrder() + " begins in 10 seconds!", 5);
+		
+		nextTask = ThreadPool.schedule(() -> beginStage(), 10 * 1000);
+	}
+	
+	private void beginTeleport()
+	{
+		getNextStage();
+		for (Player player : players)
+		{
+			player.broadcastPacket(new MagicSkillUse(player, 1050, 1, 10000, 10000));
+			broadcastScreenMessage("You will be teleported in 10 seconds!", 3);
+		}
+		
+		nextTask = ThreadPool.schedule(() -> teleportPlayers(), 10 * 1000);
+	}
+	
+	private void getNextStage()
+	{
+		currentStage = currentStage == null ? template.getStages().get(1) : template.getStages().get(currentStage.getOrder() + 1);
+	}
+	
+	private void broadcastTimer()
+	{
+		int secondsLeft = (int) (((stageBeginTime + (1000 * 60 * currentStage.getMinutes())) - System.currentTimeMillis()) / 1000);
+		int minutes = secondsLeft / 60;
+		int seconds = secondsLeft % 60;
+		ExShowScreenMessage packet = new ExShowScreenMessage(String.format("%02d:%02d", minutes, seconds), 1010, SMPOS.BOTTOM_RIGHT, false);
+		for (Player player : players)
+			player.sendPacket(packet);
+	}
+	
+	private void broadcastScreenMessage(String msg, int seconds)
+	{
+		ExShowScreenMessage packet = new ExShowScreenMessage(msg, seconds * 1000, SMPOS.TOP_CENTER, false);
+		for (Player player : players)
+			player.sendPacket(packet);
+	}
+	
+	public List<Player> getPlayers()
+	{
+		return players;
+	}
+}
### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Dungeon/DungeonManager.java
===================================================================
--- java/Base/Dungeon/DungeonManager.java  (nonexistent)
+++ java/Base/Dungeon/DungeonManager.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dungeon;
+
+import java.io.File;
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
+import java.util.ArrayList;
+import java.util.HashMap;
+import java.util.List;
+import java.util.Map;
+import java.util.concurrent.ConcurrentHashMap;
+import java.util.concurrent.CopyOnWriteArrayList;
+import java.util.logging.Level;
+import java.util.logging.Logger;
+
+import net.sf.l2j.commons.pool.ConnectionPool;
+import net.sf.l2j.commons.pool.ThreadPool;
+
+import net.sf.l2j.gameserver.model.actor.Player;
+import net.sf.l2j.gameserver.model.location.Location;
+
+import org.w3c.dom.Document;
+import org.w3c.dom.NamedNodeMap;
+import org.w3c.dom.Node;
+
+import Base.XML.XMLDocumentFactory;
+
+/**
+ * @author Anarchy
+ */
+public class DungeonManager
+{
+	private static Logger log = Logger.getLogger(DungeonManager.class.getName());
+	
+	private Map<Integer, DungeonTemplate> templates;
+	private List<Dungeon> running;
+	private List<Integer> dungeonParticipants;
+	private boolean reloading = false;
+	private Map<String, Long[]> dungeonPlayerData;
+	
+	protected DungeonManager()
+	{
+		templates = new ConcurrentHashMap<>();
+		running = new CopyOnWriteArrayList<>();
+		dungeonParticipants = new CopyOnWriteArrayList<>();
+		dungeonPlayerData = new ConcurrentHashMap<>();
+		
+		load();
+		ThreadPool.scheduleAtFixedRate(() -> updateDatabase(), 1000*60*30, 1000*60*60);
+	}
+	
+	@SuppressWarnings("resource")
+	private void updateDatabase()
+	{
+		try (Connection con = ConnectionPool.getConnection())
+		{
+			PreparedStatement stm = con.prepareStatement("DELETE FROM dungeon");
+			stm.execute();
+			
+			for (String ip : dungeonPlayerData.keySet())
+			{
+				for (int i = 1; i < dungeonPlayerData.get(ip).length; i++)
+				{
+					PreparedStatement stm2 = con.prepareStatement("INSERT INTO dungeon VALUES (?,?,?)");
+					stm2.setInt(1, i);
+					stm2.setString(2, ip);
+					stm2.setLong(3, dungeonPlayerData.get(ip)[i]);
+					stm2.execute();
+					stm2.close();
+				}
+			}
+			
+			stm.close();
+		}
+		catch (Exception e)
+		{
+			e.printStackTrace();
+		}
+	}
+	
+	public boolean reload()
+	{
+		if (!running.isEmpty())
+		{
+			reloading = true;
+			return false;
+		}
+		
+		templates.clear();
+		load();
+		return true;
+	}
+	
+	@SuppressWarnings("resource")
+	private void load()
+	{	
+		try
+		{
+			File f = new File("./events/Dungeon.xml");
+			Document doc = XMLDocumentFactory.getInstance().loadDocument(f);
+			
+			Node n = doc.getFirstChild();
+			for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
+			{
+				if (d.getNodeName().equals("dungeon"))
+				{
+					int id = Integer.parseInt(d.getAttributes().getNamedItem("id").getNodeValue());
+					String name = d.getAttributes().getNamedItem("name").getNodeValue();
+					int players = Integer.parseInt(d.getAttributes().getNamedItem("players").getNodeValue());
+					Map<Integer, Integer> rewards = new HashMap<>();
+					String rewardHtm = d.getAttributes().getNamedItem("rewardHtm").getNodeValue();
+					Map<Integer, DungeonStage> stages = new HashMap<>();
+					
+					String rewards_data = d.getAttributes().getNamedItem("rewards").getNodeValue();
+					if(!rewards_data.isEmpty()) // If config is empty do not feed the rewards
+					{
+						String[] rewards_data_split = rewards_data.split(";");
+						for (String reward : rewards_data_split)
+						{
+							String[] reward_split = reward.split(",");
+							rewards.put(Integer.parseInt(reward_split[0]), Integer.parseInt(reward_split[1]));
+						}
+					}
+
+					for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
+					{
+						NamedNodeMap attrs = cd.getAttributes();
+						
+						if (cd.getNodeName().equals("stage"))
+						{
+							int order = Integer.parseInt(attrs.getNamedItem("order").getNodeValue());
+							String loc_data = attrs.getNamedItem("loc").getNodeValue();
+							String[] loc_data_split = loc_data.split(",");
+							Location loc = new Location(Integer.parseInt(loc_data_split[0]), Integer.parseInt(loc_data_split[1]), Integer.parseInt(loc_data_split[2]));
+							boolean teleport = Boolean.parseBoolean(attrs.getNamedItem("teleport").getNodeValue());
+							int minutes = Integer.parseInt(attrs.getNamedItem("minutes").getNodeValue());
+							Map<Integer, List<Location>> mobs = new HashMap<>();
+							
+							for (Node ccd = cd.getFirstChild(); ccd != null; ccd = ccd.getNextSibling())
+							{
+								NamedNodeMap attrs2 = ccd.getAttributes();
+								
+								if (ccd.getNodeName().equals("mob"))
+								{
+									int npcId = Integer.parseInt(attrs2.getNamedItem("npcId").getNodeValue());
+									List<Location> locs = new ArrayList<>();
+									
+									String locs_data = attrs2.getNamedItem("locs").getNodeValue();
+									String[] locs_data_split = locs_data.split(";");
+									for (String locc : locs_data_split)
+									{
+										String[] locc_data = locc.split(",");
+										locs.add(new Location(Integer.parseInt(locc_data[0]), Integer.parseInt(locc_data[1]), Integer.parseInt(locc_data[2])));
+									}
+									
+									mobs.put(npcId, locs);
+								}
+							}
+							
+							stages.put(order, new DungeonStage(order, loc, teleport, minutes, mobs));
+						}
+					}
+					
+					templates.put(id, new DungeonTemplate(id, name, players, rewards, rewardHtm, stages));
+				}
+			}
+		}
+		catch (Exception e)
+		{
+			log.log(Level.WARNING, "DungeonManager: Error loading dungeons.xml", e);
+			e.printStackTrace();
+		}
+		
+		try (Connection con = ConnectionPool.getConnection())
+		{
+			PreparedStatement stm = con.prepareStatement("SELECT * FROM dungeon");
+			ResultSet rset = stm.executeQuery();
+			
+			while (rset.next())
+			{
+				int dungid = rset.getInt("dungid");
+				String ipaddr = rset.getString("ipaddr");
+				long lastjoin = rset.getLong("lastjoin");
+				
+				if (!dungeonPlayerData.containsKey(ipaddr))
+				{
+					Long[] times = new Long[templates.size()+1];
+					for (int i = 0; i < times.length; i++)
+						times[i] = 0L;
+					times[dungid] = lastjoin;
+					
+					dungeonPlayerData.put(ipaddr, times);
+				}
+				else
+					dungeonPlayerData.get(ipaddr)[dungid] = lastjoin;
+			}
+			
+			rset.close();
+			stm.close();
+		}
+		catch (Exception e)
+		{
+			e.printStackTrace();
+		}
+		
+		log.info("DungeonManager: Loaded "+templates.size()+" dungeon templates");
+	}
+	
+	public synchronized void removeDungeon(Dungeon dungeon)
+	{
+		running.remove(dungeon);
+		
+		if (reloading && running.isEmpty())
+		{
+			reloading = false;
+			reload();
+		}
+	}
+	
+	public synchronized void enterDungeon(int id, Player player)
+	{
+		if (reloading)
+		{
+			player.sendMessage("The Dungeon system is reloading, please try again in a few minutes.");
+			return;
+		}
+	
+		
+		DungeonTemplate template = templates.get(id);
+		if (template.getPlayers() > 1 && (!player.isInParty() || player.getParty().getMembersCount() != template.getPlayers()))
+		{
+			player.sendMessage("You need a party of "+template.getPlayers()+" players to enter this Dungeon.");
+			return;
+		}
+		else if (template.getPlayers() == 1 && player.isInParty())
+		{
+			player.sendMessage("You can only enter this Dungeon alone.");
+			return;
+		}
+		
+		List<Player> players = new ArrayList<>();
+		if (player.isInParty())
+		{
+			for (Player pm : player.getParty().getMembers())
+			{
+				String pmip = pm.getIP();
+				if (dungeonPlayerData.containsKey(pmip) && (System.currentTimeMillis() - dungeonPlayerData.get(pmip)[template.getId()] < 1000*60*60*12))
+				{
+					player.sendMessage("One of your party members cannot join this Dungeon because 12 hours have not passed since they last joined.");
+					return;
+				}
+			}
+			
+			for (Player pm : player.getParty().getMembers())
+			{
+				String pmip = pm.getIP();
+				
+				dungeonParticipants.add(pm.getObjectId());
+				players.add(pm);
+				if (dungeonPlayerData.containsKey(pmip))
+					dungeonPlayerData.get(pmip)[template.getId()] = System.currentTimeMillis();
+				else
+				{
+					Long[] times = new Long[templates.size()+1];
+					for (int i = 0; i < times.length; i++)
+						times[i] = 0L;
+					times[template.getId()] = System.currentTimeMillis();
+					dungeonPlayerData.put(pmip, times);
+				}
+			}
+		}
+		else
+		{
+			String pmip = player.getIP();
+			if (dungeonPlayerData.containsKey(pmip) && (System.currentTimeMillis() - dungeonPlayerData.get(pmip)[template.getId()] < 1000*60*60*12))
+			{
+				player.sendMessage("12 hours have not passed since you last entered this Dungeon.");
+				return;
+			}
+			
+			dungeonParticipants.add(player.getObjectId());
+			players.add(player);
+			if (dungeonPlayerData.containsKey(pmip))
+				dungeonPlayerData.get(pmip)[template.getId()] = System.currentTimeMillis();
+			else
+			{
+				Long[] times = new Long[templates.size()+1];
+				for (int i = 0; i < times.length; i++)
+					times[i] = 0L;
+				times[template.getId()] = System.currentTimeMillis();
+				dungeonPlayerData.put(pmip, times);
+			}
+		}
+		
+		running.add(new Dungeon(template, players));
+	}
+	
+	public boolean isReloading()
+	{
+		return reloading;
+	}
+	
+	public boolean isInDungeon(Player player)
+	{
+		for (Dungeon dungeon : running)
+			for (Player p : dungeon.getPlayers())
+				if (p == player)
+					return true;
+		
+		return false;
+	}
+	
+	public Map<String, Long[]> getPlayerData()
+	{
+		return dungeonPlayerData;
+	}
+	
+	public List<Integer> getDungeonParticipants()
+	{
+		return dungeonParticipants;
+	}
+	
+	public static DungeonManager getInstance()
+	{
+		return SingletonHolder.instance;
+	}
+	
+	private static class SingletonHolder
+	{
+		protected static final DungeonManager instance = new DungeonManager();
+	}
+}


### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Dungeon/DungeonStage.java
===================================================================
--- java/Base/Dungeon/DungeonStage.java  (nonexistent)
+++ java/Base/Dungeon/DungeonStage.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dungeon;
+
+import java.util.List;
+import java.util.Map;
+
+import net.sf.l2j.gameserver.model.location.Location;
+
+
+
+/**
+ * @author Anarchy
+ *
+ */
+public class DungeonStage
+{
+	private int order;
+	private Location location;
+	private boolean teleport;
+	private int minutes;
+	private Map<Integer, List<Location>> mobs;
+	
+	public DungeonStage(int order, Location location, boolean teleport, int minutes, Map<Integer, List<Location>> mobs)
+	{
+		this.order = order;
+		this.location = location;
+		this.teleport = teleport;
+		this.minutes = minutes;
+		this.mobs = mobs;
+	}
+	
+	public int getOrder()
+	{
+		return order;
+	}
+	
+	public Location getLocation()
+	{
+		return location;
+	}
+	
+	public boolean teleport()
+	{
+		return teleport;
+	}
+	
+	public int getMinutes()
+	{
+		return minutes;
+	}
+	
+	public Map<Integer, List<Location>> getMobs()
+	{
+		return mobs;
+	}
+}

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Dungeon/DungeonTemplate.java
===================================================================
--- java/Base/Dungeon/DungeonTemplate.java  (nonexistent)
+++ java/Base/Dungeon/DungeonTemplate.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dungeon;
+
+import java.util.Map;
+
+/**
+ * @author Anarchy
+ *
+ */
+public class DungeonTemplate
+{
+	private int id;
+	private String name;
+	private int players;
+	private Map<Integer, Integer> rewards;
+	private String rewardHtm;
+	private Map<Integer, DungeonStage> stages;
+	
+	public DungeonTemplate(int id, String name, int players, Map<Integer, Integer> rewards, String rewardHtm, Map<Integer, DungeonStage> stages)
+	{
+		this.id = id;
+		this.name = name;
+		this.players = players;
+		this.rewards = rewards;
+		this.rewardHtm = rewardHtm;
+		this.stages = stages;
+	}
+	
+	public int getId()
+	{
+		return id;
+	}
+	
+	public String getName()
+	{
+		return name;
+	}
+	
+	public int getPlayers()
+	{
+		return players;
+	}
+	
+	public Map<Integer, Integer> getRewards()
+	{
+		return rewards;
+	}
+	
+	public String getRewardHtm()
+	{
+		return rewardHtm;
+	}
+	
+	public Map<Integer, DungeonStage> getStages()
+	{
+		return stages;
+	}
+}

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Dungeon/Instance.java
===================================================================
--- java/Base/Dungeon/Instance.java  (nonexistent)
+++ java/Base/Dungeon/Instance.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dungeon;
+
+import java.util.ArrayList;
+import java.util.List;
+
+import net.sf.l2j.gameserver.model.actor.instance.Door;
+
+/**
+ * @author Anarchy
+ *
+ */
+public class Instance
+{
+	private int id;
+	private List<Door> doors;
+	
+	public Instance(int id)
+	{
+		this.id = id;
+		doors = new ArrayList<>();
+	}
+	
+	public void openDoors()
+	{
+		for (Door door : doors)
+			door.openMe();
+	}
+	
+	public void closeDoors()
+	{
+		for (Door door : doors)
+			door.closeMe();
+	}
+	
+	public void addDoor(Door door)
+	{
+		doors.add(door);
+	}
+	
+	public List<Door> getDoors()
+	{
+		return doors;
+	}
+	
+	public int getId()
+	{
+		return id;
+	}
+}

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Dungeon/InstanceIdFactory.java
===================================================================
--- java/Base/Dungeon/InstanceIdFactory.java  (nonexistent)
+++ java/Base/Dungeon/InstanceIdFactory.java   (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dungeon;
+
+import java.util.concurrent.atomic.AtomicInteger;
+
+/**
+ * @author Anarchy
+ *
+ */
+public final class InstanceIdFactory
+{
+	private static AtomicInteger nextAvailable = new AtomicInteger(1);
+	
+	public synchronized static int getNextAvailable()
+	{
+		return nextAvailable.getAndIncrement();
+	}
+}

### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/Base/Dungeon/InstanceManager.java
===================================================================
--- java/Base/Dungeon/InstanceManager.java  (nonexistent)
+++ java/Base/Dungeon/InstanceManager.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+ * 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dungeon;
+
+import java.util.Map;
+import java.util.concurrent.ConcurrentHashMap;
+
+import net.sf.l2j.gameserver.model.actor.instance.Door;
+
+/**
+ * @author Anarchy
+ *
+ */
+public class InstanceManager
+{
+	private Map<Integer, Instance> instances;
+	
+	protected InstanceManager()
+	{
+		instances = new ConcurrentHashMap<>();
+		instances.put(0, new Instance(0));
+	}
+	
+	public void addDoor(int id, Door door)
+	{
+		if (!instances.containsKey(id) || id == 0)
+			return;
+		
+		instances.get(id).addDoor(door);
+	}
+	
+	public void deleteInstance(int id)
+	{
+		if (id == 0)
+		{
+			System.out.println("Attempt to delete instance with id 0.");
+			return;
+		}
+		
+		// delete doors
+	}
+	
+	public synchronized Instance createInstance()
+	{
+		Instance instance = new Instance(InstanceIdFactory.getNextAvailable());
+		instances.put(instance.getId(), instance);
+		return instance;
+	}
+	
+	public Instance getInstance(int id)
+	{
+		return instances.get(id);
+	}
+	
+	public static InstanceManager getInstance()
+	{
+		return SingletonHolder.instance;
+	}
+	
+	private static final class SingletonHolder
+	{
+		protected static final InstanceManager instance = new InstanceManager();
+	}
+}



### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/network/clientpackets/EnterWorld.java
===================================================================
--- java/net/sf/l2j/gameserver/network/clientpackets/EnterWorld.java  (nonexistent)
+++ java/net/sf/l2j/gameserver/network/clientpackets/EnterWorld.java    (working copy)

	@Override
	protected void runImpl()
	{
		final Player player = getClient().getPlayer();
		if (player == null)
		{
			getClient().closeNow();
			return;
		}
	+	// Set NewChar
	+	switch (player.getClassId().getId())
	+	{
	+		case 0:
	+		case 10:
	+		case 18:
	+		case 25:
	+		case 31:
	+		case 38:
	+		case 44:
	+		case 49:
	+		case 53:
	+			Player.doNewChar(player, 1);
	+			break;
	+	}		
		
		
		
		getClient().setState(GameClientState.IN_GAME);






		if (Config.PLAYER_SPAWN_PROTECTION > 0)
			player.setSpawnProtection(true);
		
		player.spawnMe();
		
+		if (player.getMemos().getLong("newEndTime", 0) > 0)
+			onEnterNewChar(player);		



		// Tutorial
		final QuestState qs = player.getQuestList().getQuestState("Tutorial");
		if (qs != null)
			qs.getQuest().notifyEvent("UC", null, player);
		
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
+	private static void onEnterNewChar(Player activeChar)
+	{
+		long now = Calendar.getInstance().getTimeInMillis();
+		long endDay = activeChar.getMemos().getLong("newEndTime");
+
+		if (now > endDay)
+			Player.removeNewChar(activeChar);
+		else
+		{
+			activeChar.setNewChar(true);
+			activeChar.broadcastUserInfo();
+		}
+	}	

	@Override
	protected boolean triggersOnActionRequest()
	{
		return false;
	}



### Eclipse Workspace Patch 1.0
#P aCis-Gameserver
Index: java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java
===================================================================
--- java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java  (nonexistent)
+++ java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java    (working copy)
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.instance.OlympiadManagerNpc;
+import net.sf.l2j.gameserver.model.item.instance.ItemInstance;




import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.gameserver.scripting.QuestState;

+import Base.Dungeon.InstanceManager;






		else if (_command.startsWith("player_help "))
		{
			final String path = _command.substring(12);
			if (path.indexOf("..") != -1)
				return;
			
			final StringTokenizer st = new StringTokenizer(path);
			final String[] cmd = st.nextToken().split("#");
			
			final NpcHtmlMessage html = new NpcHtmlMessage(0);
			html.setFile("data/html/help/" + cmd[0]);
			if (cmd.length > 1)
			{
				final int itemId = Integer.parseInt(cmd[1]);
				html.setItemId(itemId);
				
				if (itemId == 7064 && cmd[0].equalsIgnoreCase("lidias_diary/7064-16.htm"))
				{
					final QuestState qs = player.getQuestList().getQuestState("Q023_LidiasHeart");
					if (qs != null && qs.getCond() == 5 && qs.getInteger("diary") == 0)
						qs.set("diary", "1");
				}
			}
			html.disableValidation();
			player.sendPacket(html);
		}
		
+		if (_command.startsWith("bp_reward"))
+		{
+			int type = Integer.parseInt(_command.substring(10));
+			int itemId = 0;
+			int count = 1;
+			
+			switch (type)
+			{
+				case 0:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL0;
+					
+					break;
+				}
+				case 1:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL1;
+					
+					break;
+				}
+				case 2:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL2;
+					
+					break;
+				}
+				case 3:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL3;
+					
+					break;
+				}
+				case 4:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL4;
+					
+					break;
+				}
+				case 5:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL5;
+					
+					break;
+				}
+				case 6:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL6;
+					
+					break;
+				}
+				case 7:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL7;
+					
+					break;
+				}
+				case 8:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL8;
+					
+					break;
+				}
+				case 9:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL9;
+					
+					break;
+				}
+				case 10:
+				{
+					itemId = Config.DUNGEON_ITEM_RENEWAL10;
+					
+					break;
+				}
+				case 11:
+				{
+					itemId = Config.DUNGEON_PARTY_ITEM_RENEWAL0;
+					
+					break;
+				}
+				case 12:
+				{
+					itemId = Config.DUNGEON_PARTY_ITEM_RENEWAL1;
+					
+					break;
+				}
+				case 13:
+				{
+					itemId = Config.DUNGEON_PARTY_ITEM_RENEWAL2;
+					
+					break;
+				}
+				case 14:
+				{
+					itemId = Config.DUNGEON_PARTY_ITEM_RENEWAL3;
+					
+					break;
+				}
+				case 15:
+				{
+					itemId = Config.DUNGEON_PARTY_ITEM_RENEWAL4;
+					
+					break;
+				}
+			}
+			
+			if (itemId == 0)
+			{
+				System.out.println(player.getName() + " tried to send custom id on dungeon solo rewards.");
+				return;
+			}
+			
+            if(player.getDungeon() != null)
+           {                         	  
+				ItemInstance item = player.addItem("dungeon reward", itemId, count, null, true);
+				item.setEnchantLevel(25);
+				player.getInventory().equipItemAndRecord(item);
+               PlayerMemo.setVar(player, "delete_temp_item_" + item.getObjectId(), item.getObjectId(), System.currentTimeMillis() + (1000 *60 *60 *5)); 
+				InstanceManager.getInstance().getInstance(0);
+				player.setDungeon(null);						
+				player.teleportTo(Config.DUNGEON_SPAWN_X, Config.DUNGEON_SPAWN_Y, Config.DUNGEON_SPAWN_Z, Config.DUNGEON_SPAWN_RND);	                  	                            	   
+           }
+            else 
+           {
+            	player.sendMessage("No puedes recibir el premio");  
+           }
+        	   						
+		}			

		else if (_command.startsWith("npc_"))




Gameserver/data/xml/npc

DungeonManagerNpc.xml
+<?xml version="1.0" encoding="utf-8"?>
+<list>
+		<npc id="65514" idTemplate="31228" name="Dungeon Manager" title="Renewal Dungeon">
+		<set name="usingServerSideName" val="true"/>
+		<set name="usingServerSideTitle" val="true"/>
+		<set name="level" val="78"/>
+		<set name="radius" val="9"/>
+		<set name="height" val="16"/>
+		<set name="rHand" val="0"/>
+		<set name="lHand" val="0"/>
+		<set name="type" val="DungeonManagerNpc"/>
+		<set name="exp" val="18299"/>
+		<set name="sp" val="2212"/>
+		<set name="hp" val="3010.8929976"/>
+		<set name="mp" val="1777.86"/>
+		<set name="hpRegen" val="8.772"/>
+		<set name="mpRegen" val="3.1008"/>
+		<set name="pAtk" val="961.93701102"/>
+		<set name="pDef" val="361.0859925"/>
+		<set name="mAtk" val="677.644106544"/>
+		<set name="mDef" val="246.330714"/>
+		<set name="crit" val="4"/>
+		<set name="atkSpd" val="253"/>
+		<set name="str" val="40"/>
+		<set name="int" val="21"/>
+		<set name="dex" val="30"/>
+		<set name="wit" val="20"/>
+		<set name="con" val="43"/>
+		<set name="men" val="20"/>
+		<set name="corpseTime" val="7"/>
+		<set name="walkSpd" val="45"/>
+		<set name="runSpd" val="175"/>
+		<set name="dropHerbGroup" val="1"/>
+		<set name="attackRange" val="40"/>
+		<ai type="DEFAULT" ssCount="100" ssRate="10" spsCount="100" spsRate="5" aggro="500" canMove="true" seedable="true"/>
+
+	</npc>
+	
+</list>



DungeonMob.xml
+<?xml version="1.0" encoding="utf-8"?>
+<list>
+	<npc id="65515" idTemplate="20620" name="Dungeon Monster" title="Event">
+		<set name="usingServerSideName" val="true"/>
+		<set name="usingServerSideTitle" val="true"/>
+		<set name="level" val="78"/>
+		<set name="radius" val="21"/>
+		<set name="height" val="35"/>
+		<set name="rHand" val="0"/>
+		<set name="lHand" val="0"/>
+		<set name="type" val="DungeonMob"/>
+		<set name="exp" val="18299"/>
+		<set name="sp" val="2212"/>
+		<set name="hp" val="3010.8929976"/>
+		<set name="mp" val="1777.86"/>
+		<set name="hpRegen" val="8.772"/>
+		<set name="mpRegen" val="3.1008"/>
+		<set name="pAtk" val="961.93701102"/>
+		<set name="pDef" val="361.0859925"/>
+		<set name="mAtk" val="677.644106544"/>
+		<set name="mDef" val="246.330714"/>
+		<set name="crit" val="4"/>
+		<set name="atkSpd" val="253"/>
+		<set name="str" val="40"/>
+		<set name="int" val="21"/>
+		<set name="dex" val="30"/>
+		<set name="wit" val="20"/>
+		<set name="con" val="43"/>
+		<set name="men" val="20"/>
+		<set name="corpseTime" val="7"/>
+		<set name="walkSpd" val="45"/>
+		<set name="runSpd" val="175"/>
+		<set name="dropHerbGroup" val="1"/>
+		<set name="attackRange" val="40"/>
+		<ai type="DEFAULT" ssCount="100" ssRate="10" spsCount="100" spsRate="5" aggro="500" canMove="true" seedable="true"/>
+	<skills>
+	<skill id="4028" level="3"/>
+	<skill id="4408" level="11"/>
+	<skill id="4410" level="12"/>
+	<skill id="4411" level="12"/>
+	<skill id="4412" level="10"/>
+	<skill id="4413" level="10"/>
+	<skill id="4416" level="11"/>
+	</skills>
+	
+	<drops>
+	<category id="0">
+	<drop itemid="57" min="10000" max="15000" chance="1000000"/>
+	</category>
+	</drops>
+	
+	</npc>
+</list>



gameserver/events/Dungeon.xml
+<?xml version="1.0" encoding="UTF-8"?>
+<list>
+	<dungeon id="1" name="Solo Story Mode" players="1" rewards="9500,20000,9501,50" rewardHtm="data/html/mods/dungeon/solo_rewards.htm">
+		<stage order="1" loc="174122,-76173,-5106" teleport="true" minutes="5">
+			<mob npcId="65515" locs="174527,-75636,-5106;174654,-75987,-5106;174647,-76483,-5106;174160,-76758,-5106"/>
+		</stage>
+		<stage order="2" loc="174082,-78151,-5106" teleport="false" minutes="5">
+			<mob npcId="65515" locs="174527,-75636,-5106;174654,-75987,-5106;174647,-76483,-5106;174160,-76758,-5106"/>
+		</stage>
+		<stage order="3" loc="174038,-80727,-5106" teleport="true" minutes="5">
+			<mob npcId="65515" locs="173739,-81254,-5122;173736,-81613,-5122;173729,-82232,-5122;174041,-82539,-5122;174377,-82379,-5122;174451,-81893,-5122;174433,-81342,-5122;174152,-81560,-5122"/>
+		</stage>
+		<stage order="4" loc="174230,-87512,-5116" teleport="true" minutes="15">
+			<mob npcId="65515" locs="174229,-86659,-5106"/>
+		</stage>
+	</dungeon>
+	<dungeon id="2" name="Quatro Hard Mode" players="4" rewards="8191,1" rewardHtm="NULL">
+		<stage order="1" loc="174122,-76173,-5106" teleport="true" minutes="5">
+			<mob npcId="62100" locs="174527,-75636,-5106;174654,-75987,-5106;174647,-76483,-5106;174160,-76758,-5106"/>
+		</stage>
+		<stage order="2" loc="174082,-78151,-5106" teleport="false" minutes="5">
+			<mob npcId="62100" locs="174527,-75636,-5106;174654,-75987,-5106;174647,-76483,-5106;174160,-76758,-5106"/>
+		</stage>
+		<stage order="3" loc="174038,-80727,-5106" teleport="true" minutes="5">
+			<mob npcId="62101" locs="173739,-81254,-5122;173736,-81613,-5122;173729,-82232,-5122;174041,-82539,-5122;174377,-82379,-5122;174451,-81893,-5122;174433,-81342,-5122;174152,-81560,-5122"/>
+		</stage>
+		<stage order="4" loc="174056,-83856,-5106" teleport="true" minutes="5">
+			<mob npcId="62101" locs="174742,-84194,-5106;174745,-84526,-5106;174726,-85127,-5106;174231,-85361,-5106"/>
+		</stage>
+		<stage order="5" loc="174229,-86659,-5106" teleport="true" minutes="5">
+			<mob npcId="62102" locs="174742,-84194,-5106;174745,-84526,-5106;174726,-85127,-5106;174231,-85361,-5106"/>
+		</stage>
+		<stage order="6" loc="174229,-86659,-5106" teleport="false" minutes="5">
+			<mob npcId="62103" locs="174229,-86659,-5106"/>
+		</stage>
+		<stage order="7" loc="174229,-86659,-5106" teleport="false" minutes="10">
+			<mob npcId="62104" locs="174229,-86659,-5106"/>
+		</stage>
+	</dungeon>
+</list>



gameserver/events/Dungeon_Html_Reward.properties
+#Item Id Prince L2DungeonManager
+DungeonCoinId = 57
+# ID of the item that will be Buy to Dungeon
+DungeonContItem = 1
+
+#Cordenadas para la vuelta del jugador al terminar el evento
+DungeonSpawnX = 82732
+DungeonSpawnY = 149316
+DungeonSpawnZ = -3495
+DungeonSpawnRnd = 25
+
+
+#Renewal Dungeon Open Html Finishi!
+DungeonRenewalHtml0 = 6657
+DungeonRenewalHtml1 = 6656
+DungeonRenewalHtml2 = 6658
+DungeonRenewalHtml3 = 6659
+DungeonRenewalHtml4 = 6660
+DungeonRenewalHtml5 = 8191
+DungeonRenewalHtml6 = 49997
+DungeonRenewalHtml7 = 49998
+DungeonRenewalHtml8 = 50000
+DungeonRenewalHtml9 = 59989
+DungeonRenewalHtml10 = 52366
+
+
+DungeonPartyRenewalHtml0 = 58012
+DungeonPartyRenewalHtml1 = 50051
+DungeonPartyRenewalHtml2 = 59999
+DungeonPartyRenewalHtml3 = 52030
+DungeonPartyRenewalHtml4 = 52031
+DungeonPartyRenewalHtml5 = 52032




Archivos sql y html bajar importante: https://www.mediafire.com/file/ntbn69340fjbops/Dungeon.rar/file







