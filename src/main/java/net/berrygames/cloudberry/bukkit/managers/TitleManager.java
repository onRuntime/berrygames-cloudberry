package net.berrygames.cloudberry.bukkit.managers;

import net.berrygames.cloudberry.bukkit.title.AnimatedTitle;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface TitleManager {

    void addTitle(UUID uniqueId, AnimatedTitle title);
    default void addTitle(Player player, AnimatedTitle title) {
        addTitle(player.getUniqueId(), title);
    }
}
