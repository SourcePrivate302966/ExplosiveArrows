package com.nomic.ExplosiveArrows;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class Blocks implements Listener {

	private Main plugin;
	public Blocks(Main pl) {
		plugin = pl;
	}

	@EventHandler
	public void onArrowHit(ProjectileHitEvent e) {
		if (!(plugin.getConfig().getDefaultSection().get("blockExplosions").equals(true)))
			return;
		Player p = (Player) e.getEntity().getShooter();
		Location l = e.getEntity().getLocation();
		World w = l.getWorld();
		
		if (!(Main.getWorldGuard().canBuild(p, l)))
			return;
		if (!(e.getEntity().getShooter() instanceof Player))
			return;
		if (!(e.getEntity().getType() == EntityType.ARROW))
			return;
		if (!(p.hasPermission("arrow.explosive") || p.isOp()))
			return;
		w.createExplosion(l, plugin.getConfig().getInt("explosionPower"));
		e.getEntity().remove();
	}
}
