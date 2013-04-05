/**
 * 
 */
package com.alphahelical.bukkit.labelframes;

import org.bukkit.block.Block;
import org.bukkit.entity.Hanging;
import org.bukkit.inventory.InventoryHolder;

/**
 * @author kbeckman
 *
 */
public class Util {
	private Util() {}
	
	public static Block attachedBlock(Hanging f) {
		return f.getLocation().getBlock().getRelative(f.getAttachedFace());
	}
	
	public static boolean isLabel(Hanging f) {
		return (attachedBlock(f).getState() instanceof InventoryHolder); 
	}
}
