package com.hrhn.presentation.ui.screen.main.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrhn.domain.model.Challenge
import com.hrhn.domain.usecase.GetTodayChallengeUseCase
import com.hrhn.presentation.util.Event
import com.hrhn.presentation.util.emit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    getTodayChallengeUseCase: GetTodayChallengeUseCase,
) : ViewModel() {
    private val _today = MutableStateFlow(LocalDateTime.now())
    val today = _today.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = LocalDateTime.now()
    )

    val todayChallenge: StateFlow<Challenge?> = getTodayChallengeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = null
        )

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> get() = _message

    private val _addEvent = MutableLiveData<Event<Unit>>()
    val addEvent: LiveData<Event<Unit>> get() = _addEvent

    private val _editEvent = MutableLiveData<Event<Challenge>>()
    val editEvent: LiveData<Event<Challenge>> get() = _editEvent

    fun addTodayChallenge() = _addEvent.emit()

    fun editTodayChallenge() {
        todayChallenge.value?.let { challenge ->
            _editEvent.emit(challenge)
        }
    }
}
