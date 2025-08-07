package de.unipassau.fim.sommercamp.mod.network;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Payload {
    // Dieses Record enthält eine UUID, eine Profession und das Level des Villagers.
    // Es kann aber leicht erweitert werden, um weitere Daten über Villager vom Server an den Client zu senden.
    public record VillagerInfo(String UUID, String profession, String level) {}

    // In diesem Record wird die Payload definiert, die an den Client geschickt wird.
    public record VHPayload(BlockPos pos, List<VillagerInfo> villagerInfos) implements CustomPayload {
        public static final Identifier VHPayloadID = Identifier.of(VillagerQOLMod.MOD_ID, "vh_payload");
        public static final CustomPayload.Id<VHPayload> ID = new CustomPayload.Id<>(VHPayloadID);

        // PacketCodecs sind erforderlich, um Minecraft zu sagen, wie die versendeten Objekte verpackt und entpackt werden können.
        // Weil diese Payload eine Liste von Objekten einer eigenen Klasse verschickt, wird das hier etwas kompliziert.
        // ChatGPT hat einen Großteil dieses Codes generiert und ich habe nicht alles verstanden.
        // -Felix

        // Zunächst wird ein Codec für das eigene Villager-Info Record erstellt.
        public static final PacketCodec<RegistryByteBuf, VillagerInfo> VILLAGER_INFO_CODEC = PacketCodec.tuple(
                PacketCodecs.STRING, VillagerInfo::UUID,
                PacketCodecs.STRING, VillagerInfo::profession,
                PacketCodecs.STRING, VillagerInfo::level,
                VillagerInfo::new
        );

        // Nun wird ein Codec erstellt, der erklärt, wie Listen von VillagerInfo-Records gehandhabt werden.
        // Dabei wird auf den VILLAGER_INFO_CODEC zurückgegriffen.
        public static final PacketCodec<RegistryByteBuf, List<VillagerInfo>> VILLAGER_INFO_LIST_CODEC =
                PacketCodec.of(
                        (list, buf) -> {
                            buf.writeVarInt(list.size());
                            for (VillagerInfo info : list) {
                                VILLAGER_INFO_CODEC.encode(buf, info);
                            }
                        },
                        (buf) -> {
                            int size = buf.readVarInt();
                            List<VillagerInfo> list = new ArrayList<>(size);
                            for (int i = 0; i < size; i++) {
                                list.add(VILLAGER_INFO_CODEC.decode(buf));
                            }
                            return list;
                        }
                );

        // Abschließend wird ein Codec erstellt, der Anweisungen für die gesamte Payload enthält.
        public static final PacketCodec<RegistryByteBuf, VHPayload> CODEC = PacketCodec.tuple(
                BlockPos.PACKET_CODEC, VHPayload::pos,
                VILLAGER_INFO_LIST_CODEC, VHPayload::villagerInfos,
                VHPayload::new);

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public record RemoveVillagerPayload(BlockPos pos, String villagerUUID) implements CustomPayload {
        public static final Identifier RemoveVillagerPayloadID = Identifier.of(VillagerQOLMod.MOD_ID,"remove_villager_payload");
        public static final CustomPayload.Id<RemoveVillagerPayload> ID = new CustomPayload.Id<>(RemoveVillagerPayloadID);

        public static final PacketCodec<RegistryByteBuf, RemoveVillagerPayload> CODEC = PacketCodec.tuple(
                BlockPos.PACKET_CODEC, RemoveVillagerPayload::pos,
                PacketCodecs.STRING, RemoveVillagerPayload::villagerUUID,
                RemoveVillagerPayload::new
        );

        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public record OpenTradeMenuPayload(BlockPos pos, String villagerUUID) implements CustomPayload {
        public static final Identifier OpenTradeMenuPayloadID = Identifier.of(VillagerQOLMod.MOD_ID, "open_trade_menu_payload_id");
        public static final CustomPayload.Id<OpenTradeMenuPayload> ID = new CustomPayload.Id<>(OpenTradeMenuPayloadID);

        public static final PacketCodec<RegistryByteBuf, OpenTradeMenuPayload> CODEC = PacketCodec.tuple(
                BlockPos.PACKET_CODEC, OpenTradeMenuPayload::pos,
                PacketCodecs.STRING, OpenTradeMenuPayload::villagerUUID,
                OpenTradeMenuPayload::new
        );

        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}
