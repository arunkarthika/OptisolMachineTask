package com.machinetask.optisol.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.machinetask.optisol.Dao.FeedDao
import com.machinetask.optisol.Model.Feed

@Database(entities = [Feed::class], version = 1, exportSchema = false,)
abstract class FeedDatabase : RoomDatabase(){
    abstract fun feedDao(): FeedDao

    }
