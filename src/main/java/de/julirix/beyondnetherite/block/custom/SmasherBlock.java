package de.julirix.beyondnetherite.block.custom;

import com.mojang.serialization.MapCodec;
import de.julirix.beyondnetherite.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;

public class SmasherBlock extends FallingBlock {
    private static final MapCodec<SmasherBlock> CODEC = simpleCodec(SmasherBlock::new);

    public SmasherBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onLand(Level level, @NonNull BlockPos pos, @NonNull BlockState state, @NonNull BlockState replacedBlock, @NonNull FallingBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }

        // Items lie on the block directly below the landed smasher.
        AABB area = new AABB(pos.below()).inflate(0.25, 0.1, 0.25).expandTowards(0, 0.6, 0);

        for (ItemEntity itemEntity : level.getEntitiesOfClass(ItemEntity.class, area)) {
            ItemStack stack = itemEntity.getItem();
            if (!stack.is(ModItems.RAW_PURPURIT)) {
                continue;
            }

            int totalParticles = 0;
            for (int i = 0; i < stack.getCount(); i++) {
                totalParticles += 4 + level.getRandom().nextInt(4);
            }

            itemEntity.discard();
            dropParticles(level, pos.below(), totalParticles);
        }
    }

    private static void dropParticles(Level level, BlockPos pos, int totalParticles) {
        if (totalParticles <= 0) {
            return;
        }

        int maxStackSize = ModItems.PURPURIT_PARTICALS.getDefaultMaxStackSize();
        int remaining = totalParticles;

        while (remaining > 0) {
            int count = Math.min(remaining, maxStackSize);
            Block.popResource(level, pos, new ItemStack(ModItems.PURPURIT_PARTICALS, count));
            remaining -= count;
        }
    }

    @Override
    protected @NonNull MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }

    @Override
    public int getDustColor(@NonNull BlockState blockState, @NonNull BlockGetter level, @NonNull BlockPos pos) {
        return 0;
    }
}
