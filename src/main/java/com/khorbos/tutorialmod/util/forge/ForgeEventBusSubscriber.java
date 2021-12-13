package com.khorbos.tutorialmod.util.forge;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.init.DimensionInit;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TutorialMod.ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
    @SubscribeEvent
    public static void onRegisterDimension(final RegisterDimensionsEvent event){
        if (DimensionType.byName(TutorialMod.EXAMPLE_DIM_TYPE) == null){
            DimensionManager.registerDimension(TutorialMod.EXAMPLE_DIM_TYPE, DimensionInit.EXAMPLE_DIM.get(), null, true);
        }
        TutorialMod.LOGGER.info("Registered Dimensions passed!");
    }
}
