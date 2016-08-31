package me.Scusemua.customenchantments.customweapons;

import me.Scusemua.customenchantments.core.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BerserkerWeapon {
    private ItemStack baseItem;     // The base ItemStack for the weapon (sword/axe).
    private int currentLevel;       // The current Sharpness enchantment on the weapon.
    private long timeOfLastKill;    // The time that this weapon was last used to kill a mob.
    private String weaponID;        // Randomly generated string used as a weapon id.

    /**
     * Constructor that accepts the ItemStack that represents the baseItem of this BerserkerWeapon.
     */
    public BerserkerWeapon(ItemStack baseItem) {
        this.baseItem = baseItem;
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);
        currentLevel = 0;

        weaponID = "%" + Main.generateString(new Random(), Main.possibleIDChars, 8);
    }

    /**
     * Constructor that accepts the ItemStack that represents the baseItem of this BerserkerWeapon
     * as well as a string representing the item's ID (the string is randomly generated).
     */
    public BerserkerWeapon(ItemStack baseItem, String weaponID) {
        this.baseItem = baseItem;
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);
        currentLevel = 0;
        this.weaponID = weaponID;
    }

    /**
     * Decreases the current Enchantment level on the weapon.
     */
    public void decrementLevel(int amount) {
        // Update the weapon's enchantment.
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);

        currentLevel -= amount;

        if (currentLevel < 0) {
            currentLevel = 0;
            return;
        }

        baseItem.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, currentLevel);
    }

    /**
     * Increases the current Enchantment level by the specified amount.
     */
    public void increaseCurrentLevel(int amount) {
        // Update the weapon's enchantment.
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);

        currentLevel += amount;

        if (currentLevel > 10) currentLevel = 10; // Maximum Sharpness enchantment is ten.

        baseItem.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, currentLevel);
    }

    /**
     * Removes Sharpness (DAMAGE_ALL) from the BerserkerWeapon's baseItem ItemStack.
     */
    public void clearSharpnessEnchantment() {
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);
    }

    /**
     * Returns this BerserkerWeapon's baseitem.
     */
    public ItemStack getBaseItem() {
        return baseItem;
    }

    /**
     * Modifies the BerserkerWeapon's base item reference.
     * @param newBaseItem the new base item ItemStack object.
     */
    public void setBaseItem(ItemStack newBaseItem) {
        baseItem = newBaseItem;
    }

    /**
     * Return this BerserkerWeapon instance's weaponID value.
     */
    public String getWeaponID() {
        return weaponID;
    }

    /**
     * Returns the time that this weapon was last used to kill a mob.
     */
    public long getTimeOfLastKill() {
        return timeOfLastKill;
    }

    /**
     * Sets the time that this weapon was last used to kill a mob.
     */
    public void setTimeOfLastKill(long newTime) {
        timeOfLastKill = newTime;
    }

    /**
     * Returns the current level, and therefore Sharpness (DAMAGE_ALL) enchantment of the weapon.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }
}
