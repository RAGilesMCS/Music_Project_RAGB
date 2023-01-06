package com.example.music_project_ragb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.music_project_ragb.view.Communicator
import com.example.music_project_ragb.view.DisplayFragment
import com.example.music_project_ragb.view.SearchFragment

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_search, SearchFragment()).commit()
    }
    override fun sendDataToSearch(musicGender: String){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_display, DisplayFragment.newInstance(musicGender)).addToBackStack(null).commit()
    }
}