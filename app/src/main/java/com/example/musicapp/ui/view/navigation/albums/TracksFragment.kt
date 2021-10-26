package com.example.musicapp.ui.view.navigation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.musicapp.core.getSavedSession
import com.example.musicapp.data.model.spotifymodels.Track
import com.example.musicapp.data.model.spotifymodels.TrackItem
import com.example.musicapp.databinding.FragmentTracksBinding
import com.example.musicapp.ui.view.adapters.AdapterAlbumList
import com.example.musicapp.ui.view.adapters.AdapterTracksList
import com.example.musicapp.ui.viewmodel.TracksViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TracksFragment : Fragment(), TracksFragmentInterface {

    private var _binding: FragmentTracksBinding? = null
    private val model: TracksViewModel by activityViewModels()
    private val binding get() = _binding!!
    private lateinit var adapterTracksList: AdapterTracksList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)

        val playlistId = arguments?.getString("playlistId")
        if (playlistId != null) {
            viewModelObserver(playlistId)
        }
        setUpRecyclerView()
        return binding.root
    }

    private fun viewModelObserver(playlistId: String) {
        model.getPlayList(getSavedSession(requireContext()), playlistId)

        model.playlistLiveData.observe(viewLifecycleOwner, {
            if (it.playListItems.isNotEmpty()) {
                adapterTracksList.updateTracksList(it.playListItems)
            }
        })
        model.isLoadingLiveData.observe(viewLifecycleOwner, {
            binding.loadingTracks.isVisible = it
        })
    }

    private fun setUpRecyclerView() {
        adapterTracksList = AdapterTracksList(this)
        val mLayout = LinearLayoutManager(context)

        binding.recyclerViewTrackList.apply {
            setHasFixedSize(true)
            layoutManager = mLayout
            adapter = adapterTracksList
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickFavourite(tracksList: List<TrackItem>, indexItem: Int) {
        if (tracksList[indexItem].favorite) {
            tracksList[indexItem].favorite = false
            model.removeTrackForUser(getSavedSession(requireContext()), tracksList[indexItem].track!!.id)
            Snackbar.make(requireView(), "Canción eliminada de favoritos", Snackbar.LENGTH_SHORT).show()
        } else {
            tracksList[indexItem].favorite = true
            model.saveTrackForUser(getSavedSession(requireContext()), tracksList[indexItem].track!!.id)
            Snackbar.make(requireView(), "Canción agregada a favoritos", Snackbar.LENGTH_SHORT).show()
        }
        adapterTracksList.updateTracksList(tracksList)
    }
}