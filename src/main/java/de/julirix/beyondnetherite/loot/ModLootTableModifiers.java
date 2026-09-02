package de.julirix.beyondnetherite.loot;

import de.julirix.beyondnetherite.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModLootTableModifiers {

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) {
                return;
            }

            // End-City-Chests
            if (key.identifier().equals(Identifier.withDefaultNamespace("chests/end_city_treasure"))) {
                tableBuilder.pool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.35f))
                        .add(LootItem.lootTableItem(ModItems.PURPURIT_TEMPLATE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).build()
                );
            }

            // Ender Dragon
            if (key.identifier().equals(Identifier.withDefaultNamespace("entities/ender_dragon"))) {
                tableBuilder.pool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.PURPURIT_TEMPLATE)).build()
                );
            }
        });
    }
}