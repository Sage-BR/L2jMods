### Eclipse Workspace Patch 1.0
#P aCis_gameserver
diff --git java/Base/AutoFarm/AutofarmConstants.java java/Base/AutoFarm/AutofarmConstants.java
new file mode 100644
index 0000000..70e4aef
--- /dev/null
+++ java/Base/AutoFarm/AutofarmConstants.java
@@ -0,0 +1,11 @@
+package Base.AutoFarm;
+
+import java.util.Arrays;
+import java.util.List;
+
+public class AutofarmConstants 
+{
+    public final static List<Integer> attackSlots = Arrays.asList(0, 1, 2, 3);
+    public final static List<Integer> chanceSlots = Arrays.asList(4, 5, 6, 7);
+    public final static List<Integer> lowLifeSlots = Arrays.asList(8, 9, 10, 11);
+}
\ No newline at end of file
diff --git java/Base/AutoFarm/AutofarmPlayerRoutine.java java/Base/AutoFarm/AutofarmPlayerRoutine.java
index e69de29..bb24adb 100644
--- java/Base/AutoFarm/AutofarmPlayerRoutine.java
+++ java/Base/AutoFarm/AutofarmPlayerRoutine.java
@@ -0,0 +1,669 @@
+package Base.AutoFarm;
+
+import net.sf.l2j.Config;
+
+
+import net.sf.l2j.commons.math.MathUtil;
+import net.sf.l2j.commons.pool.ConnectionPool;
+import net.sf.l2j.commons.pool.ThreadPool;
+import net.sf.l2j.commons.random.Rnd;
+
+import net.sf.l2j.gameserver.enums.ShortcutType;
+import net.sf.l2j.gameserver.enums.TeamType;
+import net.sf.l2j.gameserver.enums.skills.SkillTargetType;
+import net.sf.l2j.gameserver.enums.skills.SkillType;
+import net.sf.l2j.gameserver.geoengine.GeoEngine;
+import net.sf.l2j.gameserver.handler.IItemHandler;
+import net.sf.l2j.gameserver.handler.ItemHandler;
+import net.sf.l2j.gameserver.handler.voicedcommandhandlers.AutoFarm;
+import net.sf.l2j.gameserver.model.Shortcut;
+import net.sf.l2j.gameserver.model.WorldObject;
+import net.sf.l2j.gameserver.model.WorldRegion;
+
+import net.sf.l2j.gameserver.model.actor.Creature;
+import net.sf.l2j.gameserver.model.actor.Player;
+import net.sf.l2j.gameserver.model.actor.Summon;
+
+import net.sf.l2j.gameserver.model.actor.instance.Chest;
+import net.sf.l2j.gameserver.model.actor.instance.Monster;
+import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
+import net.sf.l2j.gameserver.network.SystemMessageId;
+import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
+import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;
+import net.sf.l2j.gameserver.skills.AbstractEffect;
+import net.sf.l2j.gameserver.skills.L2Skill;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage.SMPOS;
+
+import java.sql.Connection;
+import java.sql.PreparedStatement;
+import java.sql.ResultSet;
+import java.sql.SQLException;
+import java.util.ArrayList;
+import java.util.Arrays;
+import java.util.Collections;
+import java.util.List;
+import java.util.concurrent.ScheduledFuture;
+import java.util.function.Function;
+import java.util.stream.Collectors;
+
+public class AutofarmPlayerRoutine
+{
+	private final Player player;
+	private ScheduledFuture<?> _task;
+	private Creature committedTarget = null;
+
+	public AutofarmPlayerRoutine(Player player)
+	{
+		this.player = player;
+	}
+
+	public void start()
+	{
+		if (_task == null)
+		{
+			
+            
+            if (isIpAllowed(player.getIP())) {
+                player.sendMessage("Solo puedes usar Auto Farm con una IP a la vez.");
+                return;
+            }else {
+    			_task = ThreadPool.scheduleAtFixedRate(() -> executeRoutine(), 450, 450);
+    			
+    			player.sendPacket(new ExShowScreenMessage("Auto Farming Actived...", 5*1000, SMPOS.TOP_CENTER, false));
+    			player.sendPacket(new SystemMessage(SystemMessageId.AUTO_FARM_ACTIVATED));
+    		    player.setTeam(player.isMageClass() ? TeamType.BLUE : TeamType.RED);
+    		    insertIpEntry(player.getObjectId(), player.getIP());
+    		    player.broadcastUserInfo();
+            }
+            
+            
+            
+            
+			
+
+		
+		}
+	}
+	
+	public void stop()
+	{
+		if (_task != null)
+		{
+			
+			removeIpEntry(player.getObjectId());
+			_task.cancel(false);
+			_task = null;
+			
+			player.sendPacket(new ExShowScreenMessage("Auto Farming Deactivated...", 5*1000, SMPOS.TOP_CENTER, false));
+			player.sendPacket(new SystemMessage(SystemMessageId.AUTO_FARM_DESACTIVATED));
+			player.setTeam(TeamType.NONE);
+			player.broadcastUserInfo();
+		   
+		}
+	}
+	
+	
+    // Método para insertar la IP del jugador en la tabla Auto_Farm_Ip
+    private void insertIpEntry(int charId, String ip) {
+        try (Connection con = ConnectionPool.getConnection()) {
+            String insertSql = "INSERT INTO Auto_Farm_Ip (char_id, ip) VALUES (?, ?)";
+            try (PreparedStatement insertStatement = con.prepareStatement(insertSql)) {
+                insertStatement.setInt(1, charId);
+                insertStatement.setString(2, ip);
+                insertStatement.executeUpdate();
+            }
+        } catch (SQLException e) {
+            e.printStackTrace();
+        }
+    }
+   
+    public static boolean isIpAllowed( String ip) {
+        try (Connection con = ConnectionPool.getConnection()) {
+            String selectSql = "SELECT * FROM Auto_Farm_Ip WHERE ip = ?";
+            try (PreparedStatement selectStatement = con.prepareStatement(selectSql)) {
+               
+                selectStatement.setString(1, ip);
+                try (ResultSet result = selectStatement.executeQuery()) {
+                    return result.next(); 
+                }
+            }
+        } catch (SQLException e) {
+            e.printStackTrace();
+        }
+        return false; // En caso de error o si no hay coincidencia
+    }
+
+    // Método para eliminar la entrada del jugador en la tabla Auto_Farm_Ip
+    public static void removeIpEntry(int charId) {
+        try (Connection con = ConnectionPool.getConnection()) {
+            String deleteSql = "DELETE FROM Auto_Farm_Ip WHERE char_id = ?";
+            try (PreparedStatement deleteStatement = con.prepareStatement(deleteSql)) {
+                deleteStatement.setInt(1, charId);
+                deleteStatement.executeUpdate();
+            }
+        } catch (SQLException e) {
+            e.printStackTrace();
+        }
+    }
+	
+	
+	
+	public void executeRoutine()
+	{
+		if (player.isNoBuffProtected() && player.getAllEffects().length <= 8)
+		{
+			player.sendMessage("You don't have buffs to use autofarm.");
+			player.broadcastUserInfo();
+			stop();	
+			player.setAutoFarm(false);
+			AutoFarm.showAutoFarm(player);
+			return;
+		}
+		
+		calculatePotions();
+		checkSpoil();
+		targetEligibleCreature();
+	    if (player.isMageClass()) 
+	    {
+	        useAppropriateSpell(); // Prioriza el uso de hechizos para los magos
+	    }
+	    else if (shotcutsContainAttack())
+	    {
+	        attack(); // Si es una clase no maga y tiene la acción de ataque en los shortcuts
+	    }
+	    else
+	    {
+	        useAppropriateSpell(); // Si es una clase no maga y no tiene la acción de ataque, usa hechizos
+	    }
+		checkSpoil();
+		useAppropriateSpell();
+	}
+
+	private void attack() 
+	{
+		Boolean shortcutsContainAttack = shotcutsContainAttack();
+		
+	    if (shortcutsContainAttack) 
+	        physicalAttack();
+	}
+
+	private void useAppropriateSpell() 
+	{
+		L2Skill chanceSkill = nextAvailableSkill(getChanceSpells(), AutofarmSpellType.Chance);
+
+		if (chanceSkill != null)
+		{
+			doSkill(chanceSkill, false);
+			return;
+		}
+
+		L2Skill lowLifeSkill = nextAvailableSkill(getLowLifeSpells(), AutofarmSpellType.LowLife);
+
+		if (lowLifeSkill != null) 
+		{
+			doSkill(lowLifeSkill, true);
+			return;
+		}
+
+		L2Skill attackSkill = nextAvailableSkill(getAttackSpells(), AutofarmSpellType.Attack);
+
+		if (attackSkill != null) 
+		{
+			doSkill(attackSkill, false);
+			return;
+		}
+	}
+
+	public L2Skill nextAvailableSkill(List<Integer> skillIds, AutofarmSpellType spellType) 
+	{
+		for (Integer skillId : skillIds) 
+		{
+			L2Skill skill = player.getSkill(skillId);
+
+			if (skill == null) 
+				continue;
+			
+			if (skill.getSkillType() == SkillType.SIGNET || skill.getSkillType() == SkillType.SIGNET_CASTTIME)
+				continue;
+
+			if (player.isSkillDisabled(skill)) 
+				continue;
+
+			if (isSpoil(skillId))
+			{
+				if (monsterIsAlreadySpoiled())
+				{
+					continue;
+				}
+				return skill;
+			}
+			
+			if (spellType == AutofarmSpellType.Chance && getMonsterTarget() != null)
+			{
+				if (getMonsterTarget().getFirstEffect(skillId) == null) 
+					return skill;
+				continue;
+			}
+
+			if (spellType == AutofarmSpellType.LowLife && getHpPercentage() > player.getHealPercent()) 
+				break;
+
+			return skill;
+		}
+
+		return null;
+	}
+	
+	private void checkSpoil() 
+	{
+		if (canBeSweepedByMe() && getMonsterTarget().isDead()) 
+		{
+			L2Skill sweeper = player.getSkill(42);
+			if (sweeper == null) 
+				return;
+			
+			doSkill(sweeper, false);
+		}
+	}
+
+	private Double getHpPercentage() 
+	{
+		return player.getStatus().getHp() * 100.0f / player.getStatus().getMaxHp();
+	}
+
+	private Double percentageMpIsLessThan() 
+	{
+		return player.getStatus().getMp() * 100.0f / player.getStatus().getMaxMp();
+	}
+
+	private Double percentageHpIsLessThan()
+	{
+		return player.getStatus().getHp() * 100.0f / player.getStatus().getMaxHp();
+	}
+
+	private List<Integer> getAttackSpells()
+	{
+		return getSpellsInSlots(AutofarmConstants.attackSlots);
+	}
+
+	private List<Integer> getSpellsInSlots(List<Integer> attackSlots) 
+	{
+		return Arrays.stream(player.getShortcutList().getShortcuts()).filter(shortcut -> shortcut.getPage() == player.getPage() && shortcut.getType() == ShortcutType.SKILL && attackSlots.contains(shortcut.getSlot())).map(Shortcut::getId).collect(Collectors.toList());
+	}
+
+	private List<Integer> getChanceSpells()
+	{
+		return getSpellsInSlots(AutofarmConstants.chanceSlots);
+	}
+
+	private List<Integer> getLowLifeSpells()
+	{
+		return getSpellsInSlots(AutofarmConstants.lowLifeSlots);
+	}
+
+	private boolean shotcutsContainAttack() 
+	{
+		return Arrays.stream(player.getShortcutList().getShortcuts()).anyMatch(shortcut -> shortcut.getPage() == player.getPage() && shortcut.getType() == ShortcutType.ACTION && (shortcut.getId() == 2 || player.isSummonAttack() && shortcut.getId() == 22));
+	}
+	
+	
+	
+	
+	private boolean monsterIsAlreadySpoiled()
+	{
+		return getMonsterTarget() != null && getMonsterTarget().getSpoilState().isSpoiled();
+	}
+	
+	private static boolean isSpoil(Integer skillId)
+	{
+		return skillId == 254 || skillId == 302;
+	}
+	
+	private boolean canBeSweepedByMe() 
+	{
+	    return getMonsterTarget() != null && getMonsterTarget().isDead() && getMonsterTarget().getSpoilState().isSweepable();
+	}
+	
+
+	
+		private void doSkill(L2Skill skill, boolean isSelfSkill)
+		{
+			final WorldObject target = player.getTarget();
+			if (skill == null || !(target instanceof Creature))
+				return;
+			
+			if (skill.getSkillType() == SkillType.RECALL && !Config.KARMA_PLAYER_CAN_TELEPORT && player.getKarma() > 0)
+			{
+				player.sendPacket(ActionFailed.STATIC_PACKET);
+				return;
+			}
+
+			if (skill.isToggle() && player.isMounted())
+			{
+				player.sendPacket(ActionFailed.STATIC_PACKET);
+				return;
+			}
+
+			if (player.isOutOfControl())
+			{
+				player.sendPacket(ActionFailed.STATIC_PACKET);
+				return;
+			}
+			
+			
+			
+			
+			
+			if (isNecessarySkill(skill))
+				player.getAI().tryToCast(isSelfSkill ? player : (Creature) target, skill);
+		}
+	
+	
+		private boolean isNecessarySkill(L2Skill skill)
+		{
+			if (skill == null)
+				return false;
+			
+			final WorldObject target = player.getTarget();
+			if (target instanceof Monster)
+			{
+				final Monster monster = (Monster) target;
+				if (skill.getSkillType() == SkillType.SPOIL && monster.getSpoilState().isSpoiled())
+					return false;
+				
+				List<AbstractEffect> effects = Arrays.stream(monster.getAllEffects()).filter(e -> e.getSkill().isDebuff()).collect(Collectors.toList());
+				if (effects != null && !effects.isEmpty() && effects.stream().anyMatch(e -> e.getSkill().getId() == skill.getId()))
+					return false;
+				
+				if (!monster.isDead() && skill.getTargetType() == SkillTargetType.CORPSE_MOB)
+					return false;
+				
+				return true;
+			}
+			return false;
+		}
+
+	private void physicalAttack()
+	{
+	    if (!(player.getTarget() instanceof Monster)) 
+	        return;
+
+	    Monster target = (Monster) player.getTarget();
+
+	    if (!player.isMageClass()) 
+	    {
+	        if (target.canAutoAttack(player) && GeoEngine.getInstance().canSeeTarget(player, target))
+	        {
+	            if (GeoEngine.getInstance().canSeeTarget(player, target))
+	            {
+	                player.getAI().tryToAttack(target);
+	                player.onActionRequest();
+
+	                if (player.isSummonAttack() && player.getSummon() != null)
+	                {
+	                    // Siege Golem's
+	                    if (player.getSummon().getNpcId() >= 14702 && player.getSummon().getNpcId() <= 14798 || player.getSummon().getNpcId() >= 14839 && player.getSummon().getNpcId() <= 14869)
+	                        return;
+
+	                    Summon activeSummon = player.getSummon();
+	                    activeSummon.setTarget(target);
+	                    activeSummon.getAI().tryToAttack(target);
+
+	                    int[] summonAttackSkills = {4261, 4068, 4137, 4260, 4708, 4709, 4710, 4712, 5135, 5138, 5141, 5442, 5444, 6095, 6096, 6041, 6044};
+	                    if (Rnd.get(100) < player.getSummonSkillPercent())
+	                    {
+	                        for (int skillId : summonAttackSkills)
+	                        {
+	                            useMagicSkillBySummon(skillId, target);
+	                        }
+	                    }
+	                }
+	            }
+	        }
+	        else
+	        {
+	            if (target.canAutoAttack(player) && GeoEngine.getInstance().canSeeTarget(player, target))
+	            if (GeoEngine.getInstance().canSeeTarget(player, target))
+	                player.getAI().tryToFollow(target, false);
+	        }
+	    }
+	    else
+	    {
+	        if (player.isSummonAttack() && player.getSummon() != null)
+	        {
+	            // Siege Golem's
+	            if (player.getSummon().getNpcId() >= 14702 && player.getSummon().getNpcId() <= 14798 || player.getSummon().getNpcId() >= 14839 && player.getSummon().getNpcId() <= 14869)
+	                return;
+
+	            Summon activeSummon = player.getSummon();
+	            activeSummon.setTarget(target);
+	            activeSummon.getAI().tryToAttack(target);
+
+	            int[] summonAttackSkills = {4261, 4068, 4137, 4260, 4708, 4709, 4710, 4712, 5135, 5138, 5141, 5442, 5444, 6095, 6096, 6041, 6044};
+	            if (Rnd.get(100) < player.getSummonSkillPercent())
+	            {
+	                for (int skillId : summonAttackSkills)
+	                {
+	                    useMagicSkillBySummon(skillId, target);
+	                }
+	            }
+	        }
+	    }
+	}
+
+	private void useMagicSkillBySummon(int skillId, WorldObject target)
+	{
+	    // No owner, or owner in shop mode.
+	    if (player == null || player.isInStoreMode())
+	        return;
+	    
+	    final Summon activeSummon = player.getSummon();
+	    if (activeSummon == null)
+	        return;
+	    
+	    // Pet which is 20 levels higher than owner.
+	    if (activeSummon instanceof Pet && activeSummon.getStatus().getLevel() - player.getStatus().getLevel() > 20)
+	    {
+	        player.sendPacket(SystemMessageId.PET_TOO_HIGH_TO_CONTROL);
+	        return;
+	    }
+	    
+	    // Out of control pet.
+	    if (activeSummon.isOutOfControl())
+	    {
+	        player.sendPacket(SystemMessageId.PET_REFUSING_ORDER);
+	        return;
+	    }
+	    
+	    // Verify if the launched skill is mastered by the summon.
+	    final L2Skill skill = activeSummon.getSkill(skillId);
+	    if (skill == null)
+	        return;
+	    
+	    // Can't launch offensive skills on owner.
+	    if (skill.isOffensive() && player == target)
+	        return;
+	    
+	    activeSummon.setTarget(target);
+	    activeSummon.getAI().tryToCast(committedTarget, skill);
+	}
+
+
+
+	public void targetEligibleCreature() {
+	    if (player.getTarget() == null) {
+	        selectNewTarget(); // Llamada a selectNewTarget si el jugador no tiene un objetivo
+	        return;
+	    }
+
+	    if (committedTarget != null) {
+	        if (/*!isSameInstance(player, committedTarget) ||*/ (committedTarget.isDead() && GeoEngine.getInstance().canSeeTarget(player, committedTarget))) {
+	            committedTarget = null;
+	            selectNewTarget(); // Llamada a selectNewTarget después de que el objetivo actual muere o es de otra instancia
+	            return;
+	        } else if (!committedTarget.isDead() && GeoEngine.getInstance().canSeeTarget(player, committedTarget)) {
+	            attack();
+	            return;
+	        } else if (!GeoEngine.getInstance().canSeeTarget(player, committedTarget)) {
+	            committedTarget = null;
+	            selectNewTarget(); // Buscar otro objetivo si el jugador no puede ver al objetivo actual
+	            return;
+	        }
+	        player.getAI().tryToFollow(committedTarget, false);
+	        committedTarget = null;
+	        player.setTarget(null);
+	    }
+	    
+	    if (committedTarget instanceof Summon) 
+	        return;
+	        
+	    List<Monster> targets = getKnownMonstersInRadius(player, player.getRadius(), creature -> GeoEngine.getInstance().canMoveToTarget(player.getX(), player.getY(), player.getZ(), creature.getX(), creature.getY(), creature.getZ()) && !player.ignoredMonsterContain(creature.getNpcId()) && !creature.isRaidRelated() && !creature.isRaidBoss() && !creature.isDead() && !(creature instanceof Chest) && !(player.isAntiKsProtected() && creature.getTarget() != null && creature.getTarget() != player && creature.getTarget() != player.getSummon()) /*&& isSameInstance(player, creature)*/);
+
+	    if (targets.isEmpty())
+	        return;
+
+	    Monster closestTarget = targets.stream().min((o1, o2) -> Integer.compare((int) Math.sqrt(player.distance2D(o1)), (int) Math.sqrt(player.distance2D(o2)))).get();
+
+	    committedTarget = closestTarget;
+	    player.setTarget(closestTarget);
+	}
+
+
+	// Función para verificar si dos objetos pertenecen a la misma instancia
+/*	private static boolean isSameInstance(WorldObject obj1, WorldObject obj2) 
+	{
+	    return obj1.getInstanceId2() == obj2.getInstanceId2();
+	}*/
+
+	// Función para seleccionar un nuevo objetivo
+	private void selectNewTarget() 
+	{
+	    List<Monster> targets = getKnownMonstersInRadius(player, player.getRadius(), creature -> GeoEngine.getInstance().canMoveToTarget(player.getX(), player.getY(), player.getZ(), creature.getX(), creature.getY(), creature.getZ()) && !player.ignoredMonsterContain(creature.getNpcId()) && !creature.isRaidRelated() && !creature.isRaidBoss() && !creature.isDead() && !(creature instanceof Chest) && !(player.isAntiKsProtected() && creature.getTarget() != null && creature.getTarget() != player && creature.getTarget() != player.getSummon()) /*&& isSameInstance(player, creature)*/);
+
+	    if (targets.isEmpty())
+	        return;
+
+	    Monster closestTarget = targets.stream().min((o1, o2) -> Integer.compare((int) Math.sqrt(player.distance2D(o1)), (int) Math.sqrt(player.distance2D(o2)))).get();
+
+	    committedTarget = closestTarget;
+	    player.setTarget(closestTarget);
+	}
+
+
+
+
+	public final static List<Monster> getKnownMonstersInRadius(Player player, int radius, Function<Monster, Boolean> condition)
+	{
+		final WorldRegion region = player.getRegion();
+		if (region == null)
+			return Collections.emptyList();
+
+		final List<Monster> result = new ArrayList<>();
+
+		for (WorldRegion reg : region.getSurroundingRegions())
+		{
+			for (WorldObject obj : reg.getObjects())
+			{
+				if (!(obj instanceof Monster) || !MathUtil.checkIfInRange(radius, player, obj, true) || !condition.apply((Monster) obj))
+					continue;
+
+				result.add((Monster) obj);
+			}
+		}
+
+		return result;
+	}
+
+	public Monster getMonsterTarget()
+	{
+		if(!(player.getTarget() instanceof Monster)) 
+		{
+			return null;
+		}
+
+		return (Monster)player.getTarget();
+	}
+
+	
+
+	private void calculatePotions()
+	{
+		if (percentageHpIsLessThan() < player.getHpPotionPercentage())
+			forceUseItem(1539); 
+
+		if (percentageMpIsLessThan() < player.getMpPotionPercentage())
+			forceUseItem(728); 
+	}	
+
+	private void forceUseItem(int itemId)
+	{
+		final ItemInstance potion = player.getInventory().getItemByItemId(itemId);
+		if (potion == null)
+			return; 
+
+		final IItemHandler handler = ItemHandler.getInstance().getHandler(potion.getEtcItem());
+		if (handler != null)
+			handler.useItem(player, potion, false); 
+	}	
+}
\ No newline at end of file
diff --git java/Base/AutoFarm/AutofarmSpell.java java/Base/AutoFarm/AutofarmSpell.java
new file mode 100644
index 0000000..beefc8d
--- /dev/null
+++ java/Base/AutoFarm/AutofarmSpell.java
@@ -0,0 +1,20 @@
+package Base.AutoFarm;
+
+public class AutofarmSpell {
+	private final Integer _skillId;
+	private final AutofarmSpellType _spellType;
+	
+	public AutofarmSpell(Integer skillId, AutofarmSpellType spellType){
+		
+		_skillId = skillId;
+		_spellType = spellType;
+	}
+	
+	public Integer getSkillId() {
+		return _skillId;
+	}
+	
+	public AutofarmSpellType getSpellType() {
+		return _spellType;
+	}
+}
\ No newline at end of file
diff --git java/Base/AutoFarm/AutofarmSpellType.java java/Base/AutoFarm/AutofarmSpellType.java
new file mode 100644
index 0000000..2ec3039
--- /dev/null
+++ java/Base/AutoFarm/AutofarmSpellType.java
@@ -0,0 +1,8 @@
+package Base.AutoFarm;
+
+public enum AutofarmSpellType
+{
+    Attack,
+    Chance,
+    LowLife    
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/handler/VoicedCommandHandler.java java/net/sf/l2j/gameserver/handler/VoicedCommandHandler.java
index 6e1e2ac..7dc1b59 100644
--- java/net/sf/l2j/gameserver/handler/VoicedCommandHandler.java
+++ java/net/sf/l2j/gameserver/handler/VoicedCommandHandler.java
@@ -3,6 +3,7 @@
 import java.util.HashMap;
 import java.util.Map;
 
+import net.sf.l2j.gameserver.handler.voicedcommandhandlers.AutoFarm;
 import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Epic;
 import net.sf.l2j.gameserver.handler.voicedcommandhandlers.EventCommand;
 import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Menu;
@@ -18,6 +19,7 @@
 	
 	protected VoicedCommandHandler()
 	{
+		registerHandler(new AutoFarm());
 		registerHandler(new Epic());
 		registerHandler(new Online());
 		registerHandler(new Menu());
diff --git java/net/sf/l2j/gameserver/handler/voicedcommandhandlers/AutoFarm.java java/net/sf/l2j/gameserver/handler/voicedcommandhandlers/AutoFarm.java
new file mode 100644
index 0000000..3e10933
--- /dev/null
+++ java/net/sf/l2j/gameserver/handler/voicedcommandhandlers/AutoFarm.java
@@ -0,0 +1,324 @@
+package net.sf.l2j.gameserver.handler.voicedcommandhandlers;
+
+import java.util.StringTokenizer;
+
+import net.sf.l2j.commons.lang.StringUtil;
+
+import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
+import net.sf.l2j.gameserver.model.WorldObject;
+import net.sf.l2j.gameserver.model.actor.Player;
+import net.sf.l2j.gameserver.model.actor.instance.Monster;
+
+import net.sf.l2j.gameserver.network.SystemMessageId;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
+import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
+import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;
+
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage.SMPOS;
+
+public class AutoFarm implements IVoicedCommandHandler 
+{
+    private final String[] VOICED_COMMANDS = 
+    {
+    	"autofarm",
+    	"enableAutoFarm",
+    	"radiusAutoFarm",
+    	"pageAutoFarm",
+    	"enableBuffProtect",
+    	"healAutoFarm",
+    	"hpAutoFarm",
+    	"mpAutoFarm",
+    	"enableAntiKs",
+    	"enableSummonAttack",
+    	"summonSkillAutoFarm",
+    	"ignoreMonster",
+    	"activeMonster"
+    };
+
+    @Override
+    public boolean useVoicedCommand(final String command, final Player activeChar, final String args)
+    {
+		final AutofarmPlayerRoutine bot = activeChar.getBot();
+		
+    	if (command.startsWith("autofarm"))
+    		showAutoFarm(activeChar);
+    	
+		if (command.startsWith("radiusAutoFarm"))
+		{
+			StringTokenizer st = new StringTokenizer(command, " ");
+			st.nextToken();
+			try
+			{
+				String param = st.nextToken();
+
+				if (param.startsWith("inc_radius"))
+				{
+					activeChar.setRadius(activeChar.getRadius() + 200);
+					showAutoFarm(activeChar);
+				}
+				else if (param.startsWith("dec_radius"))
+				{
+					activeChar.setRadius(activeChar.getRadius() - 200);
+					showAutoFarm(activeChar);
+				}
+				activeChar.saveAutoFarmSettings();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+		}
+		
+		if (command.startsWith("pageAutoFarm"))
+		{
+			StringTokenizer st = new StringTokenizer(command, " ");
+			st.nextToken();
+			try
+			{
+				String param = st.nextToken();
+
+				if (param.startsWith("inc_page"))
+				{
+					activeChar.setPage(activeChar.getPage() + 1);
+					showAutoFarm(activeChar);
+				}
+				else if (param.startsWith("dec_page"))
+				{
+					activeChar.setPage(activeChar.getPage() - 1);
+					showAutoFarm(activeChar);
+				}
+				activeChar.saveAutoFarmSettings();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+		}
+		
+		if (command.startsWith("healAutoFarm"))
+		{
+			StringTokenizer st = new StringTokenizer(command, " ");
+			st.nextToken();
+			try
+			{
+				String param = st.nextToken();
+
+				if (param.startsWith("inc_heal"))
+				{
+					activeChar.setHealPercent(activeChar.getHealPercent() + 10);
+					showAutoFarm(activeChar);
+				}
+				else if (param.startsWith("dec_heal"))
+				{
+					activeChar.setHealPercent(activeChar.getHealPercent() - 10);
+					showAutoFarm(activeChar);
+				}
+				activeChar.saveAutoFarmSettings();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+		}
+		
+		if (command.startsWith("hpAutoFarm"))
+		{
+			StringTokenizer st = new StringTokenizer(command, " ");
+			st.nextToken();
+			try
+			{
+				String param = st.nextToken();
+
+				if (param.contains("inc_hp_pot"))
+				{
+					activeChar.setHpPotionPercentage(activeChar.getHpPotionPercentage() + 5);
+					showAutoFarm(activeChar);
+				}
+				else if (param.contains("dec_hp_pot"))
+				{
+					activeChar.setHpPotionPercentage(activeChar.getHpPotionPercentage() - 5);
+					showAutoFarm(activeChar);
+				}
+				activeChar.saveAutoFarmSettings();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+		}
+		
+		if (command.startsWith("mpAutoFarm"))
+		{
+			StringTokenizer st = new StringTokenizer(command, " ");
+			st.nextToken();
+			try
+			{
+				String param = st.nextToken();
+
+				if (param.contains("inc_mp_pot"))
+				{
+					activeChar.setMpPotionPercentage(activeChar.getMpPotionPercentage() + 5);
+					showAutoFarm(activeChar);
+				}
+				else if (param.contains("dec_mp_pot"))
+				{
+					activeChar.setMpPotionPercentage(activeChar.getMpPotionPercentage() - 5);
+					showAutoFarm(activeChar);
+				}
+				activeChar.saveAutoFarmSettings();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+		}
+		
+		if (command.startsWith("enableAutoFarm"))
+		{
+	    	if (activeChar.isAutoFarm())
+	    	{
+	    		bot.stop();
+				activeChar.setAutoFarm(false);
+	    	}
+	    	else
+	    	{
+	    		bot.start();
+				activeChar.setAutoFarm(true);
+	    	}
+
+	    	showAutoFarm(activeChar);
+		}
+		
+		if (command.startsWith("enableBuffProtect"))
+		{
+			activeChar.setNoBuffProtection(!activeChar.isNoBuffProtected());
+			showAutoFarm(activeChar);
+			activeChar.saveAutoFarmSettings();
+		}
+		
+		if (command.startsWith("enableAntiKs"))
+		{
+			activeChar.setAntiKsProtection(!activeChar.isAntiKsProtected());
+			
+			if(activeChar.isAntiKsProtected()) {
+				activeChar.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_RESPECT_HUNT));
+				activeChar.sendPacket(new ExShowScreenMessage("Respct Hunt On" , 3*1000, SMPOS.TOP_CENTER, false));
+			}else {
+				activeChar.sendPacket(new SystemMessage(SystemMessageId.DESACTIVATE_RESPECT_HUNT));
+				activeChar.sendPacket(new ExShowScreenMessage("Respct Hunt Off" , 3*1000, SMPOS.TOP_CENTER, false));
+			}
+			
+			activeChar.saveAutoFarmSettings();
+			showAutoFarm(activeChar);
+		}
+		
+		if (command.startsWith("enableSummonAttack"))
+		{
+			activeChar.setSummonAttack(!activeChar.isSummonAttack());
+			if(activeChar.isSummonAttack()) {
+				activeChar.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_SUMMON_ACTACK));
+				activeChar.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack On" , 3*1000, SMPOS.TOP_CENTER, false));
+			}else {
+				activeChar.sendPacket(new SystemMessage(SystemMessageId.DESACTIVATE_SUMMON_ACTACK));
+				activeChar.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack Off" , 3*1000, SMPOS.TOP_CENTER, false));
+			}
+			activeChar.saveAutoFarmSettings();
+			showAutoFarm(activeChar);
+		}
+		
+		if (command.startsWith("summonSkillAutoFarm"))
+		{
+			StringTokenizer st = new StringTokenizer(command, " ");
+			st.nextToken();
+			try
+			{
+				String param = st.nextToken();
+
+				if (param.startsWith("inc_summonSkill"))
+				{
+					activeChar.setSummonSkillPercent(activeChar.getSummonSkillPercent() + 10);
+					showAutoFarm(activeChar);
+				}
+				else if (param.startsWith("dec_summonSkill"))
+				{
+					activeChar.setSummonSkillPercent(activeChar.getSummonSkillPercent() - 10);
+					showAutoFarm(activeChar);
+				}
+				activeChar.saveAutoFarmSettings();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+		}
+		
+		if (command.startsWith("ignoreMonster"))
+		{
+			int monsterId = 0;
+			WorldObject target = activeChar.getTarget();
+			if (target instanceof Monster)
+				monsterId = ((Monster) target).getNpcId();
+			
+			if (target == null)
+			{
+				activeChar.sendMessage("You dont have a target");
+				return false;
+			}
+			
+			activeChar.sendMessage(target.getName() + " has been added to the ignore list.");
+			activeChar.ignoredMonster(monsterId);
+		}
+		
+		if (command.startsWith("activeMonster"))
+		{
+			int monsterId = 0;
+			WorldObject target = activeChar.getTarget();
+			if (target instanceof Monster)
+				monsterId = ((Monster) target).getNpcId();
+			
+			if (target == null)
+			{
+				activeChar.sendMessage("You dont have a target");
+				return false;
+			}
+			
+			activeChar.sendMessage(target.getName() + " has been removed from the ignore list.");
+			activeChar.activeMonster(monsterId);
+		}
+		
+        return false;
+    }
+    
+	private static final String ACTIVED = "<font color=00FF00>STARTED</font>";
+	private static final String DESATIVED = "<font color=FF0000>STOPPED</font>";
+	private static final String STOP = "STOP";
+	private static final String START = "START";
+	
+	public static void showAutoFarm(Player activeChar)
+	{
+		NpcHtmlMessage html = new NpcHtmlMessage(0);
+		
+		html.setFile(activeChar.getLocale(), "html/mods/menu/AutoFarm.htm"); 
+		html.replace("%player%", activeChar.getName());
+		html.replace("%page%", StringUtil.formatNumber(activeChar.getPage() + 1));
+		html.replace("%heal%", StringUtil.formatNumber(activeChar.getHealPercent()));
+		html.replace("%radius%", StringUtil.formatNumber(activeChar.getRadius()));
+		html.replace("%summonSkill%", StringUtil.formatNumber(activeChar.getSummonSkillPercent()));
+		html.replace("%hpPotion%", StringUtil.formatNumber(activeChar.getHpPotionPercentage()));
+		html.replace("%mpPotion%", StringUtil.formatNumber(activeChar.getMpPotionPercentage()));
+		html.replace("%noBuff%", activeChar.isNoBuffProtected() ? "back=L2UI.CheckBox_checked fore=L2UI.CheckBox_checked" : "back=L2UI.CheckBox fore=L2UI.CheckBox");
+		html.replace("%summonAtk%", activeChar.isSummonAttack() ? "back=L2UI.CheckBox_checked fore=L2UI.CheckBox_checked" : "back=L2UI.CheckBox fore=L2UI.CheckBox");
+		html.replace("%antiKs%", activeChar.isAntiKsProtected() ? "back=L2UI.CheckBox_checked fore=L2UI.CheckBox_checked" : "back=L2UI.CheckBox fore=L2UI.CheckBox");
+		html.replace("%autofarm%", activeChar.isAutoFarm() ? ACTIVED : DESATIVED);
+		html.replace("%button%", activeChar.isAutoFarm() ? STOP : START);
+		activeChar.sendPacket(html);
+	}
+
+    @Override
+    public String[] getVoicedCommandList() 
+    {
+        return VOICED_COMMANDS;
+    }
+}
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/model/actor/Npc.java java/net/sf/l2j/gameserver/model/actor/Npc.java
index 75120d5..40051ec 100644
--- java/net/sf/l2j/gameserver/model/actor/Npc.java
+++ java/net/sf/l2j/gameserver/model/actor/Npc.java
@@ -81,6 +81,8 @@
 import net.sf.l2j.gameserver.taskmanager.AiTaskManager;
 import net.sf.l2j.gameserver.taskmanager.DecayTaskManager;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 /**
  * An instance type extending {@link Creature}, which represents a Non Playable Character (or NPC) in the world.
  */
@@ -1366,7 +1368,7 @@
 	{
 		if (!isTeleportAllowed(player))
 			return;
-		
+		final AutofarmPlayerRoutine bot = player.getBot();
 		final List<Location> teleports = InstantTeleportData.getInstance().getTeleports(getNpcId());
 		if (teleports == null || index > teleports.size())
 			return;
@@ -1376,6 +1378,11 @@
 			return;
 		
 		player.teleportTo(teleport, 20);
+	    if (player.isAutoFarm())
+        {
+            bot.stop();
+            player.setAutoFarm(false);
+        }
 	}
 	
 	/**
@@ -1398,6 +1405,9 @@
 		if (teleport == null)
 			return;
 		
+		
+		final AutofarmPlayerRoutine bot = player.getBot();
+		
 		if (teleport.getCastleId() > 0)
 		{
 			final Castle castle = CastleManager.getInstance().getCastleById(teleport.getCastleId());
@@ -1411,6 +1421,12 @@
 		if (Config.FREE_TELEPORT && player.getStatus().getLevel() <= Config.LVL_FREE_TELEPORT || teleport.getPriceCount() == 0 || player.destroyItemByItemId("InstantTeleport", teleport.getPriceId(), teleport.getCalculatedPriceCount(player), this, true))
 			player.teleportTo(teleport, 20);
 		
+	    if (player.isAutoFarm())
+	    	           {
+	    	               bot.stop();
+	    	               player.setAutoFarm(false);
+	    	           }
+		
 		player.sendPacket(ActionFailed.STATIC_PACKET);
 	}
 	
diff --git java/net/sf/l2j/gameserver/model/actor/Player.java java/net/sf/l2j/gameserver/model/actor/Player.java
index df09dcb..ddfcb84 100644
--- java/net/sf/l2j/gameserver/model/actor/Player.java
+++ java/net/sf/l2j/gameserver/model/actor/Player.java
@@ -34,7 +34,9 @@
 import net.sf.l2j.commons.util.ArraysUtil;
 
 import net.sf.l2j.Config;
+
 import net.sf.l2j.gameserver.LoginServerThread;
+
 import net.sf.l2j.gameserver.communitybbs.CommunityBoard;
 import net.sf.l2j.gameserver.communitybbs.model.Forum;
 import net.sf.l2j.gameserver.data.SkillTable;
@@ -240,6 +242,8 @@
 import net.sf.l2j.gameserver.taskmanager.ShadowItemTaskManager;
 import net.sf.l2j.gameserver.taskmanager.WaterTaskManager;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 /**
  * This class represents a player in the world.<br>
  * There is always a client-thread connected to this (except if a player-store is activated upon logout).
@@ -6386,6 +6390,8 @@
 	{
 		super.deleteMe();
 		
+		 _bot.stop();
+		
 		if (getMountType() == 2 && isInsideZone(ZoneId.NO_LANDING))
 			teleportTo(RestartType.TOWN);
 		
@@ -6457,6 +6463,8 @@
 			// Remove the Player from the world
 			decayMe();
 			
+			_bot.stop();
+			
 			// If a party is in progress, leave it
 			if (_party != null)
 				_party.removePartyMember(this, MessageType.DISCONNECTED);
@@ -7738,4 +7746,228 @@
 	{
 		return _selectedBlocksList;
 	}
+
+	
+	// ------------
+	// Autofarm
+	// ------------
+
+	private boolean _autoFarm;
+	
+	public void setAutoFarm(boolean comm)
+	{
+		_autoFarm = comm;
+	}
+
+	public boolean isAutoFarm()
+	{
+		return _autoFarm;
+	}
+	
+	private int autoFarmRadius = 1200;
+	
+	public void setRadius(int value) 
+	{
+		autoFarmRadius = MathUtil.limit(value, 200, 3000);
+	}
+	
+	public int getRadius()
+	{
+		return autoFarmRadius;
+	}
+	
+	private int autoFarmShortCut = 9;
+	
+	public void setPage(int value) 
+	{
+		autoFarmShortCut = MathUtil.limit(value, 0, 9);
+	}
+	
+	public int getPage()
+	{
+		return autoFarmShortCut;
+	}
+	
+	private int autoFarmHealPercente = 30;
+	
+	public void setHealPercent(int value) 
+	{
+		autoFarmHealPercente = MathUtil.limit(value, 20, 90);
+	}
+	
+	public int getHealPercent()
+	{
+		return autoFarmHealPercente;
+	}
+	
+	private boolean autoFarmBuffProtection = false;
+	
+	public void setNoBuffProtection(boolean val) 
+	{
+		autoFarmBuffProtection = val;
+	}
+	
+	public boolean isNoBuffProtected()
+	{
+		return autoFarmBuffProtection;
+	}
+	
+	private boolean autoAntiKsProtection = false;
+	
+	public void setAntiKsProtection(boolean val) 
+	{
+		autoAntiKsProtection = val;
+	}
+	
+	public boolean isAntiKsProtected()
+	{
+		return autoAntiKsProtection;
+	}
+	
+	private boolean autoFarmSummonAttack = false;
+	
+	public void setSummonAttack(boolean val) 
+	{
+		autoFarmSummonAttack = val;
+	}
+	
+	public boolean isSummonAttack()
+	{
+		return autoFarmSummonAttack;
+	}
+	
+	private int autoFarmSummonSkillPercente = 0;
+	
+	public void setSummonSkillPercent(int value) 
+	{
+		autoFarmSummonSkillPercente = MathUtil.limit(value, 0, 90);
+	}
+	
+	public int getSummonSkillPercent()
+	{
+		return autoFarmSummonSkillPercente;
+	}
+
+	private int hpPotionPercent = 60;
+	private int mpPotionPercent = 60;
+
+	public void setHpPotionPercentage(int value)
+	{
+		hpPotionPercent = MathUtil.limit(value, 0, 100);
+	}
+
+	public int getHpPotionPercentage() 
+	{
+		return hpPotionPercent;
+	}
+
+	public void setMpPotionPercentage(int value) 
+	{
+		mpPotionPercent = MathUtil.limit(value, 0, 100);
+	}
+
+	public int getMpPotionPercentage()
+	{
+		return mpPotionPercent;
+	}
+
+	private List<Integer> _ignoredMonster = new ArrayList<>();
+
+	public void ignoredMonster(Integer npcId)
+	{
+		_ignoredMonster.add(npcId);
+	}
+
+	public void activeMonster(Integer npcId)
+	{
+		if (_ignoredMonster.contains(npcId))
+			_ignoredMonster.remove(npcId);
+	}
+
+	public boolean ignoredMonsterContain(int npcId)
+	{
+		return _ignoredMonster.contains(npcId);
+	}
+
+	private AutofarmPlayerRoutine _bot = new AutofarmPlayerRoutine(this);
+	
+	public AutofarmPlayerRoutine getBot()
+	{
+		if (_bot == null)
+			_bot = new AutofarmPlayerRoutine(this);
+		
+		return _bot;
+	}
+	
+	
+	public String getIP() 
+	{
+		if (getClient().getConnection() == null)
+			return "N/A IP"; 
+		return getClient().getConnection().getInetAddress().getHostAddress();
+	}
+	
+	
+	
+    // Función para guardar los valores del autofarm en la base de datos
+    public void saveAutoFarmSettings() {
+        try (Connection con = ConnectionPool.getConnection()) {
+            String updateSql = "REPLACE INTO character_autofarm (char_id, char_name,  radius, short_cut, heal_percent, buff_protection, anti_ks_protection, summon_attack, summon_skill_percent, hp_potion_percent, mp_potion_percent) VALUES (?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?)";
+            try (PreparedStatement updateStatement = con.prepareStatement(updateSql)) {
+                updateStatement.setInt(1, getObjectId()); // char_id
+                updateStatement.setString(2, getName()); // char_name
+               
+                updateStatement.setInt(3, autoFarmRadius);
+                updateStatement.setInt(4, autoFarmShortCut);
+                updateStatement.setInt(5, autoFarmHealPercente);
+                updateStatement.setBoolean(6, autoFarmBuffProtection);
+                updateStatement.setBoolean(7, autoAntiKsProtection);
+                updateStatement.setBoolean(8, autoFarmSummonAttack);
+                updateStatement.setInt(9, autoFarmSummonSkillPercente);
+                updateStatement.setInt(10, hpPotionPercent);
+                updateStatement.setInt(11, mpPotionPercent);
+                updateStatement.executeUpdate();
+            }
+        } catch (SQLException e) {
+            e.printStackTrace();
+        }
+    }
+
+    public void loadAutoFarmSettings() {
+        try (Connection con = ConnectionPool.getConnection()) {
+            String selectSql = "SELECT * FROM character_autofarm WHERE char_id = ?";
+            try (PreparedStatement selectStatement = con.prepareStatement(selectSql)) {
+                selectStatement.setInt(1, getObjectId()); // char_id
+                try (ResultSet resultSet = selectStatement.executeQuery()) {
+                    if (resultSet.next()) {
+                       
+                        autoFarmRadius = resultSet.getInt("radius");
+                        autoFarmShortCut = resultSet.getInt("short_cut");
+                        autoFarmHealPercente = resultSet.getInt("heal_percent");
+                        autoFarmBuffProtection = resultSet.getBoolean("buff_protection");
+                        autoAntiKsProtection = resultSet.getBoolean("anti_ks_protection");
+                        autoFarmSummonAttack = resultSet.getBoolean("summon_attack");
+                        autoFarmSummonSkillPercente = resultSet.getInt("summon_skill_percent");
+                        hpPotionPercent = resultSet.getInt("hp_potion_percent");
+                        mpPotionPercent = resultSet.getInt("mp_potion_percent");
+                    } else {
+                        // Si no se encontraron registros, cargar valores predeterminados
+                      
+                        autoFarmRadius = 1200;
+                        autoFarmShortCut = 9;
+                        autoFarmHealPercente = 30;
+                        autoFarmBuffProtection = false;
+                        autoAntiKsProtection = false;
+                        autoFarmSummonAttack = false;
+                        autoFarmSummonSkillPercente = 0;
+                        hpPotionPercent = 60;
+                        mpPotionPercent = 60;
+                    }
+                }
+            }
+        } catch (SQLException e) {
+            e.printStackTrace();
+        }
+    }
+
 }
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/network/SystemMessageId.java java/net/sf/l2j/gameserver/network/SystemMessageId.java
index 3e0e45e..12cf8b2 100644
--- java/net/sf/l2j/gameserver/network/SystemMessageId.java
+++ java/net/sf/l2j/gameserver/network/SystemMessageId.java
@@ -11796,6 +11796,17 @@
 	 */
 	public static final SystemMessageId PLEASE_WAIT_A_MOMENT;
 	
+	public static final SystemMessageId DESACTIVATE_SUMMON_ACTACK;	
+	public static final SystemMessageId ACTIVATE_RESPECT_HUNT;
+	public static final SystemMessageId DESACTIVATE_RESPECT_HUNT;
+	public static final SystemMessageId ACTIVATE_SUMMON_ACTACK;	
+	public static final SystemMessageId AUTO_FARM_DESACTIVATED;
+	public static final SystemMessageId AUTO_FARM_ACTIVATED;
+	
+	
+	
+	
+	
 	/**
 	 * Array containing all SystemMessageIds<br>
 	 * Important: Always initialize with a length of the highest SystemMessageId + 1!!!
@@ -13766,6 +13777,13 @@
 		CURRENTLY_LOGGING_IN = new SystemMessageId(2030);
 		PLEASE_WAIT_A_MOMENT = new SystemMessageId(2031);
 		
+		AUTO_FARM_DESACTIVATED = new SystemMessageId(2155);
+		ACTIVATE_SUMMON_ACTACK = new SystemMessageId(2156);
+		DESACTIVATE_SUMMON_ACTACK = new SystemMessageId(2157);		
+		ACTIVATE_RESPECT_HUNT = new SystemMessageId(2158);
+		DESACTIVATE_RESPECT_HUNT = new SystemMessageId(2159);
+		AUTO_FARM_ACTIVATED = new SystemMessageId(2160);
+		
 		buildFastLookupTable();
 	}
 	
diff --git java/net/sf/l2j/gameserver/network/clientpackets/EnterWorld.java java/net/sf/l2j/gameserver/network/clientpackets/EnterWorld.java
index 10b5aa1..c16bd46 100644
--- java/net/sf/l2j/gameserver/network/clientpackets/EnterWorld.java
+++ java/net/sf/l2j/gameserver/network/clientpackets/EnterWorld.java
@@ -64,6 +64,8 @@
 import net.sf.l2j.gameserver.skills.L2Skill;
 import net.sf.l2j.gameserver.taskmanager.GameTimeTaskManager;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 public class EnterWorld extends L2GameClientPacket
 {
 	@Override
@@ -257,6 +259,18 @@
 		LMEvent.onLogin(player);
 		TvTEvent.onLogin(player);
 		
+		
+		
+        	if(AutofarmPlayerRoutine.isIpAllowed(player.getIP())) {
+        		AutofarmPlayerRoutine.removeIpEntry(player.getObjectId());
+        	}
+        	
+            
+                       player.loadAutoFarmSettings();
+
+        	       if(player.isSummonAttack()) {
+        	    	   player.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_SUMMON_ACTACK));
+        	           
+        	       }
+        	       
+        	       if(player.isAntiKsProtected()) {
+        	    	   player.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_RESPECT_HUNT));
+        	       }  
+		
+		
 		// If the Player is a Dark Elf, check for Shadow Sense at night.
 		if (player.getRace() == ClassRace.DARK_ELF && player.hasSkill(L2Skill.SKILL_SHADOW_SENSE))
 			player.sendPacket(SystemMessage.getSystemMessage((GameTimeTaskManager.getInstance().isNight()) ? SystemMessageId.NIGHT_S1_EFFECT_APPLIES : SystemMessageId.DAY_S1_EFFECT_DISAPPEARS).addSkillName(L2Skill.SKILL_SHADOW_SENSE));
diff --git java/net/sf/l2j/gameserver/network/clientpackets/Logout.java java/net/sf/l2j/gameserver/network/clientpackets/Logout.java
index fc35889..7e45882 100644
--- java/net/sf/l2j/gameserver/network/clientpackets/Logout.java
+++ java/net/sf/l2j/gameserver/network/clientpackets/Logout.java
@@ -12,6 +12,8 @@
 import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
 import net.sf.l2j.gameserver.taskmanager.AttackStanceTaskManager;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 public final class Logout extends L2GameClientPacket
 {
 	@Override
@@ -39,6 +41,9 @@
 			return;
 		}
 		
+		
+		
+		
 		if (AttackStanceTaskManager.getInstance().isInAttackStance(player) && !player.isGM())
 		{
 			player.sendPacket(SystemMessageId.CANT_LOGOUT_WHILE_FIGHTING);
@@ -59,6 +64,17 @@
 		LMEvent.onLogout(player);
 		TvTEvent.onLogout(player);
 		
+        if (player.isAutoFarm())
+        {
+        	if(AutofarmPlayerRoutine.isIpAllowed(player.getIP())) {
+        		AutofarmPlayerRoutine.removeIpEntry(player.getObjectId());
+        	}
+        	
+            
+        }
+		
+		
+		
 		player.removeFromBossZone();
 		AntiFeedManager.getInstance().onDisconnect(player.getClient());
 		player.logout(true);
diff --git java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java
index 5f8debc..78fa84c 100644
--- java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java
+++ java/net/sf/l2j/gameserver/network/clientpackets/RequestBypassToServer.java
@@ -45,11 +45,16 @@
 import net.sf.l2j.gameserver.model.spawn.ASpawn;
 import net.sf.l2j.gameserver.network.SystemMessageId;
 import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
+import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage.SMPOS;
 import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
+import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;
 import net.sf.l2j.gameserver.scripting.QuestState;
 import net.sf.l2j.gameserver.skills.AbstractEffect;
 import net.sf.l2j.gameserver.skills.L2Skill;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 public final class RequestBypassToServer extends L2GameClientPacket
 {
 	private static final Logger GMAUDIT_LOG = Logger.getLogger("gmaudit");
@@ -75,9 +80,11 @@
 			return;
 		
 		final Player player = getClient().getPlayer();
+		
+		
 		if (player == null)
 			return;
-		
+		final AutofarmPlayerRoutine bot = player.getBot();
 		if (_command.startsWith("admin_"))
 		{
 			String command = _command.split(" ")[0];
@@ -130,6 +137,119 @@
 			html.disableValidation();
 			player.sendPacket(html);
 		}
+		
+		
+		
+        else if (_command.startsWith("_infosettings"))
+        {
+            showAutoFarm(player);
+        }
+
+        
+        
+        else if (_command.startsWith("_autofarm"))
+        {
+            if (player.isAutoFarm())
+            {
+                bot.stop();
+                player.setAutoFarm(false);
+            }
+            else
+            {
+                bot.start();
+                player.setAutoFarm(true);
+            }
+            
+        }
+        
+        if (_command.startsWith("_pageAutoFarm"))
+        {
+            StringTokenizer st = new StringTokenizer(_command, " ");
+            st.nextToken();
+            try
+            {
+                String param = st.nextToken();
+                
+                if (param.startsWith("inc_page") || param.startsWith("dec_page"))
+                {
+                    int newPage;
+                    
+                    if (param.startsWith("inc_page"))
+                    {
+                        newPage = player.getPage() + 1;
+                    }
+                    else
+                    {
+                        newPage = player.getPage() - 1;
+                    }
+                    
+                    if (newPage >= 0 && newPage <= 9)
+                    {
+                        String[] pageStrings =
+                        {
+                            "F1",
+                            "F2",
+                            "F3",
+                            "F4",
+                            "F5",
+                            "F6",
+                            "F7",
+                            "F8",
+                            "F9",
+                            "F10"
+                        };
+                        
+                        player.setPage(newPage);
+                        player.sendPacket(new ExShowScreenMessage("Auto Farm Skill Bar " + pageStrings[newPage], 3 * 1000, SMPOS.TOP_CENTER, false));
+                        player.saveAutoFarmSettings();
+                        
+                    }
+                    
+                }
+                
+            }
+            catch (Exception e)
+            {
+                e.printStackTrace();
+            }
+        }
+        
+        if (_command.startsWith("_enableBuffProtect"))
+        {
+            player.setNoBuffProtection(!player.isNoBuffProtected());
+            if (player.isNoBuffProtected())
+            {
+                player.sendPacket(new ExShowScreenMessage("Auto Farm Buff Protect On", 3 * 1000, SMPOS.TOP_CENTER, false));
+            }
+            else
+            {
+                player.sendPacket(new ExShowScreenMessage("Auto Farm Buff Protect Off", 3 * 1000, SMPOS.TOP_CENTER, false));
+            }
+            player.saveAutoFarmSettings();
+        }
+        if (_command.startsWith("_enableSummonAttack"))
+        {
+            player.setSummonAttack(!player.isSummonAttack());
+            if (player.isSummonAttack())
+            {
+                player.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_SUMMON_ACTACK));
+                player.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack On", 3 * 1000, SMPOS.TOP_CENTER, false));
+            }
+            else
+            {
+                player.sendPacket(new SystemMessage(SystemMessageId.DESACTIVATE_SUMMON_ACTACK));
+                player.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack Off", 3 * 1000, SMPOS.TOP_CENTER, false));
+            }
+            player.saveAutoFarmSettings();
+        }
+        
+        
+          
+		
+				if (_command.startsWith("_enableRespectHunt"))
+		{
+			
+			player.setAntiKsProtection(!player.isAntiKsProtected());
+			if (player.isAntiKsProtected())
+			{
+				player.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_RESPECT_HUNT));
+				player.sendPacket(new ExShowScreenMessage("Respct Hunt On", 3 * 1000, SMPOS.TOP_CENTER, false));
+			}
+			else
+			{
+				player.sendPacket(new SystemMessage(SystemMessageId.DESACTIVATE_RESPECT_HUNT));
+				player.sendPacket(new ExShowScreenMessage("Respct Hunt Off", 3 * 1000, SMPOS.TOP_CENTER, false));
+			}
+			player.saveAutoFarmSettings();
+			
+		}
+		
+		
+		if (_command.startsWith("_radiusAutoFarm"))
+		{
+			StringTokenizer st = new StringTokenizer(_command, " ");
+			st.nextToken();
+			try
+			{
+				String param = st.nextToken();
+				
+				if (param.startsWith("inc_radius"))
+				{
+					player.setRadius(player.getRadius() + 200);
+					player.sendPacket(new ExShowScreenMessage("Auto Farm Range: " + player.getRadius(), 3 * 1000, SMPOS.TOP_CENTER, false));
+					
+				}
+				else if (param.startsWith("dec_radius"))
+				{
+					player.setRadius(player.getRadius() - 200);
+					player.sendPacket(new ExShowScreenMessage("Auto Farm Range: " + player.getRadius(), 3 * 1000, SMPOS.TOP_CENTER, false));
+					
+				}
+				player.saveAutoFarmSettings();
+			}
+			catch (Exception e)
+			{
+				e.printStackTrace();
+			}
+		}
+		
+		
+		
 		else if (_command.startsWith("npc_"))
 		{
 			if (!player.validateBypass(_command))
@@ -582,4 +702,33 @@
 		
 		html.replace("%content%", sb.toString());
 	}
+	
+	
+	
+	  private static final String ACTIVED = "<font color=00FF00>STARTED</font>";
+	  private static final String DESATIVED = "<font color=FF0000>STOPPED</font>";
+	  private static final String STOP = "STOP";
+	  private static final String START = "START";
+	  
+	  public static void showAutoFarm(Player activeChar)
+	  {
+	      NpcHtmlMessage html = new NpcHtmlMessage(0);
+	      html.setFile(activeChar.getLocale(),"html/mods/menu/AutoFarm.htm");
+	      html.replace("%player%", activeChar.getName());
+	      html.replace("%page%", StringUtil.formatNumber(activeChar.getPage() + 1));
+	      html.replace("%heal%", StringUtil.formatNumber(activeChar.getHealPercent()));
+	      html.replace("%radius%", StringUtil.formatNumber(activeChar.getRadius()));
+	      html.replace("%summonSkill%", StringUtil.formatNumber(activeChar.getSummonSkillPercent()));
+	      html.replace("%hpPotion%", StringUtil.formatNumber(activeChar.getHpPotionPercentage()));
+	      html.replace("%mpPotion%", StringUtil.formatNumber(activeChar.getMpPotionPercentage()));
+	      html.replace("%noBuff%", activeChar.isNoBuffProtected() ? "back=L2UI.CheckBox_checked fore=L2UI.CheckBox_checked" : "back=L2UI.CheckBox fore=L2UI.CheckBox");
+	      html.replace("%summonAtk%", activeChar.isSummonAttack() ? "back=L2UI.CheckBox_checked fore=L2UI.CheckBox_checked" : "back=L2UI.CheckBox fore=L2UI.CheckBox");
+	      html.replace("%antiKs%", activeChar.isAntiKsProtected() ? "back=L2UI.CheckBox_checked fore=L2UI.CheckBox_checked" : "back=L2UI.CheckBox fore=L2UI.CheckBox");
+	      html.replace("%autofarm%", activeChar.isAutoFarm() ? ACTIVED : DESATIVED);
+	      html.replace("%button%", activeChar.isAutoFarm() ? STOP : START);
+	      activeChar.sendPacket(html);
+	  }
+
+	
+	
 }
\ No newline at end of file
diff --git java/net/sf/l2j/gameserver/network/clientpackets/RequestRestart.java java/net/sf/l2j/gameserver/network/clientpackets/RequestRestart.java
index 00a37d4..e95b67d 100644
--- java/net/sf/l2j/gameserver/network/clientpackets/RequestRestart.java
+++ java/net/sf/l2j/gameserver/network/clientpackets/RequestRestart.java
@@ -11,6 +11,8 @@
 import net.sf.l2j.gameserver.network.serverpackets.RestartResponse;
 import net.sf.l2j.gameserver.taskmanager.AttackStanceTaskManager;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 public final class RequestRestart extends L2GameClientPacket
 {
 	@Override
@@ -38,6 +40,16 @@
 			return;
 		}
 		
+        if (player.isAutoFarm())
+        {
+        	if(AutofarmPlayerRoutine.isIpAllowed(player.getIP())) {
+        		AutofarmPlayerRoutine.removeIpEntry(player.getObjectId());
+        	}
+        	
+            
+        }
+		
+		
 		if (AttackStanceTaskManager.getInstance().isInAttackStance(player) && !player.isGM())
 		{
 			player.sendPacket(SystemMessageId.CANT_RESTART_WHILE_FIGHTING);
diff --git java/net/sf/l2j/gameserver/network/serverpackets/Die.java java/net/sf/l2j/gameserver/network/serverpackets/Die.java
index b332215..b1d2328 100644
--- java/net/sf/l2j/gameserver/network/serverpackets/Die.java
+++ java/net/sf/l2j/gameserver/network/serverpackets/Die.java
@@ -14,6 +14,8 @@
 import net.sf.l2j.gameserver.model.residence.castle.Siege;
 import net.sf.l2j.gameserver.model.residence.clanhall.ClanHallSiege;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 public class Die extends L2GameServerPacket
 {
 	private final Creature _creature;
@@ -34,9 +36,16 @@
 		
 		if (creature instanceof Player player)
 		{
+			 final AutofarmPlayerRoutine bot = player.getBot();
 			_allowFixedRes = player.getAccessLevel().allowFixedRes();
 			_clan = player.getClan();
 			
+			          if (player.isAutoFarm())
+				           {
+				               bot.stop();
+				               player.setAutoFarm(false);
+				           }
+			
 		}
 		else if (creature instanceof Monster monster)
 			_sweepable = monster.getSpoilState().isSweepable();
diff --git java/net/sf/l2j/gameserver/network/serverpackets/SystemMessage.java java/net/sf/l2j/gameserver/network/serverpackets/SystemMessage.java
index 19bebb0..6760dfb 100644
--- java/net/sf/l2j/gameserver/network/serverpackets/SystemMessage.java
+++ java/net/sf/l2j/gameserver/network/serverpackets/SystemMessage.java
@@ -29,7 +29,7 @@
 	private SMParam[] _params;
 	private int _paramIndex;
 	
-	private SystemMessage(final SystemMessageId smId)
+	public SystemMessage(final SystemMessageId smId)
 	{
 		final int paramCount = smId.getParamCount();
 		_smId = smId;
