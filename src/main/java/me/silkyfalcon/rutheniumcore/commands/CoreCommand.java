package me.silkyfalcon.rutheniumcore.commands;

import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length != 1) {
            sender.sendMessage(ConfigUtils.coreInvalidArgs());
            return true;
        }

        if (!sender.hasPermission("rutheniumcore.reload") && sender instanceof Player) {
            sender.sendMessage(ConfigUtils.coreNoPermission());
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            ConfigUtils.reload();
            sender.sendMessage(ConfigUtils.reloadMessage());
            return true;
        }

        return true;
    }
}
