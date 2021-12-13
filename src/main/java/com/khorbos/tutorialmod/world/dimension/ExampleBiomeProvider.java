package com.khorbos.tutorialmod.world.dimension;

import com.google.common.collect.ImmutableSet;
import com.khorbos.tutorialmod.init.BiomeInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Random;
import java.util.Set;

public class ExampleBiomeProvider extends BiomeProvider {
    private Random rand;
    private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.EXAMPLE_BIOME.get());

    public ExampleBiomeProvider() {
        super(biomeList);
        rand = new Random();
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return BiomeInit.EXAMPLE_BIOME.get();
    }
}
