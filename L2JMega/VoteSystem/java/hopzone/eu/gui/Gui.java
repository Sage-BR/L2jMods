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
package hopzone.eu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import hopzone.eu.Configurations;
import hopzone.eu.global.Global;
import hopzone.eu.util.Url;
import hopzone.eu.util.Utilities;
import hopzone.eu.util.VDSThreadPool;

/**
 * @Author Nightwolf iToPz Discord: https://discord.gg/KkPms6B5aE
 * @Author Rationale Base structure credits goes on Rationale Discord: Rationale#7773
 *         <p>
 *         VDS Stands for: Vote Donation System Script website: https://itopz.com/ Partner website: https://hopzone.eu/ Script version: 1.8 Pack Support: Mobius 09.2 ReturnOfTheQueenAnt
 *         <p>
 *         Freemium Donate Panel V4: https://www.denart-designs.com/ Download: https://mega.nz/folder/6oxUyaIJ#qQDUXeoXlPvBjbPMDYzu-g Buy: https://shop.denart-designs.com/product/auto-donate-panel-v4/ Quick Guide: https://github.com/nightw0lv/VDSystem/tree/master/Guide
 */
public class Gui extends JFrame
{
	// console
	private JTextArea console;
	
	// menu
	private JMenu menuServer, menuDonate, menuAbout;
	
	// box
	private Box box;
	
	// label
	private JLabel HOPZONE, ITOPZ, HOPZONENET, L2TOPGAMESERVER, L2NETWORK, TOPL2JBRASIL, L2VOTES, HOTSERVERS, L2RANKZONE, TOP4TEAMBR;
	
	/**
	 * Constructor load gui
	 */
	Gui()
	{
		if (Configurations.VDS_CONSOLE_ENABLE)
		{
			startGui();
			addMenuItems();
		}
	}
	
