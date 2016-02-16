package com.nomic.ExplosiveArrows;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class Event implements Listener{
	
	@EventHandler
	public void onArrowHit(ProjectileHitEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player))
			return;
		if (!(e.getEntity().getType() == EntityType.ARROW))
			return;
		Player p = (Player) e.getEntity().getShooter();
		if (!(p.hasPermission("arrow.explosive") || p.isOp()))
			return;
		Location l = e.getEntity().getLocation();
		World w = l.getWorld();
		w.createExplosion(l, 4); //Make configurable power soon.
	}
}
