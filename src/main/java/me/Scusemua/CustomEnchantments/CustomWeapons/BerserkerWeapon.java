package me.Scusemua.CustomEnchantments.CustomWeapons;

import me.Scusemua.CustomEnchantments.ScheduledTasks.BerserkerTask;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Benjamin on 8/26/2016.
 */
public class BerserkerWeapon extends ItemStack {
    private ItemStack baseItem; // The base ItemStack for the weapon (sword/axe).
    private int currentLevel;   // The current Sharpness enchantment on the weapon.
    private boolean recentKill; // Whether or not the weapon has gotten a kill in the past five seconds.

    public BerserkerWeapon(ItemStack baseItem, int currentLevel) {
        this.baseItem = baseItem;
        this.currentLevel = currentLevel;

        // Add a reference to this to the weapons ArrayList<BerserkerWeapon> within BerserkerTask.
        BerserkerTask.weapons.add(this);

        // Add a reference to this to the HashMap<ItemStack, BerserkerWeapon> within BerserkerTask.
        BerserkerTask.weaponsHashMap.put(baseItem, this);
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
        currentLevel -= amount;

        if (currentLevel < 0) currentLevel = 0; // Minimum enchantment is zero.

        // Update the weapon's enchantment.
        baseItem.removeEnchantment(Enchantment.DAMAGE_ALL);
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
}
