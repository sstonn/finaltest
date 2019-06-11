package com.example.android.trackmysleepquality.pojo

import com.squareup.moshi.Json

data class Country (
    @Json(name = "name")
    val name:String){}