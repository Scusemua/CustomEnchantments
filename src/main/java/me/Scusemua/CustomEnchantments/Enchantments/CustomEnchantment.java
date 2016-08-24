package me.Scusemua.CustomEnchantments.Enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Benjamin on 8/21/2016.
 */
public abstract class CustomEnchantment extends Enchantment {

    private String name;
    private int maxLevel;
    private int minLevel;

    public CustomEnchantment(int id) {
        super(id);
    }

    public abstract ItemStack addCustomEnchant(ItemStack item, int level);

    public abstract String getName();
    public abstract int getMaxLevel();
    public abstract int getMinLevel();
    public abstract EnchantmentTarget getItemTarget();
    public abstract boolean conflictsWith(Enchantment other);
    public abstract boolean canEnchantItem(ItemStack item);

}
