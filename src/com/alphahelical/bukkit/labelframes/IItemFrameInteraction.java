/**
 * 
 */
package com.alphahelical.bukkit.labelframes;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * @author kbeckman
 *
 */
public interface IItemFrameInteraction {
	public boolean doBehaviour(Player p, ItemFrame f, Cancellable e);
}
