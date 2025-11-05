package jaiz.bakingaway.registry;

import jaiz.bakingaway.BakingAway;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> BURNABLE_DONUTS = of("burnable_donuts");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(BakingAway.MOD_ID, id));
    }
}
