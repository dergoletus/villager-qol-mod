package de.unipassau.fim.sommercamp.mod.events;

import com.mojang.serialization.Codec;
import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import de.unipassau.fim.sommercamp.mod.items.VillagerRegisterItem;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class ModEvents {

    public static void init() {
        VillagerQOLMod.LOGGER.info("Adding Mod Events");
        registerVillagerRightClickEvent();
    }

    // If a villager is right-clicked with a Villager Register in hand, its trading GUI needs to be suppressed
    // and overridden with the Villager Register's useOnEntity() logic.
    // If it wasn't for this event, right-clicking with a Villager Register would simply open the villager's menu.
    private static void registerVillagerRightClickEvent() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity.getType() == EntityType.VILLAGER && player.getStackInHand(hand).getItem() == ModItems.VILLAGER_REGISTER) {
                return VillagerRegisterItem.useOnVillager(player.getStackInHand(hand), player, entity, hand);
            } else {
                return ActionResult.FAIL;
            }
        });
    }

}
