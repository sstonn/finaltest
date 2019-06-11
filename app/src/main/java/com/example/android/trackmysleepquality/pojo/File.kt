package com.example.android.trackmysleepquality.pojo

import androidx.annotation.Nullable
import com.squareup.moshi.Json

data class File (
//        @Json(name = "description")
//        @Nullable
//        val description:String,
    @Json(name = "preview")
    val preview:Preview){}