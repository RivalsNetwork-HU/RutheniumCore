package me.silkyfalcon.rutheniumcore.commands;

import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SwitcherBallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ConfigUtils.invalidArgs());
            return true;
        }

        if (sender instanceof Player && sender.hasPermission("rutheniumcore.admin") && args.length == 1 && args[0].equals("give")) {
            ((Player) sender).getInventory().addItem(ConfigUtils.switcherBallItem());
            return true;
        }

        if (sender instanceof ConsoleCommandSender) {
            if (args[0].equalsIgnoreCase("give") && args.length == 2) {
                Player t = Bukkit.getPlayerExact(args[1]);
                t.getInventory().addItem(ConfigUtils.switcherBallItem());
            }
        }

        if (sender instanceof Player) {
            if (!sender.hasPermission("rutheniumcore.admin")) {
                sender.sendMessage(ConfigUtils.noPermission());
                return true;
            }

            if (args[0].equalsIgnoreCase("give") && args.length == 2) {
                Player t = Bukkit.getPlayerExact(args[1]);
                t.getInventory().addItem(ConfigUtils.switcherBallItem());
            }
        }


        return true;
    }
}
