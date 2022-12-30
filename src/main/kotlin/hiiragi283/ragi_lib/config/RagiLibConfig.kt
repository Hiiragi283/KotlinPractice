package hiiragi283.ragi_lib.config

import net.minecraftforge.common.config.Configuration
import java.io.File

object RagiLibConfig {

    var isDebug = false

    fun load(file: File?) {
        val dir = File(file, "ragi_lib.cfg")
        loadConfig(Configuration(dir))
    }

    private fun loadConfig(config: Configuration) {
        try {
            //configの読み込み
            config.load()
            //各値の取得
            isDebug = config.get(
                "debug setting",
                "Debug Log",
                isDebug,
                "If true, Ragi Library throws sooo many debug logs..."
            ).boolean
        } catch (e: Exception) {
            //エラーを出力
            e.printStackTrace()
        } finally {
            //configを保存
            config.save()
        }
    }
}