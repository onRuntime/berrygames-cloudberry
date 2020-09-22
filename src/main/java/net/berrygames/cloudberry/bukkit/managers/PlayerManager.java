package net.berrygames.cloudberry.bukkit.managers;

import net.berrygames.cloudberry.bukkit.player.PlayerData;

import java.awt.*;
import java.util.UUID;

public interface PlayerManager {

    void loadPlayer(String playerName);
    void unloadPlayer(String playerName);

    PlayerData getPlayer(String playerName);
    PlayerData getPlayer(String playerName, boolean forceRefresh);

    EconomyManager getEconomyManager();

    void connect(String playerName, String server);
    void kick(String playerName, TextComponent reason);
}
