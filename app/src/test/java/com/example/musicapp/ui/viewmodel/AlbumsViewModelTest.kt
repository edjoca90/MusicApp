package com.example.musicapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicapp.CoroutinesTestRule
import com.example.musicapp.domain.SpotifyUseCasesTest
import com.example.musicapp.fakes.fakeFeaturedPlaylists
import com.example.musicapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumsViewModelTest {

    private lateinit var mViewModel: AlbumsViewModel
    private var fakeSpotifyRepository = Mockito.mock(SpotifyUseCasesTest::class.java)

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Check getFeaturedPlayList function functionality`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        Mockito.doReturn(fakeFeaturedPlaylists).`when`(fakeSpotifyRepository).getFeaturedPlaylistUseCase("FakeToken")
          
        mViewModel = AlbumsViewModel(fakeSpotifyRepository)
        mViewModel.getFeaturedPlayList("FakeToken")

        val result = mViewModel.featuredPlaylistsLiveData.getOrAwaitValue().find {
            listOf(it) == fakeFeaturedPlaylists.listPlaylist!!.listItems
        }
        assertThat(result != null).isTrue()
    }


}