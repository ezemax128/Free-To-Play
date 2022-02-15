package pumpkin.app.freeToPlay.data.valueObject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pumpkin.app.freeToPlay.presentation.UI.model.GameEntity
import pumpkin.app.freeToPlay.data.GamesDao


@Database(entities = arrayOf(GameEntity::class), version = 1)

abstract class AppDataBase: RoomDatabase() {

    abstract fun gameDao(): GamesDao

    companion object{
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase{
            INSTANCE = INSTANCE?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "Games"
            ).build()
            return INSTANCE!!
        }
    }





}