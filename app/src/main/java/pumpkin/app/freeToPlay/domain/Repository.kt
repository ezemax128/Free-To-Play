package pumpkin.app.freeToPlay.domain

import pumpkin.app.freeToPlay.data.model.Game
import pumpkin.app.freeToPlay.data.model.GameEntity
import pumpkin.app.freeToPlay.valueObject.Resourse

interface Repository {
    suspend fun getListByGenre(genre: String): Resourse<List<Game>>
    suspend fun SaveFavoriteGame(game: GameEntity)
    suspend fun getFavorites():Resourse<List<GameEntity>>
    suspend fun deleteGame(id: String)
    suspend fun getIdList(): List<String>
}