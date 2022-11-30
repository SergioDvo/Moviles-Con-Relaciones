package com.example.appnobasica.domain.usecases.personas

import com.example.formulationapp.data.Repository
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repository: Repository){

   suspend operator fun invoke() = repository.getCharacters()
}