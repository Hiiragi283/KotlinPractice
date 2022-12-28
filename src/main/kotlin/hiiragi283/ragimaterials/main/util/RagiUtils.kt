package hiiragi283.ragimaterials.main.util

import hiiragi283.ragimaterials.main.Reference
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.oredict.OreDictionary
import java.util.*

object RagiUtils {
    //コマンドを実行するメソッド
    fun executeCommand(sender: ICommandSender?, command: String?) {
        if (Reference.SERVER !== null) {
            Reference.SERVER.getCommandManager().executeCommand(sender!!, command!!)
        }
    }

    //ResourceLocationからBlockを取得するメソッド
    //Blockがnullの場合はバリアブロックを返す
    fun getBlock(registryName: String): Block {
        val block: Block? = ForgeRegistries.BLOCKS.getValue(getResource(registryName))
        return if (block !== null) block else {
            RagiLogger.warnDebug("The block <$registryName> was not found...")
            Blocks.BARRIER
        }
    }

    fun getBlock(domain: String, path: String): Block {
        return getBlock("$domain:$path")
    }

    //液体名からFluidを取得するメソッド
    //Fluidがnullの場合は水を返す
    fun getFluid(name: String): Fluid {
        val fluid: Fluid = FluidRegistry.getFluid(name)
        val water: Fluid = FluidRegistry.getFluid("water")
        return if (fluid !== null) fluid else {
            RagiLogger.warnDebug("The fluid <fluid:$name> was not found...")
            water
        }
    }

    //ResourceLocationからItemを取得するメソッド
    //Itemがnullの場合はバリアブロックを返す
    fun getItem(registryName: String): Item {
        val item: Item? = ForgeRegistries.ITEMS.getValue(getResource(registryName))
        val barrier: Item? = ForgeRegistries.ITEMS.getValue(ResourceLocation("minecraft", "barrier"))
        return if (Objects.nonNull(item)) item!! else {
            RagiLogger.warnDebug("The item <$registryName> was not found...")
            barrier!!
        }
    }

    fun getItem(domain: String, path: String): Item {
        return getItem("$domain:$path")
    }

    //ResourceLocationなどからItemStackを取得するメソッド
    //ItemStackがnullの場合はバリアブロックを返す
    fun getStack(registryName: String, amount: Int, meta: Int): ItemStack {
        val item: Item? = ForgeRegistries.ITEMS.getValue(getResource(registryName))
        return if (Objects.nonNull(item)) ItemStack(item!!, amount, meta) else {
            RagiLogger.warnDebug("The item stack <$registryName:$meta> * $amount was not found...")
            ItemStack(getItem("minecraft", "barrier"), amount, 0)
        }
    }

    fun getStack(domain: String, path: String, amount: Int, meta: Int): ItemStack {
        return getStack("$domain:$path", amount, meta)
    }

    //ResourceLocationなどからIBlockStateを取得するメソッド
    //IBlockStateがnullの場合はバリアブロックを返す
    fun getState(registryName: String, meta: Int): IBlockState {
        val block = getBlock(registryName)
        val state: IBlockState = block.getStateFromMeta(meta)
        return if (block !== Blocks.BARRIER) state else {
            RagiLogger.warnDebug("The blockstate <blockstate:$block:$meta> was not found...")
            Blocks.BARRIER.defaultState
        }
    }

    fun getState(domain: String, path: String, meta: Int): IBlockState {
        return getState("$domain:$path", meta)
    }

    fun getState(block: Block, meta: Int): IBlockState {
        val state: IBlockState = block.getStateFromMeta(meta)
        return if (block !== Blocks.BARRIER) state else {
            RagiLogger.warnDebug("The blockstate <blockstate:$block:$meta> was not found...")
            Blocks.BARRIER.defaultState
        }
    }

    //ResourceLocationからPotionを取得するメソッド
    //Potionがnullの場合は不運を返す
    fun getPotion(registryName: String): Potion {
        val potion: Potion? = ForgeRegistries.POTIONS.getValue(getResource(registryName))
        val unluck: Potion? = ForgeRegistries.POTIONS.getValue(ResourceLocation("minecraft", "unluck"))
        return if (Objects.nonNull(potion)) potion!! else {
            RagiLogger.warnDebug("The potion <potion:$registryName> was not found...")
            unluck!!
        }
    }

    fun getPotion(domain: String, path: String): Potion {
        return getPotion("$domain:$path")
    }

    //ResourceLocationなどからPotionEffectを取得するメソッド
    fun getPotionEffect(domain: String, path: String, time: Int, level: Int): PotionEffect {
        return PotionEffect(getPotion(domain, path), time, level)
    }

    //StringからResource Locationを取得するメソッド
    fun getResource(location: String): ResourceLocation {
        return ResourceLocation(location.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0], location.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
    }

    //ResourceLocationからSoundEventを取得するメソッド
    //SoundEventがnullの場合はレベルアップの音を返す
    fun getSound(registryName: String): SoundEvent {
        val location: ResourceLocation = getResource(registryName)
        val sound: SoundEvent? = ForgeRegistries.SOUND_EVENTS.getValue(location)
        val levelUp: SoundEvent? = ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation("minecraft", "entity.player.levelup"))
        return if (Objects.nonNull(sound)) sound!! else {
            RagiLogger.warnDebug("The sound <soundevent:$location> was not found...")
            levelUp!!
        }
    }

    fun getSound(domain: String, path: String): SoundEvent {
        return getSound("$domain:$path")
    }

    //鉱石辞書を追加するメソッド
    fun setOreDict(oreDict: String, stack: ItemStack) {
        OreDictionary.registerOre(oreDict, stack)
        RagiLogger.infoDebug("New ore dictionary <ore:" + oreDict + "> was added to " + stackToBracket(stack))
    }

    //titleコマンドをより簡潔に実行するメソッド
    fun setTitle(player: EntityPlayer?, title: String, subtitle: String) {
        //コマンドの実行結果を出力しないようにする
        executeCommand(player, "gamerule sendCommandFeedback false")
        //titleの設定
        executeCommand(player, "title @p times 20 60 20")
        executeCommand(player, "title @p title [{\"translate\":\"$title\"}]")
        executeCommand(player, "title @p subtitle {\"translate\":\"$subtitle\"}")
    }

    //Hypixelで慣れ親しんだこの音声を再び聞いたとき，感動で泣きそうになりました
    fun soundHypixel(world: World, pos: BlockPos) {
        world.playSound(null, pos, getSound("minecraft", "entity.player.levelup"), SoundCategory.BLOCKS, 1.0f, 0.5f)
    }

    //ItemStackをCrTのブラケット記法に変換するメソッド (ログ出力用)
    fun stackToBracket(stack: ItemStack): String {
        val item: Item = stack.item
        val location: ResourceLocation? = item.registryName
        val amount: Int = stack.count
        val meta: Int = stack.metadata
        return "<$location:$meta> * $amount"
    }
}