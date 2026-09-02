package de.julirix.beyondnetherite.datagen;

import de.julirix.beyondnetherite.item.ModItems;
import de.julirix.beyondnetherite.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, @Nullable BlockTagsProvider blockTagsProvider) {
        super(output, registryLookupFuture, blockTagsProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider lookup) {
        valueLookupBuilder(ModTags.Items.GLAZED_TERRACOTTAS)
                .add(Items.BLACK_GLAZED_TERRACOTTA)
                .add(Items.GRAY_GLAZED_TERRACOTTA)
                .add(Items.GREEN_GLAZED_TERRACOTTA)
                .add(Items.BLUE_GLAZED_TERRACOTTA)
                .add(Items.BROWN_GLAZED_TERRACOTTA)
                .add(Items.CYAN_GLAZED_TERRACOTTA)
                .add(Items.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .add(Items.LIGHT_GRAY_GLAZED_TERRACOTTA)
                .add(Items.LIME_GLAZED_TERRACOTTA)
                .add(Items.MAGENTA_GLAZED_TERRACOTTA)
                .add(Items.ORANGE_GLAZED_TERRACOTTA)
                .add(Items.PINK_GLAZED_TERRACOTTA)
                .add(Items.PURPLE_GLAZED_TERRACOTTA)
                .add(Items.WHITE_GLAZED_TERRACOTTA)
                .add(Items.RED_GLAZED_TERRACOTTA)
                .add(Items.YELLOW_GLAZED_TERRACOTTA);
        valueLookupBuilder(ModTags.Items.PURPURIT).add(ModItems.PURPURIT);
        //Tools
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.PURPURIT_SWORD);
        valueLookupBuilder(ItemTags.AXES).add(ModItems.PURPURIT_AXE);
        valueLookupBuilder(ItemTags.PICKAXES).add(ModItems.PURPURIT_PICKAXE);
        valueLookupBuilder(ItemTags.SHOVELS).add(ModItems.PURPURIT_SHOVEL);
        valueLookupBuilder(ItemTags.HOES).add(ModItems.PURPURIT_HOE);
        valueLookupBuilder(ItemTags.SPEARS).add(ModItems.PURPURIT_SPEAR);
        valueLookupBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .add(ModItems.PURPURIT_SPEAR)
                .add(ModItems.PURPURIT_SWORD)
                .add(ModItems.PURPURIT_AXE);
        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.PURPURIT_PICKAXE)
                .add(ModItems.PURPURIT_AXE)
                .add(ModItems.PURPURIT_SHOVEL)
                .add(ModItems.PURPURIT_HOE);
        //Armor
        valueLookupBuilder(ItemTags.HEAD_ARMOR).add(ModItems.PURPURIT_HELMET);
        valueLookupBuilder(ItemTags.CHEST_ARMOR).add(ModItems.PURPURIT_CHESTPLATE);
        valueLookupBuilder(ItemTags.LEG_ARMOR).add(ModItems.PURPURIT_LEGGINGS);
        valueLookupBuilder(ItemTags.FOOT_ARMOR).add(ModItems.PURPURIT_BOOTS);
        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PURPURIT_HELMET)
                .add(ModItems.PURPURIT_CHESTPLATE)
                .add(ModItems.PURPURIT_LEGGINGS)
                .add(ModItems.PURPURIT_BOOTS);
        valueLookupBuilder(ItemTags.ARMOR_ENCHANTABLE).add(ModItems.PURPURIT_HORSE_ARMOR);
        valueLookupBuilder(ItemTags.ARMOR_ENCHANTABLE).add(ModItems.PURPURIT_NAUTILUS_ARMOR);
    }
}
