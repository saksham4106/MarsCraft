package com.marscraft.marscraftmod.client.gui;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.container.ElectricSmelterContainer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectricSmelterScreen extends ContainerScreen<ElectricSmelterContainer>{

	
	private static final ResourceLocation TEXTURE = new ResourceLocation(MarsCraftMod.MOD_ID,
			"textures/gui/electric_smelter.png");
	
	
	
	
	public ElectricSmelterScreen(ElectricSmelterContainer screenContainer, PlayerInventory inv,
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

	this.blit(this.guiLeft + 22, this.guiTop + 35, 176, 0, 18, this.container.getSmeltProgressionScaled());
	this.blit(this.guiLeft + 65, this.guiTop + 35, 176, 0, 18, this.container.getSmeltProgressionScaled1());
	this.blit(this.guiLeft + 104, this.guiTop + 35, 176, 0, 18, this.container.getSmeltProgressionScaled2());
	this.blit(this.guiLeft + 143, this.guiTop + 35, 176, 0, 18, this.container.getSmeltProgressionScaled3());
	}
	

	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
