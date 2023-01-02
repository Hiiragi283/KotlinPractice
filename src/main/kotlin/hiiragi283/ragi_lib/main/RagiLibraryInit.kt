package hiiragi283.ragi_lib.main

import hiiragi283.ragi_lib.main.base.FluidBase
import hiiragi283.ragi_lib.main.event.RightClickBlock
import hiiragi283.ragi_lib.main.item.ItemBookDebug
import hiiragi283.ragi_lib.main.item.ItemRagiTicket
import hiiragi283.ragi_lib.main.util.RagiColor
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiLibraryInit {

    //Blockの定義

    //Fluidの定義
    var FluidRagi: Fluid = FluidBase("hiiragi").setColor(RagiColor.RAGI_RED).setTemperature(283)

    //Itemの定義
    var ItemBookDebug: Item = ItemBookDebug()
    var ItemRagiTicket: Item = ItemRagiTicket()

    //Blockを登録するメソッド
    fun registerBlocks() {}

    //Eventを登録するメソッド
    fun registerEvents() {
        MinecraftForge.EVENT_BUS.register(RightClickBlock())
    }

    //Fluidを登録するメソッド
    fun registerFluids() {
        registerFluid(FluidRagi)
    }

    //Itemを登録するメソッド
    fun registerItems() {
        ForgeRegistries.ITEMS.register(ItemBookDebug)
        ForgeRegistries.ITEMS.register(ItemRagiTicket)
    }

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {}

    //FluidとBucketを登録するメソッド
    private fun registerFluid(fluid: Fluid) {
        FluidRegistry.addBucketForFluid(fluid)
        FluidRegistry.registerFluid(fluid)
    }
}