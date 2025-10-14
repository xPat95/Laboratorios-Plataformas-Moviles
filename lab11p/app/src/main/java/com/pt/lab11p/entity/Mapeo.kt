package com.pt.lab11p.entity

import com.pt.lab11p.datos.Character
import com.pt.lab11p.datos.Location

fun CharacterEntity.toDomain(): Character = Character(id, name, species, status, gender, image)
fun Character.toEntity() = CharacterEntity(id, name, species, status, gender, image)

fun LocationEntity.toDomain() = Location(id, name, type, dimension)
fun Location.toEntity() = LocationEntity(id, name, type, dimension)