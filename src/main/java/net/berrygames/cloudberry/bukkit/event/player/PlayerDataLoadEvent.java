package net.berrygames.cloudberry.bukkit.event.player;

import lombok.Getter;
import net.berrygames.cloudberry.bukkit.player.PlayerData;

public class PlayerDataLoadEvent extends PlayerDataEvent {

    @Getter
    private final long loadTime;

    public PlayerDataLoadEvent(PlayerData playerData, long loadTime) {
        super(playerData);
        this.loadTime = loadTime;
    }
}
