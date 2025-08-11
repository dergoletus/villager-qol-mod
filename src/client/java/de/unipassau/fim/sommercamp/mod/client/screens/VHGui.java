package de.unipassau.fim.sommercamp.mod.client.screens;

import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.List;

@Environment(EnvType.CLIENT)

public class VHGui extends Screen {
    //screen diamentions
    int screenwidth = 1920;
    int screenheight = 1200;
    double transformation_ratio = 2;
    int realscreenwidth = (int) (screenwidth / transformation_ratio);
    int realscreenheight = (int) (screenheight / transformation_ratio);

    // StartX and Y for villager descriptions
    int descriptionX = (int) (realscreenwidth * 0.35);
    int descriptionY = 100;

    // ButtonX and Y for villager buttons
    int buttonX = descriptionX + 300;
    int buttonY = descriptionY;

    private final List<Payload.VillagerInfo> villagerInfoList;

    public BlockPos pos;

    public VHGui(Text title, List<Payload.VillagerInfo> villagerInfos, BlockPos pos) {
        super(title);
        this.villagerInfoList = villagerInfos;
        this.pos = pos;
    }

    @Override
    protected void init() {
        int buttonWidth = 75;
        int buttonHeight = 20;
        int spacing = 25; // space between buttons

        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        // Register the button widget.

        for(int i = 0; i < villagerInfoList.size(); i++) {
            Payload.VillagerInfo villagerInfo = villagerInfoList.get(i);

            int x = buttonX;
            int y = buttonY + i * spacing;

            // Der Trade-Knopf sollte nur dann hinzugefügt werden, wenn der Villager eine Arbeit hat.
            if (!villagerInfo.profession().equals("None")) {
                this.addDrawableChild(ButtonWidget.builder(Text.of("Trade"), (btn) -> {
                    Payload.OpenTradeMenuPayload openTradeMenuPayload = new Payload.OpenTradeMenuPayload(pos, villagerInfo.UUID());
                    MinecraftClient.getInstance().setScreen(null);
                    ClientPlayNetworking.send(openTradeMenuPayload);
                }).dimensions(x, y, buttonWidth, buttonHeight).build());
            }

            this.addDrawableChild(ButtonWidget.builder(Text.of("Remove"),(btn) -> {
                Payload.RemoveVillagerPayload removeVillagerPayload = new Payload.RemoveVillagerPayload(pos, villagerInfo.UUID());
                ClientPlayNetworking.send(removeVillagerPayload);
                // Remove-Knopf schließt die GUI und der Server schickt ein neues Packet, dass die GUI erneut öffnet
                // So kann die GUI aktualisiert werden
                MinecraftClient.getInstance().setScreen(null);
            }).dimensions(x + 80, y, buttonWidth, buttonHeight).build());
        }

        this.addDrawableChild(ButtonWidget.builder(Text.of("Close"),(btn) ->
                MinecraftClient.getInstance().setScreen(null)).dimensions(realscreenwidth / 2 - 60, (int) (realscreenheight * 0.8),120,20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Draw header and information

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow

        context.drawText(this.textRenderer, "Villager Hub", realscreenwidth / 2 - 35, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, "Store villagers in this block to trade with them easily!", 30, 60 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, "Villagers that are dead or out of render distance are automatically removed from the block.", 30, 75 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);

        int spacing = 25; // space between buttons

        for (int i = 0; i < villagerInfoList.size(); i++){
            Payload.VillagerInfo villagerInfo = villagerInfoList.get(i);

            int x = descriptionX;
            int y = descriptionY + 25 + i * spacing;

            String displayText = (i + 1) + ". ";
            if (!villagerInfo.name().isEmpty()) {
                displayText += villagerInfo.name();
            } else if (!villagerInfo.profession().equals("None")) {
                displayText += villagerInfo.level() + " " + villagerInfo.profession();
            } else {
                displayText += "Jobless Villager";
            }

            int endOfTextX = context.drawText(this.textRenderer, displayText, x, y - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);


            if (!villagerInfo.outOfStockItems().isEmpty()) {
                // Nun werden die Icons hinzugefügt, die anzeigen, welche trades out of stock sind.
                endOfTextX += 5;
                int barrierX = endOfTextX;
                int barrierY = y - 23;
                context.drawItem(new ItemStack(Items.BARRIER), barrierX, barrierY);

                int barrierCenterX = barrierX + 8;
                int barrierCenterY = barrierY + 8;
                if (barrierCenterX - 8 < mouseX && mouseX < barrierCenterX + 8 && barrierCenterY - 8 < mouseY && mouseY < barrierCenterY + 8) {
                    context.drawTooltip(this.textRenderer, Text.of("The following items are out of stock:"), barrierCenterX, barrierCenterY);
                }

                for (int j = 0; j < villagerInfo.outOfStockItems().size(); j++) {
                    ItemStack outOfStockItem = villagerInfo.outOfStockItems().get(j);
                    int itemX = endOfTextX + 17 * (j + 1);
                    int itemY = y - 23;
                    context.drawItem(outOfStockItem, itemX, itemY);

                    int itemCenterX = itemX + 8;
                    int itemCenterY = itemY + 8;
                    if (itemCenterX - 8 < mouseX && mouseX < itemCenterX + 8 && itemCenterY - 8 < mouseY && mouseY < itemCenterY + 8) {
                        context.drawItemTooltip(this.textRenderer, outOfStockItem, itemCenterX, itemCenterY);
                    }
                }
            }

            // Neben dem Text werden noch Icons angezeigt, die einen gewissen Charme in die Sache bringen.
            if (!villagerInfo.profession().equals("None")) {
                int itemX = buttonX - 40;
                int itemY = y - 23;
                ItemStack workstationItem = getWorkstationItem(villagerInfo.profession());
                context.drawItem(workstationItem, itemX, itemY);

                int itemCenterX = itemX + 8;
                int itemCenterY = itemY + 8;
                if (itemCenterX - 8 < mouseX && mouseX < itemCenterX + 8 && itemCenterY - 8 < mouseY && mouseY < itemCenterY + 8) {
                    context.drawItemTooltip(this.textRenderer, workstationItem, itemCenterX, itemCenterY);
                }

                itemX = buttonX - 20;
                ItemStack levelItem = getLevelItem(villagerInfo.level());
                context.drawItem(levelItem, itemX, itemY);

                itemCenterX = itemX + 8;
                itemCenterY = itemY + 8;
                if (itemCenterX - 8 < mouseX && mouseX < itemCenterX + 8 && itemCenterY - 8 < mouseY && mouseY < itemCenterY + 8) {
                    context.drawItemTooltip(this.textRenderer, levelItem, itemCenterX, itemCenterY);
                }

            }
        }
    }

    private ItemStack getWorkstationItem(String profession) {
        return switch (profession) {
            case "Armorer" -> new ItemStack(Items.BLAST_FURNACE);
            case "Butcher" -> new ItemStack(Items.SMOKER);
            case "Cartographer" -> new ItemStack(Items.CARTOGRAPHY_TABLE);
            case "Cleric" -> new ItemStack(Items.BREWING_STAND);
            case "Farmer" -> new ItemStack(Items.COMPOSTER);
            case "Fisherman" -> new ItemStack(Items.BARREL);
            case "Fletcher" -> new ItemStack(Items.FLETCHING_TABLE);
            case "Leatherworker" -> new ItemStack(Items.CAULDRON);
            case "Librarian" -> new ItemStack(Items.LECTERN);
            case "Mason" -> new ItemStack(Items.STONECUTTER);
            case "Shepherd" -> new ItemStack(Items.LOOM);
            case "Toolsmith" -> new ItemStack(Items.SMITHING_TABLE);
            case "Weaponsmith" -> new ItemStack(Items.GRINDSTONE);
            default -> ItemStack.EMPTY;
        };
    }

    private ItemStack getLevelItem(String level) {
        return switch (level) {
            case "Novice" -> new ItemStack(Items.CLAY_BALL);
            case "Apprentice" -> new ItemStack(Items.BRICK);
            case "Journeyman" -> new ItemStack(Items.GOLD_INGOT);
            case "Expert" -> new ItemStack(Items.EMERALD);
            case "Master" -> new ItemStack(Items.DIAMOND);
            default -> ItemStack.EMPTY;
        };
    }
}
