package org.blackteasea.revival;


import java.util.ArrayList;
import java.util.List;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.hover.content.Item;

public class Recipe {
    
    public static ShapedRecipe totemRecipe(){

        ItemStack respawnerTotem = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta totemMeta = respawnerTotem.getItemMeta();
        final Component totemName = Component.text("Totem of Revival").color(TextColor.color(0x5D3FD3));

        totemMeta.displayName(totemName);

        List<Component> infolore = new ArrayList<Component>();



        final Component revivalName = Component.text("Drop in a villager well")
                .color(TextColor.color(0x2708A0))
                .decoration(TextDecoration.BOLD, true);
        infolore.add(revivalName);

        respawnerTotem.setItemMeta(totemMeta);

        respawnerTotem.lore(infolore);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Data.getInstance().getJavaPlugin(), "Totem"), respawnerTotem);

        recipe.shape (" X ", 
                      " X ",
                      " X ");
        recipe.setIngredient('X', Material.NETHER_STAR);

        return recipe;
    }

}
