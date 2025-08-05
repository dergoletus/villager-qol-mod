package de.unipassau.fim.sommercamp.mod.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class EmeraldBladeItem extends Item {

    public EmeraldBladeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            var instance = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 2000, 255, false, true, true);
            player.addStatusEffect(instance);
        }

        return super.use(world, player, hand);
    }
}
