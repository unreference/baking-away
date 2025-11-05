package jaiz.bakingaway.item.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;

public enum SuspiciousDonutIngredient {
    // TODO: Add constructor with item tags
    SATURATION("saturation", StatusEffects.SATURATION, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP, Items.COOKED_SALMON, Items.COOKED_BEEF, Items.BLUE_ORCHID, Items.DANDELION),
    SPEED("speed", StatusEffects.SPEED, Items.BREEZE_ROD, Items.SUGAR),
    SLOWNESS("slowness", StatusEffects.SLOWNESS, Items.IRON_CHAIN, Items.COBWEB, Items.SOUL_SAND),
    HASTE("haste", StatusEffects.HASTE, Items.GOLDEN_PICKAXE, Items.REDSTONE),
    MINING_FATIGUE("mining_fatigue", StatusEffects.MINING_FATIGUE, Items.PRISMARINE_SHARD, Items.PRISMARINE_CRYSTALS, Items.SPONGE, Items.WET_SPONGE),
    STRENGTH("strength", StatusEffects.STRENGTH, Items.BLAZE_POWDER, Items.IRON_INGOT),
    INSTANT_HEALTH("instant_health", StatusEffects.INSTANT_HEALTH, Items.GLISTERING_MELON_SLICE, Items.TOTEM_OF_UNDYING),
    INSTANT_DAMAGE("instant_damage", StatusEffects.INSTANT_DAMAGE, Items.FERMENTED_SPIDER_EYE, Items.PUFFERFISH, Items.POISONOUS_POTATO),
    JUMP_BOOST("jump_boost", StatusEffects.JUMP_BOOST, Items.RABBIT_FOOT, Items.CORNFLOWER),
    NAUSEA("nausea", StatusEffects.NAUSEA, 220, Items.WIND_CHARGE, Items.CREAKING_HEART),
    REGENERATION("regeneration", StatusEffects.REGENERATION, Items.GHAST_TEAR, Items.NETHER_STAR, Items.OXEYE_DAISY),
    RESISTANCE("resistance", StatusEffects.RESISTANCE, Items.SHIELD, Items.SHULKER_SHELL, Items.TURTLE_SCUTE),
    FIRE_RESISTANCE("fire_resistance", StatusEffects.FIRE_RESISTANCE, Items.MAGMA_CREAM),
    WATER_BREATHING("water_breathing", StatusEffects.WATER_BREATHING, Items.NAUTILUS_SHELL, Items.SEA_PICKLE),
    INVISIBILITY("invisibility", StatusEffects.INVISIBILITY, Items.ENDER_EYE, Items.ENDER_PEARL),
    BLINDNESS("blindness", StatusEffects.BLINDNESS, Items.INK_SAC, Items.OPEN_EYEBLOSSOM, Items.AZURE_BLUET),
    NIGHT_VISION("night_vision", StatusEffects.NIGHT_VISION, Items.GOLDEN_CARROT, Items.POPPY, Items.TORCHFLOWER),
    HUNGER("hunger", StatusEffects.HUNGER, Items.ROTTEN_FLESH, Items.DRIED_KELP),
    WEAKNESS("weakness", StatusEffects.WEAKNESS, Items.RESIN_CLUMP, Items.SPIDER_EYE, Items.WHITE_TULIP, Items.RED_TULIP, Items.PINK_TULIP, Items.ORANGE_TULIP),
    POISON("poison", StatusEffects.POISON, Items.RED_MUSHROOM, Items.LILY_OF_THE_VALLEY),
    WITHER("wither", StatusEffects.WITHER, Items.DRIED_GHAST, Items.WITHER_SKELETON_SKULL, Items.WITHER_ROSE),
    HEALTH_BOOST("health_boost", StatusEffects.HEALTH_BOOST, Items.GOLDEN_APPLE, Items.APPLE),
    ABSORPTION("absorption", StatusEffects.ABSORPTION, Items.ENCHANTED_GOLDEN_APPLE, Items.GOLD_BLOCK),
    GLOWING("glowing", StatusEffects.GLOWING, Items.GLOW_INK_SAC, Items.GLOW_BERRIES, Items.GLOWSTONE),
    LEVITATION("levitation", StatusEffects.LEVITATION, Items.ELYTRA, Items.FIREWORK_ROCKET),
    LUCK("luck", StatusEffects.LUCK, Items.EMERALD, Items.DIAMOND),
    UNLUCK("unluck", StatusEffects.UNLUCK, Items.DEAD_BUSH),
    SLOW_FALLING("slow_falling", StatusEffects.SLOW_FALLING, Items.PHANTOM_MEMBRANE, Items.HAY_BLOCK, Items.WHITE_WOOL),
    CONDUIT_POWER("conduit_power", StatusEffects.CONDUIT_POWER, Items.CONDUIT, Items.TRIDENT),
    DOLPHINS_GRACE("dolphins_grace", StatusEffects.DOLPHINS_GRACE, Items.COD, Items.SALMON, Items.TROPICAL_FISH),
    BAD_OMEN("bad_omen", StatusEffects.BAD_OMEN, Items.CROSSBOW, Items.OMINOUS_BOTTLE),
    HERO_OF_THE_VILLAGE("hero_of_the_village", StatusEffects.HERO_OF_THE_VILLAGE, Items.CAKE, Items.BELL, Items.EMERALD_BLOCK),
    DARKNESS("darkness", StatusEffects.DARKNESS, Items.CLOSED_EYEBLOSSOM, Items.SCULK_SENSOR),
    RAID_OMEN("raid_omen", StatusEffects.RAID_OMEN, Items.OMINOUS_TRIAL_KEY),
    WIND_CHARGED("wind_charged", StatusEffects.WIND_CHARGED, Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, Items.LIGHTNING_ROD),
    WEAVING("weaving", StatusEffects.WEAVING, Items.STRING, Items.WHITE_CARPET),
    OOZING("oozing", StatusEffects.OOZING, Items.SLIME_BALL, Items.SLIME_BLOCK),
    INFESTED("infested", StatusEffects.INFESTED, Items.STONE, Items.INFESTED_STONE);

    private static final int DEFAULT_DURATION = 600;

    private final String name;
    private final int icingColor;
    private final RegistryEntry<StatusEffect> effect;
    private final Item[] items;
    private int minTicks;
    private int maxTicks;
    private int ticks;

    SuspiciousDonutIngredient(String name, RegistryEntry<StatusEffect> effect, Item... items) {
        this.name = name;
        this.effect = effect;
        this.icingColor = effect.value().getColor();
        this.ticks = DEFAULT_DURATION;
        this.items = items;
    }

    SuspiciousDonutIngredient(String name, RegistryEntry<StatusEffect> effect, int ticks, Item... items) {
        this.name = name;
        this.effect = effect;
        this.icingColor = effect.value().getColor();
        this.ticks = ticks;
        this.items = items;
    }

    SuspiciousDonutIngredient(String name, RegistryEntry<StatusEffect> effect, int minTicks, int maxTicks, Item... items) {
        this.name = name;
        this.effect = effect;
        this.icingColor = effect.value().getColor();
        this.minTicks = minTicks;
        this.maxTicks = maxTicks;
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

    public int getMinTicks() {
        return this.minTicks;
    }

    public int getMaxTicks() {
        return this.maxTicks;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public Item[] getItems() {
        return this.items;
    }
}
