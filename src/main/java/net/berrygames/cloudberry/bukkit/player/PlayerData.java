package net.berrygames.cloudberry.bukkit.player;

import net.berrygames.cloudberry.bukkit.player.economy.MoneyCallback;
import net.berrygames.cloudberry.bukkit.player.permission.Permission;
import net.berrygames.cloudberry.bukkit.player.rank.Rank;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public abstract class PlayerData {

    public abstract String getDisplayName();
    public abstract boolean hasNickname();
    public abstract String getNickname();

    public abstract List<Rank> getRanks();
    public abstract Rank getRank();
    public abstract int getPermissionLevel();
    public abstract boolean hasPermission(Permission permission);

    public abstract void creditCoins(long amount, String reason, boolean applyMultiplier, MoneyCallback callBack, boolean tell);
    public abstract void withdrawCoins(long amount, MoneyCallback callBack);
    public abstract long increaseCoins(long incrBy);
    public abstract long increaseCredits(long incrBy);
    public abstract long decreaseCoins(long drecrBy);
    public void creditCoins(long amount, String reason) {
        this.creditCoins(amount, reason, true, null, false);
    }
    public void creditCoins(long amount, String reason, boolean applyMultiplier, boolean tell) {
        this.creditCoins(amount, reason, applyMultiplier, null, tell);
    }
    public void creditCoins(long amount, String reason, MoneyCallback moneyCallback, boolean tell) {
        this.creditCoins(amount, reason, true, moneyCallback, tell);
    }
    public void withdrawCoins(long amount) {
        this.withdrawCoins(amount, null);
    }

    public abstract long getCoins();
    public boolean hasEnoughCoins(long amount) {
        return this.getCoins() >= amount;
    }
    public abstract long getCredits();
    public abstract long getExperience();

    public abstract Locale getLocale();
    public abstract String format(String key, Object... parameters);
    public abstract boolean isLoaded();
    public abstract boolean needRefresh();
}
