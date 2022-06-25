package me.silkyfalcon.rutheniumcore.commands;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.modules.scheduler.CommandScheduler;
import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoreCommand implements CommandExecutor {
    private final RutheniumCore plugin;

    public CoreCommand(RutheniumCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ConfigUtils.invalidArgs());
            return true;
        }

        if (args.length > 1) {
            sender.sendMessage(ConfigUtils.invalidArgs());
            return true;
        }

        if (!sender.hasPermission("rutheniumcore.reload") && sender instanceof Player) {
            sender.sendMessage(ConfigUtils.noPermission());
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.getYamlStorage().load();
            plugin.reloadConfig();
            CommandScheduler.timeCheck();
            sender.sendMessage(ConfigUtils.reloadMessage());
            return true;
        }

        return true;
    }
}
