package com.khorbos.tutorialmod.objects.blocks;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class CustomBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 2, 12, 2),
            Block.makeCuboidShape(0, 0, 14, 2, 12, 16),
            Block.makeCuboidShape(14, 0, 0, 16, 12, 2),
            Block.makeCuboidShape(14, 0, 14, 16, 12, 16),
            Block.makeCuboidShape(0, 12, 0, 16, 16, 16),
            Block.makeCuboidShape(2, 0, 2, 14, 12, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(14, 0, 14, 16, 12, 16),
            Block.makeCuboidShape(14, 0, 0, 16, 12, 2),
            Block.makeCuboidShape(0, 0, 14, 2, 12, 16),
            Block.makeCuboidShape(0, 0, 0, 2, 12, 2),
            Block.makeCuboidShape(0, 12, 0, 16, 16, 16),
            Block.makeCuboidShape(2, 0, 2, 14, 12, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(0, 0, 14, 2, 12, 16),
            Block.makeCuboidShape(14, 0, 14, 16, 12, 16),
            Block.makeCuboidShape(0, 0, 0, 2, 12, 2),
            Block.makeCuboidShape(14, 0, 0, 16, 12, 2),
            Block.makeCuboidShape(0, 12, 0, 16, 16, 16),
            Block.makeCuboidShape(2, 0, 2, 14, 12, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(14, 0, 0, 16, 12, 2),
            Block.makeCuboidShape(0, 0, 0, 2, 12, 2),
            Block.makeCuboidShape(14, 0, 14, 16, 12, 16),
            Block.makeCuboidShape(0, 0, 14, 2, 12, 16),
            Block.makeCuboidShape(0, 12, 0, 16, 16, 16),
            Block.makeCuboidShape(2, 0, 2, 14, 12, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public CustomBlock() {
        super(Block.Properties
                .create(Material.IRON)
                .hardnessAndResistance(2.0F, 10.0F)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.ANVIL)
                .lightValue(4)
                .slipperiness(1.2F)
                .speedFactor(0.7F)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)){
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()){
            ServerWorld serverWorld = (ServerWorld) worldIn;
            int rand = 1 + (int)(Math.random() * ((1000 - 1) + 1));
            if (rand == 64) {
                IronGolemEntity entity = new IronGolemEntity(EntityType.IRON_GOLEM, worldIn);
                entity.setPosition(player.getPosX()+5, player.getPosY(), player.getPosZ()+5);
                serverWorld.addEntity(entity);
            }
        }
        return ActionResultType.SUCCESS;
    }
}
