package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class IngredientsReqModel(
    @SerializedName("quantity")
    var quantity: Float?,
    @SerializedName("measureURI")
    var measureUri: String?,
    @SerializedName("foodId")
    var foodId: String?
)