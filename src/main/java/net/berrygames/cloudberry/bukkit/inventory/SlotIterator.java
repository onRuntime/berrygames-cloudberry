package net.berrygames.cloudberry.bukkit.inventory;

import net.berrygames.cloudberry.bukkit.item.ItemBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.inventory
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 17:17.
 */
public interface SlotIterator {

    enum Type {
        HORIZONTAL,
        VERTICAL
    }

    Optional<ItemBuilder> get();
    SlotIterator set(ItemBuilder item);

    SlotIterator previous();
    SlotIterator next();

    SlotIterator blacklist(int row, int column);

    int row();
    SlotIterator row(int row);

    int column();
    SlotIterator column(int column);

    boolean ended();


    class Impl implements SlotIterator {

        private InventoryContent contents;
        private SmartInventory inv;

        private Type type;
        private int row, column;

        private Set<Pair<Integer, Integer>> blacklisted = new HashSet<>();

        public Impl(InventoryContent contents, SmartInventory inv,
                    Type type, int startRow, int startColumn) {

            this.contents = contents;
            this.inv = inv;

            this.type = type;

            this.row = startRow;
            this.column = startColumn;

            this.blacklisted.addAll(List.of(Pair.of(2, 7),
                            Pair.of(2, 8),
                            Pair.of(3, 0),
                            Pair.of(3, 1)));
        }

        public Impl(InventoryContent contents, SmartInventory inv,
                    Type type) {

            this(contents, inv, type, 0, 0);
        }

        @Override
        public Optional<ItemBuilder> get() {
            return contents.get(row, column);
        }

        @Override
        public SlotIterator set(ItemBuilder item) {
            contents.set(row, column, item);
            return this;
        }

        @Override
        public SlotIterator previous() {
            if(row == 0 && column == 0)
                return this;

            do {
                switch(type) {
                    case HORIZONTAL:
                        column--;

                        if(column == 0) {
                            column = inv.getColumns() - 1;
                            row--;
                        }
                        break;
                    case VERTICAL:
                        row--;

                        if(row == 0) {
                            row = inv.getRows() - 1;
                            column--;
                        }
                        break;
                }
            }
            while((row != 0 || column != 0) && blacklisted.contains(Pair.of(row, column)));

            return this;
        }

        @Override
        public SlotIterator next() {
            if(ended())
                return this;

            do {
                switch(type) {
                    case HORIZONTAL:
                        column = ++column % inv.getColumns();

                        if(column == 0)
                            row++;
                        break;
                    case VERTICAL:
                        row = ++row % inv.getRows();

                        if(row == 0)
                            column++;
                        break;
                }
            }
            while(!ended() && blacklisted.contains(Pair.of(row, column)));

            return this;
        }

        @Override
        public SlotIterator blacklist(int row, int column) {
            this.blacklisted.add(Pair.of(row, column));
            return this;
        }

        @Override
        public int row() { return row; }

        @Override
        public SlotIterator row(int row) {
            this.row = row;
            return this;
        }

        @Override
        public int column() { return column; }

        @Override
        public SlotIterator column(int column) {
            this.column = column;
            return this;
        }

        @Override
        public boolean ended() {
            return row == inv.getRows() - 1
                    && column == inv.getColumns() - 1;
        }
    }
}
