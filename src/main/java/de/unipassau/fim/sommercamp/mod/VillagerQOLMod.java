package de.unipassau.fim.sommercamp.mod;

import de.unipassau.fim.sommercamp.mod.BlockEntities.ModBlockEntities;
import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import de.unipassau.fim.sommercamp.mod.events.ModEvents;
import de.unipassau.fim.sommercamp.mod.items.ModItemGroups;
import de.unipassau.fim.sommercamp.mod.items.ModItems;
import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static de.unipassau.fim.sommercamp.mod.items.EmeraldBladeItem.restockCooldowns;

public class VillagerQOLMod implements ModInitializer {

    public static final String MOD_ID = "villager-qol";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.init();
        ModItemGroups.init();
        ModBlocks.init();
        ModBlockEntities.init();
        ModEvents.init();

        PayloadTypeRegistry.playC2S().register(Payload.RemoveVillagerPayload.ID, Payload.RemoveVillagerPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(Payload.VHPayload.ID, Payload.VHPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(Payload.RemoveVillagerPayload.ID, (payload, context) -> {
           context.player().getServer().execute(() -> {
              System.out.println("Client requested remove for villager " + payload.villagerUUID());
           });
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            restockCooldowns.replaceAll((uuid, ticks) -> Math.max(0, ticks - 1));
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> CooldownHandler.tick());
        PayloadTypeRegistry.playS2C().register(payload.VHpayload.ID, payload.VHpayload.CODEC);
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                new TradedItem(Items.EMERALD, 64),
                Optional.of(new TradedItem(Items.BOOK, 1)),
                enchantedBook(entity, Enchantments.SHARPNESS, 5),
                0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.EFFICIENCY, 5),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.PROTECTION, 4),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.AQUA_AFFINITY, 1),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.DEPTH_STRIDER, 3),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.FEATHER_FALLING, 4),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.RESPIRATION, 3),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.BREACH, 4),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.DENSITY, 5),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.FIRE_ASPECT, 2),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.LOOTING, 3),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.SMITE, 5),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.SWEEPING_EDGE, 3),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.FLAME, 1),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.INFINITY, 1),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.POWER, 5),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.PUNCH, 2),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.FORTUNE, 3),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.SILK_TOUCH, 1),
                    0, 999999, 100, 0));

            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 64),
                    Optional.of(new TradedItem(Items.BOOK, 1)),
                    enchantedBook(entity, Enchantments.MENDING, 1),
                    0, 999999, 100, 0));
        });
    }

    public static ItemStack enchantedBook(Entity entity, RegistryKey<Enchantment> registryKey, int level) {
        var entry = entity.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(registryKey.getValue()).orElseThrow();
        return EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(entry, level));
    }

}
