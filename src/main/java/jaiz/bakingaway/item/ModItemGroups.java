package jaiz.bakingaway.item;

import jaiz.bakingaway.BakingAway;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BAKE_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(BakingAway.MOD_ID, "baking"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.bakingaway.baking"))
                    .icon(() -> new ItemStack(Items.BREAD.asItem())).entries((displayContext, entries) -> {
                        // things go here
                        entries.add(ModItems.FLOUR);
                        entries.add(ModItems.SWEET_DOUGH);
                        entries.add(ModItems.RISEN_SWEET_DOUGH);
                        entries.add(ModItems.UNCOOKED_DONUT);
                        entries.add(ModItems.UNICED_DONUT);
                        entries.add(ModItems.DONUT);
                        entries.add(ModItems.BURNT_DONUT);
                        entries.add(ModItems.SPOON);
                        entries.add(ModItems.ICED_SPOON);
                        entries.add(ModItems.MILL);
                        entries.add(ModItems.PIE);
                        entries.add(ModItems.SUSHI);
                        entries.add(ModItems.BAKED_COOKIE);
                    }).build());

    public static void registerItemGroups() {
    }
}
