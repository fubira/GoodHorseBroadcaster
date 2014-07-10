package net.ironingot.goodhorse;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class GoodHorseBroadcaster extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("Minecraft");
    private ConfigHandler configHandler;

    public void onEnable() {
        new GoodHorseSpawnListener(this);
        loadConfig();
    }

    public void onDisable() {
    }

    public void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");

        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        configHandler = new ConfigHandler(configFile);
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }
}
