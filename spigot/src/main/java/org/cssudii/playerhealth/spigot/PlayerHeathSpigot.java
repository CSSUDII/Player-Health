package org.cssudii.playerhealth.spigot;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.cssudii.playerhealth.api.PlayerHealthAPI;
import org.cssudii.playerhealth.api.platform.PlayerHealthPlatform;
import org.cssudii.playerhealth.spigot.platform.SpigotPlayerHealthAPI;
import org.cssudii.playerhealth.spigot.updater.SpigotUpdater;

public class PlayerHeathSpigot extends JavaPlugin implements PlayerHealthPlatform<Listener, Player> {
    private static PlayerHeathSpigot instance;
    private PlayerHealthAPI<Listener, Player> api = new SpigotPlayerHealthAPI();

    private PluginManager pluginManager;
    private FileConfiguration configuration;

    private HealthType healthType;
    private String displayName;
    private int resourceId;

    public PlayerHeathSpigot() {
        instance = this;

        pluginManager = getServer().getPluginManager();
        configuration = getConfig();

        // TODO: Error handling
        healthType = HealthType.valueOf(configuration.getString("health-type").toUpperCase());
    }

    @Override
    public void onEnable() {
        for (Listener listener : getAPI().getListeners()) {
            registerEvent(listener);
        }

        saveDefaultConfig();

        if (!configuration.getBoolean("enabled")) {
            setEnabled(false);
        }

        if (configuration.getBoolean("check-for-updates")) {
            new SpigotUpdater(this, resourceId).getVersion(version -> {

            });
        }
    }

    @Override
    public void onDisable() {
        if (configuration.getBoolean("debug.shutdownserverondisable")) {
            getServer().shutdown();
        } else {
            getAPI().getListeners().clear();
        }
    }

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    public static PlayerHeathSpigot getInstance() {
        return instance;
    }

    @Override
    public PlayerHealthAPI<Listener, Player> getAPI() {
        return api;
    }

    public void registerEvent(Listener listener) {
        pluginManager.registerEvents(listener, this);
    }

    public HealthType getHealthType() {
        return healthType;
    }

    public String getDisplayName() {
        return displayName;
    }
}
