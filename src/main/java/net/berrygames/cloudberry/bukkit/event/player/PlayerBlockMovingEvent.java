package net.berrygames.cloudberry.bukkit.event.player;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * This file is a part of BerryGames, located on net.berrygames.api.event.game
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 04/02/2020 at 21:14.
 */
public class PlayerBlockMovingEvent extends PlayerEvent {

    @Getter
    private static HandlerList handlerList = new HandlerList();

    @Getter
    private Block from, to;

    public PlayerBlockMovingEvent(Player player, Block from, Block to) {
        super(player);
        this.from = from;
        this.to = to;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
