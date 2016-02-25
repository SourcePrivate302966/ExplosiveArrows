package com.nomic.ExplosiveArrows;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		registerEvents();
	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new Blocks(this), this);
		pm.registerEvents(new Players(this), this);
	}
	
	public static WorldGuardPlugin getWorldGuard() {
		Plugin wg = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if ((wg != null) && ((wg instanceof WorldGuardPlugin)) && (wg.isEnabled())) {
			return (WorldGuardPlugin) wg;
		}
		return null;
	}
}
