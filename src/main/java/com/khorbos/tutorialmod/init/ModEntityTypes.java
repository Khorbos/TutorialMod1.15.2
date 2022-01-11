package com.khorbos.tutorialmod.init;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.entities.ExampleEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<EntityType<?>>(ForgeRegistries.ENTITIES, TutorialMod.ID);

    public static final RegistryObject<EntityType<ExampleEntity>> EXAMPLE_ENTITY = ENTITY_TYPES.register("example_entity", () -> EntityType.Builder.<ExampleEntity>create(ExampleEntity::new, EntityClassification.CREATURE).size(2.04F, 2.04F).build(new ResourceLocation(TutorialMod.ID, "example_entity").toString()));
}
