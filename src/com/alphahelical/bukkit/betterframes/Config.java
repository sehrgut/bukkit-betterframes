/**
 * 
 */
package com.alphahelical.bukkit.betterframes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Rotation;
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
		String mode = getPlugin().getConfig().getString("item-removal.drop-mode").toUpperCase();
		return EnumUtil.find(DropModes.class, mode, DropModes.SCATTER);
	}
	
	public static Rotation getRotation() {
		String rotation = getPlugin().getConfig().getString("item-rotation.direction").toUpperCase();
		return EnumUtil.find(Rotation.class, rotation, Rotation.COUNTER_CLOCKWISE);
	}
	
	public static boolean getOpenInventory() {
		return getPlugin().getConfig().getBoolean("inventory.open");
	}

	public static Material getRotationTool() {
		String tool = getPlugin().getConfig().getString("item-rotation.tool").toUpperCase();
		Material mat = EnumUtil.find(Material.class, tool, null);
		if (! tool.isEmpty() && ! tool.equalsIgnoreCase("ANY") && mat == null)
			getPlugin().getLogger().warning(String.format("Invalid item-rotation.tool: %s. Using ANY.", tool));
		
		return mat;
	}
	
	public static boolean getChatBooks() {
		return getPlugin().getConfig().getBoolean("books.chat");
	}
	
	public static boolean getAllowWritableBooks() {
		return getPlugin().getConfig().getBoolean("books.allow-writable");
	}

	public static String getEmptyBookMessage() {
		return getPlugin().getConfig().getString("books.empty-message");
	}

}
