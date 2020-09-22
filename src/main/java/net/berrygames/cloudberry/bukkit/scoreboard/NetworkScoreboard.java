package net.berrygames.cloudberry.bukkit.scoreboard;

import lombok.Getter;
import net.berrygames.cloudberry.bukkit.BukkitCloud;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * This file is a part of BerryGames, located on net.berrygames.tools.scoreboard
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author Jérèm {@literal <hey@3dx900.fr>}
 * Created the 06/02/2020 at 16:47.
 */
public abstract class NetworkScoreboard {

    @Getter
    private final Player player;
    @Getter
    private final ObjectiveSign objective;

    private final ScheduledFuture glowingTask, updatingTask;

    private int countdown = 0;
    private int ipCharIndex = 0;

    public NetworkScoreboard(Player player) {
        this.player = player;
        objective = new ObjectiveSign(Bukkit.getServerName().toLowerCase(), "BerryGames");

        BukkitCloud.get().getScoreboardManager().addScoreboard(player.getUniqueId(), this);

        objective.setDisplayName("§6§lBerryGames");
        objective.addReceiver(player);
        objective.setLine(0, "§0");

        var scheduledExecutorService = Executors.newScheduledThreadPool(8);

        glowingTask = scheduledExecutorService.scheduleAtFixedRate(
                () -> scheduledExecutorService.execute(() -> update(glowIp())), 80, 80, TimeUnit.MILLISECONDS);
        updatingTask = scheduledExecutorService.scheduleAtFixedRate(
                () -> scheduledExecutorService.execute(this::updateData), 500, 500, TimeUnit.MILLISECONDS);

        updateData();
    }

    public abstract ObjectiveSign getScoreboard(ObjectiveSign objective);

    public abstract void updateData();

    public void update(String glowIp) {
        ObjectiveSign updatedObjective = getScoreboard(objective);

        if(glowIp != null) {
            updatedObjective.setLine(14, "§r");
            updatedObjective.setLine(15, ChatColor.GOLD + glowIp);
        }
        updatedObjective.updateLines();
    }

    public void stop() {
        objective.removeReceiver(player);
        glowingTask.cancel(true);
        updatingTask.cancel(true);
        BukkitCloud.get().getScoreboardManager().removeScoreboard(player.getUniqueId());

        objective.clearScores();
    }

    private String glowIp() {
        var ip = "play.berrygames.net";

        if(countdown > 0) {
            countdown--;
            return ip;
        }
        var formattedIp = new StringBuilder();

        if(ipCharIndex > 0) {
            formattedIp.append(ip, 0, ipCharIndex - 1);
            formattedIp.append(ChatColor.YELLOW).append(ip, ipCharIndex - 1, ipCharIndex);
        } else {
            formattedIp.append(ip, 0, ipCharIndex);
        }
        formattedIp.append(ChatColor.WHITE).append(ip.charAt(ipCharIndex));

        if(ipCharIndex + 1 < ip.length()) {
            formattedIp.append(ChatColor.YELLOW).append(ip.charAt(ipCharIndex + 1));

            if(ipCharIndex + 2 < ip.length())
                formattedIp.append(ChatColor.GOLD).append(ip.substring(ipCharIndex + 2));
            ipCharIndex++;
        } else {
            ipCharIndex = 0;
            countdown = 50;
        }
        return formattedIp.toString();
    }
}
