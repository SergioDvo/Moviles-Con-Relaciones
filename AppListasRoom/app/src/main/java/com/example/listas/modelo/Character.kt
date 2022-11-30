package com.example.appnobasica.domain.modelo

import com.example.listas.modelo.Habilidad


data class Character(
    val name: String,
    val description: String,
    val poder: Int,
    val sexo: String,
    val anime: String,
    val image: String,
    val habilidades: List<Habilidad>?,
)





