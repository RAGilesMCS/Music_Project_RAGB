package com.example.music_project_ragb.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music_project_ragb.databinding.DisplayFragmentBinding
import com.example.music_project_ragb.model.remote.MusicResponse
import com.example.music_project_ragb.model.remote.Network
import com.example.music_project_ragb.view.adapter.MusicAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DisplayFragment"

class DisplayFragment : Fragment() {

    companion object{
        const val EXTRA_MUSIC = "EXTRA_MUSIC"
        fun newInstance(musicGender: String) = DisplayFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_MUSIC, musicGender)
            }
        }
    }

    private lateinit var binding: DisplayFragmentBinding
    private lateinit var musicGender: String
    private val adapter: MusicAdapter by lazy {
        MusicAdapter(mutableListOf())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DisplayFragmentBinding.inflate(inflater, container, false)
        arguments?.let {
            musicGender = it.getString(EXTRA_MUSIC) ?: ""
            getMusicData(musicGender)
        }
        initViews()
        return binding.root
    }

    private fun getMusicData(musicGender: String) {
        Network().api.getMusicPage(musicGender)
            .enqueue(
            object : Callback<MusicResponse>{
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            updateAdapter(it)
                            Log.d(TAG, "onResponse: ${response.message()}")
                        }
                    }
                    else{
                        Log.d(TAG, "onResponse: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                    t.printStackTrace()
                }
            }
        )
    }

    private fun initViews() {
        binding.apply {
            musicListResult.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            musicListResult.adapter = adapter
        }
    }

    private fun updateAdapter(newDataSet: MusicResponse) {
        adapter.updateDataSet(newDataSet.results)
    }

}