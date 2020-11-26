package com.marscraft.marscraftmod.recipes.ElectrolycticChamber;

import com.marscraft.marscraftmod.init.RecipeSerializerInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ElectrolycticChamberRecipe implements IElectrolycticChamberRecipe {
	private final ResourceLocation id;
	private Ingredient input;
	private final ItemStack hydrogen_output;
	private final ItemStack oxygen_output;
	private final ItemStack chlorine_output;
	private final ItemStack wasteOutput;

	public ElectrolycticChamberRecipe(ResourceLocation id, Ingredient input, ItemStack hydrogen_output,
			ItemStack oxygen_output, ItemStack chlorine_output, ItemStack wasteOutput) {
		this.id = id;
		this.hydrogen_output = hydrogen_output;
		this.oxygen_output = oxygen_output;
		this.chlorine_output = chlorine_output;
		this.input = input;
		this.wasteOutput = wasteOutput;
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		return this.hydrogen_output;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.hydrogen_output;
	}

	@Override
	public ItemStack getRecipeOutput2() {
		return this.oxygen_output;
	}

	@Override
	public ItemStack getRecipeOutput3() {
		return this.chlorine_output;
	}
	
	@Override
	public ItemStack getRecipeOutput4() {
		return this.wasteOutput;
	}


	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return RecipeSerializerInit.ELECTRIC_SMELTER_SERIALIZER.get();
	}

	@Override
	public Ingredient getInput() {
		return this.input;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.from(null, this.input);
	}

	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) {
		return false;
	}

	@Override
	public boolean matches(RecipeWrapper inv, World worldIn, int slot) {
		return false;
	}

	public boolean matchesSlot(RecipeWrapper inv, World worldIn, int slot) {
		return this.input.test(inv.getStackInSlot(slot));
	}

}
