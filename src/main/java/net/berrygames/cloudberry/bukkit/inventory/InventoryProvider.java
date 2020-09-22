package net.berrygames.cloudberry.bukkit.inventory;

import org.bukkit.entity.Player;

/**
 * This file is a part of BerryGames, located on net.berrygames.core.inventory.gui
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 17:05.
 */
public interface InventoryProvider {

    void init(Player player, InventoryContent content);
    void update(Player player, InventoryContent content);
}
