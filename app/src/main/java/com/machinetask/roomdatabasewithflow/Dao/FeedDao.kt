package com.machinetask.roomdatabasewithflow.Dao

import androidx.room.*
import com.machinetask.roomdatabasewithflow.Model.Feed
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(feed: Feed)

    @Update
    fun updateFeed(feed: Feed)

    @Query("SELECT * FROM feed ORDER BY isLive DESC")
     fun getFeed(): Flow<List<Feed>>

    @Query("DELETE FROM feed")
    fun delete()
}