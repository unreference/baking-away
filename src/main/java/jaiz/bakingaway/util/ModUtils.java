package jaiz.bakingaway.util;

import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;

public class ModUtils {
    public static String getItemName(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem()).getPath();
    }
}
