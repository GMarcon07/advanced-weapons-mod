package com.gmarcon07.advancedweapons

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.RangedWeaponItem
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.UseAction

object FirearmItems {
	private val MOD_ID = "advanced-weapons-mod"

	// Arma 1: Rifle de Assalto M4A1
	val M4A1_RIFLE = CustomFirearmItem(
		"M4A1 Rifle de Assalto",
		"Um rifle de assalto confiável e versátil",
		0.8f, // precisão
		3 // cadência de tiro (tiros por segundo)
	)

	// Arma 2: Pistola Tática 9mm
	val TACTICAL_PISTOL = CustomFirearmItem(
		"Pistola 9mm",
		"Uma pistola compacta e rápida",
		0.6f,
		4
	)

	// Arma 3: Shotgun Combat
	val COMBAT_SHOTGUN = CustomFirearmItem(
		"Shotgun Combat",
		"Uma shotgun poderosa para combate próximo",
		0.5f,
		1
	)

	// Arma 4: Rifle de Precisão AWM
	val AWM_SNIPER = CustomFirearmItem(
		"Rifle AWM",
		"Um rifle de precisão com dano devastador",
		1.0f,
		0
	)

	// Arma 5: Subfusil MP5
	val MP5_SUBMACHINE = CustomFirearmItem(
		"Subfusil MP5",
		"Um submachine gun rápido e letal",
		0.7f,
		6
	)

	fun register() {
		registerFirearm("m4a1_rifle", M4A1_RIFLE)
		registerFirearm("tactical_pistol", TACTICAL_PISTOL)
		registerFirearm("combat_shotgun", COMBAT_SHOTGUN)
		registerFirearm("awm_sniper", AWM_SNIPER)
		registerFirearm("mp5_submachine", MP5_SUBMACHINE)

		// Adicionar ao grupo criativo
		ItemGroupEvents.modifyContents(ItemGroups.COMBAT) { content ->
			content.add(M4A1_RIFLE)
			content.add(TACTICAL_PISTOL)
			content.add(COMBAT_SHOTGUN)
			content.add(AWM_SNIPER)
			content.add(MP5_SUBMACHINE)
		}
	}

	private fun registerFirearm(name: String, item: Item) {
		Registry.register(
			Registries.ITEM,
			Identifier.of(MOD_ID, name),
			item
		)
	}

	private class CustomFirearmItem(
		private val displayName: String,
		private val description: String,
		private val accuracy: Float,
		private val fireRate: Int
	) : RangedWeaponItem(Item.Settings().maxCount(1)) {

		override fun getName(stack: net.minecraft.item.ItemStack) =
			Text.literal(displayName)

		override fun appendTooltip(
			stack: net.minecraft.item.ItemStack,
			context: net.minecraft.item.Item.TooltipContext,
			tooltip: MutableList<Text>,
			type: TooltipType
		) {
			tooltip.add(Text.literal("§7$description"))
			tooltip.add(Text.literal("§6Precisão: ${(accuracy * 100).toInt()}%§r"))
			tooltip.add(Text.literal("§c Cadência: $fireRate tiros/s§r"))
		}

		override fun getUseAction(stack: net.minecraft.item.ItemStack) = UseAction.BOW
		override fun getMaxUseTime(stack: net.minecraft.item.ItemStack) = 72000
	}
}
