package jaiz.bakingaway;

import jaiz.bakingaway.block.ModBlocks;
import jaiz.bakingaway.item.ModItemGroups;
import jaiz.bakingaway.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BakingAway implements ModInitializer {
    public static final String MOD_ID = "bakingaway";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {

        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
    }
}