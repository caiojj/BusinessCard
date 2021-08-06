package br.com.dio.data
/*
* A classe que está anotada com @Database precisa atender às seguintes condições:
*
* Ser uma classe abstrata que estende RoomDatabase;
* Incluir a lista de entidades associadas ao banco de dados na anotação;
* Conter um método abstrato que tenha 0 argumentos e retorne a classe que é anotada com @Dao;
*
* */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusinessCard::class], version = 2)

abstract class AppDatabase : RoomDatabase() {

    abstract fun businessDao() : BusinessCardDao

    // Segindo o padrão singleton
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}