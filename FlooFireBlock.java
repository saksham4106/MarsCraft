package com.saksham.pottercraftmod.objects.blocks;

import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.saksham.pottercraftmod.particles.WandSpellParticle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.SixWayBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FlooFireBlock extends Block{

	 public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
	 public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	 public static final BooleanProperty EAST = SixWayBlock.EAST;
	 public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	 public static final BooleanProperty WEST = SixWayBlock.WEST;
	 public static final BooleanProperty UP = SixWayBlock.UP;
	 private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().stream().filter((p_199776_0_) -> {
	    return p_199776_0_.getKey() != Direction.DOWN;
	     }).collect(Util.toMapCollector());
 
  public FlooFireBlock(Block.Properties properties) {
	  super(properties);
		this.setDefaultState(
				this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)).with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)));
  }

  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return VoxelShapes.empty();
   }
  
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      return this.isValidPosition(stateIn, worldIn, currentPos) ? this.getStateForPlacement(worldIn, currentPos).with(AGE, stateIn.get(AGE)) : Blocks.AIR.getDefaultState();
   }

   @Nullable
   public BlockState getStateForPlacement(BlockItemUseContext context) {
      return this.getStateForPlacement(context.getWorld(), context.getPos());
   }

   public BlockState getStateForPlacement(IBlockReader blockReader, BlockPos pos) {
      BlockPos blockpos = pos.down();
      BlockState blockstate = blockReader.getBlockState(blockpos);
      if (!this.canCatchFire(blockReader, pos, Direction.UP) && !Block.hasSolidSide(blockstate, blockReader, blockpos, Direction.UP)) {
         BlockState blockstate1 = this.getDefaultState();

         for(Direction direction : Direction.values()) {
            BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(direction);
            if (booleanproperty != null) {
               blockstate1 = blockstate1.with(booleanproperty, Boolean.valueOf(this.canCatchFire(blockReader, pos.offset(direction), direction.getOpposite())));
            }
         }

         return blockstate1;
      } else {
         return this.getDefaultState();
      }
   }

   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.down();
      return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) || this.areNeighborsFlammable(worldIn, pos);
   }

   /**
    * How many world ticks before ticking
    */
   public int tickRate(IWorldReader worldIn) {
      return 30;
   }

   public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
      if (worldIn.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
         if (!worldIn.isAreaLoaded(pos, 2)) return; // Forge: prevent loading unloaded chunks when spreading fire
         if (!state.isValidPosition(worldIn, pos)) {
            worldIn.removeBlock(pos, false);
         }

         
         BlockState other = worldIn.getBlockState(pos.down());
         boolean flag = other.isFireSource(worldIn, pos.down(), Direction.UP);
         int i = state.get(AGE);
         if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && rand.nextFloat() < 0.2F + (float)i * 0.03F) {
            worldIn.removeBlock(pos, false);
         } else {
            int j = Math.min(15, i + rand.nextInt(3) / 2);
            if (i != j) {
               state = state.with(AGE, Integer.valueOf(j));
               worldIn.setBlockState(pos, state, 4);
            }

            if (!flag) {
               worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + rand.nextInt(10));
               if (!this.areNeighborsFlammable(worldIn, pos)) {
                  BlockPos blockpos = pos.down();
                  if (!worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) || i > 3) {
                     worldIn.removeBlock(pos, false);
                  }

                  return;
               }

               if (i == 15 && rand.nextInt(4) == 0 && !this.canCatchFire(worldIn, pos.down(), Direction.UP)) {
                  worldIn.removeBlock(pos, false);
                  return;
               }
            }

            boolean flag1 = worldIn.isBlockinHighHumidity(pos);
            int k = flag1 ? -50 : 0;
            this.tryCatchFire(worldIn, pos.east(), 300 + k, rand, i, Direction.WEST);
            this.tryCatchFire(worldIn, pos.west(), 300 + k, rand, i, Direction.EAST);
            this.tryCatchFire(worldIn, pos.down(), 250 + k, rand, i, Direction.UP);
            this.tryCatchFire(worldIn, pos.up(), 250 + k, rand, i, Direction.DOWN);
            this.tryCatchFire(worldIn, pos.north(), 300 + k, rand, i, Direction.SOUTH);
            this.tryCatchFire(worldIn, pos.south(), 300 + k, rand, i, Direction.NORTH);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(int l = -1; l <= 1; ++l) {
               for(int i1 = -1; i1 <= 1; ++i1) {
                  for(int j1 = -1; j1 <= 4; ++j1) {
                     if (l != 0 || j1 != 0 || i1 != 0) {
                        int k1 = 100;
                        if (j1 > 1) {
                           k1 += (j1 - 1) * 100;
                        }

                        blockpos$mutable.setPos(pos).move(l, j1, i1);
                        int l1 = this.getNeighborEncouragement(worldIn, blockpos$mutable);
                        if (l1 > 0) {
                           int i2 = (l1 + 40 + worldIn.getDifficulty().getId() * 7) / (i + 30);
                           if (flag1) {
                              i2 /= 2;
                           }

                           if (i2 > 0 && rand.nextInt(k1) <= i2 && (!worldIn.isRaining() || !this.canDie(worldIn, blockpos$mutable))) {
                              int j2 = Math.min(15, i + rand.nextInt(5) / 4);
                              worldIn.setBlockState(blockpos$mutable, this.getStateForPlacement(worldIn, blockpos$mutable).with(AGE, Integer.valueOf(j2)), 3);
                           }
                        }
                     }
                  }
               }
            }

         }
      }
   }

   protected boolean canDie(World worldIn, BlockPos pos) {
      return worldIn.isRainingAt(pos) || worldIn.isRainingAt(pos.west()) || worldIn.isRainingAt(pos.east()) || worldIn.isRainingAt(pos.north()) || worldIn.isRainingAt(pos.south());
   }


   private void tryCatchFire(World worldIn, BlockPos pos, int chance, Random random, int age, Direction face) {
      int i = worldIn.getBlockState(pos).getFlammability(worldIn, pos, face);
      if (random.nextInt(chance) < i) {
         BlockState blockstate = worldIn.getBlockState(pos);
         if (random.nextInt(age + 10) < 5 && !worldIn.isRainingAt(pos)) {
            int j = Math.min(age + random.nextInt(5) / 4, 15);
            worldIn.setBlockState(pos, this.getStateForPlacement(worldIn, pos).with(AGE, Integer.valueOf(j)), 3);
         } else {
            worldIn.removeBlock(pos, false);
         }

         blockstate.catchFire(worldIn, pos, face, null);
      }

   }

   private boolean areNeighborsFlammable(IBlockReader worldIn, BlockPos pos) {
      for(Direction direction : Direction.values()) {
         if (this.canCatchFire(worldIn, pos.offset(direction), direction.getOpposite())) {
            return true;
         }
      }

      return false;
   }

   private int getNeighborEncouragement(IWorldReader worldIn, BlockPos pos) {
      if (!worldIn.isAirBlock(pos)) {
         return 0;
      } else {
         int i = 0;

         for(Direction direction : Direction.values()) {
            BlockState blockstate = worldIn.getBlockState(pos.offset(direction));
            i = Math.max(blockstate.getFireSpreadSpeed(worldIn, pos.offset(direction), direction.getOpposite()), i);
         }

         return i;
      }
   }


   public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
      if (oldState.getBlock() != state.getBlock()) {
         if (worldIn.dimension.getType() != DimensionType.OVERWORLD && worldIn.dimension.getType() != DimensionType.THE_NETHER || !((NetherPortalBlock)Blocks.NETHER_PORTAL).trySpawnPortal(worldIn, pos)) {
            if (!state.isValidPosition(worldIn, pos)) {
               worldIn.removeBlock(pos, false);
            } else {
               worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
            }
         }
      }
   }

   /**
    * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
    * this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
    * of whether the block can receive random update ticks
    */
   @OnlyIn(Dist.CLIENT)
   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
      if (rand.nextInt(24) == 0) {
         worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
      }

      BlockPos blockpos = pos.down();
      BlockState blockstate = worldIn.getBlockState(blockpos);
      if (!this.canCatchFire(worldIn, blockpos, Direction.UP) && !Block.hasSolidSide(blockstate, worldIn, blockpos, Direction.UP)) {
         if (this.canCatchFire(worldIn, blockpos.west(), Direction.EAST)) {
            for(int j = 0; j < 2; ++j) {
               double d3 = (double)pos.getX() + rand.nextDouble() * (double)0.1F;
               double d8 = (double)pos.getY() + rand.nextDouble();
               double d13 = (double)pos.getZ() + rand.nextDouble();
               worldIn.addParticle(new WandSpellParticle.WandSpellParticleData(0.1f, 0.1f, 0.1f, 0.1f), d3, d8, d13, 0.0D, 0.0D, 0.0D);
            }
         }

         if (this.canCatchFire(worldIn, pos.east(), Direction.WEST)) {
            for(int k = 0; k < 2; ++k) {
               double d4 = (double)(pos.getX() + 1) - rand.nextDouble() * (double)0.1F;
               double d9 = (double)pos.getY() + rand.nextDouble();
               double d14 = (double)pos.getZ() + rand.nextDouble();
               worldIn.addParticle(new WandSpellParticle.WandSpellParticleData(0.1f, 0.1f, 0.1f, 0.1f), d4, d9, d14, 0.0D, 0.0D, 0.0D);
            }
         }

         if (this.canCatchFire(worldIn, pos.north(), Direction.SOUTH)) {
            for(int l = 0; l < 2; ++l) {
               double d5 = (double)pos.getX() + rand.nextDouble();
               double d10 = (double)pos.getY() + rand.nextDouble();
               double d15 = (double)pos.getZ() + rand.nextDouble() * (double)0.1F;
               worldIn.addParticle(new WandSpellParticle.WandSpellParticleData(0.1f, 0.1f, 0.1f, 0.1f), d5, d10, d15, 0.0D, 0.0D, 0.0D);
            }
         }

         if (this.canCatchFire(worldIn, pos.south(), Direction.NORTH)) {
            for(int i1 = 0; i1 < 2; ++i1) {
               double d6 = (double)pos.getX() + rand.nextDouble();
               double d11 = (double)pos.getY() + rand.nextDouble();
               double d16 = (double)(pos.getZ() + 1) - rand.nextDouble() * (double)0.1F;
               worldIn.addParticle(new WandSpellParticle.WandSpellParticleData(0.1f, 0.1f, 0.1f, 0.1f), d6, d11, d16, 0.0D, 0.0D, 0.0D);
            }
         }

         if (this.canCatchFire(worldIn, pos.up(), Direction.DOWN)) {
            for(int j1 = 0; j1 < 2; ++j1) {
               double d7 = (double)pos.getX() + rand.nextDouble();
               double d12 = (double)(pos.getY() + 1) - rand.nextDouble() * (double)0.1F;
               double d17 = (double)pos.getZ() + rand.nextDouble();
               worldIn.addParticle(new WandSpellParticle.WandSpellParticleData(0.1f, 0.1f, 0.1f, 0.1f), d7, d12, d17, 0.0D, 0.0D, 0.0D);
            }
         }
      } else {
         for(int i = 0; i < 3; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            worldIn.addParticle(new WandSpellParticle.WandSpellParticleData(0.1f, 0.1f, 0.1f, 0.1f), d0, d1, d2, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(AGE, NORTH, EAST, SOUTH, WEST, UP);
   }


   public boolean canCatchFire(IBlockReader world, BlockPos pos, Direction face) {
      return world.getBlockState(pos).isFlammable(world, pos, face);
   }

	

}
