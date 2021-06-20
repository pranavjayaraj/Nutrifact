package com.zenmobi.nutrifact

import android.view.View
import com.zenmobi.domain.models.DetailedNutrientDataResModel
import com.zenmobi.nutrifact.base.BaseViewHolder
import com.zenmobi.nutrifact.utils.roundTo
import kotlinx.android.synthetic.main.item_calorie.view.*

class CalorieVH(view: View): BaseViewHolder<DetailedNutrientDataResModel>(view) {
    var calorieDataDetailed: DetailedNutrientDataResModel? = null


    override fun setData(dataDetailed: DetailedNutrientDataResModel) {
        calorieDataDetailed = dataDetailed
        itemView.calorieNameTv.text = dataDetailed.label
        itemView.calorieValueTv.text = dataDetailed.quantity?.roundTo(2).toString()
        itemView.calorieUnitTv.text = dataDetailed.unit
    }

    enum class ViewType {
        SELECT
    }
}