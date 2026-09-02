package de.julirix.beyondnetherite.datagen;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.item.ModItems;
import de.julirix.beyondnetherite.util.ModTags;
import net.minecraft.advancements.*;
import net.minecraft.advancements.predicates.DamagePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.EntityEquipmentPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends AdvancementProvider {
    public ModAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new BeyondNetheriteAdvancements()));
    }

    public static class BeyondNetheriteAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider provider, @NonNull Consumer<AdvancementHolder> consumer) {
            var items = provider.lookupOrThrow(Registries.ITEM);
            var entities = provider.lookupOrThrow(Registries.ENTITY_TYPE);
            var structures = provider.lookupOrThrow(Registries.STRUCTURE);

            EntityType<?> horse = entities.getOrThrow(EntityTypeIds.HORSE).value();
            EntityType<?> nautilus = entities.getOrThrow(EntityTypeIds.NAUTILUS).value();

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(ModItems.PURPURIT,
                            Component.translatable("advancements.beyondnetherite.root.titel"),
                            Component.translatable("advancements.beyondnetherite.root.discription"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false
                    )
                    .addCriterion("has_purpurit", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/root");

            AdvancementHolder rawPurpurit = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RAW_PURPURIT,
                            Component.translatable("advancements.beyondnetherite.rawPurpurit.titel"),
                            Component.translatable("advancements.beyondnetherite.rawPurpurit.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_rawPurpurit", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.RAW_PURPURIT.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/rawpurpurit");

            AdvancementHolder purpuritOre = Advancement.Builder.advancement()
                    .parent(rawPurpurit)
                    .display(ModBlocks.PURPURIT_END_ORE.asItem(),
                            Component.translatable("advancements.beyondnetherite.purpuritOre.titel"),
                            Component.translatable("advancements.beyondnetherite.purpuritOre.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_purpuritOre", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModBlocks.PURPURIT_END_ORE.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpuritore");

            AdvancementHolder smasherBlock = Advancement.Builder.advancement()
                    .parent(purpuritOre)
                    .display(ModBlocks.SMASHER_BLOCK.asItem(),
                            Component.translatable("advancements.beyondnetherite.smasherBlock.titel"),
                            Component.translatable("advancements.beyondnetherite.smasherBlock.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_smasherBlock", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModBlocks.SMASHER_BLOCK.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/smasherblock");

            AdvancementHolder purpuritParticals = Advancement.Builder.advancement()
                    .parent(smasherBlock)
                    .display(ModItems.PURPURIT_PARTICALS.asItem(),
                            Component.translatable("advancements.beyondnetherite.purpuritParticals.titel"),
                            Component.translatable("advancements.beyondnetherite.purpuritParticals.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_purpuritParticals", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_PARTICALS.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpuritparticals");

            AdvancementHolder smelterWorkbench = Advancement.Builder.advancement()
                    .parent(purpuritParticals)
                    .display(ModBlocks.SMELTER_WORKBENCH.asItem(),
                            Component.translatable("advancements.beyondnetherite.smelterWorkbench.titel"),
                            Component.translatable("advancements.beyondnetherite.smelterWorkbench.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_smelterWorkbench", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModBlocks.SMELTER_WORKBENCH.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/smelterworkbench");

            AdvancementHolder purpurit64 = Advancement.Builder.advancement()
                    .parent(smelterWorkbench)
                    .display(ModItems.PURPURIT,
                            Component.translatable("advancements.beyondnetherite.purpurit64.titel"),
                            Component.translatable("advancements.beyondnetherite.purpurit64.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_purpurit64", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT.asItem())
                                    .withCount(MinMaxBounds.Ints.atLeast(64))))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpurit64");


            AdvancementHolder purpurit = Advancement.Builder.advancement()
                    .parent(smelterWorkbench)
                    .display(ModItems.PURPURIT,
                            Component.translatable("advancements.beyondnetherite.purpurit.titel"),
                            Component.translatable("advancements.beyondnetherite.purpurit.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_purpurit", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpurit");

            AdvancementHolder fullPurpuritArmor = Advancement.Builder.advancement()
                    .parent(purpurit)
                    .display(ModItems.PURPURIT_HELMET,
                            Component.translatable("advancements.beyondnetherite.fullPurpuritArmor.titel"),
                            Component.translatable("advancements.beyondnetherite.fullPurpuritArmor.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("purpuritHelmet", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_HELMET.asItem())))
                    .addCriterion("purpuritChestplate", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_CHESTPLATE.asItem())))
                    .addCriterion("purpuritLeggings", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_LEGGINGS.asItem())))
                    .addCriterion("purpuritBoots", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_BOOTS.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/fullpurpuritarmor");

            AdvancementHolder purpuritEndFight = Advancement.Builder.advancement()
                    .parent(fullPurpuritArmor)
                    .display(Items.DRAGON_HEAD,
                            Component.translatable("advancements.purpuritEndFight.purpurit.titel"),
                            Component.translatable("advancements.purpuritEndFight.purpurit.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("helmet", killedDragonWithEquipment(entities,
                            EntityEquipmentPredicate.Builder.equipment()
                                    .head(ItemPredicate.Builder.item().of(items, ModItems.PURPURIT_HELMET))
                                    .build()))
                    .addCriterion("chestplate", killedDragonWithEquipment(entities,
                            EntityEquipmentPredicate.Builder.equipment()
                                    .chest(ItemPredicate.Builder.item().of(items, ModItems.PURPURIT_CHESTPLATE))
                                    .build()))
                    .addCriterion("leggings", killedDragonWithEquipment(entities,
                            EntityEquipmentPredicate.Builder.equipment()
                                    .legs(ItemPredicate.Builder.item().of(items, ModItems.PURPURIT_LEGGINGS))
                                    .build()))
                    .addCriterion("boots", killedDragonWithEquipment(entities,
                            EntityEquipmentPredicate.Builder.equipment()
                                    .feet(ItemPredicate.Builder.item().of(items, ModItems.PURPURIT_BOOTS))
                                    .build()))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpuritendfight");

            AdvancementHolder purpuritChargedCrepper = Advancement.Builder.advancement()
                    .parent(fullPurpuritArmor)
                    .display(Items.CREEPER_HEAD,
                            Component.translatable("advancements.purpuritChargedCrepper.purpurit.titel"),
                            Component.translatable("advancements.purpuritChargedCrepper.purpurit.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("code_triggered", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpuritchargedcrepper");

            AdvancementHolder fullPurpuritArmorEndCity = Advancement.Builder.advancement()
                    .parent(fullPurpuritArmor)
                    .display(Items.PURPUR_BLOCK,
                            Component.translatable("advancements.beyondnetherite.fullPurpuritArmorEndCity.titel"),
                            Component.translatable("advancements.beyondnetherite.fullPurpuritArmorEndCity.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("wearing_full_set_in_end_city", wearingFullArmorAtStructure(items, structures,
                            ModItems.PURPURIT_HELMET.asItem(), ModItems.PURPURIT_CHESTPLATE.asItem(),
                            ModItems.PURPURIT_LEGGINGS.asItem(), ModItems.PURPURIT_BOOTS.asItem()))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/fullpurpuritarmorendcity");

            AdvancementHolder fullProt4PurpuritArmor = Advancement.Builder.advancement()
                    .parent(fullPurpuritArmor)
                    .display(ModItems.PURPURIT_CHESTPLATE,
                            Component.translatable("advancements.beyondnetherite.fullProt4PurpuritArmor.titel"),
                            Component.translatable("advancements.beyondnetherite.fullProt4PurpuritArmor.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("wearing_full_prot4_set", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/fullprot4purpuritarmor");

            AdvancementHolder allPurpuritTools = Advancement.Builder.advancement()
                    .parent(purpurit)
                    .display(ModItems.PURPURIT_PICKAXE,
                            Component.translatable("advancements.beyondnetherite.allPurpuritTools.titel"),
                            Component.translatable("advancements.beyondnetherite.allPurpuritTools.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .addCriterion("purpuritSword", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_SWORD.asItem())))
                    .addCriterion("purpuritAxe", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_AXE.asItem())))
                    .addCriterion("purpuritPickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_PICKAXE.asItem())))
                    .addCriterion("purpuritShovel", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_SHOVEL.asItem())))
                    .addCriterion("purpuritHoe", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_HOE.asItem())))
                    .addCriterion("purpuritSpear", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_SPEAR.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/allpurpurittools");

            AdvancementHolder spearOnHorse = Advancement.Builder.advancement()
                    .parent(fullPurpuritArmor)
                    .display(ModItems.PURPURIT_SPEAR,
                            Component.translatable("advancements.beyondnetherite.spearOnHorse.titel"),
                            Component.translatable("advancements.beyondnetherite.spearOnHorse.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("kill_with_spear_on_horse", killMobWithSpearOnHorse(items, entities))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/spearonhorse");

            AdvancementHolder damage30 = Advancement.Builder.advancement()
                    .parent(fullPurpuritArmor)
                    .display(ModItems.PURPURIT_SWORD,
                            Component.translatable("advancements.beyondnetherite.damage30.titel"),
                            Component.translatable("advancements.beyondnetherite.damage30.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("dealt_30_damage", hurtEntityWithPurpuritTool(items, 30))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/damage_30");

            AdvancementHolder destroyPurpuritTool = Advancement.Builder.advancement()
                    .parent(fullPurpuritArmor)
                    .display(ModItems.PURPURIT_AXE,
                            Component.translatable("advancements.beyondnetherite.destroyPurpuritTool.titel"),
                            Component.translatable("advancements.beyondnetherite.destroyPurpuritTool.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            true
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("purpuritSword", ConsumeItemTrigger.TriggerInstance.usedItem(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_SWORD)))
                    .addCriterion("purpuritAxe", ConsumeItemTrigger.TriggerInstance.usedItem(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_AXE)))
                    .addCriterion("purpuritPickaxe", ConsumeItemTrigger.TriggerInstance.usedItem(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_PICKAXE)))
                    .addCriterion("purpuritShovel", ConsumeItemTrigger.TriggerInstance.usedItem(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_SHOVEL)))
                    .addCriterion("purpuritHoe", ConsumeItemTrigger.TriggerInstance.usedItem(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_HOE)))
                    .addCriterion("purpuritSpear", ConsumeItemTrigger.TriggerInstance.usedItem(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_SPEAR)))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/destroypurpurittool");

            AdvancementHolder purpuritHoe = Advancement.Builder.advancement()
                    .parent(purpurit)
                    .display(ModItems.PURPURIT_HOE,
                            Component.translatable("advancements.beyondnetherite.purpuritHoe.titel"),
                            Component.translatable("advancements.beyondnetherite.purpuritHoe.discription"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_purpuritHoe", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item()
                                    .of(items, ModItems.PURPURIT_HOE.asItem())))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpurithoe");

            AdvancementHolder purpuritHorseArmor = Advancement.Builder.advancement()
                    .parent(purpurit)
                    .display(ModItems.PURPURIT_HORSE_ARMOR,
                            Component.translatable("advancements.beyondnetherite.purpuritHorseArmor.titel"),
                            Component.translatable("advancements.beyondnetherite.purpuritHorseArmor.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("equip_horse_armor", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                            ItemPredicate.Builder.item().of(items, ModItems.PURPURIT_HORSE_ARMOR),
                            Optional.of(EntityPredicate.wrap(EntityPredicate.Builder.entity().of(entities, horse).build()))))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpurithorsearmor");

            AdvancementHolder purpuritNautilusShell = Advancement.Builder.advancement()
                    .parent(purpurit)
                    .display(ModItems.PURPURIT_NAUTILUS_ARMOR,
                            Component.translatable("advancements.beyondnetherite.purpuritNautilusShell.titel"),
                            Component.translatable("advancements.beyondnetherite.purpuritNautilusShell.discription"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("equip_nautilus_armor", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                            ItemPredicate.Builder.item().of(items, ModItems.PURPURIT_NAUTILUS_ARMOR),
                            Optional.of(EntityPredicate.wrap(EntityPredicate.Builder.entity().of(entities, nautilus).build()))))
                    .save(consumer, BeyondNetherite.MOD_ID + ":beyondnetherite/purpuritnautilusshell");

        }

        private static Criterion<KilledTrigger.TriggerInstance> killedDragonWithEquipment(
                HolderGetter<EntityType<?>> entities, EntityEquipmentPredicate equipment) {
            EntityType<?> enderDragon = entities.getOrThrow(EntityTypeIds.ENDER_DRAGON).value();

            return CriteriaTriggers.PLAYER_KILLED_ENTITY.createCriterion(
                    new KilledTrigger.TriggerInstance(
                            Optional.of(EntityPredicate.wrap(
                                    EntityPredicate.Builder.entity()
                                            .equipment(equipment)
                                            .build()
                            )),
                            Optional.of(EntityPredicate.wrap(
                                    EntityPredicate.Builder.entity()
                                            .of(entities, enderDragon)
                                            .build()
                            )),
                            Optional.empty()
                    )
            );
        }

        private static Criterion<PlayerTrigger.TriggerInstance> wearingFullArmorAtStructure(
                HolderGetter<Item> items, HolderGetter<Structure> structures,
                Item helmet, Item chestplate, Item leggings, Item boots) {

            EntityEquipmentPredicate equipment = EntityEquipmentPredicate.Builder.equipment()
                    .head(ItemPredicate.Builder.item().of(items, helmet))
                    .chest(ItemPredicate.Builder.item().of(items, chestplate))
                    .legs(ItemPredicate.Builder.item().of(items, leggings))
                    .feet(ItemPredicate.Builder.item().of(items, boots))
                    .build();

            LocationPredicate.Builder location = LocationPredicate.Builder.location()
                    .setStructures(HolderSet.direct(structures.getOrThrow(BuiltinStructures.END_CITY)));

            EntityPredicate player = EntityPredicate.Builder.entity()
                    .equipment(equipment)
                    .located(location)
                    .build();

            return CriteriaTriggers.LOCATION.createCriterion(
                    new PlayerTrigger.TriggerInstance(Optional.of(EntityPredicate.wrap(player)))
            );
        }

        private static Criterion<KilledTrigger.TriggerInstance> killMobWithSpearOnHorse(HolderGetter<Item> items, HolderGetter<EntityType<?>> entities) {
            EntityType<?> horse = entities.getOrThrow(EntityTypeIds.HORSE).value();

            EntityPredicate playerPredicate = EntityPredicate.Builder.entity()
                    .vehicle(EntityPredicate.Builder.entity().of(entities, horse))
                    .equipment(EntityEquipmentPredicate.Builder.equipment()
                            .mainhand(ItemPredicate.Builder.item().of(items, ModItems.PURPURIT_SPEAR.asItem()))
                            .build())
                    .build();

            return CriteriaTriggers.PLAYER_KILLED_ENTITY.createCriterion(
                    new KilledTrigger.TriggerInstance(
                            Optional.of(EntityPredicate.wrap(playerPredicate)),
                            Optional.empty(), // beliebiges Mob
                            Optional.empty()
                    )
            );
        }

        private static Criterion<PlayerHurtEntityTrigger.TriggerInstance> hurtEntityWithPurpuritTool(HolderGetter<Item> items, double minDamage) {
            EntityPredicate playerPredicate = EntityPredicate.Builder.entity()
                    .equipment(EntityEquipmentPredicate.Builder.equipment()
                            .mainhand(ItemPredicate.Builder.item().of(items, ModTags.Items.PURPURIT_TOOLS)))
                            .build();

            DamagePredicate damage = DamagePredicate.Builder.damageInstance()
                    .dealtDamage(MinMaxBounds.Doubles.atLeast(minDamage))
                    .build();

            return CriteriaTriggers.PLAYER_HURT_ENTITY.createCriterion(
                    new PlayerHurtEntityTrigger.TriggerInstance(
                            Optional.of(EntityPredicate.wrap(playerPredicate)),
                            Optional.of(damage),
                            Optional.empty()
                    )
            );
        }
    }
}
