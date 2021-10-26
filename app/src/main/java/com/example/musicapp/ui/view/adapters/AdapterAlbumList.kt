package com.example.musicapp.ui.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapp.data.model.spotifymodels.Playlist
import com.example.musicapp.databinding.ItemAlbumBinding
import com.example.musicapp.ui.view.navigation.albums.AlbumsFragmentInterface


class AdapterAlbumList(private val albumsFragmentInterface: AlbumsFragmentInterface) :
    RecyclerView.Adapter<AdapterAlbumList.MyViewHolder>() {

    private var playlistsItems: List<Playlist> = emptyList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = playlistsItems[position]
        holder.textViewPlayListName.text = item.name
        Glide.with(context).load(item.images[0].url).into(holder.imageViewPlayList)

        holder.materialCard.setOnClickListener {
            albumsFragmentInterface.onClickPlayList(item)
        }
    }

    override fun getItemCount(): Int = playlistsItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(it: List<Playlist>) {
        this.playlistsItems = it
        notifyDataSetChanged()
    }

    inner class MyViewHolder(binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewPlayListName = binding.textViewPlayListName
        val imageViewPlayList = binding.imageViewPlayList
        val materialCard = binding.materialCard
    }
}