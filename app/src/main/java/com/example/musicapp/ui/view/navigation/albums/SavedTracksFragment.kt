package com.example.musicapp.ui.view.navigation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.core.getSavedSession
import com.example.musicapp.data.model.spotifymodels.TrackItem
import com.example.musicapp.databinding.FragmentSavedTracksBinding
import com.example.musicapp.ui.view.adapters.AdapterSavedTrackList
import com.example.musicapp.ui.view.adapters.AdapterTracksList
import com.example.musicapp.ui.viewmodel.SavedTracksViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedTracksFragment : Fragment(), SavedTracksFragmentInterface {

    private var _binding: FragmentSavedTracksBinding? = null
    private val model: SavedTracksViewModel by activityViewModels()
    private val binding get() = _binding!!
    private lateinit var adapterSavedTrackList: AdapterSavedTrackList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSavedTracksBinding.inflate(inflater, container, false)
        viewModelObserver()
        setUpRecyclerView()
        return binding.root
    }

    private fun viewModelObserver() {
        model.getUserSavedTracks(getSavedSession(requireContext()))
        model.savedTracksLiveData.observe(viewLifecycleOwner, {
            if (it.playListItems.isNotEmpty()) {
                it.playListItems.forEach { item -> item.favorite = true }
                adapterSavedTrackList.updateTracksList(it.playListItems)
            }
        })

        model.isLoadingLiveData.observe(viewLifecycleOwner, {
            binding.loadingSavedTracks.isVisible = it
        })
    }

    private fun setUpRecyclerView() {
        adapterSavedTrackList = AdapterSavedTrackList(this)
        val mLayout = LinearLayoutManager(context)

        binding.recyclerViewSavedTrackList.apply {
            setHasFixedSize(true)
            layoutManager = mLayout
            adapter = adapterSavedTrackList
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickFavourite(tracksList: List<TrackItem>, indexItem: Int) {
        model.removeTrackForUser(getSavedSession(requireContext()), tracksList[indexItem].track!!.id)
        val newTracksList = tracksList.toMutableList()
        newTracksList.remove(tracksList[indexItem])

        Snackbar.make(requireView(), "Canción eliminada de favoritos", Snackbar.LENGTH_SHORT).show()
        adapterSavedTrackList.updateTracksList(newTracksList)
    }
}