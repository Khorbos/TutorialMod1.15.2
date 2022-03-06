package com.khorbos.tutorialmod.util.forge;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.client.gui.ExampleChestScreen;
import com.khorbos.tutorialmod.entities.render.ExampleEntityRender;
import com.khorbos.tutorialmod.init.BlockInit;
import com.khorbos.tutorialmod.init.ModContainerTypes;
import com.khorbos.tutorialmod.init.ModEntityTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = TutorialMod.ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModContainerTypes.EXAMPLE_CHEST.get(), ExampleChestScreen::new);
        RenderTypeLookup.setRenderLayer(BlockInit.EXAMPLE_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.EXAMPLE_DOOR.get(), RenderType.getCutout());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.EXAMPLE_ENTITY.get(), ExampleEntityRender::new);
        RenderTypeLookup.setRenderLayer(BlockInit.CORN_CROP.get(), RenderType.getCutout());
    }
}
