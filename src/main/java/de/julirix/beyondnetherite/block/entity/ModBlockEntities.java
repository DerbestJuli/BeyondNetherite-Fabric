package de.julirix.beyondnetherite.block.entity;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<SmelterBlockEntity> SMELTER_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, BeyondNetherite.id("smelter_be"), FabricBlockEntityTypeBuilder.create(
                    SmelterBlockEntity::new, ModBlocks.SMELTER_WORKBENCH
            ).build());

    public static void registerModBlockEntities() {
        BeyondNetherite.LOGGER.info("Registering Mod Block Entities for " + BeyondNetherite.MOD_ID);
    }
}
