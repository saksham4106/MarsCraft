package com.marscraft.marscraftmod.container;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.marscraft.marscraftmod.init.BlockInit;
import com.marscraft.marscraftmod.init.ModContainerTypes;
import com.marscraft.marscraftmod.tileentity.ElectrolycticChamberTileEntity;
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

public class ElectrolycticChamberContainer extends Container{
	
	public ElectrolycticChamberTileEntity tileEntity;
	private IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentSmeltTime;
	public FunctionalIntReferenceHolder hydrogenCount;
	
	public ElectrolycticChamberContainer(final int windowId, final PlayerInventory playerInv,
			final ElectrolycticChamberTileEntity tile) {
		super(ModContainerTypes.ELECTROLYCTIC_CHAMBER.get(), windowId);
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
		
		this.addSlot(new SlotItemHandler(tile.getInventory(), 0, 29, 39));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 1, 60, 71));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 2, 91, 71));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 3, 121, 71));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 4, 150, 71));
		
		this.trackInt(currentSmeltTime = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime,
				value -> this.tileEntity.currentSmeltTime = value));
		
		this.trackInt(hydrogenCount = new FunctionalIntReferenceHolder(() -> this.tileEntity.hydrogenCount,
				value -> this.tileEntity.hydrogenCount = value));
		
	}
	// Client Constructor
	public ElectrolycticChamberContainer(final int windowID, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowID, playerInv, getTileEntity(playerInv, data));
	}

	private static ElectrolycticChamberTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "playerInv cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof ElectrolycticChamberTileEntity) {
			return (ElectrolycticChamberTileEntity) tileAtPos;
		}
		throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.ELECTROLYCTIC_CHAMBER.get());
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
	public int getHydrogen() {
		return this.hydrogenCount.get() != 0 && this.tileEntity.maxHydrogenCount != 0
				? this.hydrogenCount.get() * 46 / this.tileEntity.maxHydrogenCount
				: 0;
	}
	

	@OnlyIn(Dist.CLIENT)
	public int getSmeltProgressionScaled() {
		return this.currentSmeltTime.get() != 0 && this.tileEntity.maxSmeltTime != 0
				? this.currentSmeltTime.get() * 46 / this.tileEntity.maxSmeltTime
				: 0;
	}


}

