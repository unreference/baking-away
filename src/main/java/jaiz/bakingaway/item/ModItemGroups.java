package jaiz.bakingaway.item;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.item.custom.SuspiciousDonutIngredent;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

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
                        addSuspiciousDonuts(entries);
                        entries.add(ModItems.BURNT_DONUT);
                        entries.add(ModItems.SPOON);
                        entries.add(ModItems.ICED_SPOON);
                        entries.add(ModItems.MILL);
                        entries.add(ModItems.PIE);
                        entries.add(ModItems.SUSHI);
                        entries.add(ModItems.BAKED_COOKIE);
                    }).build());

    private static void addSuspiciousDonuts(ItemGroup.Entries entries) {
        for (SuspiciousDonutIngredent ingredient : SuspiciousDonutIngredent.values()) {
            final ItemStack donut = new ItemStack(ModItems.SUSPICIOUS_DONUT);
            final SuspiciousStewEffectsComponent.StewEffect effect = new SuspiciousStewEffectsComponent.StewEffect(ingredient.getEffect(), ingredient.getDuration());
            final SuspiciousStewEffectsComponent effects = new SuspiciousStewEffectsComponent(List.of(effect));
            final DyedColorComponent icing = new DyedColorComponent(ingredient.getIcingColor());

            donut.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, effects);
            donut.set(DataComponentTypes.DYED_COLOR, icing);
            entries.add(donut);
        }
    }

    public static void registerItemGroups() {
    }
}
