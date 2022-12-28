package hiiragi283.ragimaterials.main

import net.minecraftforge.common.config.Config

@Config(modid = Reference.MOD_ID)
object RagiMaterialConfig {
    @Config.Comment("If true, GOHD Tweaks throws debug log")
    var isDebug = false
}