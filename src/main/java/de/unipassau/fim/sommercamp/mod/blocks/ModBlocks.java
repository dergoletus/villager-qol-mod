package de.unipassau.fim.sommercamp.mod.blocks;

import de.unipassau.fim.sommercamp.mod.SommercampMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block MYSTERY_BLOCK = register("mystery_block",
            AbstractBlock.Settings.copy(Blocks.STONE));

    private static Block register(String id, AbstractBlock.Settings settings) {
        Identifier identifier = Identifier.of(SommercampMod.MOD_ID, id);
        RegistryKey<Block> registryKey = RegistryKey.of(Registries.BLOCK.getKey(), identifier);

        Block block = new Block(settings.registryKey(registryKey));

        return Registry.register(Registries.BLOCK, identifier, block);
    }

    private static BlockItem registerBlockItem(Block block) {
        Identifier identifier = Registries.BLOCK.getId(block);
        Item.Settings settings = new Item.Settings().registryKey(RegistryKey.of(Registries.ITEM.getKey(), identifier));

        BlockItem blockItem = new BlockItem(block, settings);

        return Registry.register(Registries.ITEM, identifier, blockItem);
    }

    public static void init() {
        SommercampMod.LOGGER.info("Adding Blocks");
    }
}