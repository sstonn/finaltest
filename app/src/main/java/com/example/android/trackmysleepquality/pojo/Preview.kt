package com.example.android.trackmysleepquality.pojo

import com.squareup.moshi.Json

data class Preview (
    @Json(name = "url")
    val url:String){}