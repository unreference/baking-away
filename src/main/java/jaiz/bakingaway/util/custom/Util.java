package jaiz.bakingaway.util.custom;

import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;

public class Util {
    public static String itemName(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem()).getPath();
    }

    public static int hex(String hex) {
        String clean = hex;

        if (clean.startsWith("#")) {
            clean = clean.substring(1);
        }

        return Integer.parseInt(clean, 16);
    }
}
