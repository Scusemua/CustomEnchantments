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
 * Custom Enchantment which increases arrow shot speed.
 */
public class ShotspeedEnchantment extends CustomEnchantment {

    public ShotspeedEnchantment(int id) {
        super(id);
        name = "SHOTSPEED";
        maxLevel = 3;
        minLevel = 1;
        startingLevel = 1;
        description = "<I, II, III>: Increases the velocity of arrows shot out of a bow.";
    }

    public ItemStack addCustomEnchant(ItemStack item, int level) {
        item.addUnsafeEnchantment(Main.shotspeedEnchantment, level);
        item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        ItemMeta meta = item.getItemMeta();

        if (level > maxLevel) level = maxLevel;

        // Set the item's lore (description when hovering over the item with the mouse).
        meta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Shotspeed " + getRomanNumeral(level)));
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
