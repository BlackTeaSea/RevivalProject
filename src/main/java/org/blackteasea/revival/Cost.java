package org.blackteasea.revival;

//Calculates the price of revival based on that dead player's achievements

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.naming.Name;
import javax.xml.stream.events.Namespace;
import java.util.ArrayList;
import java.util.List;

public class Cost {
    public static String[] advancementChecks = {
            "story/mine_stone",
            "story/smelt_iron",
            "story/iron_tools",
            "story/form_obsidian",
            "story/mine_diamond",
            "story/enter_the_nether",
            "story/shiny_gear",
            "story/enter_the_end",
            "nether/obtain_blaze_rod",
            "end/dragon_breath",
            "end/kill_dragon",
            "end/find_end_city",
            "adventure/kill_a_mob",
            "adventure/kill_all_mobs"
            };


    public static int getDeathTime(Player player){return player.getStatistic(Statistic.TIME_SINCE_DEATH)/20;}
    public static int getDeathCount(Player player) {return player.getStatistic(Statistic.DEATHS);}

    public static List<Component> StatComponent (Player player) {
        ArrayList<Component> stats = new ArrayList<>();
        stats.add(Component.text(getDeathCount(player) + " Deaths").color(TextColor.color(0xFFFFFF)));
        stats.add(Component.text("Died " + getDeathTime(player) + " seconds ago").color(TextColor.color(0xFFFFFF)));

        return stats;
    }

    public static boolean checkRequirements(Player user, Player dead){
        for(ItemStack itemStack : Cost.deathCost(dead)) {
            if(!user.getInventory().containsAtLeast(itemStack, getDeathCount(dead))){


                return false;
            }
        }

        return true;
    }

    public static List<Component> CostComponent (Player player) {

        //cross-check the

        ArrayList<Component> costs = new ArrayList<>();
        return costs;
    }

    public static int test (){


        return 0;
    }







    //TODO: Get item based on achievements

    public static List<ItemStack> deathCost (Player player) {

        for (String advancement : advancementChecks){

        }
        List<ItemStack> cost = new ArrayList<ItemStack>();


        return cost;
    }




}

