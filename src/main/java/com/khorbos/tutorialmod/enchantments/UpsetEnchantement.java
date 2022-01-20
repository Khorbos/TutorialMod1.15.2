package com.khorbos.tutorialmod.enchantments;

import com.khorbos.tutorialmod.TutorialMod;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class UpsetEnchantement extends Enchantment {
    public UpsetEnchantement(Rarity rareIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rareIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    protected boolean canApplyTogether(Enchantment enchantment) {
        return !enchantment.equals(Enchantments.DEPTH_STRIDER) || !enchantment.equals(Enchantments.FROST_WALKER);
    }

    @Mod.EventBusSubscriber(modid = TutorialMod.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class UpStepEquipped {

        @SubscribeEvent
        public static void doStuff(TickEvent.PlayerTickEvent event){
            PlayerEntity playerIn = event.player;
            World worldIn = playerIn.world;
            if (playerIn.isSneaking()){
                worldIn.setBlockState(playerIn.getPosition().down(), Blocks.GLASS.getDefaultState());
            } else {
                if (worldIn.getBlockState(playerIn.getPosition().down()).getBlock() == Blocks.GLASS){
                    worldIn.setBlockState(playerIn.getPosition(), Blocks.AIR.getDefaultState());

                }
            }
        }
    }
}
