package com.example.music_project_ragb.view

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.music_project_ragb.R
import com.example.music_project_ragb.databinding.SerchFragmentBinding
import com.example.music_project_ragb.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG = "SearchFragment"

class SearchFragment: Fragment() {
    private lateinit var binding: SerchFragmentBinding
    private lateinit var communicator: Communicator
    lateinit var musicGender: String
    lateinit var tabLayout : TabLayout
    lateinit var viewPager : ViewPager2


    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is Communicator -> communicator = context
            else -> throw Exception("Incorrect Host Activity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SerchFragmentBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        tabLayout = binding.root.findViewById(R.id.tab_layout)
        viewPager = binding.root.findViewById(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(binding.root.context as FragmentActivity)
        TabLayoutMediator(tabLayout, viewPager){ tab,index->
            when(index){
                0 -> {
                    tab.text = "Rock"
                    tab.setIcon(R.drawable.rock_icon)
                    musicGender = tab.text.toString()
                    sendSearchParams()
                }
                1 -> {
                    tab.text = "Classic"
                    tab.setIcon(R.drawable.classic_icon)
                }
                2 -> {
                    tab.text = "Pop"
                    tab.setIcon(R.drawable.pop_icon)
                }
                3 -> {
                    tab.text = "Jazz"
                    tab.setIcon(R.drawable.jazz_icon)
                }
                else -> {throw  Resources.NotFoundException("Item Not Found")}
            }
        }.attach()
        binding.apply {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager!!.currentItem = tab.position
                    musicGender = tab.text.toString()
                    Log.d(TAG, "onTabSelected: $musicGender")
                    sendSearchParams()
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {

                }
                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }

    private fun sendSearchParams() {
        communicator.sendDataToSearch(musicGender)
    }
}