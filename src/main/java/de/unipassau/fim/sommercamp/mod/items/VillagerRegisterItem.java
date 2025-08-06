package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import de.unipassau.fim.sommercamp.mod.components.ModComponents;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class VillagerRegisterItem extends Item {

    public static ArrayList<String> VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS = new ArrayList<>();

    public VillagerRegisterItem(Settings settings) {
        super(settings);
    }

    public static LoreComponent createLoreComponent() {
        for (int i = 0; i < 3; i++) {
            VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS.add("item.villager-qol.villager_register.description." + i);
        }
        return new LoreComponent(List.of(Text.translatable(VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS.get(0)),
            Text.translatable(VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS.get(1))));
    }

    public static ActionResult useOnVillager(ItemStack stack, PlayerEntity player, Entity villager, Hand hand) {
        String villagerUUID = villager.getUuidAsString();

        System.out.println(stack.get(ModComponents.VILLAGER_REGISTER_UUID_COMPONENT));

        // If the Villager Register is already full, a UUID cannot be added.
        if (!stack.get(ModComponents.VILLAGER_REGISTER_UUID_COMPONENT).isEmpty()) {
            player.sendMessage(Text.literal("Your Villager Register is full!"), true);
            return ActionResult.FAIL;
            // Otherwise, the entities UUID is added into the register.
        } else {
            stack.set(ModComponents.VILLAGER_REGISTER_UUID_COMPONENT, villagerUUID);
            player.sendMessage(Text.literal("UUID " + villagerUUID + " stored."), true);
            return ActionResult.SUCCESS;
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (entity.getType() != EntityType.VILLAGER) {
            player.sendMessage(Text.literal("This item can only be used on villagers"), true);
            return ActionResult.FAIL;
        }
        return VillagerRegisterItem.useOnVillager(stack, player, entity, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        context.getWorld().getBlockEntity(context.getBlockPos(), ModBlockEntities.VILLAGER_HUB_BE_BLOCK_BLOCK_ENTITY_TYPE)
                .ifPresentOrElse((villagerHubBlockEntity) -> {
                    String villagerUUID = context.getStack().get(ModComponents.VILLAGER_REGISTER_UUID_COMPONENT);
                    if (villagerUUID.isEmpty()) {
                       context.getPlayer().sendMessage(Text.literal("Your villager register is empty! Fill it up by right-clicking a villager."), true);
                    } else if (villagerHubBlockEntity.getVillagerUUIDs().size() == villagerHubBlockEntity.villagerCapacity) {
                        context.getPlayer().sendMessage(Text.literal("This Villager Hub Block is full!"), true);
                    } else if (villagerHubBlockEntity.getVillagerUUIDs().contains(villagerUUID)) {
                        context.getPlayer().sendMessage(Text.literal("This Villager Hub Block already contains UUID " + villagerUUID + ". " +
                                "Your villager register has been emptied."), true);
                        context.getStack().set(ModComponents.VILLAGER_REGISTER_UUID_COMPONENT, "");
                    } else {
                        villagerHubBlockEntity.addVillager(villagerUUID);
                        context.getPlayer().sendMessage(Text.literal("UUID " + villagerUUID + " stored in Villager Hub Block."), true);
                        context.getStack().set(ModComponents.VILLAGER_REGISTER_UUID_COMPONENT, "");
                    }
                }, () -> {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        player.sendMessage(Text.literal("Use this item on a Villager Hub Block!"), true);
                    }
                });
        return ActionResult.SUCCESS;
    }
}
