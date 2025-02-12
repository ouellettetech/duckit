package com.ouellettetech.duckit.presentation.viewModel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ouellettetech.duckit.data.model.AddPostRequest
import com.ouellettetech.duckit.networking.DuckitApiService
import com.ouellettetech.duckit.presentation.events.AddPostEvents
import com.ouellettetech.duckit.presentation.navigation.NavigationItem
import com.ouellettetech.duckit.presentation.uiState.AddPostUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    application: Application,
    private val duckitApiService: DuckitApiService,
) : DuckitViewModel<AddPostUIState>() {
    private lateinit var mNavController: NavController
    var headline = ""
    var image = ""


    fun setNavController(nav: NavController) {
        mNavController = nav
    }

    fun onEvent(event: AddPostEvents) {
        when (event) {
            AddPostEvents.AddPostButtonPressed -> {
                viewModelScope.launch {
                    val result =
                        duckitApiService.post(AddPostRequest(headline = headline, image = image))
                    if (result.isSuccessful) {
                        mNavController.navigate(NavigationItem.PostsScreen.route)
                    } else {
                        updateState {
                            it.copy(networkdialog = true)
                        }
                    }
                }
            }

            AddPostEvents.NetworkError -> {
                updateState {
                    it.copy(networkdialog = true)
                }
            }

            AddPostEvents.DismissNetworkError -> {
                updateState {
                    it.copy(networkdialog = false)
                }
            }

            AddPostEvents.Close -> {
                mNavController.popBackStack()
            }
        }
    }

    fun headerUpdate(newHeadline: String) {
        headline = newHeadline
        updateState {
            it.copy(headline = headline)
        }
    }

    fun imageUpdate(newUrl: String) {
        image = newUrl
        updateState {
            it.copy(url = image)
        }
    }

    override fun initState(): AddPostUIState =
        AddPostUIState(
            loading = false,
            networkdialog = false,
            loggedIn = false,
            headline = "",
            url = ""
        )
}