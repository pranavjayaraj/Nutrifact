package com.zenmobi.nutrifact

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.zenmobi.domain.SchedulerProvider
import com.zenmobi.domain.interactors.GetDetailedNutritionDataUseCase
import com.zenmobi.domain.interactors.GetNutritionDataUseCase
import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.domain.models.IngredientsReqModel
import com.zenmobi.domain.models.NutrientsReqModel
import com.zenmobi.nutrifact.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Single
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import java.util.*
import java.util.Optional.empty
import javax.inject.Inject


class MainViewModel @Inject constructor(
    schedulersFacade: SchedulerProvider
) : BaseViewModel(schedulersFacade) {

    @Inject
    lateinit var getNutritionDataUseCase: GetNutritionDataUseCase

    @Inject
    lateinit var getDetailedNutritionDataUseCase: GetDetailedNutritionDataUseCase

    var nutritionData = MutableLiveData<Pair<String, DetailedIngredientDataResModel?>>()

    var calories = 0f

    var deletedIndex: Int? = null

    fun observeNutritionData(): MutableLiveData<Pair<String, DetailedIngredientDataResModel?>> {
        return nutritionData
    }

    fun getNutritionData(ing: String?) {
        var image : String? = ""
        getNutritionDataUseCase
            .execute(ing!!)
            .flatMap {
                //put the condition to call the second api only if the measurement is in kgs
                image = it.image
                return@flatMap if (true) {
                    getDetailedNutritionDataUseCase.execute(
                        NutrientsReqModel(
                            arrayListOf(
                                IngredientsReqModel(
                                    quantity = it.quantity,
                                    measureUri = it.measure,
                                    foodId = it.foodId
                                )
                            )
                        )
                    )
                } else {
                    return@flatMap Observable.just(it).single(it)
                }
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                if (it != null) {
                    it.image = image
                    nutritionData.postValue(Pair(ing, it))
                }
            }, {
                Log.d("[DETAILED INGR]", it?.localizedMessage.toString())
            }).addTo(getCompositeDisposable())
    }

    fun getDetailedNutritionData(param: NutrientsReqModel) {
        getDetailedNutritionDataUseCase.execute(param)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({

            }, {

            }).let { }
    }

}