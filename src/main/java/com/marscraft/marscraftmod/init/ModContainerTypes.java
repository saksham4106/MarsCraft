package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.container.ElectricSmelterContainer;
import com.marscraft.marscraftmod.container.ElectrolycticChamberContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(
			ForgeRegistries.CONTAINERS, MarsCraftMod.MOD_ID);
	
	public static final RegistryObject<ContainerType<ElectricSmelterContainer>> ELECTRIC_SMELTER = CONTAINER_TYPES
			.register("electric_smelter", () -> IForgeContainerType.create(ElectricSmelterContainer::new));
	
	public static final RegistryObject<ContainerType<ElectrolycticChamberContainer>> ELECTROLYCTIC_CHAMBER = CONTAINER_TYPES
			.register("electrolyctic_chamber", () -> IForgeContainerType.create(ElectrolycticChamberContainer::new));
	

}
