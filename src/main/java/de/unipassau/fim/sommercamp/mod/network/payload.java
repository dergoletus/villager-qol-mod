package de.unipassau.fim.sommercamp.mod.network;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class payload {
    public record VHpayload(BlockPos pos) implements CustomPayload {
        public static final Identifier SUMMON_LIGHTNING_PAYLOAD_ID = Identifier.of(VillagerQOLMod.MOD_ID, "summon_lightning");
        public static final CustomPayload.Id<VHpayload> ID = new CustomPayload.Id<>(SUMMON_LIGHTNING_PAYLOAD_ID);
        public static final PacketCodec<RegistryByteBuf, VHpayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, VHpayload::pos, VHpayload::new);

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}
