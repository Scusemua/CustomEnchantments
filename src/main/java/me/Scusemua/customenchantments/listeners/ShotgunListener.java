package me.Scusemua.customenchantments.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Created by Benjamin on 8/24/2016.
 */
public class ShotgunListener implements Listener {
    private Plugin myPlugin;
    private Random RNG = new Random();

    public ShotgunListener(Plugin plugin) {
        myPlugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onShootBow(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        Float speed = event.getForce() * 2f;

        if (entity.getType().equals(EntityType.PLAYER)) {
            Player p = (Player) entity;
            ItemStack itemInHand = p.getInventory().getItemInMainHand();

            if (itemInHand.getType() == Material.BOW) {
                if (itemInHand.hasItemMeta()) {
                    ItemMeta meta = itemInHand.getItemMeta();
                    for (String s : meta.getLore()) {
                        if (s.contains("Shotgun")) {
                            int level;
                            if (s.contains("X")) level = 10;
                            else if (s.contains("IX")) level = 9;
                            else if (s.contains("VIII")) level = 8;
                            else if (s.contains("VII")) level = 7;
                            else if (s.contains("VI")) level = 6;
                            else if (s.contains(" V")) level = 5;    // Notice the leading space.
                            else if (s.contains("IV")) level = 4;
                            else if (s.contains("III")) level = 3;
                            else if (s.contains("II")) level = 2;
                            else if (s.contains("I")) level = 1;
                            else level = 1;

                            // Ensure the player has enough ammunition to fire.
                            if (!(p.getInventory().contains(Material.ARROW, level * 2))) {
                                p.sendMessage(ChatColor.RED + "WARNING: You need " + level * 2 + " arrows to fire a shotgun blast.");
                                return;
                            }

                            // Get the player's direction in the form of a vector.
                            Vector playerDirection = p.getLocation().getDirection();

                            // Fire the correct number of arrows with a randomly generated offset, simulating a shotgun.
                            for (int i = 1; i < level * 2; i++) {
                                double xOffset = 0.001 + (0.25 - 0.001) * RNG.nextDouble();
                                double yOffset = 0.001 + (0.25 - 0.001) * RNG.nextDouble();
                                double zOffset = 0.001 + (0.25 - 0.001) * RNG.nextDouble();

                                int negative = RNG.nextInt(2);
                                if (negative == 0) xOffset *= -1;

                                negative = RNG.nextInt(2);
                                if (negative == 0) yOffset *= -1;

                                negative = RNG.nextInt(2);
                                if (negative == 0) zOffset *= -1;

                                Vector arrowVelocity = new Vector((playerDirection.getX() + xOffset) * speed,
                                        (playerDirection.getY() + yOffset) * speed,
                                        (playerDirection.getZ() + zOffset) * speed);
                                p.launchProjectile(Arrow.class, arrowVelocity);
                            }

                            // playSound(location, sound, volume, pitch)
                            p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 5, 1);

                            // Remove the arrows from the player's inventory.
                            p.getInventory().removeItem(new ItemStack(Material.ARROW, level * 2));
                            return;
                        }
                    }
                }
            }
        }
    }
}
