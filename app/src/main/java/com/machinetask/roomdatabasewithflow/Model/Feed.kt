package com.machinetask.roomdatabasewithflow.Model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "feed", indices = [Index(value = ["name"], unique = true)])
data class Feed (val name:String, val isLive:Boolean, val dateTime:String):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}