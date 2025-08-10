package de.unipassau.fim.sommercamp.mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.entity.passive.MerchantEntity")
public abstract class VillagerEntityRangeMixin {

    @ModifyConstant(method = "canInteract(Lnet/minecraft/entity/player/PlayerEntity;)Z", constant = @Constant(doubleValue = 4.0))
    private double injected(double value) {
        return 508.0;
    }
}
