package pumpkin.app.freeToPlay.domain

import pumpkin.app.freeToPlay.data.model.Game
import pumpkin.app.freeToPlay.data.model.GameEntity
import pumpkin.app.freeToPlay.valueObject.Resourse

class RepoImplementer(private val dataSource: DataSource): Repository {

    override suspend fun getListByGenre(Genrex: String): Resourse<List<Game>> {
        return dataSource.getGamesByGenre(Genrex)
    }

    override suspend fun SaveFavoriteGame(game: GameEntity) {
        dataSource.saveGameIntoRoomDB(game)
    }

    override suspend fun getFavorites(): Resourse<List<GameEntity>> {
        return dataSource.getFavoritesGames()
    }

    override suspend fun deleteGame(id: String) {
        dataSource.deleteGameFromFavorites(id)
    }

    override suspend fun getIdList(): List<String> {
        return dataSource.getIdListFromRoom()
    }
}