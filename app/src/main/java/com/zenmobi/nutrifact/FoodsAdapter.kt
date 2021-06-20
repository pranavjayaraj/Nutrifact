package com.zenmobi.nutrifact

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.nutrifact.base.BaseAdapterItemClick
import com.zenmobi.nutrifact.base.BaseViewHolder

class FoodsAdapter(
    val itemClick: BaseAdapterItemClick<DetailedIngredientDataResModel>, context: Context
) : RecyclerView.Adapter<BaseViewHolder<DetailedIngredientDataResModel>>() {

    private val mContext: Context = context
    private val addons = ArrayList<DetailedIngredientDataResModel>()
    private var prevposition: Int = -1 // current selected position

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<DetailedIngredientDataResModel> {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        val holder = FoodVH(view)
        holder.setCallbackListener(itemClick)
        return holder
    }

    fun setData(purchase1: ArrayList<DetailedIngredientDataResModel>) {
        val diff = DiffUtil.calculateDiff(DiffUtils(purchase1))
        diff.dispatchUpdatesTo(this)
        addons.clear()
        addons.addAll(purchase1)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_food
    }

    override fun getItemCount(): Int {
        return addons.size
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<DetailedIngredientDataResModel>,
        position: Int
    ) {
        holder.setData(addons[position])
    }

    inner class DiffUtils(private val newAddons: List<DetailedIngredientDataResModel>) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(positionOld: Int, positionNew: Int): Boolean {

            return addons[positionOld].calories == newAddons[positionNew].calories
        }

        override fun getOldListSize(): Int {
            return addons.size
        }

        override fun getNewListSize(): Int {
            return newAddons.size
        }

        override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
            // check type and content
            return addons[p0].calories == newAddons[p1].calories
                    && addons[p0].cautions == newAddons[p1].cautions
                    && addons[p0].labels == newAddons[p1].labels
                    && addons[p0].totalDetailedNutrientData == newAddons[p1].totalDetailedNutrientData
        }
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}