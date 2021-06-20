package com.zenmobi.domain.models

data class DetailedIngredientDataResModel(
    val calories:Float?,
    val weight: String?,
    val labels: ArrayList<String>?,
    val cautions: ArrayList<String>?,
    val totalDetailedNutrientData: ArrayList<DetailedNutrientDataResModel?>?,
    val detailedIngredientsInfoRemoteMode: DetailedIngredientParsedResModel?,
    val hint: ArrayList<GeneralIngrHintDataRemoteModel>?,
    val measure: String?,
    val quantity: Float?,
    val foodId: String?,
    var image: String?
){
    var ingredientName = ""
    var isDeleted = false
}