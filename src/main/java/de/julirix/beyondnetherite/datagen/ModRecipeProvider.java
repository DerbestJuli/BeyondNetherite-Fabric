package de.julirix.beyondnetherite.datagen;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.item.ModItems;
import de.julirix.beyondnetherite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import org.jspecify.annotations.NonNull;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPURIT_BLOCK)
                        .pattern("AAA")
                        .pattern("AAA")
                        .pattern("AAA")
                        .define('A', ModItems.PURPURIT)
                        .unlockedBy(getHasName(ModItems.PURPURIT), has(ModItems.PURPURIT))
                        .group("purpurit")
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMASHER_BLOCK)
                        .pattern("A A")
                        .pattern("BCB")
                        .pattern("DDD")
                        .define('A', Items.ECHO_SHARD)
                        .define('B', Items.RESIN_BRICK)
                        .define('C', Items.NETHER_STAR)
                        .define('D', Items.NETHERITE_INGOT)
                        .unlockedBy(getHasName(ModItems.RAW_PURPURIT), has(ModItems.RAW_PURPURIT))
                        .group("purpurit")
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.PURPURIT_TEMPLATE)
                        .pattern("ACA")
                        .pattern("ABA")
                        .pattern("AAA")
                        .define('A', ModItems.PURPURIT_PARTICALS)
                        .define('B', Items.NETHERITE_INGOT)
                        .define('C', Items.END_STONE)
                        .unlockedBy(getHasName(ModItems.PURPURIT), has(ModItems.PURPURIT))
                        .group("purpurit")
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMELTER_WORKBENCH)
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern("CCC")
                        .define('A', ItemTags.GLAZED_TERRACOTTA)
                        .define('B', Items.BLAST_FURNACE)
                        .define('C', Items.NETHERITE_INGOT)
                        .unlockedBy(getHasName(ModItems.PURPURIT_PARTICALS), has(ModItems.PURPURIT_PARTICALS))
                        .group("purpurit")
                        .save(output);
                shapeless(RecipeCategory.MISC, ModItems.PURPURIT, 9)
                        .requires(ModBlocks.PURPURIT_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.PURPURIT_BLOCK), has(ModBlocks.PURPURIT_BLOCK))
                        .group("purpurit")
                        .save(output);
                registerToolUpgrades(output);
                registerArmorUpgrades(output);
                SmithingTrimRecipeBuilder.smithingTrim(
                                Ingredient.of(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE),
                                this.tag(ItemTags.TRIMMABLE_ARMOR),
                                this.tag(ItemTags.TRIM_MATERIALS),
                                this.registries.lookupOrThrow(Registries.TRIM_PATTERN).getOrThrow(TrimPatterns.SPIRE),
                                RecipeCategory.MISC)
                        .unlocks("has_smithing_trim_template", this.has(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE))
                        .save(this.output, ResourceKey.create(
                                Registries.RECIPE,
                                BeyondNetherite.id("purpurit_trim")
                        ));
            }

            private void upgrade(Item base, Item result, RecipeOutput output, String name) {
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(ModItems.PURPURIT_TEMPLATE),
                                Ingredient.of(base),
                                Ingredient.of(ModItems.PURPURIT),
                                RecipeCategory.COMBAT,
                                result
                        )
                        .unlocks("has_" + name.replace("purpurit_", ""), has(base))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE,
                                BeyondNetherite.id(name)
                        ));
            }

            private void registerToolUpgrades(RecipeOutput output) {
                upgrade(Items.NETHERITE_SWORD, ModItems.PURPURIT_SWORD, output, "purpurit_sword");
                upgrade(Items.NETHERITE_PICKAXE, ModItems.PURPURIT_PICKAXE, output, "purpurit_pickaxe");
                upgrade(Items.NETHERITE_AXE, ModItems.PURPURIT_AXE, output, "purpurit_axe");
                upgrade(Items.NETHERITE_SHOVEL, ModItems.PURPURIT_SHOVEL, output, "purpurit_shovel");
                upgrade(Items.NETHERITE_HOE, ModItems.PURPURIT_HOE, output, "purpurit_hoe");
                upgrade(Items.NETHERITE_SPEAR, ModItems.PURPURIT_SPEAR, output, "purpurit_spear");
            }
            private void registerArmorUpgrades(RecipeOutput output) {
                upgrade(Items.NETHERITE_HELMET, ModItems.PURPURIT_HELMET, output, "purpurit_helmet");
                upgrade(Items.NETHERITE_CHESTPLATE, ModItems.PURPURIT_CHESTPLATE, output, "purpurit_chestplate");
                upgrade(Items.NETHERITE_LEGGINGS, ModItems.PURPURIT_LEGGINGS, output, "purpurit_leggings");
                upgrade(Items.NETHERITE_BOOTS, ModItems.PURPURIT_BOOTS, output, "purpurit_boots");
                upgrade(Items.NETHERITE_HORSE_ARMOR, ModItems.PURPURIT_HORSE_ARMOR, output, "purpurit_horse_armor");
                upgrade(Items.NETHERITE_NAUTILUS_ARMOR, ModItems.PURPURIT_NAUTILUS_ARMOR, output, "purpurit_nautilus_armor");
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "BeyondNetherite Recipes";
    }
}
