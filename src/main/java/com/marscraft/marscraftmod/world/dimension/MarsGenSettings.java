package com.marscraft.marscraftmod.world.dimension;

import net.minecraft.world.gen.GenerationSettings;

public class MarsGenSettings extends GenerationSettings{
	
	public int getBiomeSize() {
		return 4;
	}
	
	public int getRiverSize() {
		return 0;
	}
	
	
	public int getBiomeId() {
		return -1;
	}
	
	
	@Override
	public int getBedrockFloorHeight() {
		return 0;
	}
	
}
