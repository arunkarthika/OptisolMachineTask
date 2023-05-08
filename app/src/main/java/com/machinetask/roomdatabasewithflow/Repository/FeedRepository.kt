package com.machinetask.roomdatabasewithflow.Repository

import androidx.annotation.WorkerThread
import com.machinetask.roomdatabasewithflow.Dao.FeedDao
import com.machinetask.roomdatabasewithflow.Model.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedRepository @Inject constructor (private val feedDao: FeedDao) {
    val getUser:Flow<List<Feed>> = feedDao.getFeed()

    @WorkerThread
    suspend fun insert(feed:Feed) = withContext(Dispatchers.IO){
        feedDao.insert(feed)
    }

    @WorkerThread
    suspend fun updateFeed(feed:Feed) = withContext(Dispatchers.IO){
        feedDao.updateFeed(feed)
    }

    @WorkerThread
    suspend fun delete() = withContext(Dispatchers.IO){
        feedDao.delete()
    }
}