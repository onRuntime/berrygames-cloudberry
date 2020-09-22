package net.berrygames.cloudberry.bukkit.managers;

import net.berrygames.cloudberry.bukkit.scoreboard.NetworkScoreboard;

import java.util.Map;
import java.util.UUID;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.scoreboard
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 06/02/2020 at 16:55.
 */
public interface IScoreboardManager {

    void addScoreboard(UUID uniqueId, NetworkScoreboard scoreboard);
    void removeScoreboard(UUID uniqueId);

    Map<UUID, NetworkScoreboard> getScoreboards();
}
