package com.example.listas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listas.data.modelo.CharacterEntity
import com.example.listas.data.modelo.HabilidadEntity

@Database(entities = [CharacterEntity::class, HabilidadEntity::class], version =1, exportSchema = true)
abstract class CharacterRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(context: Context): CharacterRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterRoomDatabase::class.java,
                    "item_database"
                )
                    .createFromAsset("database/characters.db")
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}