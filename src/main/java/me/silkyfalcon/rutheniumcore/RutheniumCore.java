package me.silkyfalcon.rutheniumcore;

import me.silkyfalcon.rutheniumcore.config.YamlStorage;
import me.silkyfalcon.rutheniumcore.utils.MainHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class RutheniumCore extends JavaPlugin {
    private YamlStorage yamlStorage;

    @Override
    public void onEnable() {
        // YamlStorage
        yamlStorage = new YamlStorage(this);
        yamlStorage.createFile();
        yamlStorage.load();

        new MainHandler().onStartup(this);
    }

    @Override
    public void onDisable() {
        new MainHandler().onStartup(this);
    }


    public YamlStorage getYamlStorage() {
        return yamlStorage;
    }



}
