package org.blackteasea.revival;

//Calculates the price of revival based on that dead player's achievements

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Cost {


    public static int getDeathTime(Player player){return player.getStatistic(Statistic.TIME_SINCE_DEATH)/20;}
    public static String displayDeathTime(Player player){return "Last died " + getDeathTime(player) + " seconds ago";}

    public static int getDeathCount(Player player) {return player.getStatistic(Statistic.DEATHS);}
    public static String displayDeathCount(Player player){return  getDeathCount(player) + " deaths";}







    //TODO: Get item based on achievements
    public List<ItemStack> deathCost (Player player) {

        Material Cobblestone = Material.COBBLESTONE;
        Material Stone = Material.STONE;
        List<ItemStack> cost = new ArrayList<ItemStack>();

        cost.add(new ItemStack(Cobblestone, getDeathCount(player)));
        cost.add(new ItemStack(Stone, getDeathCount(player)));


        return cost;
    }




}

