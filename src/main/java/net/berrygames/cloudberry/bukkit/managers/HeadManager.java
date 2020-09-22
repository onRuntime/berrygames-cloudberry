package net.berrygames.cloudberry.bukkit.managers;

import net.berrygames.cloudberry.bukkit.item.ItemBuilder;

public interface HeadManager {

    void addHead(String name, String path);
    void removeHead(String name);

    ItemBuilder getHead(String name);
}
