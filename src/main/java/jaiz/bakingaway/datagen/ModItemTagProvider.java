package jaiz.bakingaway.datagen;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.item.custom.SuspiciousDonutIngredent;
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
        for (SuspiciousDonutIngredent ingredient : SuspiciousDonutIngredent.values()) {
            final TagKey<Item> key = TagKey.of(RegistryKeys.ITEM, Identifier.of(BakingAway.MOD_ID, "suspicious_donut_" + ingredient.getName()));
            this.valueLookupBuilder(key).add(ingredient.getItems());
        }
    }
}
