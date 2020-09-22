package net.berrygames.cloudberry.bukkit.event.player;

import lombok.Getter;
import net.berrygames.cloudberry.bukkit.player.PlayerData;

public class PlayerDataExperienceEvent extends PlayerDataEvent {

    @Getter
    private final int expDifference;

    public PlayerDataExperienceEvent(PlayerData playerData, int expDifference) {
        super(playerData);
        this.expDifference = expDifference;
    }
}
