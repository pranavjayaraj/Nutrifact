package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class GeneralIngrFoodDataRemoteModel(
    @SerializedName("foodId")
    val foodId: String?,
    @SerializedName("label")
    val foodLabel: String?,
    @SerializedName("nutrients")
    val nutrients: GeneralIngrNutrientsRemoteModel?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("categoryLabel")
    val categoryLabel: String?,
    @SerializedName("image")
    val image: String?
)
