package com.example.formulationapp.data

import com.example.appnobasica.domain.modelo.Character
import com.example.listas.data.CharacterDao
import com.example.listas.data.modelo.toCharacter
import com.example.listas.data.modelo.toCharacterEntity
import com.example.listas.data.modelo.toHabilidadEntity
import com.example.listas.modelo.Habilidad
import javax.inject.Inject

class Repository @Inject constructor(private val characterDao: CharacterDao) {

    suspend fun addCharacter(character: Character) =
        characterDao.insert(character.toCharacterEntity())

    suspend fun getCharacters() = characterDao.getCharacters().map { it.toCharacter() }

    suspend fun getCharacter(name: String) = characterDao.getCharacter(name)?.toCharacter()


    suspend fun eliminarCharacter(character: Character) {
        val habilidades = characterDao.getHabilidades(character.name)
        characterDao.deleteCharacter(habilidades, character.toCharacterEntity())

    }


    suspend fun updateCharacter(character: Character) =
        characterDao.update(character.toCharacterEntity())

    suspend fun getCharacterWithHabilidades(name: String) =
        characterDao.getCharacterWithHabilidades(name)?.toCharacter()

    suspend fun addRandomHabilidad(character: Character, habilidad: Habilidad) =
        characterDao.insertHabilidad(habilidad.toHabilidadEntity())

}