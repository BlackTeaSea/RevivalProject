package org.blackteasea.revival;

//Calculates the price of revival based on that dead player's achievements

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Cost {


    public static int getDeathTime(Player player){return player.getStatistic(Statistic.TIME_SINCE_DEATH)/20;}
    public static int getDeathCount(Player player) {return player.getStatistic(Statistic.DEATHS);}

    public static List<Component> StatComponent (Player player) {
        ArrayList<Component> stats = new ArrayList<>();
        stats.add(Component.text(getDeathCount(player)).color(TextColor.color(0xFFFFFF)));
        stats.add(Component.text(getDeathTime(player)).color(TextColor.color(0xFFFFFF)));
        return stats;
    }

    public static List<Component> CostComponent (Player player) {
        ArrayList<Component> costs = new ArrayList<>();
        return costs;
    }









    //TODO: Get item based on achievements
    public static List<ItemStack> deathCost (Player player) {

        Material Cobblestone = Material.COBBLESTONE;
        Material Stone = Material.STONE;
        List<ItemStack> cost = new ArrayList<ItemStack>();

        cost.add(new ItemStack(Cobblestone, getDeathCount(player)));
        cost.add(new ItemStack(Stone, getDeathCount(player)));


        return cost;
    }




}

