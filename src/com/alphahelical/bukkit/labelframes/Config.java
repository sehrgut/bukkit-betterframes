/**
 * 
 */
package com.alphahelical.bukkit.labelframes;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import com.alphahelical.bukkit.DropUtil.DropModes;
import com.alphahelical.util.EnumUtil;

/**
 * @author kbeckman
 *
 */
public class Config {
	private Config () {}
	
	private static Plugin plugin;
	protected static Plugin getPlugin() {
		if (plugin == null)
			plugin = Bukkit.getServer().getPluginManager().getPlugin("LabelFrames");
		return plugin;
	}

	public static boolean getTryInventoryPlace() {
		return getPlugin().getConfig().getBoolean("item-removal.try-inventory-place");
	}

	public static boolean getDropOnFailure() {
		return getPlugin().getConfig().getBoolean("item-removal.drop-on-failure");
	}

	public static DropModes getDropMode() {
		String mode = getPlugin().getConfig().getString("item-removal.drop-mode");
		return EnumUtil.find(DropModes.class, mode, DropModes.SCATTER);
	}
	
	public static Level getLogLevel() {
		return Level.parse(getPlugin().getConfig().getString("loglevel"));
	}
}
