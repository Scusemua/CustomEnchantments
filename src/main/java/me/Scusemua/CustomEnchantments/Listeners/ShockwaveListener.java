package me.Scusemua.CustomEnchantments.Listeners;

import me.Scusemua.CustomEnchantments.Enchantments.Tools.ShockwaveEnchantment;
import me.Scusemua.CustomEnchantments.Utility.MaterialTypes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class ShockwaveListener implements Listener {
    private Plugin myPlugin;

    public ShockwaveListener(Plugin plugin) {
        myPlugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack itemInMainHand = inventory.getItemInMainHand();

        // If the player is holding an item that has lore of some sort...
        if (itemInMainHand.hasItemMeta()) {
            List<String> itemLore = itemInMainHand.getItemMeta().getLore();
            // Iterate through each line of the item lore. If the item lore has "Shockwave"
            // in it, the tool is a "Shockwave"-enchanted tool.
            for (String s : itemLore) {
                if (s.contains("Shockwave")) {
                    int level;
                    if (s.contains("III")) level = 3;
                    else if (s.contains("II")) level = 2;
                    else level = 1;
                    String direction = getCardinalDirection(player);
                    Location blockLocation = event.getBlock().getLocation();
                    World currentWorld = blockLocation.getWorld();
                    double x = blockLocation.getX();
                    double y = blockLocation.getY();
                    double z = blockLocation.getZ();
                    List<Location> locationsToCheck;

                    // If the player was facing East or West, these are the
                    // directions that should be checked when breaking a block.
                    if (direction.substring(0, 1).equals("W") || direction.substring(0, 1).equals("E")) {
                        locationsToCheck =
                                Arrays.asList(new Location(currentWorld, x-1, y+1, z),
                                        new Location(currentWorld, x, y+1, z),
                                        new Location(currentWorld, x+1, y+1, z),
                                        new Location(currentWorld, x-1, y, z),
                                        new Location(currentWorld, x+1, y, z),
                                        new Location(currentWorld, x-1, y-1, z),
                                        new Location(currentWorld, x, y-1, z),
                                        new Location(currentWorld, x+1, y-1, z));
                    }
                    // If the player was facing North or South, these are the
                    // directions that should be checked when breaking a block.
                    else {
                        locationsToCheck =
                                Arrays.asList(new Location(currentWorld, x, y+1, z-1),
                                        new Location(currentWorld, x, y+1, z),
                                        new Location(currentWorld, x, y+1, z+1),
                                        new Location(currentWorld, x, y, z-1),
                                        new Location(currentWorld, x, y, z+1),
                                        new Location(currentWorld, x, y-1, z-1),
                                        new Location(currentWorld, x, y-1, z),
                                        new Location(currentWorld, x, y-1, z+1));
                    }

                    // Now we need to determine if the block was above or below the player. If it was,
                    // we handle things a little differently. Different locations need to be checked.
                    // Determine if the player hit a block above or below them by checking their pitch.
                    // A pitch of 90 is straight down, while a pitch of -90 is straight up.
                    boolean lookingUpOrDown = false;
                    if (player.getLocation().getPitch() >= 65f || player.getLocation().getPitch() <= -65f) {
                        lookingUpOrDown = true;
                    }

                    // If the block was at a different height level, change the locations we're checking.
                    if (lookingUpOrDown) {
                        locationsToCheck =
                                Arrays.asList(new Location(currentWorld, x+1, y, z+1),
                                        new Location(currentWorld, x, y, z+1),
                                        new Location(currentWorld, x-1, y, z+1),
                                        new Location(currentWorld, x+1, y, z),
                                        new Location(currentWorld, x-1, y, z),
                                        new Location(currentWorld, x+1, y, z-1),
                                        new Location(currentWorld, x, y, z-1),
                                        new Location(currentWorld, x-1, y, z-1));
                    }

                    // If the tool is a pickaxe, do pickaxe-related stuff. If it's a shovel, do shovel-related stuff.
                    if (MaterialTypes.PICKAXES.contains(itemInMainHand.getType())) {
                        if (level == 3) { // Shockwave III
                            for (Location l : locationsToCheck) {
                                // Check for both Level 1 and Level 3 materials.
                                if (ShockwaveEnchantment.LevelOnePickaxeMaterials.contains(l.getBlock().getType()) ||
                                ShockwaveEnchantment.LevelThreePickaxeMaterials.contains(l.getBlock().getType())) {
                                    // Since it's Shockwave III, have every block location drop it's proper drop.
                                    l.getBlock().breakNaturally();
                                }
                            }
                            blockLocation.getBlock().breakNaturally();
                        }
                        else if (level == 2) { // Shockwave II
                            for (Location l : locationsToCheck) {
                                // Check for just Level 1 materials.
                                if (ShockwaveEnchantment.LevelOnePickaxeMaterials.contains(l.getBlock().getType())) {
                                    // Since it's Shockwave II, have every block location drop it's proper drop.
                                    l.getBlock().breakNaturally();
                                }
                            }
                        }
                        else { // Shockwave I
                            for (Location l : locationsToCheck) {
                                // Check for just Level 1 materials.
                                if (ShockwaveEnchantment.LevelOnePickaxeMaterials.contains(l.getBlock().getType())) {
                                    // Since it's Shockwave I, have only the mined location drop it's proper drop.
                                    // The other locations are simply set to AIR, effectively not dropping the proper item.
                                    l.getBlock().setType(Material.AIR);
                                }
                            }
                            blockLocation.getBlock().breakNaturally();
                        }
                    } else { // It's a shovel...
                        if (level >= 2) { // Shockwave II & Shockwave III
                            for (Location l : locationsToCheck) {
                                // Check the Level One shovel materials since there's only one tier of shovel materials.
                                if (ShockwaveEnchantment.LevelOneShovelMaterials.contains(l.getBlock().getType())) {
                                    // Since we're in Shockwave 2+ (2 or 3), have every block drop it's proper item.
                                    l.getBlock().breakNaturally();
                                }
                            }
                        }
                        else {  // Shockwave I
                            for (Location l : locationsToCheck) {
                                // Check the Level One shovel materials since there's only one tier of shovel materials.
                                if (ShockwaveEnchantment.LevelOneShovelMaterials.contains(l.getBlock().getType())) {
                                    // Since it's Shockwave I, only the specifically "mined" block
                                    // will drop an item. The other blocks will simply be set to air.
                                    l.getBlock().setType(Material.AIR);
                                }
                            }
                            blockLocation.getBlock().breakNaturally();
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the direction a given player is facing.
     */
    public String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
}
