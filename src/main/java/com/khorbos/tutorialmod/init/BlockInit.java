package com.khorbos.tutorialmod.init;

import com.electronwill.nightconfig.core.ConfigSpec;
import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.objects.blocks.*;
import com.khorbos.tutorialmod.tileentity.ExampleChestTileEntity;
import com.khorbos.tutorialmod.world.features.ExampleTree;
import net.minecraft.block.*;
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
    public static final RegistryObject<ExampleSaplingBlock> EXAMPLE_SAPLING = BLOCKS.register("example_sapling", () -> new ExampleSaplingBlock(() -> new ExampleTree(), Block.Properties.from(Blocks.OAK_SAPLING)));
    public static final RegistryObject<LogBlock> EXAMPLE_LOG = BLOCKS.register("example_log", () -> new LogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> EXAMPLE_PLANKS = BLOCKS.register("example_planks", () -> new Block(Block.Properties.from(BlockInit.EXAMPLE_LOG.get())));
    public static final RegistryObject<LeavesBlock> EXAMPLE_LEAVES = BLOCKS.register("example_leaves", () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<QuarryBlock> QUARRY = BLOCKS.register("quarry", QuarryBlock::new);

    public static final RegistryObject<ExampleChestBlock> EXAMPLE_CHEST = BLOCKS.register("example_chest", ExampleChestBlock::new);
    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop", () -> new CornCrop(Block.Properties.from(Blocks.WHEAT)));

    public static final RegistryObject<Block> EXAMPLE_DOOR = BLOCKS.register("example_door", () -> new CustomDoorBlock(Block.Properties.from(BlockInit.EXAMPLE_PLANKS.get())));
}
