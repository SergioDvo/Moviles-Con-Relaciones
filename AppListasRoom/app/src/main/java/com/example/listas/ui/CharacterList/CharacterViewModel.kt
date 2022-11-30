package com.example.listas.ui.CharacterList

import androidx.lifecycle.*
import com.example.appnobasica.domain.usecases.personas.GetCharacters
import com.example.formulationapp.domain.usecases.personas.DeleteCharacter
import com.example.formulationapp.ui.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val deleteCharacter: DeleteCharacter,
) : ViewModel() {
    private val _uiState = MutableLiveData(CharacterListState())
    val uiState: LiveData<CharacterListState> get() = _uiState

    private fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterListState(characterList = getCharacters())
        }

    }

    private fun deleteCharacterByButton(string: String) {
        viewModelScope.launch {
            if (deleteCharacter(string)) {
                _uiState.value = _uiState.value?.copy(error = Constantes.PERSONAJE_ELIMINADO , characterList = getCharacters())
            } else
                _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
        }
    }
    fun handleEvent(event: ListEvent) {
        when (event) {
            is ListEvent.DeleteCharacter -> {
                deleteCharacterByButton(event.character)
            }
            is ListEvent.GetCharacters -> {
                loadCharacters()
            }
        }



    }
}