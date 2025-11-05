package jaiz.bakingaway.item;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.item.custom.SuspiciousDonutIngredient;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Set;

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
        final Set<ItemStack> donuts = ItemStackSet.create();

        for (SuspiciousDonutIngredient ingredient : SuspiciousDonutIngredient.values()) {
            final ItemStack donut = new ItemStack(ModItems.SUSPICIOUS_DONUT);
            final SuspiciousStewEffectsComponent.StewEffect effect = new SuspiciousStewEffectsComponent.StewEffect(
                    ingredient.getEffect(),
                    ingredient.getTicks()
            );

            donut.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffectsComponent(List.of(effect)));
            donut.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(ingredient.getIcingColor()));
            donuts.add(donut);
        }

        entries.addAll(donuts);
    }

    public static void register() {
    }
}
