package net.berrygames.cloudberry.bukkit.event.player;

import net.berrygames.cloudberry.bukkit.player.PlayerData;

public class PlayerDataUnloadEvent extends PlayerDataEvent {

    public PlayerDataUnloadEvent(PlayerData playerData) {
        super(playerData);
    }
}
