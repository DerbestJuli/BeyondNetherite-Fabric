package de.julirix.beyondnetherite;

import de.julirix.beyondnetherite.screen.ModMenuTypes;
import de.julirix.beyondnetherite.screen.custom.SmelterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class BeyondNetheriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.SMELTER_MENU, SmelterScreen::new);
    }
}