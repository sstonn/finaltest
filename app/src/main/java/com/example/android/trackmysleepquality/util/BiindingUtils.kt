package com.example.android.trackmysleepquality.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.trackmysleepquality.domain.Disater
import com.example.android.trackmysleepquality.pojo.NaturalDisater

@BindingAdapter("disaterScore")
fun TextView.setDisaterScore(item: Disater){
    item?.let {
        text = String.format("%.1f",it.score)
    }
}

@BindingAdapter("disaterTitle")
fun TextView.setDisaterTitle(item: Disater){
    item?.let {
        text = it.title

    }
}