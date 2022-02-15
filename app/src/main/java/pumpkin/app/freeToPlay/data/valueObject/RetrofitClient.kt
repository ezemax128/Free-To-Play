package pumpkin.app.freeToPlay.data.valueObject

import com.google.gson.GsonBuilder
import pumpkin.app.freeToPlay.data.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webService = Retrofit.Builder()
        .baseUrl("https://www.freetogame.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build().create(WebService::class.java)
}