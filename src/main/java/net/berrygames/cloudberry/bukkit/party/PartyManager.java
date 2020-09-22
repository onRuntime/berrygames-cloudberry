package net.berrygames.cloudberry.bukkit.party;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface PartyManager {

    void createParty(UUID uniqueId);
    default void createParty(Player player) {
        createParty(player.getUniqueId());
    }

    Party getParty(UUID uniqueId);
    default Party getParty(Player player) {
        return getParty(player.getUniqueId());
    }

    boolean hasParty(UUID uniqueId);
    default boolean hasParty(Player player) {
        return hasParty(player.getUniqueId());
    }
}
