/**
 * 
 */
package com.alphahelical.bukkit.labelframes;

import org.bukkit.Bukkit;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * @author kbeckman
 *
 */
public class AttachedBlockInteraction implements IItemFrameInteraction {

	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.labelframes.IItemFrameInteraction#doBehaviour(org.bukkit.entity.Player, org.bukkit.entity.ItemFrame)
	 */
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		if(Util.isLabel(f)) {
			e.setCancelled(true);

			PlayerInteractEvent pe = new PlayerInteractEvent(p,
					Action.RIGHT_CLICK_BLOCK,
					p.getItemInHand(),
					Util.attachedBlock(f),
					f.getAttachedFace().getOppositeFace());
			Bukkit.getServer().getPluginManager().callEvent(pe);
			
			if(! pe.isCancelled()) {
				// get the wrapper InventoryHolder in case of double-chests
				Inventory i = ((InventoryHolder)pe.getClickedBlock().getState()).getInventory().getHolder().getInventory();
				pe.getPlayer().openInventory(i);
			}
			
			return true;
		}
		return false;
	}

}
