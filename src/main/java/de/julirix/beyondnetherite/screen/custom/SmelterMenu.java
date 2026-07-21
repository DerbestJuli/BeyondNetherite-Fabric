package de.julirix.beyondnetherite.screen.custom;

import de.julirix.beyondnetherite.BeyondNetherite;
import de.julirix.beyondnetherite.block.ModBlocks;
import de.julirix.beyondnetherite.block.entity.SmelterBlockEntity;
import de.julirix.beyondnetherite.item.ModItems;
import de.julirix.beyondnetherite.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.NonNull;

public class SmelterMenu extends AbstractContainerMenu {
    public final SmelterBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    // Wird von ExtendedScreenHandlerType aufgerufen, extraData ist bereits zur BlockPos decodiert
    public SmelterMenu(int pContainerId, Inventory inv, BlockPos pos) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(pos), new SimpleContainerData(2));
    }

    public SmelterMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.SMELTER_MENU, pContainerId);
        this.blockEntity = ((SmelterBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        int slotIndex = 0;

        for (int i = 0; i < 3; i++) { //Top 3 Row
            addIngredientSlot(slotIndex++, 44 + i * 18, 21);
        }
        for (int i = 0; i < 5; i++) { // Reihe 1
            addIngredientSlot(slotIndex++, 26 + i * 18, 39);
        }
        for (int i = 0; i < 5; i++) { // Reihe 2
            addIngredientSlot(slotIndex++, 26 + i * 18, 57);
        }
        for (int i = 0; i < 5; i++) { // Reihe 3
            addIngredientSlot(slotIndex++, 26 + i * 18, 75);
        }
        for (int i = 0; i < 3; i++) { //Bottom 3 Row
            addIngredientSlot(slotIndex++, 44 + i * 18, 93);
        }

        this.addSlot(new Slot(blockEntity, 21, 122, 93) { // Fuel-Slot
            @Override
            public boolean mayPlace(@NonNull ItemStack itemStack) {
                return itemStack.is(Items.LAVA_BUCKET);
            }
        });
        this.addSlot(new Slot(blockEntity, 22, 149, 57) { // Output-Slot
            @Override
            public boolean mayPlace(@NonNull ItemStack itemStack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int arrowPixelSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 23;

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            int startIndex;
            int endIndex;
            if (sourceStack.is(Items.LAVA_BUCKET)) {
                startIndex = TE_INVENTORY_FIRST_SLOT_INDEX + 21;
                endIndex = startIndex + 1;
            } else if (sourceStack.is(ModItems.PURPURIT_PARTICALS)) {
                startIndex = TE_INVENTORY_FIRST_SLOT_INDEX;
                endIndex = TE_INVENTORY_FIRST_SLOT_INDEX + 21;
            } else {
                return ItemStack.EMPTY;
            }
            if (!moveItemStackTo(sourceStack, startIndex, endIndex, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
               player, ModBlocks.SMELTER_WORKBENCH);
    }

    private void addIngredientSlot(int index, int x, int y) {
        this.addSlot(new Slot(blockEntity, index, x, y) {
            @Override
            public boolean mayPlace(@NonNull ItemStack itemStack) {
                return itemStack.is(ModItems.PURPURIT_PARTICALS);
            }
        });
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 127 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 185));
        }
    }
}