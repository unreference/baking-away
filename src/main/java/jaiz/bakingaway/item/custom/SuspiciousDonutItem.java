package jaiz.bakingaway.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class SuspiciousDonutItem extends Item {
    public SuspiciousDonutItem(Settings settings) {
        super(settings);
    }

    private static SuspiciousDonutIngredient getIngredientFromEffect(RegistryEntry<StatusEffect> effect) {
        final StatusEffect find = effect.value();
        for (SuspiciousDonutIngredient ingredient : SuspiciousDonutIngredient.values()) {
            if (ingredient.getEffect().value().equals(find)) {
                return ingredient;
            }
        }

        return null;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        final SuspiciousStewEffectsComponent effects = stack.get(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS);
        if (effects != null) {
            for (SuspiciousStewEffectsComponent.StewEffect effect : effects.effects()) {
                final SuspiciousDonutIngredient ingredient = getIngredientFromEffect(effect.effect());
                if (ingredient != null) {
                    if (ingredient.getMinTicks() > 19 && ingredient.getMaxTicks() > 20 && ingredient.getMinTicks() < ingredient.getMaxTicks()) {
                        ingredient.setTicks(world.getRandom().nextBetween(ingredient.getMinTicks(), ingredient.getMaxTicks()));
                    } else {
                        ingredient.setTicks(ingredient.getTicks());
                    }

                    user.addStatusEffect(new StatusEffectInstance(ingredient.getEffect(), ingredient.getTicks()));
                }
            }
        }

        return super.finishUsing(stack, world, user);
    }
}
