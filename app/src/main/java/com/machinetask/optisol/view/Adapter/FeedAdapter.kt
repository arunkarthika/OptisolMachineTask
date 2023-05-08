package com.machinetask.optisol.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machinetask.optisol.Model.Feed
import com.machinetask.optisol.databinding.EachRowBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FeedAdapter
 constructor(
     private val onItemClickListener: OnItemClickListener,
     private var feedList:ArrayList<Feed>)
    : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = feedList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(feedList[position]){
                binding.llFeed.setOnClickListener {
                    onItemClickListener.onItemClick(feedList[position])
                }
                binding.tvFeedName.text = this.name
                binding.tvFeedTime.text = convertUtcToLocal(this.dateTime)
                if(isLive){
                    binding.tvLive.visibility=View.VISIBLE
                }else{
                    binding.tvLive.visibility=View.GONE
                }
            }
        }
    }

    fun setFeed(feedList: ArrayList<Feed>){
        this.feedList=feedList
        notifyDataSetChanged()
    }
    private fun convertUtcToLocal(utcDateTime: String): String {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")

        val localFormat = SimpleDateFormat("dd MMM yy HH:mm aa", Locale.getDefault())
        val utcDate = utcFormat.parse(utcDateTime)

        return localFormat.format(utcDate)
    }
}
interface OnItemClickListener {
    fun onItemClick(feed: Feed)
}