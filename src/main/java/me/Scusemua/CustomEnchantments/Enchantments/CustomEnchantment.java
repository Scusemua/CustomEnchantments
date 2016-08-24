package me.Scusemua.CustomEnchantments.Enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Benjamin on 8/21/2016.
 */
public abstract class CustomEnchantment extends Enchantment {

    protected String name;        // Name of the custom enchantment.
    protected int maxLevel;       // Maximum level for the custom enchantment.
    protected int minLevel;       // Minimum level for the custom enchantment.
    protected int startingLevel;  // Default level of the custom enchantment.

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

    /**
     * Returns the roman numeral version of the given number.
     */
    protected String getRomanNumeral(int num) {
        switch(num) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            default:
                return "ERR_IMPROPER_VAL";
        }
    }

}
