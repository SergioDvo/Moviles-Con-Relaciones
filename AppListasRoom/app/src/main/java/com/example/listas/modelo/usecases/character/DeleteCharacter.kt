package com.example.formulationapp.domain.usecases.personas

import com.example.formulationapp.data.Repository
import javax.inject.Inject

class DeleteCharacter @Inject constructor(private val repository: Repository){

    suspend operator fun invoke(string: String): Boolean {
        val character= repository.getCharacter(string)
        return if (character != null) {
            repository.eliminarCharacter(character)
            true
        } else {
            false
        }

    }
}