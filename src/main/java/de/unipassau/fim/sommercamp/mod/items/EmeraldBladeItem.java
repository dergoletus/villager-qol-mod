package de.unipassau.fim.sommercamp.mod.items;

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

    public static final Map<UUID, Integer> restockCooldowns = new HashMap<>();

    public EmeraldBladeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        UUID playerId = player.getUuid();

        if (!world.isClient()) {
            var instance = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 20000, 255, false, true, true);
            player.addStatusEffect(instance);
            player.sendMessage(Text.literal("Reduced all prices!"), true);
        }
        if (!world.isClient() && player.isSneaking()) {
            int cooldown = restockCooldowns.getOrDefault(playerId, 0);
            if (cooldown > 0) {
                player.sendMessage(Text.literal("Restock is on cooldown! (" + cooldown / 20 + "s left)"), true);
                return ActionResult.FAIL;
            }

            int radius = 16;
            List<VillagerEntity> nearbyVillagers = world.getEntitiesByClass(
                    VillagerEntity.class,
                    player.getBoundingBox().expand(radius),
                    villager -> true
            );
            for (VillagerEntity villager : nearbyVillagers) {
                villager.restock();
            }

            player.sendMessage(Text.literal("Villagers restocked: " + nearbyVillagers.size()), true);
            restockCooldowns.put(playerId, 2400); // 2 minutes

            return ActionResult.SUCCESS;
        }

        return super.use(world, player, hand);
    }
}
