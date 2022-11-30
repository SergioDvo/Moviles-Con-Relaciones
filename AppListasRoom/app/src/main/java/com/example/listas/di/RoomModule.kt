package com.example.listas.di

import android.content.Context
import androidx.room.Room
import com.example.formulationapp.utils.StringProvider
import com.example.listas.data.CharacterDao
import com.example.listas.data.CharacterRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Named("assetDB")
    fun getAssetDB() : String = "database/characters.db"


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("assetDB") ruta: String
    )  : CharacterRoomDatabase =
        Room.databaseBuilder(context, CharacterRoomDatabase::class.java, "item_database")
            .createFromAsset(ruta)
            .fallbackToDestructiveMigrationFrom(1)
            .build()

    @Provides
    fun providesPersonaDao(characterDataBase: CharacterRoomDatabase) :CharacterDao =
        characterDataBase.characterDao()


    @Provides
    fun provideStringProvider(@ApplicationContext context: Context) =
        StringProvider.instance(context)
}