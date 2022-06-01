package me.silkyfalcon.rutheniumcore.utils;

import me.silkyfalcon.rutheniumcore.RutheniumCore;

public class ConfigUtils {
    private static RutheniumCore plugin;

    public static int enderPearlRemoverCooldown() {
        return plugin.getYamlStorage().getData().getInt("enderpearl-remover.interval");
    }


}
