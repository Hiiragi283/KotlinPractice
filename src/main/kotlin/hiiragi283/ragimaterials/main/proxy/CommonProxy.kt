package hiiragi283.ragimaterials.main.proxy

open class CommonProxy {
    //Pre-Initializationで読み込むメソッド
    open fun loadPreInit() {}

    //Initializationで読み込むメソッド
    open fun loadInit() {}

    //Post-Initializationで読み込むメソッド
    open fun loadPostInit() {}
}