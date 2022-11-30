package com.example.listas.ui.UpdateCharacter

import com.example.appnobasica.domain.modelo.Character
import com.example.formulationapp.ui.Constantes

data class UpdateCharacterState (
    val character: Character = Character(
        Constantes.NULL,
        Constantes.NULL,
        Constantes.ZERO,
        Constantes.NULL,
        Constantes.NULL,
        Constantes.NULL,
        null,
    ),
    val error: String? = null
)