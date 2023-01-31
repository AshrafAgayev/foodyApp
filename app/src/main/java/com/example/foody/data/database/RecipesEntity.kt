package com.example.foody.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.model.RecipeResponse
import com.example.foody.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipe: RecipeResponse) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}