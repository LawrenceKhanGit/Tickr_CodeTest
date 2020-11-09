package com.practice.mynews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.mynews.R
import com.practice.mynews.databinding.ActivityMainBinding
import com.practice.mynews.internal.LoadingState
import com.practice.mynews.model.News
import com.practice.mynews.ui.adapter.MyNewsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var newsList = emptyList<News>()
    private lateinit var binding: ActivityMainBinding
    private val myNewsviewModel:MyNewsViewModel by viewModel()

    private lateinit var myNewsAdapter: MyNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupRecyclerView()
        setupRecyclerViewData()

        myNewsviewModel.loadingState.observe(this, Observer { loadingState ->
            Log.i(TAG, "onCreate: ${loadingState}")
            when (loadingState.status) {
                LoadingState.Status.FAILED -> {
                    Toast.makeText(
                        this,
                        loadingState.msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                LoadingState.Status.RUNNING -> {
                    Toast.makeText(
                        this,
                        "Loading",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.i(TAG, "onCreateRunning: ${loadingState}")
                    pbMain.visibility = View.VISIBLE
                }

                LoadingState.Status.SUCCESS -> {
                    Toast.makeText(
                        this,
                        "Loaded",
                        Toast.LENGTH_SHORT
                    ).show()
                    pbMain.visibility = View.GONE
                }
            }
        })
    }

    private fun setupRecyclerViewData() {
        myNewsviewModel.newsList.observe(this, Observer { fetchedNewsList ->
            fetchedNewsList?.let {
                newsList = fetchedNewsList
                myNewsAdapter.results = newsList
            }
        })
    }

    private fun setupRecyclerView() {
        newsRecyclerView.apply {
            myNewsAdapter = MyNewsAdapter()
            adapter = myNewsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }
    }
}