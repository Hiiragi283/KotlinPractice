package hiiragi283.ragi_lib.main.item

import hiiragi283.ragi_lib.config.RagiLibConfig
import hiiragi283.ragi_lib.main.base.ItemBase
import hiiragi283.ragi_lib.main.util.RagiLoot
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World

class ItemRagiTicket : ItemBase("ragi_ticket", 0) {

    //Rarityを得るメソッド
    override fun getRarity(item: ItemStack): EnumRarity {
        //EPICを返す
        return EnumRarity.EPIC
    }

    //アイテムを右クリックすると呼ばれるevent
    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val stack: ItemStack = player.getHeldItem(hand)
        player.inventory.addItemStackToInventory(RagiLoot.getLoot(world, RagiLibConfig.lootRagiTicket))
        //RagiLogger.infoDebug("loot added!")
        stack.shrink(1)
        //RagiLogger.infoDebug("ticket shrink!")
        return ActionResult(EnumActionResult.SUCCESS, stack)
    }
}