package me.Scusemua.customenchantments.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Benjamin on 8/21/2016.
 */
public abstract class CustomEnchantment extends Enchantment {

    protected String name;        // Name of the custom enchantment.
    protected String description; // Description of the enchantment.
    protected int maxLevel;       // Maximum level for the custom enchantment.
    protected int minLevel;       // Minimum level for the custom enchantment.
    protected int startingLevel;  // Default level of the custom enchantment.
    protected int idNumber;       // ID number of the enchantment.

    public CustomEnchantment(int id) {
        super(id);
        idNumber = id;
    }

    public abstract ItemStack addCustomEnchant(ItemStack item, int level);

    public abstract EnchantmentTarget getItemTarget();
    public abstract boolean conflictsWith(Enchantment other);
    public abstract boolean canEnchantItem(ItemStack item);

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

    public int getIdNum() {
        return idNumber;
    }

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

    /**
     * Returns a string of the CustomEnchantment's name + its description.
     */
    public String displayString() {
        return name + " " + description;
    }

    /*
    Potential enchantments:
        Pickaxe - AutoSmelt
        Fishing Pole - AutoCook
        Sword - Wolf Tamer (chance to spawn wolf)
        Axe - Thor (Chance of lightning + explosion)
        Sword/Axe - Mage (chance to fire fireball in direction of swing)
        Sword/Axe - Necromancer (chance to summon zombies/skeletons to fight for you)
        Bow - Shoot arrows faster
        Sword/Bow - Poison
     */
}
