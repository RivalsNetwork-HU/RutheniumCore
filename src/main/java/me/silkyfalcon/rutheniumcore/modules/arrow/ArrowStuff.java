package me.silkyfalcon.rutheniumcore.modules.arrow;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArrowStuff implements CommandExecutor {
    private static final RutheniumCore plugin = RutheniumCore.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player && !sender.hasPermission("rutheniumcore.give.arrow")) {
            sender.sendMessage(ConfigUtils.arrowNoPermission());
            return true;
        }

        if (args.length <= 2) {
            sender.sendMessage(ConfigUtils.arrowInvalidArgs());
            return true;
        }

        if (plugin.getYamlStorage().getData().getConfigurationSection("arrows." + args[0]) == null) {
            sender.sendMessage(ConfigUtils.noConfigurationSection());
            return true;
        }

        Player p = Bukkit.getPlayer(args[1]);
        if (p == null) {
            sender.sendMessage(YamlColor.formatter(plugin.getYamlStorage().getData().getString("commands.arrow.fail")));
            return true;
        }

        ItemStack arrow = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta meta = (PotionMeta) arrow.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.WATER));

        meta.setColor(Color.fromRGB(plugin.getYamlStorage().getData().getInt("arrows." + args[0] + ".color.red"), plugin.getYamlStorage().getData().getInt("arrows." + args[0] + ".color.green"), plugin.getYamlStorage().getData().getInt("arrows." + args[0] + ".color.blue")));
        for (String effects : plugin.getYamlStorage().getData().getStringList("arrows." + args[0] + ".effects")) {
            meta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(effects), plugin.getYamlStorage().getData().getInt("arrows." + args[0] + ".durations." + effects) * 20, plugin.getYamlStorage().getData().getInt("arrows." + args[0] + ".powers." + effects)), true);
        }

        meta.setDisplayName(YamlColor.formatter(plugin.getYamlStorage().getData().getString("arrows." + args[0] + ".name")));
        List<String> lore = new ArrayList<>();
        for (String itemlore : plugin.getYamlStorage().getData().getStringList("arrows." + args[0] + ".lore")) {
            lore.add(YamlColor.formatter(itemlore));
        }

        if (plugin.getYamlStorage().getData().getBoolean("arrows." + args[0] + ".enchanted")) {
            meta.addEnchant(Enchantment.DURABILITY, 3, true);
        }

        if (plugin.getYamlStorage().getData().getBoolean("arrows." + args[0] + ".hide_potions")) {
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.setLore(lore);
        arrow.setItemMeta(meta);
        arrow.setAmount(Integer.parseInt(args[2]));

        p.getInventory().addItem(arrow);
        sender.sendMessage(YamlColor.formatter(plugin.getYamlStorage().getData().getString("commands.arrow.success").replace("%player%", p.getName())));
        return true;
    }
}
