package de.julirix.beyondnetherite.block.entity;

import de.julirix.beyondnetherite.block.custom.SmelterBlock;
import de.julirix.beyondnetherite.item.ModItems;
import de.julirix.beyondnetherite.screen.custom.SmelterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SmelterBlockEntity extends BlockEntity implements MenuProvider, Container {

    private static final int INPUT_SLOTS = 21; // Slots 0..20
    private static final int FUEL_SLOT = 21;
    private static final int OUTPUT_SLOT = 22;
    private static final int SLOT_COUNT = 23;

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public SmelterBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.SMELTER_BE, worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> SmelterBlockEntity.this.progress;
                    case 1 -> SmelterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0 -> SmelterBlockEntity.this.progress = value;
                    case 1 -> SmelterBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ContainerData getContainerData() {
        return data;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe() && hasLavaFuel()) {
            progress++;
            setChanged();

            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
            setChanged();
        }
        if (!level.isClientSide()) {
            boolean shouldBeLit = hasRecipe() && hasLavaFuel() && progress > 0;

            if (state.getValue(SmelterBlock.SMELTING) != shouldBeLit) {
                level.setBlock(pos, state.setValue(SmelterBlock.SMELTING, shouldBeLit), 3);
            }
        }
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    private boolean hasLavaFuel() {
        return items.get(FUEL_SLOT).is(Items.LAVA_BUCKET);
    }

    private boolean hasRecipe() {
        boolean hasIngredients = true;
        for (int i = 0; i < INPUT_SLOTS; i++) {
            if (!items.get(i).is(ModItems.PURPURIT_PARTICALS)) {
                hasIngredients = false;
                break;
            }
        }

        ItemStack output = items.get(OUTPUT_SLOT);
        boolean canOutput = output.isEmpty() || output.getCount() < output.getMaxStackSize();

        return hasIngredients && canOutput;
    }

    private void craftItem() {
        for (int i = 0; i < INPUT_SLOTS; i++) {
            items.get(i).shrink(1);
        }
        items.set(FUEL_SLOT, new ItemStack(Items.BUCKET));

        ItemStack output = items.get(OUTPUT_SLOT);
        if (output.isEmpty()) {
            items.set(OUTPUT_SLOT, new ItemStack(ModItems.PURPURIT));
        } else {
            output.grow(1);
        }

        setChanged();
    }

    public void dropContents() {
        if (level == null || level.isClientSide()) {
            return;
        }
        Containers.dropContents(level, worldPosition, items);
    }

    // ---- Container-Interface ----

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public @NonNull ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public @NonNull ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(items, slot, amount);
        if (!result.isEmpty()) setChanged();
        return result;
    }

    @Override
    public @NonNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, @NonNull ItemStack stack) {
        items.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        output.putInt("smelter.progress", progress);
        output.putInt("smelter.max_progress", maxProgress);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        progress = input.getIntOr("smelter.progress", 0);
        maxProgress = input.getIntOr("smelter.max_progress", 72);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NonNull Component getDisplayName() {
        return Component.translatable("block.beyondnetherite.smelter_workbench");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NonNull Inventory inventory, @NonNull Player player) {
        return new SmelterMenu(containerId, inventory, this, data);
    }
}