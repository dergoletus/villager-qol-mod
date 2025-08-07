package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.network.CooldownHandler;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class EmeraldWandItem extends Item{
    public static final Map<UUID, Integer> restockCooldowns = new HashMap<>();
    public EmeraldWandItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        UUID playerId = player.getUuid();
        Item item = player.getStackInHand(hand).getItem();

        int cooldown = CooldownHandler.getCooldown(playerId, item);
        if (!world.isClient()) {
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
            CooldownHandler.setCooldown(playerId, item, 2400); // 120 seconds
            return ActionResult.SUCCESS;
        }
        return super.use(world, player, hand);
    }
}
