package com.example.listas.ui.CharacterList

sealed class ListEvent {
    object GetCharacters : ListEvent()
    class DeleteCharacter(val character: String) : ListEvent()
}