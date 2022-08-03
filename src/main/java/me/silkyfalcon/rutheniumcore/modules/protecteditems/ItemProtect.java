package me.silkyfalcon.rutheniumcore.modules.protecteditems;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ItemProtect implements Listener {

    @EventHandler
    public void onCactusDamage(EntityDamageEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.CONTACT) && e.getEntity().getType() == EntityType.DROPPED_ITEM) {
            e.setDamage(0.0);
            e.setCancelled(true);
        }
    }
}
