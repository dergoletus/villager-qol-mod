package de.unipassau.fim.sommercamp.mod.screens;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers
    public static ScreenHandlerType<VHScreenHandler> VH_SCREEN_HANDLER;

    public static void register() {
        VH_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
                new Identifier("VillagerQOLMod", "example_screen"),
                (syncId, inv) -> new VHScreenHandler(syncId, BlockPos.ORIGIN, inv.player.world) // dummy data
        );
    }{
}
