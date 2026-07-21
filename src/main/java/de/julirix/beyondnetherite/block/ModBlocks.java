package de.julirix.beyondnetherite.block;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.block.custom.SmasherBlock;
import de.julirix.beyondnetherite.block.custom.SmelterBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    // Blocks
    public static final Block PURPURIT_END_ORE = registerBlock("purpurit_end_ore",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.LODESTONE)
                    .lightLevel(state -> 4)
            ));
    public static final Block PURPURIT_BLOCK = registerBlock("purpurit_block",
            properties -> new Block(properties.strength(8f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    // Workbench Blocks
    public static final Block SMASHER_BLOCK = registerBlock("smasher_block",
            properties -> new SmasherBlock(properties.strength(6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANVIL)
            ));
    public static final Block SMELTER_WORKBENCH = registerBlock("smelter_workbench",
            properties -> new SmelterBlock(properties.strength(4f)
                    .sound(SoundType.BASALT)
                    .lightLevel(state -> state.getValue(SmelterBlock.SMELTING) ? 13 : 0)
            ));


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toReturn = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, BeyondNetherite.id(name))));
        registerBlockItem(name, toReturn);
        return Registry.register(BuiltInRegistries.BLOCK, BeyondNetherite.id(name), toReturn);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, BeyondNetherite.id(name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, BeyondNetherite.id(name)))));
    }

    public static void registerModBlocks() {
        BeyondNetherite.LOGGER.info("Registering Mod Blocks for " + BeyondNetherite.MOD_ID);
    }
}
