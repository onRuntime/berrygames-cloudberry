package net.berrygames.cloudberry.bukkit.player.rank;

import lombok.Getter;
import org.bukkit.ChatColor;

public class Rank implements Cloneable {

    @Getter
    private String name;

    private String tag, prefix, suffix;

    @Getter
    private int order, permissionLevel;

    public Rank(String name, String tag, String prefix, String suffix, int order, int permissionLevel) {
        this.name = name;
        this.tag = tag;
        this.prefix = prefix;
        this.suffix = suffix;
        this.order = order;
        this.permissionLevel = permissionLevel;
    }

    public String getTag() {
        if(tag == null)
            return "";
        return ChatColor.translateAlternateColorCodes('&', tag.replaceAll("&s", " "));
    }

    public String getPrefix() {
        return prefix != null ? prefix : "";
    }

    public String getSuffix() {
        return suffix != null ? suffix : "";
    }

    public Rank clone() {
        Rank rank = null;
        try {
            rank = (Rank) super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return rank;
    }
}
