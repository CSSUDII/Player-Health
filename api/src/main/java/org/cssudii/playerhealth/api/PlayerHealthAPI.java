package org.cssudii.playerhealth.api;

import java.util.ArrayList;

public interface PlayerHealthAPI<ListenerType, PlayerType> {
    ArrayList<ListenerType> getListeners();

    void setScoreBoardDefault(PlayerType player);
    int getPlayerMaxHealth(PlayerType player);
    int getPlayerHealth(PlayerType player);

    String getHealthIcon();
}
