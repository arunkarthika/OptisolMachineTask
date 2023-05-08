package com.machinetask.optisol.Repository

import androidx.annotation.WorkerThread
import com.machinetask.optisol.Dao.FeedDao
import com.machinetask.optisol.Model.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedRepository @Inject constructor (private val feedDao: FeedDao) {
    val getFeed:Flow<List<Feed>> = feedDao.getFeed()

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