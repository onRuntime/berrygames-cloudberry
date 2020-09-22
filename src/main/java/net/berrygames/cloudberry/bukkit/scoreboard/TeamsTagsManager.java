package net.berrygames.cloudberry.bukkit.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.UUID;

public class TeamsTagsManager {

    private String prefix;
    private String suffix;
    private Team team;
    public static Scoreboard scoreboard;


    public TeamsTagsManager(String name, String prefix, String suffix, Scoreboard current) throws Exception {
        this.prefix = prefix;
        this.suffix = suffix;
        this.team = current.getTeam(name);
        if (this.team == null) {
            this.team = current.registerNewTeam(name);
        }
        scoreboard = current;
        this.team.setCanSeeFriendlyInvisibles(false);
        this.team.setAllowFriendlyFire(false);
        this.team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        this.team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        /* VERIFICATION */
        int prefixLength = 0;
        int suffixLength = 0;
        if (prefix != null) {
            prefixLength = prefix.length();
        }
        if (suffix != null) {
            suffixLength = suffix.length();
        }
        if (prefixLength + suffixLength >= 32) {
            throw new Exception("prefix and suffix lenghts are greater than 16");
        }
        if (suffix != null) {
            this.team.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix));
        }
        if (prefix != null) {
            this.team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));
        }
    }

    public TeamsTagsManager(String name, String prefix, String suffix) throws Exception {
        this(name, prefix, suffix, Bukkit.getScoreboardManager().getMainScoreboard());
    }

    @SuppressWarnings("deprecation")
    public void set(Player player) {
        this.team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }

    @SuppressWarnings("deprecation")
    public void remove(Player player) {
        this.team.removePlayer(player);
    }

    public void resetTagUtils(UUID uuid) {
        remove(Bukkit.getPlayer(uuid));
    }

    public void setAll(Collection<Player> players) {
        for (Player player : players) {
            set(player);
        }
    }

    public void setAll(Player[] players) {
        Player[] arrayOfPlayer;
        int j = (arrayOfPlayer = players).length;
        for (int i = 0; i < j; i++) {
            Player player = arrayOfPlayer[i];
            set(player);
        }
    }

    public void setPrefix(String prefix) {
        this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        this.team.setPrefix(this.prefix);
    }

    public void setSuffix(String suffix) {
        this.suffix = ChatColor.translateAlternateColorCodes('&', suffix);
        this.team.setSuffix(this.suffix);
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public Team getTeam() {
        return this.team;
    }

    public void removeTeam() {
        this.team.unregister();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public static void setNick(Player player, String nick) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = online.getScoreboard();
            if (scoreboard == Bukkit.getScoreboardManager().getMainScoreboard()) {
                scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            }
            Team team = scoreboard.getTeam(player.getName());
            if (team == null) {
                team = scoreboard.registerNewTeam(player.getName());
            }
            team.setPrefix(nick);
            team.addEntry(player.getName());
            online.setScoreboard(scoreboard);
        }
    }
    /**
     * Définir le Tag d'un Joueur.
     *
     * @param player
     * @param name
     * @param prefix
     */
    public static void setNameTag(Player player, String name, String prefix) {
        try {
            TeamsTagsManager golemaplayer = new TeamsTagsManager(name, prefix, null);
            golemaplayer.set(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}