package com.example.foody.model


import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("results")
    val results: List<Result>,
)