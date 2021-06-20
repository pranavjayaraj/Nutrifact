package com.zenmobi.nutrifact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zenmobi.domain.models.DetailedNutrientDataResModel
import com.zenmobi.nutrifact.base.BaseViewHolder

class CaloriesAdapter() : RecyclerView.Adapter<BaseViewHolder<DetailedNutrientDataResModel>>() {

    private val calories = ArrayList<DetailedNutrientDataResModel?>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<DetailedNutrientDataResModel> {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calorie, parent, false)
        val holder = CalorieVH(view = view)
        return holder
    }

    fun setData(purchase1: ArrayList<DetailedNutrientDataResModel?>?) {
        val diff = DiffUtil.calculateDiff(DiffUtils(purchase1))
        diff.dispatchUpdatesTo(this)
        calories.clear()
        calories.addAll(purchase1!!)
    }


    override fun getItemViewType(position: Int): Int {
        return R.layout.item_food
    }

    override fun getItemCount(): Int {
        return calories.size
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<DetailedNutrientDataResModel>,
        position: Int
    ) {
        holder.setData(calories[position]!!)
    }

    inner class DiffUtils(private val newAddons: List<DetailedNutrientDataResModel?>?) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(positionOld: Int, positionNew: Int): Boolean {
            return calories[positionOld]?.unit == calories[positionNew]?.unit
        }

        override fun getOldListSize(): Int {
            return calories.size
        }

        override fun getNewListSize(): Int {
            return newAddons?.size?:0
        }

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
            // check type and content
            return calories[p0]?.quantity == calories[p1]?.quantity && calories[p0]?.label == calories[p1]?.label

        }
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}