package jaiz.bakingaway.datagen;

import jaiz.bakingaway.BakingAway;
import jaiz.bakingaway.block.ModBlocks;
import jaiz.bakingaway.item.ModItems;
import jaiz.bakingaway.item.custom.SuspiciousDonutIngredent;
import jaiz.bakingaway.util.ModUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.data.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private static void offerSuspiciousDonutRecipes(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        for (SuspiciousDonutIngredent ingredient : SuspiciousDonutIngredent.values()) {
            final SuspiciousStewEffectsComponent.StewEffect entry = new SuspiciousStewEffectsComponent.StewEffect(ingredient.getEffect(), ingredient.getDuration());
            final SuspiciousStewEffectsComponent effects = new SuspiciousStewEffectsComponent(List.of(entry));

            final ItemStack donut = new ItemStack(ModItems.SUSPICIOUS_DONUT);
            donut.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, effects);
            donut.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(ingredient.getIcingColor()));

            for (Item ingredientItem : ingredient.getItems()) {
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, donut)
                        .input(ModItems.UNICED_DONUT)
                        .input(ModItems.ICED_SPOON)
                        .input(ingredientItem)
                        .criterion(hasItemName(ingredientItem), hasItemCondition(itemLookup, ingredientItem))
                        .offerTo(exporter, String.valueOf(Identifier.of(BakingAway.MOD_ID, fromConversion(donut.getItem(), ingredientItem))));
            }
        }
    }

    private static void offerDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item icedSpoon = ModItems.ICED_SPOON;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.DONUT)
                .input(ModItems.UNICED_DONUT)
                .input(icedSpoon)
                .criterion(hasItemName(icedSpoon), hasItemCondition(itemLookup, icedSpoon))
                .offerTo(exporter);
    }

    private static void offerIcedSpoonRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item spoon = ModItems.SPOON;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.ICED_SPOON)
                .input(Items.EGG)
                .input(Items.SUGAR)
                .input(spoon)
                .criterion(hasItemName(spoon), hasItemCondition(itemLookup, spoon))
                .offerTo(exporter);
    }

    private static void offerSpoonRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModItems.SPOON)
                .input('P', ItemTags.PLANKS)
                .input('S', Items.STICK)
                .pattern(" P")
                .pattern("S ")
                .criterion(hasItemName(Items.STICK), hasItemCondition(itemLookup, Items.STICK))
                .offerTo(exporter);
    }

    private static void offerUnicedDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item raw = ModItems.UNCOOKED_DONUT;
        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItem(raw), RecipeCategory.FOOD, ModItems.UNICED_DONUT, 0.7f, 200)
                .criterion(hasItemName(raw), hasItemCondition(itemLookup, raw))
                .offerTo(exporter);
    }

    private static void offerBurntDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item donut = ModItems.UNICED_DONUT;
        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItem(donut), RecipeCategory.FOOD, ModItems.BURNT_DONUT, 0.7f, 200)
                .criterion(hasItemName(donut), hasItemCondition(itemLookup, donut))
                .offerTo(exporter);
    }

    private static void offerUncookedDonutRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item risenSweetDough = ModItems.RISEN_SWEET_DOUGH;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.UNCOOKED_DONUT)
                .input(risenSweetDough)
                .input(Items.SUGAR)
                .criterion(hasItemName(risenSweetDough), hasItemCondition(itemLookup, risenSweetDough))
                .offerTo(exporter);
    }

    private static void offerSweetDoughRecipe(RegistryWrapper.Impl<Item> itemLookup, RecipeExporter exporter) {
        final Item flour = ModItems.FLOUR;
        ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.FOOD, ModItems.SWEET_DOUGH)
                .input(Items.BOWL)
                .input(Items.SUGAR)
                .input(flour)
                .input(Items.WATER_BUCKET)
                .criterion(hasItemName(flour), hasItemCondition(itemLookup, flour))
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
                .criterion(hasItemName(ironIngot), hasItemCondition(itemLookup, ironIngot))
                .offerTo(exporter);
    }

    private static String fromConversion(ItemConvertible to, ItemConvertible from) {
        return ModUtils.getItemName(to) + "_from_" + ModUtils.getItemName(from);
    }

    private static String hasItemName(ItemConvertible item) {
        return "has_" + ModUtils.getItemName(item);
    }

    private static AdvancementCriterion<InventoryChangedCriterion.Conditions> hasItemCondition(RegistryWrapper.Impl<Item> itemLookup, ItemConvertible item) {
        return RecipeGenerator.conditionsFromPredicates(ItemPredicate.Builder.create().items(itemLookup, item));
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
                offerSuspiciousDonutRecipes(itemLookup, exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Recipe Provider (" + BakingAway.MOD_ID + ")";
    }
}
