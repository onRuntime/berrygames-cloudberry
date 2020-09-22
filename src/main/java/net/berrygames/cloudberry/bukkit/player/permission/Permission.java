package net.berrygames.cloudberry.bukkit.player.permission;

import lombok.Getter;

import java.util.EnumSet;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.player.permission
 *
 * @author Noctember
 * Created the 3/26/20 at 1:08 AM.
 */
public enum Permission {
    // Commands
    BAN(0),
    KICK(1),
    MUTE(2),

    ;

    public static final long ALL_COMMAND_PERMISSIONS
            = Permission.getRaw(BAN);
    public static final long ALL_PERMISSIONS = Permission.getRaw(Permission.values());

    @Getter
    private final int offset;
    private final long raw;


    Permission(int offset) {
        this.offset = offset;
        this.raw = 1 << offset;
    }

    public static long getRaw(Permission... permissions) {
        long raw = 0;
        for (Permission permission : permissions) {
            if (permission != null) {
                raw |= permission.raw;
            }
        }
        return raw;
    }

    public static EnumSet<Permission> getPermissions(long permissions) {
        if (permissions == 0)
            return EnumSet.noneOf(Permission.class);
        EnumSet<Permission> perms = EnumSet.noneOf(Permission.class);
        for (Permission perm : Permission.values()) {
            if ((permissions & perm.raw) == perm.raw)
                perms.add(perm);
        }
        return perms;
    }
}
