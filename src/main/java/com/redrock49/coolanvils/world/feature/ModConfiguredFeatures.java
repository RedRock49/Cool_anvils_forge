package com.redrock49.coolanvils.world.feature;

import com.redrock49.coolanvils.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class ModConfiguredFeatures {

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_MYTHRIL_ORES = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.MYTHRIL_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.MYTHRIL_ORE_DEEPSLATE.get().defaultBlockState()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MYTHRIL_ORE = FeatureUtils.register("mythril_ore", Feature.ORE, new OreConfiguration(OVERWORLD_MYTHRIL_ORES, 6));
}
