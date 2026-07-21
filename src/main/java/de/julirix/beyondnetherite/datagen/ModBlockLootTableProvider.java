package de.julirix.beyondnetherite.datagen;

import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.PURPURIT_BLOCK);
        dropSelf(ModBlocks.SMASHER_BLOCK);
        dropSelf(ModBlocks.SMELTER_WORKBENCH);

        add(ModBlocks.PURPURIT_END_ORE,
                createOreDrop(ModBlocks.PURPURIT_END_ORE, ModItems.RAW_PURPURIT));
    }
}
