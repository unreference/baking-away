package jaiz.bakingaway.block;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.block.custom.MillBlock;
import jaiz.bakingaway.block.custom.SweetDoughBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    //Main Bits

    public static final IntProperty DOUGH_STAGE = IntProperty.of("stage", 0, 3);

    public static final Block MILL = registerBlock("mill",
            MillBlock::new, Block.Settings.copy(Blocks.STONE));

    public static final Block SWEET_DOUGH = registerBlock("sweet_dough",
            SweetDoughBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().ticksRandomly().strength(1.0F, 2.0F));


    public static <B extends Block> B registerBlock(String name, Function<AbstractBlock.Settings, B> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(BakingAway.MOD_ID, name));
        B block = factory.apply(settings.registryKey(key));

        return Registry.register(Registries.BLOCK, key, block);
    }

    public static void registerModBlocks() {
        BakingAway.LOGGER.info("Registering ModBlocks for " + BakingAway.MOD_ID);
    }

}
