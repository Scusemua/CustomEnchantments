package me.Scusemua.CustomEnchantments.Utility;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.EnumSet;

/**
 * Created by Benjamin on 8/26/2016.
 */
public class EntityTypes {
    public static EnumSet<EntityType> generalMobs = EnumSet.of(EntityType.BLAZE, EntityType.ZOMBIE, EntityType.PIG_ZOMBIE,
            EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.WITCH, EntityType.WITHER, EntityType.GHAST,
            EntityType.CREEPER, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.SILVERFISH, EntityType.ENDERMITE,
            EntityType.WOLF, EntityType.GIANT, EntityType.GUARDIAN, EntityType.IRON_GOLEM);
}
