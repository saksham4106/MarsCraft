package com.marscraft.marscraftmod.init;

import com.marscraft.marscraftmod.MarsCraftMod;
import com.marscraft.marscraftmod.world.biomes.MarsBiome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {

	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
			MarsCraftMod.MOD_ID);

	public static final RegistryObject<Biome> MARS = BIOMES.register("mars",
			() -> new MarsBiome(new Biome.Builder().precipitation(RainType.NONE)
					.surfaceBuilder(SurfaceBuilder.DEFAULT,
							new SurfaceBuilderConfig(BlockInit.MARTIAN_SOIL.get().getDefaultState(),
									BlockInit.MARTIAN_ROCK.get().getDefaultState(),
									BlockInit.MARTIAN_ROCK.get().getDefaultState()))
					.scale(0.05F).depth(0.125F).temperature(0.5F).waterColor(4159204).waterFogColor(329011)
					.category(Category.NONE).downfall(0.0f).parent(null)));

	public static void registerBiomes() {
		registerBiome(MARS.get(), Type.DRY);
	}

	private static void registerBiome(Biome biome, Type... types) {
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}

}
