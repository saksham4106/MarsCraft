package com.marscraft.marscraftmod.recipes.electricSmelter;

import com.marscraft.marscraftmod.init.RecipeSerializerInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ElectricSmelterRecipe implements IElectricSmelterRecipe{
	private final ResourceLocation id;
	private Ingredient input;
	private final ItemStack output;

	public ElectricSmelterRecipe(ResourceLocation id, Ingredient input, ItemStack output ) {
		this.id = id;
		this.output = output;
		this.input = input;
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		return this.output;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean matches(RecipeWrapper inv, World worldIn, int slot) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public boolean matchesSlot(RecipeWrapper inv, World worldIn, int slot) {
        return this.input.test(inv.getStackInSlot(slot));
    }



}
