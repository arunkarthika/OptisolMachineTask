package com.machinetask.optisol.view.Activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.machinetask.optisol.view.Adapter.OnItemClickListener
import com.machinetask.optisol.view.Adapter.FeedAdapter
import com.machinetask.optisol.Model.Feed
import com.machinetask.optisol.ViewModel.FeedViewModel
import com.machinetask.optisol.databinding.ActivityMainBinding
import com.machinetask.optisol.view.Fragment.BottomDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var binding: ActivityMainBinding
    private val userViewModel:FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        userViewModel.getFeed.observe(this, Observer {response->
            feedAdapter.setFeed(response as ArrayList<Feed>)
            Log.d("main", "$response")
        })

        binding.addFeed.setOnClickListener {
            val bottomDialog = BottomDialogFragment()
            bottomDialog.show(supportFragmentManager, "bottom_dialog")

        }

    }


    private fun initRecyclerView() {
        feedAdapter= FeedAdapter(this, ArrayList())
        binding.rvFeed.apply {
            val decoration  =  DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@FeedActivity)
            adapter=feedAdapter
        }
    }

    override fun onItemClick(feed: Feed) {
        val bottomSheet = BottomDialogFragment.newInstance(feed)
        bottomSheet.show(supportFragmentManager, "bottom_sheet")
    }
}