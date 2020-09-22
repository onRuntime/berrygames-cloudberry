package net.berrygames.cloudberry.bukkit.party;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface Party {

    UUID getOwner();
    boolean isOwner(UUID uniqueId);
    default boolean isOwner(Player player) {
        return isOwner(player.getUniqueId());
    }

    List<UUID> getMembers();
    List<UUID> getOnlineMembers();

    void addMember(UUID uniqueId);
    default void addMember(Player player) {
        addMember(player.getUniqueId());
    }

    void removeMember(UUID uniqueId);
    default void removeMember(Player player) {
        removeMember(player.getUniqueId());
    }

    void delete();
}
