package de.unipassau.fim.sommercamp.mod.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class VillagerQOLModDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(TranslationGenerator::new);
        pack.addProvider(ModelGenerator::new);
        pack.addProvider(FabricDocsReferenceRecipeProvider::new);
        pack.addProvider(FabricDocsReferenceBlockLootTableProvider::new);
    }

}
