package jaiz.bakingaway.item;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.block.ModBlocks;
import jaiz.bakingaway.item.custom.IcedSpoonItem;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final Item FLOUR = registerItem("flour", Item::new, new Item.Settings());

    public static final Item DONUT = registerItem("donut", Item::new, new Item.Settings()
            .food(ModFoodComponents.DONUT)
            .component(DataComponentTypes.DYED_COLOR, new DyedColorComponent(16675227)));

    public static final Item SUSHI = registerItem("sushi", Item::new, new Item.Settings()
            .food(ModFoodComponents.EXTRA_FOOD)
            .component(DataComponentTypes.DYED_COLOR, new DyedColorComponent(15719874)));
    public static final Item PIE = registerItem("pie", Item::new, new Item.Settings()
            .food(ModFoodComponents.EXTRA_FOOD)
            .component(DataComponentTypes.DYED_COLOR, new DyedColorComponent(14210737)));
    public static final Item BAKED_COOKIE = registerItem("baked_cookie", Item::new, new Item.Settings()
            .food(ModFoodComponents.EXTRA_FOOD)
            .component(DataComponentTypes.DYED_COLOR, new DyedColorComponent(14210737)));

    public static final Item BURNT_DONUT = registerItem("burnt_donut", Item::new, new Item.Settings()
            .food(ModFoodComponents.BURNT_DONUT,
                    ModConsumableComponents.BURNT_DONUT));


    public static final Item UNCOOKED_DONUT =
            registerItem("uncooked_donut", Item::new, new Item.Settings().food(ModFoodComponents.UNCOOKED_DONUT));
    public static final Item SPOON =
            registerItem("spoon", Item::new, new Item.Settings());

    public static final Item ICED_SPOON =
            registerItem("iced_spoon", IcedSpoonItem::new, new Item.Settings().food(ModFoodComponents.ICING));

    public static final Item RISEN_SWEET_DOUGH =
            registerItem("risen_sweet_dough", Item::new, new Item.Settings().food(ModFoodComponents.SWEET_DOUGH));
    public static final Item UNICED_DONUT =
            registerItem("uniced_donut", Item::new, new Item.Settings().food(ModFoodComponents.UNICED_DONUT));

    public static final Item SWEET_DOUGH = registerBlockItem("sweet_dough", ModBlocks.SWEET_DOUGH);
    public static final Item MILL = registerBlockItem("mill", ModBlocks.MILL);

    public static BlockItem registerBlockItem(String name, Block block) {
        return registerItem(name, settings -> new BlockItem(block, settings), new Item.Settings().useBlockPrefixedTranslationKey());
    }

    public static <I extends Item> I registerItem(String name, Function<Item.Settings, I> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BakingAway.MOD_ID, name));
        I item = factory.apply(settings.registryKey(key));

        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    public static Item registerItem(String id, Function<Item.Settings, Item> factory) {
        return registerItem(id, factory, new Item.Settings());
    }

    public static void registerModItems() {
        BakingAway.LOGGER.info("registering Mod Items for " + BakingAway.MOD_ID);
    }
}

