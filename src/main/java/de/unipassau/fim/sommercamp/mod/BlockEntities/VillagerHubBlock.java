package de.unipassau.fim.sommercamp.mod.BlockEntities;


import com.mojang.serialization.MapCodec;
import de.unipassau.fim.sommercamp.mod.blocks.VillagerHubBlockEntity;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class VillagerHubBlock extends BlockWithEntity{


    public static Dictionary<Integer, String> VILLAGER_LEVEL_DICTIONARY = new Hashtable<>();

    public VillagerHubBlock(Settings settings) {
        super(settings);
        // Populate Dictionary for Villager Level conversion from int to String
        VILLAGER_LEVEL_DICTIONARY.put(0, "None");
        VILLAGER_LEVEL_DICTIONARY.put(1, "Novice");
        VILLAGER_LEVEL_DICTIONARY.put(2, "Apprentice");
        VILLAGER_LEVEL_DICTIONARY.put(3, "Journeyman");
        VILLAGER_LEVEL_DICTIONARY.put(4, "Expert");
        VILLAGER_LEVEL_DICTIONARY.put(5,"Master");
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(VillagerHubBlock::new);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // If the Villager Hub Block is right-clicked with a Villager Register Item, the Hub Block shall not open a GUI
        // but instead let the Item pick up the right click in the method VillagerRegisterItem.onUseBlock(...).
        if (player.getStackInHand(player.getActiveHand()).getItem() == ModItems.VILLAGER_REGISTER) {
            return ActionResult.PASS;
        }
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof VillagerHubBlockEntity villagerHubBlockEntity && player instanceof ServerPlayerEntity serverPlayer) {
                sendVillagerDataToClient(world, serverPlayer, villagerHubBlockEntity);
            }
        }
        return ActionResult.SUCCESS;
    }

    public void sendVillagerDataToClient(World world, ServerPlayerEntity player, VillagerHubBlockEntity villagerHubBlockEntity) {
        // Sammeln von Villager UUIDs und ihren Professions
        List<Payload.VillagerInfo> villagerInfos = new ArrayList<>();
        List<VillagerEntity> villagers = VillagerHubBlockEntity.findVillagersByUUID(world, villagerHubBlockEntity.getVillagerUUIDs());
        for (VillagerEntity villager : villagers) {
            if (villager != null) {
                villagerInfos.add(new Payload.VillagerInfo(
                        villager.getUuidAsString(),
                        villager.getVillagerData().profession().getIdAsString(),
                        VILLAGER_LEVEL_DICTIONARY.get(villager.getVillagerData().level())));
            } else {
                villagerInfos.add(new Payload.VillagerInfo(null, null, null));
            }
        }

        Payload.VHPayload payload = new Payload.VHPayload(villagerHubBlockEntity.getPos(), villagerInfos);
        //for (ServerPlayerEntity user : PlayerLookup.world((ServerWorld) world)) {
            ServerPlayNetworking.send(player, payload);
        //}
    }



    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VillagerHubBlockEntity(pos, state);
    }
}