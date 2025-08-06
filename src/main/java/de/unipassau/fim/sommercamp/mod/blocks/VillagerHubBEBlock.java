package de.unipassau.fim.sommercamp.mod.blocks;

import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VillagerHubBEBlock extends ChestBlockEntity implements ScreenHandlerFactory {

    public final int villagerCapacity = 3;
    private final List<String> villagerUUIDs = new ArrayList<>(villagerCapacity);

    public VillagerHubBEBlock(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VILLAGER_HUB_BE_BLOCK_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return this.checkUnlocked(playerEntity) ? this.createScreenHandler(i, playerInventory) : null;
    }

    public Text getDisplayName() {
        return Text.literal("Villager Hub");
    }

    public void addVillager(String uuid) {
        if (villagerUUIDs.size() < 3 && !villagerUUIDs.contains(uuid)){
            villagerUUIDs.add(uuid);
        }
    }

    public void removeVillager(String uuid){
        villagerUUIDs.remove(uuid);
    }

    public List<String> getVillagerUUIDs(){
        return Collections.unmodifiableList(villagerUUIDs);
    }
}
