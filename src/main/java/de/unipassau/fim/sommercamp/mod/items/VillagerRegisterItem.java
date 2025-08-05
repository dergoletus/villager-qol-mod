package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            Text.translatable(VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS.get(1)),
            Text.translatable(VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS.get(2))));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        ArrayList<String> villagerUUIDs = new ArrayList<>();
        villagerUUIDs.add(stack.get(ModComponents.VILLAGER_1_UUID));
        villagerUUIDs.add(stack.get(ModComponents.VILLAGER_2_UUID));
        villagerUUIDs.add(stack.get(ModComponents.VILLAGER_3_UUID));

        int nextFreeElement = -1;
        for (int i = 0; i < villagerUUIDs.size(); i++) {
            if (Objects.equals(villagerUUIDs.get(i), "")) {
                nextFreeElement = i + 1;
            }
        }

        String villagerUUID = entity.getUuidAsString();

        // If none of the villagerUUIDs are empty, no free space is available within the Villager Register.
        if (nextFreeElement == -1) {
            player.sendMessage(Text.literal("Your Villager Register is full!"), true);
            return ActionResult.FAIL;
        // Otherwise, the entities UUID is added into whatever slot is free, simulating a queue system.
        } else if (nextFreeElement == 1) {
            stack.set(ModComponents.VILLAGER_1_UUID, villagerUUID);
        } else if (nextFreeElement == 2) {
            stack.set(ModComponents.VILLAGER_2_UUID, villagerUUID);
        } else if (nextFreeElement == 3) {
            stack.set(ModComponents.VILLAGER_3_UUID, villagerUUID);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        context.getWorld().getBlockEntity(context.getBlockPos(), ModBlockEntities.VILLAGER_HUB_BE_BLOCK_BLOCK_ENTITY_TYPE)
                .ifPresentOrElse((blockEntity) -> {
                    // Get UUID from UUID List

                    // TODO: Villager UUID dem Villager Hub Block übergeben
                }, () -> {
                    PlayerEntity player = context.getPlayer();
                    if (player != null) {
                        player.sendMessage(Text.literal("Use this item on a Villager Hub Block!"), true);
                    }
                });
        return ActionResult.SUCCESS;
    }
}
