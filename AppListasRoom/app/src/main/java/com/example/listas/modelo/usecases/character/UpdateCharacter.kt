package com.example.listas.modelo.usecases.character

import com.example.formulationapp.data.Repository
import javax.inject.Inject

class UpdateCharacter @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(character: com.example.appnobasica.domain.modelo.Character) =
        if (repository.getCharacter(character.name) != null) {
            repository.updateCharacter(character)
            true
        } else {
            false
        }

}