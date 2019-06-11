package com.example.android.trackmysleepquality.pojo

import com.squareup.moshi.Json

data class Field(
    @Json(name="title")
    val title:String){}