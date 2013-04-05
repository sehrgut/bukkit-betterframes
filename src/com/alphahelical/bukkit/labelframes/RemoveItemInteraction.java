package com.alphahelical.bukkit.labelframes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.alphahelical.bukkit.DropUtil;
import com.alphahelical.bukkit.DropUtil.DropModes;

public class RemoveItemInteraction implements IItemFrameInteraction {

	private boolean placeInInventory;
	private boolean dropOnFailure;
	private DropModes dropMode;
	
	public RemoveItemInteraction(boolean placeInInventory, boolean dropOnFailure, DropModes dropMode) {
		this.placeInInventory = placeInInventory;
		this.dropMode = dropMode;
		this.dropOnFailure = dropOnFailure;
	}
	
	@Override
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e) {
		e.setCancelled(true);
		
		ItemStack item = f.getItem();
		Inventory inv = p.getInventory();
		Location loc = f.getLocation();
		
		if(! (item.getType() == Material.AIR)) {
			if(this.placeInInventory) {
				if(this.dropOnFailure) {
					DropUtil.addToInventoryOrDrop(inv, loc, this.dropMode, item);
				} else {
					if(inv.addItem(item).values().size() == 0) return false;
				}
			} else {
				DropUtil.drop(loc, this.dropMode, item);
			}
			
			f.setItem(new ItemStack(Material.AIR, 1));

			return true;
		}
		
		return false;
	}
	
}
