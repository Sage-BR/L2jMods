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

import java.util.Arrays;

import hopzone.eu.Configurations;

/**
 * @Author Nightwolf iToPz Discord: https://discord.gg/KkPms6B5aE
 * @Author Rationale Base structure credits goes on Rationale Discord: Rationale#7773
 *         <p>
 *         VDS Stands for: Vote Donation System Script website: https://itopz.com/ Partner website: https://hopzone.eu/ Script version: 1.8 Pack Support: aCis 401
 *         <p>
 *         Freemium Donate Panel V4: https://www.denart-designs.com/ Download: https://mega.nz/folder/6oxUyaIJ#qQDUXeoXlPvBjbPMDYzu-g Buy: https://shop.denart-designs.com/product/auto-donate-panel-v4/ Quick Guide: https://github.com/nightw0lv/VDSystem/tree/master/Guide
 */
public enum Url
{
	HOPZONE_INDIVIDUAL_URL_IP("https://api.hopzone.eu/v1/?api_key=" + Configurations.HOPZONE_EU_SERVER_API_KEY + "&ip=%IP%&type=json"),
	HOPZONE_INDIVIDUAL_URL_VOTE_ID("https://api.hopzone.eu/v1/?api_key=" + Configurations.HOPZONE_EU_SERVER_API_KEY + "&vote_id=%IP%&type=json"),
	HOPZONE_INDIVIDUAL_URL_NAME("https://api.hopzone.eu/v1/?api_key=" + Configurations.HOPZONE_EU_SERVER_API_KEY + "&name=%PLAYER_NAME%&type=json"),
	HOPZONE_INDIVIDUAL_GENERATE_VOTE("https://api.hopzone.eu/v1/?api_key=" + Configurations.HOPZONE_EU_SERVER_API_KEY + "&generate=" + Configurations.HOPZONE_EU_SERVER_ID + "&type=json"),
	HOPZONE_GLOBAL_URL("https://api.hopzone.eu/v1/?api_key=" + Configurations.HOPZONE_EU_SERVER_API_KEY + "&server_id=" + Configurations.HOPZONE_EU_SERVER_ID),
	HOPZONE_URL("https://hopzone.eu/"),
	ITOPZ_GLOBAL_URL("https://itopz.com/check/" + Configurations.ITOPZ_SERVER_API_KEY + "/" + Configurations.ITOPZ_SERVER_ID + "/"),
	ITOPZ_INDIVIDUAL_URL_IP("https://itopz.com/check/" + Configurations.ITOPZ_SERVER_API_KEY + "/" + Configurations.ITOPZ_SERVER_ID + "/%IP%"),
	ITOPZ_SERVER_URL("https://itopz.com/info/" + Configurations.ITOPZ_SERVER_ID),
	ITOPZ_URL("https://itopz.com/"),
	HOPZONENET_INDIVIDUAL_URL_IP("https://api.hopzone.net/lineage2/vote?token=" + Configurations.HOPZONE_NET_SERVER_API_KEY + "&ip_address=%IP%"),
	HOPZONENET_GLOBAL_URL("https://api.hopzone.net/lineage2/votes?token=" + Configurations.HOPZONE_NET_SERVER_API_KEY),
	HOPZONENET_URL("https://hopzone.net/"),
	L2TOPGAMESERVER_INDIVIDUAL_URL_IP("https://l2.topgameserver.net/lineage/VoteApi/API_KEY=" + Configurations.L2TOPGAMESERVER_API_KEY + "/getData/%IP%"),
	L2TOPGAMESERVER_GLOBAL_URL("https://l2.topgameserver.net/lineage/VoteApi/API_KEY=" + Configurations.L2TOPGAMESERVER_API_KEY + "/getData/"),
	L2TOPGAMESERVER_URL("https://l2.topgameserver.net/"),
	L2NETWORK_INDIVIDUAL_URL_IP("https://l2network.eu/index.php?a=in&u=" + Configurations.L2NETWORK_USER_NAME + "&ipc=%IP%"),
	L2NETWORK_GLOBAL_URL("https://l2network.eu/api.php"),
	L2NETWORK_URL("https://l2network.eu/"),
	L2JBRASIL_INDIVIDUAL_URL_IP("https://top.l2jbrasil.com/votesystem/index.php?username=" + Configurations.L2JBRASIL_USER_NAME + "&ip=%IP%&type=json"),
	L2JBRASIL_GLOBAL_URL("https://top.l2jbrasil.com/votesystem/index.php?username=" + Configurations.L2JBRASIL_USER_NAME + "&type=json"),
	L2JBRASIL_URL("https://top.l2jbrasil.com/"),
	HOTSERVERS_INDIVIDUAL_URL_IP("https://hotservers.org/api/servers/" + Configurations.HOTSERVERS_SERVER_ID + "/voteCheck?api_token=" + Configurations.HOTSERVERS_API_KEY + "&ip_address=%IP%"),
	HOTSERVERS_GLOBAL_URL("https://hotservers.org/api/servers/" + Configurations.HOTSERVERS_SERVER_ID + "/votes?api_token=" + Configurations.HOTSERVERS_API_KEY),
	HOTSERVERS_URL("https://hotservers.org/"),
	L2VOTES_INDIVIDUAL_URL_IP("https://l2votes.com/api.php?apiKey=" + Configurations.L2VOTES_API_KEY + "&ip=%IP%"),
	L2VOTES_GLOBAL_URL("https://l2votes.com/api.php?apiKey=" + Configurations.L2VOTES_API_KEY),
	L2VOTES_URL("https://l2votes.com/"),
	L2RANKZONE_INDIVIDUAL_URL_IP("https://l2rankzone.com/api/vote-reward?apiKey=" + Configurations.L2RANKZONE_API_KEY + "&ip=%IP%"),
	L2RANKZONE_GLOBAL_URL("https://l2rankzone.com/api/server-stats?apiKey=" + Configurations.L2RANKZONE_API_KEY),
	L2RANKZONE_URL("https://l2rankzone.com/"),
	TOP4TEAMBR_INDIVIDUAL_URL_IP("https://top.4teambr.com/index.php?a=in&u=" + Configurations.TOP4TEAMBR_USER_NAME + "&ipc=%ip%"),
	// TOP4TEAMBR_GLOBAL_URL("https://top.4teambr.com/index.php?a=in&u=" + Configurations.TOP4TEAMBR_USER_NAME + "&ipc=%IP%"),
	TOP4TEAMBR_GLOBAL_URL("https://top.4teambr.com/plugins/Callback/"),
	TOP4TEAMBR_URL("https://top.4teambr.com/"),
	DISCORD("https://discord.gg/Bf26EkYk"),
	DENART_DESIGNS("https://www.denart-designs.com"),
	DENART_DESIGNS_DONATE_PANEL("https://shop.denart-designs.com/product/auto-donate-panel-v4/"),
	DENART_DESIGNS_VOTE_PANEL("https://shop.denart-designs.com/product/l2off-l2java-vote-panel-v2/"),
	DENART_DESIGNS_REFERRAL_PANEL("https://shop.denart-designs.com/product/referral-panel/"),
	GITHUB_URL("https://github.com/nightw0lv/VDSystem");
	
	private final String _text;
	private static final Url[] _urls = values();
	
	/**
	 * @param text String
	 */
	Url(final String text)
	{
		_text = text;
	}
	
	/**
	 * (non-Javadoc)
	 * @see Enum#toString()
	 */
	@Override
	public String toString()
	{
		return _text;
	}
	
	/**
	 * returns enum from string
	 * @param TOPSITE string
	 * @return Individual
	 */
	public static Url from(String TOPSITE)
	{
		return Arrays.stream(_urls).filter(s -> s.name().equalsIgnoreCase(TOPSITE)).findFirst().orElse(HOPZONE_URL);
	}
}