package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.SommercampMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item STOP_STICK = register("stop_stick",
            new Item.Settings()
                    .maxCount(1));

    public static Item register(String id, Item.Settings settings) {
        Identifier identifier = Identifier.of(SommercampMod.MOD_ID, id);
        RegistryKey<Item> registryKey = RegistryKey.of(Registries.ITEM.getKey(), identifier);

        Item item = new Item(settings.registryKey(registryKey));

        return Registry.register(Registries.ITEM, identifier, item);
    }

    public static void init() {
        SommercampMod.LOGGER.info("Adding Items");
    }
}
