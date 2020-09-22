package net.berrygames.cloudberry.bukkit.network;

import net.berrygames.cloudberry.bukkit.BukkitCloud;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * This file is a part of BerryGames, located on net.berrygames.cloudberry.bukkit.tools.network
 *
 * @author Noctember
 * Created the 3/26/20 at 12:01 AM.
 */
public class ServerSwitcher {
    public static void send(Player player, String serverName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeUTF("Connect");
            dos.writeUTF(serverName);
        } catch (Exception ex) {
            //TODO: Proper error handling
        }
        player.sendPluginMessage(BukkitCloud.get(), "BungeeCord", baos.toByteArray());
    }
}
