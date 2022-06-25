package me.silkyfalcon.rutheniumcore.modules.voidspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidSpawn implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!e.getPlayer().getWorld().getName().equals("lobby")) return;
        if (e.getPlayer().getLocation().getY() <= 0) {
            Location loc = new Location(Bukkit.getWorld("lobby"), 53, 55, 71);
            e.getPlayer().teleportAsync(loc);
        }
    }
}
