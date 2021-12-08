package com.khorbos.tutorialmod.objects.blocks;

import net.minecraft.block.PressurePlateBlock;

public class CustomPressurePlate extends PressurePlateBlock {
    public CustomPressurePlate(Properties propertiesIn) {
        super(Sensitivity.EVERYTHING, propertiesIn);
    }
}
