package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class DetailedNutrientDataResModel(
    @SerializedName("label")
    val label:String?,
    @SerializedName("quantity")
    val quantity:Float?,
    @SerializedName("unit")
    val unit:String?
)