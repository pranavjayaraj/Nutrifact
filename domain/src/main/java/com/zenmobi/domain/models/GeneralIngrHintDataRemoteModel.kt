package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class GeneralIngrHintDataRemoteModel(
    @SerializedName("food")
    val food: GeneralIngrFoodDataRemoteModel?,
    @SerializedName("quantity")
    val quantity:Float?,
    @SerializedName("measure")
    val measure:ArrayList<GeneralIngrMeasureRemoteModel>?
)