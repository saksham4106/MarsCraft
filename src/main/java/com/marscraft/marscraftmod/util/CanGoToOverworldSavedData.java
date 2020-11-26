package com.marscraft.marscraftmod.util;

import com.marscraft.marscraftmod.MarsCraftMod;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;

public class CanGoToOverworldSavedData extends WorldSavedData{

	private static final String DATA_NAME = MarsCraftMod.MOD_ID + "_canTeleportOverworld";
	public boolean canTeleport = false;
	
	
	public CanGoToOverworldSavedData() {
		super(DATA_NAME);
	}
	public CanGoToOverworldSavedData(String name) {
		super(name);
	}
	
	public void setter(boolean teleport) {
		this.canTeleport = teleport;
	}
	
	public boolean getter() {
		return this.canTeleport;
	}

	@Override
	public void read(CompoundNBT nbt) {
		this.canTeleport = nbt.getBoolean("CanRun");
		
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("CanRun", this.canTeleport);
		return compound;
	}

}
