package com.korugan.expansetrackerapp.domain.usecase.asset

import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.domain.repository.assets.AssetsRepository
import javax.inject.Inject

class InsertAssetUseCase @Inject constructor(
    private val assetsRepository: AssetsRepository
) {
    suspend operator fun invoke(assets: Assets) {
        assetsRepository.insertAsset(assets)
    }
}