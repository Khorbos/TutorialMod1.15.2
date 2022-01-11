package com.khorbos.tutorialmod.objects.items.food;

import com.khorbos.tutorialmod.TutorialMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class CornItem extends Item {
    public static final Food CORN = (new Food.Builder()).hunger(4).saturation(0.6F).build();

    public CornItem(Properties properties) {
        super(properties);
    }

    public CornItem() {
        super(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance).food(CornItem.CORN));
    }


}
