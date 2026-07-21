package de.julirix.beyondnetherite.item;

import de.julirix.beyondnetherite.BeyondNetherite;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.function.Function;

public class ModItems {

    public static final Item PURPURIT = registerItem("purpurit", Item::new);
    public static final Item RAW_PURPURIT = registerItem("raw_purpurit", Item::new);
    public static final Item PURPURIT_PARTICALS = registerItem("purpurit_particals", Item::new);
    public static final Item PURPURIT_TEMPLATE = registerItem("purpurit_template", Item::new);

    //Tools
    public static final Item PURPURIT_SWORD = registerItem("purpurit_sword",
            props -> new Item(props.sword(ModToolMaterial.PURPURIT, 3.5f, -2.4f)));
    public static final Item PURPURIT_AXE = registerItem("purpurit_axe",
            props -> new Item(props.axe(ModToolMaterial.PURPURIT, 4.5f, -3.0f)));
    public static final Item PURPURIT_PICKAXE = registerItem("purpurit_pickaxe",
            props -> new Item(props.pickaxe(ModToolMaterial.PURPURIT, 1, -2.8f)));
    public static final Item PURPURIT_SHOVEL = registerItem("purpurit_shovel",
            props -> new Item(props.shovel(ModToolMaterial.PURPURIT, -1.5f, -3.0f)));
    public static final Item PURPURIT_HOE = registerItem("purpurit_hoe",
            props -> new Item(props.hoe(ModToolMaterial.PURPURIT, -4.0f, 0.0f)));
    public static final Item PURPURIT_SPEAR = registerItem("purpurit_spear",
            props -> new Item(props.spear(ModToolMaterial.PURPURIT, 1.2f, 1.3f, 0.3f,
                    2.0f, 8.0f, 4.5f, 5.1f, 7.5f, 4.6f)));

    //Armor
    public static final Item PURPURIT_HELMET = registerItem("purpurit_helmet",
            props -> new Item(props.humanoidArmor(ModArmorMaterial.PURPURIT_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item PURPURIT_CHESTPLATE = registerItem("purpurit_chestplate",
            props -> new Item(props.humanoidArmor(ModArmorMaterial.PURPURIT_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item PURPURIT_LEGGINGS = registerItem("purpurit_leggings",
            props -> new Item(props.humanoidArmor(ModArmorMaterial.PURPURIT_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item PURPURIT_BOOTS = registerItem("purpurit_boots",
            props -> new Item(props.humanoidArmor(ModArmorMaterial.PURPURIT_ARMOR_MATERIAL, ArmorType.BOOTS)));
    public static final Item PURPURIT_HORSE_ARMOR = registerItem("purpurit_horse_armor",
            props -> new Item(props.horseArmor(ModArmorMaterial.PURPURIT_ARMOR_MATERIAL)));
    public static final Item PURPURIT_NAUTILUS_ARMOR = registerItem("purpurit_nautilus_armor",
            props -> new Item(props.nautilusArmor(ModArmorMaterial.PURPURIT_ARMOR_MATERIAL)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, de.julirix.beyondnetherite.BeyondNetherite.id(name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, de.julirix.beyondnetherite.BeyondNetherite.id(name)))));
    }

    public static void registerModItems() {
        BeyondNetherite.LOGGER.info("Registering Mod Items for " + de.julirix.beyondnetherite.BeyondNetherite.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(ModItems.PURPURIT);
            output.accept(ModItems.RAW_PURPURIT);
            output.accept(ModItems.PURPURIT_PARTICALS);
            output.accept(ModItems.PURPURIT_TEMPLATE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.accept(ModItems.PURPURIT_PICKAXE);
            output.accept(ModItems.PURPURIT_AXE);
            output.accept(ModItems.PURPURIT_SHOVEL);
            output.accept(ModItems.PURPURIT_HOE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(ModItems.PURPURIT_SWORD);
            output.accept(ModItems.PURPURIT_SPEAR);
            output.accept(ModItems.PURPURIT_HELMET);
            output.accept(ModItems.PURPURIT_CHESTPLATE);
            output.accept(ModItems.PURPURIT_LEGGINGS);
            output.accept(ModItems.PURPURIT_BOOTS);
            output.accept(ModItems.PURPURIT_HORSE_ARMOR);
            output.accept(ModItems.PURPURIT_NAUTILUS_ARMOR);
        });
    }
}
