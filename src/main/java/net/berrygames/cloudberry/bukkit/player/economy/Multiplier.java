package net.berrygames.cloudberry.bukkit.player.economy;

import java.util.Map;

public interface Multiplier {

    int getAmount();
    Map<String, Integer> getCombinedData();

    Multiplier cross(int multiplier);
    Multiplier cross(Multiplier multiplier);

    boolean isValid();

    String getMessage();
}
