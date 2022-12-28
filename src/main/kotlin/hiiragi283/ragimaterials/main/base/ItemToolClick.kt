package hiiragi283.ragimaterials.main.base

import com.google.common.collect.Sets
import hiiragi283.ragimaterials.main.Reference
import hiiragi283.ragimaterials.main.util.RagiUtils
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool
import net.minecraft.util.*
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import java.util.*

//Sandpaper系の継承用のクラス
class ItemToolClick(ID: String?, maxDamage: Int) : ItemTool(ToolMaterial.WOOD, BLOCKS) {

    companion object {
        val BLOCKS: Set<Block?> = Sets.newHashSet(RagiUtils.getBlock("minecraft", "air"))
    }

    //コンストラクタの宣言
    init {
        creativeTab = CreativeTabs.TOOLS //表示するクリエイティブタブの設定
        setRegistryName(Reference.MOD_ID, ID) //IDの設定
        unlocalizedName = ID.toString() //翻訳キーをIDから取得
        this.maxDamage = maxDamage //最大耐久地をmaxDamageに設定
    }

    //onItemRightClickに使用するレシピマップを指定するメソッド
    //あえてメソッドとして記述することで, Overrideできるようにする
    fun recipeMap(state: IBlockState?): IBlockState? {
        return null
    }

    fun recipeMap(block: Block?): IBlockState? {
        return null
    }

    //レシピ完了時に鳴らす音もメソッドにしておく
    fun recipeSound(): SoundEvent? {
        return null
    }

    //アイテムを右クリックすると呼ばれるイベント
    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        //保持しているアイテムをItemStack型で取得
        val stack = player.getHeldItem(hand)
        //サーバー側のみで実行
        if (!world.isRemote) {
            //playerの視線を取得
            val ray = rayTrace(world, player, false)
            //視線の先がブロックの場合
            if (ray.typeOfHit == RayTraceResult.Type.BLOCK) {
                //ブロックまわりの値の取得
                val pos = ray.blockPos
                val state = world.getBlockState(pos)
                val block = state.block
                //stateと対応する組み合わせがある場合
                if (Objects.nonNull(recipeMap(state))) {
                    //対応するstateで置き換える
                    world.setBlockState(pos, recipeMap(state)!!)
                    //stackの耐久地を1減らす
                    stack.damageItem(1, player)
                    //とりあえず音鳴らすか
                    world.playSound(null, pos, recipeSound()!!, SoundCategory.BLOCKS, 1.0f, 0.5f)
                } else if (Objects.nonNull(recipeMap(block))) {
                    //対応するstateで置き換える
                    world.setBlockState(pos, recipeMap(block)!!)
                    //stackの耐久地を1減らす
                    stack.damageItem(1, player)
                    //とりあえず音鳴らすか
                    world.playSound(null, pos, recipeSound()!!, SoundCategory.BLOCKS, 1.0f, 0.5f)
                }
            }
            //実験は成功だ!
            return ActionResult(EnumActionResult.SUCCESS, stack)
        }
        //何がいけなかったのだろうか
        return ActionResult(EnumActionResult.FAIL, stack)
    }
}