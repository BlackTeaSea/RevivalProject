package org.blackteasea.revival;

//Calculates the price of revival based on that dead player's achievements

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.naming.Name;
import javax.xml.stream.events.Namespace;
import java.util.ArrayList;
import java.util.List;

public class Cost {
    public static String[][] advancementChecks = {
            {"story/mine_stone", "Stone Breaker"},
            {"story/smelt_iron", "Iron Smelter"},
            {"story/mine_diamond", "Gem Hoarder"},
            {"story/enter_the_nether", "Hell Diver"},
            {"story/enter_the_end", "Witness to the End"},
            {"end/kill_dragon", "Dragonslayer"},
            {"adventure/kill_a_mob", "Hunter"},
            {"adventure/trade", "Capitalist"},
            {"adventure/totem_of_undying", "Reincarnated"},

            };


    public static int getDeathTime(Player player){return player.getStatistic(Statistic.TIME_SINCE_DEATH)/20;}
    public static int getDeathCount(Player player) {return player.getStatistic(Statistic.DEATHS);}

    public static AdvancementProgress getAdvancementProgress(Player player, String plugin, String advancementName){
        NamespacedKey namespace = new NamespacedKey(plugin, advancementName);
        Advancement advancement = Data.getInstance().getJavaPlugin().getServer().getAdvancement(namespace);
        return player.getAdvancementProgress(advancement);
    }

    public static boolean hasCompleted(Player player, String advancementName){
        AdvancementProgress toCheck = getAdvancementProgress(player, "minecraft", advancementName);
        if (toCheck.isDone()){
            return true;
        }

        return false;
    }

    public static List<Component> StatComponent (Player player) {
        ArrayList<Component> stats = new ArrayList<>();
        //Statistics
        stats.add(Component.text(getDeathCount(player) + " Deaths").color(TextColor.color(0xFFFFFF)));
        stats.add(Component.text("Died " + getDeathTime(player) + " seconds ago").color(TextColor.color(0xFFFFFF)));

        //Advancements
        for(String[] titles : advancementChecks){
            if(hasCompleted(player, titles[0])){
                stats.add(Component.text(titles[1]).color(TextColor.color(0xFFFDD0)));
            }
        }

        return stats;
    }



}

