package org.blackteasea.revival;

//Calculates the price of revival based on that dead server.getPlayer(uuid)'s achievements

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.w3c.dom.Text;

import javax.naming.Name;
import javax.xml.stream.events.Namespace;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

    
    
public class Cost {
    public static String[]advancementChecks = {
            "end/kill_dragon",
            "nether/netherite_armor",
            "adventure/kill_all_mobs",
            "adventure/adventuring_time",
            "adventure/sniper_duel"
            };
    private static final Server server = Data.getInstance().getJavaPlugin().getServer();
    


    public static int getDeathTime(UUID uuid){return server.getPlayer(uuid).getStatistic(Statistic.TIME_SINCE_DEATH)/20;}
    public static int getDeathCount(UUID uuid) {return server.getPlayer(uuid).getStatistic(Statistic.DEATHS);}
    public static int getPlayTime(UUID uuid) {return server.getPlayer(uuid).getStatistic(Statistic.PLAY_ONE_MINUTE)/20;};

    public static int getPower(UUID uuid){
        int timeAlive = (getPlayTime(uuid) - getDeathTime(uuid))/(60);
        int score = server.getPlayer(uuid).getPlayer().getTotalExperience();


        return score + timeAlive;
    }

    public static Component displayPower(UUID uuid){
        return Component.text(getPower(uuid) + " power").color(TextColor.color(0x7FFFD4));
    }

    public static Component cleanDeathTime(int seconds) {
        Component response = Component.text("Died ").color(TextColor.color(0xFFFFFF));
        String[] options = {
                "just now",
                "a few minutes ago",
                "a few hours ago",
                "a few days ago",
                "a few weeks ago",
                "a long time ago..."
        };
        int[] timestamps = {60, 3600, 86400, 604800, 18144000};

        for(int i = 0;i < 4; i++ ){
            if(seconds < timestamps[i]){
                return response.append(Component.text(options[i]));
            }
        }
        return response.append(Component.text(options[5]));
    }




    public static List<Component> StatComponent (UUID uuid) {
        ArrayList<Component> stats = new ArrayList<>();
        //Statistics
        stats.add(Component.text(getDeathCount(uuid) + " Deaths").color(TextColor.color(0xFFFFFF)));
        stats.add(cleanDeathTime(getDeathTime(uuid)));
        stats.add(displayPower(uuid));

        return stats;
    }

    public static float ExpCost(UUID uuid) {
        int power = getPower(uuid);
        int deaths = getDeathCount(uuid);

        return (int)((power) * (0.15 + 0.02*deaths));
    }

//    public static boolean chargeEXP(UUID buyer, UUID resurrect){
//        Player buyerPlayer = server.getPlayer(buyer);
//        OfflinePlayer resurrectPlayer = server.getOfflinePlayer(
//
//        float buyerCurrentExp = buyer.getExp();
//        float Cost = ExpCost(resurrect);
//
//
//        if (buyerCurrentExp < Cost){
//            return false;
//        }
//
//        buyer.setExp(buyerCurrentExp - Cost);
//        return true;
//    }


}

