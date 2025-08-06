package de.unipassau.fim.sommercamp.mod.screens;

import de.unipassau.fim.sommercamp.mod.blocks.VillagerHubBEBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class VHScreenHandler extends ScreenHandler {
    private final BlockPos pos;
    private final World world;
    private VillagerHubBEBlock blockEntity;

    public VHScreenHandler(int syncId, BlockPos pos, World world) {
        super(ModScreenHandlers.VH_SCREEN_HANDLER, syncId);
        this.pos = pos;
        this.world = world;

        if (world.getBlockEntity(pos) instanceof VillagerHubBEBlock be) {
            this.blockEntity = be;
        }
    }



    public void setSomeValue(String value) {
        if (blockEntity != null) {
            blockEntity.addVillager(value);
        }
    }
    public List<String> getSomeValue() {
        return blockEntity != null ? blockEntity.getVillagerUUIDs() : villagerUUIDs;
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return blockEntity != null && player.squaredDistanceTo(pos.getX(), pos.getY(), pos.getZ()) <= 64;
    }
}
