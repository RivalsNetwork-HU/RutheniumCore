package me.silkyfalcon.rutheniumcore.modules.eotw;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class EotwChatListener implements Listener {
    private static final RutheniumCore plugin = RutheniumCore.getInstance();

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChatEvent(PlayerChatEvent e) {
        if (!plugin.getYamlStorage().getData().getBoolean("eotw.chat.enabled")) return;
        if (e.getMessage().contains("eotw") || e.getMessage().contains("Eotw") || e.getMessage().contains("EOTW")) {
            e.setCancelled(true);
            Bukkit.broadcastMessage(YamlColor.formatter(plugin.getYamlStorage().getData().getString("eotw.chat.message")));
        }
    }
}
