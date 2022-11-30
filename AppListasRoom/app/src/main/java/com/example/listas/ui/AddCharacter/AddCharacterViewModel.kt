package com.example.formulationapp.ui

import androidx.lifecycle.*
import com.example.appnobasica.domain.modelo.Character
import com.example.appnobasica.domain.usecases.personas.AddCharacterUseCase
import com.example.listas.ui.AddCharacter.AddCharacterState
import com.example.listas.ui.AddCharacter.AddEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addCharacterUseCase: AddCharacterUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(AddCharacterState())
    val uiState: LiveData<AddCharacterState> get() = _uiState


    private fun addPersona(character: Character) {
        viewModelScope.launch {
            if (addCharacterUseCase(character)) {
                _uiState.value = AddCharacterState(
                    character = character
                )
                _uiState.value = _uiState.value?.copy(error = Constantes.EXITO)
            } else {
                _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
            }
        }


    }
    fun handleEvent(event: AddEvent) {
        when (event) {
            is AddEvent.AddCharacter -> {
                addPersona(event.character)
            }
        }
    }
}

