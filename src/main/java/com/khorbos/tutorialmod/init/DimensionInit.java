package com.khorbos.tutorialmod.init;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.world.dimension.ExampleModDimension;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {
    public static final DeferredRegister<ModDimension> MOD_DIMENSION = new DeferredRegister<ModDimension>(ForgeRegistries.MOD_DIMENSIONS, TutorialMod.ID);

    public static final RegistryObject<ModDimension> EXAMPLE_DIM = MOD_DIMENSION.register("example_dim", ExampleModDimension::new);
}
