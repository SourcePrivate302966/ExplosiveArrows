package com.nomic.ExplosiveArrows;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		registerEvents();
	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new Blocks(this), this);
		pm.registerEvents(new Players(this), this);
	}
}
