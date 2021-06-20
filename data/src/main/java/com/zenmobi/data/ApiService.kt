package com.zenmobi.data

import com.zenmobi.data.models.DetailedIngredientDataRemoteModel
import com.zenmobi.data.models.GeneralIngrDataRemoteModel
import com.zenmobi.domain.models.NutrientsReqModel
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET(Urls.GET_GEN_FOOD_INFO)
    fun getGenFoodInfo(@Query("ingr") data: String): Single<GeneralIngrDataRemoteModel>

    @POST(Urls.GET_DETAILED_FOOD_INFO)
    fun getDetailedFoodInfo(@Body data: NutrientsReqModel): Single<DetailedIngredientDataRemoteModel>
}