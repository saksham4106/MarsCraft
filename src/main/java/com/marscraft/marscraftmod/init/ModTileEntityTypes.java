package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.tileentity.ElectricSmelterTileEntity;
import com.marscraft.marscraftmod.tileentity.ElectrolycticChamberTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, MarsCraftMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<ElectricSmelterTileEntity>> ELECTRIC_SMELTER = TILE_ENTITY_TYPES
			.register("electric_smelter", () -> TileEntityType.Builder
					.create(ElectricSmelterTileEntity::new, BlockInit.ELECTRIC_SMELTER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<ElectrolycticChamberTileEntity>> ELECTROLYCTIC_CHAMBER = TILE_ENTITY_TYPES
			.register("electrolyctic_chamber", () -> TileEntityType.Builder
					.create(ElectrolycticChamberTileEntity::new, BlockInit.ELECTROLYCTIC_CHAMBER.get()).build(null));	
	
	
	

}
