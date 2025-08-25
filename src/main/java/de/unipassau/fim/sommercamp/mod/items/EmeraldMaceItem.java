package de.unipassau.fim.sommercamp.mod.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.MaceItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.UUID;

public class EmeraldMaceItem extends MaceItem {
    public EmeraldMaceItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            var instance = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 72000, 255, false, true, true);
            player.addStatusEffect(instance);
            player.sendMessage(Text.literal("Reduced all prices!"), true);
        }

        return super.use(world, player, hand);
    }
}
