package me.silkyfalcon.rutheniumcore.modules.scheduler;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommandScheduler {
    static final RutheniumCore plugin = RutheniumCore.getPlugin(RutheniumCore.class);

    public static void commandSchedule() {
        TimeZone timeZone = TimeZone.getTimeZone("Hungary/Budapest");
        Date now = Calendar.getInstance(timeZone).getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = simpleDateFormat.format(now);

        for (String schedulers : Objects.requireNonNull(plugin.getYamlStorage().getData().getConfigurationSection("schedulers")).getKeys(false)) {
            List<String> time3 = plugin.getYamlStorage().getData().getStringList("schedulers." + schedulers + ".time");
            List<String> command = plugin.getYamlStorage().getData().getStringList("schedulers." + schedulers + ".commands");

            for (String commands : command) {
                if (time3.contains(time)) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands);
                }
            }
        }
    }

    public static void timeCheck() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            TimeZone timeZone = TimeZone.getTimeZone("Hungary/Budapest");
            Date now = Calendar.getInstance(timeZone).getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            String time = simpleDateFormat.format(now);

            for (String schedulers : Objects.requireNonNull(plugin.getYamlStorage().getData().getConfigurationSection("schedulers")).getKeys(false)) {
                List<String> time3 = plugin.getYamlStorage().getData().getStringList("schedulers." + schedulers + ".time");

                if (time3.contains(time)) {
                    commandSchedule();
                }
            }
        }, 20, 20);
    }
}
