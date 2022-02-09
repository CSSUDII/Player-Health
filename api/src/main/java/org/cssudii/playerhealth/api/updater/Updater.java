package org.cssudii.playerhealth.api.updater;

import java.util.function.Consumer;

public interface Updater {
    void getVersion(final Consumer<String> consumer);
}
