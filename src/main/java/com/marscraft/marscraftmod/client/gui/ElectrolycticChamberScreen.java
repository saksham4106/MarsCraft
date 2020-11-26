package com.marscraft.marscraftmod.client.gui;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.container.ElectrolycticChamberContainer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectrolycticChamberScreen extends ContainerScreen<ElectrolycticChamberContainer>{

	private static final ResourceLocation TEXTURE = new ResourceLocation(MarsCraftMod.MOD_ID,
			"textures/gui/electrolyctic_chamber.png");
	
	
	public ElectrolycticChamberScreen(ElectrolycticChamberContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		
		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 176;
		this.ySize = 178;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		this.blit(this.guiLeft + 60, this.guiTop + 18, 176, 0, 16, this.container.getHydrogen());
		//this.blit(this.guiLeft + 60, this.guiTop + 18, 176, 0, 16, this.container.getSmeltProgressionScaled());
	}

	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
