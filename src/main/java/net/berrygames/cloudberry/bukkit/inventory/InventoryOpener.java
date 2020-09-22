package net.berrygames.cloudberry.bukkit.inventory;

import net.berrygames.cloudberry.bukkit.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * This file is a part of BerryGames, located on hub.utils.gui
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 16:21.
 */
public interface InventoryOpener {

    Inventory open(SmartInventory inventory, Player player);
    boolean supports(InventoryType type);

    default void fill(Inventory inventory, InventoryContent content) {
        ItemBuilder[][] items = content.all();

        for(int row = 0; row < items.length; row++) {
            for(int column = 0; column < items[row].length; column++) {
                if(items[row][column] != null)
                    inventory.setItem(9 * row + column, items[row][column]);
            }
        }
    }
}
