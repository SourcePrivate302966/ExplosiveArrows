package com.nomic.ExplosiveArrows.Events;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.nomic.ExplosiveArrows.Main;

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
		if (p.isSneaking() && plugin.getConfig().get("disableOnSneak").equals(true))
			return;
		if (Main.getWorldGuard() != null) {
			if (!(Main.getWorldGuard().canBuild(p, l)))
				return;
		}
		if (!(e.getEntity().getShooter() instanceof Player))
			return;
		if (!(e.getEntity().getType() == EntityType.ARROW))
			return;
		if (!(p.hasPermission("arrow.explosive") || p.isOp()))
			return;
		if (plugin.getConfig().get("drill").equals(false)) {
			w.createExplosion(l, plugin.getConfig().getInt("explosionPower"));
			e.getEntity().remove();
		} else {
			int chance = (1 + new Random().nextInt(plugin.getConfig().getInt("drillChance") - 1));
			if (chance == 1) {
				w.createExplosion(l, plugin.getConfig().getInt("explosionPower"));
				Location l2 = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());
				w.createExplosion(l2, plugin.getConfig().getInt("explosionPower"));
				Location l3 = new Location(l.getWorld(), l.getX(), l.getY() - 2, l.getZ());
				w.createExplosion(l3, plugin.getConfig().getInt("explosionPower"));
				Location l4 = new Location(l.getWorld(), l.getX(), l.getY() - 3, l.getZ());
				w.createExplosion(l4, plugin.getConfig().getInt("explosionPower"));
				Location l5 = new Location(l.getWorld(), l.getX(), l.getY() - 4, l.getZ());
				w.createExplosion(l5, plugin.getConfig().getInt("explosionPower"));
				Location l6 = new Location(l.getWorld(), l.getX(), l.getY() - 5, l.getZ());
				w.createExplosion(l6, plugin.getConfig().getInt("explosionPower"));
				e.getEntity().remove();
			} else {
				w.createExplosion(l, plugin.getConfig().getInt("explosionPower"));
				e.getEntity().remove();
			}
		}
	}
}
