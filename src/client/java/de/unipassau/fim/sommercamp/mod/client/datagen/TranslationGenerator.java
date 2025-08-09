package de.unipassau.fim.sommercamp.mod.client.datagen;

import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import de.unipassau.fim.sommercamp.mod.items.EmeraldBladeItem;
import de.unipassau.fim.sommercamp.mod.items.ModItemGroups;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import de.unipassau.fim.sommercamp.mod.items.VillagerRegisterItem;
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
        translationBuilder.add(ModItems.STOP_STICK_DESCRIPTION_TRANSLATION_KEY, "Freezes all villagers within 16 Blocks.");
        translationBuilder.add(ModItems.RESUME_STICK, "Resume Stick");
        translationBuilder.add(ModItems.RESUME_STICK_DESCRIPTION_TRANSLATION_KEY, "Unfreezes all villagers previously frozen with the Stop Stick within 16 blocks.");
        translationBuilder.add(ModItems.VILLAGER_REGISTER, "Villager Register");
        translationBuilder.add(VillagerRegisterItem.VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS.get(0), "Store a reference to a villager in here by right-clicking on a villager.");
        translationBuilder.add(VillagerRegisterItem.VILLAGER_REGISTER_DESCRIPTION_TRANSLATION_KEYS.get(1), "Right click again on a Villager Hub Block to connect villagers to the Hub Block.");
        translationBuilder.add(ModItems.EMERALD_BLADE, "Emerald Blade");
        translationBuilder.add(ModItems.EMERALD_BLADE_DESCRIPTION_TRANSLATION_KEY, "The Emerald Blade forces villagers to reduce their prices.");
        translationBuilder.add(ModItems.COMPRESSED_EMERALD, "Compressed Emerald");
        translationBuilder.add(ModItems.COMPRESSED_EMERALD_DESCRIPTION_TRANSLATION_KEY, "The equivalent of 81 emeralds.");
        translationBuilder.add(ModItems.COMPRESSED_EMERALD_BLOCK, "Compressed Emerald Block");
        translationBuilder.add(ModItems.COMPRESSED_EMERALD_BLOCK_DESCRIPTION_TRANSLATION_KEY, "The equivalent of 729 emeralds.");
        translationBuilder.add(ModItems.EMERALD_WAND_DESCRIPTION_TRANSLATION_KEY, "This Wand forces villagers to restock their trades.");
        translationBuilder.add(ModItems.EMERALD_WAND, "Emerald Wand");
        translationBuilder.add(ModItems.EMERALD_MACE_DESCRIPTION_TRANSLATION_KEY, "The Emerald Mace forces villagers to reduce their prices infinitely.");
        translationBuilder.add(ModItems.EMERALD_MACE, "Emerald Mace");
    }
}
