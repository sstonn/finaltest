package com.example.android.trackmysleepquality.network

import com.example.android.trackmysleepquality.pojo.Data
import com.example.android.trackmysleepquality.pojo.DataDetail
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory


//private const val BASE_URL = "https://api.reliefweb.int/v1/"
public var BASE_URL="https://api.reliefweb.int/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

public interface DisaterApiService{
    @GET("reports")
    fun getProperties(@Query(value = "appname")appname:String,@Query(value = "query[value]")type:String,
                      @Query(value = "preset")preset:String):
    //Call<Data>
            Deferred<Data>

    @GET("reports/{id}")
    fun getDetailProperties(@Path(value = "id") id:String):
            Call<DataDetail>
}

object DisaterApi{
    val retrofitService:DisaterApiService by lazy{ retrofit.create(DisaterApiService::class.java)}
}