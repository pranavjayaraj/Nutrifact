package com.zenmobi.nutrifact

import android.app.ActionBar
import android.util.Log
import android.view.View
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.domain.models.DetailedNutrientDataResModel
import com.zenmobi.nutrifact.base.BaseViewHolder
import com.zenmobi.nutrifact.utils.GlideDelegate
import kotlinx.android.synthetic.main.item_food.view.*

class FoodVH(view: View) : BaseViewHolder<DetailedIngredientDataResModel>(view) {
    var foodDataDetailed: DetailedIngredientDataResModel? = null

    init {
        RxView.clicks(itemView.deleteIv)
            .subscribe {
                adapterCallback!!.onItemClick(
                    FoodVH.ViewType.DELETE.ordinal,
                    foodDataDetailed!!,
                    adapterPosition
                )
            }.let {

            }
        RxView.clicks(itemView.editIv)
            .subscribe {
                adapterCallback!!.onItemClick(
                    ViewType.EDIT.ordinal,
                    foodDataDetailed!!,
                    adapterPosition
                )
            }.let {

            }
    }

    override fun setData(dataDetailed: DetailedIngredientDataResModel) {
        var nutrientsData: ArrayList<DetailedNutrientDataResModel?>? = null
        nutrientsData = dataDetailed.totalDetailedNutrientData
        val glide = GlideDelegate(itemView.context)
        Log.d("[IMAGE CALORIES]",dataDetailed.image.toString())
        Log.d("[IMAGE CALORIES]",dataDetailed.ingredientName)
        glide.loadUrlWithPlaceHolder(itemView.foodIv,dataDetailed.image,-1)
        foodDataDetailed = dataDetailed
        itemView.foodTv.text = dataDetailed.ingredientName
        itemView.caloriesTv.text = dataDetailed.calories.toString()
        var caloriesAdapter = CaloriesAdapter()
        itemView.calorieRv.itemAnimator = null
        val childLayoutManager = LinearLayoutManager(itemView.context)
        childLayoutManager.orientation = RecyclerView.HORIZONTAL
        itemView.calorieRv.apply {
            adapter = caloriesAdapter
            layoutManager = childLayoutManager
        }
        Log.d("[CALORIES]", "${nutrientsData?.get(0)}")
        caloriesAdapter.setData(nutrientsData)
    }

    enum class ViewType {
        DELETE,
        EDIT
    }
}