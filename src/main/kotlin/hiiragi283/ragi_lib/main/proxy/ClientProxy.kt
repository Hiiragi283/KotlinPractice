package hiiragi283.ragi_lib.main.proxy

import hiiragi283.ragi_lib.main.RagiLibraryInit
import hiiragi283.ragi_lib.main.util.RagiModel

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        RagiModel.setModel(RagiLibraryInit.ItemBookDebug)
        RagiModel.setModel(RagiLibraryInit.ItemRagiTicket)
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {}

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}