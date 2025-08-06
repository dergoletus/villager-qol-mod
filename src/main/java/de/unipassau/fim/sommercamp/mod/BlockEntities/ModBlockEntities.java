package de.unipassau.fim.sommercamp.mod.BlockEntities;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import de.unipassau.fim.sommercamp.mod.blocks.VillagerHubBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<VillagerHubBlockEntity> VILLAGER_HUB_BE_BLOCK_BLOCK_ENTITY_TYPE =
            register("villager_hub", VillagerHubBlockEntity::new, ModBlocks.VILLAGER_HUB);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.of(VillagerQOLMod.MOD_ID, name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {
        VillagerQOLMod.LOGGER.info("Adding Blocks");
    }


}


