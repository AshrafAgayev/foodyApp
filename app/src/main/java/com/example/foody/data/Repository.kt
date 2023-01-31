package com.example.foody.data

import com.example.foody.data.database.RecipesEntity
import com.example.foody.model.RecipeResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {

    private val remote = remoteDataSource
    private val local = localDataSource

    suspend fun getRecipes(queries: Map<String, String>): Response<RecipeResponse> {
        return remote.getRecipes(queries)
    }

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return local.readDatabase()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        local.insertRecipes(recipesEntity)
    }
}