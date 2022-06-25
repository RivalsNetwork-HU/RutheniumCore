package me.silkyfalcon.rutheniumcore.modules.switcherball;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import me.silkyfalcon.rutheniumcore.config.YamlColor;
import me.silkyfalcon.rutheniumcore.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.UUID;

public class SwitcherBallListener implements Listener {
    public final HashMap<UUID, Long> cooldown = new HashMap<>();
    private final RutheniumCore plugin;

    public SwitcherBallListener(RutheniumCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSwitcherThrow(ProjectileLaunchEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player p)) return;
        if (!(e.getEntity() instanceof Snowball)) return;
        p.getInventory().getItemInMainHand();
        if (!p.getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!p.getInventory().getItemInMainHand().getItemMeta().hasLocalizedName()) return;
        if (!p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("SWITCHER_BALL")) return;

        checkWorld(e, p);
        coolDown(e, p);
        e.getEntity().setMetadata("SWITCHER_BALL", new FixedMetadataValue(plugin, true));
    }

    @EventHandler
    public void onSwitcherHit(ProjectileHitEvent e) {
        if (e.getHitBlock() != null) return;
        if (e.getHitBlockFace() != null) return;
        if (!e.getEntity().hasMetadata("SWITCHER_BALL")) return;
        Player p = (Player) e.getEntity().getShooter();
        if (!(e.getHitEntity() instanceof Player t)) return;
        if (e.getHitEntity() == p) return;

        assert p != null;
        if (t.getLocation().distanceSquared(p.getLocation()) <= plugin.getYamlStorage().getData().getDouble("switcherball.range")*plugin.getYamlStorage().getData().getDouble("switcherball.range")) {
            p.teleportAsync(t.getLocation());
            t.teleportAsync(p.getLocation());
            t.sendMessage(ConfigUtils.switchMessage(p));
            p.sendMessage(ConfigUtils.switchMessage(t));
        }
        else {
            p.sendMessage(YamlColor.formatter(plugin.getYamlStorage().getData().getString("switcherball.messages.not-in-range")
                    .replace("%range%", String.valueOf(plugin.getYamlStorage().getData().getDouble("switcherball.range")))));
            t.sendMessage(YamlColor.formatter(plugin.getYamlStorage().getData().getString("switcherball.messages.not-in-range")
                    .replace("%range%", String.valueOf(plugin.getYamlStorage().getData().getDouble("switcherball.range")))));
        }
    }

    public void checkWorld(ProjectileLaunchEvent e, Player p) {
        for (String blocked_worlds : ConfigUtils.blockedWorlds()) {
            if (blocked_worlds.contains(e.getEntity().getWorld().getName())) {
                p.sendMessage(ConfigUtils.blockedWorldMessage());
                e.setCancelled(true);
                return;
            }
        }
    }

    public void coolDown(ProjectileLaunchEvent e, Player p) {
        if (!cooldown.containsKey(p.getUniqueId()) || System.currentTimeMillis() - cooldown.get(p.getUniqueId()) > ConfigUtils.cd()) {
            cooldown.put(p.getUniqueId(), System.currentTimeMillis());
        } else {
            String cd = String.valueOf((ConfigUtils.cd() - (System.currentTimeMillis() - cooldown.get(p.getUniqueId()))) / 1000);
            e.setCancelled(true);

            p.sendMessage(ConfigUtils.coolDownMessage().replace("%cooldown%", cd));

        }
    }
}
