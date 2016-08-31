package me.Scusemua.customenchantments.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
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

public class ShotspeedListener implements Listener {
    private Plugin myPlugin;

    public ShotspeedListener(Plugin plugin) {
        myPlugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onShootBow(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        Float speed = event.getForce();
        Entity arrow = event.getProjectile();

        if (entity.getType().equals(EntityType.PLAYER)) {
            Player p = (Player) entity;
            ItemStack itemInHand = p.getInventory().getItemInMainHand();
            if (itemInHand.getType() == Material.BOW) {
                if (itemInHand.hasItemMeta()) {
                    ItemMeta meta = itemInHand.getItemMeta();
                    for (String s : meta.getLore()) {
                        if (s.contains("Shotspeed")) {
                            int level;
                            if (s.contains("III")) level = 3;
                            else if (s.contains("II")) level = 2;
                            else level = 1;

                            Vector vector = p.getLocation().getDirection();

                            // Increase the arrow's velocity.
                            arrow.setVelocity(new Vector(vector.getX() * speed * (level * 3),
                                    vector.getY() * speed * (level * 3),
                                    vector.getZ() * speed * (level * 3)));

                            // playSound(location, sound, volume, pitch)
                            if (level >= 3) p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 5, 14);

                            return;
                        }
                    }
                }
            }
        }
    }
}
