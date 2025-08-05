package de.unipassau.fim.sommercamp.mod.client.datagen;

import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import de.unipassau.fim.sommercamp.mod.items.ModItemGroups;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TranslationGenerator extends FabricLanguageProvider {

    protected TranslationGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItemGroups.VILLAGER_QOL_GROUP_TRANSLATION_KEY, "Villager QOL");
        translationBuilder.add(ModItems.STOP_STICK, "Stop Stick");
        translationBuilder.add(ModBlocks.VILLAGER_HUB, "Villager Hub");
        translationBuilder.add(ModBlocks.VILLAGER_HUB.asItem(), "Villager Hub");
        translationBuilder.add(ModItems.STOP_STICK_DESCRIPTION_TRANSLATION_KEY, "Freezes all Villagers within 16 Blocks in place");
    }
}
