/**
 * 
 */
package com.alphahelical.bukkit.labelframes;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * @author kbeckman
 *
 */
public class FrameListener implements Listener {

	//TODO: Custom events for frame clicks

	private LabelFrames plugin;
	
	public FrameListener (LabelFrames plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if(! e.isCancelled() && e.getRightClicked() instanceof ItemFrame) {
			this.plugin.getLogger().info("Handling PlayerInteractEntityEvent");
			
			Player p = e.getPlayer();
			ItemFrame f = (ItemFrame) e.getRightClicked();
			
			if(p.isSneaking()) {
				this.plugin.getLogger().info("Player sneak-right-clicked frame");
				this.doSneakRightClick(p, f, e);
			} else {
				this.plugin.getLogger().info("Player right-clicked frame");
				this.doRightClick(p, f, e);
			}
		}
	}
// TODO: cofig options to protect labels from damage based on protection of their host, or just globally
	@EventHandler
	public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
		if(! e.isCancelled() &&
				e.getRemover() instanceof Player && 
				e.getEntity() instanceof ItemFrame) {

			this.plugin.getLogger().info("Handling HangingBreakByEntityEvent");

			Player p = (Player) e.getRemover();
			ItemFrame f = (ItemFrame) e.getEntity();
			
			if(Util.isLabel(f)) {
				if(p.isSneaking()) {
					this.plugin.getLogger().info("Player sneak-left-clicked frame");
					this.doSneakLeftClick(p, f, e);
				} else {
					this.plugin.getLogger().info("Player left-clicked frame");
					this.doLeftClick(p, f, e);
				}
			}
		}
	}
	
	private void doLeftClick(Player p, ItemFrame f, Cancellable e) {
		IItemFrameInteraction ix = new RemoveItemInteraction(Config.getTryInventoryPlace(), Config.getDropOnFailure(), Config.getDropMode());
		ix.doBehaviour(p, f, e);
	}

	private void doSneakLeftClick(Player p, ItemFrame f, Cancellable e) {
		IItemFrameInteraction ix = new IgnoreInteraction();
		ix.doBehaviour(p, f, e);
	}
	
	private void doRightClick(Player p, ItemFrame f, Cancellable e) {
		IItemFrameInteraction ix = new AttachedBlockInteraction();
		ix.doBehaviour(p, f, e);
	}

	private void doSneakRightClick(Player p, ItemFrame f, Cancellable e) {
		IItemFrameInteraction ix = new IgnoreInteraction();
		ix.doBehaviour(p, f, e);		
	}

	
	
}
