package de.unipassau.fim.sommercamp.mod.blocks;

import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VillagerHubBlockEntity extends ChestBlockEntity implements ScreenHandlerFactory {

    public final int villagerCapacity = 3;
    private final static List<Payload.VillagerInfo> villagerInfoList = new ArrayList<>();
    private final List<String> villagerUUIDs = new ArrayList<>(villagerCapacity);

    public VillagerHubBlockEntity(BlockPos pos, BlockState state) {
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
        if (villagerUUIDs.size() < villagerCapacity && !villagerUUIDs.contains(uuid)){
            villagerUUIDs.add(uuid);
        }
    }

    public void removeVillager(String uuid){
        villagerUUIDs.remove(uuid);
    }

    public List<String> getVillagerUUIDs(){
        return Collections.unmodifiableList(villagerUUIDs);
    }

    public static VillagerEntity findVillagerByUUID(World world, String UUID) {
        if (world instanceof ServerWorld serverWorld) {
            // This list will always just contain 1 Element because there can only be one villager with a specified UUID.
            List<? extends VillagerEntity> villagerList = serverWorld.getEntitiesByType(
                    EntityType.VILLAGER,
                    (villager) -> villager.getUuidAsString().equals(UUID));
            return villagerList.isEmpty() ? null : villagerList.get(0);
        } else {
            System.out.println("Non-server world given.");
            return null;
        }
    }

    public static List<VillagerEntity> findVillagersByUUID(World world, List<String> UUIDList) {
        List<VillagerEntity> villagerEntities = new ArrayList<>();
        for (String UUID : UUIDList) {
            VillagerEntity villagerEntity = findVillagerByUUID(world, UUID);
            villagerEntities.add(villagerEntity);
        }
        return villagerEntities;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        System.out.println("write nbt " + villagerUUIDs.size());
        for(String villagerInfo : villagerUUIDs){
            //NbtCompound compound = new NbtCompound();
            NbtList nbtList = new NbtList();
            nbtList.add(NbtString.of(villagerInfo));
            nbt.put("uuids", nbtList);
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        NbtList list = nbt.getListOrEmpty("uuids");
        for(NbtElement villagerInfo : list){
            villagerUUIDs.add(villagerInfo.toString());
        }


    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
