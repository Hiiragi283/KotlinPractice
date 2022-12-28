package hiiragi283.ragimaterials.main.util

import hiiragi283.ragimaterials.main.Reference
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import java.util.*

object RagiRecipe {
    //不定形レシピに鉱石辞書をねじ込むメソッド
    fun setOreDict(oreDict: String?): Ingredient {
        //鉱石辞書からItemStackのリストを取得
        val stacks = OreDictionary.getOres(oreDict)
        //結合用のリストを宣言
        val listIngredients: MutableCollection<Ingredient> = ArrayList()
        //stacks内の各keyに対して実行
        for (stack in stacks) {
            //listIngredientsにItemStackを足していく
            listIngredients.add(Ingredient.fromStacks(stack))
        }
        //listIngredientを1つのIngredientにまとめる
        return Ingredient.merge(listIngredients)
    }

    //かまどレシピを追加するメソッド
    fun addFurnace(output: ItemStack, input: ItemStack) {
        GameRegistry.addSmelting(input, output, 0f)
        RagiLogger.infoDebug("The smelting recipe " + RagiUtils.stackToBracket(input) + " -> " + RagiUtils.stackToBracket(output) + " was added successfully!")
    }

    //かまどレシピを削除するメソッド
    /*public static void removeFurnace(ItemStack output) {
        //かまどレシピのマップを取得する
        Map<ItemStack, ItemStack> mapFurnace = FurnaceRecipes.instance().getSmeltingList();
        //インプットのイテレータを取得する
        Iterator<ItemStack> iteratorFurnace = mapFurnace.keySet().iterator();
        //イテレータの各要素について実行する
        while (iteratorFurnace.hasNext()) {
            //完成品が一致する場合
            if (DCUtil.isSameItem(mapFurnace.get(iteratorFurnace.next()), output, false)) {
                //レシピを削除する
                iteratorFurnace.remove();
                RagiLogger.infoDebug("The smelting output " + RagiUtils.stackToBracket(output) + " was removed successfully!");
            }
        }
    }*/
    //定型クラフトレシピを追加するメソッド
    fun addShaped(output: ItemStack, vararg inputs: Any?) {
        //registryNameからResource Locationを生成
        val location = ResourceLocation(Reference.MOD_ID, output.item.registryName!!.resourcePath + "_" + output.metadata)
        //レシピを追加する
        GameRegistry.addShapedRecipe(location, location, output, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    fun addShaped(alt: String, output: ItemStack, vararg inputs: Any?) {
        //registryNameからResource Locationを生成
        val location = ResourceLocation(Reference.MOD_ID, output.item.registryName!!.resourcePath + "_" + output.metadata + "_" + alt)
        //レシピを追加する
        GameRegistry.addShapedRecipe(location, location, output, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    //定型クラフトレシピを上書きするメソッド
    fun addShapedOverride(registryName: String, output: ItemStack?, vararg inputs: Any?) {
        //registryNameからResource Locationを生成
        val location = RagiUtils.getResource(registryName)
        //レシピを上書きする
        GameRegistry.addShapedRecipe(location, location, output!!, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was overrided successfully!")
    }

    //不定型クラフトレシピを追加するメソッド
    fun addShapeless(output: ItemStack, vararg inputs: Ingredient?) {
        //registryNameからResource Locationを生成
        val location = ResourceLocation(Reference.MOD_ID, output.item.registryName!!.resourcePath + "_" + output.metadata)
        //レシピを追加する
        GameRegistry.addShapelessRecipe(location, location, output, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    fun addShapeless(alt: String, output: ItemStack, vararg inputs: Ingredient?) {
        //registryNameからResource Locationを生成
        val location = ResourceLocation(Reference.MOD_ID, output.item.registryName!!.resourcePath + "_" + output.metadata + "_" + alt)
        //レシピを追加する
        GameRegistry.addShapelessRecipe(location, location, output, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    //不定型クラフトレシピを上書きするメソッド
    fun addShapelessOverride(registryName: String, output: ItemStack?, vararg inputs: Ingredient?) {
        //registryNameからResource Locationを生成
        val location = RagiUtils.getResource(registryName)
        //レシピを上書きする
        GameRegistry.addShapelessRecipe(location, location, output!!, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was overrided successfully!")
    }

    //クラフトレシピを削除するメソッド
    fun remove(registryName: String) {
        //registryNameからResource Locationを生成
        val location = RagiUtils.getResource(registryName)
        //locationからレシピを取得
        val recipeBefore = CraftingManager.getRecipe(location)
        //取得したレシピがnullでない場合
        if (Objects.nonNull(recipeBefore)) {
            //レシピを置き換える
            GameRegistry.addShapedRecipe(location, location, recipeBefore!!.recipeOutput, "A", 'A', RagiUtils.getStack("minecraft:barrier", 1, 0))
            RagiLogger.infoDebug("The recipe <recipe:$location> was removed successfully!")
        } else {
            RagiLogger.warnDebug("The recipe <recipe:$location> was not found...")
        }
    }
}