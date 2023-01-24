package com.example.foody.data

import com.example.foody.model.RecipeResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
     remoteDataSource: RemoteDataSource
) {

    val  remote = remoteDataSource

    suspend fun getRecipes(queries:Map<String, String>) : Response<RecipeResponse>{
        return remote.getRecipes(queries)
    }
}