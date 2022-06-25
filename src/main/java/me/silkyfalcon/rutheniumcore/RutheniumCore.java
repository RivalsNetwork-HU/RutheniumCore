package me.silkyfalcon.rutheniumcore;

import me.silkyfalcon.rutheniumcore.commands.CoreCommand;
import me.silkyfalcon.rutheniumcore.commands.SwitcherBallCommand;
import me.silkyfalcon.rutheniumcore.config.Dependency;
import me.silkyfalcon.rutheniumcore.config.YamlStorage;
import me.silkyfalcon.rutheniumcore.modules.enderpearl.EnderPearlRemover;
import me.silkyfalcon.rutheniumcore.utils.ChatSentry;
import me.silkyfalcon.rutheniumcore.utils.MainHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RutheniumCore extends JavaPlugin {
    private YamlStorage yamlStorage;
    private static RutheniumCore instance;
    private Dependency dependency;

    @Override
    public void onEnable() {
        instance = this.instance;
        // YamlStorage
        yamlStorage = new YamlStorage(this);
        yamlStorage.createFile();
        yamlStorage.load();

        dependency = new Dependency(this);
        dependency.createFile();

        getCommand("rutheniumcore").setExecutor(new CoreCommand(this));
        getCommand("switcherball").setExecutor(new SwitcherBallCommand());
        Bukkit.getPluginManager().registerEvents(new EnderPearlRemover(this), this);

        new ChatSentry().onChatSentryCheck(this);
        new MainHandler().onStartup(this);
    }

    @Override
    public void onDisable() {
        new MainHandler().onShutdown(this);
    }


    public YamlStorage getYamlStorage() {
        return yamlStorage;
    }

    public static RutheniumCore getInstance() {
        return instance;
    }
}
