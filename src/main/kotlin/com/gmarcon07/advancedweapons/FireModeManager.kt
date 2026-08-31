package com.gmarcon07.advancedweapons

import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World

object FireModeManager {
	private val MOD_ID = "advanced-weapons-mod"

	fun register() {
		// Adicionar keybind para mudar fire mode (número 6 por padrão)
		// Handler de mudança de fire mode via keybind ou comando
	}

	fun cycleFireMode(player: PlayerEntity, stack: ItemStack) {
		val nbt = stack.orCreateNbt
		val currentMode = try {
			FirearmItems.FireMode.valueOf(nbt.getString("FireMode").ifEmpty { "SEMI" })
		} catch (e: Exception) {
			FirearmItems.FireMode.SEMI
		}

		val nextMode = when (currentMode) {
			FirearmItems.FireMode.SEMI -> FirearmItems.FireMode.BURST
			FirearmItems.FireMode.BURST -> FirearmItems.FireMode.FULL_AUTO
			FirearmItems.FireMode.FULL_AUTO -> FirearmItems.FireMode.SEMI
		}

		nbt.putString("FireMode", nextMode.name)
		stack.nbt = nbt

		if (!player.world.isClient) {
			player.sendMessage(
				net.minecraft.text.Text.literal("§bFire Mode: §f${nextMode.name}§r"),
				true
			)
		}
	}

	fun getFireMode(stack: ItemStack): FirearmItems.FireMode {
		val nbt = stack.orCreateNbt
		return try {
			FirearmItems.FireMode.valueOf(nbt.getString("FireMode").ifEmpty { "SEMI" })
		} catch (e: Exception) {
			FirearmItems.FireMode.SEMI
		}
	}

	fun canShoot(stack: ItemStack, fireMode: FirearmItems.FireMode): Boolean {
		val nbt = stack.orCreateNbt
		val ammo = nbt.getInt("Ammo")
		return ammo > 0
	}

	fun consumeAmmo(stack: ItemStack, amount: Int = 1) {
		val nbt = stack.orCreateNbt
		val currentAmmo = nbt.getInt("Ammo")
		nbt.putInt("Ammo", (currentAmmo - amount).coerceAtLeast(0))
		stack.nbt = nbt
	}

	fun addAmmo(stack: ItemStack, amount: Int, maxCapacity: Int) {
		val nbt = stack.orCreateNbt
		val currentAmmo = nbt.getInt("Ammo")
		nbt.putInt("Ammo", (currentAmmo + amount).coerceAtMost(maxCapacity))
		stack.nbt = nbt
	}

	fun reload(stack: ItemStack, ammoStack: ItemStack, maxCapacity: Int): Boolean {
		val nbt = stack.orCreateNbt
		val currentAmmo = nbt.getInt("Ammo")

		if (currentAmmo >= maxCapacity || ammoStack.isEmpty) {
			return false
		}

		val ammoNeeded = maxCapacity - currentAmmo
		val ammoAvailable = ammoStack.count

		if (ammoAvailable == 0) return false

		val ammoToTransfer = minOf(ammoNeeded, ammoAvailable)

		addAmmo(stack, ammoToTransfer, maxCapacity)
		ammoStack.decrement(ammoToTransfer)

		return true
	}
}
