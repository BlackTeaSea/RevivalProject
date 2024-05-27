package org.blackteasea.revival.Items;


import java.util.ArrayList;
import java.util.List;
import org.blackteasea.revival.Data;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

/**
 * This class is responsible for creating a custom recipe in the game.
 */
public class Recipe {

    /**
     * This method creates a custom shaped recipe for a "Totem of Revival".
     * The totem is represented by a Nether Star item with a custom name and lore.
     * The recipe is shaped like a cross, with a Totem of Undying in the center,
     * Netherite Scrap at the top and bottom, Rotten Flesh and Blaze Powder on the left and right,
     * and an Ender Eye at the bottom.
     *
     * @return ShapedRecipe object representing the custom recipe
     */
    public static ShapedRecipe totemRecipe(){
        // Create a new ItemStack representing the totem
        ItemStack respawnTotem = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta totemMeta = respawnTotem.getItemMeta();

        // Set the display name of the totem
        final Component totemName = Component.text("Totem of Revival").color(TextColor.color(0x5D3FD3));
        totemMeta.displayName(totemName);

        // Create a list for the lore of the totem
        List<Component> infoLore = new ArrayList<>();

        // Add the lore to the list
        final Component revivalName = Component.text("Drop in a villager well")
                .color(TextColor.color(0x2708A0))
                .decoration(TextDecoration.BOLD, true);
        infoLore.add(revivalName);

        // Set the lore of the totem
        respawnTotem.setItemMeta(totemMeta);
        respawnTotem.lore(infoLore);

        // Create a new ShapedRecipe for the totem
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Data.getInstance().getJavaPlugin(), "Totem"), respawnTotem);

        // Set the shape of the recipe
        recipe.shape   ("NTN",
                        "RXR",
                        "NBN");
        // Set the ingredients of the recipe
        recipe.setIngredient('X', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('N', Material.NETHERITE_SCRAP);
        recipe.setIngredient('R', Material.ROTTEN_FLESH);
        recipe.setIngredient('T', Material.BLAZE_POWDER);
        recipe.setIngredient('B', Material.ENDER_EYE);

        // Return the custom recipe
        return recipe;
    }
}
