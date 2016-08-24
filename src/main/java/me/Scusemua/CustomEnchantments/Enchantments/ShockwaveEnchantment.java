package me.Scusemua.CustomEnchantments.Enchantments;

import me.Scusemua.CustomEnchantments.Core.Main;
import me.Scusemua.CustomEnchantments.Utility.MaterialTypes;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Benjamin on 8/21/2016.
 */
public class ShockwaveEnchantment extends CustomEnchantment {
    private String name;
    private int maxLevel;
    private int minLevel;
    private int startingLevel;
    private int id;

    public ShockwaveEnchantment(int id) {
        super(id);

        name = "Shockwave";
        maxLevel = 1;
        minLevel = 1;
        startingLevel = 1;
        this.id = id;
    }

    @Override
    public String getName() {
        return "Shockwave";
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return startingLevel;
    }

    @Override
    public int getMinLevel() {
        return minLevel;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    /**
     * Returns true if the item in question is a pickaxe or a shovel.
     */
    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        if (MaterialTypes.SHOVELS.contains(itemStack.getType())) return true;
        if (MaterialTypes.PICKAXES.contains(itemStack.getType())) return true;

        return false;
    }

    /**
     * Add the Shockwave enchantment to the given item.
     */
    public ItemStack addCustomEnchant(ItemStack item, int level) {
        item.addUnsafeEnchantment(Main.shockwaveEnchantment, 1);
        ItemMeta meta = item.getItemMeta();

        // Determine whether or not the item is a shovel or a pickaxe.
        // Depending on what the item is, set the displayName String to the proper value.
        String displayName;
        if (MaterialTypes.SHOVELS.contains(item.getType())) {
            displayName = "Shovel";
        } else {
            displayName = "Pickaxe";
        }

        // Set the item's display name and lore (description when hovering over the item with the mouse).
        meta.setDisplayName("Shockwave I " + displayName);
        meta.setLore(Arrays.asList(ChatColor.GOLD + "Shockwave I"));
        item.setItemMeta(meta);
        return item;
    }

    public String a() {
        return "enchantment." + this.name;
    }

    public String c(int i) {
        return "enchantment.level." + i;
    }
}
