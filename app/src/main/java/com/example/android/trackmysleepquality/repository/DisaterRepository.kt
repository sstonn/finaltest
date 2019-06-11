package com.example.android.trackmysleepquality.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.database.DisaterDatabase
import com.example.android.trackmysleepquality.database.asDomainModel
import com.example.android.trackmysleepquality.domain.Disater
import com.example.android.trackmysleepquality.network.DisaterApi
import com.example.android.trackmysleepquality.network.asDatabaseModel
import com.example.android.trackmysleepquality.pojo.Data
import com.example.android.trackmysleepquality.pojo.NaturalDisater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisaterRepository(val database: DisaterDatabase){

    val disaters: LiveData<List<Disater>> =
        Transformations.map(database.disaterDao.getDisaters()){
            it.asDomainModel()
        }

    suspend fun refreshDisaters(appname:String,type:String,preset: String){
        withContext(Dispatchers.IO) {
            val listdata  = DisaterApi.retrofitService.getProperties(appname,type,preset).await()
            System.out.println("RefreshDisater Again!!")
            database.disaterDao.deleteAll()
            database.disaterDao.insertAll(listdata.asDatabaseModel())
        }
    }
}