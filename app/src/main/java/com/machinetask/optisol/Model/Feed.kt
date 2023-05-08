package com.machinetask.optisol.Model


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "feed", indices = [Index(value = ["name"], unique = true)])
data class Feed (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name:String,
    val isLive:Boolean,
    val dateTime:String
) : Serializable