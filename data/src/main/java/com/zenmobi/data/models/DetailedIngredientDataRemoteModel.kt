package com.zenmobi.data.models

import com.google.gson.annotations.SerializedName

class DetailedIngredientDataRemoteModel(
    @SerializedName("calories")
    val calories: Float?,
    @SerializedName("totalWeight")
    val weight: String?,
    @SerializedName("healthLabels")
    val labels: ArrayList<String>?,
    @SerializedName("cautions")
    val cautions: ArrayList<String>?,
    @SerializedName("totalNutrients")
    val totalNutrientData: DetailedNutrientsRemoteModel?,
    @SerializedName("ingredients")
    val detailedIngredientsInfoRemoteMode: ArrayList<DetailedIngredientsInfoRemoteModel>
)