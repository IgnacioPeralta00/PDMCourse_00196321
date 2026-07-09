package com.pdm.rankeuca.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdm.rankeuca.data.local.dao.OptionDao
import com.pdm.rankeuca.data.local.dao.QuestionDao
import com.pdm.rankeuca.data.local.entities.OptionEntity
import com.pdm.rankeuca.data.local.entities.QuestionEntity


@Database(
    entities = [QuestionEntity::class, OptionEntity::class],
    version = 3,
    exportSchema = false
)
abstract class RankeUcaDatabase : RoomDatabase() {

    abstract fun optionDao(): OptionDao
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: RankeUcaDatabase? = null

        fun getDatabase(context: Context): RankeUcaDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = RankeUcaDatabase::class.java,
                    name = "rankeuca_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}