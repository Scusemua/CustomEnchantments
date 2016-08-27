package me.Scusemua.CustomEnchantments.ScheduledTasks;

import me.Scusemua.CustomEnchantments.CustomWeapons.BerserkerWeapon;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Benjamin on 8/26/2016.
 */
public class BerserkerTask {
    private final int TICKS_BEFORE_DECREMENT = 100; // One second is twenty ticks, so one hundred ticks is five seconds.

    public static HashMap<ItemStack, BerserkerWeapon> weaponsHashMap = new HashMap<ItemStack, BerserkerWeapon>();
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
        }, 0, 100);
    }

}
