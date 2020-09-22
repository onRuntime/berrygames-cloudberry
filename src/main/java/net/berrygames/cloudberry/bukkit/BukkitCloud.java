package net.berrygames.cloudberry.bukkit;

import net.berrygames.cloudberry.Cloudberry;
import net.berrygames.cloudberry.bukkit.announce.Announcer;
import net.berrygames.cloudberry.bukkit.item.ItemBuilder;
import net.berrygames.cloudberry.bukkit.managers.*;
import net.berrygames.cloudberry.bukkit.player.PlayerData;
import net.berrygames.cloudberry.bukkit.world.WorldShield;
import net.berrygames.translation.ITranslatable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public abstract class BukkitCloud extends JavaPlugin implements Cloudberry {

    private static BukkitCloud INSTANCE;

    public BukkitCloud() {
        INSTANCE = this;
    }

    public abstract IScoreboardManager getScoreboardManager();

    public abstract InventoryManager getInventoryManager();

    public abstract RankManager getRankManager();

    public abstract PlayerManager getPlayerManager();

    public abstract TitleManager getTitleManager();

    public abstract HeadManager getHeadManager();

    public abstract WorldShield getWorldShield();

    public abstract Announcer getAnnouncer();

    public void registerHead(String name, String path) {
        getHeadManager().addHead(name, path);
    }

    public ItemBuilder getHead(String name) {
        return getHeadManager().getHead(name);
    }

    public void announce(String announce) {
        // getAnnouncer().announce(level, announce);
    }

    public PlayerData getPlayer(String playerName) {
        return getPlayerManager().getPlayer(playerName);
    }

    @Override
    public abstract ITranslatable getTranslatable();

    public static BukkitCloud get() {
        return INSTANCE;
    }
}
