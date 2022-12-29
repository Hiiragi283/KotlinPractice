package hiiragi283.ragi_lib.config

import hiiragi283.ragi_lib.main.Reference
import net.minecraftforge.common.config.Config

@Config(modid = Reference.MOD_ID)
object RagiMaterialsConfig {
    @Config.Comment("If true, Ragi Library throws too many debug log")
    var isDebug = false
}