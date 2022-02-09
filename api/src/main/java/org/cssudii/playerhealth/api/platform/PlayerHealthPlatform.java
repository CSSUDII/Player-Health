package org.cssudii.playerhealth.api.platform;

import org.cssudii.playerhealth.api.PlayerHealthAPI;

import java.util.logging.Logger;

public interface PlayerHealthPlatform<ListenerType, PlayerType> {
    Logger getLogger();
    boolean isEnabled();
    String getVersion();

    PlayerHealthAPI<ListenerType, PlayerType> getAPI();
}
