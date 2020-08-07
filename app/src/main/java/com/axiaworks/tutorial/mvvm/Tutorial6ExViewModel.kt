package com.axiaworks.tutorial.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axiaworks.tutorial.mvvm.repository.ConnpassRepository
import com.axiaworks.tutorial.mvvm.response.ConnpassEvents
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class Tutorial6ExViewModel : ViewModel(), KoinComponent {
    companion object {
        const val TAG = "Tutorial6ExViewModel"
    }

    private val connpassRepository: ConnpassRepository by inject()

    val isProgress: LiveData<Boolean>
        get() = _isProgress
    private val _isProgress = MutableLiveData<Boolean>(false)
    val connpassEvents: LiveData<ConnpassEvents>
        get() = _connpassEvents
    private val _connpassEvents = MutableLiveData<ConnpassEvents>()

    fun fetchTagEvents(tag: String) {
        _isProgress.value = true
        viewModelScope.launch {
            runCatching { connpassRepository.fetchTagEvents(tag) }
                .fold({
                    _connpassEvents.value = it
                    _isProgress.value = false
                }, {
                    Log.e(Tutorial6ExViewModel.TAG, "error", it)
                    _isProgress.value = false
                })
        }
    }
}