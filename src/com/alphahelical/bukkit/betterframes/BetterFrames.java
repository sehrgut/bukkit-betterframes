/**
 * 
 */
package com.alphahelical.bukkit.betterframes;

import java.util.logging.Level;

import com.alphahelical.bukkit.SGPlugin;

/**
 * TODO: Read books in frames
 * TODO: respect permissions on attachment block, which might need Vault
 * @author kbeckman
 *
 */
public class BetterFrames extends SGPlugin {
	
	@Override
	protected void onEnabling() {
		this.setDefaultLoglevel(Level.SEVERE);		
	}
	
	@Override
	protected void onEnabled() {
		this.registerListeners(new ItemFrameListener(this));
	}

	@Override
	protected void onDisabling() {
		
	}
	
	@Override
	protected void onDisabled() {

	}

}
