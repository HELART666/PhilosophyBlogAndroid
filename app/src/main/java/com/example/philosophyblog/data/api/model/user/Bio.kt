package com.example.philosophyblog.data.api.model.user

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class Bio(
    @SerializedName("age")
    val age: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("goals")
    val goals: JsonArray? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("personality")
    val personality: JsonArray? = null,
    @SerializedName("philosophyDirection")
    val philosophyDirection: String? = null,
    @SerializedName("qualities")
    val qualities: JsonArray? = null,
    @SerializedName("quote")
    val quote: String? = null,
    @SerializedName("sex")
    val sex: String? = null,
)
