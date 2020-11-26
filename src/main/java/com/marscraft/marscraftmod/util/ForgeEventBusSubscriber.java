package com.marscraft.marscraftmod.util;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.init.DimensionInit;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MarsCraftMod.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if(DimensionType.byName(MarsCraftMod.MARS_DIM_TYPE) == null) {
			DimensionManager.registerDimension(MarsCraftMod.MARS_DIM_TYPE, DimensionInit.MARS_DIMENSION.get(), null, true);
			
		}
		MarsCraftMod.LOGGER.info("Dimensions registered");
	}
	

}
