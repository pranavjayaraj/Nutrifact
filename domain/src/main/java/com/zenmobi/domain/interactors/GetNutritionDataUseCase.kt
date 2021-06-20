package com.zenmobi.domain.interactors

import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.domain.repository.ApiRepository
import javax.inject.Inject
import com.zenmobi.domain.types.SingleUseCaseWithParam
import io.reactivex.Single

class GetNutritionDataUseCase @Inject constructor(private val apiRepository: ApiRepository) :
    SingleUseCaseWithParam<String, DetailedIngredientDataResModel?> {
    override fun execute(param: String): Single<DetailedIngredientDataResModel?> {
        return apiRepository.getGeneralNutritionData(param)
    }
}