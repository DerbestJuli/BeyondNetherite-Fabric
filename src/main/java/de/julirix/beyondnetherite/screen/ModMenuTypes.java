package de.julirix.beyondnetherite.screen;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.screen.custom.SmelterMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<SmelterMenu> SMELTER_MENU =
            Registry.register(BuiltInRegistries.MENU, BeyondNetherite.id("smelter_menu"), new ExtendedMenuType<>(SmelterMenu::new, BlockPos.STREAM_CODEC));


    public static void registerModMenuTypes() {
        BeyondNetherite.LOGGER.info("Registering Mod Menu Types for " + BeyondNetherite.MOD_ID);
    }
}
