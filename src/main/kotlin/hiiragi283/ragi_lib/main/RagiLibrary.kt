package hiiragi283.ragi_lib.main

import hiiragi283.ragi_lib.config.RagiLibConfig
import hiiragi283.ragi_lib.main.proxy.CommonProxy
import hiiragi283.ragi_lib.main.util.RagiUtils
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

//Modの定義
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, acceptedMinecraftVersions = Reference.MC_VERSIONS, modLanguageAdapter = Reference.LANGUAGE)
class RagiLibrary {

    companion object {
        //Proxyの定義
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        var proxy: CommonProxy? = null
    }

    //Pre-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        //configの読み込み
        RagiLibConfig.load(event!!.modConfigurationDirectory)
        //Block, Event, Itemの登録
        RagiLibraryInit.registerBlocks()
        RagiLibraryInit.registerEvents()
        RagiLibraryInit.registerItems()
        //proxyの読み込み
        proxy!!.loadPreInit()
    }

    //Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        //鉱石辞書の登録
        RagiLibraryInit.registerOreDict()
        //proxyの読み込み
        proxy!!.loadInit()
    }

    //Post-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        //Itemの最大スタック数を上書きする
        //どんな名前のclassにしたらいいか思いつかなかった
        val listMaxSize = mutableListOf("minecraft:acacia_boat")
        listMaxSize.add("minecraft:bed")
        listMaxSize.add("minecraft:beetroot_soup")
        listMaxSize.add("minecraft:birch_boat")
        listMaxSize.add("minecraft:boat")
        listMaxSize.add("minecraft:bucket")
        listMaxSize.add("minecraft:cake")
        listMaxSize.add("minecraft:chest_minecart")
        listMaxSize.add("minecraft:command_block_minecart")
        listMaxSize.add("minecraft:dark_oak_boat")
        listMaxSize.add("minecraft:diamond_horse_armor")
        listMaxSize.add("minecraft:egg")
        listMaxSize.add("minecraft:enchanted_book")
        listMaxSize.add("minecraft:ender_pearl")
        listMaxSize.add("minecraft:furnace_minecart")
        listMaxSize.add("minecraft:golden_horse_armor")
        listMaxSize.add("minecraft:hopper_minecart")
        listMaxSize.add("minecraft:iron_horse_armor")
        listMaxSize.add("minecraft:jungle_boat")
        listMaxSize.add("minecraft:lava_bucket")
        listMaxSize.add("minecraft:minecart")
        listMaxSize.add("minecraft:mushroom_stew")
        listMaxSize.add("minecraft:rabbit_stew")
        listMaxSize.add("minecraft:record_11")
        listMaxSize.add("minecraft:record_13")
        listMaxSize.add("minecraft:record_blocks")
        listMaxSize.add("minecraft:record_cat")
        listMaxSize.add("minecraft:record_chirp")
        listMaxSize.add("minecraft:record_far")
        listMaxSize.add("minecraft:record_mall")
        listMaxSize.add("minecraft:record_mellohi")
        listMaxSize.add("minecraft:record_stal")
        listMaxSize.add("minecraft:record_strad")
        listMaxSize.add("minecraft:record_wait")
        listMaxSize.add("minecraft:record_ward")
        listMaxSize.add("minecraft:saddle")
        listMaxSize.add("minecraft:sign")
        listMaxSize.add("minecraft:snowball")
        listMaxSize.add("minecraft:spruce_boat")
        listMaxSize.add("minecraft:tnt_minecart")
        listMaxSize.add("minecraft:water_bucket")
        listMaxSize.add("minecraft:written_book")
        for (name in listMaxSize) {
            RagiUtils.getItem(name).setMaxStackSize(64)
        }
        //proxyの読み込み
        proxy!!.loadPostInit()
    }
}