package pumpkin.app.freeToPlay.data

import pumpkin.app.freeToPlay.presentation.UI.model.Game
import retrofit2.http.GET

interface WebService {
    @GET("games?platform=pc")
    suspend fun newGameList(): List<Game>
}