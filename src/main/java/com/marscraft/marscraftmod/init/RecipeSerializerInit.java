package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.recipes.ElectrolycticChamber.ElectrolycticChamberRecipe;
import com.marscraft.marscraftmod.recipes.ElectrolycticChamber.ElectrolycticChamberRecipeSerializer;
import com.marscraft.marscraftmod.recipes.ElectrolycticChamber.IElectrolycticChamberRecipe;
import com.marscraft.marscraftmod.recipes.electricSmelter.ElectricSmelterRecipe;
import com.marscraft.marscraftmod.recipes.electricSmelter.ElectricSmelterRecipeSerializer;
import com.marscraft.marscraftmod.recipes.electricSmelter.IElectricSmelterRecipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {
	
	
	public static final IRecipeSerializer<ElectricSmelterRecipe> ELECTRIC_SMELTER_RECIPE_SERIALIZER = new ElectricSmelterRecipeSerializer();
	public static final IRecipeType<IElectricSmelterRecipe> ELECTRIC_SMELTER_TYPE = registerType(IElectricSmelterRecipe.RECIPE_TYPE_ID);
	
	public static final IRecipeSerializer<ElectrolycticChamberRecipe> ELECTROLYTIC_CHAMBER_RECIPE_SERIALIZER = new ElectrolycticChamberRecipeSerializer();
	public static final IRecipeType<IElectrolycticChamberRecipe> ELECTROLYCTIC_CHAMBER_TYPE = registerType(IElectrolycticChamberRecipe.RECIPE_TYPE_ID);
	
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(
			ForgeRegistries.RECIPE_SERIALIZERS, MarsCraftMod.MOD_ID);
	
	public static final RegistryObject<IRecipeSerializer<?>> ELECTRIC_SMELTER_SERIALIZER = RECIPE_SERIALIZERS.register("electric_smelter",
			() -> ELECTRIC_SMELTER_RECIPE_SERIALIZER);
	
	public static final RegistryObject<IRecipeSerializer<?>> ELECTROLYTIC_CHAMBER_SERIALIZER = RECIPE_SERIALIZERS.register("electrolyctic_chamber",
			() -> ELECTROLYTIC_CHAMBER_RECIPE_SERIALIZER);
	
	
	
		
	
	private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
		@Override
		public String toString() {
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
		return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
	}

}
