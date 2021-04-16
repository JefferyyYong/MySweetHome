package com.example.mysweethome.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface lostFoundDAO {
    //Need: Get, Update, Insert

    @Query("SELECT * FROM lost_found_table")
    //suspend fun loadAllLostFound(): List<lostFound?>?
    suspend fun loadAllLostFound():LiveData<List<lostFound>>

    @Insert
    suspend fun insert(lostFound: lostFound)

    @Update
    suspend fun update(lostFound: lostFound)

    @Query("SELECT * FROM lost_found_table WHERE lostFoundId = :key")
    suspend fun get (key:Int):lostFound?

    /*
    //
    @Insert
    suspend fun insert(night: SleepNight)

    @Update
    suspend fun update(night: SleepNight)

    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
    suspend fun get(key: Long): SleepNight?

    @Query("DELETE FROM daily_sleep_quality_table")
    suspend fun clear()

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    suspend fun getTonight(): SleepNight?

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>



    @Query("SELECT * FROM PERSON ORDER BY ID")
    fun loadAllPersons(): List<Person?>?

    @Insert
    fun insertPerson(person: Person?)

    @Update
    fun updatePerson(person: Person?)

    @Delete
    fun delete(person: Person?)

    @Query("SELECT * FROM PERSON WHERE id = :id")
    fun loadPersonById(id: Int): Person?


     */
    
}