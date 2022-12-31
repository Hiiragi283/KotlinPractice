package hiiragi283.ragi_lib.config

import net.minecraftforge.common.config.Configuration
import java.io.File

object RagiLibConfig {

    //変数の宣言
    //Debug Setting
    var isDebug = false

    //Loot Tables
    var lootRagiTicket = "minecraft:chests/simple_dungeon"

    //configを読み込むメソッド
    fun load(file: File) {
        //configファイルを指定
        val config = Configuration(File(file, "ragi_lib.cfg"))
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
            lootRagiTicket = config.get(
                "loot tables",
                "Loot Table for Ragi Ticket",
                lootRagiTicket,
                "Specify which loot tables to use for Ragi Ticket"
            ).string
        } catch (e: Exception) {
            //エラーを出力
            e.printStackTrace()
        } finally {
            //configを保存
            config.save()
        }
    }
}