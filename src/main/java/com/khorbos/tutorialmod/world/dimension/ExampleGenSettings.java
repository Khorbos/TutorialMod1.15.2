package com.khorbos.tutorialmod.world.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.GenerationSettings;

public class ExampleGenSettings extends GenerationSettings {
    private BlockPos spawnPos;

    public int getBiomeSize() {
        return 4;
    }

    public int getRiverSize() {
        return 4;
    }

    public int getBiomeId() {
        return -1;
    }

    @Override
    public int getBedrockFloorHeight() {
        return 0;
    }

    public ExampleGenSettings setSpawnPos(BlockPos pos) {
        this.spawnPos = pos;
        return this;
    }

    public BlockPos getSpawnPos() {
        return this.spawnPos;
    }
}
