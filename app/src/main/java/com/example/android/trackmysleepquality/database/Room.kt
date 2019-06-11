package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DisaterDao{
    @Query("select * from databasedisater")
    fun getDisaters(): LiveData<List<DatabaseDisater>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( disaters: List<DatabaseDisater>)

    @Query("delete from databasedisater")
    fun deleteAll()
}

@Database(entities = [DatabaseDisater::class],version = 1,exportSchema = false)
abstract class DisaterDatabase:RoomDatabase(){
    abstract val disaterDao: DisaterDao

    companion object{
        @Volatile
        private var INSTANCE: DisaterDatabase ?= null

        fun getDatabase(context: Context): DisaterDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context,DisaterDatabase::class.java,"disater_database")
                        .fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}