package com.khorbos.tutorialmod.util.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

public class KeyboardHelper {
    private static final long WINDOWS = Minecraft.getInstance().getMainWindow().getHandle();

    @OnlyIn(Dist.CLIENT)
    public static boolean isHoldingShift() {
        return InputMappings.isKeyDown(WINDOWS, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOWS, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isHoldingCtrl() {
        return InputMappings.isKeyDown(WINDOWS, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(WINDOWS, GLFW.GLFW_KEY_RIGHT_CONTROL);
    }

}
