package me.silkyfalcon.rutheniumcore.modules.scheduler;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommandScheduler {
    private static final RutheniumCore plugin = RutheniumCore.getInstance();
    private static final ArrayList<String> onlinePlayers = new ArrayList<>();

    public static void commandSchedule() {
        TimeZone timeZone = TimeZone.getTimeZone("Hungary/Budapest");
        Date now = Calendar.getInstance(timeZone).getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = simpleDateFormat.format(now);

        for (String schedulers : plugin.getYamlStorage().getData().getConfigurationSection("schedulers").getKeys(false)) {
            List<String> time2 = plugin.getYamlStorage().getData().getStringList("schedulers." + schedulers + ".time");
            List<String> command = plugin.getYamlStorage().getData().getStringList("schedulers." + schedulers + ".commands");
            boolean enabled = plugin.getYamlStorage().getData().getBoolean("schedulers." + schedulers + ".enabled");

            if (time2.contains(time) && enabled) {
                for (String commands : command) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), commands);
                }
            }
        }
    }

    public static void timeCheck() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, CommandScheduler::commandSchedule, 20, 20);
    }
}
