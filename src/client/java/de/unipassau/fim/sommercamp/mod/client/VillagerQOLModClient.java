package de.unipassau.fim.sommercamp.mod.client;

import de.unipassau.fim.sommercamp.mod.client.screens.VHGui;
import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class VillagerQOLModClient implements ClientModInitializer {

    public static Dictionary<Integer, String> VILLAGER_LEVEL_DICTIONARY = new Hashtable<>();

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(Payload.VHPayload.ID, (payload, context) -> {
            ClientWorld world = context.client().world;

            if (world == null) {
                return;
            }

            System.out.println("used");
            // Convert payload villagerInfos to villagerInfos with readable data
            List<Payload.VillagerInfo> readableVillagerInfos = new ArrayList<>();
            for (Payload.VillagerInfo villagerInfo : payload.villagerInfos()) {
                readableVillagerInfos.add(new Payload.VillagerInfo(
                        villagerInfo.UUID(),
                        getProfessionName(villagerInfo.profession()),
                        villagerInfo.level()
                ));
            }

            MinecraftClient.getInstance().setScreen(
                    new VHGui(Text.literal("Villager Hub"), readableVillagerInfos)
            );
            // Your custom logic here
        });
    }

    public static String getProfessionName(String profession) {
        String professionNameLowerCase = profession.split(":")[1];
        return professionNameLowerCase.substring(0, 1).toUpperCase() + professionNameLowerCase.substring(1);
    }

}
