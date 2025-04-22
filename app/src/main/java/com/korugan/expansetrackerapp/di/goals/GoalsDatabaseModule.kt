package com.korugan.expansetrackerapp.di.goals

import android.content.Context
import androidx.room.Room
import com.korugan.expansetrackerapp.data.local.goals.dao.GoalsDao
import com.korugan.expansetrackerapp.data.local.goals.database.GoalsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GoalsDatabaseModule {

    @Provides
    @Singleton
    fun provideGoalsDatabase(@ApplicationContext context: Context): GoalsDatabase {
        return Room.databaseBuilder(
            context,
            GoalsDatabase::class.java,
            "goals_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGoalsDao(goalsDatabase: GoalsDatabase): GoalsDao {
        return goalsDatabase.goalDao()
    }
}