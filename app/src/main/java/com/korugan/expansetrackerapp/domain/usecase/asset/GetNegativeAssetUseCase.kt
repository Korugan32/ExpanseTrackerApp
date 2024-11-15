package com.korugan.expansetrackerapp.domain.usecase.asset

import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.domain.repository.assets.AssetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNegativeAssetUseCase @Inject constructor(
    private val assetsRepository: AssetsRepository
) {
    operator fun invoke(): Flow<List<Assets>> {
        return assetsRepository.getNegativeAssets()
    }
}