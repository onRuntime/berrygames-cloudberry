package net.berrygames.cloudberry.bukkit.announce;

import org.slf4j.event.Level;

public interface Announcer {

    default void alert(String announce) {
        send(Level.WARN, announce);
    }

    default void info(String announce) {
        send(Level.INFO, announce);
    }

    default void send(String announce) {
        info(announce);
    }

    void send(Level level, String announce);

    default void alertNetwork(String announce) {
        sendNetwork(Level.WARN, announce);
    }

    default void infoNetwork(String announce) {
        sendNetwork(Level.INFO, announce);
    }

    default void sendNetwork(String announce) {
        infoNetwork(announce);
    }

    void sendNetwork(Level level, String announce);
}
