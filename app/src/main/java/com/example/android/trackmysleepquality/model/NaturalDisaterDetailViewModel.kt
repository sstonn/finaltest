package com.example.android.trackmysleepquality.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.trackmysleepquality.network.DisaterApi
import com.example.android.trackmysleepquality.pojo.DataDetail
import com.example.android.trackmysleepquality.pojo.DisaterDetail
import com.example.android.trackmysleepquality.pojo.NaturalDisater
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaturalDisaterDetailViewModel(val disaterUrl: String): ViewModel(){

    private val _naturalDisater = MutableLiveData<DisaterDetail>()
    val naturalDisater:LiveData<DisaterDetail>
        get() = _naturalDisater

    private val _error = MutableLiveData<Boolean>()
    val error : LiveData<Boolean>
        get() = _error

    init {
        initData()
    }

    private fun initData(){
        DisaterApi.retrofitService.getDetailProperties(disaterUrl).enqueue(object : Callback<DataDetail>{
            override fun onFailure(call: Call<DataDetail>, t: Throwable) {
                _error.value = true
            }

            override fun onResponse(call: Call<DataDetail>, response: Response<DataDetail>) {
                _naturalDisater.value = response.body()!!.list[0]
            }
        })
    }
}