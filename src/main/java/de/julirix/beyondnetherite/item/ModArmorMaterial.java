package de.julirix.beyondnetherite.item;

import com.google.common.collect.Maps;
import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.util.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class ModArmorMaterial {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> PURPURIT_ASSET = ResourceKey.create(ROOT_ID, BeyondNetherite.id("purpurit"));

    public static final ArmorMaterial PURPURIT_ARMOR_MATERIAL = new ArmorMaterial(
            40,
            makeDefense(4,7,9,4,24),
            20,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            4.0f,
            0.2f,
            ModTags.Items.PURPURIT,
            PURPURIT_ASSET
    );

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}
