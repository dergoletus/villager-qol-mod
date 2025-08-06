package de.unipassau.fim.sommercamp.mod.blocks;

import com.jcraft.jorbis.DspState;
import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VillagerHubBEBlock extends BlockEntity {
    public VillagerHubBEBlock(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VILLAGER_HUB_BE_BLOCK_BLOCK_ENTITY_TYPE, pos, state);
    }


}
