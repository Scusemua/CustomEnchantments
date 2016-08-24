package me.Scusemua.CustomEnchantments.Core;

import me.Scusemua.CustomEnchantments.Commands.BaseCommand;
import me.Scusemua.CustomEnchantments.Enchantments.Armor.SpeedDemonEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.CustomEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.Tools.ShockwaveEnchantment;
import me.Scusemua.CustomEnchantments.Listeners.ShockwaveListener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import me.Scusemua.CustomEnchantments.Listeners.SpeedDemonListener;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Benjamin on 8/21/2016.
 */
public class Main extends JavaPlugin {
    public static CustomEnchantment shockwaveEnchantment = new ShockwaveEnchantment(80);
    public static Permission perms = null;
    public static Chat chat = null;
    public static CustomEnchantment speedDemonEnchantment = new SpeedDemonEnchantment(81);

    @Override
    public void onEnable() {
        getLogger().info("Enabling CustomEnchantments.");
        try {
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            @SuppressWarnings("unchecked")
            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

            if(byId.containsKey(80))
                byId.remove(80);

            if(byName.containsKey("Shockwave"))
                byName.remove("Shockwave");

            if(byId.containsKey(81))
                byId.remove(81);

            if(byName.containsKey("Speed Demon"))
                byName.remove("Speed Demon");
        } catch (Exception ignored) { }

        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(shockwaveEnchantment);
                Enchantment.registerEnchantment(speedDemonEnchantment);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Commands
        this.getCommand("customenchantments").setExecutor(new BaseCommand(this));

        // Events
        getServer().getPluginManager().registerEvents(new ShockwaveListener(this), this);

        // Set up chat & permissions.
        setupPermissions();
        setupChat();
        getServer().getPluginManager().registerEvents(new SpeedDemonListener(this), this);
    }

    @Override
    public void onDisable() {

    }

    /**
     * Set up the permissions integration with Vault.
     * @return boolean indicating successful/unsuccessful integration with Vault.
     */
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    /**
     * Set up the chat integration with Vault.
     * @return boolean indicating successful/unsuccessful integration with Vault.
     */
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
}
