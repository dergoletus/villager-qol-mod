package de.unipassau.fim.sommercamp.mod.blocks;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBlocks {

    private static Block register(String id, AbstractBlock.Settings settings) {
        Identifier identifier = Identifier.of(VillagerQOLMod.MOD_ID, id);
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
        VillagerQOLMod.LOGGER.info("Adding Blocks");
    }
}