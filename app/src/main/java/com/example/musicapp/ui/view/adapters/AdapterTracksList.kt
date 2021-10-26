package com.example.musicapp.ui.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapp.R
import com.example.musicapp.data.model.spotifymodels.TrackItem
import com.example.musicapp.databinding.ItemTrackBinding
import com.example.musicapp.ui.view.navigation.albums.TracksFragmentInterface

class AdapterTracksList(private val tracksFragmentInterface: TracksFragmentInterface) :
    RecyclerView.Adapter<AdapterTracksList.MyViewHolder>() {

    private var tracksList: List<TrackItem> = emptyList()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(ItemTrackBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = tracksList[position]
        holder.textViewTrackName.text = item.track!!.name
        holder.textViewArtist.text = item.track!!.artists[0].name.toString()
        Glide.with(context).load(item.track!!.album!!.images[1].url).into(holder.trackAlbumImage)

        if (item.favorite) {
            Glide.with(context).load(R.drawable.ic_favorite_fill_24).into(holder.buttonFavorite)
        } else {
            Glide.with(context).load(R.drawable.ic_favorite_border_only_24).into(holder.buttonFavorite)
        }

        holder.buttonFavorite.setOnClickListener {
            tracksFragmentInterface.onClickFavourite(tracksList, tracksList.indexOf(item))
        }
    }

    override fun getItemCount(): Int = tracksList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateTracksList(tracksList: List<TrackItem>) {
        this.tracksList = tracksList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewTrackName = binding.textViewTrackName
        val trackAlbumImage = binding.imageViewAlbumTrack
        val textViewArtist = binding.textViewArt
        val buttonFavorite = binding.buttonFavorite

    }
}