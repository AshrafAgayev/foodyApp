package com.example.foody.util

class Constants {

    companion object{
        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY = "096c9627b629456783b8dc788f4177c9"


        // API Query Keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_RECIPE_INFO = "addRecipeInformation"
        const val QUERY_FILL_INGRIDIENTS = "fillIngredients"


        // ROOM database
        const val DATABASE_NAME = "recipes_database"
         const val RECIPES_TABLE = "recipes_table"
     }
}