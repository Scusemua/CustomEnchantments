package me.Scusemua.customenchantments.enchantments.tools;

import me.Scusemua.customenchantments.core.Main;
import me.Scusemua.customenchantments.enchantments.CustomEnchantment;
import me.Scusemua.customenchantments.utilities.MaterialTypes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Custom Enchantment which causes pickaxes to mine 3x1 areas instead of 1x1.
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

        name = "SHOCKWAVE";
        maxLevel = 3;
        minLevel = 1;
        startingLevel = 1;
        description = "<I, II, III>: Unleash MUCH more powerful pickaxe/shovel capabilities.";
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

        // Set the item's lore (description when hovering over the item with the mouse).
        meta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Shockwave " + getRomanNumeral(level)));
        item.setItemMeta(meta);

        return item;
    }
}
