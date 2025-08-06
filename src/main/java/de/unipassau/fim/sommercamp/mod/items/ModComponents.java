package de.unipassau.fim.sommercamp.mod.items;

import com.mojang.serialization.Codec;
import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModComponents {

    public static final ComponentType<String> VILLAGER_1_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(VillagerQOLMod.MOD_ID, "villager_1_uuid"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<String> VILLAGER_2_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(VillagerQOLMod.MOD_ID, "villager_2_uuid"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<String> VILLAGER_3_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(VillagerQOLMod.MOD_ID, "villager_3_uuid"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    protected static void init() {
        VillagerQOLMod.LOGGER.info("Adding Mod Components");
    }

}
