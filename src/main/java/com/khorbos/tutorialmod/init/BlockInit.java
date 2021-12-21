package com.khorbos.tutorialmod.init;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.objects.blocks.*;
import com.khorbos.tutorialmod.tileentity.ExampleChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, TutorialMod.ID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(Block.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> EXAMPLE_STAIRS = BLOCKS.register("example_stairs", () -> new StairsBlock(() -> BlockInit.EXAMPLE_BLOCK.get().getDefaultState(), Block.Properties.create(Material.WOOD)));
     public static final RegistryObject<Block> EXAMPLE_SLAB = BLOCKS.register("example_slab", () -> new SlabBlock(Block.Properties.create(Material.IRON)));
    public static final RegistryObject<Block> EXAMPLE_FENCE = BLOCKS.register("example_fence", () -> new FenceBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON)));
    public static final RegistryObject<Block> EXAMPLE_BUTTON = BLOCKS.register("example_button", () -> new CustomButton(Block.Properties.create(Material.IRON, MaterialColor.IRON)));
    public static final RegistryObject<Block> EXAMPLE_PRESSURE_PLATE = BLOCKS.register("example_pressure_plate", () -> new CustomPressurePlate(Block.Properties.create(Material.IRON)));

    public static final RegistryObject<CustomBlock> CUSTOM_BLOCK = BLOCKS.register("custom_block", () -> new CustomBlock());

    public static final RegistryObject<QuarryBlock> QUARRY = BLOCKS.register("quarry", QuarryBlock::new);

    public static final RegistryObject<ExampleChestBlock> EXAMPLE_CHEST = BLOCKS.register("example_chest", ExampleChestBlock::new);
}
