package me.silkyfalcon.rutheniumcore.utils;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import me.silkyfalcon.rutheniumcore.modules.protecteditems.ItemProtect;
import me.silkyfalcon.rutheniumcore.modules.scheduler.CommandScheduler;
import me.silkyfalcon.rutheniumcore.modules.sumo.SumoEvents;
import me.silkyfalcon.rutheniumcore.modules.switcherball.SwitcherBallListener;
import me.silkyfalcon.rutheniumcore.modules.voidspawn.VoidSpawn;
import org.bukkit.Bukkit;

public class MainHandler {

    public void onStartup(RutheniumCore plugin) {
        Bukkit.getConsoleSender().sendMessage(YamlColor.formatter("&c&lRUTHENIUM &e» &fStarted!"));

        // Event registering
//        Bukkit.getPluginManager().registerEvents(new EnderPearlRemover(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new SumoEvents(), plugin);
        Bukkit.getPluginManager().registerEvents(new SwitcherBallListener(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new ItemProtect(), plugin);
        Bukkit.getPluginManager().registerEvents(new VoidSpawn(), plugin);


        // Time check
        CommandScheduler.timeCheck();
    }

    public void onShutdown(RutheniumCore plugin) {
        Bukkit.getConsoleSender().sendMessage(YamlColor.formatter("&c&lRUTHENIUM &e» &fStopped!"));
    }

}
