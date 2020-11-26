package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {

	public static final ResourceLocation SEAWATER_STILL_RL = new ResourceLocation(MarsCraftMod.MOD_ID,
			"blocks/seawater_still");
	public static final ResourceLocation SEAWATER_FLOWING_RL = new ResourceLocation(MarsCraftMod.MOD_ID,
			"blocks/seawater_flowing");
	public static final ResourceLocation SEAWATER_OVERLAY_RL = new ResourceLocation(MarsCraftMod.MOD_ID,
			"blocks/seawater_overlay");

	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS,
			MarsCraftMod.MOD_ID);

	public static final RegistryObject<FlowingFluidBlock> SEAWATER_BLOCK = BlockInit.BLOCKS.register("seawater",
			() -> new FlowingFluidBlock(() -> FluidInit.SEAWATER_FLUID.get(), Block.Properties.create(Material.WATER)
					.doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

	public static final RegistryObject<FlowingFluid> SEAWATER_FLUID = FLUIDS.register("seawater_fluid",
			() -> new ForgeFlowingFluid.Source(FluidInit.SEAWATER_PROPERTIES));

	public static final RegistryObject<FlowingFluid> SEAWATER_FLOWING = FLUIDS.register("seawater_flowing",
			() -> new ForgeFlowingFluid.Flowing(FluidInit.SEAWATER_PROPERTIES));

	public static final ForgeFlowingFluid.Properties SEAWATER_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> SEAWATER_FLUID.get(), () -> SEAWATER_FLOWING.get(),
			FluidAttributes.builder(SEAWATER_STILL_RL, SEAWATER_FLOWING_RL).density(1).luminosity(0)
					.rarity(Rarity.COMMON).sound(SoundEvents.ITEM_BUCKET_EMPTY).overlay(SEAWATER_OVERLAY_RL))
							.block(() -> FluidInit.SEAWATER_BLOCK.get()).bucket(() -> ItemInit.SEAWATER_BUCKET.get());

}
