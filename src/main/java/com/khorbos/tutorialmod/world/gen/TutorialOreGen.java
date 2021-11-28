package com.khorbos.tutorialmod.world.gen;

import com.khorbos.tutorialmod.init.BlockInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialOreGen {
    public static void generateOre(){
        ForgeRegistries.BIOMES.forEach(biome -> {
            if (biome == Biomes.PLAINS) {
                ConfiguredPlacement<CountRangeConfig> customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 5, 5, 25));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.EXAMPLE_BLOCK.get().getDefaultState(), 10)).withPlacement(customConfig));
            } else if (biome.getCategory().equals(Biome.Category.OCEAN)) {
                ConfiguredPlacement<CountRangeConfig> customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(30, 5, 5, 25));
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.EXAMPLE_BLOCK.get().getDefaultState(), 32)).withPlacement(customConfig));
                 biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.EXAMPLE_BLOCK.get().getDefaultState(), 32)).withPlacement(customConfig));
            }
        });
    }
}
