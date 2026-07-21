package de.julirix.beyondnetherite.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomeModifiers {
    public static void bootstrap() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_END),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.END_PURPURIT_ORE_PLACED_KEY
        );
    }
}