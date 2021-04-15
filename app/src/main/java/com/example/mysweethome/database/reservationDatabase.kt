package com.example.mysweethome.database

import androidx.room.Database


@Database(entities = [reservation::class], version = 1, exportSchema = false)
abstract class reservationDatabase {
    abstract val reservationDao: reservationDAO


}