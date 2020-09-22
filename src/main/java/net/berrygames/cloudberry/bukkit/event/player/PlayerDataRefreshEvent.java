package net.berrygames.cloudberry.bukkit.event.player;

import net.berrygames.cloudberry.bukkit.player.PlayerData;

public class PlayerDataRefreshEvent extends PlayerDataEvent {

    public PlayerDataRefreshEvent(PlayerData playerData) {
        super(playerData);
    }
}
