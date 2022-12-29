package hiiragi283.ragi_lib.main

import hiiragi283.ragi_lib.main.item.ItemBookDebug
import hiiragi283.ragi_lib.main.item.ItemRagiTicket
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiLibraryInit {

    //Blockの定義

    //Itemの定義
    var ItemBookDebug: Item = ItemBookDebug()
    var ItemRagiTicket: Item = ItemRagiTicket()

    //Blockを登録するメソッド
    fun registerBlocks() {}

    //Eventを登録するメソッド
    fun registerEvents() {}

    //Itemを登録するメソッド
    fun registerItems() {
        ForgeRegistries.ITEMS.register(ItemBookDebug)
        ForgeRegistries.ITEMS.register(ItemRagiTicket)
    }

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {}
}