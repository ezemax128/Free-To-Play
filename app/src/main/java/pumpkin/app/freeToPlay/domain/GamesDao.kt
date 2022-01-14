package pumpkin.app.freeToPlay.domain

import androidx.room.*
import pumpkin.app.freeToPlay.data.model.GameEntity
import pumpkin.app.freeToPlay.valueObject.Resourse

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(gameEntity: GameEntity)

    @Query("SELECT * FROM GameEntity")
    suspend fun getGamesFavorites(): List<GameEntity>

    @Query("SELECT id FROM GameEntity")
    suspend fun getIdListFromRoom(): List<String>

    @Query("DELETE FROM GameEntity WHERE id=:id")
    suspend fun deleteGameFromFavorites(id: String)
}