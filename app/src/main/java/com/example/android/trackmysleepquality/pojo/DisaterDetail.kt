package com.example.android.trackmysleepquality.pojo

import com.squareup.moshi.Json

data class DisaterDetail (
    @Json(name = "fields")
    val fields:FieldDetail
){}