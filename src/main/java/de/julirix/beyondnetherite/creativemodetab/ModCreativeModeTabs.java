package de.julirix.beyondnetherite.creativemodetab;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab BEYOND_NETHERITE_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            BeyondNetherite.id("beyond_netherite_tab"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PURPURIT))
                    .title(Component.translatable("creativetab.beyondnetherite.beyondnetherite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Items
                        output.accept(ModItems.PURPURIT);
                        output.accept(ModItems.RAW_PURPURIT);
                        output.accept(ModItems.PURPURIT_PARTICALS);
                        output.accept(ModItems.PURPURIT_TEMPLATE);
                        //Tools
                        output.accept(ModItems.PURPURIT_SWORD);
                        output.accept(ModItems.PURPURIT_AXE);
                        output.accept(ModItems.PURPURIT_PICKAXE);
                        output.accept(ModItems.PURPURIT_SHOVEL);
                        output.accept(ModItems.PURPURIT_HOE);
                        output.accept(ModItems.PURPURIT_SPEAR);
                        //Armor
                        output.accept(ModItems.PURPURIT_HELMET);
                        output.accept(ModItems.PURPURIT_CHESTPLATE);
                        output.accept(ModItems.PURPURIT_LEGGINGS);
                        output.accept(ModItems.PURPURIT_BOOTS);
                        output.accept(ModItems.PURPURIT_HORSE_ARMOR);
                        output.accept(ModItems.PURPURIT_NAUTILUS_ARMOR);
                        //Blcks
                        output.accept(ModBlocks.PURPURIT_END_ORE);
                        output.accept(ModBlocks.PURPURIT_BLOCK);
                        output.accept(ModBlocks.SMASHER_BLOCK);
                        output.accept(ModBlocks.SMELTER_WORKBENCH);
                    }).build());

    public static void registerModCreativeModeTabs() {
        BeyondNetherite.LOGGER.info("Registering Creative Mode Tab for " + BeyondNetherite.MOD_ID);
    }
}
