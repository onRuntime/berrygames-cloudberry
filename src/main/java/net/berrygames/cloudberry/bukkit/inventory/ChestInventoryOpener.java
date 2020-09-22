package net.berrygames.cloudberry.bukkit.inventory;

import net.berrygames.cloudberry.bukkit.BukkitCloud;
import net.berrygames.cloudberry.bukkit.managers.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.inventory
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 16:40.
 */
public class ChestInventoryOpener implements InventoryOpener {

    @Override
    public Inventory open(SmartInventory inventory, Player player) {
        InventoryManager manager = BukkitCloud.get().getInventoryManager();
        Inventory handle = Bukkit.createInventory(player, inventory.getRows() * inventory.getColumns(), inventory.getTitle());

        fill(handle, manager.getContent(player).get());

        player.openInventory(handle);
        return handle;
    }

    @Override
    public boolean supports(InventoryType type) {
        switch(type) {
            case CHEST:
            case ENDER_CHEST:
                return true;
            default:
                return false;
        }
    }
}
