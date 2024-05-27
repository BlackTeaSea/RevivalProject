package org.blackteasea.revival.functions;

import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.blackteasea.revival.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import java.util.UUID;

/**
 * This class handles the resurrection of a player in the game.
 */
public class Resurrect {
    // Server instance from the Data singleton
    private final static Server server = Data.getInstance().getJavaPlugin().getServer();
    // ForwardingAudience instance from the Bukkit server
    private final static ForwardingAudience audience = Bukkit.getServer();
    // Sound instance for the revival sound effect
    private final static Sound revive = Sound.sound(Key.key("block.end_portal.spawn"), Sound.Source.NEUTRAL, 1f, 1f);

    /**
     * This method handles the resurrection of a player.
     * It adjusts the player's playtime and death time statistics, teleports the player to a specified location,
     * changes the player's game mode to survival, plays a sound effect, broadcasts a message to the server,
     * and removes the player's entry from the Data singleton.
     *
     * @param uuid The UUID of the player to be resurrected
     * @param loc The location to which the player will be teleported
     */
    public static void resurrect(UUID uuid, Location loc){
        // Calculate the new playtime
        int newPlayTime = (Cost.getPlayTime(uuid) - Cost.getDeathTime(uuid))/(60);

        // Get the player instance and teleport the player
        Player player = server.getPlayer(uuid);
        assert player != null;
        player.teleport(loc);

        // Update the player's statistics
        player.setStatistic(Statistic.PLAY_ONE_MINUTE, newPlayTime);
        player.setStatistic(Statistic.TIME_SINCE_DEATH, 0);
        player.setGameMode(org.bukkit.GameMode.SURVIVAL);

        // Play the revival sound effect
        audience.playSound(revive);

        // Broadcast the resurrection message
        final Component respawn = Component.text(player.getName())
                .color(TextColor.color(0x5D3FD3))
                .append(Component.text(" has undergone Revival!", TextColor.color(0xFFFFFF)));
        server.broadcast(respawn);

        // Remove the player's entry from the Data singleton
        Data.getInstance().deleteEntry(uuid);
    }




}
