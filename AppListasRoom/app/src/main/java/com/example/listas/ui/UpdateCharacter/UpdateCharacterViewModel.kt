package com.example.listas.ui.UpdateCharacter

import androidx.lifecycle.*
import com.example.appnobasica.domain.modelo.Character
import com.example.appnobasica.domain.usecases.personas.GetCharacters
import com.example.formulationapp.ui.Constantes
import com.example.listas.modelo.Habilidad
import com.example.listas.modelo.usecases.character.AddRandomHabilidad
import com.example.listas.modelo.usecases.character.GetCharacterByName
import com.example.listas.modelo.usecases.character.GetCharacterWithHabilidades
import com.example.listas.modelo.usecases.character.UpdateCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCharacterViewModel @Inject constructor(
    private val updateCharacter: UpdateCharacter,
    private val getCharacterByName: GetCharacterWithHabilidades,
    private val addRandomHabilidad: AddRandomHabilidad,
) : ViewModel() {

    private val _uiState = MutableLiveData(UpdateCharacterState())
    val uiState: LiveData<UpdateCharacterState> get() = _uiState


    private fun actualizarPersona(character: Character) {
        viewModelScope.launch {
            if (updateCharacter(character)) {
                _uiState.value = UpdateCharacterState(
                    character = character
                )
                _uiState.value = _uiState.value?.copy(error = Constantes.EXITO)
            } else {
                _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
            }
        }

    }

    fun handleEvent(event: UpdateEvent) {
        when (event) {
            is UpdateEvent.UpdateCharacter -> {
                actualizarPersona(event.character)
            }
            is UpdateEvent.GetCharacterWithHabilidades -> {
                getPersonaPorNombre(event.name)
            }
            is UpdateEvent.AddRandomHabilidad -> {
                addRandomHabilidad(event.nombre)
            }
        }
    }

    private fun getPersonaPorNombre(nombre: String) {
        viewModelScope.launch {
            val character = getCharacterByName(nombre)
            _uiState.value = character?.let {
                UpdateCharacterState(
                    character = it,
//                    error = Constantes.HOLA+it.name
                )
            }
        }
    }

    private fun addRandomHabilidad(nombre: String) {
        val lista = listOf(
            "Golpetaso",
            "Cachetada",
            "Proteccion",
            "Escupir",
            "Anti-robo",
            "Sed de sangre",
            "Danza",
            "Haki",
            "Terremoto",
            "Escarcha eterna",
            "Kagebunshin"
        )
        viewModelScope.launch {
            val manaRandom = (1..100).random()
            val habilidad =
                Habilidad(nombre = lista.random(), mana = manaRandom, characterName = nombre)
            getCharacterByName(nombre)?.let {
                addRandomHabilidad(it, habilidad)
                _uiState.value = UpdateCharacterState(
                    character = it.copy(habilidades = it.habilidades?.plus(habilidad))
                )
            }
        }

    }

}
