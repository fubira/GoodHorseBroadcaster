package net.ironingot.goodhorse;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {
    private File configFile;
    private YamlConfiguration config;

    public ConfigHandler(File configFile) {
        this.configFile = configFile;
        this.config = YamlConfiguration.loadConfiguration(configFile);

        load();
    }

    public void load() {
        ConfigurationSection GoodHorseBroadcasterSection = 
                                    config.getConfigurationSection("GoodHorseBroadcaster");

        if (GoodHorseBroadcasterSection != null) {
            for (String key : GoodHorseBroadcasterSection.getKeys(false)) {
                config.set(key, GoodHorseBroadcasterSection.get(key));
            }
        } 
        save();
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}