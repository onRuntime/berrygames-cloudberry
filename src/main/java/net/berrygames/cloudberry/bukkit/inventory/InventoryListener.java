package net.berrygames.cloudberry.bukkit.inventory;

import lombok.Getter;

import java.util.function.Consumer;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.inventory
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 16:55.
 */
public class InventoryListener<T> {

    @Getter
    private Class<T> type;
    private Consumer<T> consumer;

    public InventoryListener(Class<T> type, Consumer<T> consumer) {
        this.type = type;
        this.consumer = consumer;
    }

    public void accept(T t) {
        consumer.accept(t);
    }
}
