package de.julirix.beyondnetherite.screen.custom;

import de.julirix.beyondnetherite.BeyondNetherite;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class SmelterScreen extends AbstractContainerScreen<SmelterMenu> {
    private static final Identifier GUI_TEXTURE =
            BeyondNetherite.id("textures/gui/smelter/smelter_gui.png");
    private static final Identifier ARROW_TEXTURE =
            BeyondNetherite.id("textures/gui/arrow_progress.png");

    public SmelterScreen(SmelterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 175, 208);
        this.inventoryLabelY = 114;
        this.titleLabelY = 8;
    }

    @Override
    public void extractBackground(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);

        int x = this.leftPos;
        int y = this.topPos;

        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y,
                0.0F, 0.0F, 175, 208, 256, 256); //GUI render

        renderProgressArrow(graphics, x, y); //Arrow render
    }

    private void renderProgressArrow(GuiGraphicsExtractor graphics, int x, int y) {
        int arrowWidth = menu.getScaledArrowProgress();
        if (arrowWidth > 0) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 120, y + 58,
                    0, 0, arrowWidth, 16, 24, 16);
        }
    }

    @Override
    protected void extractLabels(@NonNull GuiGraphicsExtractor graphics, int xm, int ym) {
        super.extractLabels(graphics, xm , ym);
        this.extractTooltip(graphics, xm, ym);
    }
}
