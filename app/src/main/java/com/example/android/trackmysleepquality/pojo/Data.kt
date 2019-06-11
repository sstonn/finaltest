package com.example.android.trackmysleepquality.pojo

import com.squareup.moshi.Json

data class Data(
    @Json(name = "data")
    val list: List<NaturalDisater> ){}