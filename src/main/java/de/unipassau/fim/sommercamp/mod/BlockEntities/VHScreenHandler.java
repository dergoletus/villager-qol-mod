package de.unipassau.fim.sommercamp.mod.BlockEntities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class VHScreenHandler extends ScreenHandler {


    protected VHScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    /*
    @Override
    protected void init() {

        MinecraftClient.getInstance().setScreen(
                new VHGui(Text.empty())
        );

    }
    */


    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

}
