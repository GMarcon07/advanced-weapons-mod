package com.gmarcon07.advancedweapons

import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.world.World

object WeaponEffects {
	
	fun register() {
		AttackEntityCallback.EVENT.register { player, world, hand, entity, hitResult ->
			if (!world.isClient && entity is LivingEntity && player is PlayerEntity) {
				handleWeaponEffects(player, entity, world)
			}
			ActionResult.PASS
		}
	}

	private fun handleWeaponEffects(player: PlayerEntity, target: LivingEntity, world: World) {
		val mainStack = player.mainHandStack
		val itemId = mainStack.registryEntry.id.toString()

		when {
			itemId.contains("plasma_sword") -> {
				// Dano adicional + efeito de brilho
				target.addStatusEffect(StatusEffectInstance(StatusEffects.GLOWING, 60, 0))
			}
			itemId.contains("frost_blade") -> {
				// Congela o inimigo por um tempo
				target.addStatusEffect(StatusEffectInstance(StatusEffects.SLOWNESS, 80, 1))
				target.addStatusEffect(StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0))
			}
			itemId.contains("scimitar") -> {
				// Velocidade aumentada ao atacar (para o jogador)
				player.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED, 60, 0))
			}
			itemId.contains("war_axe") -> {
				// Knockback aumentado via partículas (efeito visual)
				target.knockback(1.5, player.x - target.x, player.z - target.z)
			}
			itemId.contains("demon_edge") -> {
				// Efeito de magia negra - ambos têm fraqueza
				target.addStatusEffect(StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1))
				player.addStatusEffect(StatusEffectInstance(StatusEffects.STRENGTH, 40, 0))
			}
		}
	}
}
