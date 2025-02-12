package com.ouellettetech.duckit.presentation.viewModel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ouellettetech.duckit.data.model.SignUpRequest
import com.ouellettetech.duckit.data.model.Token
import com.ouellettetech.duckit.networking.DuckitApiService
import com.ouellettetech.duckit.presentation.events.SignInEvents
import com.ouellettetech.duckit.presentation.navigation.NavigationItem
import com.ouellettetech.duckit.presentation.uiState.SignInUIState
import com.ouellettetech.duckit.utils.Constants
import com.ouellettetech.duckit.utils.Constants.SharedPrefTokenName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    application: Application,
    private val duckitApiService: DuckitApiService,
    private val sharedPreferences: SharedPreferences,
) : DuckitViewModel<SignInUIState>() {
    private lateinit var mNavController: NavController
    var email: String = ""
    var password: String = ""

    fun setNavController(nav: NavController) {
        mNavController = nav
    }

    fun onEvent(event: SignInEvents) {
        when (event) {
            SignInEvents.SigninButtonPressed -> {
                Signin(email, password)
            }

            SignInEvents.SignUpButtonPressed -> {
                SignUp(email, password)
            }

            SignInEvents.NetworkError -> {
                updateState {
                    it.copy(networkdialog = true)
                }
            }

            SignInEvents.AccountExists -> {
                updateState {
                    it.copy(accountExists = true)
                }
            }

            SignInEvents.AccountNotFound -> {
                updateState {
                    it.copy(accountNotFound = true)
                }
            }

            SignInEvents.PasswordIncorrect -> {
                updateState {
                    it.copy(passwordIncorrect = true)
                }
            }

            SignInEvents.DismissAccountExists -> {
                updateState {
                    it.copy(accountExists = false)
                }
            }

            SignInEvents.DismissNetworkError -> {
                updateState {
                    it.copy(networkdialog = false)
                }
            }

            SignInEvents.DissmissAccountNotFound -> {
                updateState {
                    it.copy(accountNotFound = false)
                }
            }

            SignInEvents.DissmissPasswordIncorrect -> {
                updateState {
                    it.copy(passwordIncorrect = false)
                }
            }
        }
    }

    fun Signin(email: String, password: String) {
        viewModelScope.launch {
            var token: String? = null
            var response: Response<Token>? = null
            try {
                response = duckitApiService.signIn(SignUpRequest(email, password))
                token = response.body()?.token
                Log.d(
                    "Network",
                    "After Network request Code: ${response.code()} Message ${response.message()}"
                )
            } catch (ex: Exception) {
                Log.e("Network", "Error Getting Data", ex)
            }
            updateState {
                it.copy(loading = false)
            }

            if (!token.isNullOrEmpty()) {
                sharedPreferences.edit().putString(SharedPrefTokenName, token).apply()
                mNavController.navigate(NavigationItem.PostsScreen.route)
            } else {
                when (response?.code()) {
                    Constants.NETWORK_ACCOUNT_NOT_FOUND -> {
                        onEvent(SignInEvents.AccountNotFound)
                    }

                    Constants.NETWORK_PASSWORD_INCORRECT -> {
                        onEvent(SignInEvents.PasswordIncorrect)
                    }

                    else -> {
                        onEvent(SignInEvents.NetworkError)
                    }
                }
            }
        }
    }

    fun SignUp(email: String, password: String) {
        viewModelScope.launch {
            var token: String? = null
            var response: Response<Token>? = null
            try {
                response = duckitApiService.signUp(SignUpRequest(email, password))
                token = response.body()?.token
                Log.d(
                    "Network",
                    "After Network request Code: ${response.code()} Message ${response.message()}"
                )
            } catch (ex: Exception) {
                Log.e("Network", "Error Getting Data", ex)
            }
            updateState {
                it.copy(loading = false)
            }

            if (!token.isNullOrEmpty()) {
                sharedPreferences.edit().putString(SharedPrefTokenName, token).apply()
                mNavController.navigate(NavigationItem.PostsScreen.route)
            } else {
                if (response?.code() == Constants.NETWORK_ACCOUNT_EXISTS) {
                    onEvent(SignInEvents.AccountExists)
                } else {
                    onEvent(SignInEvents.NetworkError)
                }
            }
        }
    }

    fun emailUpdate(newEmail: String) {
        email = newEmail
    }

    fun passwordUpdate(newPassword: String) {
        password = newPassword
    }

    override fun initState(): SignInUIState =
        SignInUIState(
            loading = false,
            email = "",
            password = "",
            networkdialog = false,
            accountExists = false,
            passwordIncorrect = false,
            accountNotFound = false,
        )

}