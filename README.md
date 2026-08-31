# Advanced Firearms Mod

Um mod Fabric para Minecraft 1.21.1 que adiciona 5 armas de fogo customizadas inspiradas em TACZ, com sons realistas, texturas detalhadas e efeitos especiais únicos para cada arma.

## 🔫 Armas Disponíveis

### 1. **M4A1 Rifle de Assalto** 🎯
- **Dano**: 8.0 por disparo
- **Cadência**: 3 tiros/segundo
- **Precisão**: 80%
- **Efeito**: Knockback médio (0.5)
- **Partículas**: Fumaça de pólvora dourada
- **Ideal para**: Combate medium-range

### 2. **Pistola 9mm** 🔱
- **Dano**: 6.0 por disparo
- **Cadência**: 4 tiros/segundo
- **Precisão**: 60%
- **Efeito**: Knockback leve (0.3)
- **Partículas**: Fumaça cinzenta
- **Ideal para**: Combate rápido close-range

### 3. **Shotgun Combat** 💥
- **Dano**: 12.0 por disparo
- **Cadência**: 1 tiro/segundo
- **Precisão**: 50%
- **Efeito**: Knockback massivo (1.5)
- **Partículas**: Fumaça vermelha
- **Ideal para**: Combate próximo devastador

### 4. **Rifle AWM (Sniper)** 🎯🔭
- **Dano**: 15.0 por disparo (headshot killer)
- **Cadência**: 0 tiros/segundo (bolt-action)
- **Precisão**: 100%
- **Efeito**: Cegueira (Blindness) ao inimigo
- **Partículas**: Fumaça azul realista
- **Ideal para**: Long-range precision kills

### 5. **Subfusil MP5** 🔥
- **Dano**: 5.0 por disparo
- **Cadência**: 6 tiros/segundo (mais rápida)
- **Precisão**: 70%
- **Efeito**: Slowness ao inimigo
- **Partículas**: Fumaça com centelhas
- **Ideal para**: Spray-and-pray close-quarters

## 📋 Requisitos

- Minecraft 1.21.1
- Fabric Loader 0.15.11+
- Fabric API
- Java 21+

## 🚀 Instalação

1. Certifique-se de ter o Fabric Loader instalado para Minecraft 1.21.1
2. Copie o arquivo `.jar` para a pasta `mods`
3. Reinicie o Minecraft
4. As armas aparecerão na aba de combate do criativo

## 🛠️ Compilação

```bash
git clone https://github.com/GMarcon07/advanced-weapons-mod.git
cd advanced-weapons-mod
git checkout firearms-weapons
./gradlew build
```

O arquivo `.jar` estará em `build/libs/`

## 📁 Estrutura do Projeto

```
src/main/kotlin/com/gmarcon07/advancedweapons/
├── AdvancedWeaponsMod.kt          # Classe principal
├── FirearmItems.kt                # Registro das 5 armas
├── FirearmSounds.kt               # Sistema de sons
├── FirearmEffects.kt              # Efeitos de impacto
├── AdvancedWeaponsClientMod.kt    # Cliente-side
└── mixin/
    └── client/
        └── ClientPlayerAttackMixin.kt

src/main/resources/
├── assets/advanced-weapons-mod/
│   ├── lang/
│   │   ├── en_us.json
│   │   └── pt_br.json
│   ├── textures/item/
│   │   ├── m4a1_rifle.png
│   │   ├── tactical_pistol.png
│   │   ├── combat_shotgun.png
│   │   ├── awm_sniper.png
│   │   └── mp5_submachine.png
│   └── sounds.json
├── fabric.mod.json
└── advanced-weapons-mod.mixins.json
```

## ✨ Recursos

✅ 5 armas de fogo com características únicas
✅ Sons customizados (disparo, recarga, impacto)
✅ Texturas realistas em alta resolução
✅ Efeitos de partículas ao disparar e acertar
✅ Status effects especiais por arma
✅ Dano balanceado e knockback customizado
✅ Suporte bilíngue (Português BR e English)
✅ Integração completa com Fabric API
✅ Códigos de fonte em Kotlin
✅ Sistema de sons 3D realista

## 🎮 Uso

1. Encontre as armas na aba de combate do modo criativo
2. Segure clique direito para apontir/mira
3. Clique esquerdo para dispara
4. As armas têm dano fixo (não é preciso munição nesta versão)

## 🔧 Configuração (Versões Futuras)

- Sistema de munição customizado
- Craftagem de armas e munição
- Upgrades e attachments (mira, silenciador)
- Modos de tiro (Semi, Burst, Auto)
- Zoom em scopes

## 📜 Licença

MIT License - Veja LICENSE para detalhes

## 👨‍💻 Autor

**GMarcon07**
- GitHub: https://github.com/GMarcon07
- Discord: GMarcon07#0000

## 🐛 Bugs & Sugestões

Encontre um bug? Tem uma sugestão? Abra uma issue no repositório!
https://github.com/GMarcon07/advanced-weapons-mod/issues

## 📝 Changelog

Veja [CHANGELOG.md](CHANGELOG.md) para histórico completo de versões.

---

**Divirta-se com suas novas armas de fogo!** 🔫🎯