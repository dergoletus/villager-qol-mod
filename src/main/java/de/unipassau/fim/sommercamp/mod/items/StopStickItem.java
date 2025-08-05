package de.unipassau.fim.sommercamp.mod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class StopStickItem extends Item {

    public StopStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            // Das Item soll Villager in einem Radius von 16 Blöcken einfrieren.
            double radius = 16;

            List<VillagerEntity> nearbyVillagers = world.getEntitiesByClass(
                    VillagerEntity.class,
                    player.getBoundingBox().expand(radius),
                    villager -> true // kein zusätzlicher Filter
            );

            for (Entity entity : nearbyVillagers) {
                if (entity instanceof VillagerEntity villager) {
                    // Bewegung stoppen
                    villager.setVelocity(Vec3d.ZERO);
                    villager.velocityDirty = true;

                    // Navigation abbrechen
                    if (villager.getNavigation() != null) {
                        villager.getNavigation().stop();
                    }
                }
            }

            player.sendMessage(Text.literal("Villagers frozen: " + nearbyVillagers.size()), true);
        }

        return super.use(world, player, hand);
    }
}
