package me.silkyfalcon.rutheniumcore.modules.enderpearl;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EnderPearlRemover implements Listener {
    private static final RutheniumCore plugin = RutheniumCore.getInstance();

    @EventHandler
    public void onEnderSpawn(EntitySpawnEvent e) {

        if (!e.getEntity().getType().equals(EntityType.ENDER_PEARL)) {
            return;
        }

        Entity entityPearl = e.getEntity();

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (entityPearl == null) return;
            entityPearl.remove();
        }, ConfigUtils.enderPearlRemoverCooldown() * 20L);
    }
}