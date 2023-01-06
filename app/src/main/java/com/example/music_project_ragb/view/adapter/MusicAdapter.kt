package com.example.music_project_ragb.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music_project_ragb.MusicDetail
import com.example.music_project_ragb.R
import com.example.music_project_ragb.databinding.MusicItemLayoutBinding
import com.example.music_project_ragb.model.remote.Music
import com.squareup.picasso.Picasso


private const val TAG = "MusicAdapter"

class MusicAdapter(private var dataSet: MutableList<Music>): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>(){

    class MusicViewHolder(private val binding: MusicItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(musicItem: Music){
            binding.apply {
                musicTitleItem.text = musicItem.trackName
                musicAlbumItem.text = musicItem.collectionName
                musicArtisItem.text = musicItem.artistName
                Picasso.get()
                    .load(musicItem.artworkUrl100)
                    .resize(250,250)
                    .into(musicCoverItem)
            }
        }
        val itemAlbum: TextView = binding.root.findViewById(R.id.music_album_item)
        val itemTitle: TextView = binding.root.findViewById(R.id.music_title_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MusicViewHolder(
        MusicItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(dataSet[position])
        holder.itemAlbum.text = dataSet[position].collectionName
        holder.itemTitle.text = dataSet[position].trackName
        holder.itemView.setOnClickListener {v ->
            val intent = Intent(v.context, MusicDetail::class.java)
            intent.putExtra("ALBUM_EXTRA", holder.itemAlbum.text)
            intent.putExtra("SONG_EXTRA", holder.itemTitle.text)
            intent.putExtra("COVER_EXTRA", dataSet[position].artworkUrl100)
            intent.putExtra("SONG_URL_EXTRA", dataSet[position].previewUrl)
            intent.putExtra("SONG_ARTIST", dataSet[position].artistName)
            intent.putExtra("SONG_YEAR", dataSet[position].releaseDate)
            intent.putExtra("SONG.PRICE",dataSet[position].collectionPrice)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(items: List<Music>) {
        val originalSize = dataSet.size - 1
        dataSet.addAll(items)
        notifyItemRangeInserted(originalSize, 50)
    }

}