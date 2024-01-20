/*
 * Copyright (c) 2024 DenArt Designs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hopzone.eu.util;

import java.util.HashMap;
import java.util.Map;

import hopzone.eu.Configurations;
import hopzone.eu.gui.Gui;
import hopzone.eu.vote.VDSystem;

/**
 * @Author Nightwolf iToPz Discord: https://discord.gg/KkPms6B5aE
 * @Author Rationale Base structure credits goes on Rationale Discord: Rationale#7773
 *         <p>
 *         VDS Stands for: Vote Donation System Script website: https://itopz.com/ Partner website: https://hopzone.eu/ Script version: 1.8 Pack Support: aCis 401
 *         <p>
 *         Freemium Donate Panel V4: https://www.denart-designs.com/ Download: https://mega.nz/folder/6oxUyaIJ#qQDUXeoXlPvBjbPMDYzu-g Buy: https://shop.denart-designs.com/product/auto-donate-panel-v4/ Quick Guide: https://github.com/nightw0lv/VDSystem/tree/master/Guide
 */
public class Json
{
	private static final Logs _log = new Logs(Json.class.getSimpleName());
	private final Map<String, String> data = new HashMap<>();
	private int i;
	private String[] split;
	
	/**
	 * set body of json array
	 * @param text string
	 * @param TOPSITE string
	 * @param TYPE enum
	 */
	public Json(String text, String TOPSITE, VDSystem.VoteType TYPE)
	{
		if (Configurations.DEBUG)
			_log.info("------------ Parsing Json " + TOPSITE + " TYPE: " + TYPE);
		i = 0;
		split = null;
		if (text != null)
		{
			for (String s : text.replaceAll("[{}\"]", "").replace("result:", "").split(","))
			{
				if (s == null)
					continue;
				
				if (TYPE == VDSystem.VoteType.GLOBAL)
					setGlobalVars(TOPSITE, s);
				if (TYPE == VDSystem.VoteType.INDIVIDUAL)
					setIndividualVars(TOPSITE, s);
			}
		}
		// put l2jbrasil votes after the loop finish
		if (TOPSITE.equals("L2JBRASIL") && TYPE == VDSystem.VoteType.GLOBAL)
			data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", "" + i);
		
		if (Configurations.DEBUG)
			_log.info("------------");
	}
	
