package org.cssudii.playerhealth.spigot.updater;

import org.bukkit.Bukkit;
import org.cssudii.playerhealth.api.updater.Updater;
import org.cssudii.playerhealth.spigot.PlayerHeathSpigot;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class SpigotUpdater implements Updater {
    private PlayerHeathSpigot plugin;
    private final int resourceId;

    public SpigotUpdater(PlayerHeathSpigot plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
        });
    }
}
