### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/Config.java
===================================================================
--- head-src/com/l2jfrozen/Config.java   (nonexistent)
+++ head-src/com/l2jfrozen/Config.java   (working copy)

import com.l2jfrozen.util.StringUtil;
+import Base.Config.ExProperties;




public final class Config
{
+	public static final String DUNGEON_REWARD_FILE = "./events/Dungeon_Html_Reward.properties";







			e.printStackTrace();
			throw new Error("Failed to Load " + OTHER + " File.");
		}
	}
	
+	public static ExProperties load(String filename)
+	{
+		return load(new File(filename));
+	}
+	
+	public static ExProperties load(File file)
+	{
+		ExProperties result = new ExProperties();
+		
+		try
+		{
+			result.load(file);
+		}
+		catch (IOException e)
+		{
+			LOGGER.warn("Error loading config : " + file.getName() + "!");
+		}
+		
+		return result;
+	}			
	// ============================================================
	public static float RATE_XP;














	public static String PVP1_CUSTOM_MESSAGE;
	public static String PVP2_CUSTOM_MESSAGE;
	
+ 	public static int DUNGEON_COIN_ID; 	
+ 	public static int CONT_DUNGEON_ITEM;
+   public static int DUNGEON_SPAWN_X;
+   public static int DUNGEON_SPAWN_Y;
+   public static int DUNGEON_SPAWN_Z;
+   public static int DUNGEON_SPAWN_RND; 
 	
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







			ONLINE_PLAYERS_ON_LOGIN = Boolean.valueOf(L2JFrozenSettings.getProperty("OnlineOnLogin", "False"));
			SHOW_SERVER_VERSION = Boolean.valueOf(L2JFrozenSettings.getProperty("ShowServerVersion", "False"));
		


+			// Dungeon
+			ExProperties SafeDungeon = load(DUNGEON_REWARD_FILE);
+			
+			DUNGEON_COIN_ID = SafeDungeon.getProperty("DungeonCoinId", 57);
+			CONT_DUNGEON_ITEM = SafeDungeon.getProperty("DungeonContItem", 1);
+			DUNGEON_SPAWN_X = SafeDungeon.getProperty("DungeonSpawnX", 82635);
+			DUNGEON_SPAWN_Y = SafeDungeon.getProperty("DungeonSpawnY", 148798);
+			DUNGEON_SPAWN_Z = SafeDungeon.getProperty("DungeonSpawnZ", -3464);
+			DUNGEON_SPAWN_RND = SafeDungeon.getProperty("DungeonSpawnRnd", 25);	
+			
+			DUNGEON_ITEM_RENEWAL0 = SafeDungeon.getProperty("DungeonRenewalHtml0", 15);
+			DUNGEON_ITEM_RENEWAL1 = SafeDungeon.getProperty("DungeonRenewalHtml1", 15);
+			DUNGEON_ITEM_RENEWAL2 = SafeDungeon.getProperty("DungeonRenewalHtml2", 15);
+			DUNGEON_ITEM_RENEWAL3 = SafeDungeon.getProperty("DungeonRenewalHtml3", 15);
+			DUNGEON_ITEM_RENEWAL4 = SafeDungeon.getProperty("DungeonRenewalHtml4", 15);
+			DUNGEON_ITEM_RENEWAL5 = SafeDungeon.getProperty("DungeonRenewalHtml5", 15);
+			DUNGEON_ITEM_RENEWAL6 = SafeDungeon.getProperty("DungeonRenewalHtml6", 15);	
+			DUNGEON_ITEM_RENEWAL7 = SafeDungeon.getProperty("DungeonRenewalHtml7", 15);	
+			DUNGEON_ITEM_RENEWAL8 = SafeDungeon.getProperty("DungeonRenewalHtml8", 15);	
+			DUNGEON_ITEM_RENEWAL9 = SafeDungeon.getProperty("DungeonRenewalHtml9", 15);	
+			DUNGEON_ITEM_RENEWAL10 = SafeDungeon.getProperty("DungeonRenewalHtml10", 15);	









### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/Base/Dev/Dungeon/Dungeon.java
===================================================================
--- head-src/Base/Dev/Dungeon/Dungeon.java   (nonexistent)
+++ head-src/Base/Dev/Dungeon/Dungeon.java    (working copy)
+/*
+ * This program is free software: you can redistribute it and/or modify it under
+ * the terms of the GNU General Public License as published by the Free Software
+ * Foundation, either version 3 of the License, or (at your option) any later
+ * version.
+* 
+ * This program is distributed in the hope that it will be useful, but WITHOUT
+ * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
+ * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
+ * details.
+ * 
+ * You should have received a copy of the GNU General Public License along with
+ * this program. If not, see <http://www.gnu.org/licenses/>.
+ */
+package Base.Dev.Dungeon;
+
+import java.util.List;
+import java.util.Map.Entry;
+import java.util.concurrent.CopyOnWriteArrayList;
+import java.util.concurrent.ScheduledFuture;
+
+
+import com.l2jfrozen.Config;
+import com.l2jfrozen.gameserver.datatables.sql.NpcTable;
+import com.l2jfrozen.gameserver.datatables.sql.SpawnTable;
+import com.l2jfrozen.gameserver.model.Location;
+import com.l2jfrozen.gameserver.model.actor.instance.L2DungeonMobInstance;
+import com.l2jfrozen.gameserver.model.actor.instance.L2PcInstance;
+import com.l2jfrozen.gameserver.model.spawn.L2Spawn;
+import com.l2jfrozen.gameserver.network.serverpackets.ExShowScreenMessage;
+import com.l2jfrozen.gameserver.network.serverpackets.ExShowScreenMessage.SMPOS;
+import com.l2jfrozen.gameserver.network.serverpackets.MagicSkillUser;
+import com.l2jfrozen.gameserver.network.serverpackets.NpcHtmlMessage;
+import com.l2jfrozen.gameserver.templates.L2NpcTemplate;
+import com.l2jfrozen.gameserver.thread.ThreadPool;
+
+import Base.Memo.PlayerMemo;
+
+
+
+/**
+ * @author Anarchy
+ */
+public class Dungeon
+{
+	private DungeonTemplate template;
+	private List<L2PcInstance> players;
+	private ScheduledFuture<?> dungeonCancelTask = null;
+	private ScheduledFuture<?> nextTask = null;
+	private ScheduledFuture<?> timerTask = null;
+	private DungeonStage currentStage = null;
+	private long stageBeginTime = 0;
+	private List<L2DungeonMobInstance> mobs = new CopyOnWriteArrayList<>();
+	private Instance instance;
+	
+	public Dungeon(DungeonTemplate template, List<L2PcInstance> players)
+	{
+		this.template = template;
+		this.players = players;
+		instance = InstanceManager.getInstance().createInstance();
+		
+		for (L2PcInstance player : players)
+			player.setDungeon(this);
+		
+		beginTeleport();
+	}
+	
+	public void onPlayerDeath(L2PcInstance player)
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
+	public synchronized void onMobKill(L2DungeonMobInstance mob)
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
+			for (L2PcInstance player : players)
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
+		for (L2PcInstance player : players)
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
+			for (L2PcInstance player : players)
+				player.sendPacket(htm);
+		}
+		else
+		{
+			for (L2PcInstance player : players)
+			{
+				player.setInstance(InstanceManager.getInstance().getInstance(0), true);
+                player.teleToLocation(Config.DUNGEON_SPAWN_X, Config.DUNGEON_SPAWN_Y, Config.DUNGEON_SPAWN_Z, Config.DUNGEON_SPAWN_RND);
+			}
+		}
+	}
+	
+	private void teleToStage()
+	{
+		if (!currentStage.teleport())
+			return;
+		
+		for (L2PcInstance player : players)
+			player.teleToLocation(currentStage.getLocation(), 25);
+	}
+	
+	private void teleToTown()
+	{
+		for (L2PcInstance player : players)
+		{
+			if (!player.isOnline2() || player.getClient().isDetached())
+				continue;
+			
+			DungeonManager.getInstance().getDungeonParticipants().remove(player.getObjectId());
+			player.setDungeon(null);
+			player.setInstance(InstanceManager.getInstance().getInstance(0), true);
+                player.teleToLocation(Config.DUNGEON_SPAWN_X, Config.DUNGEON_SPAWN_Y, Config.DUNGEON_SPAWN_Z, Config.DUNGEON_SPAWN_RND);
+		}
+	}
+	
+	private void cancelDungeon()
+	{
+		for (L2PcInstance player : players)
+		{
+			if (player.isDead())
+				player.doRevive();
+			
+			player.abortAttack();
+			player.abortCast();
+		}
+		
+		
+		for (L2DungeonMobInstance mob : mobs)
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
+	private void deleteMob(L2DungeonMobInstance mob)
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
+		L2NpcTemplate template = NpcTable.getInstance().getTemplate(mobId);
+		try
+		{
+			for (Location loc : locations)
+			{
+				L2Spawn spawn = new L2Spawn(template);
+				spawn.setLocx(loc.getX());
+				spawn.setLocy(loc.getY());
+				spawn.setLocz(loc.getZ());
+				
+				spawn.setRespawnDelay(1);
+				spawn.doSpawn(false);
+				
+				((L2DungeonMobInstance) spawn.getNpc()).setDungeon(this);
+				spawn.getNpc().setInstance(instance, false); // Set instance first
+				// SpawnTable.getInstance().addNewSpawn(spawn, false); // TODO: Useless
+				spawn.getNpc().broadcastStatusUpdate();
+				
+				// Add it to memory
+				mobs.add((L2DungeonMobInstance) spawn.getNpc());
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
+		for (L2PcInstance player : players)
+			player.setInstance(instance, true);
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
+		for (L2PcInstance player : players)
+		{
+			player.broadcastPacket(new MagicSkillUser(player, 1050, 1, 10000, 10000));
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
+		for (L2PcInstance player : players)
+			player.sendPacket(packet);
+	}
+	
+	private void broadcastScreenMessage(String msg, int seconds)
+	{
+		ExShowScreenMessage packet = new ExShowScreenMessage(msg, seconds * 1000, SMPOS.TOP_CENTER, false);
+		for (L2PcInstance player : players)
+			player.sendPacket(packet);
+	}
+	
+	public List<L2PcInstance> getPlayers()
+	{
+		return players;
+	}
+}

### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/Base/Dev/Dungeon/DungeonManager.java
===================================================================
--- head-src/Base/Dev/Dungeon/DungeonManager.java   (nonexistent)
+++ head-src/Base/Dev/Dungeon/DungeonManager.java   (working copy)
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
+package Base.Dev.Dungeon;
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
+
+
+import org.w3c.dom.Document;
+import org.w3c.dom.NamedNodeMap;
+import org.w3c.dom.Node;
+
+import com.l2jfrozen.gameserver.model.Location;
+import com.l2jfrozen.gameserver.model.actor.instance.L2PcInstance;
+import com.l2jfrozen.gameserver.thread.ThreadPool;
+import com.l2jfrozen.util.database.L2DatabaseFactory;
+
+import Base.XML.XMLDocumentFactory;
+
+/**
+ * @author Juvenil Walker
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
+		ThreadPool.scheduleAtFixedRate(() -> updateDatabase(), 1000 * 60 * 30, 1000 * 60 * 60);
+	}
+	
+	private void updateDatabase()
+	{
+		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
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
+					if (!rewards_data.isEmpty()) // If config is empty do not feed the rewards
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
+		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
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
+					Long[] times = new Long[templates.size() + 1];
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
+		log.info("DungeonManager: Loaded " + templates.size() + " dungeon templates");
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
+	public synchronized void enterDungeon(int id, L2PcInstance player)
+	{
+		if (reloading)
+		{
+			player.sendMessage("The Dungeon system is reloading, please try again in a few minutes.");
+			return;
+		}
+		
+		DungeonTemplate template = templates.get(id);
+		if (template.getPlayers() > 1 && (!player.isInParty() || player.getParty().getMemberCount() != template.getPlayers()))
+		{
+			player.sendMessage("You need a party of " + template.getPlayers() + " players to enter this Dungeon.");
+			return;
+		}
+		else if (template.getPlayers() == 1 && player.isInParty())
+		{
+			player.sendMessage("You can only enter this Dungeon alone.");
+			return;
+		}
+		
+		List<L2PcInstance> players = new ArrayList<>();
+		if (player.isInParty())
+		{
+			for (L2PcInstance pm : player.getParty().getPartyMembers())
+			{
+				String pmip = pm.getIP();
+				if (dungeonPlayerData.containsKey(pmip) && (System.currentTimeMillis() - dungeonPlayerData.get(pmip)[template.getId()] < 1000 * 60 * 60 * 24))
+				{
+					player.sendMessage("One of your party members cannot join this Dungeon because 24 hours have not passed since they last joined.");
+					return;
+				}
+			}
+			
+			for (L2PcInstance pm : player.getParty().getPartyMembers())
+			{
+				String pmip = pm.getIP();
+				
+				dungeonParticipants.add(pm.getObjectId());
+				players.add(pm);
+				if (dungeonPlayerData.containsKey(pmip))
+					dungeonPlayerData.get(pmip)[template.getId()] = System.currentTimeMillis();
+				else
+				{
+					Long[] times = new Long[templates.size() + 1];
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
+			if (dungeonPlayerData.containsKey(pmip) && (System.currentTimeMillis() - dungeonPlayerData.get(pmip)[template.getId()] < 1000 * 60 * 60 * 24))
+			{
+				player.sendMessage("24 hours have not passed since you last entered this Dungeon.");
+				return;
+			}
+			
+			dungeonParticipants.add(player.getObjectId());
+			players.add(player);
+			if (dungeonPlayerData.containsKey(pmip))
+				dungeonPlayerData.get(pmip)[template.getId()] = System.currentTimeMillis();
+			else
+			{
+				Long[] times = new Long[templates.size() + 1];
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
+	public boolean isInDungeon(L2PcInstance player)
+	{
+		for (Dungeon dungeon : running)
+			for (L2PcInstance p : dungeon.getPlayers())
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
#P L2jFrozen_GameServer
Index: head-src/Base/Dev/Dungeon/DungeonStage.java
===================================================================
--- head-src/Base/Dev/Dungeon/DungeonStage.java   (nonexistent)
+++ head-src/Base/Dev/Dungeon/DungeonStage.java   (working copy)
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
+package Base.Dev.Dungeon;
+
+import java.util.List;
+import java.util.Map;
+
+import com.l2jfrozen.gameserver.model.Location;
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
#P L2jFrozen_GameServer
Index: head-src/Base/Dev/Dungeon/DungeonTemplate.java
===================================================================
--- head-src/Base/Dev/Dungeon/DungeonTemplate.java   (nonexistent)
+++ head-src/Base/Dev/Dungeon/DungeonTemplate.java   (working copy)
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
+package Base.Dev.Dungeon;
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
#P L2jFrozen_GameServer
Index: head-src/Base/Dev/Dungeon/Instance.java
===================================================================
--- head-src/Base/Dev/Dungeon/Instance.java   (nonexistent)
+++ head-src/Base/Dev/Dungeon/Instance.java   (working copy)
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
+package Base.Dev.Dungeon;
+
+import java.util.ArrayList;
+import java.util.List;
+
+import com.l2jfrozen.gameserver.model.actor.instance.L2DoorInstance;
+
+
+
+/**
+ * @author Anarchy
+ *
+ */
+public class Instance
+{
+	private int id;
+	private List<L2DoorInstance> doors;
+	
+	public Instance(int id)
+	{
+		this.id = id;
+		doors = new ArrayList<>();
+	}
+	
+	public void openDoors()
+	{
+		for (L2DoorInstance door : doors)
+			door.openMe();
+	}
+	
+	public void closeDoors()
+	{
+		for (L2DoorInstance door : doors)
+			door.closeMe();
+	}
+	
+	public void addDoor(L2DoorInstance door)
+	{
+		doors.add(door);
+	}
+	
+	public List<L2DoorInstance> getDoors()
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
#P L2jFrozen_GameServer
Index: head-src/Base/Dev/Dungeon/InstanceIdFactory.java
===================================================================
--- head-src/Base/Dev/Dungeon/InstanceIdFactory.java   (nonexistent)
+++ head-src/Base/Dev/Dungeon/InstanceIdFactory.java   (working copy)
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
+package Base.Dev.Dungeon;
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
#P L2jFrozen_GameServer
Index: head-src/Base/Dev/Dungeon/InstanceManager.java
===================================================================
--- head-src/Base/Dev/Dungeon/InstanceManager.java   (nonexistent)
+++ head-src/Base/Dev/Dungeon/InstanceManager.java   (working copy)
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
+package Base.Dev.Dungeon;
+
+import java.util.Map;
+import java.util.concurrent.ConcurrentHashMap;
+
+import com.l2jfrozen.gameserver.model.actor.instance.L2DoorInstance;
+
+
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
+	public void addDoor(int id, L2DoorInstance door)
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
#P L2jFrozen_GameServer
Index: head-src/Base/Manager/NewCharTaskManager.java
===================================================================
--- head-src/Base/Manager/NewCharTaskManager.java   (nonexistent)
+++ head-src/Base/Manager/NewCharTaskManager.java     (working copy)
+package Base.Manager;
+
+import java.util.Map;
+import java.util.concurrent.ConcurrentHashMap;
+
+import com.l2jfrozen.gameserver.model.L2Character;
+import com.l2jfrozen.gameserver.model.actor.instance.L2PcInstance;
+import com.l2jfrozen.gameserver.thread.ThreadPool;
+
+
+/**
+ * @author Baggos
+ */
+public final class NewCharTaskManager implements Runnable
+{
+	private final Map<L2PcInstance, Long> _players = new ConcurrentHashMap<>();
+
+	protected NewCharTaskManager()
+	{
+		// Run task each 10 second.
+		ThreadPool.scheduleAtFixedRate(this, 1000, 1000);
+	}
+
+	public final void add(L2PcInstance player)
+	{
+		_players.put(player, System.currentTimeMillis());
+	}
+
+	public final void remove(L2Character player)
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
+		for (Map.Entry<L2PcInstance, Long> entry : _players.entrySet())
+		{
+			final L2PcInstance player = entry.getKey();
+
+			if (player.getMemos().getLong("newEndTime") < System.currentTimeMillis())
+			{
+				L2PcInstance.removeNewChar(player);
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
#P L2jFrozen_GameServer
Index: head-src/Base/Memo/AbstractMemo.java
===================================================================
--- head-src/Base/Memo/AbstractMemo.java  (nonexistent)
+++ head-src/Base/Memo/AbstractMemo.java    (working copy)
+package Base.Memo;
+
+import java.util.concurrent.atomic.AtomicBoolean;
+
+
+
+
+/**
+ * A {@link StatsSet} which overrides methods to prevent doing useless database operations if there is no changes since last edit (it uses an AtomicBoolean to keep edition tracking).<br>
+ * <br>
+ * It also has 2 abstract methods, named restoreMe() and storeMe().
+ */
+public abstract class AbstractMemo extends StatsSet
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
#P L2jFrozen_GameServer
Index: head-src/Base/Memo/StatsSet.java
===================================================================
--- head-src/Base/Memo/StatsSet.java (nonexistent)
+++ head-src/Base/Memo/StatsSet.java    (working copy)
+package Base.Memo;
+
+import java.util.ArrayList;
+import java.util.Collections;
+import java.util.HashMap;
+import java.util.List;
+import java.util.Map;
+
+/**
+ * This class is used in order to have a set of couples (key,value).<BR>
+ * Methods deployed are accessors to the set (add/get value from its key) and addition of a whole set in the current one.
+ * @author mkizub, G1ta0
+ */
+public class StatsSet extends HashMap<String, Object>
+{
+	/**
+	 *
+	 */
+	private static final long serialVersionUID = 1L;
+	
+	public StatsSet()
+	{
+		super();
+	}
+
+	public StatsSet(final int size)
+	{
+		super(size);
+	}
+
+	public StatsSet(final StatsSet set)
+	{
+		super(set);
+	}
+
+	public void set(final String key, final Object value)
+	{
+		put(key, value);
+	}
+
+	public void set(final String key, final String value)
+	{
+		put(key, value);
+	}
+
+	public void set(final String key, final boolean value)
+	{
+		put(key, value ? Boolean.TRUE : Boolean.FALSE);
+	}
+
+	public void set(final String key, final int value)
+	{
+		put(key, value);
+	}
+
+	public void set(final String key, final int[] value)
+	{
+		put(key, value);
+	}
+
+	public void set(final String key, final long value)
+	{
+		put(key, value);
+	}
+
+	public void set(final String key, final double value)
+	{
+		put(key, value);
+	}
+
+	public void set(final String key, final Enum<?> value)
+	{
+		put(key, value);
+	}
+
+	public void unset(final String key)
+	{
+		remove(key);
+	}
+
+	public boolean getBool(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Boolean)
+			return (Boolean) val;
+		if (val instanceof String)
+			return Boolean.parseBoolean((String) val);
+		if (val instanceof Number)
+			return ((Number) val).intValue() != 0;
+
+		throw new IllegalArgumentException("StatsSet : Boolean value required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public boolean getBool(final String key, final boolean defaultValue)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Boolean)
+			return (Boolean) val;
+		if (val instanceof String)
+			return Boolean.parseBoolean((String) val);
+		if (val instanceof Number)
+			return ((Number) val).intValue() != 0;
+
+		return defaultValue;
+	}
+
+	public byte getByte(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).byteValue();
+		if (val instanceof String)
+			return Byte.parseByte((String) val);
+
+		throw new IllegalArgumentException("StatsSet : Byte value required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public byte getByte(final String key, final byte defaultValue)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).byteValue();
+		if (val instanceof String)
+			return Byte.parseByte((String) val);
+
+		return defaultValue;
+	}
+
+	public double getDouble(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).doubleValue();
+		if (val instanceof String)
+			return Double.parseDouble((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1. : 0.;
+
+		throw new IllegalArgumentException("StatsSet : Double value required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public double getDouble(final String key, final double defaultValue)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).doubleValue();
+		if (val instanceof String)
+			return Double.parseDouble((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1. : 0.;
+
+		return defaultValue;
+	}
+
+	public double[] getDoubleArray(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof double[])
+			return (double[]) val;
+		if (val instanceof Number)
+			return new double[]
+				{
+			((Number) val).doubleValue()
+				};
+		if (val instanceof String)
+		{
+			final String[] vals = ((String) val).split(";");
+
+			final double[] result = new double[vals.length];
+
+			int i = 0;
+			for (final String v : vals)
+				result[i++] = Double.parseDouble(v);
+
+			return result;
+		}
+
+		throw new IllegalArgumentException("StatsSet : Double array required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public float getFloat(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).floatValue();
+		if (val instanceof String)
+			return Float.parseFloat((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1 : 0;
+
+		throw new IllegalArgumentException("StatsSet : Float value required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public float getFloat(final String key, final float defaultValue)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).floatValue();
+		if (val instanceof String)
+			return Float.parseFloat((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1 : 0;
+
+		return defaultValue;
+	}
+
+	public int getInteger(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).intValue();
+		if (val instanceof String)
+			return Integer.parseInt((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1 : 0;
+
+		throw new IllegalArgumentException("StatsSet : Integer value required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public int getInteger(final String key, final int defaultValue)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).intValue();
+		if (val instanceof String)
+			return Integer.parseInt((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1 : 0;
+
+		return defaultValue;
+	}
+
+	public int[] getIntegerArray(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof int[])
+			return (int[]) val;
+		if (val instanceof Number)
+			return new int[]
+				{
+			((Number) val).intValue()
+				};
+		if (val instanceof String)
+		{
+			final String[] vals = ((String) val).split(";");
+
+			final int[] result = new int[vals.length];
+
+			int i = 0;
+			for (final String v : vals)
+				result[i++] = Integer.parseInt(v);
+
+			return result;
+		}
+
+		throw new IllegalArgumentException("StatsSet : Integer array required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public int[] getIntegerArray(final String key, final int[] defaultArray)
+	{
+		try
+		{
+			return getIntegerArray(key);
+		}
+		catch (IllegalArgumentException e)
+		{
+			return defaultArray;
+		}
+	}
+
+	@SuppressWarnings("unchecked")
+	public <T> List<T> getList(final String key)
+	{
+		final Object val = get(key);
+
+		if (val == null)
+			return Collections.emptyList();
+
+		return (ArrayList<T>) val;
+	}
+
+	public long getLong(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).longValue();
+		if (val instanceof String)
+			return Long.parseLong((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1L : 0L;
+
+		throw new IllegalArgumentException("StatsSet : Long value required, but found: " + val + " for key: " + key + ".");
+	}
+
+	public long getLong(final String key, final long defaultValue)
+	{
+		final Object val = get(key);
+
+		if (val instanceof Number)
+			return ((Number) val).longValue();
+		if (val instanceof String)
+			return Long.parseLong((String) val);
+		if (val instanceof Boolean)
+			return (Boolean) val ? 1L : 0L;
+
+		return defaultValue;
+	}
+
+	public long[] getLongArray(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof long[])
+			return (long[]) val;
+		if (val instanceof Number)
+			return new long[]
+				{
+			((Number) val).longValue()
+				};
+		if (val instanceof String)
+		{
+			final String[] vals = ((String) val).split(";");
+
+			final long[] result = new long[vals.length];
+
+			int i = 0;
+			for (final String v : vals)
+				result[i++] = Integer.parseInt(v);
+
+			return result;
+		}
+
+		throw new IllegalArgumentException("StatsSet : Long array required, but found: " + val + " for key: " + key + ".");
+	}
+
+	@SuppressWarnings("unchecked")
+	public <T, U> Map<T, U> getMap(final String key)
+	{
+		final Object val = get(key);
+
+		if (val == null)
+			return Collections.emptyMap();
+
+		return (HashMap<T, U>) val;
+	}
+
+	public String getString(final String key)
+	{
+		final Object val = get(key);
+
+		if (val != null)
+			return String.valueOf(val);
+
+		throw new IllegalArgumentException("StatsSet : String value required, but unspecified for key: " + key + ".");
+	}
+
+	public String getString(final String key, final String defaultValue)
+	{
+		final Object val = get(key);
+
+		if (val != null)
+			return String.valueOf(val);
+
+		return defaultValue;
+	}
+
+	public String[] getStringArray(final String key)
+	{
+		final Object val = get(key);
+
+		if (val instanceof String[])
+			return (String[]) val;
+		if (val instanceof String)
+			return ((String) val).split(";");
+
+		throw new IllegalArgumentException("StatsSet : String array required, but found: " + val + " for key: " + key + ".");
+	}
+
+	@SuppressWarnings("unchecked")
+	public <A> A getObject(final String key, final Class<A> type)
+	{
+		final Object val = get(key);
+
+		if (val == null || !type.isAssignableFrom(val.getClass()))
+			return null;
+
+		return (A) val;
+	}
+
+	@SuppressWarnings("unchecked")
+	public <E extends Enum<E>> E getEnum(final String name, final Class<E> enumClass)
+	{
+		final Object val = get(name);
+
+		if (val != null && enumClass.isInstance(val))
+			return (E) val;
+		if (val instanceof String)
+			return Enum.valueOf(enumClass, (String) val);
+
+		throw new IllegalArgumentException("Enum value of type " + enumClass.getName() + "required, but found: " + val + ".");
+	}
+
+	@SuppressWarnings("unchecked")
+	public <E extends Enum<E>> E getEnum(final String name, final Class<E> enumClass, final E defaultValue)
+	{
+		final Object val = get(name);
+
+		if (val != null && enumClass.isInstance(val))
+			return (E) val;
+		if (val instanceof String)
+			return Enum.valueOf(enumClass, (String) val);
+
+		return defaultValue;
+	}
+}

### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/Base/Memo/PlayerMemo.java
===================================================================
--- head-src/Base/Memo/PlayerMemo.java (nonexistent)
+++ head-src/Base/Memo/PlayerMemo.java    (working copy)
+package Base.Memo;
+
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
+import java.sql.SQLException;
+import java.util.logging.Level;
+import java.util.logging.Logger;
+
+import com.l2jfrozen.gameserver.model.actor.instance.L2PcInstance;
+
+import com.l2jfrozen.util.database.L2DatabaseFactory;
+
+import Base.Util.Mysql;
+
+
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
+
+
+
+		/**
+	 * @param objectId
+	 */
+	public PlayerMemo(int objectId)
+	{
+		_objectId = objectId;
+		restoreMe();
+	}
+
+
+		// When var exist
+		public static void changeValue(L2PcInstance player, String name, String value)
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
+		public static void setVar(L2PcInstance player, String name, String value, long expirationTime)
+		{
+			if (player.getMemos().containsKey(name))
+				getVarObject(player, name).stopExpireTask();
+
+			player.getMemos().put(name, new PlayerVar(player, name, value, expirationTime));
+			Mysql.set("REPLACE INTO character_memo_dungeon (obj_id, name, value, expire_time) VALUES (?,?,?,?)", player.getObjectId(), name, value, expirationTime);
+		}
+		
+		public static void setVar(L2PcInstance player, String name, int value, long expirationTime)
+		{
+			setVar(player, name, String.valueOf(value), expirationTime);
+		}	
+			
+		public void setVar(L2PcInstance player, String name, long value, long expirationTime)
+		{
+			setVar(player, name, String.valueOf(value), expirationTime);
+		}
+		
+		
+		public static PlayerVar getVarObject(L2PcInstance player, String name)
+		{
+			if(player.getMemos() == null)
+				return null;
+			
+			return (PlayerVar) player.getMemos().get(name);
+		}
+		
+		public static long getVarTimeToExpire(L2PcInstance player, String name)
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
+		public static void unsetVar(L2PcInstance player, String name)
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
+		public static void deleteExpiredVar(L2PcInstance player, String name, String value)
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
+		public static String getVar(L2PcInstance player, String name)
+		{
+			PlayerVar pv = getVarObject(player, name);
+				
+			if (pv == null)
+				return null;
+
+			return pv.getValue();
+		}
+
+		public static long getVarTimeToExpireSQL(L2PcInstance player, String name)
+		{
+			long expireTime = 0;
+			try (Connection con = L2DatabaseFactory.getInstance().getConnection())
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
+		public static boolean getVarB(L2PcInstance player, String name, boolean defaultVal)
+		{
+			PlayerVar pv = getVarObject(player, name);
+			
+			if (pv == null)
+				return defaultVal;
+				
+			return pv.getValueBoolean();
+		}
+			
+		public static boolean getVarB(L2PcInstance player, String name)
+		{
+			return getVarB(player, name, false);
+		}
+			
+		public long getVarLong(L2PcInstance player, String name)
+		{
+			return getVarLong(player, name, 0L);
+		}
+			
+		public long getVarLong(L2PcInstance player, String name, long defaultVal)
+		{
+			long result = defaultVal;
+			String var = getVar(player, name);
+			if (var != null)
+				result = Long.parseLong(var);
+				
+			return result;
+		}
+
+		public static int getVarInt(L2PcInstance player, String name)
+		{
+			return getVarInt(player, name, 0);
+		}
+			
+		public static int getVarInt(L2PcInstance player, String name, int defaultVal)
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
+		public static void loadVariables(L2PcInstance player)
+		{
+			Connection con = null;
+			PreparedStatement offline = null;
+			ResultSet rs = null;
+			try
+			{
+				con = L2DatabaseFactory.getInstance().getConnection();
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
+		public static String getVarValue(L2PcInstance player, String var, String defaultString)
+		{
+			String value = null;
+			Connection con = null;
+			PreparedStatement offline = null;
+			ResultSet rs = null;
+			try
+			{
+				con = L2DatabaseFactory.getInstance().getConnection();
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
+				con = L2DatabaseFactory.getInstance().getConnection();
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
+		// Restore previous variables.
+		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
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
+		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
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
#P L2jFrozen_GameServer
Index: head-src/Base/Memo/PlayerVar.java
===================================================================
--- head-src/Base/Memo/PlayerVar.java (nonexistent)
+++ head-src/Base/Memo/PlayerVar.java    (working copy)
+package Base.Memo;
+
+import java.util.concurrent.ScheduledFuture;
+
+import com.l2jfrozen.gameserver.model.actor.instance.L2PcInstance;
+import com.l2jfrozen.gameserver.thread.ThreadPool;
+
+
+
+
+public class PlayerVar
+{
+	private L2PcInstance owner;
+	private String name;
+	private String value;
+	private long expire_time;
+	
+	@SuppressWarnings("rawtypes")
+	private ScheduledFuture task;
+	
+	public PlayerVar(L2PcInstance owner, String name, String value, long expire_time)
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
+	public L2PcInstance getOwner()
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
+			L2PcInstance pc = _pv.getOwner();
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
#P L2jFrozen_GameServer
Index: head-src/Base/Util/Mysql.java
===================================================================
--- head-src/Base/Util/Mysql.java (nonexistent)
+++ head-src/Base/Util/Mysql.java    (working copy)
+package Base.Util;
+
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
+import java.sql.SQLException;
+import java.sql.Statement;
+import java.util.logging.Logger;
+
+import com.l2jfrozen.util.database.L2DatabaseFactory;
+
+
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
+	public static boolean setEx(L2DatabaseFactory db, String query, Object... vars)
+	{
+		Connection con = null;
+		Statement statement = null;
+		PreparedStatement pstatement = null;
+		boolean successed = true;
+		
+		try
+		{
+			if(db == null)
+				db = L2DatabaseFactory.getInstance();
+
+			con = db.getConnection();
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
+       try {
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
+   public static void closeQuietly(ResultSet rs) {
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
#P L2jFrozen_GameServer
Index: head-src/Base/XML/XMLDocumentFactory.java
===================================================================
--- head-src/Base/XML/XMLDocumentFactory.java (nonexistent)
+++ head-src/Base/XML/XMLDocumentFactory.java    (working copy)
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
#P L2jFrozen_GameServer
Index: head-src/Base/Config/ExProperties.java
===================================================================
--- head-src/Base/Config/ExProperties.java (nonexistent)
+++ head-src/Base/Config/ExProperties.java    (working copy)
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
+package Base.Config;
+
+import java.io.File;
+import java.io.FileInputStream;
+import java.io.IOException;
+import java.io.InputStream;
+import java.util.Properties;
+
+/**
+ * @author G1ta0
+ */
+public class ExProperties extends Properties
+{
+	private static final long serialVersionUID = 1L;
+	
+	public static final String defaultDelimiter = "[\\s,;]+";
+	
+	public void load(final String fileName) throws IOException
+	{
+		load(new File(fileName));
+	}
+	
+	public void load(final File file) throws IOException
+	{
+		try (InputStream is = new FileInputStream(file))
+		{
+			load(is);
+		}
+	}
+	
+	public boolean getProperty(final String name, final boolean defaultValue)
+	{
+		boolean val = defaultValue;
+		
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+			val = Boolean.parseBoolean(value);
+		
+		return val;
+	}
+	
+	public int getProperty(final String name, final int defaultValue)
+	{
+		int val = defaultValue;
+		
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+			val = Integer.parseInt(value);
+		
+		return val;
+	}
+	
+	public long getProperty(final String name, final long defaultValue)
+	{
+		long val = defaultValue;
+		
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+			val = Long.parseLong(value);
+		
+		return val;
+	}
+	
+	public double getProperty(final String name, final double defaultValue)
+	{
+		double val = defaultValue;
+		
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+			val = Double.parseDouble(value);
+		
+		return val;
+	}
+	
+	public String[] getProperty(final String name, final String[] defaultValue)
+	{
+		return getProperty(name, defaultValue, defaultDelimiter);
+	}
+	
+	public String[] getProperty(final String name, final String[] defaultValue, final String delimiter)
+	{
+		String[] val = defaultValue;
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+			val = value.split(delimiter);
+		
+		return val;
+	}
+	
+	public boolean[] getProperty(final String name, final boolean[] defaultValue)
+	{
+		return getProperty(name, defaultValue, defaultDelimiter);
+	}
+	
+	public boolean[] getProperty(final String name, final boolean[] defaultValue, final String delimiter)
+	{
+		boolean[] val = defaultValue;
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+		{
+			final String[] values = value.split(delimiter);
+			val = new boolean[values.length];
+			for (int i = 0; i < val.length; i++)
+				val[i] = Boolean.parseBoolean(values[i]);
+		}
+		
+		return val;
+	}
+	
+	public int[] getProperty(final String name, final int[] defaultValue)
+	{
+		return getProperty(name, defaultValue, defaultDelimiter);
+	}
+	
+	public int[] getProperty(final String name, final int[] defaultValue, final String delimiter)
+	{
+		int[] val = defaultValue;
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+		{
+			final String[] values = value.split(delimiter);
+			val = new int[values.length];
+			for (int i = 0; i < val.length; i++)
+				val[i] = Integer.parseInt(values[i]);
+		}
+		
+		return val;
+	}
+	
+	public long[] getProperty(final String name, final long[] defaultValue)
+	{
+		return getProperty(name, defaultValue, defaultDelimiter);
+	}
+	
+	public long[] getProperty(final String name, final long[] defaultValue, final String delimiter)
+	{
+		long[] val = defaultValue;
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+		{
+			final String[] values = value.split(delimiter);
+			val = new long[values.length];
+			for (int i = 0; i < val.length; i++)
+				val[i] = Long.parseLong(values[i]);
+		}
+		
+		return val;
+	}
+	
+	public double[] getProperty(final String name, final double[] defaultValue)
+	{
+		return getProperty(name, defaultValue, defaultDelimiter);
+	}
+	
+	public double[] getProperty(final String name, final double[] defaultValue, final String delimiter)
+	{
+		double[] val = defaultValue;
+		final String value;
+		
+		if ((value = super.getProperty(name, null)) != null)
+		{
+			final String[] values = value.split(delimiter);
+			val = new double[values.length];
+			for (int i = 0; i < val.length; i++)
+				val[i] = Double.parseDouble(values[i]);
+		}
+		
+		return val;
+	}
+}

### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/thread/ThreadPool.java 
===================================================================
--- head-src/com/l2jfrozen/gameserver/thread/ThreadPool.java   (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/thread/ThreadPool.java    (working copy)
+package com.l2jfrozen.gameserver.thread;
+
+import java.util.concurrent.ArrayBlockingQueue;
+import java.util.concurrent.ScheduledFuture;
+import java.util.concurrent.ScheduledThreadPoolExecutor;
+import java.util.concurrent.ThreadPoolExecutor;
+import java.util.concurrent.TimeUnit;
+import java.util.logging.Logger;
+
+import com.l2jfrozen.Config;
+
+/**
+ * This class handles thread pooling system. It relies on two ThreadPoolExecutor arrays, which poolers number is generated using config.
 +* <p>
 +* Those arrays hold following pools :
 +* </p>
 +* <ul>
 +* <li>Scheduled pool keeps a track about incoming, future events.</li>
 +* <li>Instant pool handles short-life events.</li>
 +* </ul>
 +*/
+public final class ThreadPool
+{
+	protected static final Logger LOG = Logger.getLogger(ThreadPool.class.getName());
+	
+	private static final long MAX_DELAY = TimeUnit.NANOSECONDS.toMillis(Long.MAX_VALUE - System.nanoTime()) / 2;
+	
+	private static int _threadPoolRandomizer;
+	
+	protected static ScheduledThreadPoolExecutor[] _scheduledPools;
+	protected static ThreadPoolExecutor[] _instantPools;
+	
+	/**
+	 * Init the different pools, based on Config. It is launched only once, on Gameserver instance.
+	 */
+	public static void init()
+	{
+		// Feed scheduled pool.
+		int poolCount = Config.SCHEDULED_THREAD_POOL_COUNT;
+		if (poolCount == -1)
+			poolCount = Runtime.getRuntime().availableProcessors();
+		
+		_scheduledPools = new ScheduledThreadPoolExecutor[poolCount];
+		for (int i = 0; i < poolCount; i++)
+			_scheduledPools[i] = new ScheduledThreadPoolExecutor(Config.THREADS_PER_SCHEDULED_THREAD_POOL);
+		
+		// Feed instant pool.
+		poolCount = Config.INSTANT_THREAD_POOL_COUNT;
+		if (poolCount == -1)
+			poolCount = Runtime.getRuntime().availableProcessors();
+		
+		_instantPools = new ThreadPoolExecutor[poolCount];
+		for (int i = 0; i < poolCount; i++)
+			_instantPools[i] = new ThreadPoolExecutor(Config.THREADS_PER_INSTANT_THREAD_POOL, Config.THREADS_PER_INSTANT_THREAD_POOL, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100000));
+		
+		// Prestart core threads.
+		for (ScheduledThreadPoolExecutor threadPool : _scheduledPools)
+			threadPool.prestartAllCoreThreads();
+		
+		for (ThreadPoolExecutor threadPool : _instantPools)
+			threadPool.prestartAllCoreThreads();
+		
+		// Launch purge task.
+		scheduleAtFixedRate(new Runnable()
+		{
+			@Override
+			public void run()
+			{
+				for (ScheduledThreadPoolExecutor threadPool : _scheduledPools)
+					threadPool.purge();
+				
+				for (ThreadPoolExecutor threadPool : _instantPools)
+					threadPool.purge();
+			}
+		}, 600000, 600000);
+		
+		LOG.info("ThreadPool: Initialized " + getPoolSize(_scheduledPools) + "/" + getMaximumPoolSize(_scheduledPools) + " scheduled, " + getPoolSize(_instantPools) + "/" + getMaximumPoolSize(_instantPools) + " instant thread(s).");
+	}
+	
+	/**
+	 * Schedules a one-shot action that becomes enabled after a delay. The pool is chosen based on pools activity.
+	 * @param r : the task to execute.
+	 * @param delay : the time from now to delay execution.
+	 * @return a ScheduledFuture representing pending completion of the task and whose get() method will return null upon completion.
+	 */
+	public static ScheduledFuture<?> schedule(Runnable r, long delay)
+	{
+		try
+		{
+			return getPool(_scheduledPools).schedule(new TaskWrapper(r), validate(delay), TimeUnit.MILLISECONDS);
+		}
+		catch (Exception e)
+		{
+			return null;
+		}
+	}
+	
+	/**
+	 * Schedules a periodic action that becomes enabled after a delay. The pool is chosen based on pools activity.
+	 * @param r : the task to execute.
+	 * @param delay : the time from now to delay execution.
+	 * @param period : the period between successive executions.
+	 * @return a ScheduledFuture representing pending completion of the task and whose get() method will throw an exception upon cancellation.
+	 */
+	public static ScheduledFuture<?> scheduleAtFixedRate(Runnable r, long delay, long period)
+	{
+		try
+		{
+			return getPool(_scheduledPools).scheduleAtFixedRate(new TaskWrapper(r), validate(delay), validate(period), TimeUnit.MILLISECONDS);
+		}
+		catch (Exception e)
+		{
+			return null;
+		}
+	}
+	
+	/**
+	 * Executes the given task sometime in the future.
+	 * @param r : the task to execute.
+	 */
+	public static void execute(Runnable r)
+	{
+		try
+		{
+			getPool(_instantPools).execute(new TaskWrapper(r));
+		}
+		catch (Exception e)
+		{
+		}
+	}
+	
+	/**
+	 * Retrieve stats of current running thread pools.
+	 */
+	public static void getStats()
+	{
+		for (int i = 0; i < _scheduledPools.length; i++)
+		{
+			final ScheduledThreadPoolExecutor threadPool = _scheduledPools[i];
+			
+			LOG.info("=================================================");
+			LOG.info("Scheduled pool #" + i + ":");
+			LOG.info("\tgetActiveCount: ...... " + threadPool.getActiveCount());
+			LOG.info("\tgetCorePoolSize: ..... " + threadPool.getCorePoolSize());
+			LOG.info("\tgetPoolSize: ......... " + threadPool.getPoolSize());
+			LOG.info("\tgetLargestPoolSize: .. " + threadPool.getLargestPoolSize());
+			LOG.info("\tgetMaximumPoolSize: .. " + threadPool.getMaximumPoolSize());
+			LOG.info("\tgetCompletedTaskCount: " + threadPool.getCompletedTaskCount());
+			LOG.info("\tgetQueuedTaskCount: .. " + threadPool.getQueue().size());
+			LOG.info("\tgetTaskCount: ........ " + threadPool.getTaskCount());
+		}
+		
+		for (int i = 0; i < _instantPools.length; i++)
+		{
+			final ThreadPoolExecutor threadPool = _instantPools[i];
+			
+			LOG.info("=================================================");
+			LOG.info("Instant pool #" + i + ":");
+			LOG.info("\tgetActiveCount: ...... " + threadPool.getActiveCount());
+			LOG.info("\tgetCorePoolSize: ..... " + threadPool.getCorePoolSize());
+			LOG.info("\tgetPoolSize: ......... " + threadPool.getPoolSize());
+			LOG.info("\tgetLargestPoolSize: .. " + threadPool.getLargestPoolSize());
+			LOG.info("\tgetMaximumPoolSize: .. " + threadPool.getMaximumPoolSize());
+			LOG.info("\tgetCompletedTaskCount: " + threadPool.getCompletedTaskCount());
+			LOG.info("\tgetQueuedTaskCount: .. " + threadPool.getQueue().size());
+			LOG.info("\tgetTaskCount: ........ " + threadPool.getTaskCount());
+		}
+	}
+	
+	/**
+	 * Shutdown thread pooling system correctly. Send different informations.
+	 */
+	public static void shutdown()
+	{
+		try
+		{
+			System.out.println("ThreadPool: Shutting down.");
+			
+			for (ScheduledThreadPoolExecutor threadPool : _scheduledPools)
+				threadPool.shutdownNow();
+			
+			for (ThreadPoolExecutor threadPool : _instantPools)
+				threadPool.shutdownNow();
+		}
+		catch (Throwable t)
+		{
+			t.printStackTrace();
+		}
+	}
+	
+	/**
+	 * @param <T> : The pool type.
+	 * @param threadPools : The pool array to check.
+	 * @return the less fed pool.
+	 */
+	private static <T> T getPool(T[] threadPools)
+	{
+		return threadPools[_threadPoolRandomizer++ % threadPools.length];
+	}
+	
+	/**
+	 * @param delay : The delay to validate.
+	 * @return a secured value, from 0 to MAX_DELAY.
+	 */
+	private static long validate(long delay)
+	{
+		return Math.max(0, Math.min(MAX_DELAY, delay));
+	}
+	
+	/**
+	 * @param threadPools : The pool array to check.
+	 * @return the overall actual pools size.
+	 */
+	private static long getPoolSize(ThreadPoolExecutor[] threadPools)
+	{
+		long result = 0;
+		
+		for (ThreadPoolExecutor threadPool : threadPools)
+			result += threadPool.getPoolSize();
+		
+		return result;
+	}
+	
+	/**
+	 * @param threadPools : The pool array to check.
+	 * @return the overall maximum pools size.
+	 */
+	private static long getMaximumPoolSize(ThreadPoolExecutor[] threadPools)
+	{
+		long result = 0;
+		
+		for (ThreadPoolExecutor threadPool : threadPools)
+			result += threadPool.getMaximumPoolSize();
+		
+		return result;
+	}
+	
+	public static final class TaskWrapper implements Runnable
+	{
+		private final Runnable _runnable;
+		
+		public TaskWrapper(Runnable runnable)
+		{
+			_runnable = runnable;
+		}
+		
+		@Override
+		public void run()
+		{
+			try
+			{
+				_runnable.run();
+			}
+			catch (RuntimeException e)
+			{
+				LOG.warning("Exception in a Runnable execution:" + e);
+			}
+		}
+	}
+}


### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/network/serverpackets/NpcHtmlMessage.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/network/serverpackets/NpcHtmlMessage.java   (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/network/serverpackets/NpcHtmlMessage.java    (working copy)


	/**
	 * Gets the content.
	 * @return the content
	 */
	public String getContent()
	{
		return _html;
	}

+	/**
+	 * @return
+	 */
+	public String getHtml()
+	{
+		return _html;
+	}


### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/network/serverpackets/SocialAction.java 
===================================================================
--- head-src/com/l2jfrozen/gameserver/network/serverpackets/SocialAction.java   (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/network/serverpackets/SocialAction.java     (working copy)

package com.l2jfrozen.gameserver.network.serverpackets;

+import com.l2jfrozen.gameserver.model.actor.instance.L2DungeonManagerInstance;




	/**
	 * 0x3d SocialAction dd
	 * @param playerId
	 * @param actionId
	 */
	public SocialAction(final int playerId, final int actionId)
	{
		_charObjId = playerId;
		_actionId = actionId;
	}
	
+	/**
+	 * 0x3d SocialAction dd
+	 * @param l2DungeonManagerInstance
+	 * @param actionId
+	 */
+	public SocialAction(final L2DungeonManagerInstance l2DungeonManagerInstance, final int actionId)
+	{
+		this._charObjId = 0;
+		_actionId = actionId;
+	}	




### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/model/actor/instance/L2DungeonMobInstance.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/model/actor/instance/L2DungeonMobInstance.java   (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/model/actor/instance/L2DungeonMobInstance.java    (working copy)
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
+package com.l2jfrozen.gameserver.model.actor.instance;
+
+import com.l2jfrozen.gameserver.model.L2Character;
+import com.l2jfrozen.gameserver.templates.L2NpcTemplate;
+import com.l2jfrozen.gameserver.thread.ThreadPool;
+
+import Base.Dev.Dungeon.Dungeon;
+
+
+
+/**
+ * @author Juvenil Walker
+ *
+ */
+
+
+public class L2DungeonMobInstance extends L2MonsterInstance
+{
+	private Dungeon dungeon;
+	
+	public L2DungeonMobInstance(int objectId, L2NpcTemplate template)
+	{
+		super(objectId, template);
+	}
+	
+	@Override
+	public boolean doDie(L2Character killer)
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
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/model/actor/instance/L2DungeonManagerInstance.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/model/actor/instance/L2DungeonManagerInstance.java  (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/model/actor/instance/L2DungeonManagerInstance.java   (working copy)
+package com.l2jfrozen.gameserver.model.actor.instance;
+
+import java.util.StringTokenizer;
+
+
+
+
+import com.l2jfrozen.Config;
+import com.l2jfrozen.gameserver.ai.CtrlIntention;
+import com.l2jfrozen.gameserver.datatables.sql.ItemTable;
+import com.l2jfrozen.gameserver.network.serverpackets.ActionFailed;
+import com.l2jfrozen.gameserver.network.serverpackets.ExShowScreenMessage;
+import com.l2jfrozen.gameserver.network.serverpackets.MyTargetSelected;
+import com.l2jfrozen.gameserver.network.serverpackets.NpcHtmlMessage;
+import com.l2jfrozen.gameserver.network.serverpackets.SocialAction;
+import com.l2jfrozen.gameserver.network.serverpackets.ValidateLocation;
+import com.l2jfrozen.gameserver.templates.L2NpcTemplate;
+import com.l2jfrozen.util.random.Rnd;
+
+import Base.Dev.Dungeon.DungeonManager;
+
+
+
+public class L2DungeonManagerInstance extends L2NpcInstance
+{
+	public L2DungeonManagerInstance(int objectId, L2NpcTemplate template)
+	{
+		super(objectId, template);
+	}
+
+	@Override
+	public void onAction(L2PcInstance player)
+	{
+		if (this != player.getTarget()) 
+		{
+			player.setTarget(this);
+			player.sendPacket(new MyTargetSelected(getObjectId(), player.getLevel() - getLevel()));
+			player.sendPacket(new ValidateLocation(this));
+		}
+		else if (isInsideRadius(player, INTERACTION_DISTANCE, false, false)) 
+		{
+			SocialAction sa = new SocialAction(this, Rnd.get(8));
+			broadcastPacket(sa);
+			showChatWindow(player);
+			player.sendPacket(ActionFailed.STATIC_PACKET);
+		}
+		else 
+		{
+			player.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, this);
+			player.sendPacket(ActionFailed.STATIC_PACKET);
+		}
+	}
+
+
+	
+	public static String getPlayerStatus(L2PcInstance player, int dungeonId)
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
+	public void onBypassFeedback(L2PcInstance player, String command)
+	{
+		if (command.startsWith("dungeon"))
+		{
+
+						
+			if (DungeonManager.getInstance().isInDungeon(player) || player.isInOlympiadMode())
+			{
+				player.sendMessage("You are currently unable to enter a Dungeon. Please try again later.");
+				return;
+			}
+			
+			if (player.isAio())
+			{
+				player.sendMessage("AioBuffer: cannot leave You have been teleported to the nearest village.");
+				return;
+			}
+			
+			if (player.destroyItemByItemId("Donate Coin", Config.DUNGEON_COIN_ID, Config.CONT_DUNGEON_ITEM, null, true))
+			{
+				int dungeonId = Integer.parseInt(command.substring(8));
+				
+				if(dungeonId == 1 || dungeonId == 2)
+				{
+					DungeonManager.getInstance().enterDungeon(dungeonId, player);
+				}
+				
+				else if (player.isOnline2())
+				{
+					player.sendPacket(new ExShowScreenMessage("Your character Cont Item." +" "+ ItemTable.getInstance().getTemplate(Config.DUNGEON_COIN_ID).getName() + " " + Config.CONT_DUNGEON_ITEM , 6000, 2, true));
+
+					mainHtml(player, 0);
+				}
+				
+			}
+			else
+			{
+				mainHtml(player, 0);
+				player.sendPacket(new ExShowScreenMessage("Your character Cont Item." +" "+ ItemTable.getInstance().getTemplate(Config.DUNGEON_COIN_ID).getName() + " " + Config.CONT_DUNGEON_ITEM , 6000, 2, true));
+				
+			}
+		}
+
+	}
+
+	public static void mainHtml(L2PcInstance activeChar, int time)
+	{
+		
+		if (activeChar.isOnline2())
+		{
+			NpcHtmlMessage nhm = new NpcHtmlMessage(5);
+			StringBuilder html1 = new StringBuilder("");
+			html1.append("<html><head><title>Dungeon</title></head><body><center>");
+			html1.append("<br>");
+			html1.append("Your character Cont Item.");
+			html1.append("</center>");
+			html1.append("</body></html>");
+			nhm.setHtml(html1.toString());
+			activeChar.sendPacket(nhm);
+		}
+		
+	}
+	
+	@Override
+	public void showChatWindow(L2PcInstance player, int val)
+	{
+		NpcHtmlMessage htm = new NpcHtmlMessage(getObjectId());
+		htm.setFile("data/html/mods/Dungeon-L2JDev/"+getNpcId()+(val == 0 ? "" : "-"+val)+".htm");
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
+}


### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/model/actor/instance/L2NpcInstance.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/model/actor/instance/L2NpcInstance.java  (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/model/actor/instance/L2NpcInstance.java   (working copy)
import com.l2jfrozen.util.random.Rnd;
+import Base.Dev.Dungeon.Instance;





	/**
	 * Sets the custom npc instance.
	 * @param arg the new custom npc instance
	 */
	public void setCustomNpcInstance(final L2CustomNpcInstance arg)
	{
		_customNpcInstance = arg;
	}
	
	/**
	 * Throws an action command to L2BufferTeonInstance.<br>
	 * @param player --> Target player
	 * @param buffTemplate --> Name of the Buff Template to Add
	 */
	public void makeBuffs(final L2PcInstance player, final String buffTemplate)
	{
		int _templateId = 0;
		try
		{
			_templateId = Integer.parseInt(buffTemplate);
		}
		catch (final NumberFormatException e)
		{
			if (Config.ENABLE_ALL_EXCEPTIONS)
				e.printStackTrace();
			
			player.sendMessage("Buff ID doesn't exist");
		}
		if (_templateId > 0)
		{
			L2BufferInstance.makeBuffs(player, _templateId, this, true);
			this.updateEffectIcons();
		}
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
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/model/actor/instance/L2PcInstance.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/model/actor/instance/L2PcInstance.java  (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/model/actor/instance/L2PcInstance.java   (working copy)

import com.l2jfrozen.util.random.Rnd;
+import Base.Dev.Dungeon.Dungeon;
+import Base.Dev.Dungeon.Instance;
+import Base.Manager.NewCharTaskManager;
+import Base.Memo.PlayerMemo;
+import Base.Util.Mysql;



					CursedWeaponsManager.getInstance().activate(this, item);
				}
				
				item = null;
			}
		}
	}
	
	/**
	 * Adds item to Inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 * @param process : String Identifier of process triggering this action
	 * @param itemId : int Item Identifier of the item to be added
	 * @param count : int Quantity of items to be added
	 * @param reference : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return 
	 */
+	public L2ItemInstance addItemDungeon(final String process, final int itemId, final int count, final L2Object reference, final boolean sendMessage)
+	{
+		if (count > 0)
+		{
+			// Retrieve the template of the item.
+			final L2Item item = ItemTable.getInstance().getTemplate(itemId);
+			if (item == null)
+			{
+
+				return null;
+			}			
+			
+			// Sends message to client if requested
+			if (sendMessage && (!isCastingNow() && ItemTable.getInstance().createDummyItem(itemId).getItemType() == L2EtcItemType.HERB || ItemTable.getInstance().createDummyItem(itemId).getItemType() != L2EtcItemType.HERB))
+			{
+				if (count > 1)
+				{
+					if (process.equalsIgnoreCase("sweep") || process.equalsIgnoreCase("Quest"))
+					{
+						SystemMessage sm = new SystemMessage(SystemMessageId.EARNED_S2_S1_S);
+						sm.addItemName(itemId);
+						sm.addNumber(count);
+						sendPacket(sm);
+						sm = null;
+					}
+					else
+					{
+						SystemMessage sm = new SystemMessage(SystemMessageId.YOU_PICKED_UP_S1_S2);
+						sm.addItemName(itemId);
+						sm.addNumber(count);
+						sendPacket(sm);
+						sm = null;
+					}
+				}
+				else
+				{
+					if (process.equalsIgnoreCase("sweep") || process.equalsIgnoreCase("Quest"))
+					{
+						SystemMessage sm = new SystemMessage(SystemMessageId.EARNED_ITEM);
+						sm.addItemName(itemId);
+						sendPacket(sm);
+						sm = null;
+					}
+					else
+					{
+						SystemMessage sm = new SystemMessage(SystemMessageId.YOU_PICKED_UP_S1);
+						sm.addItemName(itemId);
+						sendPacket(sm);
+						sm = null;
+					}
+				}
+			}
+			// Auto use herbs - autoloot
+			if (ItemTable.getInstance().createDummyItem(itemId).getItemType() == L2EtcItemType.HERB) // If item is herb dont add it to iv :]
+			{
+				if (!isCastingNow() && !isCastingPotionNow())
+				{
+					L2ItemInstance herb = new L2ItemInstance(_charId, itemId);
+					IItemHandler handler = ItemHandler.getInstance().getItemHandler(herb.getItemId());
+					
+					if (handler == null)
+					{
+						LOGGER.warn("No item handler registered for Herb - item ID " + herb.getItemId() + ".");
+					}
+					else
+					{
+						handler.useItem(this, herb);
+						
+						if (_herbstask >= 100)
+						{
+							_herbstask -= 100;
+						}
+						
+						handler = null;
+					}
+					
+					herb = null;
+				}
+				else
+				{
+					_herbstask += 100;
+					ThreadPoolManager.getInstance().scheduleAi(new HerbTask(process, itemId, count, reference, sendMessage), _herbstask);
+				}
+			}
+			else
+			{
+				// Add the item to inventory
+				L2ItemInstance createdItem = _inventory.addItem(process, itemId, count, this, reference);
+				
+				// Send inventory update packet
+				if (!Config.FORCE_INVENTORY_UPDATE)
+				{
+					InventoryUpdate playerIU = new InventoryUpdate();
+					playerIU.addItem(createdItem);
+					sendPacket(playerIU);
+					playerIU = null;
+				}
+				else
+				{
+					sendPacket(new ItemList(this, false));
+				}
+				
+				// Update current load as well
+				StatusUpdate su = new StatusUpdate(getObjectId());
+				su.addAttribute(StatusUpdate.CUR_LOAD, getCurrentLoad());
+				sendPacket(su);
+				su = null;
+				
+				// If over capacity, drop the item
+				if (!isGM() && !_inventory.validateCapacity(item))
+				{
+					dropItem("InvDrop", createdItem, null, true, true);
+				}
+				else if (CursedWeaponsManager.getInstance().isCursed(createdItem.getItemId()))
+				{
+					CursedWeaponsManager.getInstance().activate(this, createdItem);
+				}
+				
+				return createdItem;
+			}
+		}
+		return null;
+	}		
	
	
	
	
	
	
	/**
	 * Destroy item from inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 * @param process : String Identifier of process triggering this action
	 * @param item : L2ItemInstance to be destroyed
	 * @param reference : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successfull
	 */
	public boolean destroyItem(final String process, L2ItemInstance item, final L2Object reference, final boolean sendMessage)
	{



















	/** The _original karma vip. */
	public int _originalNameColourVIP, _originalKarmaVIP;


+	private final PlayerMemo _vars2 = new PlayerMemo(getObjectId());




			else if (CursedWeaponsManager.getInstance().isCursed(newitem.getItemId()))
			{
				CursedWeaponsManager.getInstance().activate(this, newitem);
			}
			newitem = null;
		}
+			if (isNewChar())
+			NewCharTaskManager.getInstance().add(this);	


















	/**
	 * Update L2PcInstance stats in the characters table of the database.<BR>
	 * <BR>
	 */
	public synchronized void store()
	{
		store(false);
	}
	
+	/**
+	 * @return player memos.
+	 */
+	public PlayerMemo getMemos()
+	{
+		return _vars2;
+	}	
+	
+	
+	
+	public String getIP()
+	{
+		if (getClient().getConnection() == null)
+			return "N/A IP";
+		
+		return getClient().getConnection().getInetAddress().getHostAddress();
+	}
+	
+	
+		public static void doNewChar(L2PcInstance player, int time)
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
+		public static void removeNewChar(L2PcInstance player)
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
+		public void deleteTempItem(int itemObjectID)
+		{
+			boolean destroyed = false;
+			if (getInventory().getItemByObjectId(itemObjectID) != null)
+			{
+				sendMessage("Your "+ItemTable.getInstance().getTemplate(getInventory().getItemByObjectId(itemObjectID).getItemId()).getName()+" has expired.");
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
+					con = L2DatabaseFactory.getInstance().getConnection();
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
+		public void setInstance(Instance instance, boolean b)
+		{
+			return;
+			
+		}	
+	
+		public final void broadcastCharInfo()
+		{
+			for (L2PcInstance player : getKnownList().getKnownType(L2PcInstance.class))
+			{
+				player.sendPacket(new CharInfo(this));
+				
+				final int relation = getRelation(player);
+				player.sendPacket(new RelationChanged(this, relation, isAutoAttackable(player)));
+				if (getPet() != null)
+					player.sendPacket(new RelationChanged(getPet(), relation, isAutoAttackable(player)));
+			}
+		}		
+	
+		/**
+		 * @return True if the Player is online.
+		 */
+		public boolean isOnline2()
+		{
+			return _isOnline;
+		}	
+		

### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/model/L2Character.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/model/L2Character.java  (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/model/L2Character.java   (working copy)

	/** The _last skill cast. */
	private L2Skill _lastSkillCast;
	
+	protected boolean _showSummonAnimation = false;













	synchronized public void reloadShots(final boolean isMagic)
	{
		if (this instanceof L2PcInstance)
		{
			((L2PcInstance) this).rechargeAutoSoulShot(!isMagic, isMagic, false);
		}
		else if (this instanceof L2Summon)
		{
			((L2Summon) this).getOwner().rechargeAutoSoulShot(!isMagic, isMagic, true);
		}
	}

+	public void teleToLocation(int x, int y, int z, int randomOffset)
+	{
+		// Stop movement
+		stopMove(null);
+		abortAttack();
+		abortCast();
+		
+		setIsTeleporting(true);
+		setTarget(null);
+		
+		getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
+		
+		if (randomOffset > 0)
+		{
+			x += Rnd.get(-randomOffset, randomOffset);
+			y += Rnd.get(-randomOffset, randomOffset);
+		}
+		
+		z += 5;
+		
+		// Send TeleportToLocationt to the L2Character AND to all Player in the _KnownPlayers of the L2Character
+		broadcastPacket(new TeleportToLocation(this, x, y, z));
+		
+		// remove the object from its old location
+		decayMe();
+		
+		// Set the x,y,z position of the L2Object and if necessary modify its _worldRegion
+		getPosition().setXYZ(x, y, z);
+		
+		if (!(this instanceof L2PcInstance) || (((L2PcInstance) this).getClient() != null && ((L2PcInstance) 
+ this).getClient().isDetached()))
+			onTeleported();
+		
+		revalidateZone(true);
+	}	
+	
+	public void teleToLocation(Location loc, int randomOffset)
+	{
+		int x = loc.getX();
+		int y = loc.getY();
+		int z = loc.getZ();
+		
+		if (this instanceof L2PcInstance && DimensionalRiftManager.getInstance().checkIfInRiftZone(getX(), getY(), getZ(), true)) // 
+ true -> ignore waiting room :)
+		{
+			L2PcInstance player = (L2PcInstance) this;
+			player.sendMessage("You have been sent to the waiting room.");
+			if (player.isInParty() && player.getParty().isInDimensionalRift())
+			{
+				player.getParty().getDimensionalRift().usedTeleport(player);
+			}
+			int[] newCoords = DimensionalRiftManager.getInstance().getRoom((byte) 0, (byte) 0).getTeleportCoords();
+			x = newCoords[0];
+			y = newCoords[1];
+			z = newCoords[2];
+		}
+		teleToLocation(x, y, z, randomOffset);
+	}
+	
+	public void teleToLocation2(TeleportWhereType teleportWhere)
+	{
+		teleToLocation(MapRegionTable.getInstance().getTeleToLocation(this, teleportWhere), 20);
+	}	

+	/**
+	 * @return Returns the showSummonAnimation.
+	 */
+	public boolean isShowSummonAnimation()
+	{
+		return _showSummonAnimation;
+	}
+	
+	/**
+	 * @param showSummonAnimation The showSummonAnimation to set.
+	 */
+	public void setShowSummonAnimation(boolean showSummonAnimation)
+	{
+		_showSummonAnimation = showSummonAnimation;
+	}



### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/model/spawn/L2Spawn.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/model/spawn/L2Spawn.java  (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/model/spawn/L2Spawn.java   (working copy)
	public L2NpcTemplate getTemplate()
	{
		return _template;
	}
	
	public int getInstanceId()
	{
		return _instanceId;
	}
	
	public void setInstanceId(final int instanceId)
	{
		_instanceId = instanceId;
	}

+	public L2NpcInstance doSpawn(boolean isSummonSpawn)
+	{
+		L2NpcInstance mob = null;
+		try
+		{
+			// Check if the L2Spawn is not a L2Pet or L2Minion
+			if (_template.isType("L2Pet") || _template.isType("L2Minion"))
+				return mob;
+			
+			// Get L2Npc Init parameters and its generate an Identifier
+			Object[] parameters =
+			{
+				IdFactory.getInstance().getNextId(),
+				_template
+			};
+			
+			// Call the constructor of the L2Npc
+			// (can be a L2ArtefactInstance, L2FriendlyMobInstance, L2GuardInstance, L2MonsterInstance, L2SiegeGuardInstance, L2BoxInstance,
+			// L2FeedableBeastInstance, L2TamedBeastInstance, L2NpcInstance)
+			Object tmp = _constructor.newInstance(parameters);
+			
+			if (isSummonSpawn && tmp instanceof L2Character)
+				((L2Character) tmp).setShowSummonAnimation(isSummonSpawn);
+			
+			// Check if the Instance is a L2Npc
+			if (!(tmp instanceof L2NpcInstance))
+				return mob;
+			
+			mob = (L2NpcInstance) tmp;
+			return intializeNpcInstance(mob);
+		}
+		catch (Exception e)
+		{
+	
+		}
+		return mob;
+	}
+	
+	/**
+	 * @return
+	 */
+	public L2NpcInstance getNpc()
+	{
+		return _lastSpawn;
+	}	

### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/templates/L2NpcTemplate.java
===================================================================
--- head-src/com/l2jfrozen/gameserver/templates/L2NpcTemplate.java  (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/templates/L2NpcTemplate.java  (working copy)

	public final String type;
+	private final String _type;



		type = set.getString("type");
+		_type = set.getString("type");




	public final boolean isCustom()
	{
		return _custom;
	}

+	/**
+	 * Checks types, ignore case.
+	 * @param t the type to check.
+	 * @return true if the type are the same, false otherwise.
+	 */
+	public boolean isType(String t)
+	{
+		return _type.equalsIgnoreCase(t);
+	}



### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/network/clientpackets/EnterWorld.java 
===================================================================
--- head-src/com/l2jfrozen/gameserver/network/clientpackets/EnterWorld.java    (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/network/clientpackets/EnterWorld.java      (working copy)

	@Override
	protected void runImpl()
	{
		final L2PcInstance activeChar = getClient().getActiveChar();
	
		if (activeChar == null)
		{
			LOGGER.warn("EnterWorld failed! activeChar is null...");
			getClient().closeNow();
			return;
		}
		
+		// Set NewChar
+		switch (activeChar.getClassId().getId())
+		{
+			case 0:
+			case 10:
+			case 18:
+			case 25:
+			case 31:
+			case 38:
+			case 44:
+			case 49:
+			case 53:
+				L2PcInstance.doNewChar(activeChar, 1);
+				break;
+		}







		EnterGM(activeChar);
		
+		if (activeChar.getMemos().getLong("newEndTime", 0) > 0)
+			onEnterNewChar(activeChar);	





	private void onEnterAio(final L2PcInstance activeChar)
	{
		final long now = Calendar.getInstance().getTimeInMillis();
		final long endDay = activeChar.getAioEndTime();
		
		if (now > endDay)
		{
			activeChar.setAio(false);
			activeChar.setAioEndTime(0);
			activeChar.lostAioSkills();
			activeChar.sendMessage("[Aio System]: Removed your Aio stats... period ends.");
		}
		else
		{
			final Date dt = new Date(endDay);
			_daysleft = (endDay - now) / 86400000;
			if (_daysleft > 30)
				activeChar.sendMessage("[Aio System]: Aio period ends in " + df.format(dt) + ". enjoy the Game.");
			else if (_daysleft > 0)
				activeChar.sendMessage("[Aio System]: Left " + (int) _daysleft + " for Aio period ends.");
			else if (_daysleft < 1)
			{
				final long hour = (endDay - now) / 3600000;
				activeChar.sendMessage("[Aio System]: Left " + (int) hour + " hours to Aio period ends.");
			}
		}
	}
	
+	private static void onEnterNewChar(L2PcInstance activeChar)
+	{
+		long now = Calendar.getInstance().getTimeInMillis();
+		long endDay = activeChar.getMemos().getLong("newEndTime");
+
+		if (now > endDay)
+			L2PcInstance.removeNewChar(activeChar);
+		else
+		{
+			activeChar.setNewChar(true);
+			activeChar.broadcastUserInfo();
+		}
+	}		
	
	
	
	/**
	 * @param cha
	 */
	private void engage(final L2PcInstance cha)




### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: head-src/com/l2jfrozen/gameserver/network/clientpackets/RequestBypassToServer.java 
===================================================================
--- head-src/com/l2jfrozen/gameserver/network/clientpackets/RequestBypassToServer.java     (nonexistent)
+++ head-src/com/l2jfrozen/gameserver/network/clientpackets/RequestBypassToServer.java       (working copy)
import com.l2jterius.gameserver.model.actor.instance.L2ClassMasterInstance;
+import com.l2jterius.gameserver.model.actor.instance.L2ItemInstance;




import com.l2jfrozen.gameserver.util.GMAudit;
+import Base.Dev.Dungeon.InstanceManager;
+import Base.Memo.PlayerMemo;




			else if (_command.startsWith("player_help "))
			{
				playerHelp(activeChar, _command.substring(12));
			}
			
			
+			if (_command.startsWith("bp_reward"))
+			{
+				int type = Integer.parseInt(_command.substring(10));
+				int itemId = 0;
+				int count = 1;
+				
+				switch (type)
+				{
+					case 0:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL0;
+						
+						break;
+					}
+					case 1:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL1;
+						
+						break;
+					}
+					case 2:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL2;
+						
+						break;
+					}
+					case 3:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL3;
+						
+						break;
+					}
+					case 4:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL4;
+						
+						break;
+					}
+					case 5:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL5;
+						
+						break;
+					}
+					case 6:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL6;
+						
+						break;
+					}
+					case 7:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL7;
+						
+						break;
+					}
+					case 8:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL8;
+						
+						break;
+					}
+					case 9:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL9;
+						
+						break;
+					}
+					case 10:
+					{
+						itemId = Config.DUNGEON_ITEM_RENEWAL10;
+						
+						break;
+					}
+				}
+				
+				if (itemId == 0)
+				{
+					System.out.println(activeChar.getName() + " tried to send custom id on dungeon solo rewards.");
+					return;
+				}
+	                            if(activeChar.getDungeon() != null)
+                               {                         	  
+   								L2ItemInstance item = activeChar.addItemDungeon("dungeon reward", itemId, count, null, true);
+   								item.setEnchantLevel(25);
+   								activeChar.getInventory().equipItemAndRecord(item);
+   				                PlayerMemo.setVar(activeChar, "delete_temp_item_" + item.getObjectId(), item.getObjectId(), System.currentTimeMillis() + (1000 *60 *60 *5)); 
+   								InstanceManager.getInstance().getInstance(0);
+   								activeChar.setDungeon(null);						
+   								activeChar.teleToLocation(Config.DUNGEON_SPAWN_X, Config.DUNGEON_SPAWN_Y, Config.DUNGEON_SPAWN_Z, Config.DUNGEON_SPAWN_RND);	                  	                            	   
+                               }
+	                            else 
+                               {
+                            	 activeChar.sendMessage("No puedes recibir el premio");  
+                               }
+			}								
					
			else if (_command.startsWith("npc_"))



### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: Frozen 1132\gameserver\events\Dungeon.xml
===================================================================
--- Frozen 1132\gameserver\events\Dungeon.xml     (nonexistent)
+++ Frozen 1132\gameserver\events\Dungeon.xml       (working copy)
+<?xml version="1.0" encoding="UTF-8"?>
+<list>
+	<dungeon id="1" name="Solo Story Mode" players="1" rewards="57,200000" rewardHtm="data/html/mods/Dungeon-L2JDev/solo_rewards.htm">
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
+	<dungeon id="2" name="Quatro Hard Mode" players="4" rewards="57,1000000" rewardHtm="NULL">
+		<stage order="1" loc="174122,-76173,-5106" teleport="true" minutes="5">
+			<mob npcId="65515" locs="174527,-75636,-5106;174654,-75987,-5106;174647,-76483,-5106;174160,-76758,-5106"/>
+		</stage>
+		<stage order="2" loc="174082,-78151,-5106" teleport="false" minutes="5">
+			<mob npcId="65515" locs="174527,-75636,-5106;174654,-75987,-5106;174647,-76483,-5106;174160,-76758,-5106"/>
+		</stage>
+		<stage order="3" loc="174038,-80727,-5106" teleport="true" minutes="5">
+			<mob npcId="65515" locs="173739,-81254,-5122;173736,-81613,-5122;173729,-82232,-5122;174041,-82539,-5122;174377,-82379,-5122;174451,-81893,-5122;174433,-81342,-5122;174152,-81560,-5122"/>
+		</stage>
+		<stage order="4" loc="174056,-83856,-5106" teleport="true" minutes="5">
+			<mob npcId="65515" locs="174742,-84194,-5106;174745,-84526,-5106;174726,-85127,-5106;174231,-85361,-5106"/>
+		</stage>
+		<stage order="5" loc="174229,-86659,-5106" teleport="true" minutes="5">
+			<mob npcId="65515" locs="174742,-84194,-5106;174745,-84526,-5106;174726,-85127,-5106;174231,-85361,-5106"/>
+		</stage>
+		<stage order="6" loc="174229,-86659,-5106" teleport="false" minutes="5">
+			<mob npcId="65515" locs="174229,-86659,-5106"/>
+		</stage>
+		<stage order="7" loc="174229,-86659,-5106" teleport="false" minutes="10">
+			<mob npcId="65515" locs="174229,-86659,-5106"/>
+		</stage>
+	</dungeon>
+</list>


### Eclipse Workspace Patch 1.0
#P L2jFrozen_GameServer
Index: Frozen 1132\gameserver\events\Dungeon_Html_Reward.properties
===================================================================
--- Frozen 1132\gameserver\events\Dungeon_Html_Reward.properties     (nonexistent)
+++ Frozen 1132\gameserver\events\Dungeon_Html_Reward.properties       (working copy)
+#Item Id Prince L2DungeonManager
+DungeonCoinId = 57
+# ID of the item that will be Buy to Dungeon
+DungeonContItem = 1
+#Cordenadas para la vuelta del jugador al terminar el evento Idea por: jeriko90
+DungeonSpawnX = 82732
+DungeonSpawnY = 149316
+DungeonSpawnZ = -3495
+DungeonSpawnRnd = 25
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






Custom_Npc.sql

INSERT INTO `custom_npc` VALUES ('65514', '31228', 'Dungeon Manager', '1', 'L2J-FROZEN', '1', 'Monster.cat_the_cat', '9.00', '16.00', '70', 'male', 'L2DungeonManager', '40', '3862', '1493', '11.85', '2.78', '40', '43', '30', '21', '20', '10', '490', '10', '1335', '470', '780', '382', '278', '0', '333', '0', '0', '0', '88', '132', '', '0', '0', '0', 'LAST_HIT');
INSERT INTO `custom_npc` VALUES ('65515', '29031', 'Dungeon Mob', '1', '', '0', 'Monster2.ifrit', '10.00', '42.00', '80', 'male', 'L2DungeonMob', '40', '33984', '1859', '55.62', '4.16', '40', '43', '30', '21', '20', '10', '0', '0', '10275', '2307', '6886', '937', '278', '0', '333', '0', '0', '0', '41', '187', 'fire_clan', '300', '0', '0', 'LAST_HIT');




