package de.julirix.beyondnetherite.block.custom;

import com.mojang.serialization.MapCodec;
import de.julirix.beyondnetherite.block.entity.ModBlockEntities;
import de.julirix.beyondnetherite.block.entity.SmelterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SmelterBlock extends BaseEntityBlock {
    public static final MapCodec<SmelterBlock> CODEC = simpleCodec(SmelterBlock::new);
    public static final BooleanProperty SMELTING = BooleanProperty.create("smelting");
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() { return CODEC; }

    public SmelterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SMELTING, false));
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos worldPosition, @NonNull BlockState blockState) {
        return new SmelterBlockEntity(worldPosition, blockState);
    }

    @Override
    protected @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void spawnAfterBreak(@NonNull BlockState state, ServerLevel level, @NonNull BlockPos pos, @NonNull ItemStack tool, boolean dropExperience) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if(blockEntity instanceof SmelterBlockEntity smelterBlockEntity) {
            smelterBlockEntity.dropContents();
        }
        super.spawnAfterBreak(state, level, pos, tool, dropExperience);
    }

    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack itemStack, @NonNull BlockState state, Level level, @NonNull BlockPos pos,
                                                   @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof SmelterBlockEntity smelterBlockEntity) {
                player.openMenu(smelterBlockEntity);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.SMELTER_BE,
                (level1, blockPos, blockState, blockEntity) -> blockEntity.tick(level1, blockPos, blockState));
    }

    //BlockState
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SMELTING);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(SMELTING, false).setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }
}
