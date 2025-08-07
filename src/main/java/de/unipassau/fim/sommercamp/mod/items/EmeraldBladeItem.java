package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.network.CooldownHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EmeraldBladeItem extends Item {
    public EmeraldBladeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        UUID playerId = player.getUuid();

        if (!world.isClient()) {
            Item item = player.getStackInHand(hand).getItem();
            int cooldown = CooldownHandler.getCooldown(playerId, item);

            if (cooldown > 0) {
                player.sendMessage(Text.literal("Price reduction is on cooldown! (" + cooldown / 20 + "s left)"), true);
                return ActionResult.FAIL;
            }
            var instance = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 1200, 6, false, true, true);
            player.addStatusEffect(instance);
            player.sendMessage(Text.literal("Reduced all prices!"), true);
            CooldownHandler.setCooldown(playerId, item, 1200); // 60 seconds
            return ActionResult.SUCCESS;
        }
        return super.use(world, player, hand);
    }
}
