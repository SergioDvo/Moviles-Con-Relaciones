package com.example.listas.ui.CharacterList

import com.example.appnobasica.domain.modelo.Character
import com.example.formulationapp.ui.Constantes

data class CharacterListState(
    val characterList: List<Character> = emptyList(),
    val error: String? = null
)
