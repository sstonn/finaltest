package com.example.android.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.trackmysleepquality.domain.Disater

@Entity(tableName = "databasedisater")
data class DatabaseDisater constructor(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "score")
    val score: Double,
    @ColumnInfo(name = "title")
    val title: String)

fun List<DatabaseDisater>.asDomainModel(): List<Disater>{
    return map {
        Disater(
            id = it.id,
            score = it.score,
            title = it.title
        )
    }
}