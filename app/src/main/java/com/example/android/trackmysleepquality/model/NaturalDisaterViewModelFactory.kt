package com.example.android.trackmysleepquality.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.pojo.NaturalDisater

class NaturalDisaterViewModelFactory(private val disaterUrl: String):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NaturalDisaterDetailViewModel::class.java)){
            return NaturalDisaterDetailViewModel(disaterUrl) as T
        }
        throw IllegalArgumentException("Unknow ViewModel Class")
    }
}