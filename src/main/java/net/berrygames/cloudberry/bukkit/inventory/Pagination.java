package net.berrygames.cloudberry.bukkit.inventory;

import net.berrygames.cloudberry.bukkit.item.ItemBuilder;

import java.util.Arrays;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.inventory
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 31/01/2020 at 17:16.
 */
public interface Pagination {

    ItemBuilder[] getPageItems();

    int getPage();
    Pagination page(int page);

    boolean isFirst();
    boolean isLast();

    Pagination first();
    Pagination previous();
    Pagination next();
    Pagination last();

    Pagination addToIterator(SlotIterator iterator);

    Pagination setItems(ItemBuilder... items);
    Pagination setItemsPerPage(int itemsPerPage);


    class Impl implements Pagination {

        private int currentPage;

        private ItemBuilder[] items = new ItemBuilder[0];
        private int itemsPerPage = 10;

        @Override
        public ItemBuilder[] getPageItems() {
            return Arrays.copyOfRange(items,
                    currentPage * itemsPerPage,
                    (currentPage + 1) * itemsPerPage);
        }

        @Override
        public int getPage() {
            return this.currentPage;
        }

        @Override
        public Pagination page(int page) {
            this.currentPage = page;
            return this;
        }

        @Override
        public boolean isFirst() {
            return this.currentPage == 0;
        }

        @Override
        public boolean isLast() {
            return this.currentPage == this.items.length / this.itemsPerPage;
        }

        @Override
        public Pagination first() {
            this.currentPage = 0;
            return this;
        }

        @Override
        public Pagination previous() {
            if(!isFirst())
                this.currentPage--;

            return this;
        }

        @Override
        public Pagination next() {
            if(!isLast())
                this.currentPage++;

            return this;
        }

        @Override
        public Pagination last() {
            this.currentPage = (int) Math.ceil(this.items.length / (float) this.itemsPerPage);
            return this;
        }

        @Override
        public Pagination addToIterator(SlotIterator iterator) {
            for(ItemBuilder item : getPageItems())
                iterator.set(item).next();

            return this;
        }

        @Override
        public Pagination setItems(ItemBuilder... items) {
            this.items = items;
            return this;
        }

        @Override
        public Pagination setItemsPerPage(int itemsPerPage) {
            this.itemsPerPage = itemsPerPage;
            return this;
        }
    }
}
