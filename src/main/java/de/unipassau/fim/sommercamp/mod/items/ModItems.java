package de.unipassau.fim.sommercamp.mod.items;

import de.unipassau.fim.sommercamp.mod.VillagerQOLMod;
import de.unipassau.fim.sommercamp.mod.components.ModComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Function;

public class ModItems {


    public static final String STOP_STICK_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.stop_stick.description";
    public static final StopStickItem STOP_STICK = register("stop_stick",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(STOP_STICK_DESCRIPTION_TRANSLATION_KEY))))
                    .maxCount(1),
            StopStickItem::new);

    public static final String RESUME_STICK_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.resume_stick.description";
    public static final ResumeStickItem RESUME_STICK = register("resume_stick",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(RESUME_STICK_DESCRIPTION_TRANSLATION_KEY))))
                    .maxCount(1),
            ResumeStickItem::new);

    // The Villager Register class stores its own translation keys
    public static final VillagerRegisterItem VILLAGER_REGISTER = register("villager_register",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, VillagerRegisterItem.createLoreComponent())
                    .component(ModComponents.VILLAGER_REGISTER_UUID_COMPONENT, "")
                    .maxCount(1),
            VillagerRegisterItem::new);

    public static final String EMERALD_BLADE_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.emerald_blade.description";

    public static final EmeraldBladeItem EMERALD_BLADE = register("emerald_blade",
            new Item.Settings()
                    // Results in 7 attack damage and 1.6 attack speed
                    .sword(ToolMaterial.NETHERITE, 2, -2.4f)
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(EMERALD_BLADE_DESCRIPTION_TRANSLATION_KEY))))
                    .maxCount(1),
                    EmeraldBladeItem::new);

    public static final String EMERALD_WAND_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.emerald_wand.description";
    public static final EmeraldWandItem EMERALD_WAND = register("emerald_wand",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(EMERALD_WAND_DESCRIPTION_TRANSLATION_KEY))))
                    .maxCount(1),
            EmeraldWandItem::new);

    public static final String EMERALD_MACE_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.emerald_mace.description";
    public static final EmeraldMaceItem EMERALD_MACE = register("emerald_mace",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(EMERALD_MACE_DESCRIPTION_TRANSLATION_KEY))))
                    .maxCount(1),
            EmeraldMaceItem::new);

    public static final String COMPRESSED_EMERALD_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.compressed_emerald.description";
    public static final Item COMPRESSED_EMERALD = register("compressed_emerald",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(COMPRESSED_EMERALD_DESCRIPTION_TRANSLATION_KEY))
                    ))
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .maxCount(64),
                    Item::new
            );

    public static final String COMPRESSED_EMERALD_BLOCK_DESCRIPTION_TRANSLATION_KEY = "item.villager-qol.compressed_emerald_block.description";
    public static final Item COMPRESSED_EMERALD_BLOCK = register("compressed_emerald_block",
            new Item.Settings()
                    .component(DataComponentTypes.LORE, new LoreComponent(
                            List.of(Text.translatable(COMPRESSED_EMERALD_BLOCK_DESCRIPTION_TRANSLATION_KEY))
                    ))
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .maxCount(64),
                    Item::new
            );


    public static <T extends Item> T register(String id, Item.Settings settings, Function<Item.Settings, T> factory) {
        Identifier identifier = Identifier.of(VillagerQOLMod.MOD_ID, id);
        RegistryKey<Item> registryKey = RegistryKey.of(Registries.ITEM.getKey(), identifier);

        T item = factory.apply(settings.registryKey(registryKey));

        return Registry.register(Registries.ITEM, identifier, item);
    }

    public static void init() {
        VillagerQOLMod.LOGGER.info("Adding Items");
    }
}
