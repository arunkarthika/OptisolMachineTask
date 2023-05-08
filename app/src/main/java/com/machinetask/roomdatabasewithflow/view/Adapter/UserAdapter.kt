package com.machinetask.roomdatabasewithflow.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.machinetask.roomdatabasewithflow.Model.Feed
import com.machinetask.roomdatabasewithflow.databinding.EachRowBinding

class UserAdapter
 constructor(
     private val onItemClickListener: OnItemClickListener,
     private var userList:ArrayList<Feed>)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(userList[position]){
                binding.llFeed.setOnClickListener {
                    onItemClickListener.onItemClick(userList[position])
                }
                binding.feedName.text = this.name
                if(isLive){
                    binding.tvLive.visibility=View.VISIBLE
                }else{
                    binding.tvLive.visibility=View.GONE
                }
            }
        }
    }

    fun setFeed(userList: ArrayList<Feed>){
        this.userList=userList
        notifyDataSetChanged()
    }
}
interface OnItemClickListener {
    fun onItemClick(feed: Feed)
}