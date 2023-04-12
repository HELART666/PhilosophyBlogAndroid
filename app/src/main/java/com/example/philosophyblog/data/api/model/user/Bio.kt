package com.example.philosophyblog.data.api.model.user

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class Bio(
    @SerializedName("age")
    val age: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("goals")
    val goals: List<String>? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("personality")
    val personality: List<String>? = null,
    @SerializedName("philosophyDireсtion")
    val philosophyDirection: String? = null,
    @SerializedName("qualities")
    val qualities: List<String>? = null,
    @SerializedName("quote")
    val quote: String? = null,
    @SerializedName("sex")
    val sex: String? = null,
)
