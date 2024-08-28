package io.github.joaogouveia89.checkmarket.core.model

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.CheckMarketDatabase
import io.github.joaogouveia89.checkmarket.core.data.local.LOCAL_DATABASE_NAME
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        klass = CheckMarketDatabase::class.java,
        name = LOCAL_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideMarketItemDao(
        database: CheckMarketDatabase
    ): MarketItemDao = database.marketItemDao()
}