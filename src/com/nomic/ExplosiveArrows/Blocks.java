package com.nomic.ExplosiveArrows;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class Blocks implements Listener{
	
	private Main plugin;

	public Blocks(Main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onArrowHit(ProjectileHitEvent e) {
		if (!(plugin.getConfig().getDefaultSection().get("blockExplosions").equals(true)))
			return;
		if (!(e.getEntity().getShooter() instanceof Player))
			return;
		if (!(e.getEntity().getType() == EntityType.ARROW))
			return;
		Player p = (Player) e.getEntity().getShooter();
		if (!(p.hasPermission("arrow.explosive") || p.isOp()))
			return;
		Location l = e.getEntity().getLocation();
		World w = l.getWorld();
		w.createExplosion(l, plugin.getConfig().getInt("explosionPower"));
	}
}
