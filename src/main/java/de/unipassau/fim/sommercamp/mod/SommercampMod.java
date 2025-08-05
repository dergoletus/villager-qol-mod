package de.unipassau.fim.sommercamp.mod;

import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import de.unipassau.fim.sommercamp.mod.items.ModItemGroups;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SommercampMod implements ModInitializer {

    public static final String MOD_ID = "villager-qol";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.init();
        ModItemGroups.init();
        ModBlocks.init();
        ModBlockEntities.init();

    }
}
