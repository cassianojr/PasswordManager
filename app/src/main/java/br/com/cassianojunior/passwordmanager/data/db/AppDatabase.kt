package br.com.cassianojunior.passwordmanager.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.cassianojunior.passwordmanager.data.db.entity.PasswordEntity

@Database(entities = [PasswordEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pass_manager_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}