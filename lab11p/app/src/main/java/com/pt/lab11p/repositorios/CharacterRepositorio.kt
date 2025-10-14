package com.pt.lab11p.repositorios

import com.pt.lab11p.datos.Character
import com.pt.lab11p.entity.CharacterDao
import com.pt.lab11p.entity.toDomain

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(id: Int): Character
}

class CharacterRepositoryRoom(
    private val dao: CharacterDao
) : CharacterRepository {
    override suspend fun getCharacters(): List<Character> =
        dao.getAll().map { it.toDomain() }

    override suspend fun getCharacterById(id: Int): Character =
        requireNotNull(dao.getById(id)).toDomain()
}