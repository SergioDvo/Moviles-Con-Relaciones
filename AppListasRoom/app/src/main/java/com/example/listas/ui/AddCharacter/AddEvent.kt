package com.example.listas.ui.AddCharacter

sealed class AddEvent {
    class AddCharacter(val character: com.example.appnobasica.domain.modelo.Character) : AddEvent()
}