package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.objects.blocks.ElectricSmelterBlock;
import com.marscraft.marscraftmod.objects.blocks.ElectrolycticChamberBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
			MarsCraftMod.MOD_ID);
	
	public static final RegistryObject<Block> MARTIAN_ROCK = BLOCKS.register("martian_rock",
			() -> new Block(Block.Properties.from(Blocks.RED_SANDSTONE)));
	
	public static final RegistryObject<Block> MARTIAN_COBBLEROCK = BLOCKS.register("martian_cobblerock",
			() -> new Block(Block.Properties.from(Blocks.RED_SANDSTONE)));
	
	public static final RegistryObject<Block> MARTIAN_SOIL = BLOCKS.register("martian_soil",
			() -> new FallingBlock(Block.Properties.from(Blocks.SAND)));
	
	public static final RegistryObject<Block> DUSTY_STONE = BLOCKS.register("dusty_stone",
			() -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE)));
	
	
	
	public static final RegistryObject<Block> ELECTRIC_SMELTER = BLOCKS.register("electric_smelter",
			() -> new ElectricSmelterBlock(Block.Properties.create(Material.IRON)));
	
	public static final RegistryObject<Block> ELECTROLYCTIC_CHAMBER = BLOCKS.register("electrolyctic_chamber",
			() -> new ElectrolycticChamberBlock(Block.Properties.create(Material.MISCELLANEOUS)));
	
	
	
	
	
	public static final RegistryObject<OreBlock> TITANIUM_ORE = BLOCKS.register("titanium_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(20.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<OreBlock> COPPER_ORE = BLOCKS.register("copper_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<OreBlock> NICKEL_ORE = BLOCKS.register("nickel_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<OreBlock> ZINC_ORE = BLOCKS.register("zinc_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<OreBlock> OSMIUM_ORE = BLOCKS.register("osmium_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(7.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<OreBlock> PLATINUM_ORE = BLOCKS.register("platinum_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(7.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<OreBlock> SAPPHIRE_ORE = BLOCKS.register("sapphire_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(7.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<OreBlock> SILVER_ORE = BLOCKS.register("silver_ore",
			() -> new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE)));
	
	
	
	
	public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block",
			() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	
	public static final RegistryObject<Block> COPPER_BLOCk = BLOCKS.register("copper_block",
			() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	
	public static final RegistryObject<Block> NICKEL_BLOCK = BLOCKS.register("nickel_block",
			() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	
	public static final RegistryObject<Block> ZINC_BLOCK = BLOCKS.register("zinc_block",
		    () -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	
	public static final RegistryObject<Block> OSMIUM_BLOCK = BLOCKS.register("osmium_block",
			() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	
	public static final RegistryObject<Block> PLATINUM_BLOCK = BLOCKS.register("platinum_block",
			() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	
	public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block",
			() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	
		

}
