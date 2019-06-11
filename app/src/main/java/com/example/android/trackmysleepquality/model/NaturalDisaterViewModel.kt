package com.example.android.trackmysleepquality.model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.DisaterDatabase.Companion.getDatabase
import com.example.android.trackmysleepquality.domain.Disater
import com.example.android.trackmysleepquality.network.DisaterApi
import com.example.android.trackmysleepquality.network.DisaterApiService
import com.example.android.trackmysleepquality.pojo.Data
import com.example.android.trackmysleepquality.pojo.NaturalDisater
import com.example.android.trackmysleepquality.repository.DisaterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaturalDisaterViewModel(application: Application,val context: Context):AndroidViewModel(application){

//    private var _disaters = MutableLiveData<List<NaturalDisater>>()
//    val disaters:LiveData<List<NaturalDisater>>
//    get() = _disaters

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = DisaterRepository(database)

    var  disaters : LiveData<List<Disater>> = repository.disaters

    init {
        //initData("earthquake")
        refreshDisaterData("qthang.97khtn@gmail.com","earthquake","latest")
    }

    private fun isNetworkAvailable():Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun refreshDisaterData(appname: String,type: String,preset: String){
        if(isNetworkAvailable()){
            viewModelScope.launch {
                repository.refreshDisaters(appname,type,preset)
                disaters = repository.disaters
            }
        }
    }


    /*fun initData(modeType:String){
        DisaterApi.retrofitService.getProperties("qthang.97khtn@gmail.com",modeType,"latest")
                .enqueue(object : Callback<Data> {
                    override fun onFailure(call: Call<Data>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<Data>, response: Response<Data>) {
                            val listResult:List<NaturalDisater>? = response.body()?.list
                            _disaters.value = listResult
                    }
                })
    }*/

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app:Application,val context: Context): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NaturalDisaterViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return NaturalDisaterViewModel(app,context) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
