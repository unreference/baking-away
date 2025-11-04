package jaiz.bakingaway.datagen;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.block.ModBlocks;
import jaiz.bakingaway.block.custom.SweetDoughBlock;
import jaiz.bakingaway.item.ModItems;
import jaiz.bakingaway.item.custom.SuspiciousDonutIngredent;
import jaiz.bakingaway.util.ModUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.tint.DyeTintSource;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static void registerSweetDough(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.SWEET_DOUGH).with(
                BlockStateVariantMap.models(SweetDoughBlock.STAGE).generate(stage -> {
                    final Identifier id = ModelIds.getBlockModelId(ModBlocks.SWEET_DOUGH).withSuffixedPath("_stage" + stage);
                    return BlockStateModelGenerator.createWeightedVariant(id);
                })
        ));
    }

    private static void registerSuspiciousDonuts(ItemModelGenerator generator) {
        final Item donut = ModItems.SUSPICIOUS_DONUT;
        final String baseDonutName = ModUtils.getItemName(ModItems.DONUT);

        final Identifier layer0 = Identifier.of(BakingAway.MOD_ID, "item/" + baseDonutName);
        final Identifier layer1 = Identifier.of(BakingAway.MOD_ID, "item/" + baseDonutName + "_icing");
        final Identifier layer2 = Identifier.of(BakingAway.MOD_ID, "item/" + baseDonutName + "_icing_glint");

        final Identifier model = Models.GENERATED_THREE_LAYERS.upload(donut, TextureMap.layered(layer0, layer1, layer2), generator.modelCollector);
        final int icing = SuspiciousDonutIngredent.values()[0].getIcingColor();
        final TintSource noTint = ItemModels.constantTintSource(-1);

        generator.output.accept(donut, ItemModels.tinted(model, noTint, new DyeTintSource(icing), noTint));
    }

    private static void registerDonut(ItemModelGenerator generator) {
        final Item donut = ModItems.DONUT;
        final String name = ModUtils.getItemName(donut);

        final Identifier layer0 = Identifier.of(BakingAway.MOD_ID, "item/" + name);
        final Identifier layer1 = Identifier.of(BakingAway.MOD_ID, "item/" + name + "_icing");
        final Identifier layer2 = Identifier.of(BakingAway.MOD_ID, "item/" + name + "_icing_glint");

        final Identifier model = Models.GENERATED_THREE_LAYERS.upload(donut, TextureMap.layered(layer0, layer1, layer2), generator.modelCollector);
        final int icing = 16675227;
        final TintSource noTint = ItemModels.constantTintSource(-1);

        generator.output.accept(donut, ItemModels.tinted(model, noTint, new DyeTintSource(icing), noTint));
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
        registerDonut(itemModelGenerator);
        registerSuspiciousDonuts(itemModelGenerator);
        itemModelGenerator.register(ModItems.RISEN_SWEET_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICED_SPOON, Models.HANDHELD);
        itemModelGenerator.register(ModItems.UNCOOKED_DONUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.UNICED_DONUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPOON, Models.HANDHELD);
    }
}
