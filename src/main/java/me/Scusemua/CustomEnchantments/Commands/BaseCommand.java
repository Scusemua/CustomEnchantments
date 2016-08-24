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

            // Used in conjunction with the CustomEnchantmenets.* permission.
            boolean bypassPermChecks = false;

            // If the player has all ("*") perms for this, don't check.
            if (Main.perms.has(player, "CustomEnchantments.*")) bypassPermChecks = true;

            // Ensure the player has permission before executing the command.
            if (!bypassPermChecks && !(Main.perms.has(player, "CustomEnchants.general"))) {
                player.sendMessage(ChatColor.RED + "ERROR: You are lacking sufficient permissions to execute that command!");
                return true;
            }

            if (strings.length == 0) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Type " + ChatColor.DARK_PURPLE + "/CustomEnchantments help " +
                         ChatColor.LIGHT_PURPLE + "for help!");
                return true;
            }

            // If the user entered /customenchantments add
            if (strings[0].toLowerCase().equals("add")) {
                // Ensure the player has permission before executing the command.
                if (!bypassPermChecks && !(Main.perms.has(player, "CustomEnchants.add"))) {
                    player.sendMessage(ChatColor.RED + "ERROR: You are lacking sufficient permissions to execute that command!");
                    return true;
                }

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

                    // Ensure the given item may be enchanted. If so, enchant it.
                    if (Main.shockwaveEnchantment.canEnchantItem(heldItem)) {
                        Main.shockwaveEnchantment.addCustomEnchant(heldItem, level);
                        player.sendMessage(ChatColor.GOLD + "Your item has successfully been enchanted. " +
                                "Congratulations!");
                        return true;
                    }

                    // If we get to this point, it means nothing was enchanted, meaning the player did not have
                    // the correct item in-hand when they executed the command.
                    player.sendMessage(ChatColor.RED + "ERROR: Invalid item in-hand! Please make sure you're holding " +
                        "the correct type of item for the specified enchantment.");
                }
                else if (strings[1].toLowerCase().equals("speeddemon") ||
                strings[1].toLowerCase().equals("speed demon")) {
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

                    // Ensure the given item may be enchanted. If so, enchant it.
                    if (Main.speedDemonEnchantment.canEnchantItem(heldItem)) {
                        Main.speedDemonEnchantment.addCustomEnchant(heldItem, level);
                        player.sendMessage(ChatColor.GOLD + "Your item has successfully been enchanted. " +
                                "Congratulations!");
                        return true;
                    }

                    // If we get to this point, it means nothing was enchanted, meaning the player did not have
                    // the correct item in-hand when they executed the command.
                    player.sendMessage(ChatColor.RED + "ERROR: Invalid item in-hand! Please make sure you're holding " +
                            "the correct type of item for the specified enchantment.");
                }
                else {
                    player.sendMessage(ChatColor.RED + "ERROR: Unknown enchantment " + ChatColor.DARK_RED + strings[1]);
                }
            }
            // Show the help information pertaining to command syntax.
            else if (strings[0].toLowerCase().equals("help")) {
                // Ensure the player has permission before executing the command.
                if (!bypassPermChecks && !(Main.perms.has(player, "CustomEnchants.help"))) {
                    player.sendMessage(ChatColor.RED + "ERROR: You are lacking sufficient permissions to execute that command!");
                    return true;
                }
                player.sendMessage(ChatColor.GOLD + "Commands: ");
                player.sendMessage(ChatColor.DARK_PURPLE + "/customenchantments add <enchantment_name> <level>");
                player.sendMessage(ChatColor.DARK_PURPLE + "/customenchantments list");
                player.sendMessage("");
            }
            // List the currently available enchantments.
            else if (strings[0].toLowerCase().equals("list")) {
                // Ensure the player has permission before executing the command.
                if (!bypassPermChecks && !(Main.perms.has(player, "CustomEnchants.list"))) {
                    player.sendMessage(ChatColor.RED + "ERROR: You are lacking sufficient permissions to execute that command!");
                    return true;
                }
                player.sendMessage(ChatColor.GOLD + "Current Enchantments: ");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Shockwave <I, II, III>: Unleash MUCH more powerful pickaxe/shovel capabilities.");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Speed Demon <I, II>: Permanent speed boost while wearing enchanted armor.");
                player.sendMessage("");
            }
        }

        return true;
    }
}
