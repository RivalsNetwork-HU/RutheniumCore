package me.silkyfalcon.rutheniumcore.modules.eotw;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class EotwSwitch implements Listener {
    private static final RutheniumCore plugin = RutheniumCore.getInstance();

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        if (!plugin.getYamlStorage().getData().getBoolean("eotw.enabled")) return;

        e.getEntity().getPlayer().getWorld().strikeLightningEffect(e.getEntity().getPlayer().getLocation());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plugin.getYamlStorage().getData().getString("eotw.command").replace("%player%", e.getEntity().getPlayer().getName()));

        long players = Bukkit.getOnlinePlayers().stream().filter(p -> !p.getGameMode().equals(GameMode.SPECTATOR)).count() - 1;
        if (players > plugin.getYamlStorage().getData().getInt("eotw.players")) {
            e.getEntity().getPlayer().kickPlayer(YamlColor.formatter(plugin.getYamlStorage().getData().getString("eotw.kick-message")));
            Bukkit.broadcastMessage(YamlColor.formatter(plugin.getYamlStorage().getData().getString("eotw.message").replace("%player%", e.getEntity().getPlayer().getName()).replace("%remaining%", String.valueOf(players))));
            return;
        }

        e.getEntity().getPlayer().getInventory().clear();
        e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);
        Bukkit.broadcastMessage(YamlColor.formatter(plugin.getYamlStorage().getData().getString("eotw.message").replace("%player%", e.getEntity().getPlayer().getName()).replace("%remaining%", String.valueOf(players))));
    }
}
