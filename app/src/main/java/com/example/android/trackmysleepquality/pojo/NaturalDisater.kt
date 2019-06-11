package com.example.android.trackmysleepquality.pojo

import com.squareup.moshi.Json

data class NaturalDisater(
    @Json(name = "id")
    val id:String,
    @Json(name = "score")
    val score:Double,
    @Json(name="fields")
    val field:Field){}