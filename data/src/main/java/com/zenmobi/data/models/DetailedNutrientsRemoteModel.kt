package com.zenmobi.data.models

import com.google.gson.annotations.SerializedName
import com.zenmobi.domain.models.DetailedNutrientDataResModel

class DetailedNutrientsRemoteModel(
    @SerializedName("ENERC_KCAL")
    val energy: DetailedNutrientDataResModel?,
    @SerializedName("FAT")
    val fat: DetailedNutrientDataResModel?,
    @SerializedName("FIBTG")
    val fiber: DetailedNutrientDataResModel?,
    @SerializedName("SUGAR")
    val sugar: DetailedNutrientDataResModel?,
    @SerializedName("PROCNT")
    val protein: DetailedNutrientDataResModel?
    )