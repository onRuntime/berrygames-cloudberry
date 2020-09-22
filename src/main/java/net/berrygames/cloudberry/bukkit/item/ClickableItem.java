package net.berrygames.cloudberry.bukkit.item;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ClickableItem extends ItemBuilder implements Listener {

    @Getter
    private final Map<String, Consumer<Player>> actions = new HashMap<>();

    @Deprecated
    public ClickableItem(int type) {
        super(type);
    }

    public ClickableItem(Material type) {
        super(type);
    }

    public ClickableItem(String head) {
        super(head);
    }

    public ClickableItem(OfflinePlayer owner) {
        super(owner);
    }

    @Deprecated
    public ClickableItem(int type, int amount) {
        super(type, amount);
    }

    public ClickableItem(Material type, int amount) {
        super(type, amount);
    }

    public ClickableItem(String head, int amount) {
        super(head, amount);
    }

    public ClickableItem(OfflinePlayer owner, int amount) {
        super(owner, amount);
    }

    @Deprecated
    public ClickableItem(int type, int amount, short damage) {
        super(type, amount, damage);
    }

    public ClickableItem(Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    public ClickableItem(String head, int amount, short damage) {
        super(head, amount, damage);
    }

    public ClickableItem(OfflinePlayer owner, int amount, short damage) {
        super(owner, amount, damage);
    }

    @Deprecated
    public ClickableItem(int type, int amount, short damage, byte data) {
        super(type, amount, damage, data);
    }

    @Deprecated
    public ClickableItem(Material type, int amount, short damage, byte data) {
        super(type, amount, damage, data);
    }

    public ClickableItem(ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }

    public ClickableItem onClick(Consumer<Player> action) {
        on(action, ClickType.values());
        return this;
    }

    private void on(Consumer<Player> action, ClickType... type) {
        //BukkitCloud.get().getPlugin().getServer().getPluginManager().registerEvents(this, BukkitCloud.get().getPlugin());
        for (ClickType clickType : type) {
            actions.put(clickType.name(), action);
        }
        if(actions.keySet().containsAll(List.of(ClickType.values()))) {
        }
    }

    /*@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!event.hasItem()
                || !event.getItem().hasItemMeta()
                || !event.getItem().getItemMeta().hasDisplayName()
                || !event.getItem().getItemMeta().getDisplayName().equals(getDisplayName())) return;
        if(actions.containsKey(event.getAction().name())) {
            event.setCancelled(true);
            actions.get(event.getAction().name()).accept(event.getPlayer());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)
                || event.getCurrentItem() == null
                || !event.getCurrentItem().hasItemMeta()
                || !event.getCurrentItem().getItemMeta().hasDisplayName()
                || !event.getCurrentItem().getItemMeta().getDisplayName().equals(getDisplayName())) return;
        if(actions.containsKey(event.getClick().name())) {
            event.setCancelled(true);
            actions.get(event.getClick().name()).accept((Player) event.getWhoClicked());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        System.out.println(event.getInventory().getTitle());
        if(event.getInventory().contains(this))
            HandlerList.unregisterAll(this);
    }*/

    private enum ClickType {
        LEFT(""),
        SHIFT_LEFT(""),
        RIGHT(""),
        SHIFT_RIGHT(""),
        WINDOW_BORDER_LEFT(""),
        WINDOW_BORDER_RIGHT(""),
        MIDDLE(""),
        NUMBER_KEY(""),
        DOUBLE_CLICK(""),
        DROP(""),
        CONTROL_DROP(""),
        CREATIVE(""),
        LEFT_CLICK_BLOCK(""),
        RIGHT_CLICK_BLOCK(""),
        LEFT_CLICK_AIR(""),
        RIGHT_CLICK_AIR(""),
        PHYSICAL("");

        @Getter
        private String name;

        ClickType(String name) {
            this.name = name;
        }
    }
}
