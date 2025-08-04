package de.unipassau.fim.sommercamp.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SommercampMod implements ModInitializer {

    public static final String MOD_ID = "sommercamp-mod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
              new TradedItem(Items.EMERALD, 1),
                    Optional.of(new TradedItem(Items.DIAMOND, 1)),
              new ItemStack(Items.ENCHANTED_BOOK, 10), 0, 999999, 100,  0)
            );
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    Optional.of(new TradedItem(Items.DIAMOND, 2)),
                    new ItemStack(Items.ENCHANTED_BOOK, 10), 0, 999999, 100,  0)
            );
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    Optional.of(new TradedItem(Items.DIAMOND, 3)),
                    new ItemStack(Items.ENCHANTED_BOOK, 10), 0, 999999, 100,  0)
            );
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    Optional.of(new TradedItem(Items.DIAMOND, 4)),
                    new ItemStack(Items.ENCHANTED_BOOK, 10), 0, 999999, 100,  0)
            );
        });
    }

}
