package me.silkyfalcon.rutheniumcore.modules.voidspawn;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidSpawn implements Listener {
    private static final RutheniumCore plugin = RutheniumCore.getInstance();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!plugin.getYamlStorage().getData().getStringList("voidspawn.in-worlds").contains(e.getPlayer().getWorld().getName())) return;
        if (e.getPlayer().getLocation().getY() <= 0) {
            float yaw = (float) plugin.getYamlStorage().getData().getDouble("voidspawn.yaw");
            float pitch = (float) plugin.getYamlStorage().getData().getDouble("voidspawn.pitch");
            Location loc = new Location(Bukkit.getWorld(plugin.getYamlStorage().getData().getString("voidspawn.world")), plugin.getYamlStorage().getData().getDouble("voidspawn.x"), plugin.getYamlStorage().getData().getDouble("voidspawn.y"), plugin.getYamlStorage().getData().getDouble("voidspawn.z"), yaw, pitch);
            e.getPlayer().teleportAsync(loc);
        }
    }
}
