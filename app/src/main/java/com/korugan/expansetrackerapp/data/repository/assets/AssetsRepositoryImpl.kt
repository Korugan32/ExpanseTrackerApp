package com.korugan.expansetrackerapp.data.repository.assets

import com.korugan.expansetrackerapp.data.local.asset_inventory.dao.AssetsDao
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.domain.repository.assets.AssetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(private val assetsDao: AssetsDao) : AssetsRepository {
    override fun getAllAssets(): Flow<List<Assets>> {
        return assetsDao.getAllAssets()
    }

    override suspend fun insertAsset(assets: Assets) {
        assetsDao.insertAsset(assets)
    }

    override suspend fun deleteAssetsById(id: Int) {
        assetsDao.deleteAssetsById(id)
    }

    override fun getNegativeAssets(): Flow<List<Assets>> {
        return assetsDao.getNegativeAssets()
    }

    override fun getPositiveAssets(): Flow<List<Assets>> {
        return assetsDao.getPositiveAssets()
    }
}