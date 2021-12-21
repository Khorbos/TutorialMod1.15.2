package com.khorbos.tutorialmod.world.features;

import com.khorbos.tutorialmod.init.BlockInit;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class ExampleTree extends Tree {
    public static final TreeFeatureConfig EXAMPLE_TREE_CONFIG = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockInit.EXAMPLE_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(BlockInit.EXAMPLE_LEAVES.get().getDefaultState()),
            new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines()
            .setSapling((IPlantable) BlockInit.EXAMPLE_SAPLING.get()).build();

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean b) {
        return Feature.NORMAL_TREE.withConfiguration(EXAMPLE_TREE_CONFIG);

    }
}
