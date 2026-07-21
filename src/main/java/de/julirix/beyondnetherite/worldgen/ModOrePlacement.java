package de.julirix.beyondnetherite.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacment, PlacementModifier pHeightRange) {
        return List.of(pCountPlacment, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commenOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
