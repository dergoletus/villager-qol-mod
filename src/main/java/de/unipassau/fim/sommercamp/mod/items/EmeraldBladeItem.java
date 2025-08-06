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

import java.util.List;

public class EmeraldBladeItem extends Item {

    public EmeraldBladeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            var instance = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 20000, 255, false, true, true);
            player.addStatusEffect(instance);
            player.sendMessage(Text.literal("Reduced all prices!"), true);
        }
        if(!world.isClient() && player.isSneaking()){
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
        }

        return super.use(world, player, hand);
    }
}
