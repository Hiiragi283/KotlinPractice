package hiiragi283.ragimaterials.main.util

import hiiragi283.ragimaterials.main.RagiMaterialConfig
import hiiragi283.ragimaterials.main.Reference
import org.apache.logging.log4j.LogManager

object RagiLogger {
    private val LOGGER_RAGI = LogManager.getLogger(Reference.MOD_ID)
    fun info(info: Any?) {
        LOGGER_RAGI.info(info)
    }

    fun warn(info: Any?) {
        LOGGER_RAGI.warn(info)
    }

    fun error(info: Any?) {
        LOGGER_RAGI.error(info)
    }

    fun infoDebug(info: Any?) {
        if (RagiMaterialConfig.isDebug) {
            LOGGER_RAGI.info(info)
        }
    }

    fun warnDebug(info: Any?) {
        if (RagiMaterialConfig.isDebug) {
            LOGGER_RAGI.warn(info)
        }
    }

    fun errorDebug(info: Any?) {
        if (RagiMaterialConfig.isDebug) {
            LOGGER_RAGI.error(info)
        }
    }
}