package net.berrygames.cloudberry.bukkit.managers;

import net.berrygames.cloudberry.bukkit.inventory.InventoryContent;
import net.berrygames.cloudberry.bukkit.inventory.InventoryOpener;
import net.berrygames.cloudberry.bukkit.inventory.SmartInventory;
import net.berrygames.cloudberry.bukkit.item.ClickableItem;
import net.berrygames.cloudberry.bukkit.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import java.util.List;
import java.util.Optional;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.inventory
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 16:32.
 */
public interface InventoryManager {

    void init();

    Optional<InventoryOpener> findOpener(InventoryType type);
    void registerOpeners(InventoryOpener... openers);
    List<Player> getOpenedPlayers(SmartInventory inventory);

    Optional<SmartInventory> getInventory(Player player);
    void setInventory(Player player, SmartInventory inventory);

    Optional<InventoryContent>  getContent(Player player);
    void setContent(Player player, InventoryContent content);

    ItemBuilder newItem(Player player, int index, ItemBuilder item);
    ClickableItem newClickableItem(Player player, int index, ClickableItem item);
}
