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

import java.util.List;
import java.util.UUID;

public class Resurrect {
    List<UUID> store;
    private final static Server server = Data.getInstance().getJavaPlugin().getServer();
    private final static ForwardingAudience audience = Bukkit.getServer();
    private final static Sound revive = Sound.sound(Key.key("block.end_portal.spawn"), Sound.Source.NEUTRAL, 1f, 1f);

    public static void resurrect(UUID uuid, Location loc){
        int newplaytime = (Cost.getPlayTime(uuid) - Cost.getDeathTime(uuid))/(60);

        Player player = server.getPlayer(uuid);
        player.teleport(loc);

        player.setStatistic(Statistic.PLAY_ONE_MINUTE, newplaytime);
        player.setStatistic(Statistic.TIME_SINCE_DEATH, 0);
        player.setGameMode(org.bukkit.GameMode.SURVIVAL);

        //Do sounds
        audience.playSound(revive);

        final Component respawn = Component.text(player.getName())
                .color(TextColor.color(0x5D3FD3))
                .append(Component.text(" has undergone Revival!", TextColor.color(0xFFFFFF)));
        server.broadcast(respawn);

        Data.getInstance().deleteEntry(uuid);
    }




}
