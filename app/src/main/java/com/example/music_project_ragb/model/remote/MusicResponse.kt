package com.example.music_project_ragb.model.remote

data class MusicResponse(
    val resultCount: Int,
    val results: List<Music>
)

data class Music(
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val artworkUrl100: String,
    val previewUrl: String,
    val collectionPrice: Double,
    val releaseDate: String,
    val currency: String
)