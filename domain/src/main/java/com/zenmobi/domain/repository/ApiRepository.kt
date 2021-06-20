package com.zenmobi.domain.repository

import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.domain.models.NutrientsReqModel
import io.reactivex.Single

interface ApiRepository {
    fun getGeneralNutritionData(param:String): Single<DetailedIngredientDataResModel?>
    fun getDetailedNutritionData(param:NutrientsReqModel): Single<DetailedIngredientDataResModel?>
}