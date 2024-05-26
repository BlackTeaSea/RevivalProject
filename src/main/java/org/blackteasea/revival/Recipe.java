package org.blackteasea.revival;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

//this class makes recipe for totem of revival
public class Recipe {
    //represents a shaped crafting recipe
    public static ShapedRecipe totemRecipe(){

        ItemStack respawnerTotem = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta totemMeta = respawnerTotem.getItemMeta();
        final Component totemName = Component.text("Totem of Revival").color(TextColor.color(0x5D3FD3));

        totemMeta.displayName(totemName);

        List<Component> infolore = new ArrayList<>();

        final Component revivalName = Component.text("Drop in a villager well")
                .color(TextColor.color(0x2708A0))
                .decoration(TextDecoration.BOLD, true);
        infolore.add(revivalName);

        respawnerTotem.setItemMeta(totemMeta);

        respawnerTotem.lore(infolore);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Data.getInstance().getJavaPlugin(), "Totem"), respawnerTotem);

        recipe.shape ("NTN",
                "RXR",
                "NBN");
        recipe.setIngredient('X', Material.END_CRYSTAL);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('R', Material.ROTTEN_FLESH);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('B', Material.EXPERIENCE_BOTTLE);

        return recipe;
    }

    // public static ShapedRecipe compassRecipe(){}



}
