package com.zenmobi.data

import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.domain.models.NutrientsReqModel
import com.zenmobi.domain.repository.ApiRepository
import io.reactivex.Single
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val nutritionMapper: NutritionMapper
) : ApiRepository {

    override fun getGeneralNutritionData(param: String): Single<DetailedIngredientDataResModel?> {
        return apiService.getGenFoodInfo(param).map {
            nutritionMapper.getNutritionRestModel(it)
        }
    }

    override fun getDetailedNutritionData(param: NutrientsReqModel): Single<DetailedIngredientDataResModel?> {
        return apiService.getDetailedFoodInfo(param).map {
            nutritionMapper.getNutritionRestModel(it)
        }
    }

}