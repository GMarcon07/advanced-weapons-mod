package com.gmarcon07.advancedweapons.mixin.client

import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.Entity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(ClientPlayerEntity::class)
abstract class ClientPlayerAttackMixin {
	
	@Inject(method = "attack", at = At("HEAD"))
	private fun onPlayerAttack(target: Entity, ci: CallbackInfo) {
		val player = this as ClientPlayerEntity
		val mainStack = player.mainHandStack
		
		if (mainStack.isEmpty) return
		
		val itemId = mainStack.registryEntry.id.toString()
		val world = player.world
		
		// Criar efeitos visuais ao atacar com armas especiais
		when {
			itemId.contains("plasma_sword") -> {
				repeat(5) {
					val x = target.x + (Math.random() - 0.5) * target.width
					val y = target.y + (Math.random() - 0.5) * target.height
					val z = target.z + (Math.random() - 0.5) * target.width
					
					world.addParticle(
						net.minecraft.particle.ParticleTypes.ELECTRIC_SPARK,
						x, y, z,
						(Math.random() - 0.5) * 0.5,
						(Math.random() - 0.5) * 0.5,
						(Math.random() - 0.5) * 0.5
					)
				}
			}
			itemId.contains("frost_blade") -> {
				repeat(5) {
					val x = target.x + (Math.random() - 0.5) * target.width
					val y = target.y + (Math.random() - 0.5) * target.height
					val z = target.z + (Math.random() - 0.5) * target.width
					
					world.addParticle(
						net.minecraft.particle.ParticleTypes.SNOWFLAKE,
						x, y, z,
						(Math.random() - 0.5) * 0.3,
						(Math.random() - 0.5) * 0.3,
						(Math.random() - 0.5) * 0.3
					)
				}
			}
			itemId.contains("demon_edge") -> {
				repeat(8) {
					val x = target.x + (Math.random() - 0.5) * target.width
					val y = target.y + (Math.random() - 0.5) * target.height
					val z = target.z + (Math.random() - 0.5) * target.width
					
					world.addParticle(
						net.minecraft.particle.ParticleTypes.SMOKE,
						x, y, z,
						(Math.random() - 0.5) * 0.4,
						(Math.random() - 0.5) * 0.4,
						(Math.random() - 0.5) * 0.4
					)
				}
			}
		}
	}
}
