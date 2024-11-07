package com.korugan.expansetrackerapp.domain.repository.assets

import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import kotlinx.coroutines.flow.Flow

interface AssetsRepository {
    fun getAllAssets() : Flow<List<Assets>>
    suspend fun insertAsset(assets: Assets)
    suspend fun deleteAssetsById(id:Int)
    fun getNegativeAssets(): Flow<List<Assets>>
    fun getPositiveAssets(): Flow<List<Assets>>
}