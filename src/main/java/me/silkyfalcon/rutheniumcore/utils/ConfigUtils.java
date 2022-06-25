package me.silkyfalcon.rutheniumcore.utils;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ConfigUtils {
//    private static final RutheniumCore plugin = RutheniumCore.getInstance();
    static final RutheniumCore plugin = RutheniumCore.getPlugin(RutheniumCore.class);

    public static int enderPearlRemoverCooldown() {
        return plugin.getYamlStorage().getData().getInt("enderpearl-remover.interval");
    }

    public static List<String> sumoBlockedWorlds() {
        return plugin.getYamlStorage().getData().getStringList("sumo.disabled_worlds");
    }

    public static List<String> sumoBlockedEffects() {
        return plugin.getYamlStorage().getData().getStringList("sumo.removed_effects");
    }

    public static double sumoDamage() {
        return plugin.getYamlStorage().getData().getDouble("sumo.damage");
    }

    public static String noPermission() {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString("commands.no-permission"));
    }

    public static String invalidArgs() {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString("commands.invalid-args"));
    }

    public static String reloadMessage() {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString("commands.reload-message"));
    }

    public static String time() {
        return plugin.getYamlStorage().getData().getString("schedule.time");
    }

    public static List<String> commands() {
        return plugin.getYamlStorage().getData().getStringList("schedule.commands");
    }

    // Switcherball item
    public static String switcherBallName() {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString("switcherball.item.name"));
    }

    public static boolean switcherBallEnchanted() {
        return plugin.getYamlStorage().getData().getBoolean("switcherball.item.enchanted");
    }

    public static List<String> switcherBallLore() {
        return plugin.getYamlStorage().getData().getStringList("switcherball.item.lore");
    }

    public static String coolDownMessage() {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString("switcherball.messages.cooldown"));
    }

    public static String switchMessage(Player p) {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString("switcherball.messages.switch")
                .replace("%player%", p.getName()));
    }

    public static String blockedWorldMessage() {
        return YamlColor.formatter(plugin.getYamlStorage().getData().getString("switcherball.messages.blocked-world-message"));
    }

    public static List<String> blockedWorlds() {
        return plugin.getYamlStorage().getData().getStringList("switcherball.blocked-worlds");
    }

    public static int cd() {
        return (int) (plugin.getYamlStorage().getData().getInt("switcherball.cooldown") * 1000L);
    }

    // Switcher Ball Item
    public static ItemStack switcherBallItem() {
        ItemStack switcherBall = new ItemStack(Material.SNOWBALL);
        ItemMeta switcherMeta = switcherBall.getItemMeta();

        switcherMeta.setDisplayName(ConfigUtils.switcherBallName());
        List<String> itemLore = new ArrayList<>();

        for (String lore : ConfigUtils.switcherBallLore()) {
            itemLore.add(YamlColor.formatter(lore));
        }
        switcherMeta.setLore(itemLore);

        if (switcherBallEnchanted()) {
            switcherMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            switcherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        switcherMeta.setLocalizedName("SWITCHER_BALL");
        switcherBall.setItemMeta(switcherMeta);
        return switcherBall;
    }

}
