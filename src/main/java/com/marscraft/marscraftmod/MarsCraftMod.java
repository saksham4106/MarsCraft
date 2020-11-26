package com.marscraft.marscraftmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.marscraft.marscraftmod.init.BiomeInit;
import com.marscraft.marscraftmod.init.BlockInit;
import com.marscraft.marscraftmod.init.DimensionInit;
import com.marscraft.marscraftmod.init.FluidInit;
import com.marscraft.marscraftmod.init.ItemInit;
import com.marscraft.marscraftmod.init.ModContainerTypes;
import com.marscraft.marscraftmod.init.ModTileEntityTypes;
import com.marscraft.marscraftmod.init.RecipeSerializerInit;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("marscraftmod")
@Mod.EventBusSubscriber(modid = MarsCraftMod.MOD_ID, bus = Bus.MOD)
public class MarsCraftMod
{

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "marscraftmod";
	public static MarsCraftMod instance;
	public static final ResourceLocation MARS_DIM_TYPE = new ResourceLocation(MOD_ID, "mars");

    public MarsCraftMod() {
		instance = this;
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
		
		
		ItemInit.ITEMS.register(modEventBus);
		RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
		FluidInit.FLUIDS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
		ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
		BiomeInit.BIOMES.register(modEventBus);
		DimensionInit.MOD_DIMENSIONS.register(modEventBus);
		
		
		instance = this;
		MinecraftForge.EVENT_BUS.register(this);

    }
    
    @SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		BlockInit.BLOCKS.getEntries().stream()
				.filter(block -> !(block.get() instanceof FlowingFluidBlock))
				.map(RegistryObject::get).forEach(block -> {
					final Item.Properties properties = new Item.Properties().group(MarsCraftItemGroup.instance);
					final BlockItem blockItem = new BlockItem(block, properties);
					blockItem.setRegistryName(block.getRegistryName());
					registry.register(blockItem);
				});
		LOGGER.debug("Registered BlockItems!");
	}
    
    @SubscribeEvent
	public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
		BiomeInit.registerBiomes();
	}

    private void setup(final FMLCommonSetupEvent event)
    {
       
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
       
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
     
    }
    
    
	public static class MarsCraftItemGroup extends ItemGroup {
		public static final MarsCraftItemGroup instance = new MarsCraftItemGroup(ItemGroup.GROUPS.length, "marscrafttab");

		private MarsCraftItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.MARTIAN_ROCK.get());
		}
	}

}
