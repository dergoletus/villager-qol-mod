package de.unipassau.fim.sommercamp.mod.client.datagen;

import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VILLAGER_HUB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.STOP_STICK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.VILLAGER_REGISTER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RESUME_STICK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_BLADE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COMPRESSED_EMERALD, Models.GENERATED);
        itemModelGenerator.register(ModItems.COMPRESSED_EMERALD_BLOCK,
                new Model(Optional.of(Identifier.of("minecraft", "block/emerald_block")), Optional.empty()));
        itemModelGenerator.register(ModItems.EMERALD_WAND, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_MACE, Models.HANDHELD_MACE);
    }
}
