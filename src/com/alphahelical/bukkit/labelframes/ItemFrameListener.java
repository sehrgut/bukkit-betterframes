/**
 * 
 */
package com.alphahelical.bukkit.labelframes;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.alphahelical.bukkit.ItemInfo;
import com.alphahelical.bukkit.enity.itemframe.ItemFrameInteractEvent;
import com.alphahelical.bukkit.enity.itemframe.ItemFrameUtil;
import com.alphahelical.bukkit.enity.itemframe.behavior.*;

/**
 * @author kbeckman
 *
 */
public class ItemFrameListener implements Listener {

	// TODO: config options to protect labels from damage based on protection of their host, or just globally

	private LabelFrames plugin;
	
	public ItemFrameListener (LabelFrames plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onItemFrameInteract(ItemFrameInteractEvent e) {
		if (e.isCancelled()) return;

		IItemFrameBehavior ib = new Default();
		ItemFrame f = e.getTarget();
		Player p = e.getPlayer();
		ItemInfo ii = new ItemInfo(f.getItem());
		
		switch(e.getAction()) {
			case USE:
				if(ItemFrameUtil.isLabel(f) && Config.getOpenInventory()) {
					this.plugin.getLogger().info("USE: OpenAttachedInventory");
					ib =  new OpenAttachedInventory();
				} else if (Config.getChatBooks() && ii.hasBookMeta() &&
						(Config.getAllowWritableBooks() || ! ii.isWriteableBook())) {
					ib = new ChatBook(true, "The book appears to be blank.");
				} else if(ItemFrameUtil.isEmpty(f)) {
					this.plugin.getLogger().info("USE: Default");
					ib = new Default();
				} else {
					this.plugin.getLogger().info("USE: Cancel");
					ib = new Cancel();
				}
				break;
			case SNEAK_USE:
				if(ItemFrameUtil.isEmpty(f)) {
					this.plugin.getLogger().info("SNEAK_USE: Default");
					ib = new Default();
				} else {
					this.plugin.getLogger().info("SNEAK_USE: RemoveItem");
					ib = new RemoveItem(Config.getTryInventoryPlace(), Config.getDropOnFailure(), Config.getDropMode());
				}
				break;
			case HIT:
				if (! ItemFrameUtil.isEmpty(f)  &&
					(Config.getRotationTool() == null || p.getItemInHand().getType().equals(Config.getRotationTool()))
					) {
					this.plugin.getLogger().info("HIT: RotateItem");
					ib = new RotateItem(Config.getRotation());					
				} else {
					this.plugin.getLogger().info("HIT: Cancel");
					ib = new Cancel();
				}
				break;
			case SNEAK_HIT:
				this.plugin.getLogger().info("SNEAK_HIT: Default");
				ib = new Default();
				break;
		}
		
		this.plugin.getLogger().info(String.format("Player '%s' executed ItemFrameAction#%s at %s",
				p, e.getAction(), f.getLocation()));
		this.plugin.getLogger().fine(String.format("Using a %s behaviour.", ib.getClass().getName()));
		ib.doBehaviour(p, f, e);
	}

}
