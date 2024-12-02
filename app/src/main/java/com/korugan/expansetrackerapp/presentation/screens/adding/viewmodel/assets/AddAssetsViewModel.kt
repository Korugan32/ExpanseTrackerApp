package com.korugan.expansetrackerapp.presentation.screens.adding.viewmodel.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import com.korugan.expansetrackerapp.domain.usecase.asset.InsertAssetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAssetsViewModel @Inject constructor(
    private val insertAssetUseCase : InsertAssetUseCase
) : ViewModel() {

    fun addAsset(assets: Assets){
        viewModelScope.launch {
            insertAssetUseCase(assets)
        }
    }

}