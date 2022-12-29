package hiiragi283.ragi_lib.config

import hiiragi283.ragi_lib.main.Reference
import net.minecraftforge.common.config.Config

@Config(modid = Reference.MOD_ID)
object RagiMaterialsConfig {
    @Config.Comment("If true, GOHD Tweaks throws debug log")
    var isDebug = false
}