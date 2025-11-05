package jaiz.bakingaway.datagen;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.item.ModItems;
import jaiz.bakingaway.item.custom.SuspiciousDonutIngredient;
import jaiz.bakingaway.registry.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        for (SuspiciousDonutIngredient ingredient : SuspiciousDonutIngredient.values()) {
            final TagKey<Item> key = TagKey.of(RegistryKeys.ITEM, Identifier.of(BakingAway.MOD_ID, "suspicious_donut/" + ingredient.getName() + "_ingredients"));
            this.valueLookupBuilder(key).add(ingredient.getItems());
        }

        this.valueLookupBuilder(ModItemTags.BURNABLE_DONUTS).add(ModItems.UNICED_DONUT, ModItems.DONUT, ModItems.SUSPICIOUS_DONUT);
    }
}
