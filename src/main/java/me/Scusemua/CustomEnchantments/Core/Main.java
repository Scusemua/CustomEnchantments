package me.Scusemua.CustomEnchantments.Core;

import me.Scusemua.CustomEnchantments.Commands.BaseCommand;
import me.Scusemua.CustomEnchantments.Enchantments.CustomEnchantment;
import me.Scusemua.CustomEnchantments.Enchantments.ShockwaveEnchantment;
import me.Scusemua.CustomEnchantments.Listeners.ShockwaveListener;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Benjamin on 8/21/2016.
 */
public class Main extends JavaPlugin {

    public static CustomEnchantment shockwaveEnchantment = new ShockwaveEnchantment(80);

    @Override
    public void onEnable() {
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
    }

    @Override
    public void onDisable() {

    }
}
