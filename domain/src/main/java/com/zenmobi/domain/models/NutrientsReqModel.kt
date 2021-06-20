package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class NutrientsReqModel(
    @SerializedName("ingredients")
    var ingredientInfo:ArrayList<IngredientsReqModel>
)