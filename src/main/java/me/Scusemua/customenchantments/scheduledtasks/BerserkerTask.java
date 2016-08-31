package me.Scusemua.customenchantments.scheduledtasks;

import me.Scusemua.customenchantments.customweapons.BerserkerWeapon;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BerserkerTask {
    private int ticksBeforeDecrement;

    public static ArrayList<BerserkerWeapon> weapons = new ArrayList<BerserkerWeapon>();

    public BerserkerTask(final Plugin myPlugin) {
        ticksBeforeDecrement = myPlugin.getConfig().getInt("Settings.Enchantments.Berserker.TimeBetweenKillsBeforeDecrement") * 20;

        if (ticksBeforeDecrement <= 9) ticksBeforeDecrement = 10; // Minimum time increment is half a second.

        Bukkit.getScheduler().scheduleSyncRepeatingTask(myPlugin, new Runnable() {
            public void run() {
                long currentTime = System.currentTimeMillis();
                for (BerserkerWeapon bw : weapons) {
                    myPlugin.getLogger().info("Current Server Time: " + currentTime);
                    myPlugin.getLogger().info("Current Weapon's Time: " + bw.getTimeOfLastKill());
                    myPlugin.getLogger().info("Difference: " + (currentTime - bw.getTimeOfLastKill()));
                    if (currentTime - bw.getTimeOfLastKill() >= 5000) {
                        myPlugin.getLogger().info("Decrementing weapon " + bw.getWeaponID() + " to level " +
                                (bw.getCurrentLevel()  - 1));
                        bw.decrementLevel(1);
                    }
                }
            }
        }, 0, ticksBeforeDecrement);
    }

}
