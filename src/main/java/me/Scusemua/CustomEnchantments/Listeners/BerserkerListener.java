package me.Scusemua.CustomEnchantments.Listeners;

import me.Scusemua.CustomEnchantments.ScheduledTasks.BerserkerTask;
import me.Scusemua.CustomEnchantments.Utility.EntityTypes;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class BerserkerListener implements Listener {
    private Plugin myPlugin;

    public BerserkerListener(Plugin plugin) {
        myPlugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(EntityDeathEvent event) {
        // If it was a player that killed the LivingEntity...
        if (event.getEntity().getKiller() != null) {
            Player p = event.getEntity().getKiller();
            ItemStack itemInMainHand = p.getInventory().getItemInMainHand();

            // Ensure the killed entity was a mob that is either blatantly hostile or has the potential to be hostile.
            for (EntityType et : EntityTypes.generalMobs) {
                if (event.getEntity().getType() == et) {
                    // Check if the item the player is using has metadata.
                    if (itemInMainHand.hasItemMeta()) {
                       // Check if the item the player is using has lore.
                        if (itemInMainHand.getItemMeta().hasLore()) {
                            List<String> itemLore = itemInMainHand.getItemMeta().getLore();
                            for (String s : itemLore) {
                                // See if the lore contains Berserker (if so, it is enchanted with Berserker).
                                if (s.contains("Berserker")) {
                                    // Get the weapon's current Sharpness level.
                                    int currentBerserkerLevel = itemInMainHand.getEnchantmentLevel(Enchantment.DAMAGE_ALL);

                                    // Increase the item's damage output by upping the BerserkerLevel (sharpness enchantment).
                                    itemInMainHand.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, currentBerserkerLevel + 1);

                                    if (BerserkerTask.weaponsHashMap.get(itemInMainHand) != null) {
                                        BerserkerTask.weaponsHashMap.get(itemInMainHand).setRecentKill(true);
                                        BerserkerTask.weaponsHashMap.get(itemInMainHand).increaseCurrentLevel(1);
                                    } else {
                                        myPlugin.getLogger().info("Null reference for some reason...");
                                    }
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
