package org.cssudii.playerhealth.spigot.platform;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;
import org.cssudii.playerhealth.api.PlayerHealthAPI;
import org.cssudii.playerhealth.spigot.PlayerHeathSpigot;

import java.util.ArrayList;

public class SpigotPlayerHealthAPI implements PlayerHealthAPI<Listener, Player> {
    private final ArrayList<Listener> listeners = new ArrayList<>();
    PlayerHeathSpigot heathSpigot = PlayerHeathSpigot.getInstance();

    @Override
    public ArrayList<Listener> getListeners() {
        return listeners;
    }

    @Override
    public void setScoreBoardDefault(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective health = scoreboard.registerNewObjective("show_health", Criterias.HEALTH);
        health.setDisplaySlot(DisplaySlot.BELOW_NAME);
        health.setDisplayName(heathSpigot.getDisplayName());
    }

    @Override
    public int getPlayerMaxHealth(Player player) {
        return (int) player.getMaxHealth();
    }

    @Override
    public int getPlayerHealth(Player player) {
        return (int) player.getHealth();
    }

    @Override
    public String getHealthIcon() {
        return "❤";
    }
}
