package com.example.android.trackmysleepquality.pojo

import com.squareup.moshi.Json

data class DataDetail (
    @Json(name = "data")
    val list:List<DisaterDetail>){}