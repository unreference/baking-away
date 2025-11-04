package jaiz.bakingaway.datagen;

import jaiz.bakingaway.block.ModBlocks;
import jaiz.bakingaway.block.custom.SweetDoughBlock;
import jaiz.bakingaway.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.addDrop(ModBlocks.MILL);
        this.sweetDoughDrops();
    }

    private void sweetDoughDrops() {
        final Block sweetDough = ModBlocks.SWEET_DOUGH;

        final BlockStatePropertyLootCondition.Builder risen = BlockStatePropertyLootCondition
                .builder(sweetDough)
                .properties(StatePredicate.Builder.create()
                        .exactMatch(SweetDoughBlock.STAGE, 3));

        final LootTable.Builder loot =
                LootTable.builder().pool(LootPool.builder()
                        .with(ItemEntry.builder(ModItems.RISEN_SWEET_DOUGH)
                                .conditionally(risen))
                        .with(ItemEntry.builder(sweetDough)
                                .conditionally(risen.invert())));

        this.addDrop(ModBlocks.SWEET_DOUGH, loot);
    }
}
