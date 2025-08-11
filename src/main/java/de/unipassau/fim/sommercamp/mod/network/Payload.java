package de.unipassau.fim.sommercamp.mod.network;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class Payload {
    // Dieses Record enthält eine UUID, eine Profession, das Level des Villagers und eine Integerliste, die Items angibt, die out of Stock sind.
    // Es kann aber leicht erweitert werden, um weitere Daten über Villager vom Server an den Client zu senden.
    public record VillagerInfo(String UUID, String profession, String level, String name, List<ItemStack> outOfStockItems) {}

    // In diesem Record wird die Payload definiert, die an den Client geschickt wird.
    public record VHPayload(BlockPos pos, List<VillagerInfo> villagerInfos) implements CustomPayload {
        public static final Identifier VHPayloadID = Identifier.of(VillagerQOLMod.MOD_ID, "vh_payload");
        public static final CustomPayload.Id<VHPayload> ID = new CustomPayload.Id<>(VHPayloadID);

        // PacketCodecs sind erforderlich, um Minecraft zu sagen, wie die versendeten Objekte verpackt und entpackt werden können.
        // Weil diese Payload eine Liste von Objekten einer eigenen Klasse verschickt, wird das hier etwas kompliziert.
        // -Felix

        // Zunächst wird ein Codec für das eigene Villager-Info Record erstellt.
        // Mittels packetCodec.collect(PacketCodecs.toList()) kann leicht ein Codec für eine Liste erstellt werden.
        public static final PacketCodec<RegistryByteBuf, VillagerInfo> VILLAGER_INFO_CODEC = PacketCodec.tuple(
                PacketCodecs.STRING, VillagerInfo::UUID,
                PacketCodecs.STRING, VillagerInfo::profession,
                PacketCodecs.STRING, VillagerInfo::level,
                PacketCodecs.STRING, VillagerInfo::name,
                ItemStack.PACKET_CODEC.collect(PacketCodecs.toList()), VillagerInfo::outOfStockItems,
                VillagerInfo::new
        );

        // Nun wird ein Codec erstellt, der Anweisungen für die gesamte Payload enthält.
        public static final PacketCodec<RegistryByteBuf, VHPayload> CODEC = PacketCodec.tuple(
                BlockPos.PACKET_CODEC, VHPayload::pos,
                VILLAGER_INFO_CODEC.collect(PacketCodecs.toList()), VHPayload::villagerInfos,
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
