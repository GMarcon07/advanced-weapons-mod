package com.gmarcon07.advancedweapons

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object FirearmSounds {
	private val MOD_ID = "advanced-weapons-mod"

	// Sons de disparo M4A1
	val M4A1_FIRE = SoundEvent.of(Identifier.of(MOD_ID, "firearm.m4a1.fire"))
	val M4A1_RELOAD = SoundEvent.of(Identifier.of(MOD_ID, "firearm.m4a1.reload"))
	val M4A1_EMPTY = SoundEvent.of(Identifier.of(MOD_ID, "firearm.m4a1.empty"))

	// Sons de disparo Pistola 9mm
	val PISTOL_FIRE = SoundEvent.of(Identifier.of(MOD_ID, "firearm.pistol.fire"))
	val PISTOL_RELOAD = SoundEvent.of(Identifier.of(MOD_ID, "firearm.pistol.reload"))
	val PISTOL_EMPTY = SoundEvent.of(Identifier.of(MOD_ID, "firearm.pistol.empty"))

	// Sons de disparo Shotgun
	val SHOTGUN_FIRE = SoundEvent.of(Identifier.of(MOD_ID, "firearm.shotgun.fire"))
	val SHOTGUN_RELOAD = SoundEvent.of(Identifier.of(MOD_ID, "firearm.shotgun.reload"))
	val SHOTGUN_PUMP = SoundEvent.of(Identifier.of(MOD_ID, "firearm.shotgun.pump"))

	// Sons de disparo AWM Sniper
	val SNIPER_FIRE = SoundEvent.of(Identifier.of(MOD_ID, "firearm.sniper.fire"))
	val SNIPER_RELOAD = SoundEvent.of(Identifier.of(MOD_ID, "firearm.sniper.reload"))
	val SNIPER_BOLT = SoundEvent.of(Identifier.of(MOD_ID, "firearm.sniper.bolt"))

	// Sons de disparo MP5
	val MP5_FIRE = SoundEvent.of(Identifier.of(MOD_ID, "firearm.mp5.fire"))
	val MP5_RELOAD = SoundEvent.of(Identifier.of(MOD_ID, "firearm.mp5.reload"))
	val MP5_EMPTY = SoundEvent.of(Identifier.of(MOD_ID, "firearm.mp5.empty"))

	// Sons de impacto comuns
	val BULLET_HIT_FLESH = SoundEvent.of(Identifier.of(MOD_ID, "firearm.impact.flesh"))
	val BULLET_HIT_BLOCK = SoundEvent.of(Identifier.of(MOD_ID, "firearm.impact.block"))
	val BULLET_RICOCHET = SoundEvent.of(Identifier.of(MOD_ID, "firearm.impact.ricochet"))

	fun register() {
		// M4A1
		registerSound("firearm.m4a1.fire", M4A1_FIRE)
		registerSound("firearm.m4a1.reload", M4A1_RELOAD)
		registerSound("firearm.m4a1.empty", M4A1_EMPTY)

		// Pistola
		registerSound("firearm.pistol.fire", PISTOL_FIRE)
		registerSound("firearm.pistol.reload", PISTOL_RELOAD)
		registerSound("firearm.pistol.empty", PISTOL_EMPTY)

		// Shotgun
		registerSound("firearm.shotgun.fire", SHOTGUN_FIRE)
		registerSound("firearm.shotgun.reload", SHOTGUN_RELOAD)
		registerSound("firearm.shotgun.pump", SHOTGUN_PUMP)

		// Sniper
		registerSound("firearm.sniper.fire", SNIPER_FIRE)
		registerSound("firearm.sniper.reload", SNIPER_RELOAD)
		registerSound("firearm.sniper.bolt", SNIPER_BOLT)

		// MP5
		registerSound("firearm.mp5.fire", MP5_FIRE)
		registerSound("firearm.mp5.reload", MP5_RELOAD)
		registerSound("firearm.mp5.empty", MP5_EMPTY)

		// Impacto
		registerSound("firearm.impact.flesh", BULLET_HIT_FLESH)
		registerSound("firearm.impact.block", BULLET_HIT_BLOCK)
		registerSound("firearm.impact.ricochet", BULLET_RICOCHET)
	}

	private fun registerSound(id: String, soundEvent: SoundEvent) {
		Registry.register(
			Registries.SOUND_EVENT,
			Identifier.of(MOD_ID, id),
			soundEvent
		)
	}
}
