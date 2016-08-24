package me.Scusemua.CustomEnchantments.Listeners;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import me.Scusemua.CustomEnchantments.Core.Main;
import me.Scusemua.CustomEnchantments.Enchantments.Armor.SpeedDemonEnchantment;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/**
 * Created by Benjamin on 8/24/2016.
 */
public class SpeedDemonListener implements Listener {
    private Plugin myPlugin;

    public SpeedDemonListener(Plugin plugin) {
        myPlugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onArmorEquipped(ArmorEquipEvent event) {
        // If we are EQUIPPING a piece of armor...
        if(event.getNewArmorPiece() != null && event.getNewArmorPiece().getType() != Material.AIR) {
            // If the armor piece is of the proper type for the SpeedDemon enchantment...
            if (Main.speedDemonEnchantment.canEnchantItem(event.getNewArmorPiece())) {
                // Check if the armor piece was enchanted with Speed Demon.
                if (event.getNewArmorPiece().hasItemMeta()) {
                    List<String> itemLore = event.getNewArmorPiece().getItemMeta().getLore();
                    // Iterate through each line of the item lore. If the item lore has "Shockwave"
                    // in it, the tool is a "Shockwave"-enchanted tool.
                    for (String s : itemLore) {
                        if (s.contains("Shockwave")) {
                            int level;
                            if (s.contains("II")) level = 2;
                            else level = 1;
                            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, level));
                            return;
                        }
                    }
                }
            }
        } // Else, if we are UN-equipping a piece of armor...
        else if(event.getOldArmorPiece() != null && event.getOldArmorPiece().getType() != Material.AIR) {
            // If the armor piece is of the proper type for the SpeedDemon enchantment...
            if (Main.speedDemonEnchantment.canEnchantItem(event.getOldArmorPiece())) {
                // Check if the armor piece was enchanted with Speed Demon.
                if (event.getNewArmorPiece().hasItemMeta()) {
                    List<String> itemLore = event.getNewArmorPiece().getItemMeta().getLore();
                    // Iterate through each line of the item lore. If the item lore has "Shockwave"
                    // in it, the tool is a "Shockwave"-enchanted tool.
                    for (String s : itemLore) {
                        if (s.contains("Shockwave")) {
                            for (PotionEffect pe : event.getPlayer().getActivePotionEffects()) {
                                if (pe.getType() == PotionEffectType.SPEED && pe.getDuration() >= Integer.MAX_VALUE / 2) {
                                    event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
