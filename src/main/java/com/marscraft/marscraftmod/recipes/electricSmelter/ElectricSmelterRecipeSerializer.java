package com.marscraft.marscraftmod.recipes.electricSmelter;


import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ElectricSmelterRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<ElectricSmelterRecipe> {

	@Override
	public ElectricSmelterRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));

		return new ElectricSmelterRecipe(recipeId, input, output);
	}	

	@Override
	public ElectricSmelterRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output_1 = buffer.readItemStack();
		
		Ingredient input = Ingredient.read(buffer);

		return new ElectricSmelterRecipe(recipeId, input, output_1);
	}

	@Override
	public void write(PacketBuffer buffer, ElectricSmelterRecipe recipe) {
		Ingredient input = recipe.getIngredients().get(0);
		input.write(buffer);

		buffer.writeItemStack(recipe.getRecipeOutput(), false);
	}
}
