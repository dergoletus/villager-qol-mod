package de.unipassau.fim.sommercamp.mod.client;

import de.unipassau.fim.sommercamp.mod.client.screens.VHGui;
import de.unipassau.fim.sommercamp.mod.network.payload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;

public class VillagerQOLModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(payload.VHpayload.ID, (payload, context) -> {
            ClientWorld world = context.client().world;

            if (world == null) {
                return;
            }

            if (!world.isClient) {
                System.out.println("used");
                MinecraftClient.getInstance().setScreen(
                        new VHGui(Text.empty())
                );
                // Your custom logic here
            }
        });
    }

}
