package com.example.foody.data

import com.example.foody.data.database.RecipesDAO
import com.example.foody.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(var recipesDAO: RecipesDAO) {

    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDAO.readRecipes()
    }

    suspend fun insertRecipes(recipe: RecipesEntity) {
        recipesDAO.insertRecipe(recipe)
    }
}