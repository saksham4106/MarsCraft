package com.marscraft.marscraftmod.recipes.ElectrolycticChamber;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ElectrolycticChamberRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<ElectrolycticChamberRecipe> {

	@Override
	public ElectrolycticChamberRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack hydrogen_output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "hydrogen_output"), true);
		ItemStack oxygen_output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "oxygen_output"), true);
		ItemStack chlorine_output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "chlorine_output"), true);
		ItemStack waste_output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "waste_output"), true);
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
 
		return new ElectrolycticChamberRecipe(recipeId, input, hydrogen_output, oxygen_output, chlorine_output, waste_output);
	}

	@Override
	public ElectrolycticChamberRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack hydrogen_output = buffer.readItemStack();
		ItemStack oxygen_output = buffer.readItemStack();
		ItemStack chlorine_output = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);
		ItemStack waste_output = buffer.readItemStack();

		return new ElectrolycticChamberRecipe(recipeId, input, hydrogen_output, oxygen_output, chlorine_output, waste_output);
	}

	@Override
	public void write(PacketBuffer buffer, ElectrolycticChamberRecipe recipe) {
		Ingredient input = recipe.getIngredients().get(0);
		input.write(buffer);

		buffer.writeItemStack(recipe.getRecipeOutput(), false);
	}
}
