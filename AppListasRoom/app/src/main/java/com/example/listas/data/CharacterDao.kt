package com.example.listas.data

import androidx.room.*
import com.example.listas.data.modelo.CharacterEntity
import com.example.listas.data.modelo.CharacterWithHabilidadesEntity
import com.example.listas.data.modelo.HabilidadEntity

@Dao
interface CharacterDao {

    @Query("SELECT * from characters ORDER BY name ASC")
    suspend fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * from characters WHERE name = :name")
    suspend fun getCharacter(name: String): CharacterEntity?

    @Query("SELECT * from habilidades WHERE characterName = :name")
    suspend fun getHabilidades(name: String): List<HabilidadEntity>

    @Query("SELECT * from characters WHERE name = :name")
    suspend fun getCharacterWithHabilidades(name: String): CharacterWithHabilidadesEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHabilidad(item: HabilidadEntity) : Long

    @Delete
    @Transaction
    suspend fun deleteCharacter(habilidades: List<HabilidadEntity>,character: CharacterEntity)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: CharacterEntity) : Long

    @Update
    suspend fun update(item: CharacterEntity)


}