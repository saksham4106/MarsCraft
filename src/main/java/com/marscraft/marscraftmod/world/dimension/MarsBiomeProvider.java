package com.marscraft.marscraftmod.world.dimension;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.marscraft.marscraftmod.init.BiomeInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class MarsBiomeProvider extends BiomeProvider{

	@SuppressWarnings("unused")
	private Random rand;
	public MarsBiomeProvider() {
		super(biomeList);
		rand = new Random();
	}
	
	private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.MARS.get());
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return BiomeInit.MARS.get();
	}

}
