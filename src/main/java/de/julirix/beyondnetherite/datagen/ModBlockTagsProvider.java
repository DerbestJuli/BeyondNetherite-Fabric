package de.julirix.beyondnetherite.datagen;

import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PURPURIT_END_ORE.builtInRegistryHolder().key())
                .add(ModBlocks.PURPURIT_BLOCK.builtInRegistryHolder().key())
                .add(ModBlocks.SMELTER_WORKBENCH.builtInRegistryHolder().key())
                .add(ModBlocks.SMASHER_BLOCK.builtInRegistryHolder().key());

        tag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.PURPURIT_END_ORE.builtInRegistryHolder().key())
                .add(ModBlocks.PURPURIT_BLOCK.builtInRegistryHolder().key())
                .add(ModBlocks.SMASHER_BLOCK.builtInRegistryHolder().key());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SMELTER_WORKBENCH.builtInRegistryHolder().key());

        tag(ModTags.Blocks.NEEDS_PURPURIT_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_PURPURIT_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PURPURIT_TOOL);
    }
}
