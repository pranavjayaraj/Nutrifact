package com.zenmobi.nutrifact

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.zenmobi.domain.models.DetailedIngredientDataResModel
import com.zenmobi.nutrifact.base.BaseActivity
import com.zenmobi.nutrifact.base.BaseAdapterItemClick
import com.zenmobi.nutrifact.databinding.ActivityMainBinding
import com.zenmobi.nutrifact.utils.MarginItemDecoration
import com.zenmobi.nutrifact.utils.getViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_food_dialog.view.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    BaseAdapterItemClick<DetailedIngredientDataResModel> {

    lateinit var foodadapter: FoodsAdapter

    var ingArrayList = arrayListOf<DetailedIngredientDataResModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModelInstance(): MainViewModel {
        return viewModelFactory.getViewModel(this)
    }

    override fun getBindings(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getNutritionData("1 large apple")
        getNutritionData("1 large orange")
    }

    private fun getNutritionData(ing: String?) {
        viewModel.getNutritionData(ing)
    }

    override fun initializeAdapter() {
        super.initializeAdapter()
        foodadapter = FoodsAdapter(this, this)
        b.foodRv.itemAnimator = null
        b.foodRv.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_item)))
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        b.foodRv.layoutManager = layoutManager
        b.foodRv.adapter = foodadapter
    }

    override fun initializeClickListeners() {
        super.initializeClickListeners()
        RxView.clicks(addFood)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                viewModel.getNutritionData(et_message.text?.toString())
            }.let { }
    }

    override fun onItemClick(viewType: Int, dataDetailed: DetailedIngredientDataResModel, position: Int) {
        super.onItemClick(viewType, dataDetailed, position)
        when (viewType) {
            FoodVH.ViewType.DELETE.ordinal -> {
                ingArrayList.remove(dataDetailed)
                viewModel.calories -= dataDetailed.calories!!
                b.caloriesCountTv.text = "${viewModel.calories} Kcal"
                foodadapter.setData(ingArrayList)
            }
            FoodVH.ViewType.EDIT.ordinal -> {
                editDialog(dataDetailed,position)
            }
        }
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewModel.observeNutritionData().observe(this, {
            it.second?.ingredientName = it.first
            if (viewModel.deletedIndex != null) {
                ingArrayList.removeAt(viewModel.deletedIndex!!)
                ingArrayList.add(viewModel.deletedIndex!!, it.second!!)
                viewModel.deletedIndex = null
            } else {
                ingArrayList.add(it.second!!)
            }
            viewModel.calories += it.second!!.calories!!
            b.caloriesCountTv.text = "${viewModel.calories} Kcal"
            b.etMessage.setText("")
            foodadapter.setData(ingArrayList)
        })
    }

    @SuppressLint("InflateParams")
    private fun editDialog(dataDetailed:DetailedIngredientDataResModel, deleteIndex:Int) {
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val v = inflater.inflate(R.layout.edit_food_dialog, null)
        builder.setView(v)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
        v.textTv.setText(dataDetailed.ingredientName)
        initDialogClickListeners(alertDialog,v,deleteIndex)
        alertDialog.show()
    }

    private fun initDialogClickListeners(alertDialog: AlertDialog, v: View, deleteIndex: Int) {
        RxView.clicks(v.okayTv)
            .subscribe {
                viewModel.deletedIndex = deleteIndex
                viewModel.getNutritionData(v.textTv.text.toString())
                alertDialog.dismiss()
            }.let {

            }
    }

}