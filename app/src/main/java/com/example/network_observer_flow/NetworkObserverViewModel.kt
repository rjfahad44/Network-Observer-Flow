package com.example.network_observer_flow

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkObserverViewModel @Inject constructor(@ApplicationContext context: Context): ViewModel() {

    private var networkConnectivityObserver: NetworkConnectivityObserver

    init {
        networkConnectivityObserver = NetworkConnectivityObserver(context)
    }

    fun networkObserve(): Flow<ConnectivityObserver.Status> {
        return flow {
            networkConnectivityObserver.observe().collect{
                emit(it)
            }
        }
    }
}