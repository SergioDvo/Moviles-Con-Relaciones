package com.example.listas.modelo.usecases.character

import com.example.formulationapp.data.Repository
import javax.inject.Inject

class GetCharacterWithHabilidades @Inject constructor(private val repository: Repository){

   suspend operator fun invoke(name:String) = repository.getCharacterWithHabilidades(name)
}