	/**
	 * Set global variables
	 * @param TOPSITE string
	 * @param s string
	 */
	private void setGlobalVars(String TOPSITE, String s)
	{
		try
		{
			if (Configurations.DEBUG)
				_log.info(TOPSITE + " Original line:" + s);
			
			if (TOPSITE.equals("HOPZONE"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("total_monthly_votes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", split[1].trim());
				return;
			}
			
			if (TOPSITE.equals("ITOPZ"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("server_votes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", split[1].trim());
				if (split[0].contains("server_rank"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_rank", split[1].trim());
				split[0] = split[0].trim();
				if (split[0].equals("next_rank_votes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_rank_votes", split[1].trim());
				if (split[0].equals("next_rank"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_next_rank", split[1].trim());
				return;
			}
			
			// when noob hopzone return date time using ":" symbol in json.. instead of milliseconds
			if (TOPSITE.equals("HOPZONENET"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("totalvotes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", split[1].trim());
				return;
			}
			
			if (TOPSITE.equals("L2TOPGAMESERVER"))
			{
				if (s.contains("true"))
					return;
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("getVotes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", split[1].trim());
				return;
			}
			
			// this guy thinks -1 on empty page is api
			if (TOPSITE.equals("L2NETWORK"))
			{
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + s.trim());
				data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", s.trim());
				return;
			}
			
			// now there is a special place in hell for these guys.
			// 2023 note they broke again this api, global asks for player id and shit.
			if (TOPSITE.equals("L2JBRASIL"))
			{
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " DEBUG: " + i);
				if (s.startsWith("id"))
					i++;
				return;
			}
			
			// still learning what api key is
			if (TOPSITE.equals("L2VOTES"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("votes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", split[1]);
				return;
			}
			
			if (TOPSITE.equals("HOTSERVERS"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("server_votes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", split[1].trim());
			}
			
			if (TOPSITE.equals("L2RANKZONE"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("currentVotes"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", split[1].trim());
			}
			
			if (TOPSITE.equals("TOP4TEAMBR"))
			{
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + s.trim());
				data.putIfAbsent(TOPSITE.toLowerCase() + "_votes", s.trim());
				return;
			}
		}
		catch (IndexOutOfBoundsException ioobe)
		{
			if (Configurations.DEBUG)
				_log.error("IOOBE: " + ioobe.getMessage(), ioobe);
			Gui.getInstance().ConsoleWrite("IOOBE: " + ioobe.getMessage());
		}
		catch (Exception e)
		{
			if (Configurations.DEBUG)
				_log.error("Exception: " + e.getMessage(), e);
			Gui.getInstance().ConsoleWrite("IOOBE: " + e.getMessage());
		}
	}
	
	/**
	 * set individual variables
	 * @param TOPSITE string
	 * @param s string
	 */
	private void setIndividualVars(String TOPSITE, String s)
	{
		try
		{
			if (Configurations.DEBUG)
				_log.info(TOPSITE + " Original line:" + s);
			
			if (TOPSITE.equals("HOPZONE"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				// vote id check
				if (split[0].contains("status"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim().equals("completed") ? "true" : "false");
				if (split[0].contains("vote_time"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim());
				if (split[0].contains("server_time"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim());
				
				// vote ip check
				if (split[0].contains("isVoted"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				if (split[0].contains("voteTime"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim());
				if (split[0].contains("serverTime"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim());
				return;
			}
			
			if (TOPSITE.equals("ITOPZ"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("isVoted"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				if (split[0].contains("voteTime"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim());
				if (split[0].contains("serverTime"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim());
				return;
			}
			
			// when noob hopzone return date time using ":" symbol in json.. instead of milliseconds
			if (TOPSITE.equals("HOPZONENET"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("voted"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				if (split[0].contains("voteTime") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
				if (split[0].contains("hopzoneServerTime") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
				return;
			}
			
			if (TOPSITE.equals("L2TOPGAMESERVER"))
			{
				if (s.equals("true"))
					return;
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				// if (split[0].contains("already_voted"))
				// data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				if (split[0].contains("vote_time"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", "true");
				if (split[0].contains("vote_time") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
				if (split[0].contains("server_time") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
				return;
			}
			
			// this guy thinks showing with post request -1 on empty page is api
			if (TOPSITE.equals("L2NETWORK"))
			{
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + s.trim());
				data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", s.trim());
				return;
			}
			
			// this time they did it.. but still stupid as hopzone
			if (TOPSITE.equals("L2JBRASIL"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("status"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				if (split[0].contains("date") && !s.equals("date:0") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
				if (split[0].contains("server_time") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
			}
			
			// still learning what api key is
			if (TOPSITE.equals("L2VOTES"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("status"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				return;
			}
			
			// another guy who copied hopzone mistake.
			if (TOPSITE.equals("HOTSERVERS"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("has_voted"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				if (split[0].contains("vote_time") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
				if (split[0].contains("server_time") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
			}
			
			if (TOPSITE.equals("L2RANKZONE"))
			{
				split = s.split(":");
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + split[0].trim() + ":" + split[1].trim());
				if (split[0].contains("voted"))
					data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", split[1].trim());
				if (split[0].contains("voteTime") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_vote_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
				if (split[0].contains("serverTime") && split.length >= 3)
					data.putIfAbsent(TOPSITE.toLowerCase() + "_server_time", split[1].trim() + ":" + split[2].trim() + ":" + split[3].trim());
			}
			
			if (TOPSITE.equals("TOP4TEAMBR"))
			{
				if (Configurations.DEBUG)
					_log.info(TOPSITE + " trimmed line :" + s.trim());
				data.putIfAbsent(TOPSITE.toLowerCase() + "_voted", s.trim());
				return;
			}
		}
		catch (IndexOutOfBoundsException ioobe)
		{
			if (Configurations.DEBUG)
				_log.error("IOOBE: " + ioobe.getMessage(), ioobe);
			Gui.getInstance().ConsoleWrite("IOOBE: " + ioobe.getMessage());
		}
		catch (Exception e)
		{
			if (Configurations.DEBUG)
				_log.error("Exception: " + e.getMessage(), e);
			Gui.getInstance().ConsoleWrite("IOOBE: " + e.getMessage());
		}
	}
	
	/**
	 * return string value from map
	 * @param key String
	 * @return string
	 */
	public String getString(String key)
	{
		return data.containsKey(key) ? String.valueOf(data.getOrDefault(key, "-2")) : "NONE";
	}
	
	/**
	 * return integer value from map
	 * @param key String
	 * @return int
	 */
	public Integer getInteger(String key)
	{
		return data.containsKey(key) ? Integer.parseInt(data.getOrDefault(key, "-2")) : -2;
	}
	
	/**
	 * Return boolean value from map
	 * @param key String
	 * @return boolean
	 */
	public boolean getBoolean(String key)
	{
		return data.containsKey(key) ? Boolean.parseBoolean(data.getOrDefault(key, "false")) || data.getOrDefault(key, "false").equals("1") : false;
	}
	
	/**
	 * Return long value from map
	 * @param key String
	 * @return long
	 */
	public long getLong(String key)
	{
		return data.containsKey(key) ? Long.parseLong(data.getOrDefault(key, "-2")) : -2;
	}
}