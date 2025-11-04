package jaiz.bakingaway.item.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;

public enum SuspiciousDonutIngredent {
    BLINDNESS("blindness", 1120039, StatusEffects.BLINDNESS, Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE),
    FIRE_RESISTANCE("fire_resistance", 16740403, StatusEffects.FIRE_RESISTANCE, Items.MAGMA_CREAM, Items.BLAZE_POWDER),
    HASTE("haste", 16768307, StatusEffects.HASTE, Items.GOLD_INGOT, Items.GOLD_NUGGET, Items.SUGAR_CANE),
    INFESTED("infested", 11121068, StatusEffects.INFESTED, Items.CLAY_BALL),
    JUMP_BOOST("jump_boost", 8379712, StatusEffects.JUMP_BOOST, Items.PHANTOM_MEMBRANE, Items.RABBIT_FOOT),
    LEVITATION("levitation", 13434873, StatusEffects.LEVITATION, Items.SHULKER_SHELL, Items.ENDER_EYE),
    MINING_FATIGUE("mining_fatigue", 2519907, StatusEffects.MINING_FATIGUE, Items.PRISMARINE_SHARD, Items.PRISMARINE_CRYSTALS),
    NAUSEA("nausea", 13496937, StatusEffects.NAUSEA, Items.ROTTEN_FLESH, Items.BONE_MEAL, Items.BEEF, Items.CHICKEN, Items.RABBIT, Items.MUTTON, Items.PORKCHOP),
    OOZING("oozing", 5960527, StatusEffects.OOZING, Items.SLIME_BALL),
    REGENERATION("regeneration", 16734039, StatusEffects.REGENERATION, Items.GOLDEN_APPLE, Items.GLOW_BERRIES, Items.SWEET_BERRIES, Items.HONEY_BOTTLE),
    RESISTANCE("resistance", 6717839, StatusEffects.RESISTANCE, Items.IRON_INGOT, Items.RAW_IRON, Items.RAW_COPPER, Items.COPPER_INGOT, Items.DRIED_KELP, Items.EGG, Items.BROWN_EGG, Items.BLUE_EGG),
    SLOW_FALLING("slow_falling", 15859395, StatusEffects.SLOW_FALLING, Items.CHORUS_FRUIT, Items.ENDER_PEARL, Items.POPPED_CHORUS_FRUIT),
    SLOWNESS("slowness", 9001377, StatusEffects.SLOWNESS, Items.HONEYCOMB, Items.RESIN_CLUMP),
    SPEED("speed", 5362412, StatusEffects.SPEED, Items.SUGAR, Items.FEATHER),
    STRENGTH("strength", 12472400, StatusEffects.STRENGTH, Items.COOKED_BEEF, Items.COOKED_PORKCHOP, Items.COOKED_CHICKEN, Items.COOKED_RABBIT, Items.COOKED_MUTTON, Items.GOLDEN_CARROT),
    WATER_BREATHING("water_breathing", 9089535, StatusEffects.WATER_BREATHING, Items.PUFFERFISH, Items.TROPICAL_FISH, Items.COD, Items.SALMON),
    WEAVING("weaving", 14201285, StatusEffects.WEAVING, Items.COBWEB, Items.STRING);

    private final String name;
    private final int icingColor;
    private final RegistryEntry<StatusEffect> effect;
    private final Item[] items;

    SuspiciousDonutIngredent(String name, int icingColor, RegistryEntry<StatusEffect> effect, Item... items) {
        this.name = name;
        this.icingColor = icingColor;
        this.effect = effect;
        this.items = items;
    }

    public String getName() {
        return this.name;
    }

    public int getIcingColor() {
        return this.icingColor;
    }

    public RegistryEntry<StatusEffect> getEffect() {
        return this.effect;
    }

    public int getDuration() {
        return 560;
    }

    public Item[] getItems() {
        return this.items;
    }
}
