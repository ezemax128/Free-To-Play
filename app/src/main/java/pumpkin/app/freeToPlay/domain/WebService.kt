package pumpkin.app.freeToPlay.domain

import pumpkin.app.freeToPlay.data.model.Game
import pumpkin.app.freeToPlay.valueObject.Resourse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("games?platform=pc")
    suspend fun newGameList(): List<Game>
}