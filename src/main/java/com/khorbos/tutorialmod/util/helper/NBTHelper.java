package com.khorbos.tutorialmod.util.helper;

import com.khorbos.tutorialmod.tileentity.QuarryTileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NBTHelper {
    public static CompoundNBT toNBT(Object o){
        if (o instanceof ItemStack){
            return writeItemStack((ItemStack) o);
        } else if (o instanceof QuarryTileEntity){
            return writeQuarry((QuarryTileEntity) o);
        }
        return null;
    }

    @Nullable
    public static Object fromNBT(@Nonnull CompoundNBT compound) {
        switch (compound.getByte("type")) {
            case 0:
                return readItemStack(compound);
            default:
                return null;
        }
    }

    private static CompoundNBT writeItemStack(ItemStack itemStack) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("count", itemStack.getCount());
        compoundNBT.putString("item", itemStack.getItem().getRegistryName().toString());
        compoundNBT.putByte("type", (byte)0);
        return compoundNBT;
    }

    private static CompoundNBT writeQuarry(QuarryTileEntity quarryTileEntity){
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("x", quarryTileEntity.x);
        compoundNBT.putInt("y", quarryTileEntity.y);
        compoundNBT.putInt("z", quarryTileEntity.z);
        return compoundNBT;
    }

    private static ItemStack readItemStack(CompoundNBT compound) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(compound.getString("item")));
        int count = compound.getInt("count");
        return new ItemStack(item, count);
    }
}
