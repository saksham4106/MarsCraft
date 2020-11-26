package com.marscraft.marscraftmod.tileentity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.container.ElectricSmelterContainer;
import com.marscraft.marscraftmod.init.ModTileEntityTypes;
import com.marscraft.marscraftmod.init.RecipeSerializerInit;
import com.marscraft.marscraftmod.objects.blocks.ElectricSmelterBlock;
import com.marscraft.marscraftmod.recipes.electricSmelter.ElectricSmelterRecipe;
import com.marscraft.marscraftmod.util.ModItemHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ElectricSmelterTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
	private ITextComponent customName;
	public int currentSmeltTime;
	public int currentSmeltTime1;
	public int currentSmeltTime2;
	public int currentSmeltTime3;
	public int maxSmeltTime = 50;
	private ModItemHandler inventory;

	public ElectricSmelterTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);

		this.inventory = new ModItemHandler(9);


	}

	public ElectricSmelterTileEntity() {
		this(ModTileEntityTypes.ELECTRIC_SMELTER.get());
	}

	@Override
	public Container createMenu(final int windowID, final PlayerInventory playerInv, final PlayerEntity playerIn) {
		return new ElectricSmelterContainer(windowID, playerInv, this);
	}

	@Override
	public void tick() {
		boolean dirty = false;

		if (this.world != null && !this.world.isRemote) {
			if (this.world.isBlockPowered(this.getPos())) {
				/*
				 * 				if (this.getRecipe(this.inventory.getStackInSlot(8), 8).getRecipeOutput().getItem() == BlockInit.ELECTRIC_SMELTER.get().asItem() ) {
					System.out.println(this.maxSmeltTime);
					this.maxSmeltTime = 10;
				}else {
					this.maxSmeltTime = 50;
				}
				 */
				if (this.getRecipe(this.inventory.getStackInSlot(0), 0) != null
						|| this.getRecipe(this.inventory.getStackInSlot(1), 1) != null
						|| this.getRecipe(this.inventory.getStackInSlot(2), 2) != null
						|| this.getRecipe(this.inventory.getStackInSlot(3), 3) != null) {
					if (this.currentSmeltTime != this.maxSmeltTime 
							&& this.currentSmeltTime1 != this.maxSmeltTime
							&& this.currentSmeltTime2 != this.maxSmeltTime
							&& this.currentSmeltTime3 != this.maxSmeltTime) {
						this.world.setBlockState(this.getPos(),
								this.getBlockState().with(ElectricSmelterBlock.LIT, true));
					
						
						if (this.getRecipe(this.inventory.getStackInSlot(0), 0) != null) {
							this.currentSmeltTime++;
						}

						if (this.getRecipe(this.inventory.getStackInSlot(1), 1) != null) {
							this.currentSmeltTime1++;
						}
						
						if (this.getRecipe(this.inventory.getStackInSlot(2), 2) != null) {
							this.currentSmeltTime2++;
						}
						
						if (this.getRecipe(this.inventory.getStackInSlot(3), 3) != null) {
							this.currentSmeltTime3++;
						}

						dirty = true;
					} else {
						this.world.setBlockState(this.getPos(),
								this.getBlockState().with(ElectricSmelterBlock.LIT, false));

						if (this.getRecipe(this.inventory.getStackInSlot(0), 0) != null) {
							ItemStack output = this.getRecipe(this.inventory.getStackInSlot(0), 0).getRecipeOutput();
							this.inventory.insertItem(4, output.copy(), false);
							this.inventory.decrStackSize(0, 1);
							dirty = true;

							this.currentSmeltTime = 0;
						}

						if (this.getRecipe(this.inventory.getStackInSlot(1), 1) != null) {
							ItemStack output = this.getRecipe(this.inventory.getStackInSlot(1), 1).getRecipeOutput();
							this.inventory.insertItem(5, output.copy(), false);
							this.inventory.decrStackSize(1, 1);
							dirty = true;

							this.currentSmeltTime1 = 0;
						}

						if (this.getRecipe(this.inventory.getStackInSlot(2), 2) != null) {
							ItemStack output = this.getRecipe(this.inventory.getStackInSlot(2), 2).getRecipeOutput();
							this.inventory.insertItem(6, output.copy(), false);
							this.inventory.decrStackSize(2, 1);
							dirty = true;
							
							this.currentSmeltTime2 = 0;
						}

						if (this.getRecipe(this.inventory.getStackInSlot(3), 3) != null) {
							ItemStack output = this.getRecipe(this.inventory.getStackInSlot(3), 3).getRecipeOutput();
							this.inventory.insertItem(7, output.copy(), false);
							this.inventory.decrStackSize(3, 1);
							dirty = true;
							
							this.currentSmeltTime3 = 0;
						}
					}
				}
			}
		}

		if (dirty) {
			this.markDirty();
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(),
					Constants.BlockFlags.BLOCK_UPDATE);
		}
	}

	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}

	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}

	private ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + MarsCraftMod.MOD_ID + ".example_furnace");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
		}

		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, inv);
		this.inventory.setNonNullList(inv);

		this.currentSmeltTime = compound.getInt("CurrentSmeltTime");
		this.currentSmeltTime1 = compound.getInt("CurrentSmeltTime1");
		this.currentSmeltTime1 = compound.getInt("CurrentSmeltTime2");
		this.currentSmeltTime1 = compound.getInt("CurrentSmeltTime3");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (this.customName != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}

		ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
		compound.putInt("CurrentSmeltTime", this.currentSmeltTime);
		compound.putInt("CurrentSmeltTime1", this.currentSmeltTime1);
		compound.putInt("CurrentSmeltTime2", this.currentSmeltTime1);
		compound.putInt("CurrentSmeltTime3", this.currentSmeltTime1);

		return compound;
	}

	@Nullable
	private ElectricSmelterRecipe getRecipe(ItemStack stack, int slot) {
		if (stack == null) {
			return null;
		}

		Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.ELECTRIC_SMELTER_TYPE, this.world);
		for (IRecipe<?> iRecipe : recipes) {
			ElectricSmelterRecipe recipe = (ElectricSmelterRecipe) iRecipe;

			if (recipe.matchesSlot(new RecipeWrapper(this.inventory), this.world, slot)) {
				recipe.getIngredients();
				return recipe;
			}
		}

		return null;
	}

	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	@OnlyIn(Dist.CLIENT)
	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
		ClientWorld world = Minecraft.getInstance().world;
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
		Set<ItemStack> inputs = new HashSet<ItemStack>();
		Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
		for (IRecipe<?> recipe : recipes) {
			NonNullList<Ingredient> ingredients = recipe.getIngredients();
			ingredients.forEach(ingredient -> {
				for (ItemStack stack : ingredient.getMatchingStacks()) {
					inputs.add(stack);
				}
			});
		}
		return inputs;
	}

	public final IItemHandlerModifiable getInventory() {
		return this.inventory;
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return new SUpdateTileEntityPacket(this.pos, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundNBT nbt) {
		this.read(nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
	}
}
