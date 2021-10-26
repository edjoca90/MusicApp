package com.example.musicapp.ui.view.navigation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.musicapp.R
import com.example.musicapp.core.getSavedSession
import com.example.musicapp.data.model.spotifymodels.Playlist
import com.example.musicapp.databinding.FragmentAlbumsBinding
import com.example.musicapp.ui.view.adapters.AdapterAlbumList
import com.example.musicapp.ui.viewmodel.AlbumsViewModel

class AlbumsFragment : Fragment(), AlbumsFragmentInterface {

    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!
    private val model: AlbumsViewModel by activityViewModels()
    private lateinit var adapterAlbumList: AdapterAlbumList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        viewModelObserver()
        return binding.root
    }

    private fun viewModelObserver() {
        model.getFeaturedPlayList(getSavedSession(requireContext()))

        model.featuredPlaylistsLiveData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) adapterAlbumList.updateItems(it)
        })

        model.isLoadingLiveData.observe(viewLifecycleOwner, {
            binding.loadingAlbums.isVisible = it
        })
    }

    private fun setUpRecyclerView() {
        adapterAlbumList = AdapterAlbumList(this)
        val metrics = requireContext().resources.displayMetrics
        val numColumnsMetrics = ((metrics.widthPixels / metrics.density) / 180).toInt()
        val mLayout = StaggeredGridLayoutManager(numColumnsMetrics, StaggeredGridLayoutManager.VERTICAL)

        binding.recyclerViewAlbumList.apply {
            setHasFixedSize(true)
            layoutManager = mLayout
            adapter = adapterAlbumList
        }
    }

    override fun onClickPlayList(playListEntity: Playlist) {
        val bundle = Bundle()
        bundle.putString("playlistId", playListEntity.id)
        findNavController().navigate(R.id.action_navigation_albums_to_navigation_tracks, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}