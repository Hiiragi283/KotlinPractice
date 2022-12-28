package hiiragi283.ragimaterials.main.base

import com.google.common.collect.Lists
import net.minecraft.block.Block
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class ItemBlockBase(block: Block, maxMeta: Int) : ItemBlock(block) {

    //private変数の宣言
    private val maxMeta: Int

    //コンストラクタの宣言
    init {
        registryName = Objects.requireNonNull(block.registryName) //翻訳キーはblockのものから取得
        setHasSubtypes(true) //メタデータを使用する
        this.maxMeta = maxMeta //メタデータの最大値を代入
    }

    //メタデータを得るメソッド
    override fun getMetadata(damage: Int): Int {
        //代入した値とmaxMetaを比較し、小さい方を返す
        return damage.coerceAtMost(maxMeta)
    }

    //翻訳キーを得るメソッド
    override fun getUnlocalizedName(stack: ItemStack): String {
        //メタデータが0のみの場合、なにもしない
        return if (maxMeta == 0) super.getUnlocalizedName() else super.getUnlocalizedName() + "." + stack.metadata
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //listの定義
            val list: MutableList<ItemStack> = Lists.newArrayList()
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 until maxMeta + 1) {
                list.add(ItemStack(this, 1, i))
            }
            //list内のすべてのアイテムをクリエイティブタブに登録
            subItems.addAll(list)
        }
    }

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: List<String>, flag: ITooltipFlag) {
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}