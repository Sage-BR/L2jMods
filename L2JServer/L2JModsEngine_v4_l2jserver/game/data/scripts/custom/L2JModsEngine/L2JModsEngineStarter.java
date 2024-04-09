/*
 * This file is part of L2JDevs Mods.
 * 
 * L2JDevs Mods is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2JDevs Mods is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package custom.L2JModsEngine;

import org.l2jdevs.mods.ModsManager;

/**
 * This is the starter to load the Mods Manager
 * @author Zephyr
 */
public class L2JModsEngineStarter
{
	private L2JModsEngineStarter()
	{
		ModsManager.initialize();
	}
	
	public static void main(String[] args)
	{
		new L2JModsEngineStarter();
	}
}