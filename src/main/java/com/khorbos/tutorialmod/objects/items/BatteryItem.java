package com.khorbos.tutorialmod.objects.items;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.util.helper.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BatteryItem extends Item {
    public BatteryItem() {
        super(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance));
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 72000;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (KeyboardHelper.isHoldingShift()){
            tooltip.add(new StringTextComponent("Can be used as long use fuel in any furnace or smoker!"));
        } else {
            tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + " SHIFT " + "\u00A77" + "for more details"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
