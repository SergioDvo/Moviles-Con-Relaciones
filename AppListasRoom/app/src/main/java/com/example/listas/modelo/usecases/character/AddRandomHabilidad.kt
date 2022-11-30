package com.example.listas.modelo.usecases.character

import com.example.formulationapp.data.Repository
import com.example.listas.modelo.Habilidad
import javax.inject.Inject

class AddRandomHabilidad  @Inject constructor(private val repository: Repository) {

        suspend operator fun invoke(character:com.example.appnobasica.domain.modelo.Character, habilidad: Habilidad){
            repository.addRandomHabilidad(character, habilidad)
        }
}