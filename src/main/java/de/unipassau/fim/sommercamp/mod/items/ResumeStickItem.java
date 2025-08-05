package de.unipassau.fim.sommercamp.mod.items;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class ResumeStickItem extends Item {

    public ResumeStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            // The item will unfreeze villagers in a radius of 16 blocks.
            double radius = 16;

            List<VillagerEntity> nearbyVillagers = world.getEntitiesByClass(
                    VillagerEntity.class,
                    player.getBoundingBox().expand(radius),
                    villager -> true
            );

            for (VillagerEntity villager : nearbyVillagers) {
                // Re-enables the villagers velocity, navigation and ability to be pushed by other mobs
                villager.setAiDisabled(false);
            }
            player.sendMessage(Text.literal("Villagers unfrozen: " + nearbyVillagers.size()), true);
        }

        return super.use(world, player, hand);
    }
}
