package com.gmarcon07.advancedweapons

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.RangedWeaponItem
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.UseAction
import net.minecraft.nbt.NbtCompound

object FirearmItems {
	private val MOD_ID = "advanced-weapons-mod"

	// Arma 1: Rifle de Assalto M4A1
	val M4A1_RIFLE = CustomFirearmItem(
		"M4A1 Rifle de Assalto",
		"Um rifle de assalto confiável e versátil",
		0.8f, // precisão
		3, // cadência de tiro (tiros por segundo)
		30, // capacidade de munição
		8.0f, // dano por tiro
		FirearmType.RIFLE
	)

	// Arma 2: Pistola Tática 9mm
	val TACTICAL_PISTOL = CustomFirearmItem(
		"Pistola 9mm",
		"Uma pistola compacta e rápida",
		0.6f,
		4,
		15,
		6.0f,
		FirearmType.PISTOL
	)

	// Arma 3: Shotgun Combat
	val COMBAT_SHOTGUN = CustomFirearmItem(
		"Shotgun Combat",
		"Uma shotgun poderosa para combate próximo",
		0.5f,
		1,
		8,
		12.0f,
		FirearmType.SHOTGUN
	)

	// Arma 4: Rifle de Precisão AWM
	val AWM_SNIPER = CustomFirearmItem(
		"Rifle AWM",
		"Um rifle de precisão com dano devastador",
		1.0f,
		0,
		5,
		15.0f,
		FirearmType.SNIPER
	)

	// Arma 5: Subfusil MP5
	val MP5_SUBMACHINE = CustomFirearmItem(
		"Subfusil MP5",
		"Um submachine gun rápido e letal",
		0.7f,
		6,
		25,
		5.0f,
		FirearmType.SUBMACHINE_GUN
	)

	// Munição
	val AMMO_9MM = Item(Item.Settings())
	val AMMO_5_56 = Item(Item.Settings())
	val AMMO_308 = Item(Item.Settings())
	val AMMO_SHOTGUN = Item(Item.Settings())

	// Attachments
	val RED_DOT_SIGHT = Item(Item.Settings())
	val ACOG_SCOPE = Item(Item.Settings())
	val SUPPRESSOR = Item(Item.Settings())
	val MUZZLE_BRAKE = Item(Item.Settings())
	val TACTICAL_GRIP = Item(Item.Settings())

	fun register() {
		registerFirearm("m4a1_rifle", M4A1_RIFLE)
		registerFirearm("tactical_pistol", TACTICAL_PISTOL)
		registerFirearm("combat_shotgun", COMBAT_SHOTGUN)
		registerFirearm("awm_sniper", AWM_SNIPER)
		registerFirearm("mp5_submachine", MP5_SUBMACHINE)

		// Registrar munição
		registerItem("ammo_9mm", AMMO_9MM)
		registerItem("ammo_5_56", AMMO_5_56)
		registerItem("ammo_308", AMMO_308)
		registerItem("ammo_shotgun", AMMO_SHOTGUN)

		// Registrar attachments
		registerItem("red_dot_sight", RED_DOT_SIGHT)
		registerItem("acog_scope", ACOG_SCOPE)
		registerItem("suppressor", SUPPRESSOR)
		registerItem("muzzle_brake", MUZZLE_BRAKE)
		registerItem("tactical_grip", TACTICAL_GRIP)

		// Adicionar ao grupo criativo
		ItemGroupEvents.modifyContents(ItemGroups.COMBAT) { content ->
			content.add(M4A1_RIFLE)
			content.add(TACTICAL_PISTOL)
			content.add(COMBAT_SHOTGUN)
			content.add(AWM_SNIPER)
			content.add(MP5_SUBMACHINE)
			content.add(AMMO_9MM)
			content.add(AMMO_5_56)
			content.add(AMMO_308)
			content.add(AMMO_SHOTGUN)
			content.add(RED_DOT_SIGHT)
			content.add(ACOG_SCOPE)
			content.add(SUPPRESSOR)
			content.add(MUZZLE_BRAKE)
			content.add(TACTICAL_GRIP)
		}
	}

