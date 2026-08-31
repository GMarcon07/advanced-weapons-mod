package com.gmarcon07.advancedweapons

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object AdvancedWeaponsMod : ModInitializer {
	private val LOGGER = LoggerFactory.getLogger("advanced-weapons-mod")

	override fun onInitialize() {
		LOGGER.info("Advanced Firearms Mod inicializado!")
		
		// Registrar itens de fogo
		FirearmItems.register()
		
		// Registrar sons
		FirearmSounds.register()
		
		// Registrar efeitos
		FirearmEffects.register()
	}
}
