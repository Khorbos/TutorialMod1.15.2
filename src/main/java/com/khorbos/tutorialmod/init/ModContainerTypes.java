package com.khorbos.tutorialmod.init;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.container.ExampleChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, TutorialMod.ID);

    public static final RegistryObject<ContainerType<ExampleChestContainer>> EXAMPLE_CHEST = CONTAINER_TYPES.register("example_chest", () -> IForgeContainerType.create(ExampleChestContainer::new));
}
