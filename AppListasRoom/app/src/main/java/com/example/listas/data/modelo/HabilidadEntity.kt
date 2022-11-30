package com.example.listas.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "habilidades",
    foreignKeys = [
        ForeignKey(entity = CharacterEntity::class,
            parentColumns = ["name"],
            childColumns = ["characterName"])
    ])
data class HabilidadEntity(
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "mana")
    val mana: Int,
    var characterName: String="",
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
)