package me.Scusemua.customenchantments.enchantments.weapons;

import me.Scusemua.customenchantments.core.Main;
import me.Scusemua.customenchantments.enchantments.CustomEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Custom Enchantment which fires an additional arrow for each level.
 */
public class ShotgunEnchantment extends CustomEnchantment {

    public ShotgunEnchantment(int id) {
        super(id);
        name = "SHOTGUN";
        maxLevel = 10;
        minLevel = 1;
        startingLevel = 1;
        description = "<I - X>: Fires an extra arrow for each level of the enchantment.";
    }

    public ItemStack addCustomEnchant(ItemStack item, int level) {
        item.addUnsafeEnchantment(Main.shotgunEnchantment, level);
        item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
        item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
        ItemMeta meta = item.getItemMeta();

        if (level > maxLevel) level = maxLevel;

        // Set the item's lore (description when hovering over the item with the mouse).
        meta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Shotgun " + getRomanNumeral(level)));
        item.setItemMeta(meta);

        return item;
    }

    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    /**
     * Returns true if the specified item is a bow, otherwise return false.
     */
    public boolean canEnchantItem(ItemStack item) {
        if (item.getType() == Material.BOW) return true;
        return false;
    }
}
