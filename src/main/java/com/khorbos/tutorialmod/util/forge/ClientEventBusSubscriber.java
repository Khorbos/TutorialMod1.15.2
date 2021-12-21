package com.khorbos.tutorialmod.util.forge;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.client.gui.ExampleChestScreen;
import com.khorbos.tutorialmod.init.ModContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = TutorialMod.ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModContainerTypes.EXAMPLE_CHEST.get(), ExampleChestScreen::new);
    }
}
