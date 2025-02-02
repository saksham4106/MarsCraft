package com.marscraft.marscraftmod.world.dimension;


import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

public class MarsDimension extends Dimension{

	public MarsDimension(World world, DimensionType type) {
		super(world, type, 0.0F);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return new MarsChunkGenerator(world, new MarsBiomeProvider(), new MarsGenSettings());
	}

	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
		return null;
	}

	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		 double d0 = MathHelper.frac((double)worldTime / 24000.0D - 0.25D);
	      double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
	      return (float)(d0 * 2.0D + d1) / 3.0F;
	}

	@Override
	public boolean isSurfaceWorld() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canRespawnHere() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getActualHeight() {
		// TODO Auto-generated method stub
		return 256;
	}
	
	@Override
	public boolean doesWaterVaporize() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
	      float f = MathHelper.cos(celestialAngle * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
	      f = MathHelper.clamp(f, 0.0F, 1.0F);
	      float f1 = 0.7529412F;
	      float f2 = 0.84705883F;
	      float f3 = 1.0F;
	      f1 = f1 * (f * 0.94F + 0.06F);
	      f2 = f2 * (f * 0.94F + 0.06F);
	      f3 = f3 * (f * 0.91F + 0.09F);
	      return new Vec3d((double)f1, (double)f2, (double)f3);
	}
	
	
}
