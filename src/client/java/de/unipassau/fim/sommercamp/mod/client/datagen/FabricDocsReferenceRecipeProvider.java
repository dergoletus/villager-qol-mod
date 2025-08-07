package de.unipassau.fim.sommercamp.mod.client.datagen;

import java.util.concurrent.CompletableFuture;

import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class FabricDocsReferenceRecipeProvider extends FabricRecipeProvider {
    public FabricDocsReferenceRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createShaped(RecipeCategory.MISC, ModItems.STOP_STICK, 1)
                        .pattern("rdr")
                        .pattern("dxd")
                        .pattern("rdr")
                        .input('r', Items.REDSTONE_BLOCK)
                        .input('d', Items.DIAMOND)
                        .input('x', Items.RED_DYE)
                        .group("multi_bench")
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.RESUME_STICK, 1)
                        .pattern("rdr")
                        .pattern("dxd")
                        .pattern("rdr")
                        .input('r', Items.REDSTONE_BLOCK)
                        .input('d', Items.DIAMOND)
                        .input('x', Items.GREEN_DYE)
                        .group("multi_bench")
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModItems.COMPRESSED_EMERALD, 1)
                        .pattern("eee")
                        .pattern("eee")
                        .pattern("eee")
                        .input('e', Items.EMERALD_BLOCK)
                        .group("muli_bench")
                        .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of("emerald_block_to_compressed")));
                createShapeless(RecipeCategory.MISC, Items.EMERALD_BLOCK, 9)
                        .input(ModItems.COMPRESSED_EMERALD)
                        .criterion(hasItem(ModItems.COMPRESSED_EMERALD), conditionsFromItem(ModItems.COMPRESSED_EMERALD))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of("compressed_emerald_reversed")));

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModItems.COMPRESSED_EMERALD_BLOCK, 1)
                        .pattern("eee")
                        .pattern("eee")
                        .pattern("eee")
                        .input('e', ModItems.COMPRESSED_EMERALD)
                        .group("muli_bench")
                        .criterion(hasItem(Items.EMERALD_BLOCK), conditionsFromItem(Items.EMERALD_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of("compressed_emerald_to_block")));
                createShapeless(RecipeCategory.MISC, ModItems.COMPRESSED_EMERALD, 9)
                        .input(ModItems.COMPRESSED_EMERALD_BLOCK)
                        .criterion(hasItem(ModItems.COMPRESSED_EMERALD_BLOCK), conditionsFromItem(ModItems.COMPRESSED_EMERALD_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of("compressed_block_reversed")));

                createShaped(RecipeCategory.COMBAT, ModItems.EMERALD_BLADE, 1)
                        .pattern("e")
                        .pattern("e")
                        .pattern("s")
                        .input('e', ModItems.COMPRESSED_EMERALD_BLOCK)
                        .input('s', Items.STICK)
                        .group("muli_bench")
                        .criterion(hasItem(Items.EMERALD_BLOCK), conditionsFromItem(Items.EMERALD_BLOCK))
                        .offerTo(exporter);

                //Recipe for Emerald Wand
                createShaped(RecipeCategory.MISC, ModItems.EMERALD_WAND, 1)
                        .pattern("e")
                        .pattern("s")
                        .input('e', ModItems.COMPRESSED_EMERALD_BLOCK)
                        .input('s', Items.STICK)
                        .group("multi_bench")
                        .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                        .offerTo(exporter);

                //Recipe for Emerald Mace
                createShaped(RecipeCategory.MISC, ModItems.EMERALD_MACE, 1)
                        .pattern("nee")
                        .pattern("dme")
                        .pattern("bdn")
                        .input('e', ModItems.COMPRESSED_EMERALD_BLOCK)
                        .input('m', Items.MACE)
                        .input('b', ModItems.EMERALD_BLADE)
                        .input('d', Items.DIAMOND_BLOCK)
                        .input('n', Items.NETHERITE_INGOT)
                        .group("multi_bench")
                        .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                        .offerTo(exporter);

                // Rezept für Villager Register
                createShaped(RecipeCategory.MISC, ModItems.VILLAGER_REGISTER, 1)
                        .pattern("cdc")
                        .pattern("did")
                        .pattern("cdc")
                        .input('c', Items.CHEST)
                        .input('d', Items.DIAMOND)
                        .input('i', Items.ITEM_FRAME)
                        .group("multi_bench")
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "FabricDocsReferenceRecipeProvider";
    }
}