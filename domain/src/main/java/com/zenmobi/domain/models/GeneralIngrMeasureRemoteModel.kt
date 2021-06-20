package com.zenmobi.domain.models

import com.google.gson.annotations.SerializedName

class GeneralIngrMeasureRemoteModel(
    @SerializedName("uri")
    val uri: String?,
    @SerializedName("label")
    val label: String?
)