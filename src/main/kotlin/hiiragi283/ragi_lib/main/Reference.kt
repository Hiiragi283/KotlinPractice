package hiiragi283.ragi_lib.main

import net.minecraft.client.Minecraft
import net.minecraft.server.MinecraftServer

object Reference {
    //MOD IDの定義
    const val MOD_ID = "ragi_lib"

    //Mod名の定義
    const val MOD_NAME = "Ragi Library"

    //Modのバージョンの定義
    const val VERSION = "v0.0.1"

    //依存関係の定義
    const val DEPENDENCIES = "required-after:forgelin"

    //対応するMCのバージョンの定義
    const val MC_VERSIONS = "[1.12,1.12.2]"

    //Client側のProxyの定義
    const val CLIENT_PROXY_CLASS = "hiiragi283.ragi_lib.main.proxy.ClientProxy"

    //Server側のProxyの定義
    const val SERVER_PROXY_CLASS = "hiiragi283.ragi_lib.main.proxy.ServerProxy"

    //Serverの定義 (コマンド実行用)
    val SERVER: MinecraftServer? = Minecraft.getMinecraft().integratedServer
}