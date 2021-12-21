package com.khorbos.tutorialmod.init;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.tileentity.ExampleChestTileEntity;
import com.khorbos.tutorialmod.tileentity.QuarryTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<TileEntityType<?>>(ForgeRegistries.TILE_ENTITIES, TutorialMod.ID);

    public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register("quarry", () -> TileEntityType.Builder.create(QuarryTileEntity::new, BlockInit.QUARRY.get()).build(null));
    public static final RegistryObject<TileEntityType<ExampleChestTileEntity>> EXAMPLE_CHEST = TILE_ENTITY_TYPES.register("example_chest", () -> TileEntityType.Builder.create(ExampleChestTileEntity::new, BlockInit.EXAMPLE_CHEST.get()).build(null));
}
