package com.example.music_project_ragb.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {
    @GET(ENDPOINT)
    fun getMusicPage(
        @Query(TERM) musicGender: String
    ): Call<MusicResponse>
}