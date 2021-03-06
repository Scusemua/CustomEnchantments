package me.Scusemua.customenchantments.listeners;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import me.Scusemua.customenchantments.core.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

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
                        if (s.contains("Speed Demon")) {
                            int level;
                            if (s.contains("II")) level = 2;
                            else level = 1;
                            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, level - 1));
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
                if (event.getOldArmorPiece().hasItemMeta()) {
                    List<String> itemLore = event.getOldArmorPiece().getItemMeta().getLore();
                    // Iterate through each line of the item lore. If the item lore has "Speed Demon"
                    // in it, the tool is a "Speed Demon"-enchanted tool.
                    for (String s : itemLore) {
                        if (s.contains("Speed Demon")) {
                            for (PotionEffect pe : event.getPlayer().getActivePotionEffects()) {
                                if (pe.getType().getName().contains("SPEED") && pe.getDuration() >= Integer.MAX_VALUE / 2) {
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
