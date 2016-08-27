package me.Scusemua.CustomEnchantments.Core;

import me.Scusemua.CustomEnchantments.Commands.BaseCommand;
import me.Scusemua.CustomEnchantments.Enchantments.Armor.SpeedDemonEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.CustomEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.Tools.ShockwaveEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.Weapons.BerserkerEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.Weapons.ShotgunEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.Weapons.ShotspeedEnchantment;
import me.Scusemua.CustomEnchantments.Listeners.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
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
    public static CustomEnchantment speedDemonEnchantment = new SpeedDemonEnchantment(81);
    public static CustomEnchantment shotspeedEnchantment = new ShotspeedEnchantment(82);
    public static CustomEnchantment shotgunEnchantment = new ShotgunEnchantment(83);
    public static CustomEnchantment berserkerEnchantment = new BerserkerEnchantment(84);

    public static CustomEnchantment[] CustomEnchantments = new CustomEnchantment[]{shockwaveEnchantment, speedDemonEnchantment,
    shotgunEnchantment, shotspeedEnchantment, berserkerEnchantment};

    public static Permission perms = null;
    public static Chat chat = null;

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

            for (CustomEnchantment ce : CustomEnchantments) {
                if (byId.containsKey(ce.getIdNum()))
                    byId.remove(ce.getIdNum());

                if (byName.containsKey(ce.getName()))
                    byName.remove(ce.getName());
            }
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

        // Set up chat & permissions.
        setupPermissions();
        setupChat();

        // Events
        getServer().getPluginManager().registerEvents(new ShockwaveListener(this), this);
        getServer().getPluginManager().registerEvents(new SpeedDemonListener(this), this);
        getServer().getPluginManager().registerEvents(new ShotspeedListener(this), this);
        getServer().getPluginManager().registerEvents(new ShotgunListener(this), this);
        getServer().getPluginManager().registerEvents(new BerserkerListener(this), this);
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
