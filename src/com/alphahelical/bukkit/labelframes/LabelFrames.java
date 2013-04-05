/**
 * 
 */
package com.alphahelical.bukkit.labelframes;

import java.util.logging.Level;

import com.alphahelical.bukkit.SGPlugin;

/**
 * TODO: Read books in frames
 * TODO: respect permissions on attachment block, which might need Vault
 * TODO: loglevel
 * @author kbeckman
 *
 */
public class LabelFrames extends SGPlugin {

	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.SGPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		// todo: this should be automagic in SGPlugin#onEnable
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		
		this.getLogger().setLevel(Level.OFF); // TODO: SGConfig class that does this with magic
		
		this.getServer().getPluginManager().registerEvents(new FrameListener(this), this);
	}

	/* (non-Javadoc)
	 * @see com.alphahelical.bukkit.SGPlugin#onDisable()
	 */
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

	}

}
