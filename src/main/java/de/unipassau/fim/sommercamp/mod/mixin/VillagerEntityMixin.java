package de.unipassau.fim.sommercamp.mod.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(VillagerEntity.class)
public interface VillagerEntityMixin {
    @Invoker("beginTradeWith")
    void invokeBeginTradeWith(PlayerEntity customer);
}
