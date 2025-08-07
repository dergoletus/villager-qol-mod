package de.unipassau.fim.sommercamp.mod.client.screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)

public class VHGui extends Screen {

    public VHGui(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 100;
        int buttonHeight = 20;
        int spacing = 25; // space between buttons
        int startY = this.height / 4;

        ButtonWidget villager1 = ButtonWidget.builder(Text.of("Villager Hub"), (btn) -> {
            // When the button is clicked, we can display a toast to the screen.
            this.client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("Hello World!"), Text.of("This is a toast."))
            );
        }).dimensions(40, 40, 120, 20).build();

        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.
        this.addDrawableChild(villager1);
        // Register the button widget.

        for(int i = 0; i < 3; i ++){

            int x = (this.width - buttonWidth) / 2;
            int y = startY + i * spacing;

            this.addDrawableChild(ButtonWidget.builder(Text.of("Trade"),(btn) -> {

            }).dimensions(x,y,buttonWidth,buttonHeight).build());


        }

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        context.drawText(this.textRenderer, "Special Button", 40, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
    }
}
