package me.Scusemua.CustomEnchantments.Enchantments.Tools;

import me.Scusemua.CustomEnchantments.Core.Main;
import me.Scusemua.CustomEnchantments.Enchantments.CustomEnchantment;
import me.Scusemua.CustomEnchantments.Utility.MaterialTypes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Benjamin on 8/21/2016.
 */
public class ShockwaveEnchantment extends CustomEnchantment {

    public static List<Material> LevelOneShovelMaterials = Arrays.asList(Material.DIRT,
            Material.SAND,
            Material.SOUL_SAND,
            Material.GRASS,
            Material.MYCEL,
            Material.CLAY,
            Material.GRAVEL);

    public static List<Material> LevelOnePickaxeMaterials = Arrays.asList(Material.STONE,
            Material.NETHERRACK,
            Material.NETHER_BRICK,
            Material.SANDSTONE,
            Material.RED_SANDSTONE,
            Material.COBBLESTONE);

    public static List<Material> LevelThreePickaxeMaterials = Arrays.asList(Material.DIAMOND_ORE,
            Material.COAL_ORE,
            Material.EMERALD_ORE,
            Material.GLOWING_REDSTONE_ORE,
            Material.GOLD_ORE,
            Material.IRON_ORE,
            Material.REDSTONE_ORE,
            Material.QUARTZ_ORE,
            Material.LAPIS_ORE,
            Material.OBSIDIAN);

    public ShockwaveEnchantment(int id) {
        super(id);

        name = "Shockwave";
        maxLevel = 3;
        minLevel = 1;
        startingLevel = 1;
    }

    @Override
    public String getName() {
        return name;
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
        else if (MaterialTypes.PICKAXES.contains(itemStack.getType())) return true;

        return false;
    }

    /**
     * Add the Shockwave enchantment to the given item.
     */
    public ItemStack addCustomEnchant(ItemStack item, int level) {
        item.addUnsafeEnchantment(Main.shockwaveEnchantment, level);
        ItemMeta meta = item.getItemMeta();

        if (level > maxLevel) level = maxLevel;

        // Determine whether or not the item is a shovel or a pickaxe.
        // Depending on what the item is, set the displayName String to the proper value.
        String displayName;
        if (MaterialTypes.SHOVELS.contains(item.getType())) {
            displayName = "Shovel";
        } else {
            displayName = "Pickaxe";
        }

        // Set the item's lore (description when hovering over the item with the mouse).
        meta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Shockwave " + getRomanNumeral(level)));
        item.setItemMeta(meta);

        return item;
    }
}
