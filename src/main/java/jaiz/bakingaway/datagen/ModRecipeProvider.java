package jaiz.bakingaway.datagen;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.block.ModBlocks;
import jaiz.bakingaway.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                final RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                offerMillRecipe(itemLookup, exporter);
                offerSweetDoughRecipe(itemLookup, exporter);
                offerUncookedDonutRecipe(itemLookup, exporter);
                offerUnicedDonutRecipe(itemLookup, exporter);
                offerBurntDonutRecipe(itemLookup, exporter);
                offerSpoonRecipe(itemLookup, exporter);
                offerIcedSpoonRecipe(itemLookup, exporter);
                offerDonutRecipe(itemLookup, exporter);
            }
        };
    }

    private static void offerDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item icedSpoon = ModItems.ICED_SPOON;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.DONUT)
                .input(ModItems.UNICED_DONUT)
                .input(icedSpoon)
                .criterion(hasName(icedSpoon), hasCondition(itemLookup, icedSpoon))
                .offerTo(exporter);
    }

    private static void offerIcedSpoonRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item spoon = ModItems.SPOON;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.ICED_SPOON)
                .input(Items.EGG)
                .input(Items.SUGAR)
                .input(spoon)
                .criterion(hasName(spoon), hasCondition(itemLookup, spoon))
                .offerTo(exporter);
    }

    private static void offerSpoonRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModItems.SPOON)
                .input('P', ItemTags.PLANKS)
                .input('S', Items.STICK)
                .pattern(" P")
                .pattern("S ")
                .criterion(hasName(Items.STICK), hasCondition(itemLookup, Items.STICK))
                .offerTo(exporter);
    }

    private static void offerUnicedDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item raw = ModItems.UNCOOKED_DONUT;
        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItem(raw), RecipeCategory.FOOD, ModItems.UNICED_DONUT, 0.7f, 200)
                .criterion(hasName(raw), hasCondition(itemLookup, raw))
                .offerTo(exporter);
    }

    private static void offerBurntDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item donut = ModItems.UNICED_DONUT;
        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItem(donut), RecipeCategory.FOOD, ModItems.BURNT_DONUT, 0.7f, 200)
                .criterion(hasName(donut), hasCondition(itemLookup, donut))
                .offerTo(exporter);
    }

    private static void offerUncookedDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item risenSweetDough = ModItems.RISEN_SWEET_DOUGH;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.UNCOOKED_DONUT)
                .input(risenSweetDough)
                .input(Items.SUGAR)
                .criterion(hasName(risenSweetDough), hasCondition(itemLookup, risenSweetDough))
                .offerTo(exporter);
    }

    private static void offerSweetDoughRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item flour = ModItems.FLOUR;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.SWEET_DOUGH)
                .input(Items.BOWL)
                .input(Items.SUGAR)
                .input(flour)
                .input(Items.WATER_BUCKET)
                .criterion(hasName(flour), hasCondition(itemLookup, flour))
                .offerTo(exporter);
    }

    private static void offerMillRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item ironIngot = Items.IRON_INGOT;
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.DECORATIONS, ModBlocks.MILL)
                .input('S', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('I', ironIngot)
                .input('P', ItemTags.PLANKS)
                .pattern("SIS")
                .pattern("PIP")
                .criterion(hasName(ironIngot), hasCondition(itemLookup, ironIngot))
                .offerTo(exporter);
    }

    private static String hasName(ItemConvertible item) {
        return "has_" + Registries.ITEM.getId(item.asItem()).getPath();
    }

    private static AdvancementCriterion<InventoryChangedCriterion.Conditions> hasCondition(RegistryWrapper.Impl<Item> itemLookup, ItemConvertible item) {
        return RecipeGenerator.conditionsFromPredicates(ItemPredicate.Builder.create().items(itemLookup, item));
    }

    @Override
    public String getName() {
        return "Recipe Provider (" + BakingAway.MOD_ID + ")";
    }
}
