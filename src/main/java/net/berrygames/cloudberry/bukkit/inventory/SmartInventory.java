package net.berrygames.cloudberry.bukkit.inventory;

import lombok.Builder;
import lombok.Getter;
import net.berrygames.cloudberry.bukkit.BukkitCloud;
import net.berrygames.cloudberry.bukkit.managers.InventoryManager;
import net.berrygames.cloudberry.bukkit.item.ClickableItem;
import net.berrygames.translation.ITranslatable;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This file is a part of BerryGames, located on hub.utils.gui
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 16:18.
 */
@Builder
public class SmartInventory {

    @Builder.Default @Getter
    private String id = "unknown", title = "";
    @Builder.Default @Getter
    private InventoryType type = InventoryType.CHEST;
    @Builder.Default @Getter
    private int rows = 6, columns = 9;
    @Builder.Default @Getter
    private boolean closeable = true;

    @Getter private InventoryProvider provider;
    @Getter private SmartInventory parent;
    @Getter private Navigation navigation;

    @Builder.Default @Getter
    private List<InventoryListener<? extends Event>> listeners = new ArrayList<>();


    public Inventory open(Player player) {
        return open(player, 0);
    }

    public Inventory open(Player player, int page) {
        InventoryManager manager = BukkitCloud.get().getInventoryManager();
        ITranslatable translate = BukkitCloud.get().getTranslatable();
        final var cloud = BukkitCloud.get();

        title = translate.format(player.getName(), ChatColor.DARK_GRAY + title);

        Optional<SmartInventory> oldInventory = manager.getInventory(player);

        oldInventory.ifPresent(inv -> {
            inv.getListeners().stream()
                    .filter(listener -> listener.getType() == InventoryCloseEvent.class)
                    .forEach(listener -> ((InventoryListener<InventoryCloseEvent>) listener)
                            .accept(new InventoryCloseEvent(player.getOpenInventory())));

            manager.setInventory(player, null);
        });

        InventoryContent content = new InventoryContent.Impl(this);
        content.pagination().page(page);

        manager.setContent(player, content);
        if(closeable)
            content.set(5, 4, new ClickableItem(cloud.getHead("core.item.close")).onClick(HumanEntity::closeInventory).setDisplayName(translate.format(player.getName(), "hub.menu.all.item.quit")));
        if(parent != null) {
            content.set(5, 0, new ClickableItem(cloud.getHead("core.item.parent")).onClick(p -> parent.open(p)).setDisplayName(translate.format(player.getName(), "menu.back")));
        }
        if(!content.pagination().isFirst())
            content.set(5, 3, new ClickableItem(cloud.getHead("core.item.previous")).onClick(p -> content.pagination().previous().getPage()).setDisplayName(translate.format(player.getName(), "menu.previous")));
        if(!content.pagination().isLast())
            content.set(5, 5, new ClickableItem(cloud.getHead("core.item.next")).onClick(p -> content.pagination().previous().getPage()).setDisplayName(translate.format(player.getName(), "menu.next")));
        provider.init(player, content);

        InventoryOpener opener = manager.findOpener(type)
                .orElseThrow(() -> new IllegalStateException("No opener found for the inventory type " + type.name()));
        Inventory handle = opener.open(this, player);

        manager.setInventory(player, this);
        return handle;
    }

    @SuppressWarnings("unchecked")
    public void close(Player player) {
        InventoryManager manager = BukkitCloud.get().getInventoryManager();

        listeners.stream()
                .filter(listener -> listener.getType() == InventoryCloseEvent.class)
                .forEach(listener -> ((InventoryListener<InventoryCloseEvent>) listener)
                        .accept(new InventoryCloseEvent(player.getOpenInventory())));

        manager.setInventory(player, null);
        player.closeInventory();

        manager.setContent(player, null);
    }
}
