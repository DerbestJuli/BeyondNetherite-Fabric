package de.julirix.beyondnetherite;

import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.block.entity.ModBlockEntities;
import de.julirix.beyondnetherite.creativemodetab.ModCreativeModeTabs;
import de.julirix.beyondnetherite.item.ModItems;
import de.julirix.beyondnetherite.screen.ModMenuTypes;
import de.julirix.beyondnetherite.screen.custom.SmelterScreen;
import net.fabricmc.api.ModInitializer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeyondNetherite implements ModInitializer {
	public static final String MOD_ID = "beyondnetherite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerModBlockEntities();
		ModMenuTypes.registerModMenuTypes();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
