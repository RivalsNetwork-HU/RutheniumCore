package me.silkyfalcon.rutheniumcore.modules.enderpearl;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EnderPearlRemover implements Listener {
    private RutheniumCore plugin;

    public EnderPearlRemover(RutheniumCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEnderSpawn(EntitySpawnEvent e) {

        if (!e.getEntity().getType().equals(EntityType.ENDER_PEARL)) return;

        Entity entityPearl = e.getEntity();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (entityPearl != null) {

                    if (entityPearl instanceof Damageable) {
                        Damageable damageable = (Damageable) entityPearl;
                        damageable.damage(1000);
                    }
                }
            }
        }.runTaskLater(plugin, ConfigUtils.enderPearlRemoverCooldown() * 20L);
    }
}