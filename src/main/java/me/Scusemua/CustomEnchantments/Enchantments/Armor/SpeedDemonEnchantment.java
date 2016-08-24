package me.Scusemua.CustomEnchantments.Enchantments.Armor;

import me.Scusemua.CustomEnchantments.Core.Main;
import me.Scusemua.CustomEnchantments.Enchantments.CustomEnchantment;
import me.Scusemua.CustomEnchantments.Utility.MaterialTypes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SpeedDemonEnchantment extends CustomEnchantment {

    public SpeedDemonEnchantment(int id) {
        super(id);

        name = "Speed Demon";
        maxLevel = 2;
        minLevel = 1;
        startingLevel = 1;
    }


    public ItemStack addCustomEnchant(ItemStack item, int level) {
        item.addUnsafeEnchantment(Main.speedDemonEnchantment, level);
        ItemMeta meta = item.getItemMeta();

        if (level > maxLevel) level = maxLevel;

        // Determine whether or not the item is a shovel or a pickaxe.
        // Depending on what the item is, set the displayName String to the proper value.
        String displayName = "Boots";

        // Set the item's display name and lore (description when hovering over the item with the mouse).
        meta.setDisplayName("Speed Demon " + getRomanNumeral(level) + " " + displayName);
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Speed Demon " + getRomanNumeral(level)));
        item.setItemMeta(meta);

        return item;
    }

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getStartLevel() {
        return startingLevel;
    }

    public int getMinLevel() {
        return minLevel;
    }

    /**
     * Returns the EnchantmentTarget for this custom enchantment.
     */
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_FEET;
    }

    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    /**
     * Returns true if the specified item is any kind of boot.
     */
    public boolean canEnchantItem(ItemStack item) {
        if (item.getType() == Material.LEATHER_BOOTS ||
                item.getType() == Material.GOLD_BOOTS ||
                item.getType() == Material.IRON_BOOTS ||
                item.getType() == Material.DIAMOND_BOOTS ||
                item.getType() == Material.CHAINMAIL_BOOTS) {
            return true;
        }
        return false;
    }
}
