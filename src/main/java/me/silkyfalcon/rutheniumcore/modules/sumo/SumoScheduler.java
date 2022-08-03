package me.silkyfalcon.rutheniumcore.modules.sumo;


import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class SumoScheduler {
//    private static final RutheniumCore plugin = RutheniumCore.getPlugin(RutheniumCore.class);
//
//    public static void commandSchedule() {
//        TimeZone timeZone = TimeZone.getTimeZone("Hungary/Budapest");
//        Date now = Calendar.getInstance(timeZone).getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//        String time = simpleDateFormat.format(now);
//
//        if (time.equals(ConfigUtils.time())) {
//            for (String commands : ConfigUtils.commands()) {
//                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands);
//            }
//        }
//    }
//
//    public static void timeCheck() {
//        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
//            TimeZone timeZone = TimeZone.getTimeZone("Hungary/Budapest");
//            Date now = Calendar.getInstance(timeZone).getTime();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//            String time = simpleDateFormat.format(now);
//
//            if (time.equals(ConfigUtils.time())) {
//                commandSchedule();
//            }
//        }, 20, 20);
//    }
}
