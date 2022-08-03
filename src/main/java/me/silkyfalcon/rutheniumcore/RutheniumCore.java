package me.silkyfalcon.rutheniumcore;

import me.silkyfalcon.rutheniumcore.commands.CoreCommand;
import me.silkyfalcon.rutheniumcore.commands.SwitcherBallCommand;
import me.silkyfalcon.rutheniumcore.config.Dependency;
import me.silkyfalcon.rutheniumcore.config.YamlStorage;
import me.silkyfalcon.rutheniumcore.modules.arrow.ArrowStuff;
import me.silkyfalcon.rutheniumcore.modules.eotw.Timer;
import me.silkyfalcon.rutheniumcore.modules.hwid.HardwareID;
import me.silkyfalcon.rutheniumcore.utils.MainHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.UnknownHostException;

public final class RutheniumCore extends JavaPlugin {
    private static RutheniumCore instance;
    private YamlStorage yamlStorage;

    public static RutheniumCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // YamlStorage
        this.yamlStorage = new YamlStorage(this);
        this.yamlStorage.createFile();
        this.yamlStorage.load();

        Dependency dependency = new Dependency(this);
        dependency.createFile();

        getCommand("rutheniumcore").setExecutor(new CoreCommand());
        getCommand("switcherball").setExecutor(new SwitcherBallCommand());
        getCommand("carrow").setExecutor(new ArrowStuff());
        getCommand("timer").setExecutor(new Timer());

        new MainHandler().onStartup(this);
        try {
            new HardwareID().skidChecker(this);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        new MainHandler().onShutdown(this);
    }

    public YamlStorage getYamlStorage() {
        return yamlStorage;
    }
}
