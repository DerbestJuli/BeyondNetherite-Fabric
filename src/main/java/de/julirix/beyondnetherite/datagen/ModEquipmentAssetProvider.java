package de.julirix.beyondnetherite.datagen;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.item.ModArmorMaterial;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jspecify.annotations.NonNull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        output.accept(ModArmorMaterial.PURPURIT_ASSET, EquipmentClientInfo.builder()
                        .addHumanoidLayers(Identifier.fromNamespaceAndPath(BeyondNetherite.MOD_ID, "purpurit"), false)
                        .build());
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> assets = new HashMap<>();
        registerModels((id, asset) -> {
            if (assets.putIfAbsent(id, asset) != null)
                throw new IllegalStateException("Duplicate equipment asset: " + id);
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, assets);
    }

    @Override
    public @NonNull String getName() {
        return "Equipment Assets: " + BeyondNetherite.MOD_ID;
    }
}
