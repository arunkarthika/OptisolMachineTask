package com.machinetask.roomdatabasewithflow.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.machinetask.roomdatabasewithflow.Model.Feed
import com.machinetask.roomdatabasewithflow.Repository.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserViewModel
@ViewModelInject
constructor(private val feedRepository: FeedRepository) : ViewModel(){
    val getUser:LiveData<List<Feed>> get() =
        feedRepository.getUser.flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)

    fun insert(feed:Feed){
        viewModelScope.launch {
            feedRepository.insert(feed)
        }
    }
    fun update(feed:Feed){
        viewModelScope.launch {
            feedRepository.updateFeed(feed)
        }
    }
    fun delete(){
        viewModelScope.launch {
            feedRepository.delete()
        }
    }
}