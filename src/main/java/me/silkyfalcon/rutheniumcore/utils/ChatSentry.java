package me.silkyfalcon.rutheniumcore.utils;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;

import java.io.File;

public class ChatSentry {

    public void onChatSentryCheck(RutheniumCore plugin) {
        if (Bukkit.getPluginManager().isPluginEnabled("ChatSentry")) {
            System.out.println("Be van töltve!");
            return;
        }

        try {
            Bukkit.getPluginManager().loadPlugin(new File(plugin.getDataFolder(), "/Dependency.jar"));
        } catch (InvalidPluginException | InvalidDescriptionException e) {
            e.printStackTrace();
        }
    }
}
