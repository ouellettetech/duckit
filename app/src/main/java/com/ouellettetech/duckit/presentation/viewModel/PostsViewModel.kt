package com.ouellettetech.duckit.presentation.viewModel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ouellettetech.duckit.networking.DuckitApiService
import com.ouellettetech.duckit.presentation.events.PostsEvents
import com.ouellettetech.duckit.presentation.navigation.NavigationItem
import com.ouellettetech.duckit.presentation.uiState.PostsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class PostsViewModel @Inject constructor(
    application: Application,
    private val duckitApiService: DuckitApiService,
    private val sharedPreferences: SharedPreferences,
) : DuckitViewModel<PostsUIState>() {
    private lateinit var mNavController: NavController
    var getPostUpdates: Timer? = null
    // sharedPreferences.getString(SharedPrefTokenName, "").isNullOrEmpty())

    fun setupTimer() {
        viewModelScope.launch {
            if (getPostUpdates != null) {
                getPostUpdates?.cancel()
            }
            getPostUpdates = fixedRateTimer(initialDelay = 60000L, period = 60000L) {
                getPosts()
            }
        }
    }

    fun cancelTimer() {
        getPostUpdates?.cancel()
    }

    fun getPosts() {
        if (!state.value.loading) {
            updateState {
                it.copy(loading = true)
            }
            viewModelScope.launch {
                try {
                    val posts = duckitApiService.getPosts()
                    if (posts != null) {
                        updateState {
                            it.copy(posts = posts)
                        }
                    }
                    Log.d("Network", "After Network request")
                } catch (ex: Exception) {
                    Log.e("Network", "Error Getting Data", ex)
                    onEvent(PostsEvents.NetworkError)
                }
                updateState {
                    it.copy(loading = false)
                }
            }
        }
    }

    fun setNavController(nav: NavController) {
        mNavController = nav
    }

    fun onEvent(event: PostsEvents) {
        when (event) {
            PostsEvents.SigninButtonPressed -> {
                mNavController.navigate(NavigationItem.SignInScreen.route)
            }

            PostsEvents.NetworkError -> {
                updateState {
                    it.copy(networkdialog = true)
                }
            }

            PostsEvents.DismissNetworkError -> {
                updateState {
                    it.copy(networkdialog = false)
                }
            }

            is PostsEvents.DownVote -> {
                viewModelScope.launch {
                    val result = duckitApiService.downVote(event.id)
                    if (result.isSuccessful) {
                        getPosts() // Refresh screen after Downvoting to get new vote count.
                    } else {
                        updateState {
                            it.copy(networkdialog = true)
                        }
                    }
                }
            }

            is PostsEvents.UpVote -> {
                viewModelScope.launch {
                    val result = duckitApiService.upVote(event.id)
                    if (result.isSuccessful) {
                        getPosts() // Refresh screen after Downvoting to get new vote count.
                    } else {
                        updateState {
                            it.copy(networkdialog = true)
                        }
                    }
                }
            }

            PostsEvents.AddPost -> {
                mNavController.navigate(NavigationItem.AddPostScreen.route)
            }
        }
    }

    override fun initState(): PostsUIState =
        PostsUIState(
            loading = false,
            posts = null,
            networkdialog = false,
            loggedIn = false
        )
}