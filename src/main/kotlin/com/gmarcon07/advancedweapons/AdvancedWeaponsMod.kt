package com.gmarcon07.advancedweapons

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object AdvancedWeaponsMod : ModInitializer {
	private val LOGGER = LoggerFactory.getLogger("advanced-weapons-mod")

	override fun onInitialize() {
		LOGGER.info("Advanced Weapons Mod inicializado!")
		
		// Registrar itens
		WeaponItems.register()
		
		// Registrar sons
		WeaponSounds.register()
		
		// Registrar efeitos
		WeaponEffects.register()
	}
}
