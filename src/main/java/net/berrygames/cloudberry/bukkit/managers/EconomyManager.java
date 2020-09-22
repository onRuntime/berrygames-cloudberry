package net.berrygames.cloudberry.bukkit.managers;

import net.berrygames.CallBack;
import net.berrygames.cloudberry.bukkit.player.PlayerData;
import net.berrygames.cloudberry.bukkit.player.economy.Multiplier;

import java.util.UUID;

public interface EconomyManager {

    CallBack<PlayerData> creditCoins(String playerName, long coins);
    CallBack<PlayerData> creditCredits(String playerName, long credits);

    //TODO: Multiplier by rank.
    Multiplier getMultiplier(String playerName);
    Multiplier getMultiplier(int type, int game);
}
