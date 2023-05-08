package com.machinetask.roomdatabasewithflow.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.machinetask.roomdatabasewithflow.Dao.FeedDao
import com.machinetask.roomdatabasewithflow.Model.Feed

@Database(entities = [Feed::class], version = 1, exportSchema = false,)
abstract class FeedDatabase : RoomDatabase(){
    abstract fun feedDao(): FeedDao

    }
