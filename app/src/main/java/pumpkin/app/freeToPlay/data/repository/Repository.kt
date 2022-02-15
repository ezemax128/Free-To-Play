package pumpkin.app.freeToPlay.data.repository

import pumpkin.app.freeToPlay.presentation.UI.model.Game
import pumpkin.app.freeToPlay.presentation.UI.model.GameEntity
import pumpkin.app.freeToPlay.data.valueObject.Resourse

interface Repository {
    suspend fun getListByGenre(genre: String): Resourse<List<Game>>
    suspend fun SaveFavoriteGame(game: GameEntity)
    suspend fun getFavorites():Resourse<List<GameEntity>>
    suspend fun deleteGame(id: String)
    suspend fun getIdList(): List<String>
}