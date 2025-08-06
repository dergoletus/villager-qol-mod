package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import de.unipassau.fim.sommercamp.mod.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final String VILLAGER_QOL_GROUP_TRANSLATION_KEY = "itemGroup.villager_qol_group";

    public static final ItemGroup VILLAGER_QOL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.STOP_STICK))
            .displayName(Text.translatable(VILLAGER_QOL_GROUP_TRANSLATION_KEY))
            .entries(((displayContext, entries) -> {
                entries.add(new ItemStack(ModItems.STOP_STICK));
                entries.add(new ItemStack(ModItems.RESUME_STICK));
                entries.add(new ItemStack(ModItems.VILLAGER_REGISTER));
                entries.add(new ItemStack(ModItems.EMERALD_BLADE));
                entries.add(new ItemStack(ModItems.COMPRESSED_EMERALD));
                entries.add(new ItemStack(ModBlocks.COMPRESSED_EMERALD_BLOCK));
            }))
            .build();

    public static void init() {
        VillagerQOLMod.LOGGER.info("Adding Item Groups");
        Identifier demoGroupId = Identifier.of(VillagerQOLMod.MOD_ID, "villager_qol_group");
        Registry.register(Registries.ITEM_GROUP, demoGroupId, VILLAGER_QOL_GROUP);
    }

}
