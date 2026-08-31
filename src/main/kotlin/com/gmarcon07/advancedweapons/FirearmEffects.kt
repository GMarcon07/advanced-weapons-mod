package com.gmarcon07.advancedweapons

import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.world.World

object FirearmEffects {
	
	fun register() {
		AttackEntityCallback.EVENT.register { player, world, hand, entity, hitResult ->
			if (!world.isClient && entity is LivingEntity && player is PlayerEntity) {
				handleFirearmEffects(player, entity, world)
			}
			ActionResult.PASS
		}
	}

	private fun handleFirearmEffects(player: PlayerEntity, target: LivingEntity, world: World) {
		val mainStack = player.mainHandStack
		val itemId = mainStack.registryEntry.id.toString()

		when {
			itemId.contains("m4a1_rifle") -> {
				// Dano médio com knockback
				target.damage(world.damageSources.playerAttack(player), 8.0f)
				target.knockback(0.5, player.x - target.x, player.z - target.z)
			}
			itemId.contains("tactical_pistol") -> {
				// Dano baixo mas rápido
				target.damage(world.damageSources.playerAttack(player), 6.0f)
				target.knockback(0.3, player.x - target.x, player.z - target.z)
			}
			itemId.contains("combat_shotgun") -> {
				// Dano alto com knockback massivo
				target.damage(world.damageSources.playerAttack(player), 12.0f)
				target.knockback(1.5, player.x - target.x, player.z - target.z)
			}
			itemId.contains("awm_sniper") -> {
				// Dano muito alto (headshot killer)
				target.damage(world.damageSources.playerAttack(player), 15.0f)
				target.addStatusEffect(StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0))
			}
			itemId.contains("mp5_submachine") -> {
				// Dano baixo mas com efeito de lentidão
				target.damage(world.damageSources.playerAttack(player), 5.0f)
				target.knockback(0.2, player.x - target.x, player.z - target.z)
				target.addStatusEffect(StatusEffectInstance(StatusEffects.SLOWNESS, 40, 0))
			}
		}
	}
}
