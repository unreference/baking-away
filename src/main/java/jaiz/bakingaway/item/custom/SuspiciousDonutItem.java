package jaiz.bakingaway.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SuspiciousDonutItem extends Item {
    public SuspiciousDonutItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        final SuspiciousStewEffectsComponent effects = stack.get(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS);
        if (effects != null) {
            for (SuspiciousStewEffectsComponent.StewEffect effect : effects.effects()) {
                user.addStatusEffect(new StatusEffectInstance(effect.effect(), effect.duration()));
            }
        }

        return super.finishUsing(stack, world, user);
    }
}