	private fun registerFirearm(name: String, item: Item) {
		Registry.register(
			Registries.ITEM,
			Identifier.of(MOD_ID, name),
			item
		)
	}

	private fun registerItem(name: String, item: Item) {
		Registry.register(
			Registries.ITEM,
			Identifier.of(MOD_ID, name),
			item
		)
	}

	enum class FirearmType {
		RIFLE, PISTOL, SHOTGUN, SNIPER, SUBMACHINE_GUN
	}

	enum class FireMode {
		SEMI, BURST, FULL_AUTO
	}

	private class CustomFirearmItem(
		private val displayName: String,
		private val description: String,
		private val accuracy: Float,
		private val fireRate: Int,
		private val ammoCapacity: Int,
		private val damagePerShot: Float,
		private val type: FirearmType
	) : RangedWeaponItem(Item.Settings().maxCount(1)) {

		override fun getName(stack: ItemStack) =
			Text.literal(displayName)

		override fun appendTooltip(
			stack: ItemStack,
			context: TooltipContext,
			tooltip: MutableList<Text>,
			type: TooltipType
		) {
			val nbt = stack.nbt ?: NbtCompound()
			val ammo = nbt.getInt("Ammo")
			val fireMode = nbt.getString("FireMode").ifEmpty { "SEMI" }
			val hasScope = nbt.getBoolean("HasScope")
			val hasSuppressor = nbt.getBoolean("HasSuppressor")

			tooltip.add(Text.literal("§7$description"))
			tooltip.add(Text.literal("§6Dano: ${damagePerShot}§r"))
			tooltip.add(Text.literal("§6Precisão: ${(accuracy * 100).toInt()}%§r"))
			tooltip.add(Text.literal("§cCadência: $fireRate tiros/s§r"))
			tooltip.add(Text.literal("§aCapacidade: $ammo / $ammoCapacity§r"))
			tooltip.add(Text.literal("§bModo: §f$fireMode§r"))
			
			if (hasScope) tooltip.add(Text.literal("§3🔍 Mira Instalada§r"))
			if (hasSuppressor) tooltip.add(Text.literal("§8🔇 Silenciador Instalado§r"))
		}

		override fun getUseAction(stack: ItemStack) = UseAction.BOW
		override fun getMaxUseTime(stack: ItemStack) = 72000

		fun setAmmo(stack: ItemStack, ammo: Int) {
			val nbt = stack.orCreateNbt
			nbt.putInt("Ammo", ammo.coerceIn(0, ammoCapacity))
		}

		fun getAmmo(stack: ItemStack): Int {
			return stack.orCreateNbt.getInt("Ammo")
		}

		fun setFireMode(stack: ItemStack, mode: FireMode) {
			val nbt = stack.orCreateNbt
			nbt.putString("FireMode", mode.name)
		}

		fun getFireMode(stack: ItemStack): FireMode {
			val mode = stack.orCreateNbt.getString("FireMode")
			return try {
				FireMode.valueOf(mode)
			} catch (e: Exception) {
				FireMode.SEMI
			}
		}

		fun addAttachment(stack: ItemStack, attachment: String) {
			val nbt = stack.orCreateNbt
			when (attachment) {
				"scope" -> nbt.putBoolean("HasScope", true)
				"suppressor" -> nbt.putBoolean("HasSuppressor", true)
				"grip" -> nbt.putBoolean("HasGrip", true)
				"muzzle" -> nbt.putBoolean("HasMuzzle", true)
			}
		}

		fun removeAttachment(stack: ItemStack, attachment: String) {
			val nbt = stack.orCreateNbt
			when (attachment) {
				"scope" -> nbt.putBoolean("HasScope", false)
				"suppressor" -> nbt.putBoolean("HasSuppressor", false)
				"grip" -> nbt.putBoolean("HasGrip", false)
				"muzzle" -> nbt.putBoolean("HasMuzzle", false)
			}
		}
	}
}
