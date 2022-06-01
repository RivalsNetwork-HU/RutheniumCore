package me.silkyfalcon.rutheniumcore.config;

import me.silkyfalcon.rutheniumcore.RutheniumCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class YamlStorage {
    private FileConfiguration configuration;
    private File data;
    private final RutheniumCore rutheniumCore;


    public YamlStorage(RutheniumCore rutheniumCore) {
        this.rutheniumCore = rutheniumCore;
    }

    public void createFile() {
        this.data = new File(rutheniumCore.getDataFolder(), "config.yml");
        this.configuration = YamlConfiguration.loadConfiguration(this.data);
        if (!this.data.exists()) rutheniumCore.saveResource("config.yml", false);
    }


    public void load() {
        this.configuration = YamlConfiguration.loadConfiguration(this.data);
    }

    public FileConfiguration getData() {
        return this.configuration;
    }


    public void save() {
        try {
            this.configuration.save(this.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}