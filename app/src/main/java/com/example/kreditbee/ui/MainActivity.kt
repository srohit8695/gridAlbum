package com.example.kreditbee.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kreditbee.adapter.DynamicFragmentAdapter
import com.example.kreditbee.databinding.ActivityMainBinding
import com.example.kreditbee.resopnse.AlbumResponse
import com.example.kreditbee.resopnse.responseStatus.Status
import com.example.kreditbee.viewModel.AlbumViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : AlbumViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AlbumViewModel::class.java]
        initViews()
    }

    private fun initViews() {


        binding.viewpager.offscreenPageLimit = 5
        binding.viewpager.addOnPageChangeListener(TabLayoutOnPageChangeListener(binding.tabs))
        binding.tabs.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // setCurrentItem as the tab position
                binding.viewpager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
//        setDynamicFragmentToTabLayout()
        initData()
    }

    private fun initData() {

        val apiAlbumData = viewModel.getAllPackageInfo()

        apiAlbumData.observe(this, Observer {
            when (it.status){
                Status.SUCCESS -> {
                    it.data?.let { it1 -> setDynamicFragmentToTabLayout(it1) }
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        })





    }

    private fun setDynamicFragmentToTabLayout(data : AlbumResponse){

        for (i in 0 until data.size) {
            // set the tab name as "Page: " + i
            val tabData = binding.tabs.newTab().setText(data[i].title)
            binding.tabs.addTab(tabData)
            binding.tabs.setTabTextColors(Color.BLACK,Color.BLACK)
        }
        val mDynamicFragmentAdapter = DynamicFragmentAdapter(
            supportFragmentManager, binding.tabs.tabCount, data
        )

        // set the adapter
        binding.viewpager.adapter = mDynamicFragmentAdapter

        // set the current item as 0 (when app opens for first time)
        binding.viewpager.currentItem = 0

    }

}