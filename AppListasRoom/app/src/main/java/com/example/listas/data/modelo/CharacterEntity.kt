package com.example.listas.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    var name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "poder")
    val poder: Int,
    @ColumnInfo(name = "sexo")
    val sexo: String,
    @ColumnInfo(name = "anime")
    val anime: String,
) {
}