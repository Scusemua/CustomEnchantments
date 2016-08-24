package me.Scusemua.CustomEnchantments.Enchantments;

import me.Scusemua.CustomEnchantments.Core.Main;
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

    private String name;        // Name of the custom enchantment.
    private int maxLevel;       // Maximum level of the custom enchantment.
    private int minLevel;       // Minimum level of the custom enchantment.
    private int startingLevel;  // Default level of the custom enchantment.
    private int id;             // ID number for the custom enchantment.

    public ShockwaveEnchantment(int id) {
        super(id);

        name = "Shockwave";
        maxLevel = 3;
        minLevel = 1;
        startingLevel = 1;
        this.id = id;
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

    /** @deprecated */
    @Deprecated
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

        if (level > maxLevel) level = maxLevel;

        // Determine whether or not the item is a shovel or a pickaxe.
        // Depending on what the item is, set the displayName String to the proper value.
        String displayName;
        if (MaterialTypes.SHOVELS.contains(item.getType())) {
            displayName = "Shovel";
        } else {
            displayName = "Pickaxe";
        }

        // Set the item's display name and lore (description when hovering over the item with the mouse).
        meta.setDisplayName("Shockwave " + getRomanNumeral(level) + " " + displayName);
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Shockwave " + getRomanNumeral(level)));
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Returns the roman numeral version of the given number.
     */
    public String getRomanNumeral(int num) {
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
