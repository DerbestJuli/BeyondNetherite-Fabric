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
        tag(ModTags.Items.PURPURIT).add(ModItems.PURPURIT.builtInRegistryHolder().key());
        //Tools
        tag(ItemTags.SWORDS).add(ModItems.PURPURIT_SWORD.builtInRegistryHolder().key());
        tag(ItemTags.AXES).add(ModItems.PURPURIT_AXE.builtInRegistryHolder().key());
        tag(ItemTags.PICKAXES).add(ModItems.PURPURIT_PICKAXE.builtInRegistryHolder().key());
        tag(ItemTags.SHOVELS).add(ModItems.PURPURIT_SHOVEL.builtInRegistryHolder().key());
        tag(ItemTags.HOES).add(ModItems.PURPURIT_HOE.builtInRegistryHolder().key());
        tag(ItemTags.SPEARS).add(ModItems.PURPURIT_SPEAR.builtInRegistryHolder().key());
        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .add(ModItems.PURPURIT_SPEAR.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_SWORD.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_AXE.builtInRegistryHolder().key());
        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.PURPURIT_PICKAXE.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_AXE.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_SHOVEL.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_HOE.builtInRegistryHolder().key());
        //Armor
        tag(ItemTags.HEAD_ARMOR).add(ModItems.PURPURIT_HELMET.builtInRegistryHolder().key());
        tag(ItemTags.CHEST_ARMOR).add(ModItems.PURPURIT_CHESTPLATE.builtInRegistryHolder().key());
        tag(ItemTags.LEG_ARMOR).add(ModItems.PURPURIT_LEGGINGS.builtInRegistryHolder().key());
        tag(ItemTags.FOOT_ARMOR).add(ModItems.PURPURIT_BOOTS.builtInRegistryHolder().key());
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PURPURIT_HELMET.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_CHESTPLATE.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_LEGGINGS.builtInRegistryHolder().key())
                .add(ModItems.PURPURIT_BOOTS.builtInRegistryHolder().key());
        tag(ItemTags.ARMOR_ENCHANTABLE).add(ModItems.PURPURIT_HORSE_ARMOR.builtInRegistryHolder().key());
        tag(ItemTags.ARMOR_ENCHANTABLE).add(ModItems.PURPURIT_NAUTILUS_ARMOR.builtInRegistryHolder().key());
    }
}
