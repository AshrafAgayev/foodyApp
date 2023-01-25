package com.example.foody

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foody.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDAO {

    @Query("SELECT * FROM recipes_table ORDER by id ASC")
    suspend fun readRecipes(): Flow<List<Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe()
}