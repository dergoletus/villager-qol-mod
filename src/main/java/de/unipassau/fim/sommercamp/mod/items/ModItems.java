package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Function;

public class ModItems {


    public static final String STOP_STICK_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.stop_stick.description";

    public static final StopStickItem STOP_STICK = register("stop_stick",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(STOP_STICK_DESCRIPTION_TRANSLATION_KEY))))
                    .maxCount(1),
            StopStickItem::new);

    public static <T extends Item> T register(String id, Item.Settings settings, Function<Item.Settings, T> factory) {
        Identifier identifier = Identifier.of(VillagerQOLMod.MOD_ID, id);
        RegistryKey<Item> registryKey = RegistryKey.of(Registries.ITEM.getKey(), identifier);

        T item = factory.apply(settings.registryKey(registryKey));

        return Registry.register(Registries.ITEM, identifier, item);
    }

    public static void init() {
        VillagerQOLMod.LOGGER.info("Adding Items");
    }
}
