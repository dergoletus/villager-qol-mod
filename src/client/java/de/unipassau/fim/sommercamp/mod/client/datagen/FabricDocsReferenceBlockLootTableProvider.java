package de.unipassau.fim.sommercamp.mod.client.datagen;

import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FabricDocsReferenceBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected FabricDocsReferenceBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.VILLAGER_HUB);
    }
}