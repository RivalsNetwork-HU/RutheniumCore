package me.silkyfalcon.rutheniumcore.modules.eotw;

import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Timer implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player && !sender.hasPermission("eotw.timer")) {
            sender.sendMessage(ConfigUtils.noTimerPermission());
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ConfigUtils.timerInvalidArgs());
            return true;
        }

        new TimerMethod().startTimer(Integer.parseInt(args[0]));
        return true;
    }
}
