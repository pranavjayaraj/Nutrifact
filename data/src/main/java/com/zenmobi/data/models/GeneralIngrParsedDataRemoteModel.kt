package com.zenmobi.data.models

import com.google.gson.annotations.SerializedName
import com.zenmobi.domain.models.GeneralIngrFoodDataRemoteModel
import com.zenmobi.domain.models.GeneralIngrMeasureRemoteModel

class GeneralIngrParsedDataRemoteModel(
    @SerializedName("food")
    val food: GeneralIngrFoodDataRemoteModel?,
    @SerializedName("quantity")
    val quantity: Float?,
    @SerializedName("measure")
    val measure: GeneralIngrMeasureRemoteModel?,
)