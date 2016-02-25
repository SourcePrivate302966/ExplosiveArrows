package com.nomic.ExplosiveArrows;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Players implements Listener {
	
	private Main plugin;
	public Players(Main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onArrowDamage(EntityDamageByEntityEvent e) {
		if (!(plugin.getConfig().getDefaultSection().get("playerExplosions").equals(true)))
			return;
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		
		Player p = (Player) e.getEntity();
		Location l = p.getLocation();
		World w = l.getWorld();
		
		if (!(p.hasPermission("arrow.explosive") || p.isOp()))
			return;
		if (!(e.getCause() == DamageCause.PROJECTILE))
			return;
		if (Main.getWorldGuard() != null) {
			if (!(Main.getWorldGuard().canBuild(p, l)))
				return;
		}
		w.createExplosion(l, plugin.getConfig().getInt("explosionPower"));
	}
}
