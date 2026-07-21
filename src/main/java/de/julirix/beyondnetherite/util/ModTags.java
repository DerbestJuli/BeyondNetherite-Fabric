package de.julirix.beyondnetherite.util;

import de.julirix.beyondnetherite.BeyondNetherite;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_PURPURIT_TOOL = createTag("needs_purpurit_tool");
        public static final TagKey<Block> INCORRECT_FOR_PURPURIT_TOOL = createTag("incorrect_for_purpurit_tool");
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("fabric", "needs_tool_level_4")
        );

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, BeyondNetherite.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PURPURIT = createTag("purpurit");
        public static final TagKey<Item> GLAZED_TERRACOTTAS = createTag("glazed_terracottas");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, BeyondNetherite.id(name));
        }
    }
}
