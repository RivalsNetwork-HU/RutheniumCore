package me.silkyfalcon.rutheniumcore.modules.eotw;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import me.silkyfalcon.rutheniumcore.modules.scheduler.CommandScheduler;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class TimerMethod {
    private static final RutheniumCore plugin = RutheniumCore.getInstance();
    private static int timer = 0;
    public static BossBar currentBar;

    private String getString(String path) {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString(path));
    }

    public void startTimer(int time) {
        timer = time + 1;
        Bukkit.getScheduler().cancelTasks(plugin);
        CommandScheduler.timeCheck();
        currentBar = null;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            timer--;
            String name = getString("eotw.timer.bossbar.format").replace("%time%", getString("eotw.timer.bossbar.time-format").replace("%s%", String.valueOf(timer)));
            BossBar bar = BossBar.bossBar(Component.text(name), (float) timer / time, BossBar.Color.RED, BossBar.Overlay.NOTCHED_20);
            if (timer > 0) {
                if (plugin.getYamlStorage().getData().getBoolean("eotw.timer.title.enabled")) {
                    Bukkit.getOnlinePlayers().forEach(p -> p.sendTitle(getString("eotw.timer.title.title-format").replace("%time%", String.valueOf(timer)), getString("eotw.timer.title.subtitle-format").replace("%time%", String.valueOf(timer))));
                }

                if (!plugin.getYamlStorage().getData().getBoolean("eotw.timer.bossbar.enabled")) return;
                if (currentBar != null) {
                    Bukkit.getOnlinePlayers().forEach(p -> p.hideBossBar(currentBar));
                }

                Bukkit.getOnlinePlayers().forEach(p -> p.showBossBar(bar));
                currentBar = bar;
            }

            if (timer != 0) return;
            Bukkit.getOnlinePlayers().forEach(p -> {
                if (plugin.getYamlStorage().getData().getBoolean("eotw.timer.finish-title-enabled")) {
                    p.sendTitle(getString("eotw.timer.title.finish-title-format"), getString("eotw.timer.title.finish-subtitle-format"));
                }
                if (currentBar != null) {
                    p.hideBossBar(currentBar);
                }
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
                    Bukkit.getScheduler().cancelTasks(plugin);
                    CommandScheduler.timeCheck();
                    currentBar = null;

                    for (String commands : plugin.getYamlStorage().getData().getStringList("eotw.timer.finish.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands);
                    }
                }, plugin.getYamlStorage().getData().getLong("eotw.timer.finish.delay"));
            });

        }, 20, 20);
    }
}
