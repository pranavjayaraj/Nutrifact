package com.zenmobi.domain.interactors

import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.domain.models.NutrientsReqModel
import com.zenmobi.domain.repository.ApiRepository
import com.zenmobi.domain.types.SingleUseCaseWithParam
import io.reactivex.Single
import javax.inject.Inject

class GetDetailedNutritionDataUseCase @Inject constructor(private val apiRepository: ApiRepository) :
    SingleUseCaseWithParam<NutrientsReqModel, DetailedIngredientDataResModel?> {
    override fun execute(param: NutrientsReqModel): Single<DetailedIngredientDataResModel?> {
        return apiRepository.getDetailedNutritionData(param)
    }
}