	/**
	 * set up all the GUI components
	 */
	public void startGui()
	{
		setVisible(false);
		try
		{
			JWindow window = new JWindow();
			window.getContentPane().add(new JLabel("", new ImageIcon(new URL("https://denart-designs.com/assets/images/denart.png")), SwingConstants.CENTER));
			window.setBounds(500, 150, 331, 302);
			window.setBackground(new Color(0, 0, 0, 0));
			window.pack();
			window.setLocationRelativeTo(null);
			window.setVisible(true);
			
			VDSThreadPool.schedule(() -> {
				window.setVisible(false);
				window.dispose();
				setVisible(true);
			}, 7000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// set menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// set menu item
		menuServer = new JMenu("Server");
		menuBar.add(menuServer);
		menuDonate = new JMenu("Donates");
		menuBar.add(menuDonate);
		menuAbout = new JMenu("About");
		menuBar.add(menuAbout);
		menuBar.setBackground(Color.black);
		box = Box.createVerticalBox();
		
		// set HOPZONE.EU statistics label info
		HOPZONE = new JLabel("Waiting for hopzone.eu statistics info.");
		HOPZONE.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		HOPZONE.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		HOPZONE.setText("Server Votes: votes HOPZONE.EU.");
		
		// set ITOPZ.COM statistics label info
		ITOPZ = new JLabel("Waiting for itopz.com statistics info.");
		ITOPZ.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		ITOPZ.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		ITOPZ.setText("Server Votes: votes ITOPZ.COM.");
		
		// set HOPZONE.NET statistics label info
		HOPZONENET = new JLabel("Waiting for hopzone.net statistics info.");
		HOPZONENET.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		HOPZONENET.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		HOPZONENET.setText("Server Votes: votes HOPZONE.NET.");
		
		// set L2TOPGAMESERVER statistics label info
		L2TOPGAMESERVER = new JLabel("Waiting for statistics L2TOPGAMESERVER.");
		L2TOPGAMESERVER.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		L2TOPGAMESERVER.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		L2TOPGAMESERVER.setText("Server Votes: votes L2TOPGAMESERVER.");
		
		// set L2NETWORK statistics label info
		L2NETWORK = new JLabel("Waiting for statistics info L2NETWORK.");
		L2NETWORK.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		L2NETWORK.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		L2NETWORK.setText("Server Votes: L2NETWORK.");
		
		// set TOPL2JBRASIL statistics label info
		TOPL2JBRASIL = new JLabel("Waiting for statistics info TOPL2JBRASIL.");
		TOPL2JBRASIL.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		TOPL2JBRASIL.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		TOPL2JBRASIL.setText("Server Votes: votes TOPL2JBRASIL.");
		
		// set HOTSERVERS statistics label info
		HOTSERVERS = new JLabel("Waiting for statistics info HOTSERVERS.");
		HOTSERVERS.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		HOTSERVERS.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		HOTSERVERS.setText("Server Votes: HOTSERVERS.");
		
		// set L2VOTES statistics label info
		L2VOTES = new JLabel("Waiting for statistics info L2VOTES.");
		L2VOTES.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		L2VOTES.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		L2VOTES.setText("Server Votes: votes L2VOTES.");
		
		// set L2RANKZONE statistics label info
		L2RANKZONE = new JLabel("Waiting for statistics info L2RANKZONE.");
		L2RANKZONE.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		L2RANKZONE.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		L2RANKZONE.setText("Server Votes: votes L2RANKZONE.");
		
		// set TOP4TEAMBR statistics label info
		TOP4TEAMBR = new JLabel("Waiting for statistics info TOP4TEAMBR.");
		TOP4TEAMBR.setBounds(1, 150, Configurations.VDS_CONSOLE_WIDTH, 5);
		TOP4TEAMBR.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		TOP4TEAMBR.setText("Server Votes: votes TOP4TEAMBR.");
		
		// Create a JTextArea
		console = new JTextArea("Server info started.");
		console.setBounds(200, 250, Configurations.VDS_CONSOLE_WIDTH, 550);
		console.setFont(new Font(Configurations.VDS_CONSOLE_FONT, Font.BOLD, Configurations.VDS_CONSOLE_SIZE));
		console.setLineWrap(true); // wrap line
		console.setWrapStyleWord(true); // wrap line at word boundary
		console.setBackground(new Color(Configurations.VDS_CONSOLE_COLOR_R, Configurations.VDS_CONSOLE_COLOR_G, Configurations.VDS_CONSOLE_COLOR_B)); // light blue
		
		// Wrap the JTextArea inside a JScrollPane
		JScrollPane ConsolePanel = new JScrollPane(console);
		ConsolePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		ConsolePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ConsolePanel.setSize(Configurations.VDS_CONSOLE_WIDTH, 500);
		
		// Setup the content-pane of JFrame in BorderLayout
		Container cp = this.getContentPane();
		cp.setBackground(Color.BLACK);
		cp.setLayout(new GridLayout(1, 2, 5, 5)); // for 1 column, multiple rows
		
		// add box menu
		box();
		add(box);
		
		// console
		cp.add(ConsolePanel, BorderLayout.EAST);
		
		// Setup basic window settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("VDSystem server console.");
		setSize(Configurations.VDS_CONSOLE_WIDTH, Configurations.VDS_CONSOLE_HEIGHT);
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateHopzoneStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		HOPZONE.setText("HOPZONE.EU Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			HOPZONE.setForeground(Color.RED);
		else
			HOPZONE.setForeground(Color.GREEN);
		if (Configurations.HOPZONE_EU_VOTE_CHECK_DELAY > 60)
			HOPZONE.setToolTipText("<html>Statistics up date every " + Configurations.HOPZONE_EU_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			HOPZONE.setToolTipText("<html>Statistics up date every " + Configurations.HOPZONE_EU_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 * @param serverRank int
	 * @param serverNextRank int
	 * @param serverNeededVotes int
	 */
	public void UpdateItopzStats(int serverVotes, int serverRank, int serverNextRank, int serverNeededVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		ITOPZ.setText("ITOPZ Server Votes: " + serverVotes + " Rank:" + serverRank + " Next Rank(" + serverNextRank + ") need: " + serverNeededVotes + "votes.");
		if (serverVotes < 0)
		{
			ITOPZ.setForeground(Color.RED);
			ITOPZ.setToolTipText("<html>Check for errors</html>");
		}
		else
		{
			if (Configurations.ITOPZ_SERVER_API_KEY.equals("DEMO"))
			{
				ITOPZ.setForeground(Color.ORANGE);
				ITOPZ.setToolTipText("<html>Change your config from DEMO into an actual API Key obtain from itopz</html>");
			}
			else if (Configurations.ITOPZ_SERVER_API_KEY.length() >= 30)
			{
				ITOPZ.setForeground(Color.GREEN);
				ITOPZ.setToolTipText("<html>Live mode</html>");
			}
			else
			{
				ITOPZ.setForeground(Color.RED);
				ITOPZ.setToolTipText("<html>Use DEMO or an actual API Key from itopz</html>");
			}
		}
		if (Configurations.ITOPZ_VOTE_CHECK_DELAY > 60)
			ITOPZ.setToolTipText("<html>Statistics up date every " + Configurations.ITOPZ_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			ITOPZ.setToolTipText("<html>Statistics up date every " + Configurations.ITOPZ_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateHopzonenetStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		HOPZONENET.setText("HOPZONE Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			HOPZONENET.setForeground(Color.RED);
		else
			HOPZONENET.setForeground(Color.GREEN);
		if (Configurations.HOPZONE_NET_VOTE_CHECK_DELAY > 60)
			HOPZONENET.setToolTipText("<html>Statistics up date every " + Configurations.HOPZONE_NET_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			HOPZONENET.setToolTipText("<html>Statistics up date every " + Configurations.HOPZONE_NET_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateTopGameServerStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		L2TOPGAMESERVER.setText("L2TOPGAMESERVER Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			L2TOPGAMESERVER.setForeground(Color.RED);
		else
			L2TOPGAMESERVER.setForeground(Color.GREEN);
		if (Configurations.L2TOPGAMESERVER_VOTE_CHECK_DELAY > 60)
			L2TOPGAMESERVER.setToolTipText("<html>Statistics up date every " + Configurations.L2TOPGAMESERVER_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			L2TOPGAMESERVER.setToolTipText("<html>Statistics up date every " + Configurations.L2TOPGAMESERVER_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateNetworkStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		L2NETWORK.setText("L2Network Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			L2NETWORK.setForeground(Color.RED);
		else
			L2NETWORK.setForeground(Color.GREEN);
		if (Configurations.L2NETWORK_VOTE_CHECK_DELAY > 60)
			L2NETWORK.setToolTipText("<html>Statistics up date every " + Configurations.L2NETWORK_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			L2NETWORK.setToolTipText("<html>Statistics up date every " + Configurations.L2NETWORK_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateBrasilStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		TOPL2JBRASIL.setText("L2JBrasil Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			TOPL2JBRASIL.setForeground(Color.RED);
		else
			TOPL2JBRASIL.setForeground(Color.GREEN);
		if (Configurations.L2JBRASIL_VOTE_CHECK_DELAY > 60)
			TOPL2JBRASIL.setToolTipText("<html>Statistics up date every " + Configurations.L2JBRASIL_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			TOPL2JBRASIL.setToolTipText("<html>Statistics up date every " + Configurations.L2JBRASIL_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateHotServersStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		HOTSERVERS.setText("HotServers Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			HOTSERVERS.setForeground(Color.RED);
		else
			HOTSERVERS.setForeground(Color.GREEN);
		if (Configurations.HOTSERVERS_VOTE_CHECK_DELAY > 60)
			HOTSERVERS.setToolTipText("<html>Statistics up date every " + Configurations.HOTSERVERS_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			HOTSERVERS.setToolTipText("<html>Statistics up date every " + Configurations.HOTSERVERS_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateVotesStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		L2VOTES.setText("L2Votes Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			L2VOTES.setForeground(Color.RED);
		else
			L2VOTES.setForeground(Color.GREEN);
		if (Configurations.L2VOTES_VOTE_CHECK_DELAY > 60)
			L2VOTES.setToolTipText("<html>Statistics up date every " + Configurations.L2VOTES_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			L2VOTES.setToolTipText("<html>Statistics up date every " + Configurations.L2VOTES_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateL2RankZoneStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		L2RANKZONE.setText("L2RankZone Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			L2RANKZONE.setForeground(Color.RED);
		else
			L2RANKZONE.setForeground(Color.GREEN);
		if (Configurations.L2RANKZONE_VOTE_CHECK_DELAY > 60)
			L2RANKZONE.setToolTipText("<html>Statistics up date every " + Configurations.L2RANKZONE_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			L2RANKZONE.setToolTipText("<html>Statistics up date every " + Configurations.L2RANKZONE_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * update statistics
	 * @param serverVotes int
	 */
	public void UpdateTop4TeamBRStats(int serverVotes)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		
		TOP4TEAMBR.setText("Top4TeamBR Server Votes: " + serverVotes + "votes.");
		if (serverVotes < 0)
			TOP4TEAMBR.setForeground(Color.RED);
		else
			TOP4TEAMBR.setForeground(Color.GREEN);
		if (Configurations.TOP4TEAMBR_VOTE_CHECK_DELAY > 60)
			TOP4TEAMBR.setToolTipText("<html>Statistics up date every " + Configurations.TOP4TEAMBR_VOTE_CHECK_DELAY / 60 + " minutes</html>");
		else
			TOP4TEAMBR.setToolTipText("<html>Statistics up date every " + Configurations.TOP4TEAMBR_VOTE_CHECK_DELAY + " seconds</html>");
	}
	
	/**
	 * Create Menu items
	 */
	public void addMenuItems()
	{
		// run itopz global reward
		JMenuItem debug = new JMenuItem("DEBUG");
		debug.addActionListener(al -> {
			Configurations.DEBUG = !Configurations.DEBUG;
			ConsoleWrite("DEBUG: " + Configurations.DEBUG);
		});
		debug.setToolTipText("<html>Show debug messages on console</html>");
		menuServer.add(debug);
		
		// run itopz global reward
		JMenuItem run_itopz_global_reward = new JMenuItem("Run iTopZ Global");
		run_itopz_global_reward.addActionListener(al -> Global.getInstance().execute("ITOPZ"));
		run_itopz_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_itopz_global_reward);
		
		// run Hopzone global reward
		JMenuItem run_hopzone_global_reward = new JMenuItem("Run Hopzone Global");
		run_hopzone_global_reward.addActionListener(al -> Global.getInstance().execute("HOPZONE"));
		run_hopzone_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_hopzone_global_reward);
		
		// run L2TopGameServer global reward
		JMenuItem run_l2topgameserver_global_reward = new JMenuItem("Run L2TopGameServer Global");
		run_l2topgameserver_global_reward.addActionListener(al -> Global.getInstance().execute("L2TOPGAMESERVER"));
		run_l2topgameserver_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_l2topgameserver_global_reward);
		
		// run L2Network global reward
		JMenuItem run_l2network_global_reward = new JMenuItem("Run L2Network Global");
		run_l2network_global_reward.addActionListener(al -> Global.getInstance().execute("L2NETWORK"));
		run_l2network_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_l2network_global_reward);
		
		// run L2JBrasil global reward
		JMenuItem run_l2jbrasil_global_reward = new JMenuItem("Run L2JBrasil Global");
		run_l2jbrasil_global_reward.addActionListener(al -> Global.getInstance().execute("L2JBRASIL"));
		run_l2jbrasil_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_l2jbrasil_global_reward);
		
		// run L2Votes global reward
		JMenuItem run_l2votes_global_reward = new JMenuItem("Run L2Votes Global");
		run_l2votes_global_reward.addActionListener(al -> Global.getInstance().execute("L2VOTES"));
		run_l2votes_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_l2votes_global_reward);
		
		// run HotServers global reward
		JMenuItem run_hotservers_global_reward = new JMenuItem("Run HotServers Global");
		run_hotservers_global_reward.addActionListener(al -> Global.getInstance().execute("HOTSERVERS"));
		run_hotservers_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_hotservers_global_reward);
		
		// run L2RankZone global reward
		JMenuItem run_l2rankzone_global_reward = new JMenuItem("Run L2RankZone Global");
		run_l2rankzone_global_reward.addActionListener(al -> Global.getInstance().execute("L2RANKZONE"));
		run_l2rankzone_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_l2rankzone_global_reward);
		
		// run Top4TeamBR global reward
		JMenuItem run_Top4TeamBR_global_reward = new JMenuItem("Run Top4TeamBR Global");
		run_Top4TeamBR_global_reward.addActionListener(al -> Global.getInstance().execute("TOP4TEAMBR"));
		run_Top4TeamBR_global_reward.setToolTipText("<html>Run global reward</html>");
		menuServer.add(run_Top4TeamBR_global_reward);
		
		// server info
		JMenuItem server_info = new JMenuItem("Server info");
		server_info.addActionListener(al -> Utilities.openUrl(Url.ITOPZ_SERVER_URL.toString()));
		server_info.setToolTipText("<html>Visit server info at iTopZ</html>");
		menuServer.add(server_info);
		
		// donate settings
		JMenuItem donate_settings = new JMenuItem("Donate Settings");
		donate_settings.addActionListener(al -> Utilities.openUrl(Url.ITOPZ_URL.toString() + "donate_settings/" + Configurations.ITOPZ_SERVER_ID));
		donate_settings.setToolTipText("<html>Visit donate settings at iTopZ</html>");
		menuDonate.add(donate_settings);
		
		// donate payments
		JMenuItem donate_payments = new JMenuItem("Donate Payments");
		donate_payments.addActionListener(al -> Utilities.openUrl(Url.ITOPZ_URL.toString() + "donate_payments/" + Configurations.ITOPZ_SERVER_ID));
		donate_payments.setToolTipText("<html>Visit donate payments at iTopZ</html>");
		menuDonate.add(donate_payments);
		
		// donate announce
		JMenuItem donate_inform = new JMenuItem("Donate Announce link");
		donate_inform.addActionListener(al -> {
			Utilities.announce("iTopZ", "Donate for our server at " + Url.ITOPZ_URL.toString() + "donate/" + Configurations.ITOPZ_SERVER_ID);
			ConsoleWrite("Announce send to server");
		});
		donate_inform.setToolTipText("<html>Inform all players about donate link</html>");
		menuDonate.add(donate_inform);
		
		// bug report
		JMenuItem report = new JMenuItem("Bug report");
		report.addActionListener(al -> Utilities.openUrl(Url.GITHUB_URL.toString()));
		report.setToolTipText("<html>Visit Github to report a bug</html>");
		menuAbout.add(report);
		
		// about itopz
		JMenuItem about = new JMenuItem("iTopZ");
		about.addActionListener(al -> Utilities.openUrl(Url.ITOPZ_SERVER_URL.toString()));
		about.setToolTipText("<html>Visit iTopZ</html>");
		menuAbout.add(about);
		
		// about itopz discord
		JMenuItem discord = new JMenuItem("Discord Server");
		discord.addActionListener(al -> Utilities.openUrl(Url.DISCORD.toString()));
		discord.setToolTipText("<html>Visit Discord</html>");
		menuAbout.add(discord);
		
		// about denart designs
		JMenuItem denart_designs = new JMenuItem("GFX Designer");
		denart_designs.addActionListener(al -> Utilities.openUrl(Url.DENART_DESIGNS.toString()));
		denart_designs.setToolTipText("<html>Visit Denart Designs</html>");
		menuAbout.add(denart_designs);
		
		// shop donate denart designs
		JMenuItem denart_designs_donate = new JMenuItem("Donate Panel v4");
		denart_designs_donate.addActionListener(al -> Utilities.openUrl(Url.DENART_DESIGNS_DONATE_PANEL.toString()));
		denart_designs_donate.setToolTipText("<html>Visit Denart Designs</html>");
		menuAbout.add(denart_designs_donate);
		
		// shop vote denart designs
		JMenuItem denart_designs_vote = new JMenuItem("Vote Panel");
		denart_designs_vote.addActionListener(al -> Utilities.openUrl(Url.DENART_DESIGNS_VOTE_PANEL.toString()));
		denart_designs_vote.setToolTipText("<html>Visit Denart Designs</html>");
		menuAbout.add(denart_designs_vote);
		
		// shop referral denart designs
		JMenuItem denart_designs_referral = new JMenuItem("Referral Panel");
		denart_designs_referral.addActionListener(al -> Utilities.openUrl(Url.DENART_DESIGNS_REFERRAL_PANEL.toString()));
		denart_designs_referral.setToolTipText("<html>Visit Denart Designs</html>");
		menuAbout.add(denart_designs_referral);
	}
	
	/**
	 * add topsite labels
	 */
	public void box()
	{
		// ITOPZ
		box.add(ITOPZ);
		// HOPZONE
		box.add(HOPZONE);
		// L2TOPGAMESERVER
		box.add(L2TOPGAMESERVER);
		// L2NETWORK
		box.add(L2NETWORK);
		// TOPL2JBRASIL
		box.add(TOPL2JBRASIL);
		// L2VOTES
		box.add(L2VOTES);
		// HOTSERVERS
		box.add(HOTSERVERS);
		// L2RANKZONE
		box.add(L2RANKZONE);
		// TOP4TEAMBR
		box.add(TOP4TEAMBR);
	}
	
	/**
	 * write console
	 * @param message String
	 */
	public void ConsoleWrite(String message)
	{
		if (!Configurations.VDS_CONSOLE_ENABLE)
			return;
		// console append message
		console.append("\n" + message);
		// follow scroll
		console.setCaretPosition(console.getDocument().getLength());
	}
	
	public static Gui getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final Gui _instance = new Gui();
	}
}