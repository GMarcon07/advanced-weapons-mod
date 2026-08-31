package com.gmarcon07.advancedweapons.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import org.slf4j.LoggerFactory

class AdvancedWeaponsClientMod : ClientModInitializer {
	private val LOGGER = LoggerFactory.getLogger("advanced-weapons-mod-client")

	override fun onInitializeClient() {
		LOGGER.info("Advanced Weapons Mod Client inicializado!")
		
		// Registrar eventos de cliente
		ClientTickEvents.END_CLIENT_TICK.register { client ->
			handleAttackEffects(client)
		}
	}

	private fun handleAttackEffects(client: MinecraftClient) {
		val player = client.player ?: return
		val mainHandStack = player.mainHandStack
		
		if (mainHandStack.isEmpty) return
		
		val itemId = mainHandStack.registryEntry.id.toString()
		
		// Adicionar efeitos visuais baseado na arma
		when {
			itemId.contains("plasma_sword") -> handlePlasmaEffects(player)
			itemId.contains("frost_blade") -> handleFrostEffects(player)
			itemId.contains("demon_edge") -> handleDemonEffects(player)
		}
	}

	private fun handlePlasmaEffects(player: PlayerEntity) {
		val world = player.world
		if (world.isClient && player.age % 5 == 0) {
			val x = player.x + (Math.random() - 0.5) * 0.5
			val y = player.y + player.eyeHeight + (Math.random() - 0.5) * 0.5
			val z = player.z + (Math.random() - 0.5) * 0.5
			
			world.addParticle(
				net.minecraft.particle.ParticleTypes.ELECTRIC_SPARK,
				x, y, z,
				(Math.random() - 0.5) * 0.2,
				(Math.random() - 0.5) * 0.2,
				(Math.random() - 0.5) * 0.2
			)
		}
	}

	private fun handleFrostEffects(player: PlayerEntity) {
		val world = player.world
		if (world.isClient && player.age % 5 == 0) {
			val x = player.x + (Math.random() - 0.5) * 0.5
			val y = player.y + player.eyeHeight + (Math.random() - 0.5) * 0.5
			val z = player.z + (Math.random() - 0.5) * 0.5
			
			world.addParticle(
				net.minecraft.particle.ParticleTypes.SNOWFLAKE,
				x, y, z,
				(Math.random() - 0.5) * 0.1,
				(Math.random() - 0.5) * 0.1,
				(Math.random() - 0.5) * 0.1
			)
		}
	}

	private fun handleDemonEffects(player: PlayerEntity) {
		val world = player.world
		if (world.isClient && player.age % 5 == 0) {
			val x = player.x + (Math.random() - 0.5) * 0.5
			val y = player.y + player.eyeHeight + (Math.random() - 0.5) * 0.5
			val z = player.z + (Math.random() - 0.5) * 0.5
			
			world.addParticle(
				net.minecraft.particle.ParticleTypes.SMOKE,
				x, y, z,
				(Math.random() - 0.5) * 0.15,
				(Math.random() - 0.5) * 0.15,
				(Math.random() - 0.5) * 0.15
			)
		}
	}
}
