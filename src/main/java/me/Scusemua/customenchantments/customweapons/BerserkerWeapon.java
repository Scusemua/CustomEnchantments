package me.Scusemua.customenchantments.customweapons;

import me.Scusemua.customenchantments.core.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BerserkerWeapon {
    private ItemStack baseItem; // The base ItemStack for the weapon (sword/axe).
    private int currentLevel;   // The current Sharpness enchantment on the weapon.
    private boolean recentKill; // Whether or not the weapon has gotten a kill in the past five seconds.
    private String weaponID;

    public BerserkerWeapon(ItemStack baseItem) {
        this.baseItem = baseItem;
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);
        currentLevel = 0;

        weaponID = "%" + Main.generateString(new Random(), Main.possibleIDChars, 8);
    }

    public boolean getRecentKill() {
        return recentKill;
    }

    public void setRecentKill(boolean b) {
        recentKill = b;
    }

    /**
     * Decreases the current Enchantment level on the weapon.
     */
    public void decrementLevel(int amount) {
        for (int i = 0; i < amount; i++) {
            currentLevel -= 1;

            // Update the weapon's enchantment.
            baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);

            if (currentLevel < 0) {
                currentLevel = 0; // Minimum enchantment is zero.
                return; // If the level is zero, we don't want to add the enchantment.
            }
        }

        baseItem.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, currentLevel);
    }

    /**
     * Increases the current Enchantment level by the specified amount.
     */
    public void increaseCurrentLevel(int amount) {
        currentLevel += amount;

        if (currentLevel > 10) currentLevel = 10; // Maximum Sharpness enchantment is ten.

        // Update the weapon's enchantment.
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);
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
}
