package com.example.foody.model


import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("results")
    var results: List<Result>
)