package com.zenmobi.data.models

import com.google.gson.annotations.SerializedName
import com.zenmobi.domain.models.DetailedIngredientParsedResModel

class DetailedIngredientsInfoRemoteModel(
    @SerializedName("parsed")
    val parsedDataDetailed: ArrayList<DetailedIngredientParsedResModel>
)