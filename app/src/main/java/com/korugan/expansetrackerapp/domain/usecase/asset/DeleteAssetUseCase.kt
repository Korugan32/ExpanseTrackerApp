package com.korugan.expansetrackerapp.domain.usecase.asset

import com.korugan.expansetrackerapp.domain.repository.assets.AssetsRepository
import javax.inject.Inject

class DeleteAssetUseCase @Inject constructor(
    private val assetsRepository: AssetsRepository
) {
    suspend operator fun invoke(id: Int) {
        assetsRepository.deleteAssetsById(id)
    }
}