package me.silkyfalcon.rutheniumcore.utils;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import me.silkyfalcon.rutheniumcore.modules.enderpearl.EnderPearlRemover;
import org.bukkit.Bukkit;

public class MainHandler {

    public void onStartup(RutheniumCore plugin) {
    Bukkit.getConsoleSender().sendMessage(YamlColor.formatter("&c&lRUTHENIUM &e» &fStarted!"));

    Bukkit.getPluginManager().registerEvents(new EnderPearlRemover(plugin), plugin);
    
    }


    public void onShutdown(RutheniumCore plugin) {
        Bukkit.getConsoleSender().sendMessage(YamlColor.formatter("&c&lRUTHENIUM &e» &fStopped!"));
    }


}
