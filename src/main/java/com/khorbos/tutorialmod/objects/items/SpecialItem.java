package com.khorbos.tutorialmod.objects.items;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.util.helper.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SpecialItem extends Item {
    public SpecialItem() {
        super(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (KeyboardHelper.isHoldingShift()){
            tooltip.add(new StringTextComponent("When holding shift, this item has a tooltip information"));
        } else {
            tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + "SHIFT" + "\u00A77" + "for more details"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 500, 255));
        worldIn.setDayTime(0);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
