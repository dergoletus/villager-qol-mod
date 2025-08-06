package de.unipassau.fim.sommercamp.mod.blocks;

import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import de.unipassau.fim.sommercamp.mod.screens.VHScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VillagerHubBEBlock extends BlockEntity implements NamedScreenHandlerFactory{

    private List<String> villagerUUIDs = new ArrayList<>(3);

    public VillagerHubBEBlock(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VILLAGER_HUB_BE_BLOCK_BLOCK_ENTITY_TYPE, pos, state);
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

    @Override
    public Text getDisplayName() {
        return Text.literal("Villager Hub");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new VHScreenHandler(syncId, this.pos, world);
    }

}
