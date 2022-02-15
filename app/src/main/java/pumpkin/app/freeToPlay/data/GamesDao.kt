package pumpkin.app.freeToPlay.data

import androidx.room.*
import pumpkin.app.freeToPlay.presentation.UI.model.GameEntity

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