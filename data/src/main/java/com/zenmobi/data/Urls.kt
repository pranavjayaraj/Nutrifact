package com.zenmobi.data

object Urls {
    const val BASE_URL = BuildConfig.BASE_URL
    const val YOUR_APP_ID = "your app id here"
    const val YOUR_APP_KEY = "your app key here"
    const val GET_NUTRITIONS = "api/nutrition-data?app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}"
    const val GET_GEN_FOOD_INFO = "api/food-database/v2/parser?app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}&nutrition-type=logging"
    const val GET_DETAILED_FOOD_INFO = "api/food-database/v2/nutrients?app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}"
}