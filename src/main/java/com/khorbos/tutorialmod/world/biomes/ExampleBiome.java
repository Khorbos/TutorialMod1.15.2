package com.khorbos.tutorialmod.world.biomes;

import com.khorbos.tutorialmod.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ExampleBiome extends Biome {
    public ExampleBiome() {
        this(new Biome.Builder()
                .precipitation(RainType.SNOW)
                .scale(1.2F)
                .temperature(-0.8F)
                .waterColor(4159204)
                .waterFogColor(329011)
                .surfaceBuilder(SurfaceBuilder.MOUNTAIN, new SurfaceBuilderConfig(
                        BlockInit.EXAMPLE_BLOCK.get().getDefaultState(), // Top block (main block of the biome)
                        BlockInit.EXAMPLE_BLOCK.get().getDefaultState(), // Middle block (the block under top block (i.e. the dirt under the grass))
                        Blocks.COARSE_DIRT.getDefaultState()             // The block you will find underwater in the biome
                ))
                .category(Category.PLAINS)
                .depth(0.13F)       // How low down or high up the biome is (default is 0.125)
                .downfall(0.5F)     // How often it rains
                .parent("plains")   // what parent biome is it (i.e., parent of BambooForest is Jungle)
        );
    }

    public ExampleBiome(Builder biomeBuilder) {
        super(biomeBuilder);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.BEE, 12, 4, 8));
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.142858F)));
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.HELL_CAVE, new ProbabilityConfig(0.142826F)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.FOSSIL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_PASSTHROUGH.configure(new ChanceConfig(256))));
        this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.DARK_OAK_TREE.withConfiguration(DefaultBiomeFeatures.DARK_OAK_TREE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(64))));
        DefaultBiomeFeatures.addExtraGoldOre(this);
        DefaultBiomeFeatures.addExtraEmeraldOre(this);
    }
}
