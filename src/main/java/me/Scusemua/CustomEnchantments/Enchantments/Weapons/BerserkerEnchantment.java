package me.Scusemua.CustomEnchantments.Enchantments.Weapons;

import me.Scusemua.CustomEnchantments.Core.Main;
import me.Scusemua.CustomEnchantments.CustomWeapons.BerserkerWeapon;
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
 * Created by Benjamin on 8/26/2016.
 */
public class BerserkerEnchantment extends CustomEnchantment {

    public BerserkerEnchantment(int id) {
        super(id);
        name = "BerserkerEnchantment";
        maxLevel = 1;
        minLevel = 1;
        startingLevel = 1;
        description = "<I>: Increased damage for each consecutive kill. After five kills within a short amount of time, go into Berserk Mode.";
    }

    public ItemStack addCustomEnchant(ItemStack item, int level) {
        item.addUnsafeEnchantment(Main.shotgunEnchantment, level);
        ItemMeta meta = item.getItemMeta();

        if (level > maxLevel) level = maxLevel;

        // Set the item's lore (description when hovering over the item with the mouse).
        meta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Berserker " + getRomanNumeral(level)));
        item.setItemMeta(meta);

        BerserkerWeapon newWeapon = new BerserkerWeapon(item, 0);

        return item;
    }

    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    /**
     * Returns true if the item is a sword or an axe, otherwise returns false.
     */
    public boolean canEnchantItem(ItemStack item) {
        for (Material m : MaterialTypes.SWORDS) {
            if (item.getType() == m) return true;
        }

        for (Material m : MaterialTypes.AXES) {
            if (item.getType() == m) return true;
        }

        return false;
    }
}
