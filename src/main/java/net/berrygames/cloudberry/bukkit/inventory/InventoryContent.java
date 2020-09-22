package net.berrygames.cloudberry.bukkit.inventory;

import net.berrygames.cloudberry.bukkit.BukkitCloud;
import net.berrygames.cloudberry.bukkit.managers.InventoryManager;
import net.berrygames.cloudberry.bukkit.item.ItemBuilder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This file is a part of BerryGames, located on hub.utils.gui
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 16:22.
 */
public interface InventoryContent {

    SmartInventory inventory();
    Pagination pagination();

    Optional<SlotIterator> iterator(String id);
    SlotIterator newIterator(String id, SlotIterator.Type type, int startRow, int startColumn);
    SlotIterator newIterator(SlotIterator.Type type, int startRow, int startColumn);

    ItemBuilder[][] all();

    Optional<ItemBuilder> get(int row, int column);
    InventoryContent set(int row, int column, ItemBuilder item);

    InventoryContent fill(ItemBuilder item);
    InventoryContent fillRow(int row, ItemBuilder item);
    InventoryContent fillColumn(int column, ItemBuilder item);
    InventoryContent fillBorders(ItemBuilder item);
    InventoryContent fillRect(int fromRow, int fromColumn,
                              int toRow, int toColumn, ItemBuilder item);

    <T> T property(String name);
    <T> T property(String name, T def);

    InventoryContent setProperty(String name, Object value);

    class Impl implements InventoryContent {

        private SmartInventory inv;
        private ItemBuilder[][] contents;

        private Pagination pagination = new Pagination.Impl();
        private Map<String, SlotIterator> iterators = new HashMap<>();
        private Map<String, Object> properties = new HashMap<>();

        public Impl(SmartInventory inv) {
            this.inv = inv;
            this.contents = new ItemBuilder[inv.getRows()][inv.getColumns()];
        }

        @Override
        public SmartInventory inventory() { return inv; }

        @Override
        public Pagination pagination() { return pagination; }

        @Override
        public Optional<SlotIterator> iterator(String id) {
            return Optional.ofNullable(this.iterators.get(id));
        }

        @Override
        public SlotIterator newIterator(String id, SlotIterator.Type type, int startRow, int startColumn) {
            SlotIterator iterator = new SlotIterator.Impl(this, inv,
                    type, startRow, startColumn);

            this.iterators.put(id, iterator);
            return iterator;
        }

        @Override
        public SlotIterator newIterator(SlotIterator.Type type, int startRow, int startColumn) {
            return new SlotIterator.Impl(this, inv, type, startRow, startColumn);
        }

        @Override
        public ItemBuilder[][] all() { return contents; }

        @Override
        public Optional<ItemBuilder> get(int row, int column) {
            if(row >= contents.length)
                return Optional.empty();
            if(column >= contents[row].length)
                return Optional.empty();

            return Optional.ofNullable(contents[row][column]);
        }

        @Override
        public InventoryContent set(int row, int column, ItemBuilder item) {
            if(row >= contents.length)
                return this;
            if(column >= contents[row].length)
                return this;

            contents[row][column] = item;
            update(row, column, item);
            return this;
        }

        @Override
        public InventoryContent fill(ItemBuilder item) {
            for(int row = 0; row < contents.length; row++)
                for(int column = 0; column < contents[row].length; column++)
                    set(row, column, item);

            return this;
        }

        @Override
        public InventoryContent fillRow(int row, ItemBuilder item) {
            if(row >= contents.length)
                return this;

            for(int column = 0; column < contents[row].length; column++)
                set(row, column, item);

            return this;
        }

        @Override
        public InventoryContent fillColumn(int column, ItemBuilder item) {
            for(int row = 0; row < contents.length; row++)
                set(row, column, item);

            return this;
        }

        @Override
        public InventoryContent fillBorders(ItemBuilder item) {
            set(1, 0, item);
            set(0, 0, item);
            set(0, 1, item);
            set(0, 2, item);

            set(0, 6, item);
            set(0, 7, item);
            set(0, 8, item);
            set(1, 8, item);

            set(4, 8, item);
            set(5, 8, item);
            set(5, 7, item);
            set(5, 6, item);

            set(5, 2, item);
            set(5, 1, item);
            set(5, 0, item);
            set(4, 0, item);
            return this;
        }

        @Override
        public InventoryContent fillRect(int fromRow, int fromColumn, int toRow, int toColumn, ItemBuilder item) {
            for(int row = fromRow; row <= toRow; row++) {
                for(int column = fromColumn; column <= toColumn; column++) {
                    if(row != fromRow && row != toRow && column != fromColumn && column != toColumn)
                        continue;

                    set(row, column, item);
                }
            }
            return this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T property(String name) {
            return (T) properties.get(name);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T property(String name, T def) {
            return properties.containsKey(name) ? (T) properties.get(name) : def;
        }

        @Override
        public InventoryContent setProperty(String name, Object value) {
            properties.put(name, value);
            return this;
        }

        private void update(int row, int column, ItemStack item) {
            InventoryManager manager = BukkitCloud.get().getInventoryManager();

            manager.getOpenedPlayers(inv)
                    .forEach(p -> p.getOpenInventory().getTopInventory().setItem(9 * row + column, item));
        }
    }
}
