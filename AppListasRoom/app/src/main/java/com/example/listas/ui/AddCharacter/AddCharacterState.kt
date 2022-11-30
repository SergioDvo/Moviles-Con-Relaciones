package com.example.listas.ui.AddCharacter

import com.example.appnobasica.domain.modelo.Character
import com.example.formulationapp.ui.Constantes

data class AddCharacterState(
    val character: Character = Character(
        Constantes.NULL,
        Constantes.NULL,
        Constantes.ZERO,
        Constantes.NULL,
        Constantes.NULL,
        Constantes.NULL,
        null
    ),
    val error: String? = null
)
