package de.julirix.beyondnetherite.datagen;

import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.item.ModArmorMaterial;
import de.julirix.beyondnetherite.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        //Blocks
        blockModelGenerators.createTrivialCube(ModBlocks.PURPURIT_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.PURPURIT_END_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.SMASHER_BLOCK);
        //Workbench
        blockModelGenerators.createTrivialBlock(ModBlocks.SMELTER_WORKBENCH, TexturedModel.ORIENTABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        //Items
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_PURPURIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_PARTICALS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_TEMPLATE, ModelTemplates.FLAT_ITEM);
        //Tools
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateSpear(ModItems.PURPURIT_SPEAR);
        //Armor
        itemModelGenerators.generateTrimmableItem(ModItems.PURPURIT_HELMET, ModArmorMaterial.PURPURIT_ASSET, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(ModItems.PURPURIT_CHESTPLATE, ModArmorMaterial.PURPURIT_ASSET, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(ModItems.PURPURIT_LEGGINGS, ModArmorMaterial.PURPURIT_ASSET, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(ModItems.PURPURIT_BOOTS, ModArmorMaterial.PURPURIT_ASSET, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PURPURIT_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
    }
}
