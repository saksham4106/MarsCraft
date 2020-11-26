package com.marscraft.marscraftmod.util;


import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.client.gui.ElectricSmelterScreen;
import com.marscraft.marscraftmod.client.gui.ElectrolycticChamberScreen;
import com.marscraft.marscraftmod.init.ModContainerTypes;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MarsCraftMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.ELECTRIC_SMELTER.get(), ElectricSmelterScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ELECTROLYCTIC_CHAMBER.get(), ElectrolycticChamberScreen::new);
		//ItemInit.GAS_BUCKET.get().addPropertyOverride(new ResourceLocation, getter);
		
	}

}
