package com.machinetask.roomdatabasewithflow.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.machinetask.roomdatabasewithflow.view.Adapter.OnItemClickListener
import com.machinetask.roomdatabasewithflow.view.Adapter.UserAdapter
import com.machinetask.roomdatabasewithflow.Model.Feed
import com.machinetask.roomdatabasewithflow.ViewModel.UserViewModel
import com.machinetask.roomdatabasewithflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

//        userViewModel.delete()
        userViewModel.getUser.observe(this, Observer {response->
            userAdapter.setFeed(response as ArrayList<Feed>)
            Log.d("main", "$response")
        })
        binding.addAlarmFab.setOnClickListener {
            val bottomDialog = BottomDialogFragment()
            bottomDialog.show(supportFragmentManager, "bottom_dialog")

        }

    }


    private fun initRecyclerView() {
        userAdapter= UserAdapter(this, ArrayList())
        binding.recyclerView.apply {
            val decoration  =  DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=userAdapter
        }
    }

    override fun onItemClick(feed: Feed) {
        val argumentValue = feed
        val bottomSheet = BottomDialogFragment.newInstance(argumentValue)
        bottomSheet.show(supportFragmentManager, "bottom_sheet")
    }
}