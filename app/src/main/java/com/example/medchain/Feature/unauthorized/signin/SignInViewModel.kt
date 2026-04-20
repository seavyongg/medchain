package com.example.medchain.Feature.unauthorized.signin

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medchain.core.application.MySharedPreference
import com.example.medchain.core.data.ClaimTokenRequest
import com.example.medchain.core.data.ConfirmInfo
import com.example.medchain.core.data.ConfirmInfoRequest
import com.example.medchain.core.data.toConfirmInfoRequest
import com.example.medchain.core.repository.di.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: SignInRepository,
    val sharePreferences : MySharedPreference
): ViewModel() {
    private val _signInState = MutableStateFlow<SignInState>(SignInState.Loading)
    private val _isLoggedIn = MutableStateFlow( sharePreferences.getToken() != null )
    val isLoggedIn : StateFlow<Boolean> = _isLoggedIn
    val signInState : StateFlow<SignInState> = _signInState
    var token by mutableStateOf("")
    fun onTokenChanged(tokenAuth: String): String {
        var message = ""
        token = tokenAuth
        if (tokenAuth.isEmpty()){
            message = "Token is required"
        }
        else if (tokenAuth.length < 10){
            message = "Token must be at least 10 characters long"
        }
        if(tokenAuth.isNotEmpty()){
            token = ConfirmInfo(tokenAuth).toConfirmInfoRequest().token
        }
        return message
    }
    fun signIn( signInRequest : ClaimTokenRequest ) {
        if ( _signInState.value is SignInState.ValidationError) return
        viewModelScope.launch {
            _signInState.value = SignInState.Loading
            try {
                val request = signInRequest.copy(claimToken = signInRequest.claimToken)
                val response = repository.userSignIn(request)
                val success = response.data.token
                if (success.isNotEmpty()) {
                    _signInState.value = SignInState.Success(response.data.token)
                    _isLoggedIn.value = true
                    sharePreferences.saveToken(response.data.token)
                } else {
                    _signInState.value = SignInState.Error("Invalid response from server")
                }
            } catch (e: Exception) {
                _signInState.value = SignInState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            sharePreferences.clearToken()
            Log.d("TAG", "User signed out.")
        }
    }
    fun resetState() {
        _signInState.value = SignInState.Idle
    }


}