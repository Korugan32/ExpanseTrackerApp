package com.korugan.expansetrackerapp.data.local.asset_inventory.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.korugan.expansetrackerapp.data.local.asset_inventory.entity.Assets
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetsDao {

    @Query("SELECT * FROM Assets")
    fun getAllAssets() : Flow<List<Assets>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(assets: Assets)

    @Query("DELETE FROM Assets WHERE id = :id")
    suspend fun deleteAssetsById(id:Int)

    @Query("SELECT * FROM Assets WHERE amount < 0")
    fun getNegativeAssets(): Flow<List<Assets>>

    @Query("SELECT * FROM Assets WHERE amount > 0")
    fun getPositiveAssets(): Flow<List<Assets>>

}