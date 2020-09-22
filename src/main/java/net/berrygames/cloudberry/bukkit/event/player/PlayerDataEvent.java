package net.berrygames.cloudberry.bukkit.event.player;

import lombok.Getter;
import net.berrygames.cloudberry.bukkit.player.PlayerData;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDataEvent extends Event {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    @Getter
    private final PlayerData playerData;

    public PlayerDataEvent(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
