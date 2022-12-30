package hiiragi283.ragi_lib.main.item

import hiiragi283.ragi_lib.main.base.ItemBase
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack

class ItemRagiTicket : ItemBase("ragi_ticket", 0, 64) {

    //Rarityを得るメソッド
    override fun getRarity(item: ItemStack): EnumRarity {
        //EPICを返す
        return EnumRarity.EPIC
    }
}