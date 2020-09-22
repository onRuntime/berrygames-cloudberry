package net.berrygames.cloudberry.bukkit.player.economy;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.player.economy
 *
 * @author Noctember
 * Created the 3/26/20 at 7:08 AM.
 */
public interface MoneyCallback {
    void done(long newAmount, long difference, Throwable error);
}

