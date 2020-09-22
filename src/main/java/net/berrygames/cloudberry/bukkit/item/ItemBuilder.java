package net.berrygames.cloudberry.bukkit.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class ItemBuilder extends ItemStack {

    @Deprecated
    public ItemBuilder(final int type) {
        super(type);
    }

    public ItemBuilder(final Material type) {
        super(type);
    }

    public ItemBuilder(final String head) {
        this(head, 1);
    }

    public ItemBuilder(final OfflinePlayer owner) {
        this(owner, 1);
    }

    @Deprecated
    public ItemBuilder(final int type, final int amount) {
        super(type, amount);
    }

    public ItemBuilder(final Material type, final int amount) {
        super(type, amount);
    }

    public ItemBuilder(final String head, final int amount) {
        this(head, amount, (short) 3);
    }

    public ItemBuilder(final OfflinePlayer owner, final int amount) {
        this(owner, amount, (short) 3);
    }

    @Deprecated
    public ItemBuilder(final int type, final int amount, final short damage) {
        super(type, amount, damage);
    }

    public ItemBuilder(final Material type, final int amount, final short damage) {
        super(type, amount, damage);
    }

    public ItemBuilder(final String head, final int amount, final short damage) {
        this(Material.SKULL_ITEM, amount, damage);
        if(head.isEmpty()) return;

        final var meta = (SkullMeta) getItemMeta();
        final var profile = new GameProfile(UUID.randomUUID(), null);
        final var encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", head).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        try {
            final var field = meta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        setItemMeta(meta);
    }

    public ItemBuilder(final OfflinePlayer owner, final int amount, final short damage) {
        this("", amount, damage);
        final var meta = (SkullMeta) getItemMeta();
        meta.setOwningPlayer(owner);
        setItemMeta(meta);
    }

    @Deprecated
    public ItemBuilder(final int type, final int amount, final short damage, final byte data) {
        super(type, amount, damage, data);
    }

    @Deprecated
    public ItemBuilder(final Material type, final int amount, final short damage, final byte data) {
        super(type, amount, damage, data);
    }

    public ItemBuilder(final ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }

    public boolean hasDisplayName() {
        return hasItemMeta() && getItemMeta().hasDisplayName();
    }

    public String getDisplayName() {
        if(!hasItemMeta())
            return null;
        return getItemMeta().getDisplayName();
    }

    public ItemBuilder setDisplayName(String name) {
        useMeta(meta -> meta.setDisplayName(name));
        return this;
    }

    public boolean hasLore() {
        return hasItemMeta() && getItemMeta().hasLore();
    }

    public List<String> getLore() {
        if(!hasItemMeta())
            return List.of();
        return getItemMeta().getLore();
    }

    public ItemBuilder appendLore(String lore) {
        appendLore(lore.split("\n"));
        return this;
    }

    public ItemBuilder appendLore(String... lore) {
        appendLore(List.of(lore));
        return this;
    }

    public ItemBuilder appendLore(List<String> lore) {
        final var newLore = getLore();
        newLore.addAll(lore);
        setLore(newLore);
        return this;
    }

    public ItemBuilder setLore(String lore) {
        setLore(lore.split("\n"));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        setLore(List.of(lore));
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        useMeta(meta -> meta.setLore(lore));
        return this;
    }

    private void useMeta(Consumer<ItemMeta> action) {
        if(!hasItemMeta())
            return;
        final var meta = getItemMeta();
        action.accept(meta);
        setItemMeta(meta);
    }
}
