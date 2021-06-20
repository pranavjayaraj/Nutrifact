package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class GeneralIngrNutrientsRemoteModel(
    @SerializedName("ENERC_KCAL")
    val energy: Float?,
    @SerializedName("PROCNT")
    val protein: Float?,
    @SerializedName("FAT")
    val fat: Float?,
    @SerializedName("CHOCDF")
    val cholestrol: Float?,
    @SerializedName("FIBTG")
    val fiber: Float
)