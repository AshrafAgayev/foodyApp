package com.example.foody.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foody.model.RecipeResponse
import com.example.foody.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDAO {

    @Query("SELECT * FROM recipes_table ORDER by id ASC")
     fun readRecipes(): Flow<List<RecipesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipesEntity)
}