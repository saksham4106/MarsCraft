package com.marscraft.marscraftmod.events;

import java.util.function.Function;

import com.marscraft.marscraftmod.MarsCraftMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = MarsCraftMod.MOD_ID, bus = Bus.FORGE)
public class TeleportPlayerToMars {

	@SubscribeEvent
	public static void playerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().getTags().contains("GoalCompleted")) {
			if (event.getPlayer().dimension == DimensionType.OVERWORLD) {
				BlockPos pos = event.getPlayer().getPosition();
				event.getPlayer().changeDimension(DimensionType.byName(MarsCraftMod.MARS_DIM_TYPE), new ITeleporter() {
					@Override
					public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw,
							Function<Boolean, Entity> repositionEntity) {
						PlayerEntity playerIn = (PlayerEntity) entity;
						playerIn = (PlayerEntity) repositionEntity.apply(false);
						playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());

						playerIn.setSpawnPoint(pos, true, true, DimensionType.byName(MarsCraftMod.MARS_DIM_TYPE));
						return playerIn;
					}
				});
			}

		}
	}

}
