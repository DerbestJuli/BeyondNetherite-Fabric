package de.julirix.beyondnetherite.worldgen;

import de.julirix.beyondnetherite.BeyondNetherite;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> END_PURPURIT_ORE_PLACED_KEY = registerKey("end_purpurit_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, END_PURPURIT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_PURPURIT_ORE_KEY),
                ModOrePlacement.commenOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(BeyondNetherite.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifier) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifier)));
    }
}
