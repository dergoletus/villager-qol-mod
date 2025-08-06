package de.unipassau.fim.sommercamp.mod.BlockEntities;


import com.mojang.serialization.MapCodec;
import de.unipassau.fim.sommercamp.mod.blocks.VillagerHubBEBlock;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import de.unipassau.fim.sommercamp.mod.network.payload;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VillagerHubBlock extends BlockWithEntity{

    public VillagerHubBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(VillagerHubBlock::new);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // If the Villager Hub Block is right-clicked with a Villager Register Item, the Hub Block shall not open a GUI
        // but instead let the Item pick up the right click in the method VillagerRegisterItem.onUseBlock(...).
        if (player.getStackInHand(player.getActiveHand()).getItem() == ModItems.VILLAGER_REGISTER) {
            return ActionResult.PASS;
        }
        payload.VHpayload payload = new payload.VHpayload(player.getBlockPos());
        if(!world.isClient && world.getBlockEntity(pos) instanceof VillagerHubBEBlock villagerHubBEBlock){
            for (ServerPlayerEntity user : PlayerLookup.world((ServerWorld) world)) {
                ServerPlayNetworking.send(user, payload);
            }

        }
        return ActionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VillagerHubBEBlock(pos, state);
    }
}
