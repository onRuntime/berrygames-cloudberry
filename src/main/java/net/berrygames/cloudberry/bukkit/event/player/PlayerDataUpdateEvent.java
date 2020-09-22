package net.berrygames.cloudberry.bukkit.event.player;

import net.berrygames.cloudberry.bukkit.player.PlayerData;

public class PlayerDataUpdateEvent extends PlayerDataEvent {

    public PlayerDataUpdateEvent(PlayerData playerData) {
        super(playerData);
    }
}
