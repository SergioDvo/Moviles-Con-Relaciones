package com.example.listas.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithHabilidadesEntity(
    @Embedded val character:  CharacterEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "characterName"
    )
    val habilidades: List<HabilidadEntity>?
)
