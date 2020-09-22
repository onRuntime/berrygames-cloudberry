package net.berrygames.cloudberry.bukkit.managers;

import net.berrygames.CallBack;
import net.berrygames.cloudberry.bukkit.player.PlayerData;
import net.berrygames.cloudberry.bukkit.player.rank.Rank;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public interface RankManager {

    CallBack<PlayerData> applyRank(String playerName, int id, Date endDate);
    CallBack<PlayerData> applyRank(String playerName, int id);
    CallBack<PlayerData> applyNextRank(String playerName);

    CallBack<PlayerData> removeRank(String playerName, int id);
    CallBack<PlayerData> removeLastRank(String playerName);

    Rank getRank(String name);

    Rank getRankByID(int id);

    Map<Integer, Rank> getRanks();
}
