package me.silkyfalcon.rutheniumcore.modules.sumo;

import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class SumoEvents implements Listener {

    // Effect give checker
    @EventHandler
    public void onEffect(EntityPotionEffectEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        for (String blocked_world : ConfigUtils.sumoBlockedWorlds()) {
            if (e.getAction() == EntityPotionEffectEvent.Action.ADDED && blocked_world.contains(e.getEntity().getWorld().getName())) {
                e.setCancelled(true);
            }
        }
    }

    // Teleport effect remover
    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        for (String blocked_world : ConfigUtils.sumoBlockedWorlds()) {
            if (blocked_world.contains(e.getPlayer().getWorld().getName())) {
                for (String effects : ConfigUtils.sumoBlockedEffects()) {
                    e.getPlayer().removePotionEffect(Objects.requireNonNull(PotionEffectType.getByName(effects)));
                }
            }
        }
    }

    // Sumo damage setting
    @EventHandler
    public void onSumoHit(EntityDamageByEntityEvent e) {
        for (String blocked_world : ConfigUtils.sumoBlockedWorlds()) {
            if (blocked_world.contains(e.getDamager().getWorld().getName())) {
                e.setDamage(ConfigUtils.sumoDamage());
            }
        }
    }
}
