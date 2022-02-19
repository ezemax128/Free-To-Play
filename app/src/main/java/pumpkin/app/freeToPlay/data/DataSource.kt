package pumpkin.app.freeToPlay.data

import pumpkin.app.freeToPlay.data.model.Game
import pumpkin.app.freeToPlay.data.model.GameEntity
import pumpkin.app.freeToPlay.data.valueObject.AppDataBase
import pumpkin.app.freeToPlay.data.valueObject.Resourse
import pumpkin.app.freeToPlay.data.valueObject.RetrofitClient

class DataSource(private val appDataBase: AppDataBase) {

    suspend fun getGamesByGenre(_genre: String): Resourse<List<Game>>{
        val lista = Resourse.Success(RetrofitClient.webService.newGameList()).data
        val listaFiltrada: MutableList<Game> = mutableListOf()
        for (i in lista) {
            if (i.genre == _genre) {
                listaFiltrada.add(i)
            }
        }
        return Resourse.Success(listaFiltrada)
    }

    suspend fun saveGameIntoRoomDB(gameEntity: GameEntity){
        appDataBase.gameDao().saveFavorite(gameEntity)
    }

    suspend fun getFavoritesGames(): Resourse<List<GameEntity>> {
        return Resourse.Success(appDataBase.gameDao().getGamesFavorites())
    }

    suspend fun deleteGameFromFavorites(id: String){
        appDataBase.gameDao().deleteGameFromFavorites(id)
    }

    suspend fun getIdListFromRoom(): List<String> {
        return appDataBase.gameDao().getIdListFromRoom()
    }
}