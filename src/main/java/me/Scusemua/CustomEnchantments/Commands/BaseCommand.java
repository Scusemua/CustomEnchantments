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

            if (strings.length == 0) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Type " + ChatColor.DARK_PURPLE + "/CustomEnchantments help " +
                         ChatColor.LIGHT_PURPLE + "for help!");
                return true;
            }

            // If the user entered /customenchantments add
            if (strings[0].toLowerCase().equals("add")) {
                if (strings.length <= 1) {
                    player.sendMessage(ChatColor.RED + "ERROR: You need to specify an enchantment.");
                    return true;
                }
                if (strings[1].toLowerCase().equals("shockwave")) {
                    if (strings.length <= 2) {
                        player.sendMessage(ChatColor.RED + "ERROR: You need to specify an enchantment level.");
                        return true;
                    }
                    int level = -1;
                    try {
                        level = Integer.parseInt(strings[2]);
                    } catch (NumberFormatException nfe) {
                        player.sendMessage(ChatColor.RED + "ERROR: The specified enchantment level must be an integer.");
                        return true;
                    }
                    ItemStack heldItem = player.getInventory().getItemInMainHand();

                    // If the player is holding a pickaxe...
                    for (Material m : MaterialTypes.PICKAXES) {
                        if (heldItem.getType() == m) {
                            Main.shockwaveEnchantment.addCustomEnchant(heldItem, level);
                            player.sendMessage(ChatColor.GOLD + "Your pickaxe has successfully been enchanted. " +
                                    "Congratulations!");
                            return true;
                        }
                    }

                    // If the player is holding a shovel...
                    for (Material m : MaterialTypes.SHOVELS) {
                        if (heldItem.getType() == m) {
                            Main.shockwaveEnchantment.addCustomEnchant(heldItem, level);
                            player.sendMessage(ChatColor.GOLD + "Your shovel has successfully been enchanted. " +
                                    "Congratulations!");
                            return true;
                        }
                    }

                    // If we get to this point, it means nothing was enchanted, meaning the player did not have
                    // the correct item in-hand when they executed the command.
                    player.sendMessage(ChatColor.RED + "ERROR: Invalid item in-hand! Please make sure you're holding " +
                        "the correct type of item for the specified enchantment.");
                } else {
                    player.sendMessage(ChatColor.RED + "ERROR: Unknown enchantment " + ChatColor.DARK_RED + strings[1]);
                }
            }
            else if (strings[0].toLowerCase().equals("help")) {
                player.sendMessage(ChatColor.GOLD + "Commands: ");
                player.sendMessage(ChatColor.DARK_PURPLE + "/customenchantments add <enchantment_name> <level>");
                player.sendMessage(ChatColor.DARK_PURPLE + "/customenchantments list");
                player.sendMessage("");
            }
            else if (strings[0].toLowerCase().equals("list")) {
                player.sendMessage(ChatColor.GOLD + "Current Enchantments: ");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Shockwave <I, II, III>: Unleash MUCH more powerful pickaxe/shovel capabilities.");
                player.sendMessage("");
            }
        }

        return true;
    }
}
