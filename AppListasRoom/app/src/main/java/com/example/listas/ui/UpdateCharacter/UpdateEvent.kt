package com.example.listas.ui.UpdateCharacter

sealed class UpdateEvent {
    class UpdateCharacter(val character: com.example.appnobasica.domain.modelo.Character) : UpdateEvent()
    class GetCharacterWithHabilidades(val name: String) : UpdateEvent()
    class AddRandomHabilidad(val nombre:String) : UpdateEvent()
}