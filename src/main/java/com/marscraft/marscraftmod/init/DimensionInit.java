package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.world.dimension.MarsModDimension;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {
	
	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MarsCraftMod.MOD_ID);
	
	public static final RegistryObject<ModDimension> MARS_DIMENSION = MOD_DIMENSIONS.register("mars_dimension",
			() -> new MarsModDimension());
	

}
