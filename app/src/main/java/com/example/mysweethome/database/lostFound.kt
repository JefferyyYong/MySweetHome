package com.example.mysweethome.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lost_found_table")
data class lostFound(
    @PrimaryKey(autoGenerate = true)
    var lostFoundId: Int,

    @ColumnInfo(name = "lost_found_date")
    val lfDate: String,

    @ColumnInfo(name = "lost_found_location")
    val lfLocation: String,

    @ColumnInfo(name = "lost_found_item")
    val lfItem: String,

    @ColumnInfo(name = "lost_found_status")
    val lfStatus: String,


)
