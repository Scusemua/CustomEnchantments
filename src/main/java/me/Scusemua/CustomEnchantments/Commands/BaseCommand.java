package me.Scusemua.CustomEnchantments.Commands;

import me.Scusemua.CustomEnchantments.Core.Main;
import me.Scusemua.CustomEnchantments.Utility.MaterialTypes;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * Created by Benjamin on 8/21/2016.
 */
public class BaseCommand implements CommandExecutor {
    private Plugin myPlugin;

    public BaseCommand(Plugin plugin) {
        myPlugin = plugin;
    }


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            myPlugin.getLogger().info("ERROR: You may only use that command as a player.");
        }

        if (commandSender instanceof Player) {
            Player player = (Player)commandSender;

            // If the user entered /customenchantments add
            if (strings[1].toLowerCase().equals("add")) {
                if (strings[2].toLowerCase().equals("shockwave")) {
                    ItemStack heldItem = player.getInventory().getItemInMainHand();

                    // If the player is holding a pickaxe...
                    for (Material m : MaterialTypes.PICKAXES) {
                        if (heldItem.getType() == m) {
                            Main.shockwaveEnchantment.addCustomEnchant(heldItem, 1);
                            player.sendMessage(ChatColor.GOLD + "Your pickaxe has successfully been enchanted. " +
                                    "Congratulations!");
                        }
                    }

                    // If the player is holding a shovel...
                    for (Material m : MaterialTypes.SHOVELS) {
                        if (heldItem.getType() == m) {
                            Main.shockwaveEnchantment.addCustomEnchant(heldItem, 1);
                            player.sendMessage(ChatColor.GOLD + "Your shovel has successfully been enchanted. " +
                                    "Congratulations!");
                        }
                    }
                }
            }
        }

        return true;
    }
}
