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

/**
 * Custom Enchantment which applies Speed I or Speed II depending on level when wearing enchanted boots.
 */
public class SpeedDemonEnchantment extends CustomEnchantment {

    public SpeedDemonEnchantment(int id) {
        super(id);

        name = "SPEED_DEMON";
        maxLevel = 2;
        minLevel = 1;
        startingLevel = 1;
        description = "<I, II>: Permanent speed boost while wearing enchanted armor.";
    }


    public ItemStack addCustomEnchant(ItemStack item, int level) {
        item.addUnsafeEnchantment(Main.speedDemonEnchantment, level);
        ItemMeta meta = item.getItemMeta();

        if (level > maxLevel) level = maxLevel;

        // Set the item's lore (description when hovering over the item with the mouse).
        meta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Speed Demon " + getRomanNumeral(level)));
        item.setItemMeta(meta);

        return item;
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
