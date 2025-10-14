package com.pt.lab11p.repositorios

import com.pt.lab11p.datos.Location
import com.pt.lab11p.entity.LocationDao
import com.pt.lab11p.entity.toDomain

interface LocationRepository {
    suspend fun getLocations(): List<Location>
    suspend fun getLocationById(id: Int): Location
}

class LocationRepositoryRoom(
    private val dao: LocationDao
) : LocationRepository {
    override suspend fun getLocations(): List<Location> =
        dao.getAll().map { it.toDomain() }

    override suspend fun getLocationById(id: Int): Location =
        requireNotNull(dao.getById(id)).toDomain()
}