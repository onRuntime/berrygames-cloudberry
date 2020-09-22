package net.berrygames;

/**
 * This file is a part of BerryGames, located on net.berrygames
 *
 * @author Noctember
 * Created the 3/28/20 at 8:11 AM.
 */
public interface CallBackWithError<V> {
    void done(V data, Throwable error);
}
