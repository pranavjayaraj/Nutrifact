package com.zenmobi.data

import android.util.Log
import com.zenmobi.data.models.DetailedIngredientDataRemoteModel
import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.domain.models.DetailedNutrientDataResModel
import com.zenmobi.data.models.DetailedNutrientsRemoteModel
import com.zenmobi.data.models.GeneralIngrDataRemoteModel
import com.zenmobi.domain.models.GeneralIngrNutrientsRemoteModel
import javax.inject.Inject

class NutritionMapper @Inject constructor() {


    fun getNutritionRestModel(data: GeneralIngrDataRemoteModel): DetailedIngredientDataResModel {
        return DetailedIngredientDataResModel(
            calories = data.parsedData?.get(0)?.food?.nutrients?.energy?.times(10),
            weight = null,
            labels = null,
            cautions = null,
            totalDetailedNutrientData = getArrayListOfNutrientsData(data = data.parsedData?.get(0)?.food?.nutrients),
            null,
            hint = data.hint,
            foodId = data.parsedData?.get(0)?.food?.foodId,
            measure = data.parsedData?.get(0)?.measure?.uri,
            quantity = data.parsedData?.get(0)?.quantity,
            image = data.parsedData?.get(0)?.food?.image
        )
    }

    private fun getArrayListOfNutrientsData(data: GeneralIngrNutrientsRemoteModel?): ArrayList<DetailedNutrientDataResModel?> {
        //convert to arraylist and give back
        var arrayList = ArrayList<DetailedNutrientDataResModel?>()
        arrayList.add(
            toDetailedNutrientsResModel("ENERGY", data?.energy) ?: getEmptyNutrientsModel(
                label = "ENERGY"
            )
        )
        arrayList.add(
            toDetailedNutrientsResModel("FAT", data?.fat) ?: getEmptyNutrientsModel(label = "FAT")
        )
        arrayList.add(
            toDetailedNutrientsResModel("PROTEIN", data?.protein)
                ?: getEmptyNutrientsModel(label = "PROTEIN")
        )
        arrayList.add(
            toDetailedNutrientsResModel("FIBER", data?.fiber) ?: getEmptyNutrientsModel(
                label = "FIBER"
            )
        )
        arrayList.add(
            toDetailedNutrientsResModel("CHOLESTROL", data?.cholestrol) ?: getEmptyNutrientsModel(
                label = "CHOLESTROL"
            )
        )
        return arrayList
    }

    private fun getEmptyNutrientsModel(label: String?): DetailedNutrientDataResModel {
        return DetailedNutrientDataResModel(
            label = label,
            unit = "NA",
            quantity = 0f
        )
    }

    private fun toDetailedNutrientsResModel(
        label: String?,
        amount: Float?
    ): DetailedNutrientDataResModel? {
        if (label == null) {
            return null
        }
        return when (label) {
            "ENERGY" -> {
                DetailedNutrientDataResModel(unit = "Kcal", quantity = amount, label = label)
            }
            else -> {
                DetailedNutrientDataResModel(unit = "G", quantity = amount, label = label)
            }
        }
    }

    fun getNutritionRestModel(nutritionDataDetailed: DetailedIngredientDataRemoteModel): DetailedIngredientDataResModel {
        return DetailedIngredientDataResModel(
            calories = nutritionDataDetailed.calories,
            weight = nutritionDataDetailed.weight,
            labels = nutritionDataDetailed.labels,
            cautions = nutritionDataDetailed.cautions,
            totalDetailedNutrientData = getArrayListOfNutrientsData(nutritionDataDetailed.totalNutrientData),
            nutritionDataDetailed.detailedIngredientsInfoRemoteMode[0].parsedDataDetailed[0],
            null,
            null,
            null,
            null,
            null
        )
    }

    private fun getArrayListOfNutrientsData(detailedNutrientsRemoteModel: DetailedNutrientsRemoteModel?): ArrayList<DetailedNutrientDataResModel?> {
        //convert to arraylist and give back
        var arrayList = ArrayList<DetailedNutrientDataResModel?>()
        Log.d("[CALORIES]", "${detailedNutrientsRemoteModel?.energy}")
        arrayList.add(
            detailedNutrientsRemoteModel?.energy ?: getEmptyNutrientsModel(label = "ENERGY")
        )
        arrayList.add(detailedNutrientsRemoteModel?.fat ?: getEmptyNutrientsModel(label = "FAT"))
        arrayList.add(
            detailedNutrientsRemoteModel?.protein ?: getEmptyNutrientsModel(label = "PROTEIN")
        )
        arrayList.add(
            detailedNutrientsRemoteModel?.fiber ?: getEmptyNutrientsModel(label = "FIBER")
        )
        arrayList.add(
            detailedNutrientsRemoteModel?.sugar ?: getEmptyNutrientsModel(label = "SUGAR")
        )
        return arrayList
    }

}