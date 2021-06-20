package com.zenmobi.data.models

import com.google.gson.annotations.SerializedName
import com.zenmobi.domain.models.GeneralIngrHintDataRemoteModel

class GeneralIngrDataRemoteModel(
    @SerializedName("text")
    val text: String?,
    @SerializedName("parsed")
    val parsedData: ArrayList<GeneralIngrParsedDataRemoteModel>?,
    @SerializedName("hint")
    val hint: ArrayList<GeneralIngrHintDataRemoteModel>?
)