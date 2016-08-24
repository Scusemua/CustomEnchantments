package me.Scusemua.CustomEnchantments.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import java.util.List;

import static com.sun.jmx.snmp.ThreadContext.contains;

/**
 * Created by Benjamin on 8/23/2016.
 */
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

        myPlugin.getLogger().info("onBlockBreak event triggered.");

        if (itemInMainHand.hasItemMeta()) {
            List<String> itemLore = itemInMainHand.getItemMeta().getLore();
            for (String s : itemLore) {
                if (s.contains("Shockwave I")) {
                    player.sendMessage(ChatColor.GOLD + "BlockBreakEvent triggered with Shockwave I shovel/pickaxe!");
                }
            }
        }
    }
}
