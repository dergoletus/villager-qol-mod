package de.unipassau.fim.sommercamp.mod.client.screens;

import de.unipassau.fim.sommercamp.mod.network.Payload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.List;

import static java.lang.Math.round;

@Environment(EnvType.CLIENT)

public class VHGui extends Screen {
    //screen diamentions
    int screenwidth = 1920;
    int screenheight = 1200;
    double transformation_ratio = 2;
    int realscreenwidth = (int) (screenwidth / transformation_ratio);
    int realscreenheight = (int) (screenheight / transformation_ratio);


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
        int startY = this.height / 4;

        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        // Register the button widget.

        for(int i = 0; i < villagerInfoList.size(); i++) {
            Payload.VillagerInfo villagerInfo = villagerInfoList.get(i);

            int x = (int) (realscreenwidth * 0.52);
            int y = startY + i * spacing;

            this.addDrawableChild(ButtonWidget.builder(Text.of("Trade"),(btn) -> {
                Payload.OpenTradeMenuPayload openTradeMenuPayload = new Payload.OpenTradeMenuPayload(pos, villagerInfo.UUID());
                MinecraftClient.getInstance().setScreen(null);
                ClientPlayNetworking.send(openTradeMenuPayload);
            }).dimensions(x,y,buttonWidth,buttonHeight).build());

            this.addDrawableChild(ButtonWidget.builder(Text.of("Remove"),(btn) -> {
                Payload.RemoveVillagerPayload removeVillagerPayload = new Payload.RemoveVillagerPayload(pos, villagerInfo.UUID());
                ClientPlayNetworking.send(removeVillagerPayload);
                // Remove-Knopf schließt die GUI und der Server schickt ein neues Packet, dass die GUI erneut öffnet
                // So kann die GUI aktualisiert werden
                MinecraftClient.getInstance().setScreen(null);
            }).dimensions(x + 80,y,buttonWidth,buttonHeight).build());
        }

        this.addDrawableChild(ButtonWidget.builder(Text.of("Close"),(btn) -> {
            MinecraftClient.getInstance().setScreen(null);
        }).dimensions(realscreenwidth/2 -60, (int) (realscreenheight*0.8),120,20).build());
        System.out.println(realscreenheight );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        int spacing = 25; // space between buttons
        int startY = this.height / 4;
        for(int i = 0; i < villagerInfoList.size(); i++){
            Payload.VillagerInfo villagerInfo = villagerInfoList.get(i);
            int x = (int) (realscreenwidth*0.35);
            int y = startY + 25 + i * spacing;
            context.drawText(this.textRenderer, villagerInfo.profession() + ", " + villagerInfo.level(), x, y - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        }
        context.drawText(this.textRenderer, "Villager Hub", realscreenwidth/2 -35, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
    }
}
