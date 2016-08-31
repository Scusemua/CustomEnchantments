package me.Scusemua.customenchantments.listeners;

import me.Scusemua.customenchantments.customweapons.BerserkerWeapon;
import me.Scusemua.customenchantments.scheduledtasks.BerserkerTask;
import me.Scusemua.customenchantments.utilities.EntityTypes;
import org.bukkit.EntityEffect;
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

                                    for (BerserkerWeapon bw : BerserkerTask.weapons) {
                                        if (bw.getWeaponID().equals(itemLore.get(itemLore.size() - 1))) {
                                            bw.increaseCurrentLevel(1);

                                            bw.setTimeOfLastKill(System.currentTimeMillis());

                                            return;
                                        }
                                    }

                                    // Create a new BerserkerWeapon since one does not exist for this item.
                                    BerserkerWeapon bw = new BerserkerWeapon(itemInMainHand,
                                            itemLore.get(itemLore.size() - 1));

                                    bw.increaseCurrentLevel(1);

                                    // If the weapon wasn't in the collection already, add it for future use.
                                    BerserkerTask.weapons.add(bw);

                                    bw.setTimeOfLastKill(System.currentTimeMillis());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
