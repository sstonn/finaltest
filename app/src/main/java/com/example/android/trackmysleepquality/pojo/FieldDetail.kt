package com.example.android.trackmysleepquality.pojo

import androidx.annotation.Nullable
import com.squareup.moshi.Json

data class FieldDetail (
    @Json(name = "title")
    val title:String,
    @Json(name = "file")
    val file:List<File>,
    @Json(name="body")
    val body:String,
    @Json(name = "primary_country")
    val country:Country){}