package de.unipassau.fim.sommercamp.mod.client.screens;

import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.List;

@Environment(EnvType.CLIENT)

public class VHGui extends Screen {

    private static List<Payload.VillagerInfo> villagerInfoList;

    public VHGui(Text title, List<Payload.VillagerInfo> villagerInfos) {
        super(title);
        villagerInfoList = villagerInfos;
    }

    @Override
    protected void init() {
        int buttonWidth = 75;
        int buttonHeight = 20;
        int spacing = 25; // space between buttons
        int startY = this.height / 4;

        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        // Register the button widget.

        for(Payload.VillagerInfo villagerInfo : villagerInfoList){

            int x = ((this.width - buttonWidth) / 2 ) + 80;
            int y = startY + i * spacing;

            this.addDrawableChild(ButtonWidget.builder(Text.of("Trade"),(btn) -> {

            }).dimensions(x,y,buttonWidth,buttonHeight).build());

            this.addDrawableChild(ButtonWidget.builder(Text.of("Remove"),(btn) -> {

            }).dimensions(x + 80,y,buttonWidth,buttonHeight).build());
        }

        this.addDrawableChild(ButtonWidget.builder(Text.of("Close"),(btn) -> {
            MinecraftClient.getInstance().setScreen(null);
        }).dimensions(160,200,120,20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        for(Payload.VillagerInfo villagerInfo : villagerInfoList){
            context.drawText(this.textRenderer, villagerInfo.profession() + ", " + villagerInfo.level(), 160, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        }
        context.drawText(this.textRenderer, "Villager Hub", 160, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
    }
}
