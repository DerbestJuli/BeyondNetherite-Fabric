package de.julirix.beyondnetherite.event;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class AdvancementEvents {

    private static final Identifier SURVIVE_CREEPER_PURPURIT =
            Identifier.fromNamespaceAndPath(BeyondNetherite.MOD_ID, "beyondnetherite/purpuritchargedcrepper");
    private static final Identifier FULL_PROT_4_PURPURIT_ARMOR =
            Identifier.fromNamespaceAndPath(BeyondNetherite.MOD_ID, "beyondnetherite/fullprot4purpuritarmor");

    public static void register() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(AdvancementEvents::onLivingDamage);
        ServerTickEvents.END_SERVER_TICK.register(AdvancementEvents::onServerTick);
    }

    private static void onLivingDamage(LivingEntity entity, DamageSource source, float baseDamageTaken,
                                       float damageTaken, boolean blocked) {
        if (!(entity instanceof ServerPlayer player)) return;
        if (player.getHealth() <= 0) return;

        if (!source.is(DamageTypeTags.IS_EXPLOSION)) return;

        Entity attacker = source.getEntity();
        if (!(attacker instanceof Creeper creeper)) return;
        if (!creeper.isPowered()) return;

        if (!hasFullPurpuritArmor(player)) return;

        AdvancementHolder advancement = ((ServerLevel) player.level()).getServer().getAdvancements().get(SURVIVE_CREEPER_PURPURIT);
        if (advancement != null) {
            player.getAdvancements().award(advancement, "code_triggered");
        }
    }

    private static void onServerTick(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (player.tickCount % 20 != 0) continue; // nur 1x pro Sekunde prüfen, spart Performance

            Holder<Enchantment> protection = player.registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .getOrThrow(Enchantments.PROTECTION);

            boolean hasFullProt4 =
                    player.getItemBySlot(EquipmentSlot.HEAD).getEnchantments().getLevel(protection) >=4
                            && player.getItemBySlot(EquipmentSlot.CHEST).getEnchantments().getLevel(protection) >= 4
                            && player.getItemBySlot(EquipmentSlot.LEGS).getEnchantments().getLevel(protection) >= 4
                            && player.getItemBySlot(EquipmentSlot.FEET).getEnchantments().getLevel(protection) >= 4
                            && hasFullPurpuritArmor(player);

            if (hasFullProt4) {
                AdvancementHolder advancement = ((ServerLevel) player.level()).getServer().getAdvancements().get(FULL_PROT_4_PURPURIT_ARMOR);
                if (advancement != null) {
                    player.getAdvancements().award(advancement, "wearing_full_prot4_set");
                }
            }
        }
    }

    private static boolean hasFullPurpuritArmor(ServerPlayer player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.PURPURIT_HELMET)
                && player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.PURPURIT_CHESTPLATE)
                && player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.PURPURIT_LEGGINGS)
                && player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.PURPURIT_BOOTS);
    }
}
