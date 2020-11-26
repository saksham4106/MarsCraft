package com.marscraft.marscraftmod.recipes.ElectrolycticChamber;

import javax.annotation.Nonnull;

import com.marscraft.marscraftmod.MarsCraftMod;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IElectrolycticChamberRecipe extends IRecipe<RecipeWrapper> {
	ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(MarsCraftMod.MOD_ID, "electrolyctic_chamber");
	
	@Nonnull
	@Override
	default IRecipeType<?> getType() {
		return Registry.RECIPE_TYPE.getValue(RECIPE_TYPE_ID).get();
	}
	
	@Override
	default boolean canFit(int width, int height) {
		return false;
	}
	
	Ingredient getInput();

	boolean matches(RecipeWrapper inv, World worldIn, int slot);

	ItemStack getRecipeOutput2();

	ItemStack getRecipeOutput3();

	ItemStack getRecipeOutput4();


}
