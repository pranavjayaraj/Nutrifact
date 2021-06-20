package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class DetailedIngredientParsedResModel(
    @SerializedName("quanitiy")
    val quantity: Float?,
    @SerializedName("measure")
    val measure: String?,
    @SerializedName("food")
    val food: String?,
    @SerializedName("foodId")
    val foodId: String?,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("measureURI")
    val measureUri: String?,
    @SerializedName("status")
    val status: String?
)