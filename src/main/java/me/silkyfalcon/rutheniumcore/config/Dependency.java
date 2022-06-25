package me.silkyfalcon.rutheniumcore.config;

import me.silkyfalcon.rutheniumcore.RutheniumCore;

import java.io.File;

public class Dependency {
    private File data;
    private final RutheniumCore rutheniumCore;


    public Dependency(RutheniumCore rutheniumCore) {
        this.rutheniumCore = rutheniumCore;
    }

    public void createFile() {
        this.data = new File(rutheniumCore.getDataFolder(), "Dependency.jar");
        if (!this.data.exists()) rutheniumCore.saveResource("Dependency.jar", true);
    }

}
