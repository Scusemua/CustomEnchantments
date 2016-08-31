package me.Scusemua.customenchantments.scheduledtasks;

import me.Scusemua.customenchantments.customweapons.BerserkerWeapon;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BerserkerTask {
    private final int TICKS_BEFORE_DECREMENT = 100; // One second is twenty ticks, so one hundred ticks is five seconds.

    public static ArrayList<BerserkerWeapon> weapons = new ArrayList<BerserkerWeapon>();

    public BerserkerTask(Plugin myPlugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(myPlugin, new Runnable() {
            public void run() {
                for (BerserkerWeapon bw : weapons) {
                    if (!bw.getRecentKill()) {
                        bw.decrementLevel(1);
                    }
                }
            }
        }, 0, myPlugin.getConfig().getInt("Settings.enchantments.Berserker.TimeBetweenKillsBeforeDecrement") * 20);
    }

}
