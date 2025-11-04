package jaiz.bakingaway.datagen;

import jaiz.bakingaway.block.ModBlocks;
import jaiz.bakingaway.block.custom.SweetDoughBlock;
import jaiz.bakingaway.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static void registerSweetDough(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.SWEET_DOUGH).with(
                BlockStateVariantMap.models(SweetDoughBlock.DOUGH_STAGE).generate(stage -> {
                    final Identifier id = ModelIds.getBlockModelId(ModBlocks.SWEET_DOUGH).withSuffixedPath("_stage" + stage);
                    return BlockStateModelGenerator.createWeightedVariant(id);
                })
        ));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.MILL);
        registerSweetDough(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FLOUR, Models.GENERATED);
        itemModelGenerator.register(ModItems.MILL, Models.GENERATED);
        itemModelGenerator.register(ModItems.BURNT_DONUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RISEN_SWEET_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICED_SPOON, Models.HANDHELD);
        itemModelGenerator.register(ModItems.UNCOOKED_DONUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.UNICED_DONUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPOON, Models.HANDHELD);
    }
}
