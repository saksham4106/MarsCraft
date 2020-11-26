package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.MarsCraftMod.MarsCraftItemGroup;
import com.marscraft.marscraftmod.objects.items.HammerItem;
import com.marscraft.marscraftmod.objects.items.PaxswelItem;
import com.marscraft.marscraftmod.util.enums.ModItemTiers;

import net.minecraft.item.AxeItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
			MarsCraftMod.MOD_ID);

	public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OSMIUM_INGOT = ITEMS.register("osmium_ingot",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> SEAWATER_BUCKET = ITEMS.register("seawater_bucket",
			() -> new BucketItem(() -> FluidInit.SEAWATER_FLUID.get(),
					new Item.Properties().group(MarsCraftItemGroup.instance).maxStackSize(1)));
	// Tools

	// TITANIUM TOOLS

	// ItemTier, attackDamage, attackSpeed, ItemProperties

	public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
			() -> new PickaxeItem(ModItemTiers.TITANIUM, 4, 2.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
			() -> new SwordItem(ModItemTiers.TITANIUM, 11, 1.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
			() -> new ShovelItem(ModItemTiers.TITANIUM, 3, 2.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
			() -> new AxeItem(ModItemTiers.TITANIUM, 14, 3.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
			() -> new HoeItem(ModItemTiers.TITANIUM, 3, new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_PAXSWEL = ITEMS.register("titanium_paxswel",
			() -> new PaxswelItem(ModItemTiers.TITANIUM, 11, 1.5f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_HAMMER = ITEMS.register("titanium_hammer",
			() -> new HammerItem(ModItemTiers.TITANIUM, 4, 2.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	// OSMIUM TOOLS

	public static final RegistryObject<Item> OSMIUM_PICKAXE = ITEMS.register("osmium_pickaxe",
			() -> new PickaxeItem(ModItemTiers.OSMIUM, 4, 2.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OSMIUM_SWORD = ITEMS.register("osmium_sword",
			() -> new SwordItem(ModItemTiers.OSMIUM, 9, 1.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OSMIUM_SHOVEL = ITEMS.register("osmium_shovel",
			() -> new ShovelItem(ModItemTiers.OSMIUM, 3, 2.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OSMIUM_AXE = ITEMS.register("osmium_axe",
			() -> new AxeItem(ModItemTiers.OSMIUM, 11, 3.0f, new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OSMIUM_HOE = ITEMS.register("osmium_hoe",
			() -> new HoeItem(ModItemTiers.OSMIUM, 3, new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OSMIUM_PAXSWEL = ITEMS.register("osmium_paxswel",
			() -> new PaxswelItem(ModItemTiers.OSMIUM, 11, 1.5f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OSMIUM_HAMMER = ITEMS.register("osmium_hammer",
			() -> new HammerItem(ModItemTiers.OSMIUM, 4, 2.0f,
					new Item.Properties().group(MarsCraftItemGroup.instance)));

	// temporary
	public static final RegistryObject<Item> GAS_BUCKET = ITEMS.register("gas_bucket",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> HYDROGEN_BUCKET = ITEMS.register("hydrogen_bucket",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> OXYGEN_BUCKET = ITEMS.register("oxygen_bucket",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

	public static final RegistryObject<Item> CHLORINE_BUCKET = ITEMS.register("chlorine_bucket",
			() -> new Item(new Item.Properties().group(MarsCraftItemGroup.instance)));

}
