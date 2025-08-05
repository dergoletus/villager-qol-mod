package de.unipassau.fim.sommercamp.mod.items;

import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.ArrayList;
import java.util.List;

public class VillagerRegisterItem extends Item {

    public static ArrayList<String> VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS = new ArrayList<String>();

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
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        return super.useOnEntity(stack, user, entity, hand);
    }
}
