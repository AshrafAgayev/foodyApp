package com.example.foody.data

import com.example.foody.data.network.FoodRecipesApi
import com.example.foody.model.RecipeResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

   suspend fun getRecipes(queries: Map<String, String>): Response<RecipeResponse>{
        return foodRecipesApi.getRecipes(queries)
    }
}