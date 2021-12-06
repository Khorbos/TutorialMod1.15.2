package com.khorbos.tutorialmod.tileentity;

import com.khorbos.tutorialmod.init.ModTileEntityTypes;
import com.khorbos.tutorialmod.util.helper.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {
    public int x;
    public int y;
    public int z;
    public int tick;
    boolean initialized = false;

    public QuarryTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public QuarryTileEntity() {
        this(ModTileEntityTypes.QUARRY.get());
    }

    @Override
    public void tick() {
        if(!initialized) init();
        tick++;
        if (tick == 40){
            tick = 0;
            if (y > 2) execute();
        }
    }

    private void init() {
        this.initialized = true;
        this.x = this.pos.getX() - 1;
        this.y = this.pos.getY() - 1;
        this.z = this.pos.getZ() - 1;
    }

    /**
     * Quite laggy, but ok for a tutorial
     */
    private void execute() {
        int index = 0;
        Block[] blocksRemoved = new Block[81];
        for (int x = 0; x < 9; x++) {
            for (int z = 0; z < 9; z++) {
                BlockPos posToBreak = new BlockPos(this.x+ x, this.y, this.z +z);
                blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
                destroyBlock(posToBreak, true, null);
                index++;
            }
        }
        this.y--;
    }

    private boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isAir(world, pos)) return false;
        else {
            IFluidState iFluidState = world.getFluidState(pos);
            world.playEvent(2001, pos, Block.getStateId(blockState));   // 2001 playEvent type is the sound of a block being broken
            if (dropBlock){
                TileEntity tileEntity = blockState.hasTileEntity() ? world.getTileEntity(pos) : null ;
                Block.spawnDrops(blockState, world, this.pos.add(0, 1.5, 0), tileEntity, entity, ItemStack.EMPTY);
            }
            return world.setBlockState(pos, iFluidState.getBlockState(), 3);    // flag 3 if for update fluids
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("initvalues", NBTHelper.toNBT(this));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        CompoundNBT initvalues = compound.getCompound("initvalues");
        if (initvalues != null){
            this.x = initvalues.getInt("x");
            this.y = initvalues.getInt("y");
            this.z = initvalues.getInt("z");
            this.tick = 0;
            this.initialized = true;
            return;
        }
        this.init();
    }
}
