package com.marscraft.marscraftmod.container;


import java.util.Objects;

import javax.annotation.Nonnull;

import com.marscraft.marscraftmod.init.BlockInit;
import com.marscraft.marscraftmod.init.ModContainerTypes;
import com.marscraft.marscraftmod.tileentity.ElectricSmelterTileEntity;
import com.marscraft.marscraftmod.util.FunctionalIntReferenceHolder;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.SlotItemHandler;

public class ElectricSmelterContainer extends Container {

	public ElectricSmelterTileEntity tileEntity;
	private IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentSmeltTime;
	public FunctionalIntReferenceHolder currentSmeltTime1;
	public FunctionalIntReferenceHolder currentSmeltTime2;
	public FunctionalIntReferenceHolder currentSmeltTime3;

	// Server Constructor
	public ElectricSmelterContainer(final int windowID, final PlayerInventory playerInv,
			final ElectricSmelterTileEntity tile) {
		super(ModContainerTypes.ELECTRIC_SMELTER.get(), windowID);

		this.tileEntity = tile;
		this.canInteractWithCallable = IWorldPosCallable.of(tile.getWorld(), tile.getPos());

		final int slotSizePlus2 = 18;
		final int startX = 8;
 
		// Inventory
 
		int hotbarY = 154;
		for (int column = 0; column < 9; column++) {
			this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
		}
 
		final int startY = 96;
 
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
						startY + (row * slotSizePlus2)));
			}
		}
 
 
		this.addSlot(new SlotItemHandler(tile.getInventory(), 0, 23, 12));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 1, 65, 12));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 2, 105, 12));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 3, 143, 12));
 
		this.addSlot(new SlotItemHandler(tile.getInventory(), 4, 23, 66));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 5, 65, 66));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 6, 105, 66));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 7, 143, 66));
		
		
		this.addSlot(new SlotItemHandler(tile.getInventory(), 8, 30, 28));
 
 
		this.trackInt(currentSmeltTime = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime,
				value -> this.tileEntity.currentSmeltTime = value));
		
		this.trackInt(currentSmeltTime1 = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime1,
				value -> this.tileEntity.currentSmeltTime1 = value));
		
		this.trackInt(currentSmeltTime2 = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime2,
				value -> this.tileEntity.currentSmeltTime2 = value));
		
		this.trackInt(currentSmeltTime3 = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime3,
				value -> this.tileEntity.currentSmeltTime3 = value));
 
 
	}

	// Client Constructor
	public ElectricSmelterContainer(final int windowID, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowID, playerInv, getTileEntity(playerInv, data));
	}

	private static ElectricSmelterTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "playerInv cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof ElectricSmelterTileEntity) {
			return (ElectricSmelterTileEntity) tileAtPos;
		}
		throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.ELECTRIC_SMELTER.get());
	}

	@Nonnull
	@Override
	public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
		ItemStack returnStack = ItemStack.EMPTY;
		final Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			final ItemStack slotStack = slot.getStack();
			returnStack = slotStack.copy();

			final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
			if (index < containerSlots) {
				if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
			if (slotStack.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			if (slotStack.getCount() == returnStack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(player, slotStack);
		}
		return returnStack;
	}

	@OnlyIn(Dist.CLIENT)
	public int getSmeltProgressionScaled() {
		return this.currentSmeltTime.get() != 0 && this.tileEntity.maxSmeltTime != 0
				? this.currentSmeltTime.get() * 24 / this.tileEntity.maxSmeltTime
				: 0;
	}
	
	

	@OnlyIn(Dist.CLIENT)
	public int getSmeltProgressionScaled1() {
		return this.currentSmeltTime1.get() != 0 && this.tileEntity.maxSmeltTime != 0
				? this.currentSmeltTime1.get() * 24 / this.tileEntity.maxSmeltTime
				: 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getSmeltProgressionScaled2() {
		return this.currentSmeltTime2.get() != 0 && this.tileEntity.maxSmeltTime != 0
				? this.currentSmeltTime2.get() * 24 / this.tileEntity.maxSmeltTime
				: 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getSmeltProgressionScaled3() {
		return this.currentSmeltTime3.get() != 0 && this.tileEntity.maxSmeltTime != 0
				? this.currentSmeltTime3.get() * 24 / this.tileEntity.maxSmeltTime
				: 0;
	}
}
