package hiiragi283.ragimaterials.main.proxy

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        setModels()
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        setColors()
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModel(item: Item) {
        //itemがメタデータを使用する場合
        if (item.hasSubtypes) {
            //メタデータが最大値になるまで処理を繰り返す
            for (i in 0 until item.getMetadata(283) + 1) {
                ModelLoader.setCustomModelResourceLocation(item, i, ModelResourceLocation(item.registryName.toString() + "_" + i, "inventory"))
            }
        } else {
            //itemが耐久地を持っている場合，IDから設定
            ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(item.registryName!!, "inventory"))
        }
    }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    @SideOnly(Side.CLIENT)
    fun setModelSame(item: Item) {
        //メタデータが最大値になるまで処理を繰り返す
        for (i in 0 until item.getMetadata(283) + 1) {
            ModelLoader.setCustomModelResourceLocation(item, i, ModelResourceLocation(item.registryName!!, "inventory"))
        }
    }

    //代入されたIBlockColorをBlockに登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setColor(color: IBlockColor?, block: Block?) {
        Minecraft.getMinecraft().blockColors.registerBlockColorHandler(color!!, block)
    }

    //代入されたIItemColorをItemに登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setColor(color: IItemColor?, item: Item?) {
        Minecraft.getMinecraft().itemColors.registerItemColorHandler(color!!, item)
    }

    //各Itemのモデルファイルのパスを指定するメソッド
    fun setModels() {}

    //各Itemの着色を指定するメソッド
    fun setColors() {}
}