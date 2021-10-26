package com.example.musicapp

import com.example.musicapp.domain.SpotifyUseCasesTest
import com.example.musicapp.ui.viewmodel.AlbumsViewModel
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class CoroutinesTestRule : TestWatcher() {

    val testDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}