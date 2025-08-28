+package net.sf.l2j.gameserver.model.actor.instance;
+
+import java.io.FileInputStream;
+import java.io.IOException;
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
+import java.util.Properties;
+
+import net.sf.l2j.L2DatabaseFactory;
+import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
+import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
+
+public class L2TopFarmInstance extends L2NpcInstance
+{
+    private static final Properties ITEM_IDS = new Properties();
+
+    static
+    {
+        try (FileInputStream fis = new FileInputStream("config/custom/TopFarmRank.properties"))
+        {
+            ITEM_IDS.load(fis);
+        }
+        catch (IOException e)
+        {
+            System.err.println("Erro ao carregar TopFarmRank.properties: " + e.getMessage());
+        }
+    }
+
+    public L2TopFarmInstance(int objectId, NpcTemplate template)
+    {
+        super(objectId, template);
+    }
+
+    @Override
+    public void onBypassFeedback(L2PcInstance player, String command)
+    {
+        try
+        {
+            int itemId = -1;
+
+            if (command.equals("farmrank1"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank1ItemId", "0"));
+            else if (command.equals("farmrank2"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank2ItemId", "0"));
+            else if (command.equals("farmrank3"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank3ItemId", "0"));
+            else if (command.equals("farmrank4"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank4ItemId", "0"));
+            else if (command.equals("farmrank5"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank5ItemId", "0"));
+            else if (command.equals("farmrank6"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank6ItemId", "0"));
+            else if (command.equals("farmrank7"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank7ItemId", "0"));
+            else if (command.equals("farmrank8"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank8ItemId", "0"));
+            else if (command.equals("farmrank9"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank9ItemId", "0"));
+            else if (command.equals("farmrank10"))
+                itemId = Integer.parseInt(ITEM_IDS.getProperty("FarmRank10ItemId", "0"));
+            
+
+            if (itemId > 0)
+            {
+                NpcHtmlMessage htm = new NpcHtmlMessage(getObjectId());
+                htm.setFile("data/html/mods/farmrank/topfarm.htm");
+                htm.replace("%statsfarm%", getTopFarm(itemId));
+                player.sendPacket(htm);
+            }
+        }
+        catch (Exception e)
+        {
+            e.printStackTrace();
+        }
+    }
+
+    private static String getTopFarm(int itemId)
+    {
+        StringBuilder ret = new StringBuilder();
+
+        ret.append("<html><head><title>Ranking Farm</title></head><body><center></center><br1>")
+           .append("<table width=290>")
+           .append("<tr><td><center><font color=\"FF6600\">Rank</font></center></td>")
+           .append("<td><center><font color=\"FF6600\">Player</font></center></td>")
+           .append("<td><center><font color=\"FF6600\">Quant</font></center></td></tr>");
+
+        try (Connection con = L2DatabaseFactory.getInstance().getConnection();
+             PreparedStatement stm = con.prepareStatement(
+                 "SELECT C.char_name, I.item_id, I.count " +
+                 "FROM characters C " +
+                 "INNER JOIN items I ON C.obj_Id = I.owner_id " +
+                 "WHERE C.accesslevel = 0 AND I.item_id = ? " +
+                 "ORDER BY I.count DESC LIMIT 15"))
+        {
+            stm.setInt(1, itemId);
+
+            try (ResultSet rset = stm.executeQuery())
+            {
+                int i = 1;
+                while (rset.next())
+                {
+                    String name = rset.getString("char_name");
+                    long itemCount = rset.getLong("count");
+
+                    ret.append("<tr><td><center><font color=\"AAAAAA\">" + i++ + "</font></center></td>")
+                       .append("<td><center><font color=\"00FFFF\">" + name + "</font></center></td>")
+                       .append("<td><center>" + itemCount + "</center></td>")
+                       .append("</tr>");
+                }
+            }
+        }
+        catch (Exception e)
+        {
+            e.printStackTrace();
+        }
+
+        ret.append("</table></body></html>");
+        return ret.toString();
+    }
+
+    @Override
+    public String getHtmlPath(int npcId, int val)
+    {
+        String filename = (val == 0) ? "" + npcId : npcId + "-" + val;
+        return "data/html/mods/farmrank/" + filename + ".htm";
+    }
+}
