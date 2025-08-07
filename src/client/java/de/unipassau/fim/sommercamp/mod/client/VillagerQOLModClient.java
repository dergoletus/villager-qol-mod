package de.unipassau.fim.sommercamp.mod.client;

import de.unipassau.fim.sommercamp.mod.client.screens.VHGui;
import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;

import java.util.Dictionary;
import java.util.Hashtable;

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
            MinecraftClient.getInstance().setScreen(
                    new VHGui(Text.empty())
            );
            // Your custom logic here
        });

        // Populate Dictionary for Villager Level conversion from int to String
        VILLAGER_LEVEL_DICTIONARY.put(0, "None");
        VILLAGER_LEVEL_DICTIONARY.put(1, "Novice");
        VILLAGER_LEVEL_DICTIONARY.put(2, "Apprentice");
        VILLAGER_LEVEL_DICTIONARY.put(3, "Journeyman");
        VILLAGER_LEVEL_DICTIONARY.put(4, "Expert");
        VILLAGER_LEVEL_DICTIONARY.put(5,"Master");
    }

    public static String getProfessionName(String profession) {
        String professionNameLowerCase = profession.split(":")[1];
        return professionNameLowerCase.substring(0, 1).toUpperCase() + professionNameLowerCase.substring(1);
    }

}
