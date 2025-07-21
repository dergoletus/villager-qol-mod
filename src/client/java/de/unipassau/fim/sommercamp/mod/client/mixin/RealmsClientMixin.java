package de.unipassau.fim.sommercamp.mod.client.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.realms.RealmsClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(RealmsClient.class)
@Environment(EnvType.CLIENT)
public class RealmsClientMixin {

    /**
     * Disables the minecraft realms as it is not intended to be used
     *
     * @author Bernd Wagner
     * @reason Disable realms fetching on start
     */
    @Inject(at = @At("HEAD"), method = "fetchFeatureFlags()Ljava/util/Set;", cancellable = true)
    private void fetchFeatureFlags(CallbackInfoReturnable<Set<String>> info) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            info.setReturnValue(Set.of());
        }
    }

}
