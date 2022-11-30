package com.example.appnobasica.domain.usecases.personas


import com.example.appnobasica.domain.modelo.Character
import com.example.formulationapp.data.Repository
import javax.inject.Inject

class AddCharacterUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(character: Character) =
        if (repository.getCharacter(character.name) == null) {
            repository.addCharacter(character)
            true
        } else {
            false
        }
}