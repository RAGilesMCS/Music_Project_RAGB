package com.example.music_project_ragb

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MusicDetail : AppCompatActivity() {

    private lateinit var album_title: TextView
    private lateinit var album_song: TextView
    private lateinit var album_cover: ImageView
    private lateinit var album_artist: TextView
    private lateinit var play_stop_button: Button
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var btn_back: Button
    private lateinit var album_price: TextView
    var length = 0
    var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_detail)
        supportActionBar?.hide()

        val cover = intent.getStringExtra("COVER_EXTRA")
        val album = intent.getStringExtra("ALBUM_EXTRA")
        val song = intent.getStringExtra("SONG_EXTRA")
        val musicurl = intent.getStringExtra("SONG_URL_EXTRA")
        val artist = intent.getStringExtra("SONG_ARTIST")

        album_title = findViewById(R.id.album_title)
        album_song = findViewById(R.id.album_song)
        album_cover = findViewById(R.id.album_cover)
        play_stop_button = findViewById(R.id.music_btn)
        btn_back = findViewById(R.id.btn_back)
        album_artist = findViewById(R.id.album_artist)
        album_price = findViewById(R.id.album_price)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(musicurl)
        mediaPlayer.prepare()

        btn_back.setOnClickListener {
            if(isPlaying){
                mediaPlayer.stop()
            }
            finish()
        }

        Picasso.get()
            .load(cover)
            .resize(500,500)
            .into(album_cover)

        album_title.text = "Album: $album"
        album_song.text = "Song: $song"
        album_artist.text = "Artis: $artist"

        play_stop_button.setOnClickListener{
            if(isPlaying){
                mediaPlayer.pause()
                length=mediaPlayer.currentPosition
                isPlaying = false
                play_stop_button.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24)
            }
            else{
                mediaPlayer.start()
                isPlaying = true
                play_stop_button.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24)
            }
        }


    }
}