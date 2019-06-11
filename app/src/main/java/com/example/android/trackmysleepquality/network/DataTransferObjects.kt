package com.example.android.trackmysleepquality.network

import com.example.android.trackmysleepquality.database.DatabaseDisater
import com.example.android.trackmysleepquality.domain.Disater
import com.example.android.trackmysleepquality.pojo.Data

fun Data.asDomainModel(): List<Disater>{
    return list.map {
        com.example.android.trackmysleepquality.domain.Disater(
            id = it.id,
            score = it.score,
            title = it.field.title
        )
    }
}

fun Data.asDatabaseModel(): List<DatabaseDisater>{
    return list.map {
        com.example.android.trackmysleepquality.database.DatabaseDisater(
            id = it.id,
            score = it.score,
            title = it.field.title
        )
    }
}