package com.example.kotlincoroutinesmvvmflow

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincoroutinesmvvmflow.databinding.ActivityMainBinding
import com.example.kotlincoroutinesmvvmflow.model.FakeResponse
import com.example.kotlincoroutinesmvvmflow.network.RetrofitConfig
import com.example.kotlincoroutinesmvvmflow.utils.Status
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViewModel()
        setupUI()
        setupObservers()
        setUpClickListener()
    }

    private fun setUpClickListener() {
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(application, RetrofitConfig.API_SERVICE)
        )
            .get(MainViewModel::class.java)
    }

    private fun setupUI() {
        val listener = object : MainAdapter.CustomViewHolderListener {
            override fun onCustomItemClicked(pathString: String) {
                getVideoByUrl(pathString)
            }

        }
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), listener)
        binding?.recyclerView?.addItemDecoration(
            DividerItemDecoration(
                binding?.recyclerView?.context,
                (binding?.recyclerView?.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding?.recyclerView?.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding?.recyclerView?.visibility = View.VISIBLE
                        binding?.progressBar?.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        binding?.recyclerView?.visibility = View.VISIBLE
                        binding?.progressBar?.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.recyclerView?.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun getVideoByUrl(url: String) {
        viewModel.getVideoByUrl(url).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        Log.i("log", "successssss")
                    }
                    Status.ERROR -> {
                        binding?.recyclerView?.visibility = View.VISIBLE
                        binding?.progressBar?.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.recyclerView?.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun retrieveList(users: List<FakeResponse>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
