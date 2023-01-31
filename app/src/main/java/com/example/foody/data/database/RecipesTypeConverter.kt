package com.example.foody.data.database

import androidx.room.TypeConverter
import com.example.foody.model.RecipeResponse
import com.example.foody.model.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.converter.gson.GsonConverterFactory

class RecipesTypeConverter {

    var gson =  Gson()

    @TypeConverter
    fun foodRecipeToString(recipe: RecipeResponse) : String{
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data:String): RecipeResponse{
        val listType = object: TypeToken<RecipeResponse>() {}.type
        return gson.fromJson(data, listType)
    }

}