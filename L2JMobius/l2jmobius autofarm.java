### Eclipse Workspace Patch 1.0
#P L2J_Mobius_C6_Interlude
diff --git dist/db_installer/sql/game/character_autofarm.sql dist/db_installer/sql/game/character_autofarm.sql
new file mode 100644
index 0000000..35128de
--- /dev/null
+++ dist/db_installer/sql/game/character_autofarm.sql
@@ -0,0 +1,37 @@
+/*
+Navicat MySQL Data Transfer
+
+Source Server         : localhost
+Source Server Version : 50740
+Source Host           : localhost:3306
+Source Database       : 1faris
+
+Target Server Type    : MYSQL
+Target Server Version : 50740
+File Encoding         : 65001
+
+Date: 2023-10-15 07:01:50
+*/
+
+SET FOREIGN_KEY_CHECKS=0;
+
+-- ----------------------------
+-- Table structure for character_autofarm
+-- ----------------------------
+DROP TABLE IF EXISTS `character_autofarm`;
+CREATE TABLE `character_autofarm` (
+  `char_id` int(10) unsigned NOT NULL,
+  `char_name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
+  `auto_farm` tinyint(1) unsigned NOT NULL DEFAULT '0',
+  `radius` int(10) unsigned NOT NULL DEFAULT '1200',
+  `short_cut` int(10) unsigned NOT NULL DEFAULT '9',
+  `heal_percent` int(10) unsigned NOT NULL DEFAULT '30',
+  `buff_protection` tinyint(1) unsigned NOT NULL DEFAULT '0',
+  `anti_ks_protection` tinyint(1) unsigned NOT NULL DEFAULT '0',
+  `summon_attack` tinyint(1) unsigned NOT NULL DEFAULT '0',
+  `summon_skill_percent` int(10) unsigned NOT NULL DEFAULT '0',
+  `hp_potion_percent` int(10) unsigned NOT NULL DEFAULT '60',
+  `mp_potion_percent` int(10) unsigned NOT NULL DEFAULT '60',
+  PRIMARY KEY (`char_id`),
+  KEY `char_name` (`char_name`)
+) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
diff --git dist/game/data/html/mods/menu/AutoFarm.htm dist/game/data/html/mods/menu/AutoFarm.htm
new file mode 100644
index 0000000..19711f7
--- /dev/null
+++ dist/game/data/html/mods/menu/AutoFarm.htm
@@ -0,0 +1,126 @@
+<html><title>Auto Hunting</title>
+
+<table width=280>
+<tr>
+<td width=5></td>
+<td width=20 align="center"><img src="L2UI_CH3.herotower_deco" width=256 height=32></td>
+</tr>
+</table>
+
+<table width=265>
+<tr>
+<td width=120 align="right"><font color="LEVEL">Auto Farm State:</font> %autofarm%</td>
+<td width=20 align="left"><button width=75 height=19 back="buttons_bs.bs_64x27_1" fore="buttons_bs.bs_64x27_2" action="bypass voiced_enableAutoFarm" value="%button%"></td>
+</tr>
+</table>
+
+<br1>
+
+<table width=265>
+<tr>
+<td width=5></td>
+<td width=100 valign="top" align="center"><font color="e6dcbe">Target Range:</font> </td>
+<td width=20 align=center><button action="bypass voiced_radiusAutoFarm dec_radius" width=16 height=16 back=L2UI_CH3.prev1_down fore=L2UI_CH3.prev1></td>
+<td width=40 align=center><font color=CC9933>%radius%</font></td>
+<td width=20 align=center><button action="bypass voiced_radiusAutoFarm inc_radius" width=16 height=16 back=L2UI_CH3.next1_down fore=L2UI_CH3.next1></td>
+</tr> 
+<tr>
+<td width=5></td>
+<td width=100 valign="top" align="center"><font color="e6dcbe">Heal Percent: </font></td>
+<td width=20 align=center><button action="bypass voiced_healAutoFarm dec_heal" width=16 height=16 back=L2UI_CH3.prev1_down fore=L2UI_CH3.prev1></td>
+<td width=40 align=center><font color=CC9933>%heal%</font></td>
+<td width=20 align=center><button action="bypass voiced_healAutoFarm inc_heal" width=16 height=16 back=L2UI_CH3.next1_down fore=L2UI_CH3.next1></td>
+</tr> 
+<tr>
+<td width=5></td>
+<td width=100 valign="top" align="center"><font color="e6dcbe">Shortcut Page: </font></td>
+<td width=20 align=center><button action="bypass voiced_pageAutoFarm dec_page" width=16 height=16 back=L2UI_CH3.prev1_down fore=L2UI_CH3.prev1></td>
+<td width=40 align=center><font color=CC9933>%page%</font></td>
+<td width=20 align=center><button action="bypass voiced_pageAutoFarm inc_page" width=16 height=16 back=L2UI_CH3.next1_down fore=L2UI_CH3.next1></td>
+</tr> 
+<tr>
+<td width=5></td>
+<td width=100 valign="top" align="center"><font color="e6dcbe">Mana Potion Percent: </font></td>
+<td width=20 align=center><button action="bypass voiced_mpAutoFarm dec_mp_pot" width=16 height=16 back=L2UI_CH3.prev1_down fore=L2UI_CH3.prev1></td>
+<td width=40 align=center><font color=CC9933>%mpPotion%</font></td>
+<td width=20 align=center><button action="bypass voiced_mpAutoFarm inc_mp_pot" width=16 height=16 back=L2UI_CH3.next1_down fore=L2UI_CH3.next1></td>
+</tr> 
+<tr>
+<td width=5></td>
+<td width=100 valign="top" align="center"><font color="e6dcbe">Healing Potion Percent: </font></td>
+<td width=20 align=center><button action="bypass voiced_hpAutoFarm dec_hp_pot" width=16 height=16 back=L2UI_CH3.prev1_down fore=L2UI_CH3.prev1></td>
+<td width=40 align=center><font color=CC9933>%hpPotion%</font></td>
+<td width=20 align=center><button action="bypass voiced_hpAutoFarm inc_hp_pot" width=16 height=16 back=L2UI_CH3.next1_down fore=L2UI_CH3.next1></td>
+</tr>   
+<tr>
+<td width=5></td>
+<td width=100 valign="top" align="center"><font color="e6dcbe">Summon Skill Chance: </font></td>
+<td width=20 align=center><button action="bypass voiced_summonSkillAutoFarm dec_summonSkill" width=16 height=16 back=L2UI_CH3.prev1_down fore=L2UI_CH3.prev1></td>
+<td width=40 align=center><font color=CC9933>%summonSkill%</font></td>
+<td width=20 align=center><button action="bypass voiced_summonSkillAutoFarm inc_summonSkill" width=16 height=16 back=L2UI_CH3.next1_down fore=L2UI_CH3.next1></td>
+</tr> 
+</table>
+
+<table width=300>
+<tr>
+<td width=20 align=left><font color="e6dcbe">Enable Summon attack support</font></td>
+<td width=30></td>
+<td width=5 align=left><button action="bypass voiced_enableSummonAttack" %summonAtk% width="16" height="16"></td>
+</tr>
+<tr>
+<td width=50 align=left><font color="e6dcbe">Manner Mode, Anti-KS protection</font></td>
+<td width=20></td>
+<td width=10 align=left><button action="bypass voiced_enableAntiKs" %antiKs% width="16" height="16"></td>
+</tr>
+<tr>
+<td width=50 align=left><font color="e6dcbe">Disable Auto Farm after losing all buffs</font></td>
+<td width=20></td>
+<td width=10 align=left><button action="bypass voiced_enableBuffProtect" %noBuff% width="16" height="16"></td>
+</tr>
+</table>
+  
+<br1>
+<center>
+<table>
+<tr>
+    <td width=250><font color="LEVEL">Avoid specific monsters adding them to the blacklist</font></td>
+</tr>
+</table>
+	
+<table>
+<tr>
+	<td><button value="Blacklist Target" action="bypass voiced_ignoreMonster" width=90 height=14 back="buttons_bs.bs_64x15_1" fore="buttons_bs.bs_64x15_2"></td>
+	<td><button value="Unblacklist Target" action="bypass voiced_activeMonster" width=90 height=14 back="buttons_bs.bs_64x15_1" fore="buttons_bs.bs_64x15_2"></td>
+</tr>
+</table>
+</center>
+
+<img src="L2UI.SquareGray" width=310 height=1>
+<table bgcolor="000000" width=330>
+<tr>
+<td></td>
+<td><font color="LEVEL">Rules:</font></td>
+<td height=20><font color="e6dcbe">-</font> <font color="F42B00">'Attack'</font> <font color="e6dcbe">Shortcuts: </font><font color="F42B00">F1, F2, F3 & F4</font></td>
+</tr>
+<tr>
+<td></td>
+<td></td>
+<td height=20><font color="e6dcbe">-</font> <font color="70F011">'Chance'</font> <font color="e6dcbe">Shortcuts: </font><font color="70F011">F5, F6, F7 & F8</font></td>
+</tr>
+<tr>
+<td></td>
+<td></td>
+<td height=20><font color="e6dcbe">-</font> <font color="09E1FA">'Low Life'</font> <font color="e6dcbe">Shortcuts: </font><font color="09E1FA">F9, F10, F11 & F12</font></td>
+</tr>
+</table>
+<img src="L2UI.SquareGray" width=310 height=1>
+<br1>
+
+<table width=350>
+<tr>
+<td width=5></td>
+<td width=20 align="center"><a action="bypass voiced_menu">Back</a></td>
+</tr>
+</table>
+
+</body></html>
\ No newline at end of file
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
new file mode 100644
index 0000000..6f19aa0
--- /dev/null
+++ java/Base/AutoFarm/AutofarmPlayerRoutine.java
@@ -0,0 +1,586 @@
+package Base.AutoFarm;
+
+import java.util.ArrayList;
+import java.util.Arrays;
+import java.util.Collections;
+import java.util.List;
+import java.util.concurrent.ScheduledFuture;
+import java.util.function.Function;
+import java.util.stream.Collectors;
+
+import org.l2jmobius.commons.threads.ThreadPool;
+import org.l2jmobius.commons.util.Rnd;
+import org.l2jmobius.gameserver.ai.CtrlIntention;
+import org.l2jmobius.gameserver.geoengine.GeoEngine;
+import org.l2jmobius.gameserver.handler.IItemHandler;
+import org.l2jmobius.gameserver.handler.ItemHandler;
+import org.l2jmobius.gameserver.handler.voicedcommandhandlers.VoicedAutoFarm;
+import org.l2jmobius.gameserver.model.ShortCut;
+import org.l2jmobius.gameserver.model.Skill;
+import org.l2jmobius.gameserver.model.WorldObject;
+import org.l2jmobius.gameserver.model.WorldRegion;
+import org.l2jmobius.gameserver.model.actor.Creature;
+import org.l2jmobius.gameserver.model.actor.Player;
+import org.l2jmobius.gameserver.model.actor.Summon;
+import org.l2jmobius.gameserver.model.actor.instance.Chest;
+import org.l2jmobius.gameserver.model.actor.instance.Monster;
+import org.l2jmobius.gameserver.model.actor.instance.Pet;
+import org.l2jmobius.gameserver.model.item.instance.Item;
+import org.l2jmobius.gameserver.model.skill.SkillType;
+import org.l2jmobius.gameserver.network.SystemMessageId;
+import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
+import org.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;
+import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
+import org.l2jmobius.gameserver.util.Util;
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
+			_task = ThreadPool.scheduleAtFixedRate(() -> executeRoutine(), 450, 450);
+			
+			player.sendPacket(new ExShowScreenMessage("Auto Farming Actived...", 5 * 1000));
+			player.sendPacket(new SystemMessage(SystemMessageId.AUTO_FARM_ACTIVATED));
+			
+		}
+	}
+	
+	public void stop()
+	{
+		if (_task != null)
+		{
+			_task.cancel(false);
+			_task = null;
+			
+			player.sendPacket(new ExShowScreenMessage("Auto Farming Deactivated...", 5 * 1000));
+			player.sendPacket(new SystemMessage(SystemMessageId.AUTO_FARM_DESACTIVATED));
+			
+		}
+	}
+	
+	public void executeRoutine()
+	{
+		if (player.isNoBuffProtected() && (player.getAllEffects().size() <= 8))
+		{
+			player.sendMessage("You don't have buffs to use autofarm.");
+			player.broadcastUserInfo();
+			stop();
+			player.setAutoFarm(false);
+			VoicedAutoFarm.showAutoFarm(player);
+			return;
+		}
+		
+		calculatePotions();
+		checkSpoil();
+		targetEligibleCreature();
+		if (player.isMageClass())
+		{
+			useAppropriateSpell();
+		}
+		else if (shotcutsContainAttack())
+		{
+			attack();
+		}
+		else
+		{
+			useAppropriateSpell();
+		}
+		checkSpoil();
+		useAppropriateSpell();
+	}
+	
+	private void attack()
+	{
+		
+		physicalAttack();
+	}
+	
+	private void useAppropriateSpell()
+	{
+		Skill chanceSkill = nextAvailableSkill(getChanceSpells(), AutofarmSpellType.Chance);
+		
+		if (chanceSkill != null)
+		{
+			useMagicSkill(chanceSkill, false);
+			return;
+		}
+		
+		Skill lowLifeSkill = nextAvailableSkill(getLowLifeSpells(), AutofarmSpellType.LowLife);
+		
+		if (lowLifeSkill != null)
+		{
+			useMagicSkill(lowLifeSkill, true);
+			return;
+		}
+		
+		Skill attackSkill = nextAvailableSkill(getAttackSpells(), AutofarmSpellType.Attack);
+		
+		if (attackSkill != null)
+		{
+			useMagicSkill(attackSkill, false);
+			return;
+		}
+	}
+	
+	public Skill nextAvailableSkill(List<Integer> skillIds, AutofarmSpellType spellType)
+	{
+		for (Integer skillId : skillIds)
+		{
+			Skill skill = player.getSkill(skillId);
+			
+			if (skill == null)
+			{
+				continue;
+			}
+			
+			if ((skill.getSkillType() == SkillType.SIGNET) || (skill.getSkillType() == SkillType.SIGNET_CASTTIME))
+			{
+				continue;
+			}
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
+			if ((spellType == AutofarmSpellType.Chance) && (getMonsterTarget() != null))
+			{
+				if (getMonsterTarget().getFirstEffect(skillId) == null)
+				{
+					return skill;
+				}
+				continue;
+			}
+			
+			if ((spellType == AutofarmSpellType.LowLife) && (getHpPercentage() > player.getHealPercent()))
+			{
+				break;
+			}
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
+			Skill sweeper = player.getSkill(42);
+			if (sweeper == null)
+			{
+				return;
+			}
+			
+			useMagicSkill(sweeper, false);
+		}
+	}
+	
+	private Double getHpPercentage()
+	{
+		return (player.getCurrentHp() * 100.0f) / player.getMaxHp();
+	}
+	
+	private Double percentageMpIsLessThan()
+	{
+		return (player.getCurrentMp() * 100.0f) / player.getMaxMp();
+	}
+	
+	private Double percentageHpIsLessThan()
+	{
+		return (player.getCurrentHp() * 100.0f) / player.getMaxHp();
+	}
+	
+	private List<Integer> getAttackSpells()
+	{
+		return getSpellsInSlots(AutofarmConstants.attackSlots);
+	}
+	
+	private List<Integer> getSpellsInSlots(List<Integer> attackSlots)
+	{
+		ShortCut[] shortCutsArray = player.getAllShortCuts().toArray(new ShortCut[0]);
+		
+		return Arrays.stream(shortCutsArray).filter(shortcut -> (shortcut.getPage() == player.getPage()) && (shortcut.getType() == ShortCut.TYPE_SKILL) && attackSlots.contains(shortcut.getSlot())).map(ShortCut::getId).collect(Collectors.toList());
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
+		ShortCut[] shortCutsArray = player.getAllShortCuts().toArray(new ShortCut[0]);
+		
+		return Arrays.stream(shortCutsArray).anyMatch(shortcut -> (shortcut.getPage() == player.getPage()) && (shortcut.getType() == ShortCut.TYPE_ACTION) && ((shortcut.getId() == 2) || (player.isSummonAttack() && (shortcut.getId() == 22))));
+	}
+	
+	private boolean monsterIsAlreadySpoiled()
+	{
+		return (getMonsterTarget() != null) && (getMonsterTarget().getSpoiledBy() != 0);
+	}
+	
+	private static boolean isSpoil(Integer skillId)
+	{
+		return (skillId == 254) || (skillId == 302);
+	}
+	
+	private boolean canBeSweepedByMe()
+	{
+		return (getMonsterTarget() != null) && getMonsterTarget().isDead() && (getMonsterTarget().getSpoiledBy() == player.getObjectId());
+	}
+	
+	private void castSpellWithAppropriateTarget(Skill skill, Boolean forceOnSelf)
+	{
+		if (forceOnSelf)
+		{
+			WorldObject oldTarget = player.getTarget();
+			player.setTarget(player);
+			player.useMagic(skill, false, false);
+			player.setTarget(oldTarget);
+			return;
+		}
+		
+		player.useMagic(skill, false, false);
+	}
+	
+	private void physicalAttack()
+	{
+		if (!(player.getTarget() instanceof Monster))
+		{
+			return;
+		}
+		
+		Monster target = (Monster) player.getTarget();
+		
+		if (!player.isMageClass())
+		{
+			if (target.isAutoAttackable(player) && GeoEngine.getInstance().canSeeTarget(player, target))
+			{
+				if (GeoEngine.getInstance().canSeeTarget(player, target))
+				{
+					player.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
+					player.onActionRequest();
+					
+					if (player.isSummonAttack() && (player.getPet() != null))
+					{
+						// Siege Golem's
+						if (((player.getPet().getNpcId() >= 14702) && (player.getPet().getNpcId() <= 14798)) || ((player.getPet().getNpcId() >= 14839) && (player.getPet().getNpcId() <= 14869)))
+						{
+							return;
+						}
+						
+						Summon activeSummon = player.getPet();
+						activeSummon.setTarget(target);
+						activeSummon.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
+						
+						int[] summonAttackSkills =
+						{
+							4261,
+							4068,
+							4137,
+							4260,
+							4708,
+							4709,
+							4710,
+							4712,
+							5135,
+							5138,
+							5141,
+							5442,
+							5444,
+							6095,
+							6096,
+							6041,
+							6044
+						};
+						if (Rnd.get(100) < player.getSummonSkillPercent())
+						{
+							for (int skillId : summonAttackSkills)
+							{
+								useMagicSkillBySummon(skillId, target);
+							}
+						}
+					}
+				}
+			}
+			else
+			{
+				if (target.isAutoAttackable(player) && GeoEngine.getInstance().canSeeTarget(player, target))
+				{
+					if (GeoEngine.getInstance().canSeeTarget(player, target))
+					{
+						player.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, target);
+					}
+				}
+			}
+		}
+		else
+		{
+			if (player.isSummonAttack() && (player.getPet() != null))
+			{
+				// Siege Golem's
+				if (((player.getPet().getNpcId() >= 14702) && (player.getPet().getNpcId() <= 14798)) || ((player.getPet().getNpcId() >= 14839) && (player.getPet().getNpcId() <= 14869)))
+				{
+					return;
+				}
+				
+				Summon activeSummon = player.getPet();
+				activeSummon.setTarget(target);
+				activeSummon.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
+				
+				int[] summonAttackSkills =
+				{
+					4261,
+					4068,
+					4137,
+					4260,
+					4708,
+					4709,
+					4710,
+					4712,
+					5135,
+					5138,
+					5141,
+					5442,
+					5444,
+					6095,
+					6096,
+					6041,
+					6044
+				};
+				if (Rnd.get(100) < player.getSummonSkillPercent())
+				{
+					for (int skillId : summonAttackSkills)
+					{
+						useMagicSkillBySummon(skillId, target);
+					}
+				}
+			}
+		}
+	}
+	
+	public void targetEligibleCreature()
+	{
+		if (player.getTarget() == null)
+		{
+			selectNewTarget();
+			return;
+		}
+		
+		if (committedTarget != null)
+		{
+			if (!committedTarget.isDead() && GeoEngine.getInstance().canSeeTarget(player, committedTarget))
+			{
+				attack();
+				return;
+			}
+			else if (!GeoEngine.getInstance().canSeeTarget(player, committedTarget))
+			{
+				committedTarget = null;
+				selectNewTarget();
+				return;
+			}
+			player.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, committedTarget);
+			committedTarget = null;
+			player.setTarget(null);
+		}
+		
+		if (committedTarget instanceof Summon)
+		{
+			return;
+		}
+		
+		List<Monster> targets = getKnownMonstersInRadius(player, player.getRadius(), creature -> GeoEngine.getInstance().canMoveToTarget(player.getX(), player.getY(), player.getZ(), creature.getX(), creature.getY(), creature.getZ(), 0) && !player.ignoredMonsterContain(creature.getNpcId()) && !creature.isMinion() && !creature.isRaid() && !creature.isDead() && !(creature instanceof Chest) && !(player.isAntiKsProtected() && (creature.getTarget() != null) && (creature.getTarget() != player) && (creature.getTarget() != player.getPet())));
+		
+		if (targets.isEmpty())
+		{
+			return;
+		}
+		
+		Monster closestTarget = targets.stream().min((o1, o2) -> Integer.compare((int) Math.sqrt(player.getDistanceSq(o1)), (int) Math.sqrt(player.getDistanceSq(o2)))).get();
+		
+		committedTarget = closestTarget;
+		player.setTarget(closestTarget);
+	}
+	
+	private void selectNewTarget()
+	{
+		List<Monster> targets = getKnownMonstersInRadius(player, player.getRadius(), creature -> GeoEngine.getInstance().canMoveToTarget(player.getX(), player.getY(), player.getZ(), creature.getX(), creature.getY(), creature.getZ(), 0) && !player.ignoredMonsterContain(creature.getNpcId()) && !creature.isMinion() && !creature.isRaid() && !creature.isDead() && !(creature instanceof Chest) && !(player.isAntiKsProtected() && (creature.getTarget() != null) && (creature.getTarget() != player) && (creature.getTarget() != player.getPet())));
+		
+		if (targets.isEmpty())
+		{
+			return;
+		}
+		
+		Monster closestTarget = targets.stream().min((o1, o2) -> Integer.compare((int) Math.sqrt(player.getDistanceSq(o1)), (int) Math.sqrt(player.getDistanceSq(o2)))).get();
+		
+		committedTarget = closestTarget;
+		player.setTarget(closestTarget);
+	}
+	
+	public final static List<Monster> getKnownMonstersInRadius(Player player, int radius, Function<Monster, Boolean> condition)
+	{
+		final WorldRegion region = player.getWorldRegion();
+		if (region == null)
+		{
+			return Collections.emptyList();
+		}
+		
+		final List<Monster> result = new ArrayList<>();
+		
+		for (WorldRegion reg : region.getSurroundingRegions())
+		{
+			for (WorldObject obj : reg.getVisibleObjects())
+			{
+				if (!(obj instanceof Monster) || !Util.checkIfInRange(radius, player, obj, true) || !condition.apply((Monster) obj))
+				{
+					continue;
+				}
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
+		if (!(player.getTarget() instanceof Monster))
+		{
+			return null;
+		}
+		
+		return (Monster) player.getTarget();
+	}
+	
+	private void useMagicSkill(Skill skill, Boolean forceOnSelf)
+	{
+		if ((skill.getSkillType() == SkillType.RECALL) && (player.getKarma() > 0))
+		{
+			player.sendPacket(ActionFailed.STATIC_PACKET);
+			return;
+		}
+		
+		if (skill.isToggle() && player.isMounted())
+		{
+			player.sendPacket(ActionFailed.STATIC_PACKET);
+			return;
+		}
+		
+		if (player.isOutOfControl())
+		{
+			player.sendPacket(ActionFailed.STATIC_PACKET);
+			return;
+		}
+		
+		if (player.isAttackingNow())
+		{
+			castSpellWithAppropriateTarget(skill, forceOnSelf);
+			// player.getAI().setIntention(CtrlIntention.AI_INTENTION_CAST);
+		}
+		else
+		{
+			castSpellWithAppropriateTarget(skill, forceOnSelf);
+		}
+	}
+	
+	private boolean useMagicSkillBySummon(int skillId, WorldObject target)
+	{
+		// No owner, or owner in shop mode.
+		if ((player == null) || player.isInStoreMode())
+		{
+			return false;
+		}
+		
+		final Summon activeSummon = player.getPet();
+		if (activeSummon == null)
+		{
+			return false;
+		}
+		
+		// Pet which is 20 levels higher than owner.
+		if ((activeSummon instanceof Pet) && ((activeSummon.getLevel() - player.getLevel()) > 20))
+		{
+			// player.sendPacket(SystemMessageId.PET_TOO_HIGH_TO_CONTROL);
+			return false;
+		}
+		
+		// Out of control pet.
+		if (activeSummon.isOutOfControl())
+		{
+			// player.sendPacket(SystemMessageId.PET_REFUSING_ORDER);
+			return false;
+		}
+		
+		// Verify if the launched skill is mastered by the summon.
+		final Skill skill = activeSummon.getSkill(skillId);
+		if (skill == null)
+		{
+			return false;
+		}
+		
+		// Can't launch offensive skills on owner.
+		if (skill.isOffensive() && (player == target))
+		{
+			return false;
+		}
+		
+		activeSummon.setTarget(target);
+		return activeSummon.useMagic(skill, false, false);
+	}
+	
+	private void calculatePotions()
+	{
+		if (percentageHpIsLessThan() < player.getHpPotionPercentage())
+		{
+			forceUseItem(1539);
+		}
+		
+		if (percentageMpIsLessThan() < player.getMpPotionPercentage())
+		{
+			forceUseItem(728);
+		}
+	}
+	
+	private void forceUseItem(int itemId)
+	{
+		final Item potion = player.getInventory().getItemByItemId(itemId);
+		if (potion == null)
+		{
+			return;
+		}
+		
+		final IItemHandler handler = ItemHandler.getInstance().getItemHandler(potion.getItemId());
+		if (handler != null)
+		{
+			handler.useItem(player, potion);
+		}
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
diff --git java/org/l2jmobius/gameserver/handler/VoicedCommandHandler.java java/org/l2jmobius/gameserver/handler/VoicedCommandHandler.java
index 466e2a6..b25580e 100644
--- java/org/l2jmobius/gameserver/handler/VoicedCommandHandler.java
+++ java/org/l2jmobius/gameserver/handler/VoicedCommandHandler.java
@@ -30,6 +30,7 @@
 import org.l2jmobius.gameserver.handler.voicedcommandhandlers.Online;
 import org.l2jmobius.gameserver.handler.voicedcommandhandlers.StatsCmd;
 import org.l2jmobius.gameserver.handler.voicedcommandhandlers.VoiceCommand;
+import org.l2jmobius.gameserver.handler.voicedcommandhandlers.VoicedAutoFarm;
 import org.l2jmobius.gameserver.handler.voicedcommandhandlers.Wedding;
 
 public class VoicedCommandHandler
@@ -43,6 +44,7 @@
 		_datatable = new HashMap<>();
 		
 		registerVoicedCommandHandler(new VoiceCommand());
+		registerVoicedCommandHandler(new VoicedAutoFarm());
 		
 		if (Config.ENABLE_AUTO_PLAY)
 		{
diff --git java/org/l2jmobius/gameserver/handler/voicedcommandhandlers/VoicedAutoFarm.java java/org/l2jmobius/gameserver/handler/voicedcommandhandlers/VoicedAutoFarm.java
new file mode 100644
index 0000000..6501c48
--- /dev/null
+++ java/org/l2jmobius/gameserver/handler/voicedcommandhandlers/VoicedAutoFarm.java
@@ -0,0 +1,332 @@
+package org.l2jmobius.gameserver.handler.voicedcommandhandlers;
+
+import java.util.StringTokenizer;
+
+import org.l2jmobius.commons.util.StringUtil;
+import org.l2jmobius.gameserver.handler.IVoicedCommandHandler;
+import org.l2jmobius.gameserver.model.WorldObject;
+import org.l2jmobius.gameserver.model.actor.Player;
+import org.l2jmobius.gameserver.model.actor.instance.Monster;
+import org.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
+
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
+public class VoicedAutoFarm implements IVoicedCommandHandler
+{
+	private final String[] VOICED_COMMANDS =
+	{
+		"autofarm",
+		"enableAutoFarm",
+		"radiusAutoFarm",
+		"pageAutoFarm",
+		"enableBuffProtect",
+		"healAutoFarm",
+		"hpAutoFarm",
+		"mpAutoFarm",
+		"enableAntiKs",
+		"enableSummonAttack",
+		"summonSkillAutoFarm",
+		"ignoreMonster",
+		"activeMonster"
+	};
+	
+	@Override
+	public boolean useVoicedCommand(final String command, final Player activeChar, final String args)
+	{
+		final AutofarmPlayerRoutine bot = activeChar.getBot();
+		
+		if (command.startsWith("autofarm"))
+		{
+			showAutoFarm(activeChar);
+		}
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
+			if (activeChar.isAutoFarm())
+			{
+				bot.stop();
+				activeChar.setAutoFarm(false);
+				activeChar.broadcastUserInfo();
+				activeChar.broadcastCharInfo();
+			}
+			else
+			{
+				bot.start();
+				activeChar.setAutoFarm(true);
+				activeChar.broadcastUserInfo();
+				activeChar.broadcastCharInfo();
+			}
+			
+			showAutoFarm(activeChar);
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
+			if (activeChar.isAntiKsProtected())
+			{
+				// activeChar.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_RESPECT_HUNT));
+				// activeChar.sendPacket(new ExShowScreenMessage("Respct Hunt On" , 3*1000, SMPOS.TOP_CENTER, false));
+			}
+			else
+			{
+				// activeChar.sendPacket(new SystemMessage(SystemMessageId.DESACTIVATE_RESPECT_HUNT));
+				// activeChar.sendPacket(new ExShowScreenMessage("Respct Hunt Off" , 3*1000, SMPOS.TOP_CENTER, false));
+			}
+			
+			activeChar.saveAutoFarmSettings();
+			showAutoFarm(activeChar);
+		}
+		
+		if (command.startsWith("enableSummonAttack"))
+		{
+			activeChar.setSummonAttack(!activeChar.isSummonAttack());
+			if (activeChar.isSummonAttack())
+			{
+				// activeChar.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_SUMMON_ACTACK));
+				// activeChar.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack On" , 3*1000, SMPOS.TOP_CENTER, false));
+			}
+			else
+			{
+				// activeChar.sendPacket(new SystemMessage(SystemMessageId.DESACTIVATE_SUMMON_ACTACK));
+				// activeChar.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack Off" , 3*1000, SMPOS.TOP_CENTER, false));
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
+			{
+				monsterId = ((Monster) target).getNpcId();
+			}
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
+			{
+				monsterId = ((Monster) target).getNpcId();
+			}
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
+		return false;
+	}
+	
+	private static final String ACTIVED = "<font color=00FF00>STARTED</font>";
+	private static final String DESATIVED = "<font color=FF0000>STOPPED</font>";
+	private static final String STOP = "STOP";
+	private static final String START = "START";
+	
+	public static void showAutoFarm(Player activeChar)
+	{
+		NpcHtmlMessage html = new NpcHtmlMessage(0);
+		html.setFile("data/html/mods/menu/AutoFarm.htm");
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
+	@Override
+	public String[] getVoicedCommandList()
+	{
+		return VOICED_COMMANDS;
+	}
+}
\ No newline at end of file
diff --git java/org/l2jmobius/gameserver/model/actor/Creature.java java/org/l2jmobius/gameserver/model/actor/Creature.java
index e6fc06d..4fcd802 100644
--- java/org/l2jmobius/gameserver/model/actor/Creature.java
+++ java/org/l2jmobius/gameserver/model/actor/Creature.java
@@ -2224,6 +2224,15 @@
 	}
 	
 	/**
+	 * @param skillId The identifier of the L2Skill to check the knowledge
+	 * @return True if the skill is known by the Creature.
+	 */
+	public Skill getSkill(int skillId)
+	{
+		return getSkills().get(skillId);
+	}
+	
+	/**
 	 * Set the Creature flying mode to True.
 	 * @param mode the new checks if is flying
 	 */
@@ -9414,6 +9423,32 @@
 	}
 	
 	/**
+	 * Return the squared distance between the current position of the Creature and the given object.
+	 * @param object WorldObject
+	 * @return the squared distance
+	 */
+	public final double getDistanceSq(WorldObject object)
+	{
+		return getDistanceSq(object.getX(), object.getY(), object.getZ());
+	}
+	
+	/**
+	 * Return the squared distance between the current position of the Creature and the given x, y, z.
+	 * @param x X position of the target
+	 * @param y Y position of the target
+	 * @param z Z position of the target
+	 * @return the squared distance
+	 */
+	public final double getDistanceSq(int x, int y, int z)
+	{
+		double dx = x - getX();
+		double dy = y - getY();
+		double dz = z - getZ();
+		
+		return ((dx * dx) + (dy * dy) + (dz * dz));
+	}
+	
+	/**
 	 * Gets the _triggered skills.
 	 * @return the _triggeredSkills
 	 */
diff --git java/org/l2jmobius/gameserver/model/actor/Player.java java/org/l2jmobius/gameserver/model/actor/Player.java
index ce1f47c..41f6412 100644
--- java/org/l2jmobius/gameserver/model/actor/Player.java
+++ java/org/l2jmobius/gameserver/model/actor/Player.java
@@ -19,6 +19,7 @@
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
+import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collection;
@@ -240,8 +241,11 @@
 import org.l2jmobius.gameserver.taskmanager.PlayerAutoSaveTaskManager;
 import org.l2jmobius.gameserver.taskmanager.PvpFlagTaskManager;
 import org.l2jmobius.gameserver.util.Broadcast;
+import org.l2jmobius.gameserver.util.MathUtil;
 import org.l2jmobius.gameserver.util.Util;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 /**
  * This class represents all player characters in the world.<br>
  * There is always a client-thread connected to this (except if a player-store is activated upon logout).
@@ -13093,6 +13105,8 @@
 			getVariables().setIntegerList(PlayerVariables.AUTO_USE_SETTINGS, settings);
 		}
 		
+		_bot.stop();
+		
 		// Check if the Player is in observer mode to set its position to its position before entering in observer mode
 		if (inObserverMode())
 		{
@@ -15649,4 +15663,233 @@
 	{
 		return true;
 	}
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
+	private final List<Integer> _ignoredMonster = new ArrayList<>();
+	
+	public void ignoredMonster(Integer npcId)
+	{
+		_ignoredMonster.add(npcId);
+	}
+	
+	public void activeMonster(Integer npcId)
+	{
+		if (_ignoredMonster.contains(npcId))
+		{
+			_ignoredMonster.remove(npcId);
+		}
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
+		{
+			_bot = new AutofarmPlayerRoutine(this);
+		}
+		
+		return _bot;
+	}
+	
+	public void saveAutoFarmSettings()
+	{
+		try (Connection con = DatabaseFactory.getConnection())
+		{
+			String updateSql = "REPLACE INTO character_autofarm (char_id, char_name,  radius, short_cut, heal_percent, buff_protection, anti_ks_protection, summon_attack, summon_skill_percent, hp_potion_percent, mp_potion_percent) VALUES (?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?)";
+			try (PreparedStatement updateStatement = con.prepareStatement(updateSql))
+			{
+				updateStatement.setInt(1, getObjectId()); // char_id
+				updateStatement.setString(2, getName()); // char_name
+				
+				updateStatement.setInt(3, autoFarmRadius);
+				updateStatement.setInt(4, autoFarmShortCut);
+				updateStatement.setInt(5, autoFarmHealPercente);
+				updateStatement.setBoolean(6, autoFarmBuffProtection);
+				updateStatement.setBoolean(7, autoAntiKsProtection);
+				updateStatement.setBoolean(8, autoFarmSummonAttack);
+				updateStatement.setInt(9, autoFarmSummonSkillPercente);
+				updateStatement.setInt(10, hpPotionPercent);
+				updateStatement.setInt(11, mpPotionPercent);
+				updateStatement.executeUpdate();
+			}
+		}
+		catch (SQLException e)
+		{
+			e.printStackTrace();
+		}
+	}
+	
+	public void loadAutoFarmSettings()
+	{
+		try (Connection con = DatabaseFactory.getConnection())
+		{
+			String selectSql = "SELECT * FROM character_autofarm WHERE char_id = ?";
+			try (PreparedStatement selectStatement = con.prepareStatement(selectSql))
+			{
+				selectStatement.setInt(1, getObjectId()); // char_id
+				try (ResultSet resultSet = selectStatement.executeQuery())
+				{
+					if (resultSet.next())
+					{
+						
+						autoFarmRadius = resultSet.getInt("radius");
+						autoFarmShortCut = resultSet.getInt("short_cut");
+						autoFarmHealPercente = resultSet.getInt("heal_percent");
+						autoFarmBuffProtection = resultSet.getBoolean("buff_protection");
+						autoAntiKsProtection = resultSet.getBoolean("anti_ks_protection");
+						autoFarmSummonAttack = resultSet.getBoolean("summon_attack");
+						autoFarmSummonSkillPercente = resultSet.getInt("summon_skill_percent");
+						hpPotionPercent = resultSet.getInt("hp_potion_percent");
+						mpPotionPercent = resultSet.getInt("mp_potion_percent");
+					}
+					else
+					{
+						
+						autoFarmRadius = 1200;
+						autoFarmShortCut = 9;
+						autoFarmHealPercente = 30;
+						autoFarmBuffProtection = false;
+						autoAntiKsProtection = false;
+						autoFarmSummonAttack = false;
+						autoFarmSummonSkillPercente = 0;
+						hpPotionPercent = 60;
+						mpPotionPercent = 60;
+					}
+				}
+			}
+		}
+		catch (SQLException e)
+		{
+			e.printStackTrace();
+		}
+	}
+	
 }
\ No newline at end of file
diff --git java/org/l2jmobius/gameserver/network/SystemMessageId.java java/org/l2jmobius/gameserver/network/SystemMessageId.java
index faeaa6a..ec95ef5 100644
--- java/org/l2jmobius/gameserver/network/SystemMessageId.java
+++ java/org/l2jmobius/gameserver/network/SystemMessageId.java
@@ -6280,6 +6280,20 @@
 	@ClientString(id = 2153, message = "You are currently logged into 10 of your accounts and can no longer access your other accounts.")
 	public static SystemMessageId YOU_ARE_CURRENTLY_LOGGED_INTO_10_OF_YOUR_ACCOUNTS_AND_CAN_NO_LONGER_ACCESS_YOUR_OTHER_ACCOUNTS;
 	
+	@ClientString(id = 2156, message = "Because of your Fatigue level, this is not allowed.")
+	public static SystemMessageId ACTIVATE_SUMMON_ACTACK;
+	
+	@ClientString(id = 2157, message = "Because of your Fatigue level, this is not allowed.")
+	public static SystemMessageId DESACTIVATE_SUMMON_ACTACK;
+	@ClientString(id = 2158, message = "Because of your Fatigue level, this is not allowed.")
+	public static SystemMessageId ACTIVATE_RESPECT_HUNT;
+	@ClientString(id = 2159, message = "Because of your Fatigue level, this is not allowed.")
+	public static SystemMessageId DESACTIVATE_RESPECT_HUNT;
+	@ClientString(id = 2155, message = "Because of your Fatigue level, this is not allowed.")
+	public static SystemMessageId AUTO_FARM_DESACTIVATED;
+	@ClientString(id = 2160, message = "Because of your Fatigue level, this is not allowed.")
+	public static SystemMessageId AUTO_FARM_ACTIVATED;
+	
 	static
 	{
 		buildFastLookupTable();
diff --git java/org/l2jmobius/gameserver/network/clientpackets/EnterWorld.java java/org/l2jmobius/gameserver/network/clientpackets/EnterWorld.java
index fb7f499..7e69579 100644
--- java/org/l2jmobius/gameserver/network/clientpackets/EnterWorld.java
+++ java/org/l2jmobius/gameserver/network/clientpackets/EnterWorld.java
@@ -324,6 +324,19 @@
 		
 		PetitionManager.getInstance().checkPetitionMessages(player);
 		
+		player.loadAutoFarmSettings();
+		
+		if (player.isSummonAttack())
+		{
+			player.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_SUMMON_ACTACK));
+			
+		}
+		
+		if (player.isAntiKsProtected())
+		{
+			player.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_RESPECT_HUNT));
+		}
+		
 		if ((player.getClanId() != 0) && (player.getClan() != null))
 		{
 			player.sendPacket(new PledgeShowMemberListAll(player.getClan(), player));
diff --git java/org/l2jmobius/gameserver/network/clientpackets/RequestBypassToServer.java java/org/l2jmobius/gameserver/network/clientpackets/RequestBypassToServer.java
index dcfe0f4..431c5cf 100644
--- java/org/l2jmobius/gameserver/network/clientpackets/RequestBypassToServer.java
+++ java/org/l2jmobius/gameserver/network/clientpackets/RequestBypassToServer.java
@@ -16,6 +16,8 @@
  */
 package org.l2jmobius.gameserver.network.clientpackets;
 
+import java.util.StringTokenizer;
+
 import org.l2jmobius.Config;
 import org.l2jmobius.commons.network.ReadablePacket;
 import org.l2jmobius.commons.util.CommonUtil;
@@ -37,10 +39,15 @@
 import org.l2jmobius.gameserver.model.olympiad.Olympiad;
 import org.l2jmobius.gameserver.network.GameClient;
 import org.l2jmobius.gameserver.network.PacketLogger;
+import org.l2jmobius.gameserver.network.SystemMessageId;
 import org.l2jmobius.gameserver.network.serverpackets.ActionFailed;
+import org.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;
 import org.l2jmobius.gameserver.network.serverpackets.NpcHtmlMessage;
+import org.l2jmobius.gameserver.network.serverpackets.SystemMessage;
 import org.l2jmobius.gameserver.util.GMAudit;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 public class RequestBypassToServer implements ClientPacket
 {
 	// S
@@ -61,6 +68,8 @@
 			return;
 		}
 		
+		final AutofarmPlayerRoutine bot = player.getBot();
+		
 		if (!client.getFloodProtectors().canUseServerBypass())
 		{
 			return;
@@ -107,6 +116,123 @@
 				ach.useAdminCommand(_command, player);
 				player.sendPacket(ActionFailed.STATIC_PACKET);
 			}
+			
+			else if (_command.startsWith("_autofarm"))
+			{
+				if (player.isAutoFarm())
+				{
+					bot.stop();
+					player.setAutoFarm(false);
+					player.broadcastUserInfo();
+					player.broadcastCharInfo();
+				}
+				else
+				{
+					bot.start();
+					player.setAutoFarm(true);
+					player.broadcastUserInfo();
+					player.broadcastCharInfo();
+				}
+				
+			}
+			
+			if (_command.startsWith("_pageAutoFarm"))
+			{
+				StringTokenizer st = new StringTokenizer(_command, " ");
+				st.nextToken();
+				try
+				{
+					String param = st.nextToken();
+					
+					if (param.startsWith("inc_page") || param.startsWith("dec_page"))
+					{
+						int newPage;
+						
+						if (param.startsWith("inc_page"))
+						{
+							newPage = player.getPage() + 1;
+						}
+						else
+						{
+							newPage = player.getPage() - 1;
+						}
+						
+						if ((newPage >= 0) && (newPage <= 9))
+						{
+							String[] pageStrings =
+							{
+								"F1",
+								"F2",
+								"F3",
+								"F4",
+								"F5",
+								"F6",
+								"F7",
+								"F8",
+								"F9",
+								"F10"
+							};
+							
+							player.setPage(newPage);
+							player.sendPacket(new ExShowScreenMessage("Auto Farm Skill Bar " + pageStrings[newPage], 3 * 1000));
+							player.saveAutoFarmSettings();
+							
+						}
+						
+					}
+					
+				}
+				catch (Exception e)
+				{
+					e.printStackTrace();
+				}
+			}
+			
+			if (_command.startsWith("_enableBuffProtect"))
+			{
+				player.setNoBuffProtection(!player.isNoBuffProtected());
+				if (player.isNoBuffProtected())
+				{
+					player.sendPacket(new ExShowScreenMessage("Auto Farm Buff Protect On", 3 * 1000));
+				}
+				else
+				{
+					player.sendPacket(new ExShowScreenMessage("Auto Farm Buff Protect Off", 3 * 1000));
+				}
+				player.saveAutoFarmSettings();
+			}
+			if (_command.startsWith("_enableSummonAttack"))
+			{
+				player.setSummonAttack(!player.isSummonAttack());
+				if (player.isSummonAttack())
+				{
+					player.sendPacket(new SystemMessage(SystemMessageId.ACTIVATE_SUMMON_ACTACK));
+					player.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack On", 3 * 1000));
+				}
+				else
+				{
+					player.sendPacket(new SystemMessage(SystemMessageId.DESACTIVATE_SUMMON_ACTACK));
+					player.sendPacket(new ExShowScreenMessage("Auto Farm Summon Attack Off", 3 * 1000));
+				}
+				player.saveAutoFarmSettings();
+			}
+			
+			else if (_command.startsWith("voiced_"))
+			{
+				String command = _command.split(" ")[0];
+				
+				IVoicedCommandHandler ach = VoicedCommandHandler.getInstance().getVoicedCommandHandler(_command.substring(7));
+				
+				if (ach == null)
+				{
+					player.sendMessage("The command " + command.substring(7) + " does not exist!");
+					PacketLogger.warning("No handler registered for command '" + _command + "'");
+					return;
+				}
+				
+				ach.useVoicedCommand(_command.substring(7), player, null);
+			}
+			
 			else if (_command.equals("come_here") && player.isGM())
 			{
 				final WorldObject obj = player.getTarget();
diff --git java/org/l2jmobius/gameserver/network/serverpackets/Die.java java/org/l2jmobius/gameserver/network/serverpackets/Die.java
index ee75ca2..84358ae 100644
--- java/org/l2jmobius/gameserver/network/serverpackets/Die.java
+++ java/org/l2jmobius/gameserver/network/serverpackets/Die.java
@@ -27,6 +27,8 @@
 import org.l2jmobius.gameserver.model.siege.SiegeClan;
 import org.l2jmobius.gameserver.network.ServerPackets;
 
+import Base.AutoFarm.AutofarmPlayerRoutine;
+
 public class Die extends ServerPacket
 {
 	private final int _objectId;
@@ -43,9 +45,17 @@
 		if (creature instanceof Player)
 		{
 			final Player player = creature.getActingPlayer();
+			final AutofarmPlayerRoutine bot = player.getBot();
 			_allowFixedRes = player.getAccessLevel().allowFixedRes();
 			_clan = player.getClan();
 			_canTeleport = !player.isPendingRevive();
+			
+			if (player.isAutoFarm())
+			{
+				bot.stop();
+				player.setAutoFarm(false);
+			}
+			
 		}
 		_objectId = creature.getObjectId();
 		_fake = !creature.isDead();
