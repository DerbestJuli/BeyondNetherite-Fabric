package de.julirix.beyondnetherite;

import de.julirix.beyondnetherite.datagen.*;
import de.julirix.beyondnetherite.worldgen.ModConfiguredFeatures;
import de.julirix.beyondnetherite.worldgen.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class BeyondNetheriteDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);

		var blockTagsProvider = pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider((output, registriesFuture) ->
				new ModItemTagsProvider(output, registriesFuture, blockTagsProvider));

		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModDatapackProvider::new);
		pack.addProvider((FabricDataGenerator.Pack.Factory<ModEquipmentAssetProvider>) ModEquipmentAssetProvider::new);
	}
}
